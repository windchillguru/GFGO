package ext.appo.test;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class CustHelper
    implements Externalizable
{

    public CustHelper()
    {
    }

    public void writeExternal(ObjectOutput objectoutput) throws IOException
	{
		objectoutput.writeLong(0x499602d2L);
	}

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        long l = objectinput.readLong();
        if(l != 0x499602d2L)
            throw new InvalidClassException(CLASSNAME, (new StringBuilder()).append("Local class not compatible: stream classdesc externalizationVersionUID=").append(l).append(" local class externalizationVersionUID=").append(0x499602d2L).toString());
        else
            return;
    }

    private static final String CLASSNAME = CustHelper.class.getName();
    public static final CustService service = new CustServiceFwd();
    static final long serialVersionUID = 1L;
    public static final long EXTERNALIZATION_VERSION_UID = 0x499602d2L;

}

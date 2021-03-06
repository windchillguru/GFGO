package ext.appo.change.mvc.builder;

import com.ptc.core.components.descriptor.DescriptorConstants.ColumnIdentifiers;
import com.ptc.jca.mvc.components.JcaComponentParams;
import com.ptc.mvc.components.*;
import com.ptc.netmarkets.util.beans.NmCommandBean;
import com.ptc.netmarkets.util.beans.NmHelperBean;
import ext.appo.change.ModifyHelper;
import ext.appo.change.resource.ModifyResource;
import ext.appo.ecn.common.util.ChangeUtils;
import ext.appo.ecn.constants.ChangeConstants;
import ext.pi.core.PICoreHelper;
import org.apache.log4j.Logger;
import wt.change2.ChangeHelper2;
import wt.change2.Changeable2;
import wt.change2.WTChangeActivity2;
import wt.change2.WTChangeOrder2;
import wt.fc.QueryResult;
import wt.log4j.LogR;
import wt.util.WTException;
import wt.util.WTMessage;
import wt.vc.VersionControlHelper;
import wt.vc.Versioned;

import java.util.HashSet;
import java.util.Set;

import static ext.appo.change.constants.ModifyConstants.LINKTYPE_1;

@ComponentBuilder("ext.appo.change.mvc.builder.ChangeHistoryTableBuilder")
public class ChangeHistoryTableBuilder extends AbstractComponentBuilder {

    private static final String TABLE_ID = "ext.appo.change.mvc.builder.ChangeHistoryTableBuilder";
    private static final Logger LOGGER = LogR.getLogger(ChangeHistoryTableBuilder.class.getName());

    @Override
    public Object buildComponentData(ComponentConfig config, ComponentParams params) throws Exception {
        Set<WTChangeOrder2> order2s = new HashSet<>();

        NmHelperBean nmHelperBean = ((JcaComponentParams) params).getHelperBean();
        NmCommandBean nmCommandBean = nmHelperBean.getNmCommandBean();
        Object actionObject = nmCommandBean.getActionOid().getRefObject();
        LOGGER.info("=====actionObject: " + actionObject);
        if (actionObject instanceof Versioned) {
            QueryResult queryResult = VersionControlHelper.service.allVersionsOf((Versioned) actionObject);
            LOGGER.info("=====queryResult: " + queryResult.size());
            while (queryResult.hasMoreElements()) {
                Object object = queryResult.nextElement();
                LOGGER.info("=====object: " + object);
                String branchId = String.valueOf(PICoreHelper.service.getBranchId(object));
                LOGGER.info("=====branchId: " + branchId);
                order2s.addAll(ModifyHelper.service.queryWTChangeOrder2(branchId, LINKTYPE_1));

                QueryResult result = ChangeHelper2.service.getAffectingChangeActivities((Changeable2) object);
                while (result.hasMoreElements()) {
                    WTChangeActivity2 changeActivity2 = (WTChangeActivity2) result.nextElement();
                    LOGGER.info("=====changeActivity2:" + changeActivity2.getNumber());
                    WTChangeOrder2 changeOrder2 = ChangeUtils.getEcnByEca(changeActivity2);
                    LOGGER.info("=====changeOrder2:" + changeOrder2.getNumber());
                    order2s.add(changeOrder2);
                }
            }
        }

        LOGGER.info("=====result: " + order2s);
        return order2s;
    }

    @Override
    public ComponentConfig buildComponentConfig(ComponentParams arg0) throws WTException {
        ComponentConfigFactory factory = getComponentConfigFactory();
        TableConfig tableConfig = factory.newTableConfig();
        tableConfig.setId(TABLE_ID);
        tableConfig.setLabel(WTMessage.getLocalizedMessage(ModifyResource.class.getName(), ModifyResource.MY_CUSTOM_28));
        tableConfig.setSelectable(false);
        tableConfig.setShowCount(true);

        tableConfig.addComponent(factory.newColumnConfig(ColumnIdentifiers.ICON, false));
        tableConfig.addComponent(factory.newColumnConfig(ColumnIdentifiers.NUMBER, true));
        tableConfig.addComponent(factory.newColumnConfig(ColumnIdentifiers.NAME, true));
        tableConfig.addComponent(factory.newColumnConfig(ColumnIdentifiers.INFO_ACTION, false));
        tableConfig.addComponent(factory.newColumnConfig(ColumnIdentifiers.STATE, true));
        tableConfig.addComponent(factory.newColumnConfig(ColumnIdentifiers.CREATED_BY, true));
        tableConfig.addComponent(factory.newColumnConfig(ColumnIdentifiers.CREATED, true));
        tableConfig.addComponent(factory.newColumnConfig(ColumnIdentifiers.LAST_MODIFIED, true));
        tableConfig.addComponent(factory.newColumnConfig(ColumnIdentifiers.CONTAINER_NAME, true));

        return tableConfig;
    }

}

package ext.generic.doc.processor;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import wt.doc.WTDocument;
import wt.fc.Persistable;
import wt.log4j.LogR;
import wt.util.WTException;
import wt.util.WTMessage;

import com.ptc.core.components.beans.ObjectBean;
import com.ptc.core.components.forms.DefaultObjectFormProcessor;
import com.ptc.core.components.forms.FormProcessingStatus;
import com.ptc.core.components.forms.FormResult;
import com.ptc.core.components.forms.FormResultAction;
import com.ptc.core.components.util.FeedbackMessage;
import com.ptc.core.ui.resources.FeedbackType;
import com.ptc.netmarkets.util.beans.NmCommandBean;

import ext.generic.workflow.WorkFlowBase;

public class SubmitDocSignWorkflowProcessor extends DefaultObjectFormProcessor{
	private static final String CLASSNAME = SubmitDocSignWorkflowProcessor.class.getName();
	private static final Logger logger = LogR.getLogger(CLASSNAME);
	private static final String RESOURCE = "ext.generic.doc.resource.WorkflowRelatedRB";

	@Override
	public FormResult doOperation(NmCommandBean nmcommandbean, List<ObjectBean> objectBeanList) throws WTException {

		FormResult localFormResult = new FormResult();
		localFormResult.setStatus(FormProcessingStatus.SUCCESS);	
		FeedbackMessage feedbackmessage = null;
		Persistable persistable = nmcommandbean.getActionOid().getWtRef().getObject();
		Locale locale = nmcommandbean.getLocale();
		
		if (persistable instanceof WTDocument) {
			WTDocument doc = (WTDocument) persistable;
			logger.debug("doc="+doc);
			String workFlowName = "GenericDocWF";
			
			try{
				WorkFlowBase workFlowBase = new WorkFlowBase();
				if(workFlowBase.startWorkFlow(doc, workFlowName)){
					String msg = WTMessage.getLocalizedMessage(RESOURCE, "START_DOCSIGN_MSG", null, locale);
					feedbackmessage = new FeedbackMessage(FeedbackType.SUCCESS, null,msg, null, new String[] {});
				}
			}
			catch(Exception e){
				e.printStackTrace();
				String error = WTMessage.getLocalizedMessage(RESOURCE, "START_DOCSIGN_ERR", null, locale);
				feedbackmessage = new FeedbackMessage(FeedbackType.FAILURE, null, error + e.toString(), null, new String[] {});
			}finally{		
			}
		}
		
		if(feedbackmessage == null){
			String error = WTMessage.getLocalizedMessage(RESOURCE, "START_DOCSIGN_ERR", null, locale);
			feedbackmessage = new FeedbackMessage(FeedbackType.FAILURE, null,error, null, new String[] {});
		}

		localFormResult.addFeedbackMessage(feedbackmessage);
		localFormResult.setNextAction(FormResultAction.REFRESH_CURRENT_PAGE);	
//		URLFactory urlFactory = new URLFactory();
//		localFormResult.setForcedUrl(urlFactory .getBaseHREF());
		return localFormResult;
	}
}

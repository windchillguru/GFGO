package ext.appo.doc.filter;

import java.util.HashSet;
import java.util.Set;

import com.ptc.core.ui.validation.DefaultSimpleValidationFilter;
import com.ptc.core.ui.validation.UIValidationCriteria;
import com.ptc.core.ui.validation.UIValidationKey;
import com.ptc.core.ui.validation.UIValidationStatus;
import com.ptc.netmarkets.model.NmOid;
import com.ptc.netmarkets.workflow.NmWorkflowHelper;

import ext.com.workflow.WorkflowUtil;
import ext.generic.doc.config.DocValidatorRuleUtil;
import wt.doc.WTDocument;
import wt.enterprise.RevisionControlled;
import wt.fc.Persistable;
import wt.fc.PersistenceHelper;
import wt.fc.QueryResult;
import wt.fc.WTObject;
import wt.fc.WTReference;
import wt.org.WTUser;
import wt.session.SessionHelper;
import wt.util.WTException;
import wt.workflow.engine.WfProcess;
import wt.workflow.engine.WfState;

public class DocArchivedWorkflowFilter extends DefaultSimpleValidationFilter {
	public UIValidationStatus preValidateAction(UIValidationKey uivalidationkey,
			UIValidationCriteria uivalidationcriteria) {
		UIValidationStatus uivalidationstatus = UIValidationStatus.DISABLED;

		if ((uivalidationcriteria != null) && (uivalidationcriteria.getContextObject() != null)) {
			WTReference wtreference = uivalidationcriteria.getContextObject();
			Persistable persistable = wtreference.getObject();
			try {
				if (persistable instanceof WTDocument) {
					WTDocument doc = (WTDocument) persistable;

					WTUser currentUser = (WTUser) SessionHelper.manager.getPrincipal();

					String currentState = doc.getLifeCycleState().toString();
					boolean stateOk = false;
					if (currentState.equals("INWORK")) {
						stateOk = true;
					}

					// boolean typeOk = false;
					// TypeIdentifier typeIdentifier = TypeIdentifierUtility.getTypeIdentifier(doc);
					// String typename = typeIdentifier.getTypename();
					// if (typename.contains("com.ptc.ReferenceDocument")) {
					// typeOk = true;
					// }

					DocValidatorRuleUtil docUtil = DocValidatorRuleUtil.getInstance();
					boolean isNeedCreator = docUtil.isNeedCreator();

					boolean userOk = false;
					if (isNeedCreator) {
						WTUser creator = (WTUser) doc.getModifier().getPrincipal();
						if (PersistenceHelper.isEquivalent(currentUser, creator))
							userOk = true;
					} else {
						userOk = true;
					}

					boolean processok = true;

					NmOid nmOid = NmOid.newNmOid(PersistenceHelper.getObjectIdentifier(doc));
					QueryResult qr = NmWorkflowHelper.service.getRoutingHistoryData(nmOid);
					// 获取所有开启的流程模板名称
					Set<String> set = new HashSet<>();
					while (qr.hasMoreElements()) {
						WfProcess process = (WfProcess) qr.nextElement();
						String templateName = process.getTemplate().getName();
						if (process.getState()
								.equals(WfState.OPEN_RUNNING) /* && templateName.contains("GenericPartWF") */ ) {
							set.add(templateName);
						}
					}
					// 存在开启的流程
					if (set.size() > 0) {
						// 需过滤eca
						if (true) {
							// 移除对应流程名称,PS:GenericECAWF
							set.remove("GenericECNWF");
							set.remove("GenericECAWF");
							if (set.size() > 0) {
								processok = true;
							}
						}
					}

					// if ((stateOk) && (userOk) && (typeOk) && (processok) && (isLatestObject(doc))
					// && (!WorkflowUtil.isObjectCheckedOut(doc)))
					if ((stateOk) && (userOk) && (processok) && (isLatestObject(doc))
							&& (!WorkflowUtil.isObjectCheckedOut(doc)))
						uivalidationstatus = UIValidationStatus.ENABLED;
				}
			} catch (WTException e) {
				e.printStackTrace();
			}

		}

		return uivalidationstatus;
	}

	private static boolean isLatestObject(WTObject oldObj) {
		boolean isLstest = true;
		RevisionControlled iterated = null;
		if (oldObj instanceof RevisionControlled) {
			iterated = (RevisionControlled) oldObj;
			if (!iterated.isLatestIteration()) {
				isLstest = false;
			}
		}
		return isLstest;
	}
}
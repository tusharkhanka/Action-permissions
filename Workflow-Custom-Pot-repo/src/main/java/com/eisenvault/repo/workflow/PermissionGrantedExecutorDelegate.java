/**
 * 
 */
/**
 * @author tushar
 *
 */
package com.eisenvault.repo.workflow;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Hiten Rastogi
 * @author Tushar Khanka
 */
public class PermissionGrantedExecutorDelegate extends BaseJavaDelegate {

	private static Log logger = LogFactory
			.getLog(PermissionGrantedExecutorDelegate.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		logger.debug("In ReminderMailOOTBExecutorDelegate execute method");

		/**
		 * @templatePATH = reminder email template location in the Repository
		 */
		String templatePATH = "PATH:\"/app:company_home/app:dictionary/app:email_templates/cm:EisenVault_Email_Templates/cm:potwf-granted-email.html.ftl\"";

		/**
		 * @recipient = reviewer to whom the email needs to be sent
		 */
		/*		String groupName = (String) execution.getVariable("evwf_groupName");*/
		String recipient = (String) execution.getVariable("personEmail");
		logger.debug("single reviewer is : " + recipient );
		
		/**
		 * @groupName = group to which the email needs to be sent
		 */
		//List<String> EmailList = (List<String>) execution.getVariable("wf_groupMembersEmail");
		//List<String> groupName = EmailList;
		//logger.debug("group reviewers email addresses are: " + groupName );
		
		/**
		 * @workflowDescription = description of the workflow
		 */
		String workflowDescription = (String) execution.getVariable("bpm_workflowDescription");
		
		/**
		 * taskId = taskId of the current task
		 */
		String taskId = "activiti$"	+ (String) execution.getVariable("evwf_taskID");
		
		/**
		 * @subject = subject of the email
		 */
		String subject = "Permission Rejected : ";
		// Get workflow package noderef
		ScriptNode scriptnode = (ScriptNode) execution.getVariable("bpm_package");
		NodeRef packageNodeRef = scriptnode.getNodeRef();
		//NodeRef packageNodeRef1 = scriptnode1.getNodeRef();
		CustomWorkflowUtil customWorkflowUtil = new CustomWorkflowUtil();
			logger.debug(" Inside else recipent is not null " + recipient );
			//CustomWorkflowUtil customWorkflowUtil = new CustomWorkflowUtil();
			customWorkflowUtil.prepareRejectionMail(workflowDescription,
					templatePATH, recipient, taskId,  subject,
					packageNodeRef);
		
		}

	}

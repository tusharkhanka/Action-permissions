package com.eisenvault.workflow;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.ParameterDefinitionImpl;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.repo.workflow.WorkflowModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.security.AuthorityService;
import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.alfresco.service.cmr.workflow.WorkflowService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
//import com.eisenvault.repo.script.WorkflowHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InitWorkflow extends ActionExecuterAbstractBase{
	public WorkflowService workflowService;
	private AuthorityService authorityService;
	private NodeService nodeService;
	
	
	private static final String GROUP_NAME = "GROUP_Incharge";
	private static final String WORKFLOW_DESCRIPTION = "reason";
	private static final String TASK_DESCRIPTION = "Request for approval";
	private static final String ROLE_REQUIRED = "required_Role";
	private static final String ACTIVITI_PARALLEL_GROUP_REVIEW_NAME = "activiti$activitiPermissionProcess";
	
	
    public void setAuthorityService(ServiceRegistry serviceRegistry) {
   this.authorityService = serviceRegistry.getAuthorityService();
    }
   
    public void setNodeService(NodeService nodeService) {
   this.nodeService = nodeService;
    }
    
    public void setAuthorityService(AuthorityService authorityService) {
    	   this.authorityService = authorityService;
    	    }
   
    public void setWorkflowService(WorkflowService workflowService) {
 	   this.workflowService = workflowService;
 	    }

    private static Log logger = LogFactory
			.getLog(InitWorkflow.class);

	@Override
	protected void executeImpl(Action action, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
		 // Required repo services should be injected by using Spring beans
		String reason = (String) action.getParameterValue(WORKFLOW_DESCRIPTION);
		String roleRequired = (String) action.getParameterValue(ROLE_REQUIRED);
		NodeRef workflowNodeRef = workflowService.createPackage(null);
	     
	    // Set WF properties: don't forget to start group short name with 'GROUP_'
	    Map<QName, Serializable> parameters = new HashMap<QName, Serializable>();
	    parameters.put(WorkflowModel.ASSOC_PACKAGE, workflowNodeRef);
	    parameters.put(WorkflowModel.PROP_WORKFLOW_DESCRIPTION, reason);
	    parameters.put(PotentiaEisenvaultDocModel.ROLE_REQUIRED, roleRequired);
	    parameters.put(WorkflowModel.PROP_DESCRIPTION, TASK_DESCRIPTION);
	    parameters.put(WorkflowModel.ASSOC_GROUP_ASSIGNEE, authorityService.getAuthorityNodeRef("GROUP_" + GROUP_NAME));
	    parameters.put(WorkflowModel.PROP_PERCENT_COMPLETE, 50);
	     
	    // Add zero or more items
	    nodeService.addChild(
	            workflowNodeRef, 
	            actionedUponNodeRef, 
	            WorkflowModel.ASSOC_PACKAGE_CONTAINS, 
	            QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI,
	                QName.createValidLocalName(nodeService.getProperty(
	                		actionedUponNodeRef, ContentModel.PROP_NAME).toString())));
	     
	    // Start workflow
	    WorkflowDefinition wfDefinition = 
	            workflowService.getDefinitionByName(ACTIVITI_PARALLEL_GROUP_REVIEW_NAME);
	    workflowService.startWorkflow(wfDefinition.getId(), parameters);
	 
	}
	@Override
    protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
        for (String s : new String[]{WORKFLOW_DESCRIPTION, ROLE_REQUIRED}) {
            paramList.add(new ParameterDefinitionImpl(s, DataTypeDefinition.TEXT, true, getParamDisplayLabel(s)));
        }
    }       
}


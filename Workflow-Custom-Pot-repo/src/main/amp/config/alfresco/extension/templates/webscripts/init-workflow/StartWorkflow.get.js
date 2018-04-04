// Get the node on which workflow is to be started
var theNode = roothome.childByNamePath(url.extension);

logger.log("theNode=" + theNode);

if (theNode == undefined) {
    status.code = 404;
    status.message = "Node at " + url.extension + " does not exist";
    status.redirect = true;
}
else if (theNode.isContainer) {
    status.code = 404;
    status.message = "Node at " + url.extension + " is not a content node";
    status.redirect = true;
}
else {
    var workflowPackage = workflow.createPackage();
    workflowPackage.addNode(theNode);
    var workflowDef = workflow.getDefinitionByName("activiti$activitiReview");
    var parameters = new Object();
    parameters["bpm:assignee"] = person;
    var workflowPath = workflowDef.startWorkflow(workflowPackage, parameters);

    if (workflowPath == undefined) {
        status.code = 500;
        status.message = "Error starting workflow";
        status.redirect = true;
    }

    status.message = "Done";}
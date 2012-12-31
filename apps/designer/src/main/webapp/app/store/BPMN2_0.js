Ext.define('Workflow.store.BPMN2_0', {
    extend: 'Ext.data.TreeStore',
    requires: 'Workflow.model.BPMN2_0',
    model: 'Workflow.model.BPMN2_0',
    
    autoLoad: true,
    
    defaultRootProperty: 'stencils'
    	
});
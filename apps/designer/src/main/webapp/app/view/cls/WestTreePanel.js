Ext.define('Workflow.view.cls.WestTreePanel', {
	extend: 'Ext.tree.Panel',
	
	store: 'BPMN2_0',
	title: 'Shape Repository',
	
	region:'west',
    minWidth : 130,
    maxWidth : 280,
//    split: true,
    collapsible: true,
    border: true,
    
    defaultMargins : {top:0, right:0, bottom:-1, left:1},
	defaults: {
        // applied to each contained panel
        bodyStyle: 'padding:0px'
    },
    layoutConfig: {
        // layout-specific configs go here
        titleCollapse: true,
        animate: true,
        activeOnTop: true
    },   
	 
	initComponent: function() {
		
		this.callParent(arguments);
	}
	
});
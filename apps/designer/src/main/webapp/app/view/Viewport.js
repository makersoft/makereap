Ext.define('Workflow.view.Viewport',{
    extend: 'Ext.Viewport',
    requires : [
        'Workflow.view.cls.NorthToolbar',
        'Workflow.view.cls.WestTreePanel'
//        'Workflow.view.cls.TabPanel',
//        'Workflow.view.cls.South'
    ],
    
    id: 'viewport',
    layout: 'border',
    hideBorders: true,
    defaults: { xtype: 'container' },
    
    initComponent : function(){
        this.items = [
            Ext.create('Workflow.view.cls.NorthToolbar'),
            Ext.create('Workflow.view.cls.WestTreePanel')
//            Ext.create('Workflow.view.cls.TabPanel'),
//            Ext.create('Workflow.view.cls.South')
        ];
        
        this.callParent(arguments);
    }
});
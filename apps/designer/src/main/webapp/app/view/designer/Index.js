Ext.define('Workflow.view.designer.Index' ,{
    extend: 'Ext.panel.Panel',
    alias : 'widget.designer',

    title : 'Horse Workflow Designer',
    width:'auto',
    height:'auto',
    resizable:false,
    closable:true,
    html:'<span style="font-size:11px;">正在初始化...</span>',
    initComponent: function() {
    	this.renderTo = Ext.getBody();
//    	this.bodyStyle = 'padding: 8px;background:white';
    	this.bodyStyle = {
		    background: 'white',
		    padding: '10px'
    	};
//        this.store = {
//            fields: ['name', 'email'],
//            data  : [
//                {name: 'Ed',    email: 'ed@sencha.com'},
//                {name: 'Tommy', email: 'tommy@sencha.com'}
//            ]
//        };
//
//        this.columns = [
//            {header: 'Name',  dataIndex: 'name',  flex: 1},
//            {header: 'Email', dataIndex: 'email', flex: 1}
//        ];
        this.callParent(arguments);
    }
});
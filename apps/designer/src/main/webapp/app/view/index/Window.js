Ext.define('Workflow.view.index.Window' ,{
    extend: 'Ext.window.Window',
    alias : 'widget.indexwindow',

    title : 'All Users',
    width:'auto',
    height:'auto',
    modal:true,
    resizable:false,
    closable:false,
    html:'<span style="font-size:11px;">xxx</span>',
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
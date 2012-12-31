Ext.define('Workflow.view.index.LoadingWindow' ,{
    extend: 'Ext.window.Window',
    alias : 'widget.loadingWindow',

    width:'auto',
    height:'auto',
    modal:true,
    resizable:false,
    closable:false,
    bodyStyle : {
	    background: 'white',
	    padding: '8px'
	},
	layout: 'fit',
//  frame: true,
//	headerPosition :'bottom',
	header : false,
	
    initComponent: function() {
    	this.renderTo = Ext.getBody();
    	this.html = '<span style="font-size:12px;">正在初始化...</span>',
        this.callParent(arguments);
    }
});
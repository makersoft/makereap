Ext.define('Workflow.controller.toolbar.Repository', {
    extend: 'Ext.app.Controller',

    views: [
        'cls.NorthToolbar'
    ],
        
    init: function() {
        console.log('Initialized Repository!');
        
        this.control({
        	'viewport > toolbar': {
        		render: this.onPanelRendered
        	}
        });
       
       
    },

    onPanelRendered: function() {
        console.log('The toolbar was rendered');
    }
});
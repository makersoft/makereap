/**
 * Main application definition for Docs app.
 *
 * We define our own Application class because this way we can also
 * easily define the dependencies.
 */
Ext.define('Workflow.Application', {
    extend: 'Ext.app.Application',
    name: 'Workflow',
    appFolder: 'static/app',
    autoCreateViewport: true,
    enableQuickTips: true,
    
    controllers: [
        'Index',
		'toolbar.Repository'
    ],

    launch: function(profile) {
        Workflow.App = this;

        this.setConfig();
        
//        alert(this.getLanguage());
        // When google analytics event tracking script present on page
        if (Workflow.initEventTracking) {
            Workflow.initEventTracking();
        }
    },
    
    setConfig : function(){
    	Workflow.CONFIG = {};
        
        Ext.applyIf(Workflow.CONFIG, {
        	AUTO_SAVE : false
        })
    },
    
    getLanguage: function(){
    	var language;
        if (navigator.language) {
        	language = navigator.language;
    	} else {
    		language = navigator.browserLanguage;
    	}

        return language;
    }

});
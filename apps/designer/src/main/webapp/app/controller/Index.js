Ext.define('Workflow.controller.Index', {
    extend: 'Ext.app.Controller',

    views: [
        'index.LoadingWindow',
        'cls.WestTreePanel'
    ],
    
    models: ['BPMN2_0', 'PropertyPackage', 'Property', 'Stencil'],
    
    stores: ['BPMN2_0'],
    
    refs:[{
        ref: 'loadingWindow',
        selector: 'loadingWindow'
    }],
        
    init: function(application) {
        console.log('Initialized Index! This happens before the Application launch function is called');
       
        this.control({
        	'loadingWindow': {
        		render: this.onPanelRendered
        	},
        	'viewport > treepanel':{
        		render: this.onTreePanelRendered
        	}
        });
        
    },
    
    onLaunch: function() {
//    	this.showLoadingWindow();
//    	this.setMissingClasses();
//    	alert(this.getLoadingWindow());
    	
    	var bpmnStore = this.getBPMN2_0Store();        
    	bpmnStore.load({
            callback: this.onBpmnLoad,
            scope: this
        });
    	
    },
    
    onBpmnLoad: function() {
//        var stationsList = this.getStationsList();
//        stationsList.getSelectionModel().select(0);
//    	console.log(this.getBPMN2_0Store().getAt(0))
    },
    
    /**
     * 正在加载
     */
    showLoadingWindow: function(){
    	var view = Ext.widget('loadingWindow');
        view.show();
    },
    
    hideLodingWindow: function(){
    	var view = Ext.widget('loadingWindow');
    	view.hide();
    },
    
    /*
     * @Deprecated
     */
    setMissingClasses : function(){
    	try {
    		SVGElement;
    		console.log('SVGElement enabale');
            
    	} catch(e) {
    		WAPAMA.Editor.SVGClassElementsAreAvailable = false;
    		SVGSVGElement 		= document.createElementNS('http://www.w3.org/2000/svg', 'svg').toString();
    		SVGGElement 		= document.createElementNS('http://www.w3.org/2000/svg', 'g').toString();
    		SVGPathElement 		= document.createElementNS('http://www.w3.org/2000/svg', 'path').toString();
    		SVGTextElement 		= document.createElementNS('http://www.w3.org/2000/svg', 'text').toString();
    		//SVGMarkerElement 	= document.createElementNS('http://www.w3.org/2000/svg', 'marker').toString();
    		SVGRectElement 		= document.createElementNS('http://www.w3.org/2000/svg', 'rect').toString();
    		SVGImageElement 	= document.createElementNS('http://www.w3.org/2000/svg', 'image').toString();
    		SVGCircleElement 	= document.createElementNS('http://www.w3.org/2000/svg', 'circle').toString();
    		SVGEllipseElement 	= document.createElementNS('http://www.w3.org/2000/svg', 'ellipse').toString();
    		SVGLineElement	 	= document.createElementNS('http://www.w3.org/2000/svg', 'line').toString();
    		SVGPolylineElement 	= document.createElementNS('http://www.w3.org/2000/svg', 'polyline').toString();
    		SVGPolygonElement 	= document.createElementNS('http://www.w3.org/2000/svg', 'polygon').toString();
    		
    	}
    },
    
    onPanelRendered: function() {
        console.log('The panel was rendered');
    },
    
    onTreePanelRendered: function(){
    	console.log('The tree panel was rendered');
    }
});
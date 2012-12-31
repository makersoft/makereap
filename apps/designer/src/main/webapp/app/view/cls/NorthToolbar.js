Ext.define('Workflow.view.cls.NorthToolbar', {
	extend: 'Ext.toolbar.Toolbar',
	
    initComponent: function() {
        Ext.applyIf(this, {
            region: 'north',
            items: [{
            	icon : Ext.CONTEXT_ROOT + '/static/resources/images/disk_multiple_disabled.png',
            	tooltip: 'tooltip',
            	handler: function() {
            		if(Workflow.CONFIG.AUTO_SAVE){
            			this.setIcon(Ext.CONTEXT_ROOT + '/static/resources/images/disk_multiple_disabled.png');
            			Workflow.CONFIG.AUTO_SAVE = false;
            		} else {
            			this.setIcon(Ext.CONTEXT_ROOT + '/static/resources/images/disk_multiple.png');
            			Workflow.CONFIG.AUTO_SAVE = true;
            		}
            		
                }
            }, '-', {
                name : 'cut',
                description : 'description',
                icon: Ext.CONTEXT_ROOT + "/static/resources/images/cut.png"
            }, {
            	name : 'copy',
                description : 'description',
                icon: Ext.CONTEXT_ROOT + "/static/resources/images/page_copy.png"
            }, {
            	name : 'paste',
                description : 'description',
                icon: Ext.CONTEXT_ROOT + "/static/resources/images/page_paste.png"
            }, {
            	name : 'del',
                description : 'description',
                icon: Ext.CONTEXT_ROOT + "/static/resources/images/cross.png"
            }, '-', {
            	name : 'btf',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/shape_move_front.png"
            }, {
            	name : 'btb',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/shape_move_back.png"
            }, {
            	name : 'bf',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/shape_move_forwards.png"
            }, {
            	name : 'bb',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/shape_move_backwards.png"
            }, '-', {
            	name : 'undo',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/arrow_undo.png"
            }, {
            	name : 'redo',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/arrow_redo.png"
            }, '-', {
            	name : 'AddDocker.add',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/vector_add.png",
            }, {
            	name : 'AddDocker.del',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/vector_delete.png"
            }, '-', {
            	name : 'ab',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/shape_align_bottom.png"
            }, {
            	name : 'am',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/shape_align_middle.png"
            }, {
            	name : 'at',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/shape_align_top.png"
            }, {
            	name : 'al',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/shape_align_left.png"
            }, {
            	name : 'ac',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/shape_align_center.png"
            }, {
            	name : 'ar',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/shape_align_right.png"
            }, {
            	name : 'as',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/shape_align_size.png"
            }, '-', {
            	name : 'zoomIn',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/magnifier_zoom_in.png"
            }, {
            	name : 'zoomOut',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/magnifier_zoom_out.png"
            }, {
            	name : 'zoomStandard',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/zoom_standard.png",
            	disabled :true
            }, {
            	name : 'zoomFitToModel',
            	icon: Ext.CONTEXT_ROOT + "/static/resources/images/image.png",
            	disabled :true
            }]
        });
        this.callParent(arguments);
    },
    
    getItems : function(){
    	var iconRoot = Ext.CONTEXT_ROOT + "/static/resources/images/";
    	
    	var items = [];
    	
    	return items;
    }
});
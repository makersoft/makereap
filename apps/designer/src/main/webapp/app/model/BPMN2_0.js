Ext.define('Workflow.model.BPMN2_0', {
    extend: 'Ext.data.Model',
    fields: ['title', 'namespace', 'description'],
    
    hasMany: [
      {model: 'PropertyPackage', name: 'propertyPackages'},
      {model: 'Stencil', name: 'stencils'}
//      {model: 'Rule', name: 'rules'}
    ],
    
    proxy: {
        type: 'ajax',
        url: 'static/data/stencilsets/bpmn2.0/bpmn2.0.json',
        reader: {
            type: 'json'
        }
    }
});
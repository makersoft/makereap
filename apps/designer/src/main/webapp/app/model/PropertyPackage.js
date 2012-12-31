Ext.define('Workflow.model.PropertyPackage', {
    extend: 'Ext.data.Model',
    fields: ['name'],
    
    hasMany: [
      {model: 'Property', name: 'properties'}
    ]

});
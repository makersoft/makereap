Ext.define('Workflow.model.Stencil', {
    extend: 'Ext.data.Model',
    fields: ['type', 'id', 'title', 'groups', 'description', 'view', 'icon'],
    
    hasMany: [
      {model: 'PropertyPackage', name: 'propertyPackages'}
    ]

});
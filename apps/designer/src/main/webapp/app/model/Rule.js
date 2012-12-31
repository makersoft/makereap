Ext.define('Workflow.model.Stencil', {
    extend: 'Ext.data.Model',

    hasMany: [
      {model: 'PropertyPackage', name: 'cardinalityRules'}
    ]

});
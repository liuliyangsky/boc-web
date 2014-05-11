isc.SDynamicForm.create({
    ID:"addRoleForm",
    width:"500",
    height:"100%",
    autoDraw:false,
    wrapItemTitles:false,
    cellPadding:5,
    numCols:2,
    colWidths:[100, "*"],
    dataSource: "systemRoleDS",
    fields:[
        {name:"roleName", width:"*"},
    	{name:"roleDesc", width:"*"},
    	{name:"functions", width:"*", required:true, multiple:true, optionDataSource:"systemFunctionDS", pickListWidth:390, 
    		valueField:"id", displayField:"funcName", pickListProperties:{showHeader:true, cellHeight: 25}},
    	{editorType:"spacer"},
        {name:"saveBtn", editorType:"button", icon:"[SKIN]/actions/save.png", align:"center", width:100, colSpan:4, title:"Save", 
            click: function() {
            	var form = this.form;
            	if (form.validate()) {
	            	isc.confirm("Are you sure save?", function(value) {
	            		if (value) {
			                form.saveData(function (dsResponse, data, dsRequest) {
			                    if (dsResponse.status != RPCResponse.STATUS_SUCCESS || !data) return;
			                    addRoleWindow.isValueChanged = false;
			                    addRoleWindow.hide();
			                    form.successPrompt("Save");
			                });
	            		} else {
	            			form.cancelChanged(addRoleWindow);
	            		}
	            	});
            	}
            }
        }
    ],
    itemChanged: function(item, newValue) {
        if (item.form.valuesHaveChanged()) {
        	addRoleWindow.isValueChanged = true;
        } else {
        	addRoleWindow.isValueChanged = false;
        }
    }
});

isc.SWindow.create({
    ID:"addRoleWindow",
    title:"Add Role",
    width:600,
    height:370,
    items:[addRoleForm],
    isValueChanged: false,
    initWidget:function(){
        this.Super('initWidget', arguments);
    },
    editRecord:function(record) {
        if (!record) return;
        addRoleForm.editRecord(record);
    },
    clearData:function() {
    	addRoleForm.editNewRecord();
        resetWindowTitle(this, "Add Role");
        this.Super("clearData", arguments);
    },
    checkChanged: function() {
        return this.isValueChanged;
    },
    discardChange: function() {
        this.isValueChanged = false;
    }
});


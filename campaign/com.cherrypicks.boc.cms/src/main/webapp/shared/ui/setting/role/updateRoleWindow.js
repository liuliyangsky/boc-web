isc.SDynamicForm.create({
    ID:"updateRoleForm",
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
			                    updateRoleWindow.isValueChanged = false;
			                    updateRoleWindow.hide();
			                    form.successPrompt("Save");
			                });
	            		} else {
	            			form.cancelChanged(updateRoleWindow);
	            		}
	            	});
            	}
            }
        }
    ],
    itemChanged: function(item, newValue) {
        if (item.form.valuesHaveChanged()) {
        	updateRoleWindow.isValueChanged = true;
        } else {
        	updateRoleWindow.isValueChanged = false;
        }
    }
});

isc.SWindow.create({
    ID:"updateRoleWindow",
    title:"Update Role",
    width:600,
    height:370,
    items:[updateRoleForm],
    isValueChanged: false,
    initWidget:function(){
        this.Super('initWidget', arguments);
    },
    editRecord:function(record) {
        if (!record) return;
        updateRoleForm.editRecord(record);
        this.show();
    },
    clearData:function() {
        updateRoleForm.editNewRecord();
        resetWindowTitle(this, "Update Role");
        this.Super("clearData", arguments);
    },
    checkChanged: function() {
        return this.isValueChanged;
    },
    discardChange: function() {
        this.isValueChanged = false;
    }
});


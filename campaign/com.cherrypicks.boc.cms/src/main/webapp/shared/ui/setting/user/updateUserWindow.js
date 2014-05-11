isc.SDynamicForm.create({
    ID:"updateSystemUserForm",
    width:"500",
    height:"100%",
    autoDraw:false,
    wrapItemTitles:false,
    cellPadding:5,
    numCols:2,
    colWidths:[100, "*"],
    dataSource: "systemUserDS",
    fields:[
        {name:"userName",  required:true, width:"*"},
        {name:"password",  width:"*", required:true/*,
        	validators:[{type:"validPassword", errorMessage:"Password must be a combination of a-z, A-Z, 0-9 and special character with length between 8-14.", clientOnly:true,}]*/
        },
        {name:"mobile", width:"*"},
        {name:"email", width:"*"},
        {name:"roles", width:"*", required:true, multiple:true, optionDataSource:"systemRoleDS", 
        	pickListWidth:390, valueField:"id", displayField:"roleName", pickListProperties:{showHeader:true, cellHeight: 25}
        },
        {name:"status", width:"*"},
        {editorType:"spacer"},
        {name:"saveBtn", editorType:"button", icon:"[SKIN]/actions/save.png", align:"center", width:100, colSpan:4, title:"Save", 
            click: function() {
            	var form = this.form;
            	if (form.validate()) {
	            	isc.confirm("Are you sure save?", function(value) {
	            		if (value) {
			                form.saveData(function (dsResponse, data, dsRequest) {
			                    if (dsResponse.status != RPCResponse.STATUS_SUCCESS || !data) return;
			                    updateSystemUserWindow.isValueChanged = false;
			                    updateSystemUserWindow.hide();
			                    form.successPrompt("Save");
			                });
	            		} else {
	            			form.cancelChanged(updateSystemUserWindow);
	            		}
	            	});
            	}
            }
        }
    ]
});

isc.SWindow.create({
    ID:"updateSystemUserWindow",
    title:"Update User",
    width:600,
    height:370,
    items:[updateSystemUserForm],
    isValueChanged: false,
    initWidget:function(){
        this.Super('initWidget', arguments);
    },
    editRecord:function(record) {
        if (!record) return;
        updateSystemUserForm.editRecord(record);
        this.show();
    },
    clearData:function() {
        updateSystemUserForm.editNewRecord();
        resetWindowTitle(this, "Update User");
        this.Super("clearData", arguments);
    },
    checkChanged: function() {
        return this.isValueChanged;
    },
    discardChange: function() {
        this.isValueChanged = false;
    }
});


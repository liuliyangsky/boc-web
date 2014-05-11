isc.SDynamicForm.create({
    ID:"addActivityForm",
    width:"600",
    height:"100%",
    autoDraw:false,
    wrapItemTitles:false,
    cellPadding:5,
    numCols:2,
    colWidths:[100, "*"],
    dataSource: "activityManagementDS",
    fields:[
        {name:"startDate", required:true, validateOnExit: true,width:"500"},
        {name:"endDate", required:true, validateOnExit: true,width:"500"},
        {name:"addresses", width:"500", required:true, multiple:true, optionDataSource:"activityAddressDS", 
        	pickListWidth:390, valueField:"id", displayField:"address", pickListProperties:{showHeader:true, cellHeight: 25}
        },
        {name:"status", width:"500",defaultValue:"1"},
        {editorType:"spacer"},
        {name:"saveBtn", editorType:"button", icon:"[SKIN]/actions/save.png", align:"center", width:100, colSpan:4, title:"Save", 
            click: function() {
            	var form = this.form;
            	if (form.validate()) {
	            	isc.confirm("Are you sure save?", function(value) {
	            		if (value) {
			                form.saveData(function (dsResponse, data, dsRequest) {
			                    if (dsResponse.status != RPCResponse.STATUS_SUCCESS || !data) return;
			                    addActivityForm.isValueChanged = false;
			                    addActivityForm.hide();
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
    ID:"addActivityWindow",
    title:"Add Activity",
    width:800,
    height:300,
    isValueChanged: false,
    items:[addActivityForm],
	initWidget:function(){
        this.Super('initWidget', arguments);
    },
    editRecord:function(record) {
        if (!record) return;
        this.show();
    },
    clearData:function() {
    	addActivityForm.editNewRecord();
        this.Super("clearData", arguments);
    },
    checkChanged: function() {
        return this.isValueChanged;
    },
    discardChange: function() {
        this.isValueChanged = false;
    }
});
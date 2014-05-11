isc.SDynamicForm.create({
    ID:"addActivityAddressForm",
    width:"500",
    height:"100%",
    autoDraw:false,
    wrapItemTitles:false,
    cellPadding:5,
    numCols:2,
    colWidths:[100, "*"],
    dataSource: "activityAddressDS",
    fields:[
        {name:"address",  required:true, width:"*"},
        {name:"numberOfPeople",  required:true, width:"*",filterEditorProperties:{keyPressFilter:"[0-9.]", prompt:"Numeric only [0-9.]"}},
        {editorType:"spacer"},
        {name:"saveBtn", editorType:"button", icon:"[SKIN]/actions/save.png", align:"center", width:100, colSpan:4, title:"Save", 
            click: function() {
            	var form = this.form;
            	if (form.validate()) {
	            	isc.confirm("Are you sure save?", function(value) {
	            		if (value) {
			                form.saveData(function (dsResponse, data, dsRequest) {
			                    if (dsResponse.status != RPCResponse.STATUS_SUCCESS || !data) return;
			                    addActivityAddressWindow.isValueChanged = false;
			                    addActivityAddressWindow.hide();
			                    form.successPrompt("Save");
			                });
	            		} else {
	            			form.cancelChanged(addActivityAddressWindow);
	            		}
	            	});
            	}
            }
        }
    ]
});

isc.SWindow.create({
    ID:"addActivityAddressWindow",
    title:"Add Activity Address",
    width:600,
    height:370,
    items:[addActivityAddressForm],
    isValueChanged: false,
    initWidget:function(){
        this.Super('initWidget', arguments);
    },
    editRecord:function(record) {
        if (!record) return;
        addActivityAddressForm.editRecord(record);
    },
    clearData:function() {
    	addActivityAddressForm.editNewRecord();
        resetWindowTitle(this, "Add Activity Address");
        this.Super("clearData", arguments);
    },
    checkChanged: function() {
        return this.isValueChanged;
    },
    discardChange: function() {
        this.isValueChanged = false;
    }
});

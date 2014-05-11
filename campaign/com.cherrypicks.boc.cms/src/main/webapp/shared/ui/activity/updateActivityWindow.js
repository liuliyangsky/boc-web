isc.SDynamicForm.create({
    ID:"updateActivityForm",
    width:"98%",
    height:"30",
    autoDraw:false,
    wrapItemTitles:false,
    cellPadding:8,
    numCols:2,
    colWidths:[100, "*"],
    dataSource: "activityManagementDS",
    fields:[
        {name:"startDate", required:true, validateOnExit: true,width:"500"},
        {name:"endDate", required:true, validateOnExit: true,width:"500"},
        {name:"activityAddressIds", width:"500", required:true, multiple:true, optionDataSource:"activityAddressDS", 
        	pickListWidth:390, valueField:"id", displayField:"address", pickListProperties:{showHeader:true, cellHeight: 25}
        },
        {name:"status", width:"500"},
        {editorType:"spacer"},
        {name:"saveBtn", editorType:"button", icon:"[SKIN]/actions/save.png", align:"center", width:100, colSpan:4, title:"Save", 
            click: function() {
            	var form = this.form;
            	if (form.validate()) {
	            	isc.confirm("Are you sure save?", function(value) {
	            		if (value) {
			                form.saveData(function (dsResponse, data, dsRequest) {
			                    if (dsResponse.status != RPCResponse.STATUS_SUCCESS || !data) return;
			                    updateaCtivityWindow.isValueChanged = false;
			                    updateaCtivityWindow.hide();
			                    form.successPrompt("Save");
			                });
	            		} else {
	            			form.cancelChanged(updateaCtivityWindow);
	            		}
	            	});
            	}
            }
        }
    ]
});

isc.SListGrid.create({
    ID: "showUserActivityList",
    dataSource: "activityAddressDS",
    sortField: "createdTime",
    cellPadding:0,
    sortDirection: "descending",
    fields: [
        {name:"id", width:"5%",  align:"center", filterEditorProperties:{keyPressFilter:"[0-9.]", prompt:"Numeric only [0-9.]"}},
        {name:"address",align:"center",width:"20%"},
        {name:"numberOfPeople", align:"center", width:"20%"},
        {name:"createdTime", align:"center", width:"25%",
        	formatCellValue: function(value, record, rowNum, colNum, grid) {
        		return formatDateTimeCellValue(value);
        	}
        },
        {name:"updatedTime", align:"center", width:"25%",
        	formatCellValue: function(value, record, rowNum, colNum, grid) {
        		return formatDateTimeCellValue(value);
        	}
        }
    ],
});

isc.IButton.create({
    ID: "saveButton",
    width:"120",
    height:"25",
    autoFit: true,
    cellPadding: 5,
    title: "&nbsp;&nbsp;save &nbsp;&nbsp;",
    click: function () {
        dynamicForm.disableValidation = !dynamicForm.disableValidation;
        this.setTitle((dynamicForm.disableValidation ? "Enable" : "Disable")+" saveButton");
    }
});

isc.HStack.create({
    ID: "buttons",
    width:"100%",
    height: 24,
    membersMargin: 10,
    align:"right",
    members: [saveButton]    
});

isc.SHLayout.create({
    ID:"updateActivityDataLayout",
    width: "100%",
    height: "60",
    layoutMargin:5,
    membersMargin:5,
    defaultLayoutAlign: "center",
    autoDraw:false,
    members: [updateActivityForm]
});

isc.SGridPager.create({
    ID: "showUserActivityListPager",
    grid: showUserActivityList,
    border:"1px solid black",
    width: "100%",
    autoDraw:false
});

isc.SVLayout.create({
    ID:"updateActivityBodyVLayout",
    width: "100%",
    height: "100%",
    autoDraw:false,
    layoutMargin:5,
    membersMargin:10,
    members: [updateActivityDataLayout]
});

isc.SWindow.create({
    ID:"updateaCtivityWindow",
    title:"Update Activity",
    width:800,
    height:300,
    items:[updateActivityBodyVLayout],
    isValueChanged: false,
    initWidget:function(){
        this.Super('initWidget', arguments);
    },
    editRecord:function(record) {
        if (!record) return;
        updateActivityForm.editRecord(record);
       // showUserActivityList.fetchData();
        this.show();
    },
    clearData:function() {
        resetWindowTitle(this, "Update Activity");
        this.Super("clearData", arguments);
    },
    checkChanged: function() {
        return this.isValueChanged;
    },
    discardChange: function() {
        this.isValueChanged = false;
    }
});
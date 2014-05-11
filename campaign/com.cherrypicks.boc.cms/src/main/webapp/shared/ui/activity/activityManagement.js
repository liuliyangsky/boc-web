isc.SListGrid.create({
    ID: "activityManagementList",
    dataSource: "activityManagementDS",
    sortField: "createdTime",
    sortDirection: "descending",
    fields: [
        {name:"id", width:"5%",  align:"center", filterEditorProperties:{keyPressFilter:"[0-9.]", prompt:"Numeric only [0-9.]"}},
        {name:"activityAddressIds", width:"25%",  align:"center",optionDataSource:"activityAddressDS", valueField:"id", displayField:"address"},
        {name:"status",align:"center",width:"20%"},
        {name:"startDate",align:"center",width:"20%"},
        {name:"endDate",align:"center",width:"20%"},
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
    rowDoubleClick: function(record, recordNum, fieldNum) {
       this.Super("rowDoubleClick", arguments);
       updateaCtivityWindow.editRecord(record);
    }
});

isc.SGridPager.create({
    ID: "activityManagementListPager",
    grid: activityManagementList,
    border:"1px solid black",
    width: "100%",
    autoDraw:false
});

isc.AddButton.create({
    ID:"activityManagementNewBtn",
    autoDraw:false,
    click:function() {
    	addActivityWindow.show();
    }
});

isc.DeleteButton.create({
    ID:"activityManagementDeleteBtn",
    autoDraw:false,
    click:function() {
        if (activityManagementList.getSelection().length <= 0) {
            isc.say("please select one record first!");
            return;
        }
        isc.confirm("Are you sure delete?", function(value) {
            if (value) {
            	var ids = new Array();
                if (activityManagementList.getSelection().getLength() > 0) {
                	activityManagementList.getSelection().map(function (item) { 
                    	ids.add(item.id);
                    });
                }
                
                if (ids.length > 0) {
                	activityManagementList.removeData({"ids":ids}, function(dsResponse, data) {
                		if (dsResponse.status == RPCResponse.STATUS_FAILURE) return;
                		activityManagementList.successPrompt("Delete");
                    }); 
                }
            }
        });
    }
});

isc.SHLayout.create({
    ID:"activityManagementBtnLayout",
    width: "100%",
    height: "60",
    layoutMargin:5,
    membersMargin:5,
    defaultLayoutAlign: "center",
    autoDraw:false,
    members: [activityManagementNewBtn, activityManagementDeleteBtn]
});

isc.SVLayout.create({
    ID:"activityManagementBodyVLayout",
    width: "100%",
    height: "100%",
    autoDraw:false,
    members: [activityManagementListPager, activityManagementBtnLayout]
});

isc.SSectionStack.create({
    ID: "activityManagementMainBody",
    sections:[
        { items:[activityManagementBodyVLayout], title:"<span style='font-size:14px;'><b>activityManagement</b></span>", expanded:true, canCollapse:false }
    ],
    visibilityMode:"multiple",
    autoDraw:false,
    width:"100%",
    height:"100%",
    refreshData: function() {
    	activityManagementList.refreshData();
    }
});
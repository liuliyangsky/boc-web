isc.SListGrid.create({
    ID: "activityAddressList",
    dataSource: "activityAddressDS",
    sortField: "createdTime",
    sortDirection: "descending",
    fields: [
        {name:"id", width:"20%",  align:"center", filterEditorProperties:{keyPressFilter:"[0-9.]", prompt:"Numeric only [0-9.]"}},
        {name:"address",align:"center",width:"20%"},
        {name:"numberOfPeople", align:"center", width:"20%"},
        {name:"createdTime", align:"center", width:"25%",
        	formatCellValue: function(value, record, rowNum, colNum, grid) {
        		return formatDateTimeCellValue(value);
        	}
        }
    ],
    rowDoubleClick: function(record, recordNum, fieldNum) {
       this.Super("rowDoubleClick", arguments);
       updateActivityAddressWindow.editRecord(record);
    }
});

isc.SGridPager.create({
    ID: "activityAddressListPager",
    grid: activityAddressList,
    border:"1px solid black",
    width: "100%",
    autoDraw:false
});

isc.AddButton.create({
    ID:"activityAddressNewBtn",
    autoDraw:false,
    click:function() {
    	addActivityAddressWindow.show();
    }
});

isc.DeleteButton.create({
    ID:"activityAddressDeleteBtn",
    autoDraw:false,
    click:function() {
        if (activityAddressList.getSelection().length <= 0) {
            isc.say("please select one record first!");
            return;
        }
        isc.confirm("Are you sure delete?", function(value) {
            if (value) {
            	var ids = new Array();
                if (activityAddressList.getSelection().getLength() > 0) {
                	activityAddressList.getSelection().map(function (item) { 
                    	ids.add(item.id);
                    });
                }
                
                if (ids.length > 0) {
                	activityAddressList.removeData({"ids":ids}, function(dsResponse, data) {
                		if (dsResponse.status == RPCResponse.STATUS_FAILURE) return;
                		activityAddressList.successPrompt("Delete");
                    }); 
                }
            }
        });
    }
});

isc.SHLayout.create({
    ID:"activityAddressBtnLayout",
    width: "100%",
    height: "60",
    layoutMargin:5,
    membersMargin:5,
    defaultLayoutAlign: "center",
    autoDraw:false,
    members: [activityAddressNewBtn, activityAddressDeleteBtn]
});

isc.SVLayout.create({
    ID:"activityAddressBodyVLayout",
    width: "100%",
    height: "100%",
    autoDraw:false,
    members: [activityAddressListPager, activityAddressBtnLayout]
});

isc.SSectionStack.create({
    ID: "activityAddressMainBody",
    sections:[
        { items:[activityAddressBodyVLayout], title:"<span style='font-size:14px;'><b>ActivityAddress</b></span>", expanded:true, canCollapse:false }
    ],
    visibilityMode:"multiple",
    autoDraw:false,
    width:"100%",
    height:"100%",
    refreshData: function() {
    	activityAddressList.refreshData();
    }
});
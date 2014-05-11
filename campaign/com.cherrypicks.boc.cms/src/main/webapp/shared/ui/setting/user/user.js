isc.SListGrid.create({
    ID: "systemUserList",
    dataSource: "systemUserDS",
    sortField: "createdTime",
    sortDirection: "descending",
    fields: [
        {name:"id", width:"20%",  align:"center", filterEditorProperties:{keyPressFilter:"[0-9.]", prompt:"Numeric only [0-9.]"}},
        {name:"userName", width:"25%", align:"center"},
        {name:"mobile", width:"25%", align:"center"},
        {name:"email", width:"25%", align:"center"},
        {name:"status", width:"15%", canEdit:false, align:"center"},
        {name:"roles", width:"25%",  align:"center", optionDataSource:"systemRoleDS", valueField:"id", displayField:"roleName"},
        {name:"createdTime", align:"center", width:"25%",
        	formatCellValue: function(value, record, rowNum, colNum, grid) {
        		return formatDateTimeCellValue(value);
        	}
        }
    ],
    rowDoubleClick: function(record, recordNum, fieldNum) {
       this.Super("rowDoubleClick", arguments);
       updateSystemUserWindow.editRecord(record);
    }
});

isc.SGridPager.create({
    ID: "systemUserListPager",
    grid: systemUserList,
    border:"1px solid black",
    width: "100%",
    autoDraw:false
});

isc.AddButton.create({
    ID:"systemUserNewBtn",
    autoDraw:false,
    click:function() {
    	addSystemUserWindow.show();
    }
});

isc.DeleteButton.create({
    ID:"systemUserDeleteBtn",
    autoDraw:false,
    click:function() {
        if (systemUserList.getSelection().length <= 0) {
            isc.say("please select one record first!");
            return;
        }
        isc.confirm("Are you sure delete?", function(value) {
            if (value) {
            	var ids = new Array();
                if (systemUserList.getSelection().getLength() > 0) {
                	systemUserList.getSelection().map(function (item) { 
                    	ids.add(item.id);
                    });
                }
                
                if (ids.length > 0) {
                	systemUserList.removeData({"ids":ids}, function(dsResponse, data) {
                		if (dsResponse.status == RPCResponse.STATUS_FAILURE) return;
                		systemUserList.successPrompt("Delete");
                    }); 
                }
            }
        });
    }
});

isc.SHLayout.create({
    ID:"systemUserBtnLayout",
    width: "100%",
    height: "60",
    layoutMargin:5,
    membersMargin:5,
    defaultLayoutAlign: "center",
    autoDraw:false,
    members: [systemUserNewBtn, systemUserDeleteBtn]
});

isc.SVLayout.create({
    ID:"systemUserBodyVLayout",
    width: "100%",
    height: "100%",
    autoDraw:false,
    members: [systemUserListPager, systemUserBtnLayout]
});

isc.SSectionStack.create({
    ID: "systemUserMainBody",
    sections:[
        { items:[systemUserBodyVLayout], title:"<span style='font-size:14px;'><b>User</b></span>", expanded:true, canCollapse:false }
    ],
    visibilityMode:"multiple",
    autoDraw:false,
    width:"100%",
    height:"100%",
    refreshData: function() {
    	systemUserList.refreshData();
    }
});
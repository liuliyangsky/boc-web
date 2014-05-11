isc.SListGrid.create({
    ID: "roleList",
    dataSource: "systemRoleDS",
    sortField: "createdTime",
    sortDirection: "descending",
    fields: [
        {name:"id", width:"25%",  align:"center", filterEditorProperties:{keyPressFilter:"[0-9.]", prompt:"Numeric only [0-9.]"},
            getGridSummary: function(records, summaryField) {
                return setGridSummaryTitle(records);
            }
        },
        {name:"roleName", width:"25%", align:"center"},
        {name:"roleDesc", width:"25%", align:"center"},
        {name:"createdTime", align:"center", width:"25%",
            formatCellValue: function(value, record, rowNum, colNum, grid) {
                return formatDateTimeCellValue(value);
            }
        }
    ],
    rowDoubleClick: function(record, recordNum, fieldNum) {
       this.Super("rowDoubleClick", arguments);
       updateRoleWindow.editRecord(record);
    }
});

isc.SGridPager.create({
    ID: "roleListPager",
    grid: roleList,
    border:"1px solid black",
    width: "100%",
    autoDraw:false
});

isc.AddButton.create({
    ID:"roleNewBtn",
    title:"Add",
    autoDraw:false,
    click:function() {
        addRoleWindow.show();
    }
});

isc.DeleteButton.create({
    ID:"roleDeleteBtn",
    title:"Delete",
    autoDraw:false,
    click:function() {
        if (roleList.getSelection().length <= 0) {
            isc.say("please select one record first!");
            return;
        }
        isc.confirm("Are you sure delete?", function(value) {
            if (value) {
            	var ids = new Array();
                if (roleList.getSelection().getLength() > 0) {
                    roleList.getSelection().map(function (item) { 
                    	ids.add(item.id);
                    });
                }
                
                if (ids.length > 0) {
                	roleList.removeData({"ids":ids}, function(dsResponse, data) {
                		if (dsResponse.status == RPCResponse.STATUS_FAILURE || !data) return;
                        roleList.successPrompt("Delete");
                    }); 
                }
            }
        });
    }
});

isc.SHLayout.create({
    ID:"roleBtnLayout",
    width: "100%",
    height: "60",
    layoutMargin:5,
    membersMargin:5,
    defaultLayoutAlign: "center",
    autoDraw:false,
    members: [roleNewBtn, roleDeleteBtn]
});

isc.SVLayout.create({
    ID:"roleBodyVLayout",
    width: "100%",
    height: "100%",
    autoDraw:false,
    members: [roleListPager, roleBtnLayout]
});



isc.SectionStack.create({
    ID: "systemRoleMainBody",
    sections:[
        { items:[roleBodyVLayout], title:"<span style='font-size:14px;'><b>Role</b></span>", expanded:true, canCollapse:false }
    ],
    visibilityMode:"multiple",
    autoDraw:false,
    width:"100%",
    height:"100%",
    refreshData: function() {
    	roleList.refreshData();
    }
});
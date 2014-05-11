isc.SListGrid.create({
    ID: "campaginManagerList",
    dataSource: "campaginManagerDS",
    sortField: "createdTime",
    sortDirection: "descending",
    fields: [
        {name:"playType",width:"20%",align:"center"},
        {name:"campaignType", width:"20%", align:"center"},
        {name:"startDate" ,width:"20%", align:"center"},
        {name:"endDate", width:"20%", align:"center"},
        {name:"createdTime", align:"center", width:"25%",
        	formatCellValue: function(value, record, rowNum, colNum, grid) {
        		return formatDateTimeCellValue(value);
        	}
        }
    ],
    rowDoubleClick: function(record, recordNum, fieldNum) {
       this.Super("rowDoubleClick", arguments);
       		updateCampaignWindow.editRecord(record);
    }
});

isc.SGridPager.create({
    ID: "campaginManagerListPager",
    grid: campaginManagerList,
    border:"1px solid black",
    width: "100%",
    autoDraw:false
});

isc.AddButton.create({
    ID:"campaginManagerNewBtn",
    autoDraw:false,
    click:function() {
    	addCampaignWindow.show();
    }
});

isc.DeleteButton.create({
    ID:"campaginManagerDeleteBtn",
    autoDraw:false,
    click:function() {
        if (campaginManagerList.getSelection().length <= 0) {
            isc.say("please select one record first!");
            return;
        }
        isc.confirm("Are you sure delete?", function(value) {
            if (value) {
            	var ids = new Array();
                if (campaginManagerList.getSelection().getLength() > 0) {
                	campaginManagerList.getSelection().map(function (item) { 
                    	ids.add(item.id);
                    });
                }
                
                if (ids.length > 0) {
                	campaginManagerList.removeData({"ids":ids}, function(dsResponse, data) {
                		if (dsResponse.status == RPCResponse.STATUS_FAILURE) return;
                		campaginManagerList.successPrompt("Delete");
                    }); 
                }
            }
        });
    }
});

isc.SHLayout.create({
    ID:"campaginManagerBtnLayout",
    width: "100%",
    height: "60",
    layoutMargin:5,
    membersMargin:5,
    defaultLayoutAlign: "center",
    autoDraw:false,
    members: [campaginManagerNewBtn, campaginManagerDeleteBtn]
});

isc.SVLayout.create({
    ID:"campaginManagerBodyVLayout",
    width: "100%",
    height: "100%",
    autoDraw:false,
    members: [campaginManagerListPager, campaginManagerBtnLayout]
});

isc.SSectionStack.create({
    ID: "campaginManagerMainBody",
    sections:[
        { items:[campaginManagerBodyVLayout], title:"<span style='font-size:14px;'><b>Hold Campagin</b></span>", expanded:true, canCollapse:false }
    ],
    visibilityMode:"multiple",
    autoDraw:false,
    width:"100%",
    height:"100%",
    refreshData: function() {
    	campaginManagerList.refreshData();
    }
});
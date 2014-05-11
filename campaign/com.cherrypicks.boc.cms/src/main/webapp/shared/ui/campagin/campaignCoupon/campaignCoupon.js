isc.SListGrid.create({
    ID: "campaignCouponList",
    dataSource: "campaignCouponDS",
    sortField: "createdTime",
    sortDirection: "descending",
    fields: [
             {name:"id", width:"20%",  align:"center", filterEditorProperties:{keyPressFilter:"[0-9.]", prompt:"Numeric only [0-9.]"}},
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
       //updateCampaignCouponWindow.editRecord(record);
    }
});

isc.SGridPager.create({
    ID: "campaignCouponListPager",
    grid: campaignCouponList,
    border:"1px solid black",
    width: "100%",
    autoDraw:false
});

isc.AddButton.create({
    ID:"campaignCouponNewBtn",
    autoDraw:false,
    click:function() {
    	alert(3);
    }
});

isc.DeleteButton.create({
    ID:"campaignCouponDeleteBtn",
    autoDraw:false,
    click:function() {
        if (campaignCouponList.getSelection().length <= 0) {
            isc.say("please select one record first!");
            return;
        }
        isc.confirm("Are you sure delete?", function(value) {
            if (value) {
            	var ids = new Array();
                if (campaignCouponList.getSelection().getLength() > 0) {
                	campaignCouponList.getSelection().map(function (item) { 
                    	ids.add(item.id);
                    });
                }
                
                if (ids.length > 0) {
                	campaignCouponList.removeData({"ids":ids}, function(dsResponse, data) {
                		if (dsResponse.status == RPCResponse.STATUS_FAILURE) return;
                		campaignCouponList.successPrompt("Delete");
                    }); 
                }
            }
        });
    }
});

isc.SHLayout.create({
    ID:"campaignCouponBtnLayout",
    width: "100%",
    height: "60",
    layoutMargin:5,
    membersMargin:5,
    defaultLayoutAlign: "center",
    autoDraw:false,
    members: [campaignCouponNewBtn, campaignCouponDeleteBtn]
});

isc.SVLayout.create({
    ID:"campaignCouponBodyVLayout",
    width: "100%",
    height: "100%",
    autoDraw:false,
    members: [campaignCouponListPager, campaignCouponBtnLayout]
});

isc.SSectionStack.create({
    ID: "campaignCouponMainBody",
    sections:[
        { items:[campaignCouponBodyVLayout], title:"<span style='font-size:14px;'><b>Campagin Coupon</b></span>", expanded:true, canCollapse:false }
    ],
    visibilityMode:"multiple",
    autoDraw:false,
    width:"100%",
    height:"100%",
    refreshData: function() {
    	campaignCouponList.refreshData();
    }
});
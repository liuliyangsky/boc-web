isc.SDynamicForm.create({
    ID:"updateCampaignForm",
    width:"600",
    height:"100%",
    autoDraw:false,
    wrapItemTitles:false,
    cellPadding:5,
    numCols:4,
    colWidths:[100, "*"],
    dataSource: "campaginManagerDS",
    fields:[
        {name:"langCode", type:"text", width:"*", required:true, optionDataSource:"languageDS", colSpan:4,
                valueField:"code", displayField:"name", pickListWidth:390, pickListHeight:500,
                pickListFields:[
                                {name:"img", width:100, align:"center", type:"image", imageURLPrefix:"../shared/jsp/viewImage.jsp?v="},
                                {name:"name"}
                ]
        },
        {name:"startDate", required:true,colSpan:4,validateOnExit: true,width:"*"},
      /*  {name:"startTime",  width:"*", showTitle:false},*/
        {name:"endDate", required:true,colSpan:4, validateOnExit: true,width:"*"},
       /* {name:"endTime",  width:"*", showTitle:false},*/
        {name:"campaignType", width:"500",colSpan:4},
        {name:"playType", width:"500", colSpan:4},
        {name:"campaignName", width:"500",colSpan:4 },
        {name:"icon", width:"500",colSpan:4 
        	/*afterUpload: function(form, item, value) {
	    		if (value) {
	    			alert(value);
	    			//form.resetPreview();
	    		}
        	}*/
        },
        {editorType:"spacer"},
        {name:"saveBtn", editorType:"button", icon:"[SKIN]/actions/save.png", align:"center", width:100, colSpan:4, title:"Save", 
            click: function() {
            	var form = this.form;
            	if (form.validate()) {
	            	isc.confirm("Are you sure save?", function(value) {
	            		if (value) {
			                form.saveData(function (dsResponse, data, dsRequest) {
			                    if (dsResponse.status != RPCResponse.STATUS_SUCCESS || !data) return;
			                    updateCampaignWindow.isValueChanged = false;
			                    updateCampaignWindow.hide();
			                    form.successPrompt("Save");
			                });
	            		} else {
	            			form.cancelChanged(updateCampaignForm);
	            		}
	            	});
            	}
            }
        }
    ]
});

isc.SWindow.create({
    ID:"updateCampaignWindow",
    title:"Update Campaign",
    width:800,
    height:400,
    isValueChanged: false,
    items:[updateCampaignForm],
	initWidget:function(){
        this.Super('initWidget', arguments);
    },
    editRecord:function(record) {
        if (!record) return;
        updateCampaignForm.editRecord(record);
        this.show();
    },
    clearData:function() {
    	updateCampaignForm.editNewRecord();
        this.Super("clearData", arguments);
    },
    checkChanged: function() {
        return this.isValueChanged;
    },
    discardChange: function() {
        this.isValueChanged = false;
    }
});
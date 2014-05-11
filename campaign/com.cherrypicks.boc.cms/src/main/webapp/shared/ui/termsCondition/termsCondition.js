isc.SDynamicForm.create({
    ID:"TermsConditionCNForm",
    width:400,
    height:20,
    cellPadding:5,
    numCols:2, 
    title:"ask",
    colWidths:[100, "*"],
    dataSource: "termsConditionCNDS",
    fields:[
        {name:"file_cn",width:"*",canEdit:false},
    	{name:"newFile_cn", width:"*"},
    	{type:"spacer"},
    ],
    saveDataCN:function(){
    	var form = TermsConditionCNForm;
    	form.saveData(function (dsResponse, data, dsRequest) {
            if (dsResponse.status != RPCResponse.STATUS_SUCCESS || !data) return;
            form.setValues(data);
            form.successPrompt("Upload");
        });
    }
});


isc.SDynamicForm.create({
    ID:"TermsConditionENForm",
    width:400,
    cellPadding:5,
    height:20,
    numCols:2, 
    title:"ask",
    colWidths:[100, "*"],
    dataSource: "termsConditionENDS",
    fields:[
        {name:"file_en",width:"*",canEdit:false},
    	{name:"newFile_en", width:"*"},
    	{type:"spacer"},
    ],
    saveDataEN:function(){
    	var form = TermsConditionENForm;
    	form.saveData(function (dsResponse, data, dsRequest) {
            if (dsResponse.status != RPCResponse.STATUS_SUCCESS || !data) return;
            form.setValues(data);
            form.successPrompt("Upload");
        });
    }
});

isc.SButton.create({
    ID:"uploadNewBtn",
    autoDraw:false,
    width:100, 
    colSpan:2,
    title:"Upload",
    click:function() {
    	isc.confirm("Are you sure upload?", function (value) {
    		if (!value) {
    			return;
    		}
    		TermsConditionCNForm.saveDataCN();
    		TermsConditionENForm.saveDataEN();
    	});
    }
});

isc.SHLayout.create({
    ID:"actionBtnLayout",
    width: "100%",
    height: "60",
    //layoutMargin:5,
   // membersMargin:5,
    align:"center",
    defaultLayoutAlign: "center",
    autoDraw:false,
    members: [uploadNewBtn]
});



isc.SWindow.create({
    ID:"TermsConditionWindow",
    width:450,
    height:330,
    showTitle: false,
    showHeaderIcon: false, 
    showCloseButton: false,
    showMaximizeButton: false,
    showMinimizeButton: false,
    items:["TermsConditionCNForm","TermsConditionENForm","actionBtnLayout"],
    isValueChanged: false,
    initWidget:function(){
        this.Super('initWidget', arguments);
    },
    clearData:function() {
    	TermsConditionCNForm.editNewRecord();
    	TermsConditionENForm.editNewRecord();
        this.Super("clearData", arguments);
    }
    
});

isc.SectionStack.create({
    ID: "TermsConditionMainBody",
    sections:[
        { items:[TermsConditionWindow], title:"<span style='font-size:14px;'><b>Terms Condition</b></span>", expanded:true, canCollapse:false }
    ],
    visibilityMode:"multiple",
    autoDraw:false,
    width:"100%",
    height:"100%",
    refreshData: function() {
    	TermsConditionCNForm.fetchData();
    	TermsConditionENForm.fetchData();
    }
});

isc.SDynamicForm.create({
    ID: "merchantForm",
    width:400,
    height:60,
    autoDraw:false,
    wrapItemTitles:false,
    cellPadding:5,
    numCols:4,
    colWidths:[100, 200, 100, 100],
    margin: 10,
    dataSource: merchantManagementDS,
    fields: [
        {name: "file", width:"*", required:true},
        {name: "uploadBtn", editorType:"button", title:"Upload", width:100, colSpan:2, startRow:false, endRow:true,
        	click: function() {
        		var form = this.form;
        		form.saveData(function (dsResponse, data, dsRequest) {
                    if (dsResponse.status != RPCResponse.STATUS_SUCCESS) return;
                    merchantList.refreshData();
                    form.successPrompt("Upload");
                });
        	}
        }
    ]
});

isc.SListGrid.create({
    ID: "merchantList",
    autoDraw: false,
    autoFetchData: true,
    autoSaveEdits: false,
    headerHeight:30,
    cellHeight: 30,
    canEdit: true,
    modalEditing:true,
    waitForSave: true,
    dataSource: "merchantManagementDS",
    sortField: "createdTime",
    sortDirection: "descending",
    fields: [
        {name:"merchantPhone", width:"25%",  align:"center", filterEditorProperties:{keyPressFilter:"[0-9.]", prompt:"Numeric only [0-9.]"}},
        {name:"merchantWebpage", width:"25%",align:"center"},
        {name:"createdTime",canEdit:false, align:"center", width:"25%",
        	formatCellValue: function(value, record, rowNum, colNum, grid) {
        		return formatDateTimeCellValue(value);
        	}
        },
        {name:"updatedTime",canEdit:false, align:"center", width:"25%",
        	formatCellValue: function(value, record, rowNum, colNum, grid) {
        		return formatDateTimeCellValue(value);
        	}
        }
    ]
});

isc.AddButton.create({
    ID:"merchantNewBtn",
    autoDraw:false,
    click: function() {
        merchantList.startEditingNew();
    }
});

isc.SaveButton.create({
	ID:"merchantSaveBtn",
    title: "Save",
    click: function() {
    	
        // Smart client bug: when cancel the rowEditorExit, then can not exit edit through modalEditing,
        // so here exit the edit by programmatic.
        merchantList.endEditing();
        var list = merchantList;
        var editedRowNums = list.getAllEditRows();
        if (!editedRowNums){
        	return;
        }
       
        // Validate
        var valid = true;
        for (var i = 0; i < editedRowNums.length; i++) {
        	var errors = list.getRowErrors(editedRowNums[i]);
        	if (errors) {
        		valid = false;
        		break;
        	}
        }
        if (!valid)
        	return;
        
        // Smart client bug: if use list.saveAllEdits(editedRowNums..., 
        // the first click save will not call server, second click become to normal.
        list.saveAllEdits(null, function() {
		   list.endEditing();
		   list.successPrompt("Save");
        });
    }
});

isc.DeleteButton.create({
	ID:"merchantCancelBtn",
    title: "Cancel",
    icon:"[SKIN]/actions/cancel.png",
    autoDraw:false,
    click: function() {
    	var list = merchantList;
    	if (list.hasErrors()) {
    		list.clearRowErrors(list.getEditRow());
    	}
    	list.discardAllEdits();
    }
});


isc.SHLayout.create({
    ID:"merchantBtnLayout",
    width: "100%",
    height: "60",
    layoutMargin:5,
    membersMargin:5,
    defaultLayoutAlign: "center",
    autoDraw:false,
    members: [merchantNewBtn,merchantSaveBtn,merchantCancelBtn]
});


isc.SGridPager.create({
    ID: "merchantListPager",
    grid: merchantList,
    border:"1px solid black",
    width: "100%",
    autoDraw:false
});

isc.SVLayout.create({
    ID:"merchantBodyVLayout",
    width: "100%",
    height: "100%",
    autoDraw:false,
    members: [merchantListPager,merchantBtnLayout]
});

isc.SSectionStack.create({
    ID: "merchantMainBody",
    sections:[
        { items:[merchantForm], title:"<span style='font-size:14px;'><b>Import from Excel</b></span>", expanded:true, canCollapse:true },
        { items:[merchantBodyVLayout], title:"<span style='font-size:14px;'><b>merchant</b></span>", expanded:true, canCollapse:true }
    ],
    visibilityMode:"multiple",
    autoDraw:false,
    width:"100%",
    height:"100%",
    refreshData: function() {
    	merchantList.refreshData();
    }
});
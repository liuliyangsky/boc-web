// RPCRequest
isc.RPCRequest.addClassProperties({
    willHandleError: true
});

isc.RPCRequest.willHandleError = true;

isc.RPCManager.addClassProperties({
    handleError : function (response, request) { 
		if (response.data){
			isc.say(response.data);
		} else {
			isc.say("Operation has failed please try again later!");
		}
	}
});

//SButton
isc.ClassFactory.defineClass("SButton", IButton);
isc.SButton.addProperties({
	autoDraw: false
});

isc.ClassFactory.defineClass("AddButton", SButton);
isc.AddButton.addProperties({
	title : "Add",
	icon:"[SKIN]/actions/add.png"
});

isc.AddButton.addMethods({
	
});

isc.ClassFactory.defineClass("DeleteButton", SButton);
isc.DeleteButton.addProperties({
	title : "Delete",
	icon:"[SKIN]/actions/remove.png"
});

isc.DeleteButton.addMethods({
	deleteData:function(list) {
        if (list.getSelection().length <= 0) {
            isc.say("please select one record first!");
            return;
        }
        isc.confirm("Are you sure delete?", function(value) {
            if (value) {
            	var ids = new Array();
                if (list.getSelection().getLength() > 0) {
                	list.getSelection().map(function (item) { 
                    	ids.add(item.id);
                    });
                }
                
                if (ids.length > 0) {
                	list.removeData({"ids":ids}, function(dsResponse, data) {
                		if (dsResponse.status == RPCResponse.STATUS_FAILURE) return;
                		list.successPrompt("Delete");
                    }); 
                }
            }
        });
    }
});

isc.defineClass("SaveButton", SButton);
isc.SaveButton.addProperties({
	width:100, 
	align:"center", 
	title : "Save",
	icon:"[SKIN]/actions/save.png",
	colSpan:4
});

isc.SaveButton.addMethods({
	
});


//SLabel
isc.ClassFactory.defineClass("SLabel", Label);
isc.SLabel.addProperties({
    autoDraw: false
});

//STreeGrid
isc.ClassFactory.defineClass("STreeGrid", TreeGrid);
isc.STreeGrid.addClassProperties({
	createdList: null
});

isc.STreeGrid.addClassMethods({
    regiestCreatedList: function(list) {
        if (list) {
            this.createdList = list;
        }
    },
    checkChanged: function() {
    	if (this.createdList)
    		return this.createdList.hasChanges();
    	return false;
    },
    discardChanged: function() {
    	if (this.createdList) {
    		this.createdList.discardAllEdits();
    		if (this.createdList.discardChange) {
    			this.createdList.discardChange();
    		}
    	}
    }
});
isc.STreeGrid.addProperties({
	width: "100%",
    height: "100%",
    headerHeight: 50,
    cellHeight: 50,
    //editorHeight: 50,
    canEdit: false,
    autoDraw: false,
    alternateRecordStyles: true,
    autoFetchData: false,
    showFilterEditor: true,
    filterOnKeypress: true,
    keepParentsOnFilter: true,
    dataFetchMode: "local",
    loadDataOnDemand: false,
    editEvent: "doubleClick",
    listEndEditAction: "next",
    autoFitFieldWidths : true,
    autoFitWidthApproach : "title",
    canFreezeFields: false,
    scrollRedrawDelay:0,
    showGridSummary:true,
    dataProperties: {
        dataArrived:function (parentNode) {
            this.openAll();
        }
    }
});

isc.STreeGrid.addMethods({
    initWidget: function() {
        this.Super("initWidget", arguments);
        isc.STreeGrid.regiestCreatedList(this);
    },
    successPrompt: function(action) {
    	var success = action + " Successfully !";
    	isc.say(success);
    },
    refreshData: function() {
    	this.setData([]);
    	this.fetchData();
    }
});


//SListGrid
isc.ClassFactory.defineClass("SListGrid", ListGrid);
isc.SListGrid.addClassProperties({
	createdList: null
});

isc.SListGrid.addClassMethods({
    regiestCreatedList: function(list) {
        if (list) {
            this.createdList = list;
        }
    },
    checkChanged: function() {
    	if (this.createdList)
    		return this.createdList.hasChanges();
    	return false;
    },
    discardChanged: function() {
    	if (this.createdList)
    		this.createdList.discardAllEdits();
    }
});

isc.SListGrid.addProperties({
	width: "100%",
    height: "100%",
    headerHeight: 30,
    cellHeight: 30,
    canEdit: false,
    autoDraw: false,
    alternateRecordStyles: true,
    autoFetchData: false,
    showFilterEditor: true,
    filterOnKeypress: true,
    editEvent: "doubleClick",
    listEndEditAction: "next",
    autoFitFieldWidths : true,
    autoFitWidthApproach : "title",
    scrollRedrawDelay:0,
    canFreezeFields: false,
    dataPageSize: 30,
    showGridSummary: false,
    sortByDisplayField: true,
    showEmptyMessage: true
});

isc.SListGrid.addMethods({
	initWidget: function() {
		this.Super("initWidget", arguments);
		isc.SListGrid.regiestCreatedList(this);
	},
	saveAllEdits: function(rows, saveCallback) {
		var list = this;
		var groupByField = list.groupByField;
		if (groupByField) {
			list.ungroup();
		}
		var _saveCallback = isc.clone(saveCallback);
		saveCallback = function() {
            if (_saveCallback) _saveCallback();
            list.groupBy(groupByField);
        };
		return this.Super("saveAllEdits", arguments);
	},
	successPrompt: function(action) {
    	var success = action + " Successfully !";
    	isc.say(success);
    },
    refreshData: function() {
    	this.setData([]);
    	this.fetchData();
    },
    filterEditorSubmit : function (criteria) {
		if (filterFromGTTo(criteria, "createdTime")) {
			return false;
		}
	},
	filterData: function (criteria, callback, requestProperties) {
		criteria = isc.addProperties(criteria, {"searchType" : "filter"});
		
		this.setData([]);
		this.Super("filterData", arguments);
	}
});

//SDynamicForm
isc.ClassFactory.defineClass("SDynamicForm", DynamicForm);
isc.SDynamicForm.addProperties({
    width: "100%",
    height: "100%",
    autoDraw: false,
    wrapItemTitles: false,
    numCols: 2,
    cellBorder: 0
});

isc.SDynamicForm.addMethods({
    successPrompt: function(action) {
    	var success = action + " Successfully !";
    	isc.say(success);
    },
	cancelChanged: function(window) {
		window.Super("hide", arguments);
		window.isShow = false;
		window.onHide();
	},
    disabledButton: function(disabled) {
    	this.getField("saveBtn").setDisabled(disabled);
    }
});

//SHLayout
isc.ClassFactory.defineClass("SHLayout", HLayout);
isc.SHLayout.addProperties({
	width: "100%",
    height: "100%",
    autoDraw: false
});

isc.SHLayout.addMethods({
	disabledButton: function(disabled) {
    	var buttons = this.getMembers();
    	for (var i = 0; i < buttons.length; i++) {
    		buttons[i].setDisabled(disabled);
    	}
    }
});

//SVLayout
isc.ClassFactory.defineClass("SVLayout", VLayout);
isc.SVLayout.addProperties({
	width: "100%",
    height: "100%",
    autoDraw: false
});

//SSectionStack
isc.ClassFactory.defineClass("SSectionStack", SectionStack);
isc.SSectionStack.addProperties({
    visibilityMode:"multiple",
    autoDraw:false,
    width:"100%",
    height:"100%"
});

//STabSet
isc.ClassFactory.defineClass("STabSet", TabSet);
isc.STabSet.addProperties({
    autoDraw:false,
    destroyPanes:false,
    width:"100%",
    height:"100%"
});


//SWindow
isc.ClassFactory.defineClass("SWindow", Window);
isc.SWindow.addProperties({
	width:1070,
    height:600,
    autoDraw:false,
    autoSize:false,
    showFooter:true,
    showShadow:false,
    autoCenter:true,
    canDrag:true,
    canDragResize:true,
    //dragAppearance:"none",
    maximized:false,
    showMaximizeButton:true,
	isShow:false,
	keepInParentRect:true,
	headerIconProperties: {
		click:function() {
			this.parentElement.parentElement.hide();
		}
	}
});

isc.SWindow.addMethods({
    initWidget: function() {
        this.Super("initWidget", arguments);
        isc.SWindowManager.registCreatedWindow(this);
    },
    show:function() {
        this.Super("show", arguments);
        this.isShow = true;
        this.onShow();
    },
    hide:function() {
    	if (this.checkChanged()) {
            var w = this;
            isc.confirm("The data has changed, leave will cancel the changes, Are you sure leave?",
               function(value) {
                if (value) {
                    w.Super("hide", arguments);
			        w.isShow = false;
			        w.clearData();
			        w.onHide();
                }
            });
        } else {
	        this.Super("hide", arguments);
	        this.isShow = false;
	        this.clearData();
	        this.onHide();
        }
    },
    clearData:function() {
        this.discardChange();
    },
    onShow:function() {
    	
    },
    onHide:function() {
        
    },
    checkChanged: function() {
        return false;
    },
    discardChange: function() {
    	
    }
});

isc.ClassFactory.defineClass("SWindowManager");
isc.SWindowManager.addClassProperties({
	createdWindow: null,
	windowPosition:[10, 350, 730]
});

isc.SWindowManager.addClassMethods({
    registCreatedWindow: function(window) {
    	this.createdWindow = window;
    },
    hideCreatedWindow: function() {
    	if (this.createdWindow) {
    		if (this.createdWindow && this.createdWindow.isShow) {
    			this.createdWindow.discardChange();
    			this.createdWindow.hide();
    		}
    	}
    },
    checkChanged: function() {
    	if (this.createdWindow) {
    		if (this.createdWindow.isShow && this.createdWindow.checkChanged()) {
    			return true;
    		}
    	}
    	return false;
    }
});



//Define Grid Pager as a new subclass of VLayout
//This will show a ListGrid and controls to navigate around by "pages" of data
isc.ClassFactory.defineClass("SGridPager", "VLayout");

isc.SGridPager.addProperties({


// pageSize - how many records to show per 'page' of data
pageSize:30,
// numPages - how many page navigation buttons to show at one time
numPages:4,
// showTotal - should the total number of rows be displayed in the footer
showTotal:true,

initWidget : function () {

   this.Super("initWidget", arguments)
   if (!this.grid) {
       this.logWarn("GridPager class requires a specified 'grid' property");
       return;
   }

   // handle being passed an ID
   if (isc.isA.String(this.grid)) this.grid = window[this.grid];
   
   // Allow the listGrid enough space for 1 "page" of data
   var gridHeight = (this.grid.cellHeight * this.pageSize) + this.grid.headerHeight;
   this.setHeight(gridHeight + this.footerHeight);        
   
   // Observe dataChanged on the grid - if the total number of rows changes we may need
   // to rework our pagination
   this.observe(this.grid, "dataChanged", "observer.goToPage(observer.pageNum, true)");
   
   // grid shows up at the top of the VLayout
   this.addMember(this.grid);

   // create the footer - HLayout to contain controls / totals information
   this.makeFooter();
   this.addMember(this.footer);
   
   // create the total label if appropriate
   if (this.showTotal) {
       this.makeTotalLabel();
       this.footer.addMember(this.totalLabel);
   }
   
   // have the paging controls be right-aligned in the footer
   this.footer.addMember(isc.LayoutSpacer.create({width:"*"}));
   
   // create the pager controls
   this.pagerControls = this.makePagerControls();
   this.footer.addMember(this.pagerControls);
   
   // always start at page 1
   this.goToPage(1);
   
   return this.Super("initWidget", arguments);
},

draw : function () {
   this.Super("draw", arguments);
   // On an external scroll of the grid, ensure we update our pageNum info
   var body = this.grid.body;
   body.addMethods({
       scrolled : new Function (this.getID() + ".listGridScrolled(this.getScrollTop())")
   });

},

listGridScrolled : function (scrollTop) {
   if (this._scrollingGrid) return;
   var rowNum = scrollTop / this.grid.getRowHeight(),
       // each page spans from start (pageNum-1 * pageSize) to start + pageSize
       start = (this.pageNum-1) * this.pageSize,
       end = start + this.pageSize;
   
   if (rowNum > end || rowNum < start) {
       this.pageNum = Math.ceil(rowNum / this.pageSize);
       this.updatePagerControls();
   }
   
},

// Footer - will contain total label and page navigation controls
footerHeight:20,

makeFooter : function () {
   // Footer: an HLayout containing the 'total' label and the paging controls
   this.footer = isc.HLayout.create({
       autoDraw:false,
       height:this.footerHeight, overflow:"hidden",
       memberMargin:10,
       layoutLeftMargin:5, layoutRightMargin:5
   });
   return this.footer;
},

// total label
makeTotalLabel : function () {
   this.totalLabel = isc.Label.create({
       autoDraw:false, 
       creator:this,
       wrap:false, align:"left",
       getTitle: function () {
           return this.creator.getTotalLabelTitle();
       }
   });
   return this.totalLabel;
},

getTotalLabelTitle : function () {
   var totalRows = this.grid.getTotalRows();
   return "Total Rows:" + totalRows;
},

// pager navigation controls
pagerButtonWidth:30,

makePagerControls : function () {
   var pageSize = this.pageSize,
       numPages = this.numPages;
   
   var buttons = [];
   buttons.add(isc.Label.create({
       autoDraw:false,
       width:this.pagerButtonWidth,            
       contents:"&lt;&lt;",
       align:"center",            
       cursor:"hand",
       gridPager:this,
       click:function () {
           this.gridPager.previousPages();
       }
   }));
   
   for (var i = 0; i < numPages; i++) {
       buttons.add(isc.Label.create({
           align:"center",
           autoDraw:false,
           cursor:"hand",
           width:this.pagerButtonWidth,
           gridPager:this,
           click:function () {
               this.gridPager.goToPage(this.pageNum);
           },
           getTitle : function () {
               var pageNum = this.pageNum;
               if (this.gridPager.pageNum == this.pageNum) return this.pageNum;
               return "<u>" + this.pageNum + "</u>"
           },   
           // Remember pageNum is 1-based not zero based
           pageNum:i+1
       }));
   }
   
   buttons.add(isc.Label.create({
       autoDraw:false,
       contents:"&gt;&gt;",
       align:"center",            
       width:this.pagerButtonWidth,            
       cursor:"hand",
       gridPager:this,
       click:function () {
           this.gridPager.nextPages();
       }
   }));
   
   return isc.ToolStrip.create({
       width:1, overflow:"visible",
       styleName:"normal",
       members:buttons,
       autoDraw:false
   })
},

// methods to actually manage pages and navigation

getTotalPages : function () {
   var total = this.grid.getTotalRows(),
       pages = Math.ceil(total / this.pageSize);
   // never return zero pages
   if (pages == 0) pages = 1;
   return pages;
},

goToPage : function (pageNum, forceRefresh) {
   // clamp to the end of the possible set of pages
   var pages = this.getTotalPages();
       
   if (pageNum > pages) pageNum = pages;
   if (pageNum < 1) pageNum = 1;       
   
   if (!forceRefresh && this.pageNum == pageNum) return;

   this.pageNum = pageNum;
   this._scrollingGrid = true;
   this.grid.scrollRecordIntoView((pageNum -1) * this.pageSize, false);
   this._scrollingGrid = false;
   // update the buttons
   this.updatePagerControls();
},


// given a page number, returns the first page in the "group" of pages we will show in
// the navigation controls
// We're showing N pages in the pager controls, so we'll always be starting with
// a multiple of N
getGroupStart : function (pageNum) {    
   var numPages = this.numPages,
       groupIndex = Math.ceil(pageNum / numPages)-1, 
       groupStart = 1 + (groupIndex * numPages);
   // If you're in the "last" group - shift the start if necessary so we always 
   // show numPages links (unless that would give us a negative value, of course)
   groupStart = Math.max(1, Math.min(groupStart, (this.getTotalPages() - (numPages-1))));
   return groupStart;
},

updatePagerControls : function () {
   var controls = this.pagerControls.members,
       pageNum = this.pageNum, numPages = this.numPages,
       total = this.getTotalPages();
       
   // if we're already showing a group that contains the page we moved to, don't shift
   // groups - confusing UI
   // [This can happen at the end of pages where we have overlapping groups]
   if (this.groupStart <= pageNum && ((this.groupStart + numPages -1) >= pageNum)) {
       groupStart = this.groupStart;
   } else {
       groupStart = this.getGroupStart(pageNum);
   }
   this.groupStart = groupStart; 
   
   var currentPage = this.groupStart;
       
   for (var i = 1; i < controls.length-1; i++) {
       controls[i].pageNum = currentPage;
       if (currentPage > total) controls[i].hide();
       else if (!controls[i].isVisible()) controls[i].show();
       
       if (currentPage == this.pageNum) controls[i].setCursor("default");
       else controls[i].setCursor("hand");
       currentPage += 1;
   }
   
   // show / hide the prev next buttons if appropriate
   if (groupStart == 1) controls[0].hide();
   else controls[0].show();
   
   if (groupStart + numPages > total) controls[controls.length-1].hide();
   else controls[controls.length-1].show();
   
   if (this.footer.isDrawn()) this.footer.markForRedraw();
},

previousPages : function () {
   var groupStart = this.groupStart;
   this.goToPage(groupStart - 1);
},

nextPages : function () {
   this.goToPage(this.groupStart + this.numPages);        
}
});




function afterUploadFile(uploadStatus, message) {
	if (uploadStatus == "0") {
		uploadFileForm.editNewRecord();
		var item = uploadFileWindow.fileItem;
		item.setValue(message);
		item.afterUpload(item.form, item, message);
		uploadFileWindow.hide();
	} else {
		uploadFileForm.setValue("uploadFailure", message);
		uploadFileForm.showItem("uploadFailure");
	}
};

isc.Window.create({
    ID:"uploadFileWindow",
    width: 500,
    height: 250,
    title: "Upload File",
    defaultLayoutAlign:"center",
    showMinimizeButton:false,
    autoCenter:true,
    autoDraw:false,
    isModal:true,
    //canFocusInHeaderButtons:true,
    membersMargin:10,
    visibility:"hidden",
    //useBackMask:false,
    fileItem:null,
    items: [
        isc.DynamicForm.create({
            ID: "uploadFileForm",
            width:"100%",
            height:"100%",
            autoDraw:false,
            //cellBorder:1,
            cellPadding:5,
            numCols:4,
            colWidths:[100, "*", 100, "*"],
            wrapItemTitles: false,
            target:"uploadFileFrame",
            encoding:"multipart",
            dataSource: uploadFileDS,
            action:"uploadFile?callbackFunctionName=afterUploadFile",
            fields: [
                {name:"file", width:"*", colSpan:3, startRow:false, endRow:true,
                    prompt: "Maximum size is 15MB",
                    icons: [{src: "[SKIN]/actions/help.png", prompt: "Maximum size is 15MB"}],
                    validateOnChange: true, 
                    focus: function(form, item) {
			        	this.Super("focus", arguments);
			        	item.validate();
			        }
                },
                {name:"uploadFailure", type:"blurb", cellStyle:"formCellError", width:"*", colSpan: 4, visible:false },
                {name: "fileNamePrefix", type:"HiddenItem"},
                {name: "fileNameSuffix", type:"HiddenItem"},
//                {name: "useOriginalFileName", type:"HiddenItem"},
                {name: "fileNameAliasCN", type:"HiddenItem"},
                {name: "fileNameAliasEN", type:"HiddenItem"},
                {name: "fileType", type:"HiddenItem"},
                {type:"SpacerItem", height:30},
                {title: "Save", type: "button", width:100, colSpan:4, align:"center",
                    click: function() {
                    	var item = uploadFileWindow.fileItem;
                    	uploadFileForm.setValue("fileNamePrefix", item.fileNamePrefix);
                    	uploadFileForm.setValue("fileNameSuffix", item.fileNameSuffix);
//                    	uploadFileForm.setValue("useOriginalFileName", item.useOriginalFileName);
                    	uploadFileForm.setValue("fileType", item.fileType);
                    	uploadFileForm.setValue("fileNameAliasCN", item.fileNameAliasCN);
                    	uploadFileForm.setValue("fileNameAliasEN", item.fileNameAliasEN);
                    	if (!uploadFileForm.validate(false)) return;
                    	uploadFileForm.submitForm();
                    }
                }
            ]
        })
    ],
    closeClick:function() {
        //this.Super("closeClick", arguments);
        uploadFileForm.editNewRecord();
        var form = this.items[0];
        form.getField("file").title = "";
        form.clearValues();
        this.hide();
        //this.sendToBack();
    },
    setFileFieldTitle:function(title) {
    	var form = this.items[0];
    	form.getField("file").title = title;
    	form.getField("file").redraw();
    }
});

isc.defineClass("UploadFileItem", "TextItem").addProperties({
    title:"Upload File",
    align:"left",
    overflow:"hidden",
    canEdit:true,
    fileType:null,
    click : function (form, item) { 
        //this.Super("focus", arguments);
        //isc.Hover.clear();
    	
        uploadFileWindow.fileItem = item;
        var title = item.getTitle();
        uploadFileWindow.setTitle("Upload " + title);
        uploadFileWindow.setFileFieldTitle(title);
        uploadFileWindow.show();
        //uploadFileWindow.focus();
        //uploadFileWindow.bringToFront();
    },
    afterUpload: function(form, item, fileName) {
    	if (form.itemChanged) {
    	    form.itemChanged(item, fileName);
    	}
    },
    changed: function(form, item, fileName) {
    	if (form.itemChanged) {
    	    form.itemChanged(item, fileName);
    	}
    }
//    keyUp:function(item, form, keyName) {
//        uploadFileWindow.fileItem = item;
//        uploadFileWindow.show();
//    }
});


isc.ClassFactory.defineClass("CWindowManager");
isc.CWindowManager.addClassProperties({
	createdWindows:[],
	campaign_coupon_window:[],
	distributionList:[],
	windowPosition:[10, 350, 730]
});
isc.CWindowManager.addClassMethods({
    registCreatedWindow: function(window) {
    	this.createdWindows.add(window);
    },
    hideAllCreatedWindow: function() {
    	for (var i = 0; i < this.createdWindows.length; i++) {
    		var w = this.createdWindows[i];
    		if (w && w.isShow) {
    			w.discardChange();
    			w.hide();
    		}
    	}
    },
    checkChanged: function() {
    	for (var i = 0; i < this.createdWindows.length; i++) {
    		var w = this.createdWindows[i];
    		if (w.isShow && w.checkChanged()) {
    			return true;
    		}
    	}
    	return false;
    },
    showCampaignCouponWindow: function(record) {
        if (this.butterfly_window.length == 0 || this.butterfly_window.length < 3) {
        	var index = this.butterfly_window.length;
        	var layoutId = "_" + Math.floor(Math.random() * 100 + 1);
	        this.campaign_coupon_window[index] = isc.ButterflyWindow.create({ID:"campaignCouponWindow" + layoutId});
	        this.campaign_coupon_window[index].initLayout(layoutId);
            this.campaign_coupon_window[index].show();
            this.campaign_coupon_window[index].editRecord(record);
            return;
        } else {
            for (i = 0; i < this.campaign_coupon_window.length; i++) {
                if (!this.campaign_coupon_window[i].isShow) {
                	this.campaign_coupon_window[i].show();
                	this.campaign_coupon_window[i].editRecord(record);
                	return;
                }            
		    }
	    }
	},
	
	registDestributionList: function(list) {
		this.distributionList.add(list);
	},
	
	unRegistDestributionList: function(list) {
        this.distributionList.remove(list);
    },
    
    refreshDestributionList: function() {
    	for (i = 0; i < this.distributionList.length; i++) {
    		var list = this.distributionList[i];
    		if (list && list.window.isShow) {
    			list.setData([]);
    			list.fetchData();
    			list.setAutoFitWidthApproach("title");
    			list.setAutoFitFieldWidths(true);
    		}
    	}
    }
});


isc.ClassFactory.defineClass("CampaignCouponWindowTabSet", CTabSet);
isc.CampaignCouponWindowTabSet.addProperties({
    refreshTitle:function(tabIndex, isChanged) {
        switch (tabIndex) {
        case 0:
            if (isChanged) {
                this.setTabTitle(tabIndex, "<font color='red'>CampaignCoupon*</font>");
            } else {
                this.setTabTitle(tabIndex, "Campaign Coupon");
            }
        }
    }
});

isc.ClassFactory.defineClass("CampaignCouponWindow", CWindow);
isc.CampaignCouponWindow.addProperties({
	title: "Add Campaign Coupon",
	CampaignCouponPage: null,
	landingPage: null,
	distributionPage: null,
	tabSet: null,
	layoutId: null,
    initWidget:function(){
        this.Super('initWidget', arguments);
        //isc.CWindowManager.registeAddWindow(this);
    },
    initLayout: function(layoutId) {
    	this.layoutId = layoutId;
        this.tabSet = CampaignCouponWindowTabSet.create({
        	ID: "CampaignCouponWindowTabSet" + layoutId,
        	tabs:[
		        {title:"CampaignCoupon", pane: this.CampaignCouponPage,
		            tabDeselected: function(tabSet, tabNum, tabPane, ID, tab, newTab) {
		            	return this.pageDeselected(tabSet, tabNum, tabPane, ID, tab, newTab);
		            }
		        },
		        {title:"Landing", pane: this.landingPage,
		            tabDeselected: function(tabSet, tabNum, tabPane, ID, tab, newTab) {
		            	return this.pageDeselected(tabSet, tabNum, tabPane, ID, tab, newTab);
		            }
		        },
		        {title:"Distribution", pane: this.distributionPage,
		            tabDeselected: function(tabSet, tabNum, tabPane, ID, tab, newTab) {
		            	return this.pageDeselected(tabSet, tabNum, tabPane, ID, tab, newTab);
		            }
		        }
             ],
             pageDeselected: function(tabSet, tabNum, tabPane, ID, tab, newTab) {
             	if (tabPane && tabPane.isValueChanged) {
             		if (tabSet.isCanceled) {
             			tabSet.isCanceled = false;
             			return;
             		}
             		isc.confirm("The data has changed, leave will cancel the changes, " +
             				"Are you sure leave this tab?", function(value) {
             			if (value) {
             				tabSet.refreshTitle(tabNum, false);
             				tabSet.isCanceled = true;
             				tabPane.canceledChanges();
             				/*if (tabPane.CampaignCouponPageForm) {
             					var needInputOtherLang = tabPane.CampaignCouponPageForm.getValue("needInputOtherLang");
             					if (needInputOtherLang) {
             						isc.warn("Please input English language for CampaignCoupon Name and Nametag Image");
             						tabSet.isCanceled = false;
             						return false;
             					} 
             				}*/
             				tabSet.selectTab(newTab);
             			} 
             		});
             		tabSet.isCanceled = false;
             		return false;
             	} else {
             		tabSet.isCanceled = false;
             		/*if (tabPane.CampaignCouponPageForm) {
             			var needInputOtherLang = tabPane.CampaignCouponPageForm.getValue("needInputOtherLang");
             			if (needInputOtherLang) {
             				isc.warn("Please input English language for CampaignCoupon Name and Nametag Image");
             				return false
             			} 
             		}*/
             		return true;
             	}
             }
        }); 
        this.addItem(this.tabSet);
        this.CampaignCouponPage.window = this;
        this.CampaignCouponPage.tabSet = this.tabSet;
        this.CampaignCouponPage.landingPage = this.landingPage;
        this.CampaignCouponPage.distributionPage = this.distributionPage;
        
        this.landingPage.window = this;
        this.landingPage.tabSet = this.tabSet;
        this.landingPage.CampaignCouponPage = this.CampaignCouponPage;
        
        this.distributionPage.window = this;
        this.distributionPage.tabSet = this.tabSet;
    },
    initData: function() {
        
    },
    editRecord:function(record) {
        if (this.CampaignCouponPage) this.CampaignCouponPage.editRecord(record);
        if (this.landingPage) this.landingPage.editRecord(record, true);
        
        // hidden distribution when CampaignCouponType=DIY_CampaignCoupon(1) || DIY_SEND_DISTRIBUTION(2) 
        if (record && record.CampaignCouponType > 0) {
        	var tabSet = this.CampaignCouponPage.tabSet;
        	if (tabSet.tabs.length == 3)
        		tabSet.removeTab(tabSet.tabs.length - 1);
        	tabSet.selectTab(0);
        } else {
        	var tabSet = this.CampaignCouponPage.tabSet;
        	if (tabSet.tabs.length == 2) {
        		tabSet.addTab({
        			title:"Distribution", 
        			pane: this.distributionPage,
        			tabDeselected: function(tabSet, tabNum, tabPane, ID, tab, newTab) {
		            	return this.pageDeselected(tabSet, tabNum, tabPane, ID, tab, newTab);
		            }
		        });
        	}
        	 
        	if (this.distributionPage) this.distributionPage.initRecord(record);
        }
        
        if (record) {
        	setWindowTitle(this, "Update CampaignCoupon", record.CampaignCouponName, record.id, record.CampaignCouponImage);
        } else {
        	this.setTitle("Add CampaignCoupon");
        }
    },
    clearData:function() {
    	if (this.CampaignCouponPage) this.CampaignCouponPage.clearData();
        if (this.landingPage) this.landingPage.clearData();
        if (this.distributionPage) this.distributionPage.clearData();
        this.refreshTabSetTitle(0, false);
        this.refreshTabSetTitle(1, false);
        if (this.tabSet.tabs.length == 3) {
        	this.refreshTabSetTitle(2, false);
        }
        this.Super("clearData", arguments);
    },
    refreshTabSetTitle:function(tabIndex, isChanged) {
    	if (this.tabSet) {
    		this.tabSet.refreshTitle(tabIndex, isChanged);
    	}
    },
    /*hide: function() {
    	var pane = this.tabSet.getSelectedTab().pane; 
    	if (pane.isValueChanged) {
    		isc.confirm("The data has changed, leave will cancel the changes, Are you sure leave this tab?",
    		   function(value) {
                if (value) {
                    pane.isValueChanged = false;
                    pane.window.hide();
                }
            });
    	} else {
    	    this.Super("hide", arguments);
    	}
    },*/
    onShow: function() {
        var list = this.distributionPage.distributionList;
        if (list) {
            list.window = this;
            isc.CWindowManager.registDestributionList(list);
        }
    },
    onHide: function() {
        //this.CampaignCouponPage.isValueChanged = false;
        //this.landingPage.isValueChanged = false;
        //this.distributionPage.isValueChanged = false;
        
        var list = this.distributionPage.distributionList;
        if (list) {
            isc.CWindowManager.unRegistDestributionList(list);
        }
    },
    checkChanged: function() {
    	if (this.CampaignCouponPage.isValueChanged || this.landingPage.isValueChanged
    	    || this.distributionPage.isValueChanged)
    	    return true;
    	return false;
    },
    discardChange: function() {
    	this.CampaignCouponPage.isValueChanged = false;
        this.landingPage.isValueChanged = false;
        this.distributionPage.isValueChanged = false;
    }
});
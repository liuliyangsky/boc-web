isc.Tree.create({
    ID: "mainMenuTree",
    root: {children: AuthManager.authorizedMenu}
});

// for the test layout
isc.SectionStack.create({
    ID:"rightSideTestLayout",
    width:"100%",
    showResizeBar:true,
    visibilityMode:"multiple",
    animateSections:true,
    autoDraw:false,
    sections:[
        {ID:"testSection", title:"", autoShow:true, canCollapse:false}
    ]
});

isc.TreeGrid.create({
    ID: "mainMenuTreeGrid",
    autoDraw: false,
    width: "100%",
    showHeader: false,
    data: mainMenuTree,
    showResizeBar: true,
    autoFetchData: false,
    cellHeight: 35,
    selectionType:"single",
    prevSelectedNode: null,
    prevSelectedRecord: null,
    dataProperties:{
        dataArrived:function (parentNode) {
            this.openAll();
        }
    },
    nodeClick: function(viewer, node, recordNum) {
        if (this.prevSelectedNode) {
        	if (node.id != this.prevSelectedNode.id) {
        		if (isc.SWindowManager.checkChanged() || isc.STreeGrid.checkChanged() || isc.SListGrid.checkChanged()) {
        			var treeGrid = this;
        			isc.confirm("The data has changed, leave will cancel the changes, Are you sure leave?", function(value) {
                        if (value) {
                        	isc.SWindowManager.hideCreatedWindow();
                        	isc.SListGrid.discardChanged();
                        	isc.STreeGrid.discardChanged();
                        	treeGrid.switchToNode(node);
                        } else {
                        	treeGrid.deselectAllRecords();
                        	treeGrid.refreshRow(recordNum);
                            treeGrid.selectSingleRecord(treeGrid.prevSelectedRecord);
                        }
        			});
                } else {
                	this.switchToNode(node);
                }
        	} else {
        		this.switchToNode(node);
        	}
        } else {
        	this.switchToNode(node);
        }
    },
    switchToNode: function(node) {
    	if (node.id == "m_logout") {
           logoutPopupWindow.show();
        } else if (node.id == "m_role") {
        	if (mainRightSideLayout.getMember("systemRoleMainBody") != null) {
        		mainRightSideLayout.refreshData(systemRoleMainBody);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("systemRoleMainBody");
            mainRightSideLayout.refreshData(systemRoleMainBody);
        } else if (node.id == "m_user") {
        	if (mainRightSideLayout.getMember("systemUserMainBody") != null) {
        		mainRightSideLayout.refreshData(systemUserMainBody);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("systemUserMainBody");
            mainRightSideLayout.refreshData(systemUserMainBody);
        } else if (node.id == "m_hold_campaign") {
        	if (mainRightSideLayout.getMember("campaginManagerMainBody") != null) {
        		mainRightSideLayout.refreshData(campaginManagerMainBody);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("campaginManagerMainBody");
            mainRightSideLayout.refreshData(campaginManagerMainBody);
        }else if (node.id == "m_campaign_coupon") {
        	if (mainRightSideLayout.getMember("campaignCouponMainBody") != null) {
        		mainRightSideLayout.refreshData(campaignCouponMainBody);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("campaignCouponMainBody");
            mainRightSideLayout.refreshData(campaignCouponMainBody);
        }else if(node.id == "m_activity_management"){
        	if (mainRightSideLayout.getMember("activityManagementMainBody") != null) {
        		mainRightSideLayout.refreshData(activityManagementMainBody);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("activityManagementMainBody");
            mainRightSideLayout.refreshData(activityManagementMainBody);
        }else if (node.id == "m_activity_address") {
        	if (mainRightSideLayout.getMember("activityAddressMainBody") != null) {
        		mainRightSideLayout.refreshData(activityAddressMainBody, node.title);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("activityAddressMainBody");
            mainRightSideLayout.refreshData(activityAddressMainBody, node.title);
        } else if (node.id == "m_merchant_management") {
        	if (mainRightSideLayout.getMember("merchantMainBody") != null) {
        		mainRightSideLayout.refreshData(merchantMainBody);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("merchantMainBody");
            mainRightSideLayout.refreshData(merchantMainBody);                     
        } else if(node.id == "m_merchant_lang_map"){
        	if (mainRightSideLayout.getMember("merchantLangMapMainBody") != null) {
        		mainRightSideLayout.refreshData(merchantLangMapMainBody);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("merchantLangMapMainBody");
            mainRightSideLayout.refreshData(merchantLangMapMainBody);
        } else if(node.id == "m_push_schedule"){
        	if (mainRightSideLayout.getMember("pushScheduleConfigMainBody") != null) {
        		mainRightSideLayout.refreshData(pushScheduleConfigMainBody);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("pushScheduleConfigMainBody");
            mainRightSideLayout.refreshData(pushScheduleConfigMainBody);
        } else if(node.id == "m_vip_config"){
        	if (mainRightSideLayout.getMember("vipConfigMainBody") != null) {
        		mainRightSideLayout.refreshData(vipConfigMainBody);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("vipConfigMainBody");
            mainRightSideLayout.refreshData(vipConfigMainBody);
        } else if(node.id == "m_push_statistics"){
        	if (mainRightSideLayout.getMember("pushReportMainBody") != null) {
        		mainRightSideLayout.refreshData(pushReportMainBody);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("pushReportMainBody");
            mainRightSideLayout.refreshData(pushReportMainBody);
        } else if(node.id == "m_vip_grade"){
        	if (mainRightSideLayout.getMember("vipGradeMainBody") != null) {
        		mainRightSideLayout.refreshData(vipGradeMainBody);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("vipGradeMainBody");
            mainRightSideLayout.refreshData(vipGradeMainBody);
        } else if(node.id == "m_upgrade_reminder"){
        	if (mainRightSideLayout.getMember("updateUpgradeReminderMainBody") != null) {
        		mainRightSideLayout.refreshData(updateUpgradeReminderMainBody, node.title);
                return;
            }
            mainRightSideLayout.setMembers([]);
            mainRightSideLayout.addMember("updateUpgradeReminderMainBody");
            mainRightSideLayout.refreshData(updateUpgradeReminderMainBody, node.title);
        }
    	this.prevSelectedNode = node;
        this.prevSelectedRecord = this.getSelectedRecord();
    }
});

isc.SVLayout.create({
    ID: "mainRightSideLayout",
    height: "100%",
    width: "80%",
    members: [
        
    ],
    refreshData: function(sectionStack, name) {
    	if (name) {
    		sectionStack.refreshData(name);
    	} else {
    		sectionStack.refreshData();
    	}
    }
});

isc.SLabel.create({
    ID: "loginUserLabel",
    title:"IButton",
    autoDraw:false,
    wrap:false,
    contents: "<span style='font-size:12px;color:black'><b>[User: " + isc.AuthManager.userDetails.userName + "]</b></span>"
});

isc.SHLayout.create({
    ID: "mainLayout",
    height: "100%",
    width: "100%",
    members: [
        isc.SectionStack.create({
            ID:"mainLeftSideLayout",
            width:250,
            showResizeBar:true,
            visibilityMode:"multiple",
            animateSections:true,
            autoDraw:false,
            sections:[
                {title:"<span style='font-size:14px;'><b>Menu</b></span>",
                 controls:[loginUserLabel],
                 autoShow:true, canCollapse:false, items:[mainMenuTreeGrid]}
            ]
        }),
        
        mainRightSideLayout
    ]
});
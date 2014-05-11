isc.ClassFactory.defineClass("UIPages");

isc.UIPages.addClassProperties({
	commonPages:[
	    "shared/ui/login/logoutWindow.js",
        "shared/ui/main.js"
    ],
    menu: [
		{name:"Activity", id:"m_activity", title:"<span style='font-size:12px;'><b>Activity</b></span>", isFolder:true,
			children: [
			           {name:"activityManagement", id:"m_activity_management", title:"<span style='font-size:11px;'><b>Activity Management</b></span>"},
			           {name:"activityAddress", id:"m_activity_address", title:"<span style='font-size:11px;'><b>Activity Address</b></span>"}
			]
		},
		{name:"Compaign", id:"m_compaign_management", title:"<span style='font-size:12px;'><b>Compaign</b></span>", isFolder:true,
			children: [
			           {name:"holdCampaign", id:"m_hold_campaign", title:"<span style='font-size:11px;'><b>Hold Campagin</b></span>"},
			           {name:"campaignCoupon", id:"m_campaign_coupon", title:"<span style='font-size:11px;'><b>Campaign Coupon</b></span>"}
			]
		},
		{name:"merchant", id:"m_merchant", title:"<span style='font-size:12px;'><b>Merchant</b></span>", isFolder:true,
			children: [
			           {name:"merchantManagement", id:"m_merchant_management", title:"<span style='font-size:11px;'><b>Merchant Management</b></span>"},
			           {name:"merchantLangMap", id:"m_merchant_lang_map", title:"<span style='font-size:11px;'><b>Merchant Lang Map</b></span>"}
			]
		},
		{name:"setting", id:"m_setting", title:"<span style='font-size:12px;'><b>Setting</b></span>", isFolder:true,icon:"menu/gears.png",
			children: [
			           {name:"systemRole", id:"m_role", title:"<span style='font-size:11px;'><b>Role</b></span>" ,icon:"menu/icon_user_role.png"},
			           {name:"systemUser", id:"m_user", title:"<span style='font-size:12px;'><b>User</b></span>",icon:"menu/icon_user.png"}
			]
		},
		{name:"logout", id:"m_logout", title:"<span style='font-size:12px;'><b>Logout</b></span>", icon:"menu/icon_logout.png", iconSize:30}
	]
});

isc.UIPages.addClassMethods({ 
	loadPages: function() {
		var pages = AuthManager.userDetails.pages;
		pages.addAll(this.commonPages);
		var menus = AuthManager.userDetails.menus;
		AuthManager.authorizedMenu = [];
		
		for (var i = 0; i < this.menu.length; i++) {
			if (this.menu[i].isFolder) {
				var menuChildren = this.menu[i].children;
				var authChild = [];
				for (var j = 0; j < menuChildren.length; j++) {
					if (menus.contains(menuChildren[j].name)) {
						authChild.add(menuChildren[j]);
					} 
				}
				
				if (authChild.length > 0) {
					this.menu[i].children = authChild;
					AuthManager.authorizedMenu.add(this.menu[i]);
				}
			} else {
				if (menus.contains(this.menu[i].name)) {
					AuthManager.authorizedMenu.add(this.menu[i]);
				}
			}
		}
		
		AuthManager.authorizedMenu.add(this.menu[4]);
		
		loadScript(pages, function() {
            isc.VLayout.create({
                ID:"swsLayout",
                width:"100%",
                height:"100%",
                members:[mainLayout]
            });
            swsLayout.draw();            
        });
    }
});

isc.Page.clearEvent("load", 1);
isc.AuthManager.loadUserDetails(function() {	
    isc.Page.setEvent("load", eval("isc.UIPages.loadPages()"));
});

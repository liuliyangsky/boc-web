isc.SVLayout.create({
	ID: "logoutLayout",
	autoDraw: false,
    membersMargin:30,
    layoutMargin:10,
    layoutTopMargin:30,
    layoutLeftMargin:30,
    align:"center",
    members: [
        isc.SLabel.create({
            left: 20,
            height: 25,
            width: "100%",
            contents:"<font size='4'>Do you want to logout?</font>"                
        }),
     	isc.SHLayout.create({
    		layoutLeftMargin: 32,
    		membersMargin: 40,
    		members: [
    			isc.SButton.create({
    				width:65,
    			    title:"Yes", click:function() {
						logoutPopupWindow.hide();
						window.location = "j_spring_security_logout";
    			    }
    			}),
    			isc.SButton.create({
    				ID:"logoutNoBtn",
    				width:65,
    				title:"No", click:function() {
    					logoutPopupWindow.hide();
    				}
    			})
    			
    		]
    	})                	
	]
});

isc.SWindow.create({
    ID:"logoutPopupWindow",
    title:"Logout",
    width: 300,
    height: 200,
    isModal: true,
    items:[
        "logoutLayout"
    ],
    headerIconProperties:{src:"login/login.gif", width:16, height:16}
});
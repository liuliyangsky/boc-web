isc.ClassFactory.defineClass("AuthManager");

isc.AuthManager.addClassProperties({
	userDetails: null,
	handleRole: null,
	authorizedMenu: null
});

isc.AuthManager.addClassMethods({ 
	
	loadUserDetails: function(callback) {	
	    this.handleRole = callback;
		RPCManager.sendRequest({
			data:{}, 
			callback: "AuthManager.setUserDetail(data)",
			actionURL: "authagent"
		});
	},
	
	setUserDetail: function(data) {
		AuthManager.userDetails = eval(data);
		
        if (this.handleRole) {
            this.handleRole();
        }
	},
	
	checkFunction: function(func) {
		if (func != null) {
			var right = this.userDetails.functions.contains(func);
			if (right != null && right == true) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
});

// This is the default implementation of a login form used when the user submits an RPC that is
// intercepted by an authenticator.  See the client reference documentation for
// RPCManager.loginRequired() for more information on how/when this is used.
isc.RPCManager.addClassMethods({

loginRequired : function (transactionNum, rpcRequest, rpcResponse) {
    // hang on to the transactionNum so we can resubmit it
    this.transactionToResend = transactionNum;

    if(!this.loginWindow) {
        this.loginForm = isc.DynamicForm.create({
        	width: 300,
            numCols: 2,
            cellPadding: 5,
            colWidths:[75, "*"],
            autoDraw: false,
            layoutAlign :"center",
            fields : [
                {type:"RowSpacerItem"},
                {name:"loginFailure", type:"blurb", cellStyle:"formCellError", width:"*",
                  defaultValue: "Invalid username or password or Inactive User", colSpan: 2,
                  visible:false },
                {name:"username", title:"Username", titleOrientation: "left", width:"*",
                  keyPress : function (item, form, keyName) {
                    if (keyName == "Enter") {
                        form.focusInItem("password");
                        return false;
                    }
                }},
                {name:"password", title:"Password", titleOrientation: "left", width:"*",
                  type:"password", keyPress : function (item, form, keyName) {
                  if (keyName == "Enter") {
                      form.loginWindow.doLogin();
                      return false;
                  }
                }},
                {name:"submitButton", type:"button", title:"Log in", align:"right", colSpan:2, width:"215",
                  click : function(form, item) {
		            if (form.getValue("username") && form.getValue("password")) {
		                form.loginWindow.doLogin();
		            } else {
		                form.showItem("loginFailure");
		            }
		          }
                },
                {type:"RowSpacerItem"},
            ]
        });
        this.loginWindow = isc.LoginWindow.create({
            title: "Session expired - please log in",
            width: 350,
            height: 100,
            autoDraw: false,
            autoCenter: true,
            autoSize: true,
            showCloseButton: false,
            showMinimizeButton: false,
            isModal: true,
            loginForm: this.loginForm,
            items: [ this.loginForm ],
            headerIconProperties:{src:"login/login.gif", width:16, height:16}
        });
        // make loginWindow available on loginForm so we can call methods on it for the Enter
        // key and the "Log in" button
        this.loginForm.loginWindow = this.loginWindow;
    }

    // only clear the form and re-focus in the username field if we're not already showing
    // it such that background RPCs occurring during our login attempt don't clear out the
    // form
    if (!(this.loginWindow.isVisible() && this.loginWindow.isDrawn())) {
        this.loginForm.clearValues(); 
        this.loginForm.delayCall("focusInItem", ["username"]);
    }

    this.loginWindow.show();
    this.loginWindow.bringToFront();
}
});


// LoginWindow - subclass of Window, adds methods for handling login responses
isc.defineClass("LoginWindow", "Window").addProperties({

// called by user pressing the login button or hitting enter in the password field.  Submit the
// request to the server.
doLogin : function () {
	this.loginForm.getField("submitButton").setTitle("Logging in ...");
	
    isc.RPCManager.credentialsURL = "j_spring_security_check";
    isc.RPCManager.sendRequest({
        // let the RPCManager know not to delay this request and to discard this
        // request/response pair if the auth attempt fails.
        containsCredentials: true,
        // we must target the special loginSuccess.html file.  You can move it anywhere you
        // want, so long as your login attempts target it.
        actionURL: isc.RPCManager.credentialsURL,
        
        // we're not going to privide any data beyond the username, password query params
        useSimpleHttp: true,
        showPrompt: false,

        // the actual credentials, from the form
        params : {
            j_username: this.loginForm.getValue("username"),
            j_password: hex_md5(this.loginForm.getValue("password"))
        },
        callback : this.getID()+".loginReply(rpcResponse)"
    });
},

// called when the server replies
loginReply : function (rpcResponse) {
    
    // clear the form values since either way we don't want to hold on to them
    this.loginForm.clearValues();
    var status = rpcResponse.status;
    if (status == isc.RPCResponse.STATUS_SUCCESS) {
        this.loginForm.hideItem("loginFailure");
        this.hide();
        window.location = "index.jsp";
        delete this.transactionsToResubmit;
    } else if (status == isc.RPCResponse.STATUS_LOGIN_INCORRECT) {
        this.loginForm.showItem("loginFailure");
        this.loginForm.getField("submitButton").setTitle("Log in");
    } else if (status == isc.RPCResponse.STATUS_MAX_LOGIN_ATTEMPTS_EXCEEDED) {
        isc.warn("Max login attempts exceeded.");
        this.loginForm.getField("submitButton").setTitle("Log in");
    }
    this.loginForm.focusInItem("username");
}

});
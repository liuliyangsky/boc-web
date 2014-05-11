<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/iscTaglib.xml" prefix="isomorphic" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<HTML><HEAD><TITLE>BOC V1.0</TITLE>

<html>
<body>
<isomorphic:loadISC skin="Enterprise" runat="server"/>
<SCRIPT>window.isomorphicDir='isomorphic/'</SCRIPT>
<SCRIPT src="js/md5.js"></SCRIPT>

<SCRIPT>
// LoginWindow - subclass of Window, adds methods for handling login responses
isc.defineClass("LoginWindow", "Window").addProperties({

// called by user pressing the login button or hitting enter in the password field.  Submit the
// request to the server.
doLogin : function () {
	this.loginForm.getField("submitButton").setTitle("Logging in ...");
	//this.loginForm.getField("submitButton").showIcon("[SKINIMG]/loadingSmall.gif");
	//this.loginForm.getField("submitButton").redraw();
	
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
    
var loginForm = isc.DynamicForm.create({
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
        {name:"submitButton", type:"button", title:"Log in", icon:"login/login.gif", align:"right", colSpan:2, width:"215",
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

var loginWindow = isc.LoginWindow.create({
    title: "Login",
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
loginForm.loginWindow = loginWindow;
loginWindow.show();
loginForm.delayCall("focusInItem", ["username"]);
</SCRIPT>
  
<SCRIPT>//'"]]>>isc_loginRequired
//
// Embed this whole script block VERBATIM into your login page to enable
// SmartClient RPC relogin.



if (!window.isc && document.domain && document.domain.indexOf(".") != -1 
    && !(new RegExp("^(\\d{1,3}\\.){3}\\d{1,3}$").test(document.domain))) 
{
    
    var set = false;
    while (document.domain.indexOf(".") != -1) {
        try {
            if (window.opener && window.opener.isc) break;
            if (window.top.isc) break;
            
            if (!set) { document.domain = document.domain; set = true; }
            else { document.domain = document.domain.replace(/.*?\./, ''); }
        } catch (e) {
            try {
                if (!set) { document.domain = document.domain; set = true }
                else { document.domain = document.domain.replace(/.*?\./, ''); }
            } catch (ee) {
                break;
            }
        }
    } 
}

var isc = top.isc ? top.isc : window.opener ? window.opener.isc : null;
if (isc && isc.RPCManager) isc.RPCManager.delayCall("handleLoginRequired", [window]);
</SCRIPT>
</body>
</html>
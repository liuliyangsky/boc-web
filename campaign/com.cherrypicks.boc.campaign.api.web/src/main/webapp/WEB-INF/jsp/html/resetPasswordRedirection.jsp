<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reset password redirection</title>
<script>
// Get URL parameters
function getURLPara(name){
  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
  var regExpS = "[\\?&]"+name+"=([^&#]*)";
  var regExp = new RegExp( regExpS );
  //var results = regExp.exec( testURL ); // testing with demo URL
  var results = regExp.exec( window.location.href );
  if( results == null )
    return "";
  else
    return results[1];
}
function redirect(){
	var UAstring = navigator.appVersion; // Get User Agent string
	if(  (UAstring.indexOf("iPhone OS")!=-1)||(UAstring.indexOf("Android")!=-1)  ){ //Redirect only if it's iPhone or Android
		if(!getURLPara("fpId") ||!getURLPara("email")|| !getURLPara("sn")) {
			alert("Invalid requests!\n非法请求！")
			return;
		}
		var url_string;

		if(UAstring.indexOf("iPhone OS")>=0){
			url_string = getURLPara("uri");
			url_string += "://resetPassword?"
			url_string += "fpId="+getURLPara("fpId");
			url_string += "&email="+getURLPara("email");
			url_string += "&sn="+getURLPara("sn");
		} else {
			url_string = getURLPara("uri");
			url_string += "://rpw?"
			url_string += "fp_id="+getURLPara("fpId");
			url_string += "&email="+getURLPara("email");
			url_string += "&sn="+getURLPara("sn");
		}

		//alert("This is a mobile device. The redirect URL will be "+url_string);
		window.location=url_string;
		//window.close();
	} else {
		alert("Please click the reset password link in mobile device.\n請在手機平台上按下重置密碼的連結。")
		//window.close();
	}
}

</script>
</head>

<body onload="redirect();">
</body>
</html>
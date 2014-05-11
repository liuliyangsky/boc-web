<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/iscTaglib.xml" prefix="isomorphic" %>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<TITLE>BOC V1.0</TITLE>

<isomorphic:loadISC skin="Enterprise" runat="server"/>
</HEAD>

<BODY bgcolor=#e0e0e0 style="font-family:Tahoma,Verdana,Arial,Helvetica,san-serif;">
<iframe id="uploadFileFrame" name="uploadFileFrame" frameborder="0" height="0" width="0" marginheight="0" marginwidth="0" scrolling="no" src="black.html"></iframe>

<script src="shared/ui/login/reloginFlow.js"></script>

<script>
<isomorphic:loadDS ID="uploadFileDS" />
<isomorphic:loadDS ID="systemFunctionDS" />
<isomorphic:loadDS ID="systemRoleDS" />
<isomorphic:loadDS ID="systemUserDS" />
<isomorphic:loadDS ID="termsConditionCNDS" />
<isomorphic:loadDS ID="termsConditionENDS" />
<isomorphic:loadDS ID="campaginManagerDS" />
<isomorphic:loadDS ID="campaignCouponDS" />
<isomorphic:loadDS ID="languageDS" />
<isomorphic:loadDS ID="activityManagementDS" />
<isomorphic:loadDS ID="activityAddressDS" />
<isomorphic:loadDS ID="merchantManagementDS" />
</script>

<script src="shared/ui/commonDefine.js"></script>
<script src="shared/ui/commonFunction.js"></script>
<script src="shared/ui/validationFunction.js"></script>

<script src="shared/ui/authManager.js"></script>
<script src="shared/ui/loadPages.js"></script>
<script src="js/md5.js"></script>

</BODY>
</HTML>

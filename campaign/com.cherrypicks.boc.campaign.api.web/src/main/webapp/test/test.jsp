<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BOC API JSP</title>
</head>
<Body>
<!-- 1 -->
<form name="EventForm" action="../test.do" method="post">
		<h2>API test</h2>
		<input type="submit" style="width: 200px" value="Test"/>
</form>

<form name="EventForm" action="../getAllCampaign1.do" method="post">
		<h2>API getAllCampaign1</h2>
		lang:<input type="text" style="width: 200px" name="lang"/>
		device:<input type="text" style="width: 200px" name="device"/>
		deviceVerNum:<input type="text" style="width: 200px" name="deviceVerNum"/>
		userId:<input type="text" style="width: 200px" name="userId"/>
		deviceId:<input type="text" style="width: 200px" name="deviceId"/>
		<input type="submit" style="width: 200px" value="getAllCampaign1"/>
</form>

<form name="EventForm" action="../getCouponList1.do" method="post">
		<h2>API getCouponList1</h2>
		campaignId:<input type="text" style="width: 200px" name="campaignId"/>
		lang:<input type="text" style="width: 200px" name="lang"/>
		device:<input type="text" style="width: 200px" name="device"/>
		deviceVerNum:<input type="text" style="width: 200px" name="deviceVerNum"/>
		userId:<input type="text" style="width: 200px" name="userId"/>
		deviceId:<input type="text" style="width: 200px" name="deviceId"/>
		<input type="submit" style="width: 200px" value="getCouponList1"/>
</form>

<form name="EventForm" action="../getCouponDetail1.do" method="post">
		<h2>API getCouponDetail1</h2>
		couponId:<input type="text" style="width: 200px" name="couponId"/>
		isKeew:<input type="text" style="width: 200px" name="isKeew"/>
		lang:<input type="text" style="width: 200px" name="lang"/>
		device:<input type="text" style="width: 200px" name="device"/>
		deviceVerNum:<input type="text" style="width: 200px" name="deviceVerNum"/>
		userId:<input type="text" style="width: 200px" name="userId"/>
		deviceId:<input type="text" style="width: 200px" name="deviceId"/>
		<input type="submit" style="width: 200px" value="getCouponDetail1"/>
</form>
<form name="EventForm" action="../getMerchantDetail1.do" method="post">
		<h2>API getMerchantDetail1</h2>
		merchantId:<input type="text" style="width: 200px" name="merchantId"/>
		lang:<input type="text" style="width: 200px" name="lang"/>
		device:<input type="text" style="width: 200px" name="device"/>
		deviceVerNum:<input type="text" style="width: 200px" name="deviceVerNum"/>
		userId:<input type="text" style="width: 200px" name="userId"/>
		deviceId:<input type="text" style="width: 200px" name="deviceId"/>
		<input type="submit" style="width: 200px" value="getMerchantDetail1"/>
</form>

<form name="EventForm" action="../getActivityList1.do" method="post">
		<h2>API getActivityList1</h2>
		lang:<input type="text" style="width: 200px" name="lang"/>
		device:<input type="text" style="width: 200px" name="device"/>
		deviceVerNum:<input type="text" style="width: 200px" name="deviceVerNum"/>
		userId:<input type="text" style="width: 200px" name="userId"/>
		deviceId:<input type="text" style="width: 200px" name="deviceId"/>
		<input type="submit" style="width: 200px" value="getActivityList1"/>
</form>

<form name="EventForm" action="../downLoadOffer1.do" method="post">
		<h2>API downLoadOffer1</h2>
		lang:<input type="text" style="width: 200px" name="lang"/>
		device:<input type="text" style="width: 200px" name="device"/>
		deviceVerNum:<input type="text" style="width: 200px" name="deviceVerNum"/>
		userId:<input type="text" style="width: 200px" name="userId"/>
		deviceId:<input type="text" style="width: 200px" name="deviceId"/>
		couponId:<input type="text" style="width: 200px" name="couponId"/>
		<input type="submit" style="width: 200px" value="downLoadOffer1"/>
</form>
<form name="EventForm" action="../getMyCouponList1.do" method="post">
		<h2>API getMyCouponList1</h2>
		lang:<input type="text" style="width: 200px" name="lang"/>
		device:<input type="text" style="width: 200px" name="device"/>
		deviceVerNum:<input type="text" style="width: 200px" name="deviceVerNum"/>
		userId:<input type="text" style="width: 200px" name="userId"/>
		deviceId:<input type="text" style="width: 200px" name="deviceId"/>
		<input type="submit" style="width: 200px" value="getMyCouponList1"/>
</form>
<form name="EventForm" action="../getActivityDetail1.do" method="post">
		<h2>API getMyCouponList1</h2>
		lang:<input type="text" style="width: 200px" name="lang"/>
		device:<input type="text" style="width: 200px" name="device"/>
		deviceVerNum:<input type="text" style="width: 200px" name="deviceVerNum"/>
		userId:<input type="text" style="width: 200px" name="userId"/>
		deviceId:<input type="text" style="width: 200px" name="deviceId"/>
		activityId:<input type="text" style="width: 200px" name="activityId"/>
		<input type="submit" style="width: 200px" value="getActivityDetail1"/>
</form>

</BODY>
</html>
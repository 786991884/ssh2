<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<sj:head jquerytheme="sunny" />
<s:head />
</head>
<body>
	<s:form action="doEditUser">
		<s:textfield name="user.name" label="用户名"></s:textfield>
		<s:textfield name="user.provice" label="省份"></s:textfield>
		<s:textfield name="user.city" label="城市"></s:textfield>
		<s:textfield name="user.birthday" label="生日"></s:textfield>
		<s:hidden name="user.id"></s:hidden>
		<s:hidden name="user.pwd"></s:hidden>
		<s:submit value="提交"></s:submit>
	</s:form>
</body>
</html>
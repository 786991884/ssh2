<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
.pagination {
	margin-top: 0px;
}
</style>
<script type="text/javascript">
	function confirmDel(event) {
		if (!window.confirm("确定删除吗")) {
			event.stopPropagation();
			event.preventDefault();
		}
	}
</script>
</head>
<body>
	<!-- user list -->
	<div class="container" style="width: 100%">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<table class="table table-bordered  table-hover" style="border: 1px;">
					<thead>
						<tr style="background-color: red">
							<td>用户名</td>
							<td>省 份</td>
							<td>城 市</td>
							<td>生 日</td>
							<td>操 作</td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="recordList" status="sta">
							<s:if test="#sta.odd">
								<tr style="background-color: pink">
									<td><s:property value="name" /></td>
									<td><s:property value="provice" /></td>
									<td><s:property value="city" /></td>
									<td><s:property value="birthday" /></td>
									<td><s:url var="viewLink" action="viewUser">
											<s:param name="id" value="%{id}"></s:param>
										</s:url> <s:url var="editLink" action="toEditUser">
											<s:param name="id" value="%{id}"></s:param>
										</s:url> <s:url var="deleteLink" action="deleteUser">
											<s:param name="id" value="%{id}"></s:param>
										</s:url> <s:url var="addLink" action="addUser">
											<s:param name="id" value="%{id}"></s:param>
										</s:url> <a href="${viewLink} "><i class="glyphicon glyphicon-eye-open"></i></a> <a href="${editLink} "><i class="glyphicon glyphicon-edit"></i></a> <a href="User_add.jsp"><i class="glyphicon glyphicon-plus"></i></a> <a href="${deleteLink} " onclick="confirmDel(event)"><i class="glyphicon glyphicon-trash"></i></a></td>
								</tr>
							</s:if>
							<s:else>
								<tr style="background-color: sliver">
									<td><s:property value="name" /></td>
									<td><s:property value="provice" /></td>
									<td><s:property value="city" /></td>
									<td><s:property value="birthday" /></td>
									<td><s:url var="viewLink" action="viewUser">
											<s:param name="id" value="%{id}"></s:param>
										</s:url> <s:url var="editLink" action="toEditUser">
											<s:param name="id" value="%{id}"></s:param>
										</s:url> <s:url var="deleteLink" action="deleteUser">
											<s:param name="id" value="%{id}"></s:param>
										</s:url> <s:url var="addLink" action="addUser">
											<s:param name="id" value="%{id}"></s:param>
										</s:url> <a href="${viewLink} "><i class="glyphicon glyphicon-eye-open"></i></a> <a href="${editLink} "><i class="glyphicon glyphicon-edit"></i></a> <a href="User_add.jsp"><i class="glyphicon glyphicon-plus"></i></a> <a href="${deleteLink} " onclick="confirmDel(event)"><i class="glyphicon glyphicon-trash"></i></a></td>
								</tr>
							</s:else>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
		<!-- pager -->
		<s:form action="viewUserByPage">
		</s:form>
		<div class="container" style="width: 100%" align="center">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div>页次：${currentPage}/${pageCount}页 &nbsp; 每页显示：${pageSize }条 &nbsp; 总记录数：${recordCount }条</div>
					<div>
						<ul class="pagination">
							<li><a href="javascript: gotoPage(1)">首页 </a></li>
							<s:iterator begin="%{beginPageIndex}" end="%{endPageIndex}" var="num">
								<s:if test="currentPage == #num">
									<%-- 当前页 --%>
									<li><a href="javascript:void(0)">${num}</a></li>
								</s:if>
								<s:else>
									<%-- 非当前页 --%>
									<li onClick="gotoPage(${num});"><a href="javascript:void(0)">${num}</a></li>
								</s:else>
							</s:iterator>
							<li><a href="javascript: gotoPage(${pageCount})">尾页 </a></li>
						</ul>
						转到： <select id="pn" onchange="gotoPage(this.value)">
							<s:iterator begin="1" end="%{pageCount}" var="num">
								<option value="${num}">${num}</option>
							</s:iterator>
						</select>

						<script type="text/javascript">
			// 回显页码
			$("#pn").val(${currentPage});
		</script>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function gotoPage( pageNum ){
		// window.location.href = "forumAction_show.action?id=${id}&pageNum=" + pageNum;
		$(document.forms[0]).append("<input type='hidden' name='pageNum' value='" + pageNum + "'/>");
		document.forms[0].submit(); // 提交表单
	}
</script>
</body>
</html>
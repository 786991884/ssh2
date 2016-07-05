<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--两点表示上一层目录  -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<!-- top -->
	<div class="container" style="width: 100%">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="javascript:void(0)"><img alt="" style="height: 35px;" src="images/vcode.jpg"></a>
					</div>
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active"><a href="javascript:void(0)">主页面</a></li>
							<li><a href='<s:url action='viewUserByPage'></s:url>'>查看用户</a></li>
						</ul>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input class="form-control" type="text" />
							</div>
							<button type="button" class="btn btn-default">搜搜</button>
						</form>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="javascript:void(0)"><span id="timerLi"><script type="text/javascript">
								function printTime() {
									var objD = new Date();
									var str, colorhead, colorfoot;
									var yy = objD.getYear();
									if (yy < 1900)
										yy = yy + 1900;
									var MM = objD.getMonth() + 1;
									if (MM < 10)
										MM = '0' + MM;
									var dd = objD.getDate();
									if (dd < 10)
										dd = '0' + dd;
									var hh = objD.getHours();
									if (hh < 10)
										hh = '0' + hh;
									var mm = objD.getMinutes();
									if (mm < 10)
										mm = '0' + mm;
									var ss = objD.getSeconds();
									if (ss < 10)
										ss = '0' + ss;
									var ww = objD.getDay();
									if (ww == 0)
										colorhead = "<font color=\"#FF0000\">";
									if (ww > 0 && ww < 6)
										colorhead = "<font color=\"#373737\">";
									if (ww == 6)
										colorhead = "<font color=\"#008000\">";
									if (ww == 0)
										ww = "星期日";
									if (ww == 1)
										ww = "星期一";
									if (ww == 2)
										ww = "星期二";
									if (ww == 3)
										ww = "星期三";
									if (ww == 4)
										ww = "星期四";
									if (ww == 5)
										ww = "星期五";
									if (ww == 6)
										ww = "星期六";
									colorfoot = "</font>";
									str = colorhead + yy + "年" + MM + "月" + dd + "日 " + hh + ":" + mm + ":" + ss + "  " + ww + colorfoot;
									$("#timerLi").empty().append(str);
								}
								printTime();
								setInterval(printTime, 1000);
							</script></span>&nbsp;&nbsp;&nbsp;</a></li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
	</div>
	<!-- content -->
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<iframe style="width: 100%; height: 400px; border: 0px;" id="mainContent" name="mainContent"> </iframe>
			</div>
		</div>
	</div>
	<!-- 底部 -->
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">&copy;版权所有，请勿复制</div>
		</div>
	</div>
</body>
</html>
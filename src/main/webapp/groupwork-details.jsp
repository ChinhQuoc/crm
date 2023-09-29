<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="plugins/images/favicon.png">
<title>Pixel Admin</title>
<!-- Bootstrap Core CSS -->
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Menu CSS -->
<link
	href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
	rel="stylesheet">
<!-- Animation CSS -->
<link href="css/animate.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="./css/custom.css">
<!-- color CSS you can use different color css from css/colors folder -->
<!-- We have chosen the skin-blue (blue.css) for this starter
          page. However, you can choose any other skin from folder css / colors .
-->
<link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
<link rel="stylesheet" href="./css/custom.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<!-- Preloader -->
	<div class="preloader">
		<div class="cssload-speeding-wheel"></div>
	</div>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top m-b-0">
			<div class="navbar-header">
				<a class="navbar-toggle hidden-sm hidden-md hidden-lg "
					href="javascript:void(0)" data-toggle="collapse"
					data-target=".navbar-collapse"> <i class="fa fa-bars"></i>
				</a>
				<div class="top-left-part">
					<a class="logo" href=<c:url value="/" />> <b> <img
							src="plugins/images/pixeladmin-logo.png" alt="home" />
					</b> <span class="hidden-xs"> <img
							src="plugins/images/pixeladmin-text.png" alt="home" />
					</span>
					</a>
				</div>
				<ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
					<li>
						<form role="search" class="app-search hidden-xs">
							<input type="text" placeholder="Search..." class="form-control">
							<a href=""> <i class="fa fa-search"></i>
							</a>
						</form>
					</li>
				</ul>
				<ul class="nav navbar-top-links navbar-right pull-right">
					<li>
						<div class="dropdown">
							<a class="profile-pic dropdown-toggle" data-toggle="dropdown"
								href="#"> <img src="plugins/images/users/varun.jpg"
								alt="user-img" width="36" class="img-circle" /> <b
								class="hidden-xs">Cybersoft</b>
							</a>
							<ul class="dropdown-menu">
								<li><a href=<c:url value="/profile"/>>Thông tin cá nhân</a></li>
								<li><a href=<c:url value="/tasks-user"/>>Thống kê công việc</a></li>
								<li class="divider"></li>
								<li><button class="btn-logout" data-toggle="modal"
										data-target="#modalLogout">Đăng xuất</button></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<!-- /.navbar-header -->
			<!-- /.navbar-top-links -->
			<!-- /.navbar-static-side -->
		</nav>
		<!-- Left navbar-header -->
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse slimscrollsidebar">
				<ul class="nav" id="side-menu">
					<li style="padding: 10px 0 0;"><a href=<c:url value="/" />
						class="waves-effect"><i class="fa fa-clock-o fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a></li>
					<li><a href=<c:url value="/users" /> class="waves-effect"><i
							class="fa fa-user fa-fw" aria-hidden="true"></i><span
							class="hide-menu">Thành viên</span></a></li>
					<li><a href=<c:url value="/role" /> class="waves-effect"><i
							class="fa fa-modx fa-fw" aria-hidden="true"></i><span
							class="hide-menu">Quyền</span></a></li>
					<li><a href=<c:url value="/groupwork" /> class="waves-effect"><i
							class="fa fa-table fa-fw" aria-hidden="true"></i><span
							class="hide-menu">Dự án</span></a></li>
					<li><a href=<c:url value="/tasks" /> class="waves-effect"><i
							class="fa fa-table fa-fw" aria-hidden="true"></i><span
							class="hide-menu">Công việc</span></a></li>
					<li><a href=<c:url value="/blank" /> class="waves-effect"><i
							class="fa fa-columns fa-fw" aria-hidden="true"></i><span
							class="hide-menu">Blank Page</span></a></li>
				</ul>
			</div>
		</div>
		<!-- Left navbar-header end -->
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row bg-title">
					<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
						<h4 class="page-title">Chi tiết công việc</h4>
					</div>
					<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
						<ol class="breadcrumb">
							<li><a href="#">Dashboard</a></li>
							<li class="active">Blank Page</li>
						</ol>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- BEGIN THỐNG KÊ -->
				<div class="row">
					<!--col -->
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="white-box">
							<div class="col-in row">
								<div class="col-md-6 col-sm-6 col-xs-6">
									<i data-icon="E" class="linea-icon linea-basic"></i>
									<h5 class="text-muted vb">CHƯA BẮT ĐẦU</h5>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-6">
									<h3 class="counter text-right m-t-15 text-danger">${ hasntStated }%</h3>
								</div>
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="progress">
										<div class="progress-bar progress-bar-danger"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: ${hasntStated}%"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.col -->
					<!--col -->
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="white-box">
							<div class="col-in row">
								<div class="col-md-6 col-sm-6 col-xs-6">
									<i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
									<h5 class="text-muted vb">ĐANG THỰC HIỆN</h5>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-6">
									<h3 class="counter text-right m-t-15 text-megna">${ starting }%</h3>
								</div>
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="progress">
										<div class="progress-bar progress-bar-megna"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: ${starting}%"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.col -->
					<!--col -->
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="white-box">
							<div class="col-in row">
								<div class="col-md-6 col-sm-6 col-xs-6">
									<i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
									<h5 class="text-muted vb">HOÀN THÀNH</h5>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-6">
									<h3 class="counter text-right m-t-15 text-primary">${ started }%</h3>
								</div>
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="progress">
										<div class="progress-bar progress-bar-primary"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: ${started}%"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.col -->
				</div>
				<!-- END THỐNG KÊ -->

				<!-- BEGIN DANH SÁCH CÔNG VIỆC -->
				<div class="row">
					<c:forEach var="item" items="${users}">
						<div class="col-xs-12">
							<a href="#" class="group-title"> <img width="30"
								src="${ item.image != null ? item.image : 'plugins/images/users/pawandeep.jpg'}" class="img-circle" />
								<span>${ item.fullName }</span>
							</a>
						</div>
						<div class="col-md-4">
							<div class="white-box">
								<h3 class="box-title">Chưa thực hiện</h3>
								<div class="message-center">
									<c:forEach var="job" items="${item.hasntStartedJobs}">
										<a href="#">
											<div class="mail-contnet">
												<h5>${ job.name }</h5>
												<span class="mail-desc">${ job.content }</span>
												<span class="time">Bắt đầu: ${ job.startDate }</span> 
												<span class="time">Kết thúc: ${ job.endDate }</span>
											</div>
										</a>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="white-box">
								<h3 class="box-title">Đang thực hiện</h3>
								<div class="message-center">
									<c:forEach var="job" items="${item.startingJobs}">
										<a href="#">
											<div class="mail-contnet">
												<h5>${ job.name }</h5>
												<span class="mail-desc">${ job.content }</span>
												<span class="time">Bắt đầu: ${ job.startDate }</span> 
												<span class="time">Kết thúc: ${ job.endDate }</span>
											</div>
										</a>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="white-box">
								<h3 class="box-title">Đã hoàn thành</h3>
								<div class="message-center">
									<c:forEach var="job" items="${item.startedJobs}">
										<a href="#">
											<div class="mail-contnet">
												<h5>${ job.name }</h5>
												<span class="mail-desc">${ job.content }</span>
												<span class="time">Bắt đầu: ${ job.startDate }</span> 
												<span class="time">Kết thúc: ${ job.endDate }</span>
											</div>
										</a>
									</c:forEach>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- END DANH SÁCH CÔNG VIỆC -->

				<!-- Modal Logout-->
				<div class="modal fade" id="modalLogout" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Notification</h4>
							</div>
							<div class="modal-body">Are you sure want to logout?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">No</button>
								<button type="button" class="btn btn-primary btn-yes">Yes</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->
			<footer class="footer text-center"> 2018 &copy; myclass.com
			</footer>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<!-- jQuery -->
	<script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Menu Plugin JavaScript -->
	<script
		src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
	<!--slimscroll JavaScript -->
	<script src="js/jquery.slimscroll.js"></script>
	<!--Wave Effects -->
	<script src="js/waves.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="js/custom.min.js"></script>
	<!-- import file logout -->
	<script type="text/javascript" src="js/logout.js"></script>
</body>

</html>
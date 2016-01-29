<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<!-- header.jsp文件包含进去,里面就有了JqueryEasyUI所需要的文件了-->
  	<jsp:include page="header.jsp"/> 
  	
    <title>后台任务调度管理系统</title>
    <link rel="shortcut icon" href="favicon.ico">
	</head>
        <body class="easyui-layout">
		<div region="north" style="background:gray;color:8827C4;height:65px;">
		<div style="font-size:25px;font-weight:bold;width:400px;padding:10px 0 0 10px;">任务调度管理系统</div>
		</div>
		<div region="west" title="导航栏" split="true" style="width:150px;">
			<div class="easyui-accordion" fit="true" border="false">
				<div title="任务操作"  icon="icon-sys" selected="true" style="overflow:auto;">
					<div class="nav-item">
						<a href="javascript:addTab('任务列表','pages/quartz/jobList.jsp')"> 
				             <img src="images/home.png"></img>
							<span>任务列表</span>
						</a>
					</div>
					<div class="nav-item">
						<a href="javascript:addTab('任务设置','pages/quartz/jobSett.jsp')">
							 <img src="images/videos.png"></img>
							<span>任务设置</span>
						</a>
					</div>
				</div>
				<div title="任务信息" style="overflow:auto;">
					<div class="nav-item">
						<a href="javascript:addTab('区域设置')">
							<span>任务信息</span>
						</a>
					</div>
					<div class="nav-item">
						<a href="javascript:addTab('设备类别')">
							<span>任务信息</span>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div region="center">
			<div id="main-center" class="easyui-tabs" fit="true" border="false">
				<div title="首页" style="padding:20px;">
					<!-- <img src="images/aa"></img> -->
					<div style="margin-top:20px;">
					<p>首页</p>
					</div>
				</div>
			</div>
		</div>
    
  </body>
</html>
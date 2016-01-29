<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  	<head>
		<%@include file="/WEB-INF/jsp/common/head.jsp" %>
		<script type="text/javascript">
			$(function(){
				$('#tree').tree({
				    onClick: function(node){
						if(node.attributes != undefined){
						var content = '<iframe scrolling="auto" frameborder="0"  src="' + "${pageContext.request.contextPath }" + node.attributes.url + '" style="width:100%;height:100%;"></iframe>';    
						$('#tTabs').tabs('add',{title:node.text,content:content,closable:true});
						}
				    }
				});
			})
		</script>
	</head>
  
	<body class="easyui-layout" >
		<div data-options="region:'north',border:false" style="overflow:hidden;height:60px;background:#B3DFDA;padding:5px; padding-left: 35px;">
			<span><h1 style="font-size: 20px; font-family: Microsoft YaHei">任务管理系统</h1></span>
		</div>
		<div data-options="region:'west',split:true,title:'Menu'" style="width:150px;padding:10px;">
			<ul id="tree" class="easyui-tree" data-options="url:'${pageContext.request.contextPath }/jquery-easyui-1.3.5/tree_data.js',method:'get',animate:true,lines:true"></ul>
		</div>
		<div data-options="region:'south',border:false" style="height:15px;background:#A9FACD;padding:10px;">
	
		</div>
		<div data-options="region:'center',border:false">
			<div id="tTabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
				<div title="Welcome" style="padding:10px" data-options="closable:true"><div>
			</div>
		</div>
	</body>
</html>
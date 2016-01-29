<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'jobSett.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="../../jquery-easyui-1.3.5/demo/demo.css" type="text/css"></link>
	<link rel="stylesheet" href="../../jquery-easyui-1.3.5/themes/icon.css" type="text/css"></link>
	<link rel="stylesheet" href="../../jquery-easyui-1.3.5/themes/default/easyui.css" type="text/css"></link>
	<script type="text/javascript" src="../../jquery-easyui-1.3.5/jquery.min.js"></script>
	<script type="text/javascript" src="../../jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
	
        <div region="center" style="padding:5px;" border="false">  
            <table id="tt" fit="true">  
                <thead>  
                    <tr>  
                        <th field="name" width="100">名称</th>  
                        <th field="style" width="100">状态</th> 
                        <th field="style" width="150">任务描述</th>  
                        <th field="area.name" width="100" formatter="areaName">下次运行时间</th>  
                        <th field="area.name" width="100" formatter="areaName">上次运行时间</th>  
                        <th field="manufacturer" width="100">上次运行结果</th> 
                        <th field="manufacturer" width="100">执行时间(秒)</th>  
                        <th field="factoryCode" width="100">创建者</th>  
                    </tr>  
                </thead>  
            </table>  
            
        </div>  
    </body>  

</html>

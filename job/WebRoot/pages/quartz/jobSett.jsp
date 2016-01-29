<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript">
  
    </script>
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
	</head>
	<body>
        <div region="center" style="padding:5px;" border="false">  
          <table id="dg" class="easyui-datagrid" title="数据" style="width:1200px;height:350px"  
            data-options="  
            rownumbers:true,  
            singleSelect:false,  
            url:'<%=request.getContextPath() %>/quartzTriggers/getQuartzList.json'  
            ">  
            <thead>  
                <tr>  
                    <th data-options="field:'ck',checkbox:true"></th>  
                    <th data-options="field:'triggerName',width:80">名称</th>  
                    <th data-options="field:'triggerState',width:100">状态</th>   
                    <th data-options="field:'listprice',width:80,align:'right'">任务描述</th>  
                    <th data-options="field:'nextFireTime',width:200,align:'right'">下次运行时间</th>  
                    <th data-options="field:'attr1',width:200">上次运行时间</th>  
                    <th data-options="field:'status',width:80,align:'center'">执行时间(秒)</th>  
                    <th data-options="field:'status',width:60,align:'center'">创建者</th>  
               </tr>  
            </thead>  
        </table>   
            
        </div>  
    </body>  

</html>

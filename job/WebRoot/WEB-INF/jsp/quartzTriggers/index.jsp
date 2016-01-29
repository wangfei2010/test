<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  	<head>
		<%@include file="/WEB-INF/jsp/common/head.jsp" %>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.5/jquery.form.js"></script>
	</head>
	<body>
		<script type="text/javascript">
			function formatTime(val,row){
				var time = new Date(val*1000);
				var y = time.getFullYear();
				var m = time.getMonth()+1;
				var d = time.getDate();
				var h = time.getHours();
				var mm = time.getMinutes();
				var s = time.getSeconds();
				return y+'-'+m+'-'+d+' '+h+':'+mm+':'+s;
		    }
		    function addWinOpen(){$('#addWin').window('open');}
		    function editWinOpen(){
		    	var row = $("#jobTable").datagrid('getSelected');
				if(row == null){
					alert("请选择需要修改的数据！");
					return false;
				}
				$("#editTriggerId").val(row.triggerId);
				$("#editTriggerName").val(row.triggerName);
				$("input:radio[name='editTriggerState']").attr("checked",false);
				$("input:radio[name='editTriggerState']")[row.triggerState].checked=true;
				$("#editTriggerCron").val(row.triggerCron);
				$('#editWin').window('open');
		    }
		    function submitForm(){
		    	$("#addForm").ajaxSubmit({
		    		beforeSubmit: function(){
						if( "--请选择--" == $('#jobNameSelect').combobox('getText') ||$('#jobNameSelect').combobox('getValue') == "" ){
							alert("请选择任务名称！");
							return false;
						}
						if($("#triggerCron").val() == ""){
							alert("请输入运行计划！");
							return false;
						}
						return true;
					},
					success: function(jsonObj){
						if(jsonObj.success){
							$('#addForm').form('reset');
							$('#addWin').window('close');
							$("#jobTable").datagrid('reload');
						}else{
							alert("添加定时任务失败！");
						}
					},
					error: function(){
						alert("添加定时任务失败，请联系管理员处理！");
					}
		    	});
		    }
		    function saveEdit(){
		    	$("#editForm").ajaxSubmit({
		    		beforeSubmit: function(){
						if($("#triggerCron").val() == ""){
							alert("请输入运行计划！");
							return false;
						}
						return true;
					},
					success: function(jsonObj){
						if(jsonObj.success){
							$('#editWin').window('close');
							$("#jobTable").datagrid('reload');
						}else{
							alert(jssonObj.message);
						}
					},
					error: function(){
						alert("修改定时任务失败，请联系管理员处理！")
					}
		    	});
		    }
			function clearForm(){$('#addForm').form('reset');}
			function delJob(){
				var row = $("#jobTable").datagrid('getSelected');
				if(row == null){
					alert("请选择需要删除的数据！");
					return false;
				}
				if(confirm("确认删除该任务么？")){
					$.ajax({
						type: "post",
						url: "${pageContext.request.contextPath }/quartzTriggers/deleteQuartzTriggers.json",
						data: "triggerId=" + row.triggerId,
						success: function(jsonObj){
							if(jsonObj.success){$("#jobTable").datagrid('reload');}else{alert("删除失败！");}
						},
						error: function(){alert("删除失败，请联系管理员！");}
					});
				}
			}
			function reloadJob(){
				if(confirm("【警告】确认需要重新部署所有任务么？")){
					$.ajax({
						type: "get",
						url: "${pageContext.request.contextPath }/quartzTriggers/reflushJobs.json",
						cache: false,
						success: function(jsonObj){
							if(jsonObj.success){alert("部署完成！");}else{alert("重新部署失败！");}
						},
						error: function(){alert("部署失败，请联系管理员！");}
					});
				}
			}
			function reloadOneJob(){
				var row = $("#jobTable").datagrid('getSelected');
				if(row == null){
					alert("请选择需要部署的任务！");
					return false;
				}
				if(confirm("确认部署选中任务？")){
					$.ajax({
						type: "post",
						url: "${pageContext.request.contextPath }/quartzTriggers/reloadJob.json",
						data: "triggerId=" + row.triggerId,
						success: function(jsonObj){
							if(jsonObj.success){alert("部署完成！");}else{alert("重新部署失败！");}
						},
						error: function(){alert("部署失败，请联系管理员！");}
					});
				}
			}
		</script>
		<table id="jobTable" class="easyui-datagrid" fit="true" data-options="singleSelect:true,rownumbers:true,toolbar:'#tb',pagination:true,fitColumns:true,
			method:'get',url:'${pageContext.request.contextPath }/quartzTriggers/queryJobsByPage.json'">
		<thead>
		<tr>
			<th data-options="field:'triggerId',checkbox:true"></th>
			<th data-options="field:'triggerName',align:'center',fit:true,width:250">任务名称</th>
			<th data-options="field:'triggerGroup',align:'center',width:150">任务分组</th>
			<th data-options="field:'nextFireTime',align:'center',width:200,fit:true" formatter="formatTime">上一次执行时间</th>
			<th data-options="field:'triggerState',width:80,align:'center'">启用状态</th>
			<th data-options="field:'triggerCron',align:'center',width:150,fit:true">执行时间</th>
		</tr>
		</thead>
		</table>
		<div id="tb" style="padding:5px;height:auto">
			<div align="right" style="margin-bottom:5px;margin-right: 25px">
				<table>
					<tr>
						<td><a class="easyui-linkbutton" onclick="addWinOpen()" iconCls="icon-add" plain="true"  href="javascript:void(0)">新建任务</a></td>
						<td><a class="easyui-linkbutton" onclick="delJob()" iconCls="icon-remove" plain="true" href="javascript:void(0)">删除任务</a></td>
						<td><a class="easyui-linkbutton" onclick="editWinOpen()" iconCls="icon-edit" plain="true" href="javascript:void(0)">编辑任务</a></td>
						<td><a class="easyui-linkbutton" onclick="reloadOneJob()" iconCls="icon-redo" plain="true" href="javascript:void(0)">部署任务</a></td>
						<td><a class="easyui-linkbutton" onclick="reloadJob()" iconCls="icon-reload" plain="true" href="javascript:void(0)">重新部署所有任务</a></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="addWin" class="easyui-window" title="新增Job" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:310px;height:230px;padding:10px;">
			<div><form id="addForm" method="post" action="${pageContext.request.contextPath }/quartzTriggers/saveQuartzTriggers.json"><table>
				<tr><td>任务名称：</td><td>
					<select id="jobNameSelect" class="easyui-combobox" name="triggerName" style="width: 200px" data-options="   
				        valueField: 'jobName',
				        textField: 'jobName',
				        url:'${pageContext.request.contextPath }/quartzTriggers/getJobName.json'" >
				        <option>--请选择--</option>
					</select>
				</td></tr> 
				<tr><td>任务分组：</td><td><input id="triggerGroup" name="triggerGroup" class="easyui-validatebox" type="text" disabled="disabled" style="width: 200px" value="暂不支持"/></td></tr>
				<tr><td>是否启用：</td><td>停用<input name="triggerState" type="radio" value="0" />&nbsp;&nbsp;&nbsp;&nbsp;启用<input name="triggerState" type="radio" checked="checked" value="1"/></td></tr>
				<tr><td>运行计划：</td><td><input id="triggerCron" name="triggerCron" class="easyui-validatebox" data-options="required:true"  type="text" style="width: 200px" value="0 0/10 * * * ?"/></td></tr>
			</table></form></div>
			<div style="text-align:center;padding:0px">
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	    	</div>
	    </div>
	    <div id="editWin" class="easyui-window" title="编辑Job" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:310px;height:230px;padding:10px;">
			<div><form id="editForm" method="post" action="${pageContext.request.contextPath }/quartzTriggers/editQuartzTriggers.json"><table>
				<input id="editTriggerId" name="triggerId" type="hidden" />
				<tr><td>任务名称：</td><td><input id="editTriggerName" name="triggerName" class="easyui-validatebox" type="text" disabled="disabled" style="width: 200px"/></td></tr>
				<tr><td>任务分组：</td><td><input id="editTriggerGroup" name="triggerGroup" class="easyui-validatebox" type="text" disabled="disabled" style="width: 200px" value="暂不支持"/></td></tr>
				<tr><td>是否启用：</td><td>停用<input name="editTriggerState" type="radio" value="0" />&nbsp;&nbsp;&nbsp;&nbsp;启用<input name="editTriggerState" type="radio" value="1"/></td></tr>
				<tr><td>运行计划：</td><td><input id="editTriggerCron" name="triggerCron" class="easyui-validatebox" data-options="required:true"  type="text" style="width: 200px"/></td></tr>
			</table></form></div>
			<div style="text-align:center;padding:0px">
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveEdit()">修改</a>
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	    	</div>
	    </div>
		</div>
	</body>
</html>
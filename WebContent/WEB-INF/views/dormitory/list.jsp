<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>宿舍</title>
</head>
<body>
	<table id="dg" class="" title="列表" style="width: 700px; height: 300px">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'name',width:80">宿舍名称</th>
				<th  data-options="field:'username',width:80 " >宿管</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton add" iconCls="icon-add" onclick="create()" plain="true">新增</a> 
		<a href="#" class="easyui-linkbutton edit" iconCls="icon-edit" onclick="edit()" plain="true">修改</a> 
		<a href="#" class="easyui-linkbutton remove" iconCls="icon-remove" onclick="del()" plain="true">删除</a>
		<div>
			宿舍楼名称: <input class="easyui-textbox" id="paramname">
			<a href="#" class="easyui-linkbutton" onclick="doSearch()" iconCls="icon-search">查找</a>
			<a href="#" class="easyui-linkbutton" onclick="doReset()" iconCls="icon-redo">重置</a>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog" title="数据参数" data-options="modal:true" style="width: 400px; height: 350px;" closed="true" buttons="#dlg-buttons">
		<form method="post" id="fm">
			<table cellpadding="5">
				<tr>
					<td><input type="hidden" name="dormitoryid" /></td>
				</tr>
	    		<tr>
	    			<td>宿舍名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="name" maxlength="30" required="true" missingMessage="名称必须填写"></input></td>
	    		</tr>
	    			<tr>
	    			<td>宿管:</td>
	    			<td>
	    				<input name="teacherid"  id ="dlgg-username" class="easyui-combobox" data-options="valueField:'tid',textField:'username',url:'../teach/getall',prompt:'请选择宿管,required:true,editable:false'"/>
					</td>
	    		</tr>
	    	</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton  save" onclick="save()" iconCls="icon-ok">保存</a> 
		<a href="#" class="easyui-linkbutton cancel" onclick="javascript:$('#dlg').dialog('close')" iconCls="icon-cancel">取消</a>
	</div>
	<script type="text/javascript">
	$(function() {  
		loadDataGrid();
    });  	
	function loadDataGrid(queryConditon){
		 $('#dg').datagrid({  
	            title : '宿舍信息',  
	            iconCls : 'icon-ok',  
	            pageSize : 5,
	            pageList : [ 5, 10, 15, 20 ],
	            nowrap : true,
	            striped : true,
	            collapsible : true,  
	            toolbar:"#toolbar", 
	            url:'../../admin/dormitory/list',
	            loadMsg : '数据装载中......',  
	            singleSelect:false,
	            fitColumns:true,  
	            checkOnSelect:true,
	            selectOnCheck:true,
	            fit:true,
	            remoteSort : false,  
	            pagination : true,  
	            rownumbers : true,
	            queryParams:queryConditon
	        });   
	}
	
		var url;
		function create(){
			$('#dlg').dialog('open').dialog('setTitle','新建');
			$('#fm').form('clear');
			url = '../../admin/dormitory/add';
		}
		function edit(){
			var row = $('#dg').datagrid('getSelections');
			if (row.length == 1){
				$('#dlg').dialog('open').dialog('setTitle','编辑');
				$('#fm').form('clear');
				$('#fm').form('load',row[0]);
				url = '../../admin/dormitory/edit';
			}else{
				$.messager.show({
					title: '提示信息',
					msg: '请选择一行数据再进行编辑！'
				});
			}
		}
		
		function save(){
			var data = $("#fm").serialize();
			$('#fm').form('submit',{
				url: url,
				data:data,
				onSubmit: function(){
					return $(this).form('validate');
				},
				success: function(result){
					var result = eval('('+result+')');
					if (result.success){
						$('#dlg').dialog('close');		
						$('#dg').datagrid('reload');	
						$.messager.show({
							title: '提示信息',
							msg: result.msg
						});
					} else {
						$.messager.show({
							title: '提示信息',
							msg: result.msg
						});
					}
				}
			});
		}
		
		function doSearch(){
			var queryCondition = {'name':$('#paramname').val()};
			loadDataGrid(queryCondition);
		}
		function doReset(){
			$('#paramname').textbox('setValue','');
			/* $('#paramAdmin').textbox('setValue','');
			$('#paramTotal').textbox('setValue',''); */
			loadDataGrid(null);
		}
		function del(){
			var rows = $('#dg').datagrid('getSelections');
			var ids = [];
			if (rows.length > 0){
				$.messager.confirm('提示信息','确定删除?',function(r){
					if (r){
						debugger;
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].dormitoryid);
						}
						$.ajax({
							url : '../../admin/dormitory/delete',
							type : 'post',
							data : {
								ids : ids.join(',')
							},
							cache : false,
							success : function(result) {
								if (result.success){
									$('#dg').datagrid('reload');
									$.messager.show({	
										title: '提示信息',
										msg: result.msg
									});
								} else {
									$.messager.show({	
										title: '提示信息',
										msg: result.msg
									});
								}
							}
						});
					}
				});
			}else{
				$.messager.show({	
					title: '提示信息',
					msg: '请至少选择一条数据后再进行删除'
				});
			}
		}
	/* function p(value,row,index){
				if(value==null){
					return "全体";
				}else{
					return value;
				}
			} */
	</script>
	
</body>
</html>
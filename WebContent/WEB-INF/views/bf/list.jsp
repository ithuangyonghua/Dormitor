<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>报废</title>
</head>
<body>
	<table id="dg" class="" title="列表" style="width: 700px; height: 300px">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th  data-options="field:'name',width:80 ">宿舍</th>
				<th data-options="field:'assetname',width:80">财产名称</th>
				<th  data-options="field:'scraptime',width:80 "  formatter="changeDate"> 报废时间</th>
				<th  data-options="field:'scrapto',width:80 " >报废去向</th>
				<th  data-options="field:'scrapreason',width:80 " >报废事由</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton add" iconCls="icon-add" onclick="create()" plain="true">新增</a> 
		<a href="#" class="easyui-linkbutton edit" iconCls="icon-edit" onclick="edit()" plain="true">修改</a> 
		<a href="#" class="easyui-linkbutton remove" iconCls="icon-remove" onclick="del()" plain="true">删除</a>
	</div>
	<div id="dlg" class="easyui-dialog" title="数据参数" data-options="modal:true" style="width: 400px; height: 350px;" closed="true" buttons="#dlg-buttons">
		<form method="post" id="fm">
			<table cellpadding="5">
	    		<tr>
	    			<td>宿舍:</td>
	    			<td>
	    			<input   name="bedchamberid"  id ="dlgg-bedchamberid" class="easyui-combobox" data-options="valueField:'dormitoryid',textField:'dormitoryname',url:'../teach/dorgetall',prompt:'请选择宿舍,required:true,editable:false'"/>
					</td>
	    		<tr>
	    			<td>财产:</td>
	    			<td>
	    			<input  name="assetid"  id ="dlgg-assetid" class="easyui-combobox" /> 
					</td>
	    		 </tr>
	    		   
	    		 <tr>
	    			<td>报废时间:</td>
	    			<td><input class="easyui-datetimebox"  id="dlgg-scraptime" editable="false" type="text" name="scraptime" formatter="changeDate"  ></input></td>
	    		</tr> 
	    		<tr>
	    			<td>报废去向:</td>
	    			<td><input class="easyui-textbox" type="text" id ="dlgg-scrapto" name="scrapto" maxlength="30" required="true" missingMessage="价格必须填写"></input></td>
	    		</tr> 
	    		<tr>
	    		   <td>报废事由:</td>
	    		   <td><input  name="scrapreason"  id ="dlgg-scrapreason"  class="easyui-textbox" data-options="multiline:true" style="height:100px;width:200px"> </input></td>
	    		</tr>
	    	</table>
		</form>
	</div>
	
	<!--  -->
	<div id="dlg1" class="easyui-dialog" title="数据参数" data-options="modal:true" style="width: 400px; height: 350px;" closed="true" buttons="#dlg-buttons1">
		<form method="post" id="fm1">
			<table cellpadding="5">
			    <input type="hidden" name="assetid" id="edit-assetid"/>
	    		<!-- <tr>
	    			<td>宿舍:</td>
	    			<td>
	    			<input   name="bedchamberid"  id ="dlgg-bedchamberid" class="easyui-combobox" data-options="valueField:'dormitoryid',textField:'dormitoryname',url:'../teach/dorgetall',prompt:'请选择宿舍,required:true,editable:false'"/>
					</td>
	    		<tr>
	    			<td>财产:</td>
	    			<td>
	    			<input  name="assetid"  id ="dlgg-assetid" class="easyui-combobox" /> 
					</td>
	    		 </tr> -->
	    		   
	    		 <tr>
	    			<td>报废时间:</td>
	    			<td><input class="easyui-datetimebox"  id="edit-scraptime" editable="false" type="text" name="scraptime" formatter="changeDate"  ></input></td>
	    		</tr> 
	    		<tr>
	    			<td>报废去向:</td>
	    			<td><input class="easyui-textbox" type="text" id ="edit-scrapto" name="scrapto"  data-options="multiline:true" style="height:100px;width:200px"></input></td>
	    		</tr> 
	    		<tr>
	    		   <td>报废事由:</td>
	    		   <td><input class="easyui-textbox" name="scrapreason"  id ="edit-scrapreason"  data-options="multiline:true" style="height:100px;width:200px"> </input></td>
	    		</tr>
	    	</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton  save" onclick="save()" iconCls="icon-ok">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton cance" onclick="javascript:$('#dlg').dialog('close')" iconCls="icon-cancel">取消</a>
	</div>
	<div id="dlg-buttons1">
		<a href="javascript:void(0)" class="easyui-linkbutton  save" onclick="save1()" iconCls="icon-ok">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton cancel" onclick="javascript:$('#dlg1').dialog('close')" iconCls="icon-cancel">取消</a>
	</div>
	<script type="text/javascript">
	$(function() {  
		loadDataGrid();
	     $('#dlgg-bedchamberid').combobox({
       	  onSelect:function(){
       		  $('#dlgg-assetid').combobox({
	                    disabled:false,
	                    url:'../teach/getassetall/'+$('#dlgg-bedchamberid').combobox("getValue"),
	                    editable:false, //不可编辑状态
	    			    cache: false,
	                    valueField:'assetid',//id
	                    textField:'assetname',//power */
	                })
	                
       	  }
         })   
    });  
	function loadDataGrid(queryConditon){
		 $('#dg').datagrid({  
	            title : '报废信息',  
	            iconCls : 'icon-ok',  
	            pageSize : 5,
	            pageList : [ 5, 10, 15, 20 ],
	            nowrap : true,
	            striped : true,
	            collapsible : true,  
	            toolbar:"#toolbar", 
	            url:'../../admin/bf/list',
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
			url = '../../admin/bf/add';
		}
		function edit(){
			var row = $('#dg').datagrid('getSelections');
			if (row.length == 1){
				$('#dlg1').dialog('open').dialog('setTitle','编辑');
				$('#fm1').form('clear');
				$('#edit-assetid').val(row[0].assetid);//id
				$('#edit-scraptime').datetimebox('setValue',changeDate(row[0].scraptime));//报废时间
				$('#edit-scrapto').val(row[0].scrapto);
				$('#edit-scrapreason').val(row[0].scrapreason);
				url = '../../admin/bf/edit';
			}else{
				$.messager.show({
					title: '提示信息',
					msg: '请选择一行数据再进行编辑！'
				});
			}
		}
		function save1(){
			var data = $("#fm1").serialize();
			console.log(url);
			$('#fm1').form('submit',{
				url: url,
				data:data,
				 onSubmit: function(){
					return $(this).form('validate');
				}, 
				success: function(result){
					 var result = eval('('+result+')');
					if (result.success){
						$('#dlg1').dialog('close');		
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
	/* 	function edit(){
			var row = $('#dg').datagrid('getSelections');
			debugger;
			if (row.length == 1){
				$('#dlg').dialog('open').dialog('setTitle','编辑');
				$('#fm').form('clear');
				//$('#fm').form('load',row[0]);
				//$('#dlgg-assetname').textbox('setValue',row[0].assetname);
				console.log(row[0]);
				$('#dlgg-assetid').val(row[0].assetid);//id
				$('#dlgg-assetname').val(row[0].assetname);//财产名
				$('#dlgg-price').val(row[0].price);//价格
				$('#dlgg-createtime').datetimebox('setValue',changeDate(row[0].createtime));//创建时间
				$('#dlgg-bedchamberid').combobox('setValue',row[0].bedchamberid);//宿舍
				$('#dlgg-uses').combobox('setValue',row[0].uses);//使用者
				//$('#assetname').val(row[0].assetname);
				url = '../../admin/bf/edit';
			}else{
				$.messager.show({
					title: '提示信息',
					msg: '请选择一行数据再进行编辑！'
				});
			}
		} */
	
		function save(){
			var data = $("#fm").serialize();
			console.log(url);
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
			var queryCondition = {'dormname':$('#paramDormName').val(),'dormadmin': $('#paramAdmin').val(),'total': $('#paramTotal').val()};
			loadDataGrid(queryCondition);
		}
		function doReset(){
			$('#paramDormName').textbox('setValue','');
			$('#paramAdmin').textbox('setValue','');
			$('#paramTotal').textbox('setValue','');
			loadDataGrid(null);
		}
		function del(){
			var rows = $('#dg').datagrid('getSelections');
			debugger;
			var ids = [];
			if (rows.length > 0){
				$.messager.confirm('提示信息','确定删除?',function(r){
					if (r){
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].assetid);
						}
						$.ajax({
							url : '../../admin/bf/delete',
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
		function changeDate(value, row, index){
			if(value == "" || value == null){
				return "";
			}else{
				var dateChange = new Date(value);
		    	return dateChange.Format('yyyy-MM-dd');
			}
			
		}
		function changeDateTime(value, row, index){
			if(value == "" || value == null){
				return "";
			}else{
				var dateChange = new Date(value);
		    	return dateChange.Format('yyyy-MM-dd hh:mm:ss');
			}
			
		}
			
	</script>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>盘点</title>
</head>
<body>
	<table id="dg" class="" title="列表" style="width: 700px; height: 300px">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th  data-options="field:'name',width:80 ">宿舍</th>
				  <th data-options="field:'rstatus',width:80" formatter="myTest">状态</th> 
				 <th  data-options="field:'assetname',width:80 " >财产名称</th> 
				  <th  data-options="field:'username',width:80 " >盘点人</th>
				  <th  data-options="field:'rcreatetime',width:80 " formatter="changeDate">登记时间</th> 
			</tr>
		</thead>
	</table>
	
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton add" iconCls="icon-add" onclick="create()" plain="true">新增</a> 
		<a href="#" class="easyui-linkbutton edit" iconCls="icon-edit" onclick="edit()" plain="true">修改</a> 
		<a href="#" class="easyui-linkbutton remove" iconCls="icon-remove" onclick="del()" plain="true">删除</a>
		<div>
		宿舍: <input class="easyui-textbox" id="paramname">
		盘点人: <input class="easyui-textbox" id="paramusername">
		<a href="#" class="easyui-linkbutton" onclick="doSearch()" iconCls="icon-search">查找</a>
			<a href="#" class="easyui-linkbutton" onclick="doReset()" iconCls="icon-redo">重置</a>
			</div>
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
	    		   <td>设施状态:</td>
		           <td> <input  name="rstatus"  id ="dlgg-rstatus" class="easyui-combobox"  data-options="valueField: 'value',textField: 'label',data: [{label: '完好',value: '1',selected:true},{label: '损坏',value: '0'},]" /></td>
	    		</tr>
	    		   <tr>
	    			<td>登记时间:</td>
	    			<td><input class="easyui-datetimebox"  id="dlgg-rcreatetime" editable="false" type="text" name="rcreatetime" formatter="changeDate"  ></input></td>
	    	     </tr>
	    	</table>
		</form>
	</div> 
	<!-- 修改 -->
	 <div id="dlg1" class="easyui-dialog" title="数据参数" data-options="modal:true" style="width: 400px; height: 350px;" closed="true" buttons="#dlg-buttons1">
		<form method="post" id="fm1">
			<table cellpadding="5">
				 <tr>
					<td><input type="hidden" name="rid" id="edit-rid" /></td>
				</tr> 
	    		 </tr>
	    		<tr>
	    		   <td>设施状态:</td>
		           <td> <input  name="rstatus"  id ="edit-rstatus" class="easyui-combobox"  data-options="valueField: 'value',textField: 'label',data: [{label: '完好',value: '1',selected:true},{label: '损坏',value: '0'},]" /></td>
	    		</tr>
	    		   <tr>
	    			<td>登记时间:</td>
	    			<td><input class="easyui-datetimebox"  id="edit-rcreatetime" editable="false" type="text" name="rcreatetime" formatter="changeDate"  ></input></td>
	    	     </tr>
	    	</table>
		</form>
	</div> 
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton  save" onclick="save()" iconCls="icon-ok">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton cancel" onclick="javascript:$('#dlg').dialog('close')" iconCls="icon-cancel">取消</a>
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
	
	 function myTest(value,row,index){
		switch(value){
		case 0: value="丢失";
		        break;
		case 1: value="在";
        break;
		}
		return value;
	} 
	function loadDataGrid(queryConditon){
		console.log(queryConditon);
		 $('#dg').datagrid({  
	            title : '财产信息',  
	            iconCls : 'icon-ok',  
	            pageSize : 5,
	            pageList : [ 5, 10, 15, 20 ],
	            nowrap : true,
	            striped : true,
	            collapsible : true,  
	            toolbar:"#toolbar", 
	            url:'../../admin/check/list',
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
			url = '../../admin/check/add';
			
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
		function edit(){
			var row = $('#dg').datagrid('getSelections');
			if (row.length == 1){
				$('#dlg1').dialog('open').dialog('setTitle','编辑');
				$('#fm1').form('clear');
				$('#edit-rid').val(row[0].rid);//rid
				$('#edit-rstatus').combobox('setValue',row[0].rstatus);//财产
				$('#edit-rcreatetime').datetimebox('setValue',changeDate(row[0].rcreatetime));//报废时间
				url = '../../admin/check/edit';
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
		function doSearch(){
			var queryCondition = {'name':$('#paramname').val(),'username': $('#paramusername').val()};
			loadDataGrid(queryCondition);
		}
		function doReset(){
			$('#paramname').textbox('setValue','');
			$('#paramusername').textbox('setValue','');
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
							ids.push(rows[i].rid);
						}
						$.ajax({
							url : '../../admin/check/delete',
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
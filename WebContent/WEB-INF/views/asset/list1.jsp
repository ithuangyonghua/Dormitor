<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>财产</title>
</head>
<body>
	<table id="dg" class="" title="列表" style="width: 700px; height: 300px">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th  data-options="field:'name',width:80 ">宿舍</th>
				<th data-options="field:'assetname',width:80">财产名称</th>
				<th  data-options="field:'price',width:80 " >价格</th>
				 <th  data-options="field:'registrationstatus',width:80 "  formatter="changMy">设施状态</th> 
				 <th  data-options="field:'username',width:80 " formatter="myTest">使用者</th>
				 <th  data-options="field:'createtime',width:80 " formatter="changeDate">购买时间</th>
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
					<td><input type="hidden" name="assetid" id="dlgg-assetid" /></td>
				</tr>
	    		<tr>
	    			<td>财产名称:</td>
	    			<td><input type="text" name="assetname"  id ="dlgg-assetname"  maxlength="30" required="true" missingMessage="名称必须填写"></input></td>
	    		</tr>
	    		<tr>
	    			<td>宿舍:</td>
	    			<td>
	    				<input   name="bedchamberid"  id ="dlgg-bedchamberid" class="easyui-combobox" data-options="valueField:'dormitoryid',textField:'dormitoryname',url:'../teach/dorgetall',prompt:'请选择宿舍,required:true,editable:false'"/>
					</td>
	    		 </tr>
	    		 <tr>
	    			<td>价格:</td>
	    			<td><input class="easyui-textbox" type="text" id ="dlgg-price" name="price" maxlength="30" required="true" missingMessage="价格必须填写"></input></td>
	    		</tr>   
	    		 <tr>
	    			<td>购买时间:</td>
	    			<td><input class="easyui-datetimebox"  id="dlgg-createtime" editable="false" type="text" name="createtime" formatter="changeDate"  ></input></td>
	    		</tr> 
	    		<tr>
	    			<td>使用者:</td>
	    			<td>
	    			<!-- <input   name="uses"  id ="dlgg-uses" class="easyui-combobox" data-options="valueField:'stuid',textField:'stuname',url:'../teach/getstuall',prompt:'请选择宿舍,required:true,editable:false'"/> -->
	    			 <input   name="uses"  id ="dlgg-uses" class="easyui-combobox"/>
	    			</td>
	    		</tr>
	    	</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton  save" onclick="save()" iconCls="icon-ok">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton cancel" onclick="javascript:$('#dlg').dialog('close')" iconCls="icon-cancel">取消</a>
	</div>
	<script type="text/javascript">
	$(function() {  
		loadDataGrid();
		  $('#dlgg-bedchamberid').combobox({
       	  onSelect:function(){
       		  $('#dlgg-uses').combobox({
	                    disabled:false,
	                    url:'../teach/getbstuall/'+$('#dlgg-bedchamberid').combobox("getValue"),
	                    editable:false, //不可编辑状态
	    			    cache: false,
	                    valueField:'stuid',//id
	                    textField:'stuname',//power 
	                })
	                
       	  }
         })   
    });  
	function myTest(value,row,index){
		if(value==null){
			return "全体";
		}
		return value;
	}
	function changMy(value,row,index){
		if(value<0){
			return "报废";
		}else{
			return "正常使用";
		}
		
	}
	function loadDataGrid(queryConditon){
		 $('#dg').datagrid({  
	            title : '财产信息',  
	            iconCls : 'icon-ok',  
	            pageSize : 5,
	            pageList : [ 5, 10, 15, 20 ],
	            nowrap : true,
	            striped : true,
	            collapsible : true,  
	            toolbar:"#toolbar", 
	            url:'../../admin/asset/list',
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
			url = '../../admin/asset/add';
		}
		function edit(){
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
				$('#dlgg-uses').combobox('setValue',row[0].username);//使用者
				//$('#assetname').val(row[0].assetname);
				url = '../../admin/asset/edit';
			}else{
				$.messager.show({
					title: '提示信息',
					msg: '请选择一行数据再进行编辑！'
				});
			}
		}
		/*function edit(){
			easyui-textbox
		var row = $('#dg').datagrid('getSelections');
		if (row.length == 1){
			$('#dlg').dialog('open').dialog('setTitle','编辑');
			$('#fm').form('clear');
			$('#starttime').datetimebox('setValue',changeDateTime(row[0].starttime));
			$('#endtime').datetimebox('setValue',changeDateTime(row[0].endtime));
			$('#studentid').val(row[0].student.id);
			$('#id').val(row[0].id);
			$('#studentname').textbox('setValue',row[0].student.name);
			$('#visitorname').textbox('setValue',row[0].visitorname);
			$('#relationship').val(row[0].relationship);
			$('#remark').val(row[0].remark);
			url = 'visitorController.do?update';
		}else{
			$.messager.show({
				title: '提示信息',
				msg: '请选择一行数据再进行编辑！'
			});
		}
	}*/
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
							url : '../../admin/asset/delete',
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
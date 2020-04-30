<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <!-- 搜索模块  -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<!-- 添加模块 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <table>
                <tr>
	    			<td>财产分类名称:</td>
	    			<td>
	    			<input   name="ctegoryid"  id ="add-ctegoryid" class="easyui-combobox" data-options="valueField:'cid',textField:'canme',url:'../assetcategory/getall',prompt:'请选择宿舍,required:true,editable:false'"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>财产名:</td>
	    			<td><input type="text" name="pname"  id ="add-pname"  maxlength="30" required="true" missingMessage="使用年限必须填写"></input></td>
	    		</tr>
           </table>
    </form>
</div>
<!-- 修改窗口 -->
<!-- 修改模块 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
         <input type="hidden" name="pid" id="edit-pid" />
        <table>
	    		<!-- <tr>
	    			<td>财产分类名称:</td>
	    			<td>
	    			<input type="text" name="cname"  id ="edit-cname"  maxlength="30" required="true" missingMessage="财产分类名称必须填写"></input>
	    			</td>
	    		</tr> -->
	    		<tr>
	    			<td>财产名:</td>
	    			<td><input type="text" name="pname"  id ="edit-pname"  maxlength="30" required="true" missingMessage="财产名必须填写"></input></td>
	    		</tr>
        </table>
        </table>
    </form>
</div>
<div id="process-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-upload',title:'正在上传图片'" style="width:450px; padding:10px;">
<div id="p" class="easyui-progressbar" style="width:400px;" data-options="text:'正在上传中...'"></div>
</div>
<input type="file" id="photo-file" style="display:none;" onchange="upload()">
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	$(function(){
	 
	    
	});
	/**
	*  添加记录
	*/
	function add(){
		//$("#add-form").form("clear");
		var validate = $("#add-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#add-form").serialize();
		$.ajax({
			url:'add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','添加成功！','info');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	/**
	* Name 修改记录
	*/
	function edit(){
		var item = $('#data-datagrid').datagrid('getSelected');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('信息提示','请选择一条数据进行修改！','info');
			return;
		}
		item = item[0];
		var validate = $("#edit-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'edit',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','修改成功！','info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
					$('#data-datagrid').datagrid('unselectAll');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	/**
	* 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				debugger
				var item = $('#data-datagrid').datagrid('getSelections');
				if(item == null || item.length == 0){
					$.messager.alert('信息提示','请选择要删除的数据！','info');
					return;
				}
				debugger
				var ids = '';
				for(var i=0;i<item.length;i++){
					ids += item[i].pid + ',';
				}
				 $.ajax({
					url:'../../admin/assetpropertylist/delete',
					dataType:'json',
					type:'post',
					data:{ids:ids},
					success:function(data){
						if(data.type == 'success'){
							$.messager.alert('信息提示','删除成功！','info');
							$('#data-datagrid').datagrid('reload');
							$('#data-datagrid').datagrid('unselectAll');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加用户信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	//$("#add-form input").val('');
            }
        });
	}
	
	/**
	* 打开修改窗口
	*/
	function openEdit(){
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('信息提示','请选择一条数据进行修改！','info');
			return;
		}
		item = item[0];
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改财产信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$('#edit-pid').val(item.pid);
            	//$('#edit-cname').val(item.cname);
            	$('#edit-pname').val(item.pname);
            	/* $('#edit-assetid').val(item.assetid);//id
				$('#edit-assetname').val(item.assetname);//财产名
				$('#edit-price').val(item.price);//价格
				$('#edit-createtime').datetimebox('setValue',changeDate(item.createtime));//创建时间
				$('#edit-bedchamberid').combobox('setValue',item.bedchamberid);//宿舍
				$('#edit-uses').combobox('setValue',item.username);//使用者 */
            }
        });
	}	
	
	
	
	
	/** 
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'list',
		rownumbers:true,
		singleSelect:false,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'cname',title:'财产类别',width:100,sortable:true},
			{ field:'pname',title:'财产名',width:100,sortable:true},
		]],
		onLoadSuccess:function(data){  
			/* $('.authority-edit').linkbutton({text:'编辑权限',plain:true,iconCls:'icon-edit'}); */  
		 }  
	});
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
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<!-- Begin of toolbar -->
	<div id="wu-toolbar">
		<div class="wu-toolbar-button">
			<%@include file="../common/menus.jsp"%>
		</div>
		<!--  <div class="wu-toolbar-search">
            <label>姓名:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>性别:</label>
            <select id="search-sex" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<option value="0">未知</option>
            	<option value="1">男</option>
            	<option value="2">女</option>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div> -->
	</div>
	<!-- End of toolbar -->
	<table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<div id="add-dialog" class="easyui-dialog"
	data-options="closed:true,iconCls:'icon-save'"
	style="width: 420px; padding: 10px;">
	<form id="add-form" method="post">
		<table>
			<tr>
				<td width="70" align="right">宿舍:</td>
				<td><input name="bedchamberid" id="add-bedchamberid"
					class="easyui-combobox"
					data-options="valueField:'dormitoryid',textField:'dormitoryname',url:'../teach/dorgetall',prompt:'请选择宿舍,required:true,editable:false'" />
				</td>
			<tr>
			<tr>
				<td width="70" align="right">报修财产:</td>
				<td><input name="assetid" id="add-assetid"
					class="easyui-combobox" /> <!-- <input   name="assetid"  id ="add-assetid" class="easyui-combobox" data-options="valueField:'assetid',textField:'assetname',url:'../teach/getassetall/-1',prompt:'请选择宿舍,required:true,editable:false'"/> -->
			</tr>
			<tr>
				<td width="60" align="right">报修描述:</td>
				<td><input name="brank" id="add-brank" class="easyui-textbox"
					data-options="multiline:true" style="height: 100px; width: 200px" />
				</td>
			</tr>
		</table>
	</form>
</div>
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog"
	data-options="closed:true,iconCls:'icon-save'"
	style="width: 450px; padding: 10px;">
	<form id="edit-form" method="post">
		<input type="hidden" name="id" id="edit-id">
		<table>
			<tr>
				<td width="60" align="right">进度:</td>
				<td><input name="status" id="edit-rstatus"
					class="easyui-combobox"
					data-options="valueField: 'value',textField: 'label',data: [{label: '正在维修',value: '1',selected:true},{label: '维护成功',value: '0'},]" />
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="process-dialog" class="easyui-dialog"
	data-options="closed:true,iconCls:'icon-upload',title:'正在上传图片'"
	style="width: 450px; padding: 10px;">
	<div id="p" class="easyui-progressbar" style="width: 400px;"
		data-options="text:'正在上传中...'"></div>
</div>
<input type="file" id="photo-file" style="display: none;"
	onchange="upload()">
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	$(function() {
		$('#add-bedchamberid').combobox(
				{
					onSelect : function() {
						$('#add-assetid').combobox(
								{
									disabled : false,
									url : '../teach/getassetall/'
											+ $('#add-bedchamberid').combobox(
													"getValue"),
									editable : false, //不可编辑状态
									cache : false,
									valueField : 'assetid',//id
									textField : 'assetname',//power */
								})

					}
				})
	});
	//上传图片
	function start() {
		var value = $('#p').progressbar('getValue');
		if (value < 100) {
			value += Math.floor(Math.random() * 10);
			$('#p').progressbar('setValue', value);
		} else {
			$('#p').progressbar('setValue', 0)
		}
	};
	function upload() {
		if ($("#photo-file").val() == '')
			return;
		var formData = new FormData();
		formData
				.append('photo', document.getElementById('photo-file').files[0]);
		$("#process-dialog").dialog('open');
		var interval = setInterval(start, 200);
		$.ajax({
			url : 'upload_photo',
			type : 'post',
			data : formData,
			contentType : false,
			processData : false,
			success : function(data) {
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				if (data.type == 'success') {
					$("#preview-photo").attr('src', data.filepath);
					$("#add-photo").val(data.filepath);
					$("#edit-preview-photo").attr('src', data.filepath);
					$("#edit-photo").val(data.filepath);
				} else {
					$.messager.alert("消息提醒", data.msg, "warning");
				}
			},
			error : function(data) {
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				$.messager.alert("消息提醒", "上传失败!", "warning");
			}
		});
	}

	function uploadPhoto() {
		$("#photo-file").click();

	}

	/**
	 *  添加记录
	 */
	function add() {
		var validate = $("#add-form").form("validate");
		if (!validate) {
			$.messager.alert("消息提醒", "请检查你输入的数据!", "warning");
			return;
		}
		var data = $("#add-form").serialize();
		debugger;
		console.log(data);
		$.ajax({
			url : 'add',
			dataType : 'json',
			type : 'post',
			data : data,
			success : function(data) {
				if (data.type == 'success') {
					$.messager.alert('信息提示', '添加成功！', 'info');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				} else {
					$.messager.alert('信息提示', data.msg, 'warning');
				}
			}
		});
	}

	/**
	 * Name 修改记录
	 */
	function edit() {
		/* var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('信息提示','请选择一条数据进行修改！','info');
			return;
		}
		item = item[0]; */
		var validate = $("#edit-form").form("validate");
		if (!validate) {
			$.messager.alert("消息提醒", "请检查你输入的数据!", "warning");
			return;
		}
		var data = $("#edit-form").serialize();
		console.log(data);
		$.ajax({
			url : 'edit',
			dataType : 'json',
			type : 'post',
			data : data,
			success : function(data) {
				if (data.type == 'success') {
					$.messager.alert('信息提示', '修改成功！', 'info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				} else {
					$.messager.alert('信息提示', data.msg, 'warning');
				}
			}
		});
	}

	/**
	 * 删除记录
	 */
	function remove() {
		$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
			if (result) {
				var item = $('#data-datagrid').datagrid('getSelections');
				if (item == null || item.length == 0) {
					$.messager.alert('信息提示', '请选择要删除的数据！', 'info');
					return;
				}
				var ids = '';
				for (var i = 0; i < item.length; i++) {
					ids += item[i].id + ',';
				}
				$.ajax({
					url : 'delete',
					dataType : 'json',
					type : 'post',
					data : {
						ids : ids
					},
					success : function(data) {
						if (data.type == 'success') {
							$.messager.alert('信息提示', '删除成功！', 'info');
							$('#data-datagrid').datagrid('reload');
						} else {
							$.messager.alert('信息提示', data.msg, 'warning');
						}
					}
				});
			}
		});
	}

	/**
	 * Name 打开添加窗口
	 */
	function openAdd() {
		//$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed : false,
			modal : true,
			title : "添加报修信息",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : add
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#add-dialog').dialog('close');
				}
			} ],
			onBeforeOpen : function() {
				//$("#add-form input").val('');
			}
		});
	}

	/**
	 * 打开修改窗口
	 */
	function openEdit() {
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelections');
		if (item == null || item.length == 0) {
			$.messager.alert('信息提示', '请选择要修改的数据！', 'info');
			return;
		}
		if (item.length > 1) {
			$.messager.alert('信息提示', '请选择一条数据进行修改！', 'info');
			return;
		}
		item = item[0];
		$('#edit-dialog').dialog({
			closed : false,
			modal : true,
			title : "修改用户信息",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : edit
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#edit-dialog').dialog('close');
				}
			} ],
			onBeforeOpen : function() {
				console.log(item);
				$("#edit-id").val(item.id);
				$("#edit-preview-photo").attr('src', item.photo);
				$("#edit-photo").val(item.photo);
				$("#edit-username").val(item.username);
				/* $("#edit-roleId").combobox('setValue',item.roleId); */
				$("#edit-sex").combobox('setValue', item.sex);
				$("#edit-age").val(item.age);
				$("#edit-dormitoryId").combobox('setValue', item.dormitoryId);
				//$('#edit-bedchamberid').combobox('setValue',item.bedchamberid);//宿舍
				/* $("#edit-address").val(item.address); */
			}
		});
	}

	//搜索按钮监听
	$("#search-btn").click(function() {
		var roleId = 4;
		var sex = $("#search-sex").combobox('getValue')
		var option = {
			username : $("#search-name").val()
		};
		if (roleId != -1) {
			option.roleId = roleId;
		}
		if (sex != -1) {
			option.sex = sex;
		}
		$('#data-datagrid').datagrid('reload', option);
	});
	function changeDate(value, row, index) {
		if (value == "" || value == null) {
			return "";
		} else {
			var dateChange = new Date(value);
			return dateChange.Format('yyyy-MM-dd');
		}

	}
	function changeDateTime(value, row, index) {
		if (value == "" || value == null) {
			return "";
		} else {
			var dateChange = new Date(value);
			return dateChange.Format('yyyy-MM-dd hh:mm:ss');
		}

	}
	function jd(value, row, index) {
		switch (value) {
		case 0: {
			return '维修成功';
		}
		case 1: {
			return '正在维修';
		}
		}
	}
	/** 
	 * 载入数据
	 */
	$('#data-datagrid').datagrid({
		url : 'list',
		rownumbers : true,
		singleSelect : false,
		pageSize : 20,
		pagination : true,
		multiSort : true,
		fitColumns : true,
		idField : 'id',
		treeField : 'name',
		fit : true,
		success : function(data) {
			debugger;
			console.log(data)
		},
		columns : [ [ {
			field : 'chk',
			checkbox : true
		}, {
			field : 'username',
			title : '报修申请人',
			width : 100,
			sortable : true
		}, {
			field : 'name',
			title : '宿舍',
			width : 100,
			sortable : true
		}, {
			field : 'assetname',
			title : '报修财产',
			width : 100,
			sortable : true
		}, {
			field : 'status',
			title : '进度',
			width : 100,
			sortable : true,
			formatter : jd
		}, {
			field : 'brank',
			title : '报修内容',
			width : 100,
			sortable : true
		}, {
			field : 'createtime',
			title : '申请时间',
			width : 100,
			sortable : true,
			formatter : changeDateTime
		},

		] ],
		onLoadSuccess : function(data) {
			/* $('.authority-edit').linkbutton({text:'编辑权限',plain:true,iconCls:'icon-edit'});   */
		}
	});
</script>
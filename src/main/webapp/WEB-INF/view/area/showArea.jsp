<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<div class="easyui-panel" title="区域管理" 	style="width: 100%; height: 70px;margin-bottom:5px">
		<input id="areaName" class="easyui-textbox" type="text" style="margin-top: 5px" />
		<a id="btn" href="javascript:addArea()" class="easyui-linkbutton"
			data-options="iconCls:'icon-save'">新增</a>
	</div>
	<div class="easyui-panel" title="数据列表" 	style="width: 100%; height:500px;">
		<table id="areaDataGrid"></table>
	</div>
	
	<div id="tb">
		<a href="javascript:deleteMachineArr()" class="easyui-linkbutton"
			data-options="iconCls:'icon-help',plain:true">删除</a>
	</div>


	<script type="text/javascript">


    //删除
    function deleteMachineArr(){
        var ids ="";
        var arr=$("#areaDataGrid").datagrid('getChecked');
        if (arr.length >0) {
            $.messager.confirm('确认对话框', '您想要删除吗？', function(r){
                $(arr).each(function(){
                    ids+=this.id+",";
                });
                if (r){
                    $.ajax({
                        url:'<%=request.getContextPath()%>/area/deleteAreaArr.do',
                        data:{'ids':ids},
                        dataType:'json',
                        type:'post',
                        success:function(data){
                            if (data.success) {
                                $.messager.alert('我的消息',data.msg,'info');
                                $("#areaDataGrid").datagrid('reload');
                            }
                        },
                        error:function(){
                            $.messager.alert('我的消息','请求失败','info');
                        }
                    });
                }
            });
        }else{
        	  $.messager.alert('我的消息',"请选择要删除的数据",'info');
        }
    }

    //添加
    function addArea(){
                    $.ajax({
                        url:"<%=request.getContextPath()%>/area/addArea.do",
                        data:{"name":$("#areaName").val()},
                        dataType:'json',
                        type:'post',
                        success:function(data){
                            if (data.success) {
                                $.messager.alert('我的消息',data.msg,'info');
                                $("#areaDataGrid").datagrid('reload');
                                $("#areaName").val("")
                            }
                        },
                        error:function(){
                            $.messager.alert('我的消息','请求失败','info');
                        }
                    })
           
    }
    var editRow = undefined;



    $(function(){
        $("#areaDataGrid").datagrid({
            url:"<%=request.getContextPath()%>/area/findAreaList.do",
				method : 'post',
				pagination : true,
				rownumbers : true,
				fit:true,
				pageNumber : 1,
				pageSize : 10,
				pageList : [ 10, 20, 30, 50 ],
				striped : true,
				rownumbers : true,
				pagination : true,
				singleSelect : true,
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#tb',
				idField : 'id',
				loadMsg : 'load...',
				sortName : 'id',
				toolbar : '#tb',
				columns : [ [ {
					field : '',
					title : 'code',
					checkbox : true,
					width : 120
				}, {
					field : 'id',
					title : 'ID',
					width : 120,
					order : 'desc'
				}, {
					field : 'name',
					title : '区域名称',
					width : 120,
					order : 'desc'
				},

				] ],
				onAfterEdit : function(index, row, changes) {
					//endEdit该方法触发此事件

					var rowStr = JSON.stringify(row); //可以将json对象转换成json对符串
					console.log(row);
					delete row.updated;
					delete row.created;
					delete row.barcode;
					delete row.updatedTime;
					delete row.createdTime;
					delete row.cid;
					row.status = parseInt(row.status);
					row.price = parseInt(row.status);
					row.num = parseInt(row.status);
					$.ajax({
						url : sys.contextPath + '/item/updateItem.do',
						type : 'POST',
						data : {
							'rowStr' : rowStr
						},
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								$("#machineDataGrid").datagrid('reload');
							}
							$.messager.alert('信息', data.msg, 'info');

						},
						error : function() {
							$.messager.alert('信息', 'ajax失败', 'error');
						}
					})
					//editRow 赋值 位undefined表示当前没有行在编辑
					editRow = undefined;
				},
				onDblClickRow : function(index, row) {
					//双击开启编辑行
					if (editRow != undefined) {
						$("#machineDataGrid").datagrid("endEdit", editRow);
					}
					if (editRow == undefined) {
						$("#machineDataGrid").datagrid("beginEdit", index);
						editRow = index;
					}
				}
			})
		})
		//将修改用户信息更新到数据库
		function updateUserInline() {
			//            关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件，在事件中调用ajax更新后台

			$("#machineDataGrid").datagrid("endEdit", editRow);
		}
	</script>

</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: zhm
  Date: 2018/1/5
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="addform">
    <div>
        <label for="name">机器名称</label>
        <input name="name" class="easyui-validatebox" type="text">
    </div>
    <div>
        <label for="adress">投放位置</label>
        <input name="adress" class="easyui-validatebox" type="text">
    </div>
    <div>
        <label for="mid">机器ID</label>
        <input name="mid" class="easyui-numberbox" type="text" data-options="min:0,precision:2">
    </div>
    <div>
        <label for="price">单个名额价格</label>
        <input name="price" class="easyui-validatebox" type="text">
    </div>
    <div>
        <label for="isUse">是否投入使用</label>
        <input name="isUse" class="easyui-validatebox" value="1" type="radio">已投入
        <input name="isUse" class="easyui-validatebox" value="2" type="radio">未投入
    </div>
    <div>
        <label for="aplces">总名额</label>
        <input name="aplces" class="easyui-validatebox" type="text">
    </div>
    <div>
        <label for="laplces">剩余名额</label>
        <input name="laplces" class="easyui-validatebox" type="text">
    </div>
    <div>
        <label for="rplces">已售名额</label>
        <input name="rplces" class="easyui-validatebox" type="text">
    </div>
</form>
<table id="machineDataGrid"></table>
<div id="tb">
    <a href="javascript:addMachine()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">新增</a>
    <a href="javascript:updateUser()" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">修改</a>
    <a href="javascript:deleteMachineArr()" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除</a>
    <a href="javascript:editUserInline()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >开始编辑</a>
    <a href="javascript:cancleEditUserInline()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >取消修改</a>
    <a href="javascript:updateUserInline()" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_green',plain:true" >点击修改</a>
    <a href="javascript:poitest()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >导出excle表格</a>
    <a href="javascript:downImportUserTemplate()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >下载导入Excel模板</a>
    <a href="javascript:updateItemStatus()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >改变状态</a>
</div>

<div id = "addDiv"></div>

<script type="text/javascript">

    function updateItemStatus(){
        var ids ="";
        var arr=$("#machineDataGrid").datagrid('getChecked');
        if (arr.length >0) {
            $.messager.confirm('确认对话框', '您想要修改吗？', function(r){
                $(arr).each(function(){
                    ids+=this.id+",";
                });
                if (r){
                    $.ajax({
                        url:'<%=request.getContextPath()%>/item/deleteItemStatusBatch.do',
                        data:{'ids':ids},
                        dataType:'json',
                        type:'post',
                        success:function(data){
                            if (data.success) {
                                $.messager.alert('我的消息',data.msg,'info');
                                $("#machineDataGrid").datagrid('reload');
                            }
                        },
                        error:function(){
                            $.messager.alert('我的消息','请求失败','info');
                        }
                    });
                }
            });
        }
    }

    $(function(){
        $('#cc').combobox({
            url:'<%=request.getContextPath()%>/itemCat/querytree.do',
            valueField:'id',
            textField:'name',
            value:'请选择',
        })
    })

    function search(){
        var title=$("#title").val().trim();
        var cid=$("#cc").val().trim();
        $("#table").datagrid('load',{
            "title":title,
            "cid":cid,
        });
    }

    //重置查询
    function reset(){
        $("#f").form('reset');
        search();
    }

    //删除
    function deleteMachineArr(){
        var ids ="";
        var arr=$("#machineDataGrid").datagrid('getChecked');
        if (arr.length >0) {
            $.messager.confirm('确认对话框', '您想要删除吗？', function(r){
                $(arr).each(function(){
                    ids+=this.id+",";
                });
                if (r){
                    $.ajax({
                        url:'<%=request.getContextPath()%>/machine/deleteMachineArr.do',
                        data:{'ids':ids},
                        dataType:'json',
                        type:'post',
                        success:function(data){
                            if (data.success) {
                                $.messager.alert('我的消息',data.msg,'info');
                                $("#machineDataGrid").datagrid('reload');
                            }
                        },
                        error:function(){
                            $.messager.alert('我的消息','请求失败','info');
                        }
                    });
                }
            });
        }
    }

    //添加
    function addMachine(){
        $("#addDiv").dialog({
            title: '添加商品',
            width: 600,
            height: 300,
            closed: false,
            cache: false,
            href: '<%=request.getContextPath()%>/machine/toadd.do',
            modal: true,
            buttons:[{
                text:'保存',
                handler:function(){
                    $.ajax({
                        url:"<%=request.getContextPath()%>/machine/addMachine.do",
                        data:$("#addform").serialize(),
                        dataType:'json',
                        type:'post',
                        success:function(data){
                            if (data.success) {
                                $.messager.alert('我的消息',data.msg,'info');
                                $("#addDiv").dialog('close');
                                $("#machineDataGrid").datagrid('reload');
                            }
                            $.messager.alert('我的消息',data.msg,'info');
                        },
                        error:function(){
                            $.messager.alert('我的消息','请求失败','info');
                        }
                    })
                }
            },{
                text:'关闭',
                handler:function(){
                    $("#addDiv").dialog('close');

                }
            }]
        });
    }
    var editRow = undefined;



    $(function(){
        $("#machineDataGrid").datagrid({
            url:"<%=request.getContextPath()%>/item/queryItemList.do",
            method:'post',
            pagination:true,
            rownumbers:true,
            pageNumber:1,
            pageSize:2,
            pageList:[2,4,6,8],
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            checkOnSelect:false,
            selectOnCheck:false,
            toolbar:'#tb',
            idField : 'id',
            loadMsg:'等着。。。',
            sortName:'id',
            toolbar: '#tb',
            columns:[
                [
                    {field:'',title:'code',checkbox:true,width:120},
                    {field:'id',title:'ID',width:120,order:'desc'},
                    {field:'adress',title:'投放位置',width:120,editor:'text'},
                    {field:'mid',title:'机器ID',width:120,editor:'text'},
                    {field:'price',title:'单个名额价格',width:120,editor:'text'},
                    {field:'aplces',title:'总名额',width:120,editor:'text'},
                    {field:'laplces',title:'剩余名额',width:120},
                    {field:'rplces',title:'已售名额',width:120},
                    {field:'isUse',title:'是否投入使用',width:120,
                        editor: {
                            type: 'combobox',
                            options: {
                                valueField: 'id',
                                textField: 'value',
                                data: [{
                                    id: '1',
                                    value: '已投入'
                                },{
                                    id: '2',
                                    value: '未投入'
                                }],

                                required: true,
                            }
                        },
                        formatter:function(value,row,index){
                            if (value==1) {
                                return "已投入";
                            }else if (value ==2) {
                                return "未投入";
                            }
                        }
                    },
                ]
            ],
            onAfterEdit: function (index, row, changes) {
                //endEdit该方法触发此事件


                var rowStr = JSON.stringify(row); //可以将json对象转换成json对符串
                console.log(row);
                delete  row.updated;
                delete  row.created;
                delete  row.barcode;
                delete  row.updatedTime;
                delete  row.createdTime;
                delete  row.cid;
                row.status=parseInt(row.status);
                row.price=parseInt(row.status);
                row.num=parseInt(row.status);
                $.ajax({
                    url: sys.contextPath + '/item/updateItem.do',
                    type: 'POST',
                    data: {'rowStr':rowStr},
                    dataType: 'json',
                    success: function (data) {
                        if(data.success){
                            $("#machineDataGrid").datagrid('reload');
                        }
                        $.messager.alert('信息', data.msg, 'info');

                    },
                    error: function () {
                        $.messager.alert('信息', 'ajax失败', 'error');
                    }
                })
                //editRow 赋值 位undefined表示当前没有行在编辑
                editRow = undefined;
            },
            onDblClickRow: function (index, row) {
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


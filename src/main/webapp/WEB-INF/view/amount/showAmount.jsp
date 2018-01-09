<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp" %>
<html>
<head>
    <title>账户管理</title>
</head>
<body>
<form id="addform">
    <div>
        <label for="name">机器名称</label>
        <input name="name" class="easyui-validatebox" type="text">
    </div>
</form>
<table id="maintable"></table>
<div id="tb">
    <a href="javascript:addMachine()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">新增</a>
    <a href="javascript:updateUser()" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">修改</a>
</div>



<script type="text/javascript">

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


    $(function(){
        $("#machineDataGrid").datagrid({
            url:"<%=request.getContextPath()%>/amount/queryAmount.do",
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
            loadMsg:'load...',
            sortName:'id',
            toolbar: '#tb',
            columns:[
                [
                    {field:'',title:'code',checkbox:true,width:120},
                    {field:'id',title:'ID',width:120,order:'desc'},
                    {field:'money',title:'账户余额',width:120,editor:'text'},
                    {field:'uid',title:'用户',width:120,editor:'text'},
                    {field:'ken',title:'咖啡余额',width:120,editor:'text'},
                    {field:'dlen',title:'代理余额',width:120,editor:'text'},
                ]
            ],
           
        
        })
    })
    
</script>

</body>
</html>


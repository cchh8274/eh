<%--
  Created by IntelliJ IDEA.
  User: zhm
  Date: 2018/1/5
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script type="text/javascript">

</script>
</body>
</html>


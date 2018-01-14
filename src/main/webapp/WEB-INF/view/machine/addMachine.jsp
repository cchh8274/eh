<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Title</title>
</head>
<body>
	<form id="addformMerche">
		<table>
			<tr>
				<td  align="right">机器名称:</td>
				<td><input name="name" class="easyui-textbox" type="text" /></td>
			</tr>
			<tr>
				<td align="right">投放位置:</td>
				<td><input id="adressCCC" name="adress" class="easyui-combobox" type="text"/></td>
			</tr>
			<tr>
				<td align="right">机器ID:</td>
				<td><input name="mid" class="easyui-textbox" type="text"/></td>
			</tr>
			<tr>
				<td align="right">单个名额价格:</td>
				<td><input name="price" class="easyui-textbox" type="text"/></td>
			</tr>
			<tr>
				<td align="right">是否投入使用:</td>
				<td><input name="isuse" class="easyui-textbox" value="1"
					type="radio"/>已投入 <input name="isuse" class="easyui-textbox"
					value="2" type="radio"/>未投入</td>
			</tr>
			<tr>
				<td align="right">总名额:</td>
				<td><input name="aplces" class="easyui-textbox" type="text"/></td>
			</tr>
			<tr>
				<td align="right">剩余名额 :</td>
				<td><input name="laplces" class="easyui-textbox" type="text"/></td>
			</tr>
			<tr>
				<td align="right">已售名额:</td>
				<td><input name="rplces" class="easyui-textbox" type="text"/></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
	$(function(){
		$('#adressCCC').combobox({
		    url:'<%=request.getContextPath()%>/machine/selectUnit.do',
		    valueField:'id',
		    textField:'name'
		});
	})
	
	</script>
</body>
</html>


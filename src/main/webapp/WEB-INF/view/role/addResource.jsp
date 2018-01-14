<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="easyui-panel">
		<form id="addResource" method="post">
			<table border="0" align="center">
				<tr>
					<td align="right">编号:</td>
					<td><input id="id" name="id" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<td align="right">资源名称:</td>
					<td><input id="name" name="name" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<td align="right">资源类型:</td>
					<td><input id="resourcetypeId" name="resourcetypeId"
						class="easyui-combobox"
						data-options="valueField:'id',textField:'name'" /></td>

				</tr>
				<tr>
					<td align="right">资源路径:</td>
					<td><input id="url" name="url" class="easyui-textbox"
						value="0" /></td>
				</tr>
				<tr>
					<td align="right">叶子节点:</td>
					<td><input name="leafNode" class="easyui-textbox" value="1"
						type="radio" />是 <input name="leafNode" class="easyui-textbox"
						value="0" type="radio" />否</td>
				</tr>
				<tr>
					<td align="right">资源描述:</td>
					<!-- multiline:true 多行文本框  -->
					<td><input id="" name="description" class="easyui-textbox"
						/></td>
				</tr>
				<tr>
					<td align="right">顺序:</td>
					<td><input id="" name="seq" class="easyui-numberspinner"
						data-options="min:0,max:100000,editable:false" /></td>
				</tr>
				<tr>
					<td align="right">上级资源:</td>
					<td><input id="pid" name="pid" class="easyui-combotree"
						data-options="" /></td>
				</tr>
				<tr>
					<td align="right">资源图标:</td>
					<td><input id="" name="iconCls" class="easyui-textbox"
						data-options="" /></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			//自定义下拉框
			$("#cc").combo({
				editable : false,
				required : true,
			});
			//自定义下拉框 绑定 内容
			$('#sp').appendTo($('#cc').combo('panel'));

			$("input[name='leaf']").click(
					function() {
						//获取被选中的 单选的按钮的 value  以及 span的内容
						var text = $(this).next().text();
						var value = $(this).val();
						//将 value 与 text 设置 回显到 combo框  并且 隐藏面板  sp-div
						$("#cc").combo('setText', text)
								.combo('setValue', value).combo('hidePanel');
					});

			$("#resourcetypeId").combobox({
			// 				url:sys.contextPath+"/resource/toshow.do"
			})

			$('#pid').combotree({
				url : sys.contextPath + '/resource/getResourceTree.do',
				required : true,
				method : 'post',
			});

		})
	</script>
</body>
</html>
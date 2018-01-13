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
			<table border="0">
				<tr>
					<td>编号</td>
					<td><input id="id" name="id" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<td>资源名称</td>
					<td><input id="name" name="name" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<td>资源类型</td>
					<td><input id="resourcetypeId" name="resourcetypeId"
						class="easyui-combobox"
						data-options="valueField:'id',textField:'name'" /></td>

				</tr>
				<tr>
					<td>资源路径</td>
					<td><input id="url" name="url" class="easyui-validatebox"
						value="0" /></td>
				</tr>
				<tr>
					<td>叶子节点</td>
					<td><select id="cc" name="leafNode" style="width: 150px"></select>
						<div id="sp">
							<div style="color: #99BBE8; background: #fafafa; padding: 5px;">必须选择一个</div>
							<div style="padding: 10px">
								<input type="radio" name="leaf" value="1"><span>是</span><br />
								<input type="radio" name="leaf" value="0"><span>否</span>
							</div>
						</div></td>
				</tr>
				<tr>
					<td>资源描述</td>
					<!-- multiline:true 多行文本框  -->
					<td><input id="" name="description" class="easyui-validatebox"
						data-options="multiline:true" /></td>
				</tr>
				<tr>
					<td>顺序</td>
					<td><input id="" name="seq" class="easyui-numberspinner"
						data-options="min:0,max:100000,editable:false" /></td>
				</tr>
				<tr>
					<td>上级资源</td>
					<td><input id="pid" name="pid" class="easyui-combotree"
						data-options="" /></td>
				</tr>
				<tr>
					<td>资源图标</td>
					<td><input id="" name="iconCls" class="easyui-validatebox"
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
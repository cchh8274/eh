<div id="passwordDialog" title="修改密码" style="display: none;">
	<form method="post" class="easyui-form">
		<table class="table">
			<tr>
				<th>旧密码</th>
				<td><input id="oldpwd" name="oldPwd" type="password"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th>新密码</th>
				<td><input id="newpwd" name="newPwd" type="password"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th>重复密码</th>
				<td><input type="password" class="easyui-validatebox"
					data-options="required:true,validType:'equals[\'#newpwd\']'" /></td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	$(function() {
		//自定义验证规则----两次密码必须一致
		$.extend($.fn.validatebox.defaults.rules, {
			equals : {
				validator : function(value, param) {
					return value == $(param[0]).val();
				},
				message : '两次密码输入不一致'
			}
		});
	});
</script>

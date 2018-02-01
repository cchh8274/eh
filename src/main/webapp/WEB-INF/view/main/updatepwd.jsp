<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div id="passwordDialog" title="修改密码" style="display: none;">
		<form  method="post" class="easyui-form">
			<table class="table">
				<tr>
					<th>旧密码</th>
					<td><input id="oldpwd" name="oldPwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>新密码</th>
					<td><input id="newpwd" name="newPwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>重复密码</th>
					<td><input type="password" class="easyui-validatebox" data-options="required:true,validType:'equals[\'#newpwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
    
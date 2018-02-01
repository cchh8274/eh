<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/common/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>易创吧后台管理系统</title>
</head>
<body id="mainLayout" class="easyui-layout">
	<div data-options="region:'north',href:'<%=request.getContextPath()%>/main/north.do'" style="height: 70px; overflow: hidden;" class="logo"></div>
	<div data-options="region:'west',href:'',split:true" title="导航" style="width: 200px; padding: 10px;">
		<ul id="mainMenu"></ul>
	</div>
	<div data-options="region:'center'" style="overflow: hidden;">
		<div id="mainTabs">
			<div title="首页" data-options="iconCls:'icon-filter'">
				<iframe src="<%=request.getContextPath()%>/main/home.do"
					allowTransparency="true"
					style="border: 0; width: 100%; height: 99%;" frameBorder="0"></iframe>
			</div>
		</div>
	</div>
	<div
		data-options="region:'south',href:'<%=request.getContextPath()%>/main/south.do',border:false"
		style="height: 30px; overflow: hidden;"></div>
<%@include file="updatepwd.jsp"%>
<script type="text/javascript" charset="utf-8">

	//tree菜单
	var mainMenu;
	var mainTabs;
	$(function(){
		/*
		*页面加载 渲染左侧 tree菜单
		*/
		mainMenu = $('#mainMenu').tree({
			//请求后台url
			url:'<%=request.getContextPath()%>/resource/getResourceTree.do',
								parentField : 'pid',
								lines : true,
								onClick : function(node) {
									//node点击的当前节点
									//	alert(node.text+node.attributes.url);
									addTabs(node);
								},
							});

			/*
			 *
			 */
			mainTabs = $('#mainTabs')
					.tabs(
							{
								fit : true,
								border : false,
								tools : [
										{
											text : '刷新',
											iconCls : 'ext-icon-arrow_refresh',
											handler : function() {
												var panel = mainTabs.tabs(
														'getSelected').panel(
														'panel');
												var frame = panel
														.find('iframe');
												if (frame.length > 0) {
													for (var i = 0; i < frame.length; i++) {
														frame[i].contentWindow.document
																.write('');
														frame[i].contentWindow
																.close();
														frame[i].src = frame[i].src;
													}
												}
											}
										},
										{
											text : '关闭',
											iconCls : 'ext-icon-cross',
											handler : function() {
												var index = mainTabs
														.tabs(
																'getTabIndex',
																mainTabs
																		.tabs('getSelected'));
												var tab = mainTabs.tabs(
														'getTab', index);
												//	mainTabs.tabs('getSelected').panel('options');
												if (tab.panel('options').closable) {
													mainTabs.tabs('close',
															index);//title
												} else {
													$.messager
															.alert(
																	'提示',
																	'['
																			+ tab
																					.panel('options').title
																			+ ']不可以被关闭！',
																	'error');
												}
											}
										} ]
							});
			function addTabs(node) {
				var tabs = $("#mainTabs")
				var src = sys.contextPath + node.attributes.url;
				if (tabs.tabs('exists', node.text)) {
					tabs.tabs('select', node.text);
				} else {
					tabs
							.tabs(
									'add',
									{
										title : node.text,
										content : formatString(
												'<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',
												src),
										closable : true,
										border : false,
										fit : true
									});
				}
			}
		})

		function formatString(str) {
			for (var i = 0; i < arguments.length - 1; i++) {
				str = str.replace("{" + i + "}", arguments[i + 1]);
			}
			return str;
		};
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>易创吧后台管理系统</title>
<style type="text/css">	
 	li {
 		list-style-type: none;
 		color:#F00;
 		font-size:12px;
 	}
</style>
</head>
<body id="mainLayout" class="easyui-layout">
	<div data-options="region:'north',href:'<%=request.getContextPath()%>/main/north.do'" style="height: 70px; overflow: hidden;" class="logo"></div>
	<div data-options="region:'west',href:'',split:true" title="导航" style="width: 200px;">
<!-- 		<ul id="mainMenu"></ul> -->
		<div id="tree" class="easyui-accordion"  style="width:190px;height:300px;" data-options="border:false">   
		</div>  
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
	var mainTabs;
	$(function(){
			mainTabs = $('#mainTabs').tabs({
								fit : true,
								border : false,
								tools : [{
											text : '刷新',
											iconCls : 'ext-icon-arrow_refresh',
											handler : function() {
												var panel = mainTabs.tabs('getSelected').panel('panel');
												var frame = panel.find('iframe');
												if (frame.length > 0) {
													for (var i = 0; i < frame.length; i++) {
														frame[i].contentWindow.document.write('');
														frame[i].contentWindow.close();
														frame[i].src = frame[i].src;
													}
												}
											}
										},
										{
											text : '关闭',
											iconCls : 'ext-icon-cross',
											handler : function() {
												var index = mainTabs.tabs('getTabIndex',mainTabs.tabs('getSelected'));
												var tab = mainTabs.tabs('getTab', index);
												if (tab.panel('options').closable) {
													mainTabs.tabs('close',index);//title
												} else {
													$.messager.alert('提示','['+ tab.panel('options').title+ ']不可以被关闭！','error');
												}
											}
										} ]
							});
			$.ajax({
				type: "POST",
				url: "<%=request.getContextPath()%>/menu/queryall.do",
				dataType:'json',
				success: function(data){
				   	for (var i = 0; i < data.length; i++) {
						var str="";
						if(data[i].list != null){
							str+='<ul>';
								for (var j = 0; j < data[i].list.length; j++) {
									var note="{'text':'"+data[i].list[j].text+"','url':'"+data[i].list[j].url+"'}";
									str+='<li><a href="javascript:void(0)" onclick="addTabs('+note+')">'+data[i].list[j].text+'</a></li>';
								}
								str+='</ul>'
						}
						$('#tree').accordion('add', {
							title: data[i].text,
							content: str,
							height:100,
							selected: false
						});
					}
				}
			})	
			
		})
function addTabs(node) {
				var tabs = $("#mainTabs")
				var src = sys.contextPath + node.url;
				if (tabs.tabs('exists', node.text)) {
					tabs.tabs('select', node.text);
				} else {
					tabs.tabs('add',{
						title : node.text,
						content : formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',src),
						closable : true,
						border : false,
						fit : true
						});
				}
			}
function formatString(str) {
	 for (var i = 0; i < arguments.length - 1; i++) {
	       	str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};
</script>
</body>
</html>
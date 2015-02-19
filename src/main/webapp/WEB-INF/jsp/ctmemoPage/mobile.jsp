<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>작업.</title>
<link rel="stylesheet" href="<c:url value="/css/jquery.mobile-1.4.5.css"/>">
<style type="text/css">
	textarea.ui-input-text { height: inherit !important}
	.ui-listview p:nth-of-type(1){
		margin-right: 60px;
	}
</style>
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.mobile-1.4.5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/util.js"/>"></script>
<script>
	function Ctmemo(rootPath){
		// 모든 링크 시작값
		var contextRoot = rootPath;
		// 삭제된 메모 아이디 저장. 큐 형태로 활용
		var deleteQueue = [];
		var instance = this;
		
		this.init = function(){
			this.loadAllMemo();
		}
		
		// 전체 메모장을 불러온다.
		this.loadAllMemo = function(){
			$.get(contextRoot + "/listAllCtmemo.json", function(memoList) {
				$.each(memoList, function() {
					console.log(this);
					var item = $("<li/>").append("<a>");
					var title = $("<p/>").append(this.content);
					var regDate = new Date(this.regDate);
					var date = $("<p/>").append(regDate.format("yy.MM.dd"));
					date.addClass("ui-li-aside");

					item.find("a").append(title);
					item.find("a").append(date);
					
					item.find("a").attr("href", "#popup");
					item.find("a").attr("data-rel", "dialog");
					item.find("a").attr("data-transition", "pop");
					
					$("._list").append(item);
				});
				
				// jquery mobile rendering 
				$("._list").listview('refresh');
			});
		}
	}

	$(function(){
		var ctmemo = new Ctmemo("<%=request.getContextPath()%>");
		ctmemo.init();
	});
</script>
</head>
<body>
	<div data-role="page" data-title="작업."	data-url="panel-fixed-page1">
		<div data-role="header" data-position="fixed">
			<h1>메모목록</h1>
			<a href="#add-form" data-icon="plus" data-iconpos="notext">Add</a>
			<a href="#nav-panel" data-icon="action" data-iconpos="notext">Undel.</a> 
		</div>
		<!-- /header -->
		<ul data-role="listview" data-inset="true" class="_list">
		</ul>
	</div>
	
	
	
	
	<!-- Start of third page: #popup -->
	<div data-role="page" id="popup">
		<div data-role="header" data-theme="e">
			<h1>Dialog</h1>
		</div><!-- /header -->
	
		<div data-role="content" data-theme="d">	
			<h2>Popup</h2>
			<p>I have an id of "popup" on my page container and only look like a dialog because the link to me had a <code>data-rel="dialog"</code> attribute which gives me this inset look and a <code>data-transition="pop"</code> attribute to change the transition to pop. Without this, I'd be styled as a normal page.</p>		
			<p><a href="#one" data-rel="back" data-role="button" data-inline="true" data-icon="back">Back to page "one"</a></p>	
		</div><!-- /content -->
		
		<div data-role="footer">
			<h4>Page Footer</h4>
		</div><!-- /footer -->
	</div><!-- /page popup -->	
</body>
</html>

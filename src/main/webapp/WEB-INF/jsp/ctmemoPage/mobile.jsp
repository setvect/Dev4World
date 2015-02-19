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
		this.contextRoot = rootPath;
		// 삭제된 메모 아이디 저장. 큐 형태로 활용
		this.deleteQueue = [];
		this.memoMap = {};
		var instance = this;
		
		this.init = function(){
			this.loadAllMemo();
		}
		
		// 전체 메모장을 불러온다.
		this.loadAllMemo = function(){
			$.get(instance.contextRoot + "/listAllCtmemo.json", function(memoList) {
				var aa = true;
				$.each(memoList, function() {
					
					instance.memoMap[this.ctmemoSeq] = this;
					
					
					var item = $("<li/>").append("<a>");
					var title = $("<p/>").append(this.content);
					var regDate = new Date(this.regDate);
					var date = $("<p/>").append(regDate.format("yy.MM.dd"));
					date.addClass("ui-li-aside");

					item.find("a").append(title);
					item.find("a").append(date);
					item.find("a").attr("href", "#popup");
					item.find("a").attr("data-transition", "slide");
					
					item.attr("data-ctmemo_seq",this.ctmemoSeq);
					item.attr("data-bg_css",this.bgCss);
					item.attr("data-font_css",this.fontCss);
					var regDate = new Date(this.regDate);
					item.attr("data-reg_date",regDate.format("yyyy-MM-dd HH:mm:ss"));
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
		
		$(document).on("click", "._list a", function(){
			console.log($(this).parent("li"));
			console.log(ctmemo.memoMap);
		});
		
		

		
		
		
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
		<ul data-role="listview" data-inset="true" class="_list">
			<!-- 목록 표시 -->
		</ul>
	</div>
	
	
	
	
	<!-- Start of third page: #popup -->
	<div data-role="page" id="popup">
		<div data-role="header">
			<button class="ui-btn-left ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-edit">Edit</button>
			<h1>메모보기</h1>
			<button class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-delete">Delete</button>
		</div>
		<div class="ui-body">
			<p class="_content"></p>
		</div>
	</div><!-- /page popup -->
	
</body>
</html>

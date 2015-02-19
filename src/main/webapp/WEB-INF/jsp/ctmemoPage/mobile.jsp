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
	.delete_btn {
		margin-right: 0.5em !important;
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
		this.currentMemoSeq;
		var instance = this;
		
		this.init = function(){
			this.loadAllMemo();
			
			$(document).on("click", "._delete", function(){
				instance.deleteMemo();
			});
			
			// 목록에서 항목 클릭 시 적용 이벤트 
			$(document).on("click", "._list a", function(){
				instance.contentView(this);
			});

			// 삭제 취소
			$(document).on("click", "._undelete", function(){
				instance.undeleteMemo();
			});
			
			instance.undeleteDisplay();
		}
		
		this.contentView = function(eventElement){
			var item = $(eventElement).parent("li");
			var seq = item.attr("data-ctmemo_seq");

			// 메모 본문 표시
			var memo = instance.memoMap[seq];
			instance.currentMemoSeq = seq;
			$("._content").append(newline2br(memo.content));
		}
		
		// 전체 메모장을 불러온다.
		this.loadAllMemo = function(){
			$.get(instance.contextRoot + "/listAllCtmemo.json", function(memoList) {
				$("._list").html("");
				$.each(memoList, function() {
					instance.memoMap[this.ctmemoSeq] = this;
					
					var item = $("<li/>").append("<a>");
					var title = $("<p/>").append(this.content);
					var regDate = new Date(this.regDate);
					var date = $("<p/>").append(regDate.format("yy.MM.dd"));
					date.addClass("ui-li-aside");

					item.find("a").append(title);
					item.find("a").append(date);
					item.find("a").attr("href", "#content_page");
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
		
		// 메모 삭제
		this.deleteMemo = function(){
			$.post(instance.contextRoot + "/deleteMemo.do", {ctmemoSeq: instance.currentMemoSeq}, function( data ) {
				instance.deleteQueue.push(instance.currentMemoSeq);
				instance.undeleteDisplay();
				instance.loadAllMemo();
				$.mobile.navigate("#list_page");
			});		
		}		
		
		// 마지막 삭제 취소
		this.undeleteMemo = function(){
			console.log("DDDDDDDDDDDDD");
			var seq = instance.deleteQueue.pop();
			$.post(instance.contextRoot + "/undelete.json", {ctmemoSeq: seq}, function( memo ) {
				instance.loadAllMemo();
				instance.undeleteDisplay();
			});	
		}
		
		// 삭제 취소 버튼 활성화 여부 
		this.undeleteDisplay = function(){
			console.log("################");
			$("._undelete").css("display", instance.deleteQueue.length == 0 ? "none" : "inline-block");
		}
	}

	$(function(){
		
		var ctmemo = new Ctmemo("<%=request.getContextPath()%>");
		ctmemo.init();
		
	});
</script>
</head>
<body>
	<div data-role="page" data-title="작업."	data-url="panel-fixed-page1" id="list_page">
		<div data-role="header" data-position="fixed">
			<h1>메모목록</h1>
			<a href="#add-form" data-icon="plus" data-iconpos="notext">Add</a>
			<a data-icon="action" data-iconpos="notext" class="_undelete">Undel.</a> 
		</div>
		<ul data-role="listview" data-inset="true" class="_list">
			<!-- 목록 표시 -->
		</ul>
	</div>
	
	
	<div data-role="page" id="content_page">
		<div data-role="header">
			<a class="ui-btn-left ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-back" data-rel="back" data-direction="reverse">Back</a>
			<h1>메모보기</h1>
			<div data-type="horizontal" data-role="controlgroup" class="ui-btn-right">  
     		<a class="ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-delete delete_btn _delete">Delete</a>
     		<a class="ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-edit">Edit</a>
  		</div>
		</div>
		<div class="ui-body">
			<p class="_content"></p>
		</div>
	</div>
	
	
	<div data-role="page" id="write_page">
		<div data-role="header">
			<a href="#" class="ui-btn-left ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-back">Back</a>
			<h1>메모등록</h1>
			<button class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-check">Save</button>
		</div>
		<!-- /content -->
		<textarea name="textarea-2" rows="10">Textarea</textarea>
	</div>
</body>
</html>

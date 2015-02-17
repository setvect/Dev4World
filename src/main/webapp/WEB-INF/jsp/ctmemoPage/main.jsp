<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>복슬 메모장</title>
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/util.js"/>"></script>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/jquery-ui.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css"/>" />
<style type="text/css">
.memo {
	padding: 0.5em;
	position: absolute;
}

.memo span {
	font-size: 10px;
}

.memo textarea{
	font-size: 10px;
}

.memo span input {
	font-size: 10px;
	padding: 0;
	float: right;
}
.tool input{
	font-size: 10px;
	padding: 0;
}
</style>

<script type="text/javascript">
	var CONTEXT_ROOT = "<%=request.getContextPath()%>";
	var deleteQueue = [];
	
	$(function() {
		loadAllMemo();
		$("._new").on("click", function(){
			newMemo();			
		});
		
		$("._undelete").on("click", function(){
			undeleteMemo();			
		});
		
		$("._search").keyup(function(){
			search($(this).val());
		});
		
		$("#space").on("click", "._delete", function(){
			var eventObj = $(this).parents("._item");
			deleteMemo(eventObj);
		});
		
		$("#space").on("click", "._edit", function(){
			var eventObj = $(this).parents("._item");
			editMemo(eventObj);
		});
		
		$("#space").on("click", "._done", function(){
			var eventObj = $(this).parents("._item");
			editMemoDone(eventObj);
		});		
	});

	// 새로운 메모를 생성한다.
	function newMemo(){
		$.get(CONTEXT_ROOT + "/newMemo.json", function(memo) {
			console.log("##$$$$");
			console.log(memo);
			displayMemo(memo);
		});
	}

	// 전체 메모장을 불러온다.
	function loadAllMemo(){
		$.get(CONTEXT_ROOT + "/listAllCtmemo.json", function(memoList) {
			$.each(memoList, function() {
				displayMemo(this);
			});
		});
	}
	
	function displayMemo(memo){
		var item = $("<div id='draggable' class='memo ui-widget-content _item'><span class='_header'></span><div class='_content'></div></div>");
		item.attr("data-ctmemo_seq",memo.ctmemoSeq);
		item.attr("data-bg_css",memo.bgCss);
		item.attr("data-font_css",memo.fontCss);
		var regDate = new Date(memo.regDate);
		item.attr("data-reg_date",regDate.format("yyyy-MM-dd HH:mm:ss"));
		item.find("._content").append(newline2br(memo.content));
		item.css("width", memo.width)
				.css("height", memo.height)
				.css("left", memo.positionX)
				.css("top", memo.positionY)
				.css("z-index", memo.zIndex);
		item.find("._header").append(regDate.format("yy.MM.dd"));
		item.find("._header").append("<input type='button' value='D' class='_delete'/>");
		item.find("._header").append("<input type='button' value='E' class='_edit'/>");
		item.find("._header").append("<input type='button' value='Done' class='_done' />");
		item.find("._header ._done").hide();
		$("#space").append(item);
		
		item.draggable({stop: function(eventObj){
			var element = $(eventObj.target);
			saveMemo(element);
		}});
		item.resizable({   
			maxHeight: 300,
		   maxWidth: 300,
		   minHeight: 100,
		   minWidth: 100,
		   stop: function(eventObj){
				var element = $(eventObj.target);
				saveMemo(element);
		  	}
		});
	}
	
	// 추가 또는 변경된 메모장을 저장
	function saveMemo(element){
		var data = {};
		data["ctmemoSeq"] = parseInt(element.attr("data-ctmemo_seq"));
		data["content"] = br2newline(element.find("._content").html());
		data["zIndex"] = parseInt(element.css("z-index"));
		data["width"] = parseInt(element.css("width").replace('px', ''));
		data["height"] = parseInt(element.css("height").replace('px', ''));
		data["positionX"] = parseInt(element.css("left").replace('px', ''));
		data["positionY"] = parseInt(element.css("top").replace('px', ''));
		data["bgCss"] = element.attr("data-bg_css");
		data["fontCss"] = element.attr("data-font_css");
		data["regDate"] = element.attr("data-reg_date");
		console.log(data);
		
		$.post(CONTEXT_ROOT + "/saveMemo.do", data, function( zIndex ) {
			console.log(data);
			element.css("z-index", zIndex)
		});
	}
	
	// 메모 수정
	function editMemo(editElement){
		// 편집 상태에선 드레그, 사이즈 조정 금지
		editElement.draggable("disable")
		editElement.resizable("disable")
		
		editElement.find("._header ._edit").hide();
		editElement.find("._header ._done").show();
		
		var content = br2newline(editElement.find("._content").html());
		var width = editElement.find("._content").width() - 6;
		// .content 높이를 가져오지 못함
		var height = editElement.height() - 30;
		
		editElement.find("._content").html("");
		editElement.find("._content").append("<textarea>"+content+"</textarea>")
		editElement.find("textarea").css("width", width).css("height", height);
		console.log(content);
	}
	
	// 메모 수정 사항 서버 반영
	function editMemoDone(editElement){
		editElement.draggable("enable")
		editElement.resizable("enable")

		editElement.find("._header ._edit").show();
		editElement.find("._header ._done").hide();
		var content = newline2br(editElement.find("._content textarea").val());
		editElement.find("._content").html("");
		editElement.find("._content").append(content)
		saveMemo(editElement);
	}
	
	// 메모 삭제
	function deleteMemo(deleteElement){
		var seq = deleteElement.attr("data-ctmemo_seq");
		$.post(CONTEXT_ROOT + "/deleteMemo.do", {ctmemoSeq: seq}, function( data ) {
			deleteQueue.push(seq);
			deleteElement.remove();
			undeleteDisplay();
		});		
	}
	
	// 삭제 취소 버튼 활성화 여부 
	function undeleteDisplay(){
		$("._undelete").css("display", deleteQueue.length == 0 ? "none" : "inline-block");
	}
	
	// 마지막 삭제 취소
	function undeleteMemo(){
		var seq = deleteQueue.pop();
		$.post(CONTEXT_ROOT + "/undelete.json", {ctmemoSeq: seq}, function( memo ) {
			displayMemo(memo);
			undeleteDisplay();
		});	
	}
	
	// 메모 검색
	function search(word){
		console.log("search: " + word);
	}
	
	// br 테그를 newline(\n)
	function br2newline(str){
		return str.replace(/<br>/g, "\n")
	}
	
	//  newline(\n)을 br 테그로
	function newline2br(str){
		return str.replace(/\n/g, "<br>")
	}
</script>
</head>
<body>
	<div id="space">
		<div class="tool">
			<input type="text" value="" class="_search"/>
			<input type="button" value="New" class="_new" /> 
			<input type="button" value="Undel" class="_undelete" style="display: none;"/>
		</div>
	</div>
</body>
</html>
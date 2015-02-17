<%@page import="com.dev4world.ctmemo.CtmemoConstant"%>
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
	padding: 0.2em;
	position: absolute;
}

.memo .toolbar {
	font-size: 10px;
}

.memo .itemContent{
	clear: both;
}

.memo .itemContent span{
	color: red;
}

.memo textarea{
	font-size: 10px;
}

.memo span .styleBtn {
	background-color: orange;
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
.palette{
	width: 50px;
	position: absolute;
	display: none;
	z-index: 99999999999;
}
.palette li{
	float: left;
}
.palette div{
	padding: 2px;
	width: 10px;
	height: 10px;
	cursor: pointer;
}
.palette .text_area div	{
	background-color: #ee98de;
}

/* 검색 안된 메모 스타일 */
.not_search{
	opacity: .3;
}

/* 메모장 적용 스타일 */
._ct_bgstyle_1{
	background-color: teal;
}
._ct_bgstyle_2{
	background-color: fuchsia;
}
._ct_bgstyle_3{
	background-color: activeborder;
}

._ct_fontstyle_1{
	color: maroon;
}
._ct_fontstyle_2{
	color: white;
}
._ct_fontstyle_3{
	color: blue;
}
</style>

<script type="text/javascript">
	// === 전역 변수
	// 모든 링크 시작값
	var CONTEXT_ROOT = "<%=request.getContextPath()%>";
	// 메모장에 적용될 스타일
	var STYLE_LIST;
	// 메모장에 적용 스타일 prifix. 필터링 조건으로 사용
	var STYLE_PREFIX = "<%=CtmemoConstant.Style.PREFIX%>";
	
	// 삭제된 메모 아이디 저장. 큐 형태로 활용
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

		// 각 메모장 상단 버튼 이벤트
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
		
		$("#space").on("click", "._style", function(event){
			var eventObj = $(this).parents("._item");
			choiceStyle(eventObj, event);
		});		
		
		// 팔레트 항목 클릭
		$("._style_palette").on("click", "div", function(){
			applyPalette(this);
		});		
		
		$.get(CONTEXT_ROOT + "/listUsagestyle.json", function(styleList) {
			STYLE_LIST = styleList;	
			loadPalette(styleList);
		});		
	});

	// 팔레트 적용
	function loadPalette(styleList){
		var bg = $("._style_palette ._bg");
		styleList["bg"].forEach(function(entry){
			var area = $("<li/>").append("<div/>")
			area.find("div").addClass(entry);
			bg.append(area);
		});
		
		var font = $("._style_palette ._font");
		styleList["font"].forEach(function(entry){
			var area = $("<li/>").append("<div>T</div>")
			area.find("div").addClass(entry);
			font.append(area);
		});
	}
	
	// 새로운 메모를 생성한다.
	function newMemo(){
		$.get(CONTEXT_ROOT + "/newMemo.json", function(memo) {
			displayMemo(memo);
			var newElement = $("._item[data-ctmemo_seq='"+memo.ctmemoSeq+"']")
			editMemo(newElement);
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
	
	// 한 개 메모를 화면에 표시
	function displayMemo(memo){
		var item = $("<div id='draggable' class='memo _item'><span class='toolbar _header'></span><div class='itemContent _content'></div></div>");
		item.attr("data-ctmemo_seq",memo.ctmemoSeq);
		item.attr("data-bg_css",memo.bgCss);
		item.attr("data-font_css",memo.fontCss);
		var regDate = new Date(memo.regDate);
		item.attr("data-reg_date",regDate.format("yyyy-MM-dd HH:mm:ss"));
		item.find("._content").append(newline2br(memo.content));
		item.css("left", memo.positionX)
			.css("top", memo.positionY)
			.css("z-index", memo.zIndex)
			.css("width", memo.width)
			.css("height", memo.height)
			.addClass(memo.bgCss)
			.addClass(memo.fontCss);
		
		item.find("._header").append(regDate.format("yy.MM.dd"));
		item.find("._header").append("<input type='button' value='D' class='_delete'/>");
		item.find("._header").append("<input type='button' value='E' class='_edit'/>");
		item.find("._header").append("<input type='button' value='Done' class='_done' />");
		item.find("._header").append("<input type='button' value='S' class='_style styleBtn'/>");
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
		   minWidth: 110,
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
		data["content"] = removeTags(br2newline(element.find("._content").html()));
		data["zIndex"] = parseInt(element.css("z-index"));
		data["width"] = parseInt(element.css("width").replace('px', ''));
		data["height"] = parseInt(element.css("height").replace('px', ''));
		data["positionX"] = parseInt(element.css("left").replace('px', ''));
		data["positionY"] = parseInt(element.css("top").replace('px', ''));
		data["bgCss"] = element.attr("data-bg_css");
		data["fontCss"] = element.attr("data-font_css");
		data["regDate"] = element.attr("data-reg_date");
		
		$.post(CONTEXT_ROOT + "/saveMemo.do", data, function( zIndex ) {
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
		content = removeTags(content);
		var width = editElement.find("._content").width() - 6;
		// .content 높이를 가져오지 못함
		var height = editElement.height() - 30;
		
		editElement.find("._content").html("");
		editElement.find("._content").append("<textarea>"+content+"</textarea>")
		editElement.find("textarea").css("width", width).css("height", height);
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
	
	// 스타일 선택
	function choiceStyle(choiceElement, event){
		var ctmemoSeq = choiceElement.attr("data-ctmemo_seq");
		$("._style_palette").attr("data-target_seq", ctmemoSeq);
		$("._style_palette").css("left", event.pageX);
		$("._style_palette").css("top", event.pageY);
		$("._style_palette").show();
	}
	
	// 스타일 적용
	function applyPalette(choiceElement){
		var ctmemoSeq = $("._style_palette").attr("data-target_seq");
		var targetMemo = $("._item[data-ctmemo_seq='"+ctmemoSeq+"']");
		
		var choiceStyle = $(choiceElement).attr("class");
		var choiceType = $(choiceElement).parents("ul").attr("data-type");
		
		STYLE_LIST[choiceType].forEach(function(entry){
			targetMemo.removeClass(entry);
		});
		targetMemo.addClass(choiceStyle);
		if(choiceType == "bg"){
			targetMemo.attr("data-bg_css", choiceStyle);
		}
		else{
			targetMemo.attr("data-font_css", choiceStyle);
		}
		saveMemo(targetMemo);
		$("._style_palette").hide();
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
		$("#space ._item").each(function(){
			var content = br2newline($(this).find("._content").html());
			content = removeTags(content);
			if(word.length == 0){
				$(this).removeClass("not_search");
			}
			else if(content.indexOf(word) == -1){
				$(this).addClass("not_search");
			}
			else{
				$(this).removeClass("not_search");
				content = replaceAll(content, word, "<span>" + word + "</span>");
			}
			$(this).find("._content").html(newline2br(content));
		});
	}
	
	// br 테그를 newline(\n)
	function br2newline(str){
		return str.replace(/<br>/g, "\n")
	}
	
	//  newline(\n)을 br 테그로
	function newline2br(str){
		return str.replace(/\n/g, "<br>")
	}
	
	// Html 테크 제거
	function removeTags (str) {
		return str.replace(/<(?:.|\n)*?>/gm, '');
	}
	
	// 매칭 문자열 교체
	function replaceAll(str, find, replace) {
		return str.replace(new RegExp(find, 'g'), replace);
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
	
	<div class="palette _style_palette">
		<ul class="_bg" data-type="bg">
			<!-- bgstyle -->
		</ul>
		<ul class="text_area _font" data-type="font">
			<!-- fontstyle -->
		</ul>
	</div>
	
</body>
</html>
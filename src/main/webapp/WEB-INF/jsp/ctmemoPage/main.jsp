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
</style>

<script type="text/javascript">
	var CONTEXT_ROOT = "<%=request.getContextPath()%>";
	$(function() {
		loadAllMemo();
	});

	// 전체 메모장을 불러온다.
	function loadAllMemo(){
		$.get(CONTEXT_ROOT + "/listAllCtmemo.json", function(memoList) {
			$.each(memoList, function() {
				var item = $("<div id='draggable' class='memo ui-widget-content _item'><span class='_header'></span><div class='_content'></div></div>");
				item.attr("data-ctmemo_seq",this.ctmemoSeq);
				var regDate = new Date(this.regDate);
				item.attr("data-reg_date",regDate.format("yyyy-MM-dd HH:mm:ss"));
				item.find("._content").append(this.content.replace(/\n/g, "<br>"));
				item.css("width", this.width)
						.css("height", this.height)
						.css("left", this.positionX)
						.css("top", this.positionY)
						.css("z-index", this.zIndex);
				item.find("._header").append(regDate.format("yy.MM.dd HH.mm"));
				$("#space").append(item);
			});
			$("._item" ).draggable({	stop: saveMemo})
			$("._item" ).resizable({   
				maxHeight: 300,
			   maxWidth: 300,
			   minHeight: 80,
			   minWidth: 80,
			   stop: saveMemo});
		});
	}
	
	// 추가 또는 변경된 메모장을 저장
	function saveMemo(eventObj){
		var element = $(eventObj.target);
		var data = {};
		data["ctmemoSeq"] = parseInt(element.attr("data-ctmemo_seq"));
		data["content"] = element.find("._content").html().replace(/<br>/g, "\n");
		data["zIndex"] = parseInt(element.css("z-index"));
		data["width"] = parseInt(element.css("width").replace('px', ''));
		data["height"] = parseInt(element.css("height").replace('px', ''));
		data["positionX"] = parseInt(element.css("left").replace('px', ''));
		data["positionY"] = parseInt(element.css("top").replace('px', ''));
		data["regDate"] = element.attr("data-reg_date");
		console.log(data);
		
		$.post(CONTEXT_ROOT + "/saveMemo.do", data, function( data ) {
			console.log(data);
			$("._item" ).draggable().resizable({   
				maxHeight: 300,
			   maxWidth: 300,
			   minHeight: 80,
			   minWidth: 80});
		});
	}
	
</script>
</head>
<body>
	<div id="space">
	</div>
</body>
</html>

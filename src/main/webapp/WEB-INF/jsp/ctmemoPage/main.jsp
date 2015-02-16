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
				var item = $("<div id='draggable' class='memo ui-widget-content _item'><span class='_header'></span></div>");
				item.append(this.content.replace(/\n/g, "<br/>"));
				item.css("width", this.width)
						.css("height", this.height)
						.css("left", this.positionX)
						.css("top", this.positionY)
						.css("z-index", this.zIndex);
				var regDate = new Date(this.regDate);
				item.find("._header").append(regDate.format("yy.MM.dd HH.mm"));
				$("#space").append(item);
			});
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

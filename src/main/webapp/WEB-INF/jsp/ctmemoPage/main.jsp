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
#space {
	height: 100%;
	width: 100%;
}

.memo {
	width: 150px;
	height: 150px;
	padding: 0.5em;
}
</style>

<script type="text/javascript">
	var CONTEXT_ROOT = "<%=request.getContextPath()%>";
	$(function() {
		loadAllMemo();
	});

	function loadAllMemo(){
		$.get(CONTEXT_ROOT + "/listAllCtmemo.json", function(memoList) {
			$.each(memoList, function() {
				var item = $("<div id='draggable' class='memo ui-widget-content _item'><span class='_header'></span></div>");
				item.append(this.content);
				var regDate = new Date(this.regDate);
				item.find("._header").append(regDate.format("yy.MM.dd HH.mm"));
				$("#space").append(item);
			});
			$("._item" ).draggable();
		});
	}
	
</script>
</head>
<body>
	<div id="space">
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Classic theme - jQuery Mobile Demos</title>
<link rel="stylesheet" href="<c:url value="/css/jquery.mobile-1.4.5.css"/>">
<script src="<c:url value="/js/jquery-1.11.2.js"/>"></script>
<script src="<c:url value="/js/jquery.mobile-1.4.5.min.js"/>"></script>
<script>
	
</script>
<style>
.noshadow * {
	-webkit-box-shadow: none !important;
	-moz-box-shadow: none !important;
	box-shadow: none !important;
}

.nogradient, .nogradient * {
	background-image: none !important;
}

.nohighlight .ui-btn:before {
	display: none !important;
}

form.ui-mini .ui-field-contain fieldset.ui-controlgroup legend small {
	color: #666;
}
</style>
</head>
<body>
	<div>
		<ul data-role="listview" class="ui-listview">
			<li class="ui-first-child"><a href="#" class="ui-btn ui-btn-icon-right ui-icon-carat-r">Acura</a></li>
			<li><a href="#" class="ui-btn ui-btn-icon-right ui-icon-carat-r">Audi</a></li>
			<li><a href="#" class="ui-btn ui-btn-icon-right ui-icon-carat-r">BMW</a></li>
			<li><a href="#" class="ui-btn ui-btn-icon-right ui-icon-carat-r">Cadillac</a></li>
			<li class="ui-last-child"><a href="#" class="ui-btn ui-btn-icon-right ui-icon-carat-r">Ferrari</a></li>
		</ul>
	</div>
</body>
</html>

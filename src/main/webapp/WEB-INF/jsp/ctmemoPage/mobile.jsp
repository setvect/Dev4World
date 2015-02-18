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
<script src="<c:url value="/js/jquery-1.11.2.js"/>"></script>
<script src="<c:url value="/js/jquery.mobile-1.4.5.js"/>"></script>
<script>
</script>
</head>
<body>
	<div data-role="page" class="jqm-demos" id="panel-fixed-page1" data-title="작업."	data-url="panel-fixed-page1">
		<div data-role="header" data-position="fixed">
			<h1>메모목록</h1>
			<a href="#add-form" data-icon="plus" data-iconpos="notext">Add</a>
			<a href="#nav-panel" data-icon="back" data-iconpos="notext">Dalcen</a> 
		</div>
		<!-- /header -->
		<ul data-role="listview" data-inset="true">
			<li>
				<a href="index.html">
					<p>Hey Stephen, if you're available at 10am tomorrow, we've got a meeting with the jQuery team.</p>
					<p class="ui-li-aside">
						<strong>6:24</strong>PM
					</p>
				</a>
			</li>
			<li><a href="index.html">
					<p>Sure, let's plan on meeting at Highland Kitchen at 8:00 tonight. Can't wait!</p>
					<p class="ui-li-aside">
						<strong>4:48</strong>PM
					</p>
			</a></li>
		</ul>


		<div data-role="header">
			<button class="ui-btn-left ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-edit">Edit</button>
			<h1>메모보기</h1>
			<button class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-delete">Delete</button>
		</div>
		<div class="ui-body">
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse accumsan blandit fermentum. Pellentesque cursus mauris purus, auctor commodo mi ullamcorper nec. Donec semper mattis eros, nec condimentum ante sollicitudin quis. Etiam orci sem, porttitor ut tellus nec, blandit posuere urna. Proin a arcu non lacus pretium faucibus. Aliquam sed est porttitor, ullamcorper urna nec, vehicula lorem. Cras porttitor est lorem, non venenatis diam convallis congue.</p>
		</div>



		<div data-role="header">
			<a href="#" class="ui-btn-left ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-back">Back</a>
			<h1>메모등록</h1>
			<button class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-check">Save</button>
		</div>
		<!-- /content -->
		<textarea name="textarea-2" rows="10">Textarea</textarea>

		<!-- /popup -->
		<div class="ui-screen-hidden ui-popup-screen ui-overlay-inherit" id="confirm-screen"></div>
		<div class="ui-popup-container ui-popup-hidden ui-popup-truncate" id="confirm-popup">
			<div id="confirm" class="ui-content ui-popup ui-body-a ui-overlay-shadow ui-corner-all" data-role="popup"
				data-theme="a">
				<p id="question">Are you sure you want to delete:</p>
				<div class="ui-grid-a">
					<div class="ui-block-a">
						<a id="yes" class="ui-btn ui-corner-all ui-mini ui-btn-a" data-rel="back">Yes</a>
					</div>
					<div class="ui-block-b">
						<a id="cancel" class="ui-btn ui-corner-all ui-mini ui-btn-a" data-rel="back">Cancel</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>복슬 메모장</title>
<script	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
<script>
	function customersController($scope,$http) {
	  $http.get("/dev4world/listAllCtmemo.json").success(function(response) {
		  console.log(response);
		  $scope.item = response;
		  $scope.remove = function(index){
			  var deleteItem = $scope.item[index];
			  $scope.item.splice (index, 1);
		  };
		  $scope.now= function(index){
			  var choiceItem = $scope.item[index];
			  console.log(choiceItem);
			  choiceItem.regDate = new Date();
		  };		  
	  });
	}
</script>

</head>
<body>
<div ng-app="" ng-controller="customersController">
	<ul>
		<li ng-repeat="x in item">
			{{ x.content + ', ' + (x.regDate | date:'yyyy-MM-dd')}}
			<button ng-click="remove($index)">Delete</button>
			<button ng-click="now($index)">Now</button>
		</li>
	</ul>
</div>
</body>
</html>
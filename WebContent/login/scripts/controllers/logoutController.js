app.controller("LCtrl",function($scope,$rootScope,$http,$location,$window){
	
	$window.sessionStorage.removeItem("id");
	$window.sessionStorage.removeItem("fName");
	$window.sessionStorage.removeItem("lName");
	$window.sessionStorage.removeItem("adminId");
	$window.sessionStorage.removeItem("cmtId");
	$window.sessionStorage.removeItem("vendorId");
	$window.sessionStorage.removeItem("modId");
	$window.sessionStorage.removeItem("empId");
	
    
	$rootScope.tabs=[];
	
	$rootScope.ballence=0;
	window.location="#/Login";
	
});
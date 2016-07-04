app.controller("NotifController",function($scope,$rootScope,$http,$window){

	$scope.loginid=0;
	init();

	function init() {
		
		//$scope.count=0;
		if($window.sessionStorage.getItem("empId")!==null||$window.sessionStorage.getItem("modId")!==null||$window.sessionStorage.getItem("adminId")!==null) {
			$scope.loginid=0;
	//		alert("In notif 1 other");
	//		alert($scope.loginid);
		}
		
		else if($window.sessionStorage.getItem("cmtId")==null){
			$scope.loginid=$window.sessionStorage.getItem("vendorId");
		
			console.log("Inside cmt");
			
	//		alert("In notif 2 set vendor");
	//		alert($scope.loginid);
			console.log($scope.loginid);
			
		}
		
		else if ($window.sessionStorage.getItem("vendorId")==null){
			console.log("Inside vendor");
			$scope.loginid=$window.sessionStorage.getItem("cmtId");
		
	//		alert("In notif 3 set cmt");
	//		alert($scope.loginid);
		
		}
		
		

	//	alert($scope.loginid);
	
		$http.get('api/fb/getNotificationCount', {
			params : {
				value :$scope.loginid
				}
			}).success(function(notificationCount) {
	//			alert("Inside notif");
				
	//			console.log("notification");
	//			alert($rootScope.count);
				$rootScope.count=notificationCount;	
				console.log(notificationCount);
				
				if($rootScope.count>0)
				{
				

				$rootScope.countMsg=notificationCount;
			//	$scope.count=20;
				
	//			alert("count value");
				
				console.log($rootScope.count);
			
	//			alert($rootScope.count);
				
				$rootScope.notifMsg='You have '+ $rootScope.countMsg +' feedback(s) to be addressed.';
				
				if($rootScope.count!==0)
				{
				
					notif();
	//				alert($rootScope.count);
	//				alert(" Notif called");
					$rootScope.count=-1;
				}
				else
					{
					$rootScope.count=null;
					notif();
					$rootScope.notifMsg='No new notifications';
					
					}
				
			}
			}).error(function()
			{
				console.log("Error while getting the  details!!");
			});
	};
});
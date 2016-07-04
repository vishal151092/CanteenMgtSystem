app.controller('ViewMyFbCtrl2', function($scope, $rootScope, $http) {

	
	init();

	function init() {
		$scope.feedbacks = {};
		
		alert("Inside view my Notif");
		$http.get('api/feedback/viewMyNotifications', {
			params : {
				value : 1238004
			}
		}).success(function(feeddata) {

			$scope.feedbacks = feeddata;
			
/*			var blob = new Blob([feeddata], {
		        type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
		    });
		    saveAs(blob, "Report.xls");
*/			

		}).error(function() {
			console.log("Error while getting the  details!!");
		});

		
		$http.get('api/feedback/generateExcel', {
		}).success(function(feeddata) {


		}).error(function() {
			console.log("Error while getting the  details!!");
		});

		
		
	}
	
	
	
	
	
	
	$scope.takeActionOnNotification=function(index){
		
		$scope.selectedFeedback=$scope.feedbacks[index];
		window.location="#/feedbackActionForm";
		
		
	};
});
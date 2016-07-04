app.controller("FeedBackCtrl", function($scope, $rootScope, $timeout, $http,$window) {

	
	init();

	$scope.feedbacks.userMsg={};
	
	function init() {

		$scope.feedbacks = {};
		
		$scope.counter=0;

		var empid = $window.sessionStorage.getItem("empId");

		$http.get('api/feedback/getMyFeedback', {
			params : {
				value : empid
			}
		}).success(function(feeddata) {

			$scope.feedbacks=feeddata;

			console.log($scope.feedbacks);
			
		}).error(function() {
			console.log("Error while getting the  details!!");
		});
		
		
};


	$scope.getUserMsg=function(index){

		
		$http.get("api/getFeedbackActionDetails",{ params :{fId:$scope.feedbacks[index].feedbackId}}).success(function(feedbackAction)
				{
					
				}).error(function() {
			console.log("Error while getting FeedbackAction Details!!");
		});
		
	
	};

});


app.controller('fdbController', function($scope, $location, $rootScope,$timeout, $http,$window) 
		{

	
	$scope.process = {

		"init" : function() {

			$http.get('api/getAllFeedbackDetails').success(function(allFeedbacks) {
						$rootScope.allFeedbacks = allFeedbacks;	
						console.log($rootScope.allFeedbacks);
					});
		}		
	};

	$scope.process.init();
	
	$rootScope.feedbackDtl=function(fb){
		$http.get("api/getFeedbackActionDetails",{ params :{fId:fb}}).success(function(feedbackAction)
				{
					$rootScope.feedbackAction = feedbackAction;
						
				}).error(function() {
			console.log("Error while getting FeedbackAction Details!!");
		});

	/*	console.log("sad");
		
		console.log($scope.excel=$rootScope.allFeedbacks[0]+$rootScope.allFeedbacks[1]);
		
		$scope.excel=$rootScope.allFeedbacks[0]+$rootScope.allFeedbacks[1];
		
	   alasql('SELECT * INTO XLSX("john.xlsx",{headers:true}) FROM ?',$rootScope.allFeedbacks);
	*/      
	
	};
		
		
		/*var blob = new Blob([$rootScope.allFeedbacks], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "ReportFeedback.odp");
		*/
		
	$scope.generateExcel=function(stat){
		
		$http.get('api/feedback/generateExcel', { params :{status:stat}}).then(function() {

			$window.location.href="/CMS/feedback/FeedbackReport.xls";

		}),(function() {
			console.log("Error while getting the  details!!");
		});


		};
	
		
   /*     var myJsonString = JSON.stringify($rootScope.allFeedbacks);
        var blob = new Blob([myJsonString], {
            type: "application/vnd.ms-excel;charset=charset=utf-8"
        });
        saveAs(blob, "Report.xls");
	*/	
});



app.controller('fdbVendorController', function($scope,$routeParams,$http,$rootScope,$location,$window) 
		{	
	/*	$rootScope.vId;
		$rootScope.vId=$window.sessionStorage.getItem("vendorId");	
		init();
		function init(){
		//$scope.vId=$window.sessionStorage.getItem("vendorId");	
		$http.get('api/getMyValidFeedbackDetails',{ params :{vId:$rootScope.vId}}).success(
				function(allValidFeedbacks) {
					$rootScope.allValidFeedbacks = allValidFeedbacks;		
					console.log("My Valid Feedbacks"+$rootScope.allValidFeedbacks);
				}).error(function() {
			console.log("Error while getting FeedbackAction Details!!");
		});
		};
		
		$rootScope.feedbackDtl=function(fb){
			$http.get("api/getFeedbackActionDetails",{ params :{fId:fb}}).success(function(feedbackAction)
					{
						$rootScope.feedbackAction = feedbackAction;
						console.log($scope.feedbackAction);
							
					}).error(function() {
				console.log("Error while getting FeedbackAction Details!!");
			});
			};
*/	
});
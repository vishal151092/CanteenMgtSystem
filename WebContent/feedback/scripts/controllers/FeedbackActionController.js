app.controller("FeedbackActionController",function($scope,$http,$rootScope,$window){

	$scope.loginid={};
	init();

	function init() {
		$scope.feedbacks = {};
		
		$scope.loginid=$window.sessionStorage.getItem("adminId");
		
		
		$http.get('api/feedback/viewMyNotifications', {
			params : {
				value : $scope.loginid
			}
		}).success(function(feeddata) {

			$scope.feedbacks = feeddata;

		}).error(function() {
			console.log("Error while getting the  details!!");
		});	
		
		
//		$http.get('api/feedback/generateExcel', {
//		}).success(function(response) {
	/*		console.log(response);
			 var zip = new JSZip(response);
	            var file = zip.generate({type: 'blob'});
	            saveAs(file, 'table.xlsx');
*/

//		}).error(function() {
//			console.log("Error while getting the  details!!");
//		});


	}

	
	$scope.feedbackActionUpdate=function(user){
		$scope.feedbackActionObj={};

		$scope.feedbackActionObj={feedbackId:$scope.selectedFeedback.feedbackId,action:user.action,remark:user.remark,userMsg:user.msg};
			
			
		$http.post('api/fa/resolvedFeedbackAction',$scope.feedbackActionObj).success(function(fdata) {
			
			$scope.feedback =fdata;
			$scope.user={};
			
	
		}).error(function()
		{
			console.log("Error while getting the  details!!");
		});

		
		 setTimeout(function(){
			window.location="#/viewMyNotif";

			 }, 2400);
	};
	
	
	
	
	
	$scope.takeActionOnNotification=function(index){
		
		$rootScope.selectedFeedback=$scope.feedbacks[index];
		console.log($rootScope.selectedFeedback);
		
		$http.get('api/getEmpAndVendorName',{ params :{eId:$scope.feedbacks[index].empId,vId:$scope.feedbacks[index].vendorId}}).success(
				function(empVendorName) {

					
					$scope.names=empVendorName[0];
					$rootScope.empName=$scope.names[0];
					$rootScope.vendorName=$scope.names[1];
					
					console.log($scope.vendorName=$scope.names[1]);
					
			//		$('#myModal1').modal('show');

					
				}).error(function() {
			console.log("Error while getting FeedbackAction Details!!");
		});
		
		setTimeout(function(){
				window.location="#/feedbackActionForm";
			
		}, 2200);
		
		
	};
	

	
	$(function(){
	    $('#myModal').on('show.bs.modal', function(){
	        var myModal = $(this);
	        clearTimeout(myModal.data('hideInterval'));
	        myModal.data('hideInterval', setTimeout(function(){
	            myModal.modal('hide');
	        }, 2000));
	    });
  	});

	});	
		
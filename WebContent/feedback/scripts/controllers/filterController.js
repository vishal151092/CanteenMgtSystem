app.controller("FeedbackCtrl",function($scope,$rootScope,$timeout,$http){
	$scope.feedbacks={};

	$scope.acceptRemark='';
	init();
	
	function init(){
	
		$http.get('api/feedback/getAllFeedback').success(function(fbdata) {
			
			$scope.feedbacks = fbdata;

			$scope.size=$scope.feedbacks.length;
			
		}).error(function()
		{
			console.log("Error while getting the feedback details!!");
		});
		
	}
	
	$(function(){
	    $('#myModal1').on('show.bs.modal', function(){
	        var myModal1 = $(this);
	        clearTimeout(myModal1.data('hideInterval'));
	        myModal1.data('hideInterval', setTimeout(function(){
	            myModal1.modal('hide');
	        }, 2000));
	    });
	});
	
	
	$scope.accept=function(rmk)
	{
		
	if($scope.chkBoxFeedbackActn==true){	
	
		$http.get('api/feedback/accept',{params:{feedbackId:$scope.feedbacks[$scope.index].feedbackId,remark:''}}).success(function(acceptdata) {
		
		console.log("accept successful...");
		$('#myModal1').modal('show');
		init();
	}).error(function()
	{
		console.log("Error while storing accept details!!");
	});
	}
	else{
		$scope.reject($scope.acceptRemark);
		$scope.setStatus("Closed");
	}
	
	};
	
	$scope.rejectClicked=function(index){
		$scope.index=index;
	};
	
	
	$scope.reject=function(rmk){
		$scope.remarks=rmk;
		
		$http.get('api/feedback/reject',{params:{feedbackId:$scope.feedbacks[$scope.index].feedbackId,remark:$scope.remarks}}).success(function(rejectdata) {
		$scope.fbremark='';
		init();
	}).error(function()
	{
		
		console.log("Error while storing reject details!!");
	});
	};
	
	$scope.clearRemark=function(){
		
		$scope.remark='';
	};

	
	$scope.setStatus=function(stat){
		$http.get('api/feedback/setStatus',{params:{feedbackId:$scope.feedbacks[$scope.index].feedbackId,status:stat}}).success(function(rejectdata) {
		init();
	}).error(function()
	{
		
		console.log("Error while storing reject details!!");
	});
	};
	
	
	
	
	
	
});

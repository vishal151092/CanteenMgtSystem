app.controller("FeedbackController",function($scope,$rootScope,$http,$window){

	init();

	function init() {
		
		$http.get('api/fb/getVendor').success(function(vendorList) {
			
			$scope.vendorList=vendorList;

		}).error(function()
		{
			console.log("Error while getting the  details!!");
		});
		
	}

	$scope.addFeedback=function(user){
		user.empId=$window.sessionStorage.getItem("empId");
		
	/*	alasql('SELECT * INTO XLSX("john.xlsx",{headers:true}) FROM ?',[$scope.vendorList]);
	*/		
		
		$http.post('api/fb/addFeedback', user).success(function(fdata) {

			$scope.feedback =fdata;
			
		}).error(function()
		{
			console.log("Error while getting the  details!!");
		});
		
		 setTimeout(function(){
				window.location="#/getMyFeedback";
				
		 }, 2400);
		
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
	
	
	$scope.CheckCat=function(val){
		 var element=document.getElementById('vendor');
		 if(val=='Select category'||val=='Others')
		   element.style.display='none';
		 else  
		   element.style.display='block';
		};
	
	
	
	});	
		
app.controller("VndrCntrl",function($scope,$rootScope,$location,$timeout,$http,$window){
	
	init();
	
	function init()
	{
		$http.get('api/getAllItemsWithRating').success(function(menList) {
			$rootScope.menuListwithvendor = menList;

		}).error(function() {
			console.log("Error while getting menus");
		});
	}
	$scope.addMenu=function()
	{

		$http.get('api/AddToDB').success(function(string) {
		
			console.log("success from add menu");
			$scope.modalMsg=string;
	    	 $('#myModals').modal('show');
			
			})
		.error(function(string)
		{
			/*//console.log("Mismatched column name or column values.");
			$scope.modalMsg="Mismatched column name or column values.";
	    	 $('#myModals').modal('show');*/
		});
		
	},
	
	 $(function() {
			$('#myModal').on('show.bs.modal', function() {
				var myModal = $(this);
				clearTimeout(myModal.data('hideInterval'));
				myModal.data('hideInterval', setTimeout(function() {
					myModal.modal('hide');
				}, 3000));
			});
		});
	
	 $(function() {
			$('#myModals').on('show.bs.modal', function() {
				var myModal = $(this);
				clearTimeout(myModal.data('hideInterval'));
				myModal.data('hideInterval', setTimeout(function() {
					myModal.modal('hide');
				}, 3000));
			});
		});
	
	 /*$(function() {
			$('#myModale').on('show.bs.modal', function() {
				var myModal = $(this);
				clearTimeout(myModal.data('hideInterval'));
				myModal.data('hideInterval', setTimeout(function() {
					myModal.modal('hide');
				}, 3000));
			});
		});*/
	 
	 $(function() {
			$('#myModal1').on('show.bs.modal', function() {
				var myModal = $(this);
				clearTimeout(myModal.data('hideInterval'));
				myModal.data('hideInterval', setTimeout(function() {
					myModal.modal('hide');
				}, 3000));
			});
		});
	
	$scope.continueFileUpload=function (){
		var uploadUrl="api/continueFileUpload";
		var formData=new FormData();
		formData.append("file",file.files[0]);
		 $http({
		        method: 'POST',
		        url: uploadUrl,
		        headers: {'Content-Type': undefined},
		        data: formData,
		        transformRequest: function(data, headersGetterFunction) {
		                        return data;
		         }
		     })
		    .success(function(data, status) { 
		    	
		    	 console.log("success"+status);
		    	 $scope.modalMsg="File uploaded successfully.";
		    	 $('#myModal').modal('show');
		        // alert("Successful uploaded the file");
		    	 $scope.addMenu();
		         
		     })
		    .error(function()
		    		{
		    	console.log("error"+status);
		    	 $scope.modalMsg="Please select valid file format.";
		    	 $('#myModal1').modal('show');
		    	 	//alert("Please Enter Valid File");
		    		});

		};
		
});
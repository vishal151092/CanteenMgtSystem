app.controller('adminController',function($scope,$rootScope,$window,$timeout,$filter,$http,$location){
	
	
init();


function init(){
	$scope.status=['Active','Inactive'];
	$http.get('api/admin/getAllVendor').success(function(vendata) {
		
		$scope.vendors=vendata;
	});
	
}
	

$scope.addVendor=function(ven){

	
		$http.post('api/admin/addVendor',ven).success(function(bol) {
			
			  $scope.ven={};
			  	
			//$('#myModal2').modal('show');	
		});
		$timeout(function() {
			window.location="#/viewAllVendor";
//			$('#myModal2').modal('hide');
		    },4000);
};

$(function() {
	$('#myModal2').on('show.bs.modal', function() {
		var myModal = $(this);
		clearTimeout(myModal.data('hideInterval'));
		myModal.data('hideInterval', setTimeout(function() {
			myModal.modal('hide');
		}, 2000));
	});
});

$scope.updateVendor=function($index,ven)
{
		
	$scope.venUpdated={vendorId:ven.vendorId,firstName:ven.firstName,lastName:ven.lastName,address:ven.address,email:ven.email,contactNumber:ven.contactNumber,vendorName:ven.vendorName,status:ven.status};
		
	$http.post("api/admin/updateVendor",$scope.venUpdated).success(function(vendata){


		$scope.vendors[$index].isEditVisible = false;
	
		
		
	}).error(function(){
		console.log("Error while updating");
	});
	
}; 
});
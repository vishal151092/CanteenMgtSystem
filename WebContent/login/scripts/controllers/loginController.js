app.controller("Ctrl",function($scope,$rootScope,$http,$location,$window){
	
	
//	console.log('inside1');
//	alert("Inside login");
	$rootScope.disabled=false;
	$rootScope.cartList=[];
	$rootScope.totalprice=0;
	$rootScope.count=0;
//	$rootScope.tabs=[{name:'Home',url:'#/Home'},{name:'Login',url:'#/Login'}];
	window.onload = function() {
		 var pwd = document.getElementById('pwd');
		 pwd.onpaste = function(e) {
		   e.preventDefault();
		 };
		};
	$rootScope.login={
			  id:'',
			  password:''
	  };
	
		$rootScope.f=function(login)     
		{
			
			$http.post("api/login/checkLogin",login).success(function(loginDetail){
				
				console.log(loginDetail);
			
				$rootScope.role=loginDetail.role;
				console.log($rootScope.role);
				
				if($rootScope.role=="admin"){

			  $http.get("api/login/getAdminDetail",{params:{adminId:loginDetail.id}}).success(function(adminDetails){
					    console.log("hi.....admin.....");
						console.log(adminDetails);
						$window.sessionStorage.setItem("adminId",adminDetails.adminId);
						$window.sessionStorage.setItem("id",adminDetails.adminId);
						$window.sessionStorage.setItem("fName",adminDetails.firstName);
						$window.sessionStorage.setItem("lName",adminDetails.lastName);
						$rootScope.lName=$window.sessionStorage.getItem("lName");
						$rootScope.fName=$window.sessionStorage.getItem("fName");
						console.log($window.sessionStorage.getItem("id"));
						console.log($rootScope.lName);
						
						console.log($window.sessionStorage.getItem("adminId"));
						
					});	
					
	/*     $rootScope.tabs=[ {name:'Add Vendor',url:'#/addToVendor'},
	                                 {name:'View Vendor',url:'#/viewAllVendor'},
	                                 {name:'Filter Feedback',url:'#/moderatorFeedback'},
	                                 {name:'Assigned Feedbacks',url:'#/viewMyNotif'},
	                                 {name:'All Feedback',url:'#/allFeedbacks'}
	                                 ];

   	       $rootScope.disabled=true;
*/		    
			  
			  $rootScope.tabs=[ {name:'Home',url:'#/welcome'},
			                    {name:'Menu',url:'#/menu'},
			                    {name:'Add Menu',url:'#/vendorView'},
				                {name:'View Menu',url:'#/viewMenu'},
			                    {name:'Add Vendor',url:'#/addToVendor'},
	                            {name:'View Vendor',url:'#/viewAllVendor'},
	                            {name:'New Feedback',url:'#/moderatorFeedback'},
	                            {name:'Worklist',url:'#/viewMyNotif'},
	                            {name:'Feedback Log',url:'#/allFeedbacks'}, 
	                            {name:'Logout',url:'#/Logout'}
	                            ];

	        window.location="#/welcome";
				}
				

				else if($rootScope.role=="employee"){
					
					$http.get("api/login/getEmployeeDetail",{params:{employeeId:loginDetail.id}}).success(function(employeeDetails){
					    console.log("hi...employee.......");
						console.log(employeeDetails);
						
						$window.sessionStorage.setItem("empId",employeeDetails.employeeId);
						$window.sessionStorage.setItem("id",employeeDetails.employeeId);
						$window.sessionStorage.setItem("fName",employeeDetails.firstName);
						$window.sessionStorage.setItem("lName",employeeDetails.lastName);
					
						$rootScope.lName=$window.sessionStorage.getItem("lName");
						$rootScope.fName=$window.sessionStorage.getItem("fName");
					
						console.log("id : "+$window.sessionStorage.getItem("id"));
						
						console.log("empid : "+$window.sessionStorage.getItem("empId"));
/*				       
						$rootScope.tabs=[{name:'Home',url:'#/menu'},
		                      			{name:'My Cart',url:'#/cart'},
		                                 {name:'Give Feedback',url:'#/addfeedback'},
		                                 {name:'View Status',url:'#/getMyFeedback'},
		                                 ];

				       $rootScope.disabled=true;			
*/			    
						
						$rootScope.tabs=[ {name:'Home',url:'#/welcome'},
						                    {name:'Menu',url:'#/menu'},
				               		      {name:'Give Feedback',url:'#/addfeedback'},
				                          {name:'View Status',url:'#/getMyFeedback'},
				                          {name:'Logout',url:'#/Logout'}
				                          ];

						
				       window.location="#/welcome";
			
						
					});
		     					
				}
				
				/*else if($rootScope.role=="moderator" ){
					
					$http.get("api/login/getEmployeeDetail",{params:{employeeId:loginDetail.id}}).success(function(employeeDetails){
					    console.log("hi........moderator..");
						console.log(employeeDetails);
						$window.sessionStorage.setItem("modId",employeeDetails.employeeId);
						$window.sessionStorage.setItem("id",employeeDetails.employeeId);
						$window.sessionStorage.setItem("fName",employeeDetails.firstName);
						$window.sessionStorage.setItem("lName",employeeDetails.lastName);
						$rootScope.lName=$window.sessionStorage.getItem("lName");
						$rootScope.fName=$window.sessionStorage.getItem("fName");
						$rootScope.tabs=[{name:'Home',url:'#/menu'},
			                      			{name:'My Cart',url:'#/cart'},
			                                 {name:'Give Feedback',url:'#/addfeedback'},
			                                 {name:'View Status',url:'#/getMyFeedback'},
			                                 {name:'New Feedbacks',url:'#/moderatorFeedback'},
			                                 ];

					       $rootScope.disabled=true;			
				    
					       window.location="#/menu";
						
					});
		     					
				}*/
				
				else if($rootScope.role=="cmt"){
					
					$http.get("api/login/getEmployeeDetail",{params:{employeeId:loginDetail.id}}).success(function(employeeDetails){
					    console.log("hi........cmt..");
						console.log(employeeDetails);
						$window.sessionStorage.setItem("cmtId",employeeDetails.employeeId);
						$window.sessionStorage.setItem("id",employeeDetails.employeeId);
						$window.sessionStorage.setItem("fName",employeeDetails.firstName);
						$window.sessionStorage.setItem("lName",employeeDetails.lastName);
						$rootScope.lName=$window.sessionStorage.getItem("lName");
						$rootScope.fName=$window.sessionStorage.getItem("fName");
/*						$rootScope.tabs=[{name:'Home',url:'#/menu'},
			                                 {name:'Give Feedback',url:'#/addfeedback'},
			                                 {name:'View Status',url:'#/getMyFeedback'},
			                                 {name:'All Feedback',url:'#/allFeedbacks'}
			                                 ];

					       $rootScope.disabled=true;	
					       		
*/				    
						$rootScope.tabs=[ {name:'Home',url:'#/welcome'},
						                    {name:'Menu',url:'#/menu'},
				                          {name:'Give Feedback',url:'#/addfeedback'},
				                          {name:'View Status',url:'#/getMyFeedback'},
				                          {name:'Feedback Log',url:'#/allFeedbacks'},
				                          {name:'Logout',url:'#/Logout'}
				                          ];

					       window.location="#/welcome";
				
						
					});
		     					
				}
				
				
				/*else	if($rootScope.role=="vendor"){
			
					$http.get("api/login/getVendorDetail",{params:{vendorId:loginDetail.id}}).success(function(vendorDetails){
					    console.log("hi....vendor......");
						console.log(vendorDetails);
						
						
						$window.sessionStorage.setItem("vendorId",vendorDetails.vendorId);
				       	$window.sessionStorage.setItem("id",vendorDetails.vendorId);
						$window.sessionStorage.setItem("fName",vendorDetails.firstName);
						$window.sessionStorage.setItem("lName",vendorDetails.lastName);
						$rootScope.lName=$window.sessionStorage.getItem("lName");
						$rootScope.fName=$window.sessionStorage.getItem("fName");
					
						console.log($window.sessionStorage.getItem("vendorId"));
						
				       $rootScope.tabs=[{name:'Home',url:'#/vendorView'},
		                      			
		                                 {name:'View Menu',url:'#/viewMenu'}
		                                 {name:'All Feedback',url:'#/allValidFeedbacks'},
		                                 {name:'Assigned Feedbacks',url:'#/viewMyNotif'}
		                                 ];

				       $rootScope.disabled=true;			
			    
				       window.location="#/vendorView";

					
					});
		    
		 					
				}	*/
				
				
			}).error(function(){
                   //alert("sorry wrong credentials....");
              	 // angular.element(document.getElementById("wrongCredentials"))[0].disabled=false;
			     $scope.wrongCredentials=true;
			});
				
				
		};
		
		
		
		$rootScope.setCurrentTab=function(){
			
			$rootScope.currentTab=true;
			
		};
});

app.controller("menuController", function($scope, $rootScope, $timeout, $http,
		$location, $window) {

	
	init();
	function init() {
		
		$rootScope.averageList=[];
		$rootScope.vendorList = [];
		

		$rootScope.days=['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'];
		/*$rootScope.times=['Breakfast','Lunch','Dinner','Evening Snacks','Sundry'];*/
		$rootScope.categorys=['Vegetarian','Non-Vegetarian'];

		$http.get('api/getAllItemsWithRating').success(function(menList) {
			console.log(menList);
			$rootScope.menuList = menList;
		}).error(function() {
			console.log("Error while getting menus");
		});
	
		$http.get('api/getVendorName').success(function(venList) {
			console.log(venList);
			$rootScope.vendors = venList;
		}).error(function() {
			console.log("Error while getting vendor name");
		});

		
		$http.get('api/getTypeName').success(function(typeList) {
			$rootScope.times = typeList;

		}).error(function() {
			console.log("Error while getting types");
		});
		
		};
		
	
		/*$http.get('api/getMenuByVendor',{params:{vid:$window.sessionStorage.getItem("vendorId")}}).success(function(menList) {
			$rootScope.vendorMenu = menList;
		
		/*$http.get('api/getMenuByVendor',{params:{vid:$window.sessionStorage.getItem("vendorId")}}).success(function(menList) {
			$rootScope.vendorMenu = menList;

		}).error(function() {
			console.log("Error while getting menus");
		});*/
		
		/*$http.get('api/getAllVendor').success(function(venList) {
			$rootScope.vendorList = venList;

		}).error(function() {
			console.log("Error while getting vendor");
		});*/
		
		

		
		$scope.rating = 0;
		
	 $scope.getSelectedRating = function (proid,rating) { 
		 console.log(rating);
		 var eid=null;
		 if($window.sessionStorage.getItem("empId")!=null)
			 {
			 eid=$window.sessionStorage.getItem("empId");
			 }
		 else if($window.sessionStorage.getItem("adminId")!=null)
			 {
			 eid=$window.sessionStorage.getItem("adminId");
			 }
		 else if($window.sessionStorage.getItem("cmtId")!=null)
			 {
			 eid=$window.sessionStorage.getItem("cmtId");
			 }
	        $http.get('api/rating',{params:{proid:proid, eid:eid, rate:rating}}).success(function(menList)
					{
	        	$('#ratingModal').modal('show');
				//alert("success");
				$rootScope.menuList = menList;
				//console.log($scope.rate);
			}).error(function()
			{
				console.log("Error while saving stars!!");
			});
	        
	    };
	    $(function() {
			$('#ratingModal').on('show.bs.modal', function() {
				var myModal = $(this);
				clearTimeout(myModal.data('hideInterval'));
				myModal.data('hideInterval', setTimeout(function() {
					myModal.modal('hide');
				}, 3000));
			});
		});

	/*$scope.getMenuByVendor = function(vid) {
		//alert(vid);
		// $scope.listVeg=[];
		// $scope.listNonVeg=[];
		$scope.bend = vid;
		$http.get('api/getMenuByVendor', {
			params : {
				vid : vid
			}
		}).success(function(catList) {
	
			$rootScope.menuList = catList;

			 * angular.forEach($rootScope.menuList, function(value,key){
			 * if(value.category==="veg"){ $scope.listVeg.push(value); } else
			 * if(value.category==="non-veg"){ $scope.listNonVeg.push(value); }
			 * 
			 * });
			 
		}).error(function() {
			console.log("Error while getting menus");
		});

	};*/

	/*$scope.getMenuByCategory = function(category) {
		console.log(category);
		$http.get('api/getMenuByCategory', {
			params : {
				category : category
			}
		}).success(function(catList) {
			$rootScope.menuList = catList;

		}).error(function() {
			console.log("Error while getting category");
		});

	};*/

	/*$scope.getMenuByType = function(type) {
		console.log(type);
		
		if(type==='All')
			{
			$scope.getAllMenus();
			}
		$http.get('api/getMenuByType', {
			params : {
				type : type
			}
		}).success(function(typeList) {

			$rootScope.menuList = typeList;

		}).error(function() {
			console.log("Error while getting type");
		});

	};*/
		
	/*$scope.getMenuByDays = function(day) {
		console.log(day);
		$http.get('api/getMenuByDays', {
			params : {
				day : day
			}
		}).success(function(dayList) {

			$rootScope.menuList = dayList;

		}).error(function() {
			console.log("Error while getting days");
		});

	};*/
  
});



app.directive('starRating', function () {
    return {
        restrict: 'A',
        template: '<ul class="rating">' +
            '<li ng-repeat="star in stars" ng-class="star" ng-click="toggle($index)">' +
            '\u2605' +
            '</li>' +
            '</ul>',
        scope: {
            ratingValue: '=',
            max: '=',
            onRatingSelected: '&'
        },
        link: function (scope, elem, attrs) {

            var updateStars = function () {
                scope.stars = [];
                for (var i = 0; i < scope.max; i++) {
                    scope.stars.push({
                        filled: i < scope.ratingValue
                    });
                }
            };

            scope.toggle = function (index) {
                scope.ratingValue = index + 1;
                scope.onRatingSelected({
                    rating: index + 1
                });
            };

            scope.$watch('ratingValue', function (oldVal, newVal) {
                if (newVal) {
                    updateStars();
                }
            });
        }
    };
});


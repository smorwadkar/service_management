var helloApp = angular.module("helloApp", []);

var HttpController = function($scope, $http) {

	$scope.userRequests = "";
	$scope.FullRequestDetails = "";
    $scope.categoryForm = false;
	$scope.authenticate = function () {
        
		method = "POST";
		url = 'http://localhost:8080/backend-service-management/api/userLogin?userMobileNo='
				+ $scope.user.userMobileNo
				+ '&password='
				+ $scope.user.password;

		$http({
			method : method,
			url : url,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(_success, _error);
	};
    
    $scope.getRequests = function() {
		method = "GET";
		url = 'http://localhost:8080/backend-service-management//api/request/allRequestsForUser?userMobileNo='
				+ $scope.user.userMobileNo;
        $scope.categoryForm = false;
		$http({
			method : method,
			url : url,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(requests_success, requests_error);
	};
    
    $scope.showCategoryForm = function(){
        $scope.categoryForm = true;
        $scope.userRequests = null;
        $scope.FullRequestDetails = null;
    };

	function _success(response) {
		$scope.autheticatedUser = response.data;
		//getRequests();
	}

	function _error(response) {
		$scope.message = response.error;
	}
    
    $scope.hideHomePage = function(){
        return 'null' != $scope.userRequests || $scope.showCategoryForm;
    };
    
	function requests_success(response) {
		$scope.userRequests = response.data;
	}

	function requests_error(response) {
		$scope.message = response.error;
	}

	$scope.requestDetails = function(requestId) {
		console.log("Request Detail : " + requestId);
		getComplaintDetails(requestId);
	};

	var getComplaintDetails = function(requestId) {
		console.log("Getting Complaint Details of ID : " + requestId);
		
		// Get Request Details by ID
		method = "GET";
		url = 'http://localhost:8080/backend-service-management/api/request/requestDetailsById?requestId='
				+ requestId;
		
		$http({
			method : method,
			url : url,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(complaintById_success, complaintById_error);

		
		// Get Request Statuses
		method = "GET";
		url = 'http://localhost:8080/backend-service-management/api/request/requestStatuses';

		$http({
			method : method,
			url : url,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(complaintStatus_success, complaintStatus_error);
				
	};

	function complaintById_success(response) {
		$scope.FullRequestDetails = response.data;
		$scope.selectedProductCategory = $scope.FullRequestDetails.fullProductDetails.productCategory.categoryName;
		$scope.selectedProductSubCategory = $scope.FullRequestDetails.fullProductDetails.productSubCategory.subCategoryName;
		$scope.selectedProductType = $scope.FullRequestDetails.fullProductDetails.productType.typeName;
		$scope.selectedComplaintStatus = $scope.FullRequestDetails.complaint.complaintStatus;
	}

	function complaintById_error(response) {
		$scope.message = response.error;
	}
	
	function complaintStatus_success(response) {
		$scope.complaintStatuses = response.data;
	}

	function complaintStatus_error(response) {
		$scope.message = response.error;
	}

	$scope.changeRequestStatus = function(requestId,selectedComplaintStatus) {
		console.log("Changing Request Status for request : " + requestId);
		$scope.selectedComplaintStatus = selectedComplaintStatus;
		userRequests = $scope.userRequests;
		for (i = 0; i < userRequests.length; i++) {
				if(userRequests[i].requestId == requestId){
					$scope.userRequests[i].complaint.complaintStatus = selectedComplaintStatus;
				}
		}
		
		method = "POST";
		url = 'http://localhost:8080/backend-service-management/api/request/updateRequest?requestId='
				+ requestId
				+ '&complaintStatus='
				+ selectedComplaintStatus;

		$http({
			method : method,
			url : url,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(updateComplaintById_success, updateComplaintById_error);
	};

	function updateComplaintById_success(response) {
		console.log("Changed Request Status for request : " + requestId);
	}

	function updateComplaintById_error(response) {
		$scope.message = response.error;
	}
    
    
    //Add Product category
    $scope.addProductCategory = function(){
        $scope.productCategoryNameVar = 'Shivdatta';
        console.log('Product Category : '+ $scope.productCategoryName);
        var productCategoryDetails = {
            productCategoryName : $scope.productCategoryName
        };
        method = "POST";
		url = 'http://localhost:8080/backend-service-management/api/product/addProductCategory';

		$http({
			method : method,
			url : url,
            data: JSON.stringify(productCategoryDetails),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(addProductCategory_success, addProductCategory_error);
    };
    
    function addProductCategory_success(response) {
		console.log("Added Product Category successfully");
	}

	function addProductCategory_error(response) {
		$scope.message = response.error;
	}
};


helloApp.controller('HttpController', HttpController);
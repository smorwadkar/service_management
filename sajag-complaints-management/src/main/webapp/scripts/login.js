var helloApp = angular.module("helloApp",[]);

var HttpController = function($scope,$http){
                							
                $scope.user = {
                            'userMobileNo' : $scope.username,
							 'password' : $scope.password 
                }
                 
                $scope.authenticate = function(){
                    method = "POST";  
                    url = 'http://localhost:8080/backend-service-management/api/userLogin';  
                       
                $http({  
                    method : method,  
                    url : url,  
                    data : angular.toJson($scope.user),
                    headers : {  
                        'Content-Type' : 'application/json'  
                    }  
                }).then( _success, _error );
                };
                
                function _success(response) {  
                    $scope.message = response.data;
                }  
           
                function _error(response) {  
                    $scope.message = response.error;
                }  

};

helloApp.controller('HttpController',HttpController);
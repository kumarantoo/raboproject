/** CONTROLLER UPLOAD FILE - Kumaran**/
mainApp.controller('UploadFileController', function($scope, $http) {

	$scope.uploadResult ="";
	$scope.myForm = {
			description: "",
			files: []
	}
	$scope.doUploadFile = function() {  
		var url = "/";
		var data = new FormData();
		data.append("description", $scope.myForm.description);
		for (i = 0; i < $scope.myForm.files.length; i++) {
			data.append("file", $scope.myForm.files[i]);
		}
		var config = {
				transformRequest: angular.identity,
				transformResponse: angular.identity,
				headers: {
					'Content-Type': undefined
				}
		}
		$http.post(url, data, config).then(
				function(response) {
					$scope.successFull = true;
					$scope.successMessage = 'Upload SuccessFull!!';
					$scope.uploadResult =  response;
			});
	};

});
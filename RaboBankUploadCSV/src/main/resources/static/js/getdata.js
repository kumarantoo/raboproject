mainApp.controller('getcontroller', function($scope, $http, $location) {

	$scope.getfunction = function(){
		var url = $location.absUrl() + "/getFiles.json";
		$http.get(url).then(function (response) {
			$scope.getDivAvailable = true;
			$scope.response = response.data;
		}, function error(response) {
			$scope.postResultMessage = "Error Status: " +  response.statusText;
		});
		$scope.headers = Object.keys($scope.response[0]);
		$scope.changeFilterTo = function(pr) {
			if ($scope.search) {
				for (var key in $scope.search) {
					if ($scope.search[key]) {
						$scope.search[key] = undefined;
					}
				}
			}
		}
	}
});

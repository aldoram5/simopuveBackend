angular.module('simopuveApp')
// We are adding a function called Ctrl1
// to the module we got in the line above
.controller('ReportsCtrl', reportsCtrl);

reportsCtrl.$inject = ['$scope', '$http'];

function reportsCtrl($scope, $http){
    $scope.startDate = new Date();
    $scope.endDate = new Date();
    
    
}



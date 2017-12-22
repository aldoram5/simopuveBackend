angular.module('simopuveApp')
        .controller('ReportsCtrl', reportsCtrl);

reportsCtrl.$inject = ['$scope', '$http', '$mdDialog'];

function reportsCtrl($scope, $http, $mdDialog) {
    var alert;
    $scope.startDate = new Date();
    $scope.endDate = new Date();
    $scope.showLoadingIndicator = false;

    $scope.generateReports = function () {
    $scope.showLoadingIndicator = true;
    var startDateString = ($scope.startDate.getDate()+1)+'/'+$scope.startDate.getMonth()+'/'+$scope.startDate.getFullYear();
    var endDateString = ($scope.endDate.getDate() + 1)+'/'+$scope.endDate.getMonth()+'/'+$scope.endDate.getFullYear();
    console.log(startDateString);
    console.log(endDateString);
//    $http.get('http://drivechile.dynu.net/simopuve/rest/api/reports?from=' + startDateString + '&to=' + endDateString)
    $http.get('./rest/api/reports?from=' + startDateString + '&to=' + endDateString)
                .then(function (data) {
                    $scope.showLoadingIndicator = false;
                    $scope.showAlert("Exito", "El reporte se ha generado exitosamente");
                }, function (data) {
                    console.log("Error");
                    $scope.showLoadingIndicator = false;
                    $scope.showAlert("Error", "Ocurrio un error al procesar tu solicitud");
                });
    };
    $scope.showAlert = function (title, message) {
        alert = $mdDialog.alert({
            title: title,
            textContent: message,
            ok: 'OK'
        });

        $mdDialog
                .show(alert)
                .finally(function () {
                    alert = undefined;
                });
    };


}



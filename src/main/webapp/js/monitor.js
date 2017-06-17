angular.module('simopuveApp')
        .controller('MonitorCtrl', monitorCtrl);

monitorCtrl.$inject = ['$scope', '$http', '$mdDialog'];

function monitorCtrl($scope, $http, $mdDialog) {
    var alert;
    $scope.showLoadingIndicator = false;
    $scope.statuses = [];

    $scope.init = function () {
        var socket = new WebSocket("ws://drivechile.dynu.net/simopuve/monitor");
        socket.onmessage = function (event) {
            var status = JSON.parse(event.data);
            var updated = false;
            var arrayLength = $scope.statuses.length;
            for (var i = 0; i < arrayLength; i++) {
                if($scope.statuses[i].hashId === status.hashId){
                   $scope.statuses[i] = status; 
                   updated=true;
                }
                
            }
            if(updated === false){
                $scope.statuses.push(status);
            }
            
            $scope.drawStatusesToMap();
        };


    };


    $scope.drawStatusesToMap = function () {
        //$scope.showLoadingIndicator = true;
        console.log($scope.statuses);
        //TODO Do GMaps stuff 
        
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


    $scope.init();

}


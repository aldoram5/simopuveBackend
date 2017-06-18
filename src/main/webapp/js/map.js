angular.module('simopuveApp')
        .controller('MapController', mapCtrl);

mapCtrl.$inject = ['$scope', '$window'];

function mapCtrl($scope, $window) {

    $scope.init = function () {
        $window.map = new google.maps.Map(document.getElementById('map'), {
            center: {
                lat: -34.397,
                lng: 150.644
            },
            zoom: 8
        });
    };


    $scope.init();

}


angular.module('simopuveApp')
        .controller('MonitorCtrl', monitorCtrl);

monitorCtrl.$inject = ['$scope', '$http', '$mdDialog'];

function monitorCtrl($scope, $http, $mdDialog) {
    var alert;
    $scope.showLoadingIndicator = false;
    $scope.statuses = [];

    var cities = [
        {
            city: 'Toronto',
            desc: 'This is the best city in the world!',
            lat: 43.7000,
            long: -79.4000
        },
        {
            city: 'New York',
            desc: 'This city is aiiiiite!',
            lat: 40.6700,
            long: -73.9400
        },
        {
            city: 'Chicago',
            desc: 'This is the second best city in the world!',
            lat: 41.8819,
            long: -87.6278
        },
        {
            city: 'Los Angeles',
            desc: 'This city is live!',
            lat: 34.0500,
            long: -118.2500
        },
        {
            city: 'Las Vegas',
            desc: 'Sin City...\'nuff said!',
            lat: 36.0800,
            long: -115.1522
        }
    ];
    var mapOptions = {
        zoom: 4,
        center: new google.maps.LatLng(-33.447487, -70.673676),
        mapTypeId: google.maps.MapTypeId.TERRAIN
    }
    $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);

    $scope.markers = [];

    var infoWindow = new google.maps.InfoWindow();

    var createMarker = function (info) {

        var marker = new google.maps.Marker({
            map: $scope.map,
            position: new google.maps.LatLng(info.latitude, info.longitude),
            title: info.pointOfSaleAssigned
        });
        marker.content = '<div class="infoWindowContent">' + info.pointOfSaleAssigned + '</div>';

        google.maps.event.addListener(marker, 'click', function () {
            infoWindow.setContent('<h2>' + marker.title + '</h2>' + marker.content);
            infoWindow.open($scope.map, marker);
        });

        $scope.markers.push(marker);

    }

    $scope.statuses.forEach(function (status) {
        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(status.latitude, status.longitude),
            title: status.pointOfSaleAssigned,
            map: $scope.map
        });
        var infowindow = new google.maps.InfoWindow({
            content: '<div class="infoWindowContent">' + status.pointOfSaleAssigned + '</div>'
        });

        marker.addListener('click', function () {
            infowindow.open(map, marker);
        });

    });

    function ponlos(){
        for (i = 0; i < $scope.statuses.length; i++) {
            createMarker($scope.statuses[i]);
        }
    }


    $scope.openInfoWindow = function (e, selectedMarker) {
        e.preventDefault();
        google.maps.event.trigger(selectedMarker, 'click');
    }

    $scope.init = function () {
        var socket = new WebSocket("ws://drivechile.dynu.net/simopuve/monitor");
        socket.onmessage = function (event) {
            var status = JSON.parse(event.data);
            var updated = false;
            var arrayLength = $scope.statuses.length;
            for (var i = 0; i < arrayLength; i++) {
                if ($scope.statuses[i].hashId === status.hashId) {
                    $scope.statuses[i] = status;
                    updated = true;
                }

            }
            if (updated === false) {
                $scope.statuses.push(status);
            }

            $scope.drawStatusesToMap();
        };


    };


    $scope.drawStatusesToMap = function () {
        //$scope.showLoadingIndicator = true;
        console.log($scope.statuses);
        ponlos();
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


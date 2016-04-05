(function () {

    var injectParams = ['$http', '$q'];

    var profileService = function ($http, q) {
        var serviceBase = '/spring-jwt/api/profile/', factory = {};

        factory.getProfileById = function (id) {
            return $http.get(serviceBase + 'get/' + id).then(
                function (httpResponse) {
                    return httpResponse.data;
                });
        };

        return factory;
    };

    profileService.$inject = injectParams;

    angular.module('jwtApp').factory('profileService', profileService);

}());


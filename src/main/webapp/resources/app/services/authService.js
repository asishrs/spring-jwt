(function () {

    var injectParams = ['$http', '$rootScope', '$cookieStore'];

    var authFactory = function ($http, $rootScope, $cookieStore) {
        var serviceBase = '/spring-jwt/api/',
            factory = {
                loginPath: '/login',
                user: {
                    isAuthenticated: false,
                    roles: null
                }
            };

        factory.login = function (email, password) {
            return $http.post(serviceBase + 'login', {"userName": email, "password": password }).then(
                function (httpResponse) {
                    $cookieStore.put("token", httpResponse.headers('X-AUTH-TOKEN'));
                    var loggedIn = httpResponse.data.status;
                    changeAuth(loggedIn);
                    return loggedIn;
                });
        };

        factory.logout = function () {
            return $http.get(serviceBase + 'logout').then(
                function (results) {
                    $cookieStore.remove("token");
                    var loggedIn = !results.data.status;
                    changeAuth(loggedIn);
                    return loggedIn;
                });
        };

        factory.redirectToLogin = function () {
            $rootScope.$broadcast('redirectToLogin', null);
        };

        function changeAuth(loggedIn) {
            factory.user.isAuthenticated = loggedIn;
            $rootScope.$broadcast('loginStatusChanged', loggedIn);
        }

        return factory;
    };

    authFactory.$inject = injectParams;

    angular.module('jwtApp').factory('authService', authFactory);

}());


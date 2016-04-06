(function () {

    angular.module('jwtApp')
        .config(['$httpProvider', function ($httpProvider) {

        var injectParams = ['$q', '$rootScope', '$cookieStore', 'config'];

        var httpInterceptorToken = function ($q, $rootScope, $cookieStore, config) {
            var req = function (config) {
                config.headers = config.headers || {};
                if ($cookieStore.token) {
                    config.headers['X-AUTH-TOKEN'] = $cookies.token;
                }
                return config;
            };

            return req;
        };

        httpInterceptorToken.$inject = injectParams;

        $httpProvider.interceptors.push(httpInterceptorToken);

    }]);

}());
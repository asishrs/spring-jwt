(function () {

    angular.module('jwtApp')
        .config(['$httpProvider', function ($httpProvider) {

        var injectParams = ['$q', '$rootScope', '$cookieStore'];

        var httpInterceptor401 = function ($q, $rootScope, $cookieStore) {
            return {
                request: function (config) {
                  config.headers = config.headers || {};
                  if ($cookieStore.get('token')) {
                        config.headers['X-AUTH-TOKEN'] = $cookieStore.get('token');
                  }
                  return config;
                },

                response: function (response) {
                  if (response.status === 401) {
                    $rootScope.$broadcast('redirectToLogin', null);
                    return $q.reject(response);
                  }

                  if (response.status === 200) {
                    return response;
                  }
                  return $q.reject(response);
                }
            }

        };

        httpInterceptor401.$inject = injectParams;

        $httpProvider.interceptors.push(httpInterceptor401);

    }]);

}());
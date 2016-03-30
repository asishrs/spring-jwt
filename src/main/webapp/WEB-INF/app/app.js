(function () {

    var app = angular.module('jwtApp',
        ['ngRoute', 'ngAnimate', 'ui.bootstrap']);

    app.config(['$routeProvider', function ($routeProvider) {
        var viewBase = '/app/jwtapp/views/';

        $routeProvider
            .when('/admin', {
                controller: 'AdminController',
                templateUrl: viewBase + 'admin/admin.html',
                controllerAs: 'adminVM'
            })
            .when('/about', {
                controller: 'AboutController',
                templateUrl: viewBase + 'about.html',
                controllerAs: 'aboutVM'
            })
            .when('/login/:redirect*?', {
                controller: 'LoginController',
                templateUrl: viewBase + 'login.html',
                controllerAs: 'loginVM'
            })
            .otherwise({ redirectTo: '/admin' });

    }]);

    app.run(['$rootScope', '$location', 'authService',
        function ($rootScope, $location, authService) {
            
            //Client-side security. Server-side framework MUST add it's 
            //own security as well since client-based security is easily hacked
            $rootScope.$on("$routeChangeStart", function (event, next, current) {
                if (next && next.$$route && next.$$route.secure) {
                    if (!authService.user.isAuthenticated) {
                        $rootScope.$evalAsync(function () {
                            authService.redirectToLogin();
                        });
                    }
                }
            });

    }]);

}());


(function () {

    var app = angular.module('jwtApp',
        ['ngRoute', 'ngAnimate', 'ngCookies', 'wc.directives', 'ui.bootstrap']);

    app.config(['$routeProvider', function ($routeProvider) {
        var viewBase = '/spring-jwt/';

        $routeProvider
            .when('/profile', {
                controller: 'ProfileController',
                templateUrl: viewBase + 'profile.html',
                controllerAs: 'vm',
                secure: true
            })
            .when('/about', {
                controller: 'AboutController',
                templateUrl: viewBase + 'about.html',
                controllerAs: 'vm'
            })
            .when('/login/:redirect*?', {
                controller: 'LoginController',
                templateUrl: viewBase + 'login.html',
                controllerAs: 'vm'
            })
            .otherwise({ redirectTo: '/about' });

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


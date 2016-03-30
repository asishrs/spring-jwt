(function () {

    var injectParams = ['config', 'customersService', 'customersBreezeService'];

    var dataService = function (config, customersService, customersBreezeService) {
        return (config.useBreeze) ? customersBreezeService : customersService;
    };

    dataService.$inject = injectParams;

    angular.module('jwtApp').factory('dataService', dataService);

}());


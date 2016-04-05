(function () {

    var injectParams = ['profileService'];

    var ProfileController = function (profileService) {

        var vm = this;

        function init() {
            getProfileById('1001');
        }

        function getProfileById(id) {
            profileService.getProfileById(id)
                .then(function (data) {
                    vm.profile = data;
                    console.log("Profile is " + profile);
                    $timeout(function () {
                        vm.cardAnimationClass = ''; //Turn off animation since it won't keep up with filtering
                    }, 1000);

                }, function (error) {
                    $window.alert('Sorry, an error occurred: ' + error.data.message);
                });
        }

        init();

    };

    ProfileController.$inject = injectParams;

    angular.module('jwtApp').controller('ProfileController', ProfileController);

}());
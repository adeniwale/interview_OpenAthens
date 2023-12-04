'use strict';

var registrationApp = angular.module('registrationApp', []);

registrationApp.directive('iframe', function($window, $parse) {
	return {
		restrict: "E",
		require: '?ngModel',
		link: function(scope, element, attrs, ngModelCtrl) {
			if(ngModelCtrl) {
				var getter = $parse(attrs['ngModel']);
				var setter = getter.assign;

				$window.addEventListener('message', function(event){
					if (typeof(event.data) !== 'undefined'){
						scope.$apply(setter(scope, event.data))
					}
				});
			}
		}
	}
});

registrationApp.controller('formCtrl', ['$scope', function($scope) {
	$scope.messages = [];

	$scope.register = function($event) {
		localStorage.setItem('name', $scope.registrationForm.name.$viewValue);
		$scope.messages = [];
		if ($scope.registrationForm.email.$invalid)
			$scope.messages.push('You must provide a valid email address');
		if ($scope.registrationForm.url.$invalid)
			$scope.messages.push('You must provide a valid web address');
		if (typeof $scope.registrationForm.jobInterests.$viewValue === 'undefined') {
			$scope.messages.push('You must provide at least one job interest.');
			$scope.registrationForm.$valid = false;
		} else {
			$scope.registrationForm.$valid = true;
		}
		if (typeof $scope.registrationForm.password.$viewValue === 'undefined' || $scope.registrationForm.password.$viewValue.length < 8)
			$scope.messages.push('Your password must be longer than 8 characters');
		if ($scope.registrationForm.password.$viewValue !== $scope.registrationForm.confirmPassword.$viewValue) {
			$scope.messages.push('Your passwords did not match');
			$scope.registrationForm.$valid = false;
		}
		console.log($scope.registrationForm)
		if (!$scope.registrationForm.$valid)
			$event.preventDefault();
	}
}]);

registrationApp.controller('dashboardCtrl', ['$scope', function($scope) {
	$scope.welcomeMessage = 'Welcome!';
	if (typeof localStorage.getItem('name') === 'string' && localStorage.getItem('name') !== 'undefined')
		$scope.welcomeMessage = 'Welcome ' + localStorage.getItem('name') + '!';

	$scope.jobs = [
		{title: "Graduate Software Developer", href: "#", type: "Graduate job", typeClass: "label-primary", location: "North West England", closingDate: "Continuous recruitment"},
		{title: "Systems Engineer", href: "#", type: "Graduate scheme", typeClass: "label-info", location: "Various", closingDate: "Continuous recruitment"},
		{title: "Physics Programmer", href: "#", type: "Graduate job", typeClass: "label-primary", location: "Edinburgh", closingDate: "29/10/2013"},
		{title: "Cyber Security Practitioner", href: "#", type: "Law training contract", typeClass: "label-warning", location: "Various", closingDate: "Continuous recruitment"},
		{title: "Research & Development Engineer", href: "#", type: "Graduate job", typeClass: "label-primary", location: "Various", closingDate: "5/11/2013"}
	];
}]);


var app = angular.module('frame', [])

app.directive('childFrame', function($window, $parse) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            
            ngModelCtrl.$formatters.push(function(modelValue) {
                parent.postMessage(modelValue, "*")
            })
        }
    }
});
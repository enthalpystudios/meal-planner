(function(angular) {
  var WeekMenuFactory = function($resource) {
    return $resource('/weekMenus/:id', {
      id: '@id'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  };

  WeekMenuFactory.$inject = ['$resource'];
  angular.module("mealPlannerApp.services").factory("WeekMenu", WeekMenuFactory);
}(angular));
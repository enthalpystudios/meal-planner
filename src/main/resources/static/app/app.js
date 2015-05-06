(function(angular) {
  angular.module("mealPlannerApp.controllers", []);
  angular.module("mealPlannerApp.services", []);
angular.module("mealPlannerApp", ["ngResource", "ui.bootstrap.datetimepicker", "mealPlannerApp.controllers", "mealPlannerApp.services"]);
}(angular));
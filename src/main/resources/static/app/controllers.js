(function(angular) {
  var WeekMenuController = function($scope, WeekMenu) {

    WeekMenu.query(function(response) {
      $scope.weekMenus = response ? response : [];
    });

    $scope.addWeekMenu = function(newWeekMenu) {
      new WeekMenu({
        startDate: newWeekMenu.startDate,
        endDate: newWeekMenu.endDate
      }).$save(function(weekMenu) {
        $scope.weekMenus.push(weekMenu);
      });

      $scope.newWeekMenu = {};

    };

    $scope.updateWeekMenu = function(weekMenu) {
      weekMenu.$update();
    };

    $scope.deleteWeekMenu = function(weekMenu) {
      weekMenu.$remove(function() {
        $scope.weekMenus.splice($scope.weekMenus.indexOf(weekMenu), 1);
      });
    };
  };

  WeekMenuController.$inject = ['$scope', 'WeekMenu'];
  angular.module("mealPlannerApp.controllers").controller("WeekMenuController", WeekMenuController);
}(angular));
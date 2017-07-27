'use strict';
var app= angular.module("app",["ngRoute"]);
app.config(['$routeProvider',function ($routeProvider){

   $routeProvider
              .when("/processMaster", {
                        templateUrl : "/processMaster.html",
                        controller:"processMasterController"
                  })


            }]);
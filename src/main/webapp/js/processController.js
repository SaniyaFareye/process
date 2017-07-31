angular.module('app')
.controller("processController", function($scope,$http){

            $scope.draft={
                show : 'view',
                processMasterId : -1
            }


            var init=function(){
                                 view();
                                 }

                                 init();

                                function view(){
                                var url="/process/all";
                                $http.get(url).then(function(response){
                                $scope.process=response.data;
                                  });
                                }

             $scope.render=function(id){
             $scope.draft.processMasterId=id;
              var url="/job/all";
             $http.get(url).then(function(response){
             $scope.proc=response.data;
             });

             $scope.add=function(){
                     $scope.showModal = true;
             }
             $scope.cancel = function() {
               $scope.showModal = false;
             };

}


});
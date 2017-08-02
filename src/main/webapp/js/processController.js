angular.module('app')
.controller("processController", function($scope,$http){
            $scope.action=["Success","Failure"];
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
             viewjob();

            }
              function viewjob(){
                var url="/job/all";
                             $http.get(url).then(function(response){
                             $scope.proc=response.data;
                             });
              }

            $scope.addProc=function(processId,addInfo){

            $http({
            "url" : "/job/currentFlow/",
            "method" : "POST",
            "params" : {'processMasterID':processId,'crnNo':addInfo.crnNo}
            }).
            then(function(response){
                    console.log(response.data);
                    viewjob();
                    $scope.draft.show="add";
                });
            }


            $scope.success=function(crn){

            }

             $scope.fail=function(crn){

            }


});
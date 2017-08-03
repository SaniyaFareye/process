angular.module('app')
.controller("processController", function($scope,$http){
            $scope.datepicker = {date: Date.UTC(1986, 1, 22)};
//            $('.datepick').datepicker({
//                format: 'yyyy-mm-dd',
//
//            });
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

              $http({
                          "url" : "/process/codeList/",
                          "method" : "POST",
                          "params" : {'processMasterID':id}
                          }).
                          then(function(response){
                           $scope.codeList=response.data;

                          });
             viewjob(id);

            }
              function viewjob(id){

              $http({
                          "url" : "/job/all/",
                          "method" : "POST",
                          "params" : {'processMasterID':id}
                          }).
                          then(function(response){
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
                    viewjob(processId);
                    $scope.draft.show="add";
                });
            }


            $scope.success=function(crn){
            $http({
                        "url" : "/job/nexState/",
                        "method" : "POST",
                        "params" : {'crnNo':crn,'status':"success"}
                        }).
                        then(function(response){
                                console.log(response.data);
                                viewjob($scope.draft.processMasterId);
                                $scope.draft.show="add";
                            });

            }

             $scope.fail=function(crn){
             $http({
                        "url" : "/job/nexState/",
                        "method" : "POST",
                        "params" : {'crnNo':crn,'status':"fail"}
                        }).
                        then(function(response){
                                console.log(response.data);
                                viewjob($scope.draft.processMasterId);
                                $scope.draft.show="add";
                            });

            }


});
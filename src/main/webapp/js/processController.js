angular.module('app')
.controller("processController", function($scope,$http,$timeout){
            $scope.var1 = '12-07-2013';
            $scope.action=["Success","Failure"];
            $scope.dataFilter='false';
            $scope.draft={
                show : 'view',
                processMasterId : -1
            }
            var today=new Date();


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
              $scope.fil

              ter={
              startDate: today.toISOString().substr(0,10);

                         }
                         console.log($scope.filter.startDate);
             $(function () {
                           $("#datepicker").datepicker({
                                 autoclose: true,
                                 todayHighlight: true
                           }).on('changeDate', function(event) {
                                 $scope.filter.startDate=event.format();
                             });
                            });

            $(function () {
                           $("#datepick").datepicker({
                                 autoclose: true,
                                 todayHighlight: true
                           }).on('changeDate', function(event) {
                                 $scope.endDate=event.format();
                             });
                            });
             $scope.currFlow=null;


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

            $scope.search=function(fil){
            console.log($scope.startDate);
            console.log($scope.endDate);
            console.log($scope.currFlow);

            $http({
                          "url" : "/process/filterData/",
                          "method" : "POST",
                          "params" : {'processMasterID':$scope.draft.processMasterId,'startDate':$scope.startDate,
                                       'endDate':$scope.filter.endDate, 'currentFlow':filter.currentFlow}
                          }).
                          then(function(response){
                           $scope.filerData=response.data;


                          });
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
angular.module('app')
.controller("processMasterController", function($scope,$http){
        $scope.task=["Task","Condition"];

        $scope.choiceSet = {choices: []};
        var processMap={};

            $scope.choiceSet.choices = [{
                'type':'Task',
                'title':'Start',
                'code':'start'
            },
            {
                            'type':'Task',
                            'title':'End',
                            'code':'end'

                        }];
            $scope.addProcess = function () {
                $scope.choiceSet.choices.push('');
                console.log( $scope.choiceSet.choices);
            };

            $scope.removeProcess = function (z) {
                //var lastItem = $scope.choiceSet.choices.length - 1;
                $scope.choiceSet.choices.splice(z,1);
            };

            $scope.submitProcess=function(processInfo){
                for(var i=0;i<$scope.choiceSet.choices.length;i++){
                    if($scope.choiceSet.choices[i].type=='Task'){
                       $scope.choiceSet.choices[i].nextFalseCondition=null;
                       $scope.choiceSet.choices[i].conditionExpression=null;
                    }
                    processMap[$scope.choiceSet.choices[i].code]={};
                    processMap[$scope.choiceSet.choices[i].code]=$scope.choiceSet.choices[i];

                }

                processInfo.process=processMap;
                    $http.post("/process/add",processInfo).then(function(response){
                        console.log(response.data);
                    });
            }

});
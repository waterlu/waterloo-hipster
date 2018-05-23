'use strict';

angular.module('app')
    .run(['$rootScope', '$state', '$stateParams',
        function ($rootScope, $state, $stateParams) {
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
        }
    ])
    .config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/app/project');
            $stateProvider
                .state('app', {
                    abstract: true,
                    url: '/app',
                    templateUrl: 'tpl/app.html'
                })
                .state('app.project', {
                    url: '/project',
                    templateUrl: 'tpl/project.html',
                    controller: 'ProjectController'
                })
                // .state('app.object', {
                //     url: '/object',
                //     templateUrl: 'tpl/object.html',
                //     controller: 'ObjectController'
                // })
                // .state('app.database', {
                //     url: '/database',
                //     templateUrl: 'tpl/database.html',
                //     controller: 'DatabaseController'
                // })
        }
    ]);
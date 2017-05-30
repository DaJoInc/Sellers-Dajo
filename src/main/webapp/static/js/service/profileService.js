/**
 * Created by JoDa on 29/05/2017.
 */

'use strict';

angular.module('myApp').factory('ProfileService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/user/';
    var REST_SERVICE_URI_PRO = 'http://localhost:8080/user/';
    var REST_SERVICE_URI_PROF = 'http://localhost:8080/profile/';
    var REST_SERVICE_URI_PROD = 'http://localhost:8080/product/';

    var factory = {
        fetchUser: fetchUser,
        fetchAllProfi: fetchAllProfi,
        fetchAllProduct: fetchAllProduct,
        createUser: createUser,
        updateUser:updateUser,
        deleteUser:deleteUser
    };

    return factory;

    function fetchAllProduct( ) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_PROD)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
    function fetchAllProfi( ) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_PROF)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchUser(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_PRO+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createUser(user) {
        var deferred = $q.defer();
        console.log('create');
        $http.post(REST_SERVICE_URI, user)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
    function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, user)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while updating User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteUser(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);

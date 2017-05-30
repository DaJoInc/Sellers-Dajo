
/**
 * Created by JoDa on 29/05/2017.
 */
'use strict';

angular.module('myApp').factory('ProductService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/product/';

    var factory = {
        fetchAllProducts: fetchAllProducts,
        createProduct: createProduct,
        updateProduct:updateProduct,
        deleteProduct:deleteProduct
    };

    return factory;

    function fetchAllProducts() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    console.log(response.data);
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Products');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createProduct(product) {
        var deferred = $q.defer();
        console.log('create');
        $http.post(REST_SERVICE_URI, product)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating Products');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function updateProduct(product, id) {
        console.log('entroooo!')
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, product)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while updating Products');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteProduct(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Products');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);

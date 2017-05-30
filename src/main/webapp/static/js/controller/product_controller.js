/**
 * Created by JoDa on 29/05/2017.
 */
'use strict';
angular.module('myApp').controller('ProductController', ['$scope', 'ProductService','fileUpload', function($scope, ProductService,FileUpload) {
    var self = this;
    self.prduct={id:null,code_product:'',des_product:'',image_product:'',pathImage_product:'',type_product:''};
    self.prducts=[];
    self.equiva={id:null,type:'',selected: true};
    self.equivau=[];
    self.upda=0;

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.isChecked=isChecked;
    self.sync=sync;
    self.subm=subm;

    fetchAllPrducts();
    function fetchAllPrducts(){
        console.log(FileUpload);
        console.log(ProductService);
        ProductService.fetchAllProducts()
            .then(
                function(d) {
                    console.log('res');
                    console.log(d);
                    self.prducts = d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function subm(myFile,name,date) {
            var file = myFile;
            var name = name;
            var date = date;
            console.log('entrosuv');
            console.log(myFile);
            console.log(name);
            console.log(date);
            console.log(FileUpload)
            console.log('file is ' + JSON.stringify(file));
            var uploadUrl = "/product/upload";
            FileUpload.uploadFileToUrl(uploadUrl, file, name, date);
    }

    function isChecked (id){
        var match = false;
        for(var i=0 ; i < self.equivau.length; i++) {
            if(self.equivau[i].id!==null ) {
                if (self.equivau[i].id == id) {
                    match = true;
                }
            }
        }
        return match;
    };

    function sync(bool, item){
        if (self.upda==1){
            self.equivau=[];
            self.upda++;
        }
        if(bool){
            self.equivau.push(item);
        } else {
            for(var i=0 ; i < self.equivau.length; i++) {
                if(self.equivau[i].id == item.id){
                    self.equivau.splice(i,1);
                }
            }
            console.log('f'+self.equivau);
        }
    };

    function createProduct(product){
        var d = new Date();
        var datestring =  d.getFullYear()+ "-" + (d.getMonth()+1)+"-"+d.getDate() ;
        console.log('Hola')
        console.log(product)
        subm(product.image_product ,product.code_product,datestring);
        product.image_product=product.code_product;
        ProductService.createProduct(product)
            .then(
                fetchAllPrducts,
                function(errResponse){
                    console.error('Error while creating User');
                }
            );
    }
    function updateProduct(product, id){
        self.upda=0;
        console.log(product);
        var d = new Date();
        var datestring =  d.getFullYear()+ "-" + (d.getMonth()+1)+"-"+d.getDate() ;
        console.log('Hola')
        console.log(product)
        subm(product.image_product ,product.code_product,datestring);
        product.image_product=product.code_product;
        ProductService.updateProduct(product, id)
            .then(
                fetchAllPrducts,
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
    }

    function deleteProduct(id){
        ProductService.deleteProduct(id)
            .then(
                fetchAllPrducts,
                function(errResponse){
                    console.error('Error while deleting User');
                }
            );
    }

    function submit() {
        if(self.prduct.id===null){
            console.log('Saving New prduct', self.prduct);
            createProduct(self.prduct);
        }else{
            console.log('hola');
            console.log(self.prduct);
            console.log(self.prduct.id);
            updateProduct(self.prduct, self.prduct.id);
            console.log('Prduct updated with id ', self.prduct.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        self.equivau=[];
        for(var i = 0; i < self.prducts.length; i++){
            console.log(self.prducts[i]);
            if(self.prducts[i].id === id) {
                self.prduct = angular.copy(self.prducts[i]);
                self.upda=1;
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.prduct.id === id) {
            reset();
        }
        deleteProduct(id);
    }


    function reset(){
        self.prduct={id:null,code_product:'',des_product:'',image_product:'',pathImage_product:'',type_product:''};
        $scope.myForm.$setPristine();
    }
    function sortByLabel(claims) {
        claims.sort(function(a, b) {
            var labelA = a.label.toLowerCase(), labelB = b.label.toLowerCase();
            if (labelA < labelB) // sort string ascending
                return -1;
            if (labelA > labelB)
                return 1;
            return 0; // default return value (no sorting)
        });
    }

}]);
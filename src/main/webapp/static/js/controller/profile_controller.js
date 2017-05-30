/**
 * Created by JoDa on 29/05/2017.
 */
angular.module('myApp').controller('ProfileController', ['$scope', 'ProfileService', function($scope, ProfileService) {
    var self = this;
    self.userProf={id:null,password:'',identId:'',firstName:'',lastName:'',email:'',userProfiles:[{id:null,type:''}],userProducts:[{id:null,code_product:'',des_product:'',image_product:'',pathImage_product:'',type_product:''}]};
    self.usersPro=[];
    self.equiva={id:null,type:'',selected: true};
    self.equivau=[];
    self.prod={id:null,code_product:'',des_product:'',image_product:'',pathImage_product:'',type_product:''};
    self.produ=[];
    self.upda=0;
    self.updaa=0;
    self.vas=0;

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.isChecked=isChecked;
    self.sync=sync;
    var foo = getParameterByName('_id');
    if(self.vas==0){
        fetchUser(foo);
        fetchAllProfi();
        fetchAllProduct();
        self.vas++;
    }

    function fetchAllProduct() {
        ProfileService.fetchAllProduct()
            .then(
                function (d) {
                    self.prod = d;
                    console.log(self.prod);
                },
                function(errResponse){
                    console.error('Error while fetching Profile');
                }
            );

    }

    function fetchAllProfi() {
        ProfileService.fetchAllProfi()
            .then(
                function (d) {
                    self.equiva = d;
                    console.log(self.equiva);
                },
                function(errResponse){
                    console.error('Error while fetching Profile');
                }
            );

    }
    function isChecked (id){
        var match = false;
        for(var i=0 ; i < self.produ.length; i++) {
            if(self.produ[i].id!==null ) {
                if (self.produ[i].id == id) {
                    match = true;
                }
            }
        }
        return match;
    };

    function sync(bool, item){
        if (self.upda==1){
            self.produ=[];
            self.upda++;
        }
        if(bool){
            self.produ.push(item);
        } else {
            for(var i=0 ; i < self.produ.length; i++) {
                if(self.produ[i].id == item.id){
                    self.produ.splice(i,1);
                }
            }
            console.log('mostra');
            console.log(self.produ);
        }
    };
    function getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }

    function fetchUser(id){
        ProfileService.fetchUser(id)
            .then(
                function(d) {
                    self.userProf = d;
                    console.log(self.userProf);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }
    function createUser(user){
        user.userProfiles=self.equivau;

        ProfileService.createUser(user)
            .then(
                fetchUser(1),
                function(errResponse){
                    console.error('Error while creating User');
                }
            );
    }

    function updateUser(user, id){
        user.userProducts=self.produ;
        self.upda=0;
        console.log(user.id);
        ProfileService.updateUser(user, id)
            .then(
                fetchUser(id),
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
    }

    function deleteUser(id){
        ProfileService.deleteUser(id)
            .then(
                fetchAllUsers,
                function(errResponse){
                    console.error('Error while deleting User');
                }
            );
    }

    function submit() {
        if(self.userProf.id===null){
            console.log('Saving New User', self.userProf);
            createUser(self.userProf);
        }else{
            updateUser(self.userProf, self.userProf.id);
            console.log('User updated with id ', self.userProf.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        self.equivau=[];
        for(var i = 0; i < self.userProf.length; i++){
            if(self.usersPro[i].id === id) {
                self.userProf = angular.copy(self.usersPro[i]);
                self.equivau = angular.copy(self.userProf[i].userProfiles);
                self.upda=1;
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.userProf={id:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
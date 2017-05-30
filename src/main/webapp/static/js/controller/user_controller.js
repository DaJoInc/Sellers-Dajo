/**
 * Created by JoDa on 29/05/2017.
 */
'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={id:null,password:'',identId:'',firstName:'',lastName:'',email:'',userProfiles:[{id:null,type:''}]};
    self.users=[];
    self.equiva={id:null,type:'',selected: true};
    self.equivau=[];
    self.upda=0;

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.isChecked=isChecked;
    self.sync=sync;
    self.profile=profile;

    fetchAllUsers();
    fetchAllProfile();
    function profile(id) {
        window.location.replace("http://localhost:8080/profilePro?_id="+id);
    }
    function fetchAllProfile() {
        UserService.fetchAllProfile()
            .then(
                function (d) {
                    self.equiva = d;
                },
                function(errResponse){
                console.error('Error while fetching Profile');
                }
            );
        
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

    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    function createUser(user){
        user.userProfiles=self.equivau;

        UserService.createUser(user)
            .then(
                fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }

    function updateUser(user, id){
        user.userProfiles=self.equivau;
        self.upda=0;
        console.log(user);
        UserService.updateUser(user, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        self.equivau=[];
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                self.equivau = angular.copy(self.users[i].userProfiles);
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
        self.user={id:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);

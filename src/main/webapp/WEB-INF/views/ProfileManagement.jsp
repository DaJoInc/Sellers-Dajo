<%--
  Created by IntelliJ IDEA.
  User: JoDa
  Date: 29/05/2017
  Time: 12:13 PM
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <style>
        .username.ng-valid {
            background-color: lightgreen;
        }
        .username.ng-dirty.ng-invalid-required {
            background-color: red;
        }
        .username.ng-dirty.ng-invalid-minlength {
            background-color: yellow;
        }

        .email.ng-valid {
            background-color: lightgreen;
        }
        .email.ng-dirty.ng-invalid-required {
            background-color: red;
        }
        .email.ng-dirty.ng-invalid-email {
            background-color: yellow;
        }

        .user-row {
            margin-bottom: 14px;
        }

        .user-row:last-child {
            margin-bottom: 0;
        }

        .dropdown-user {
            margin: 13px 0;
            padding: 5px;
            height: 100%;
        }

        .dropdown-user:hover {
            cursor: pointer;
        }

        .table-user-information > tbody > tr {
            border-top: 1px solid rgb(221, 221, 221);
        }

        .table-user-information > tbody > tr:first-child {
            border-top: 0;
        }


        .table-user-information > tbody > tr > td {
            border-top: 0;
        }
        .toppad
        {margin-top:20px;
        }



    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
    <div class="generic-container" ng-controller="ProfileController as ctrl1">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">
            <span class="lead">Profile </span></div>
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title" ng-bind="ctrl1.userProf.identId"> </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive"> </div>
                                        <div class=" col-md-9 col-lg-9 ">
                                            <table class="table table-user-information">
                                                <tbody>
                                                <tr>
                                                    <td>FirstName</td>
                                                    <td><span ng-bind="ctrl1.userProf.firstName"></span></td>
                                                </tr>
                                                <tr>
                                                    <td>Identification</td>
                                                    <td><span ng-bind="ctrl1.userProf.identId"></span></td>
                                                </tr>
                                                <tr>
                                                    <td>LastName</td>
                                                    <td><span ng-bind="ctrl1.userProf.lastName"></span></td>
                                                </tr>
                                                <tr>
                                                    <td>Email</td>
                                                    <td><span ng-bind="ctrl1.userProf.email"></span></td>
                                                </tr>
                                                <tr>
                                                </tr>
                                                </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <a  href="http://localhost:8080/productPro/" data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                                    <span class="pull-right">
                            <a href="http://localhost:8080" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                            <a data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
                        </span>
                                </div>

            </div>
    </div>
</div>




            </div>
        </div>
    </div>

    <div class="generic-container" ng-controller="ProfileController as ctrl">
        <div class="panel panel-default">
            <div class="panel-heading"><span class="lead">Products Employe </span></div>
            <div class="formcontainer">
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.prod.id" />

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="file">Products</label>
                            <div class="col-md-7">
                                <label ng-repeat="item in ctrl.prod" class="item" id="{{item.id}}">
                                    <br>
                                    <input type="checkbox" ng-change="ctrl.sync(bool, item)" ng-model="bool" ng-checked="ctrl.isChecked(item.id)"> {{item.code_product}}
                                    <td> <img alt="" ng-src="http://localhost:8080/product/document/{{item.image_product}}" height="80px" width="80px" /> </td>
                                    <br>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!ctrl.prod.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Users </span></div>
            <div class="tablecontainer">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID.</th>
                        <th>Name</th>
                        <th>Last Name</th>
                        <th>userProfile</th>
                        <th>Products</th>
                        <th>Email</th>
                        <th width="20%"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr >
                        <td><span ng-bind="ctrl.userProf.id"></span></td>
                        <td><span ng-bind="ctrl.userProf.firstName"></span></td>
                        <td >
                            <span ng-bind="ctrl.userProf.lastName"></span>
                        </td>
                        <td >
                            <div class="col" ng-repeat="i in ctrl.userProf.userProfiles"> {{i.type}} </div>
                        </td>
                        <td >
                            <div class="col" ng-repeat="i in ctrl.userProf.userProducts"> {{i.code_product}}
                             <img alt="" ng-src="http://localhost:8080/product/document/{{i.image_product}}" height="20px" width="20px" />
                            </div>
                        </td>
                        <td><span ng-bind="ctrl.userProf.email"></span></td>
                        <td>
                            <button type="button" ng-click="ctrl.edit(ctrl.userProf.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(ctrl.userProf.id)" class="btn btn-danger custom-width">Remove</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/profileService.js' />"></script>
<script src="<c:url value='/static/js/controller/profile_controller.js' />"></script>
</body>
</html>
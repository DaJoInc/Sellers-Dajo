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
    <title>Product</title>
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
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
<div class="generic-container" ng-controller="ProductController as ctrl">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Product Registration Form </span></div>
        <div class="formcontainer">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.prduct.id" />
                <div class="row">


                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="file">Code Product</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.prduct.code_product" name="uname" class="username form-control input-sm" placeholder="Enter your code of Product" required ng-minlength="3"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="form-group col-md-12">
                    <label class="col-md-2 control-lable" for="file">Name Product</label>
                    <div class="col-md-7">
                        <input type="text" ng-model="ctrl.prduct.type_product" name="uname" class="username form-control input-sm" placeholder="Enter your name of Product" required ng-minlength="3"/>
                        <div class="has-error" ng-show="myForm.$dirty">
                            <span ng-show="myForm.uname.$error.required">This is a required field</span>
                            <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                            <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                        </div>
                    </div>
                </div>

                <div class="form-group col-md-12">
                    <label class="col-md-2 control-lable" for="file">Description Product</label>
                    <div class="col-md-7">
                        <input type="text" ng-model="ctrl.prduct.des_product" name="uname" class="username form-control input-sm" placeholder="Enter your description of Product" required ng-minlength="3"/>
                        <div class="has-error" ng-show="myForm.$dirty">
                            <span ng-show="myForm.uname.$error.required">This is a required field</span>
                            <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                            <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                        </div>
                    </div>
                </div>

                <div class="form-group col-md-12">
                    <label class="col-md-2 control-lable" for="file">Image Product</label>
                    <div class="col-md-7">
                        <input type="file" class="custom-file-input" file-model="ctrl.prduct.image_product">
                        <div class="has-error" ng-show="myForm.$dirty">
                            <span ng-show="myForm.uname.$error.required">This is a required field</span>
                            <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                            <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                        </div>
                    </div>
                </div>

                <div class="form-group col-md-12">
                    <label class="col-md-2 control-lable" for="file">Tipo Product</label>
                    <div class="col-md-7">
                        <input type="text" ng-model="ctrl.prduct.pathImage_product" name="uname" class="username form-control input-sm" placeholder="Enter your tipo of Product" required ng-minlength="3"/>
                        <div class="has-error" ng-show="myForm.$dirty">
                            <span ng-show="myForm.uname.$error.required">This is a required field</span>
                            <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                            <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                        </div>
                    </div>
                </div>



                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="{{!ctrl.prduct.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Products </span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID.</th>
                    <th>Code of Product</th>
                    <th>Name of Product</th>
                    <th>Descrition of Product</th>
                    <th>Type of Product</th>
                    <th>Image</th>
                    <th>Options</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="u in ctrl.prducts">
                    <td><span ng-bind="u.id"></span></td>
                    <td><span ng-bind="u.code_product"></span></td>
                    <td >
                        <span ng-bind="u.type_product"></span>
                    </td>
                    <td >
                        <span ng-bind="u.des_product"></span>
                    </td>

                    <td><span ng-bind="u.pathImage_product"></span></td>
                    <td> <img alt="" ng-src="http://localhost:8080/product/document/{{u.image_product}}" height="80px" width="80px" /> </td>
                    <td>
                        <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>



<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/product_service.js' />"></script>
<script src="<c:url value='/static/js/controller/product_controller.js' />"></script>
</body>
</html>
package com.dajo.service;

import com.dajo.model.UserProduct;

import java.util.List;

/**
 * Created by JoDa on 29/05/2017.
 */
public interface UserProductService {

    UserProduct findById(int id);

    UserProduct findByCode(String code);

    List<UserProduct> findAll();

    boolean isProductExist(UserProduct user);

    void saveProduct(UserProduct user);

    void updateProduct(UserProduct user);

    void deleteUserById(int id);
}

package com.dajo.dao;

import com.dajo.model.UserProduct;
import com.dajo.model.UserProfile;

import java.util.List;

/**
 * Created by JoDa on 29/05/2017.
 */
public interface ProductDao {
    List<UserProduct> findAll();

    UserProduct findByCode(String code);

    UserProduct findById(int id);

    void save(UserProduct user);

    void deleteById (int id);

}

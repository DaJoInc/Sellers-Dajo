package com.dajo.service;

import com.dajo.dao.ProductDao;
import com.dajo.dao.UserProductDaoImpl;
import com.dajo.model.UserProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by JoDa on 29/05/2017.
 */
@Service("productService")
@Transactional
public class UserProductServiceImpl implements UserProductService {

    @Autowired
    ProductDao dao;

    @Override
    public UserProduct findById(int id) {
        return dao.findById(id);
    }

    @Override
    public UserProduct findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public List<UserProduct> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean isProductExist(UserProduct user) {
        return false;
    }

    @Override
    public void saveProduct(UserProduct user){
        dao.save(user);
    }

    @Override
    public void updateProduct(UserProduct user) {
        UserProduct entity = dao.findById(user.getId());
        if(entity!=null){
            entity.setCode_product(user.getCode_product());
            entity.setImage_product(user.getImage_product());
            entity.setPathImage_product(user.getPathImage_product());
            entity.setType_product(user.getType_product());
            entity.setDes_product(user.getDes_product());
        }
        System.out.println("acabo");
    }

    @Override
    public void deleteUserById(int id) {
        UserProduct entity = dao.findById(id);
        dao.deleteById(entity.getId());
    }
}

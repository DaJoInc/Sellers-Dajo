package com.dajo.dao;

import com.dajo.model.UserProduct;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JoDa on 29/05/2017.
 */
@Repository("userProductDao")
public class UserProductDaoImpl extends AbstractDao<Integer, UserProduct>implements ProductDao{

    public UserProduct findById(int id) {
        return getByKey(id);
    }

    @Override
    public void save(UserProduct user) {
        persist(user);
    }

    @Override
    public void deleteById(int id) {
        UserProduct product = findById(id);
        delete(product);
    }

    public UserProduct findByCode(String code) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("code_product", code));
        return (UserProduct) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<UserProduct> findAll(){
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("code_product"));
        return (List<UserProduct>)crit.list();
    }

}

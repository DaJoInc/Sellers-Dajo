package com.dajo.converter;

import com.dajo.model.UserProduct;
import com.dajo.service.UserProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by JoDa on 29/05/2017.
 */

@Component
public class ProductToUserProductsConverter implements Converter<Object, UserProduct> {

    static final Logger logger = LoggerFactory.getLogger(ProductToUserProductsConverter.class);

    @Autowired
    UserProductService userProductService;

    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public UserProduct convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        UserProduct product= userProductService.findById(id);
        logger.info("Product : {}",product);
        return product;
    }

}
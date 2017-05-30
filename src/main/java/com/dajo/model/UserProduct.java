package com.dajo.model;
/**
 * Created by JoDa on 29/05/2017.
 */
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

    @Entity
    @Table(name="USER_PRODUCTS")
    public class UserProduct implements Serializable {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Integer id;

        @NotEmpty
        @Column(name="code_product", unique=true, nullable=false)
        private String code_product;

        @NotEmpty
        @Column(name="des_product", unique=true, nullable=false)
        private String des_product;

        @NotEmpty
        @Column(name="image_product", unique=true, nullable=false)
        private String image_product;

        @NotEmpty
        @Column(name="pathImage_product", unique=true, nullable=false)
        private String pathImage_product;

        @NotEmpty
        @Column(name="type", unique=true, nullable=false)
        private String type_product;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCode_product() {
            return code_product;
        }

        public void setCode_product(String code_product) {
            this.code_product = code_product;
        }

        public String getDes_product() {
            return des_product;
        }

        public void setDes_product(String des_product) {
            this.des_product = des_product;
        }

        public String getImage_product() {
            return image_product;
        }

        public void setImage_product(String image_product) {
            this.image_product = image_product;
        }

        public String getPathImage_product() {
            return pathImage_product;
        }

        public void setPathImage_product(String pathImage_product) {
            this.pathImage_product = pathImage_product;
        }

        public String getType_product() {
            return type_product;
        }

        public void setType_product(String type_product) {
            this.type_product = type_product;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            result = prime * result + ((code_product == null) ? 0 : code_product.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof com.dajo.model.User))
                return false;
            com.dajo.model.UserProduct other = (com.dajo.model.UserProduct) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            if (code_product == null) {
                if (other.code_product != null)
                    return false;
            } else if (!code_product.equals(other.code_product))
                return false;
            return true;
        }


        @Override
        public String toString() {
            return "User [id=" + id + ", code_product=" + code_product + ", des_product=" + des_product
                    + ", image_product=" + image_product + ", pathImage=" + pathImage_product
                    + ", type=" + type_product + "]";
        }



    }


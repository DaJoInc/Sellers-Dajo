package com.dajo.controller;

import com.dajo.model.UserProduct;
import com.dajo.service.DocumentMetadata;
import com.dajo.service.IArchiveService;
import com.dajo.service.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.dajo.service.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by JoDa on 29/05/2017.
 */
@RestController
public class ProductsRestController {

    private static final Logger LOG = Logger.getLogger(ProductsRestController.class);

    @Autowired
    IArchiveService archiveService;

    @Autowired
    UserProductService productService;


    //-------------------Retrieve All Products--------------------------------------------------------

    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public ResponseEntity<List<UserProduct>> listAllUsers() {
        List<UserProduct> users = productService.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<List<UserProduct>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserProduct>>(users, HttpStatus.OK);
    }



    //-------------------Retrieve Single Product--------------------------------------------------------

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProduct> getProduct(@PathVariable("id") int id) {
        System.out.println("Fetching Product with id " + id);
        UserProduct product = productService.findById(id);
        if (product == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserProduct>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserProduct>(product, HttpStatus.OK);
    }




    //-------------------Create a Product--------------------------------------------------------

    @RequestMapping(value = "/product/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody UserProduct product, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Product " + product);
        if (productService.isProductExist(product)) {
            System.out.println("A User with name " + product.getCode_product() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        productService.saveProduct(product);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
        List<DocumentMetadata> list = new ArrayList<DocumentMetadata>();
        list= getArchiveService().findDocuments(product.getCode_product());
        product.setImage_product(list.get(0).getUuid());
        System.out.println(product);
        productService.updateProduct(product);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }



    //------------------- Update a  Product --------------------------------------------------------

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserProduct> updateUser(@PathVariable("id") int id, @RequestBody UserProduct product) {
        System.out.println("Updating User " + id);
        UserProduct currentProduct = productService.findById(id);

        if (currentProduct==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserProduct>(HttpStatus.NOT_FOUND);
        }

        currentProduct.setCode_product(product.getCode_product());
        currentProduct.setDes_product(product.getDes_product());
        currentProduct.setPathImage_product(product.getPathImage_product());
        currentProduct.setType_product(product.getType_product());
        List<DocumentMetadata> list = new ArrayList<DocumentMetadata>();
        list= getArchiveService().findDocuments(product.getCode_product());
        currentProduct.setImage_product(list.get(0).getUuid());
        System.out.println(product);
        productService.updateProduct(currentProduct);
        return new ResponseEntity<UserProduct>(currentProduct, HttpStatus.OK);
    }



    //------------------- Delete a Product --------------------------------------------------------

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserProduct> deletePoduct(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);

        UserProduct user = productService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<UserProduct>(HttpStatus.NOT_FOUND);
        }

        productService.deleteUserById(id);
        return new ResponseEntity<UserProduct>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/product/upload", method = RequestMethod.POST)
    public @ResponseBody DocumentMetadata handleFileUpload(
            @RequestParam(value="file", required=true) MultipartFile file ,
            @RequestParam(value="person", required=true) String person,
            @RequestParam(value="date", required=true) @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {

        try {
            Document document = new Document(file.getBytes(), file.getOriginalFilename(), date, person );
            getArchiveService().save(document);
            return document.getMetadata();
        } catch (RuntimeException e) {
            LOG.error("Error while uploading.", e);
            throw e;
        } catch (Exception e) {
            LOG.error("Error while uploading.", e);
            throw new RuntimeException(e);
        }
    }


    @RequestMapping(value = "/product/documents", method = RequestMethod.GET)
    public HttpEntity<List<DocumentMetadata>> findDocument(
            @RequestParam(value="person", required=false) String person,
            @RequestParam(value="date", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<List<DocumentMetadata>>(getArchiveService().findDocuments(person,date), httpHeaders,HttpStatus.OK);
    }


    @RequestMapping(value = "/product/document/{id}", method = RequestMethod.GET)
    public HttpEntity<byte[]> getDocument(@PathVariable String id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(getArchiveService().getDocumentFile(id), httpHeaders, HttpStatus.OK);
    }

    public IArchiveService getArchiveService() {
        return archiveService;
    }

    public void setArchiveService(IArchiveService archiveService) {
        this.archiveService = archiveService;
    }





}

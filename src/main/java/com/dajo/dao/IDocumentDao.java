package com.dajo.dao;
import com.dajo.service.Document;
import com.dajo.service.DocumentMetadata;

import java.util.Date;
import java.util.List;
/**
 * Created by JoDa on 29/05/2017.
 */
public interface IDocumentDao {


    void insert(Document document);



    List<DocumentMetadata> findByPersonNameDate(String personName, Date date);

    List<DocumentMetadata> findByPersonName(String personName);


    Document load(String uuid);

}

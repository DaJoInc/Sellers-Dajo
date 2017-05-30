package com.dajo.service;

/**
 * Created by JoDa on 29/05/2017.
 */
import java.util.Date;
import java.util.List;



public interface IArchiveService {


    DocumentMetadata save(Document document);

    List<DocumentMetadata> findDocuments(String personName, Date date);

    List<DocumentMetadata> findDocuments(String personName);

    byte[] getDocumentFile(String id);
}
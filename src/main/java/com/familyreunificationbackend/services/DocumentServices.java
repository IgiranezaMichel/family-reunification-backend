package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

import com.familyreunificationbackend.input.DocumentInput;
import com.familyreunificationbackend.model.Document;
import com.familyreunificationbackend.repository.DocumentRepository;

public class DocumentServices {
    @Autowired
    private DocumentRepository documentRepository;

    public ResponseEntity<String> saveOrUpdateDocument(DocumentInput document) {
        try {
            byte[] decodeDocument = Base64.getDecoder().decode(document.getBase64Document().split("base64,")[1]);
            document.setEncodingName(document.getBase64Document().split("base64,")[0] + "base64,");
            document.setFile(decodeDocument);
            Document doc = documentRepository.save(document);
            return new ResponseEntity<>(doc.getFileName() + " File has been added successful", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Unable to store uploaded file", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}

package com.cbo.riskmanagement.service;


import com.cbo.riskmanagement.model.OfacLastUpdateDate;
import com.cbo.riskmanagement.model.OfacUpdateCheck;
import com.cbo.riskmanagement.model.UNLastUpdateDate;
import com.cbo.riskmanagement.model.UNUpdateCheck;
import com.cbo.riskmanagement.repository.OfacLastUpdateDateRepository;
import com.cbo.riskmanagement.repository.OfacUpdateCheckRepository;
import com.cbo.riskmanagement.repository.UNLastUpdateDateRepository;
import com.cbo.riskmanagement.repository.UNUpdateCheckRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

;


@Service
public class UNService {
    @Autowired
    private OfacLastUpdateDateRepository ofacLastUpdateDateRepository;
    @Autowired
    private OfacUpdateCheckRepository ofacUpdateCheckRepository;
    @Autowired
    private UNLastUpdateDateRepository uNLastUpdateDateRepository;
    @Autowired
    private UNUpdateCheckRepository uNUpdateCheckRepository;

    @Transactional
    public void  checkUNUpdate ()  throws IOException, ParserConfigurationException, SAXException{
        System.out.println("update check running fine fo un");
        URL url = new URL("https://scsanctions.un.org/resources/xml/en/consolidated.xml");
        URLConnection conn = url.openConnection();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(conn.getInputStream());



        Element root = doc.getDocumentElement(); // get the root element
        String date = root.getAttribute("dateGenerated"); // get the attribute value
        UNLastUpdateDate data;

        data = uNLastUpdateDateRepository.getById(1L);
        String DateFromUpdateCheck =    data.getDate();
        if(date.equals(DateFromUpdateCheck)){

        } else {
            System.out.println("Updated UN");
            int unUpdateChecker = getUpdateCounter()  +1;
            setUpdateCounter(unUpdateChecker);
            //then update ofaclastupdatedate

            setLastUpdateDate(date);
        }



    }
    public void setLastUpdateDate(String  newDate) {
        Optional<UNLastUpdateDate> optionalUpdateCheck = uNLastUpdateDateRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            UNLastUpdateDate updateCheck = optionalUpdateCheck.get();
            updateCheck.setDate(newDate);
            uNLastUpdateDateRepository.save(updateCheck);



        } else {
            throw new RuntimeException("No UN UpdateCheck found");
        }

    }

    public int  getUpdateCounter() {
        Optional<UNUpdateCheck> optionalUpdateCheck = uNUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            UNUpdateCheck updateCheck = optionalUpdateCheck.get();
            return updateCheck.getUpdateCheck();
        } else {
            throw new RuntimeException("No Un UpdateCheck found");
        }

    }

    public void setUpdateCounter(int newUpdateCounter) {
        Optional<UNUpdateCheck> optionalUpdateCheck = uNUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            UNUpdateCheck updateCheck = optionalUpdateCheck.get();
            updateCheck.setUpdateCheck(newUpdateCounter);
            uNUpdateCheckRepository.save(updateCheck);



        } else {
            throw new RuntimeException("No OfacUpdateCheck found");
        }

    }








}

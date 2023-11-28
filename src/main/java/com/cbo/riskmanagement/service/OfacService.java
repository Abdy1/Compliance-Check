package com.cbo.riskmanagement.service;



import com.cbo.riskmanagement.model.DeliquentUpdateCheck;
import com.cbo.riskmanagement.model.OfacLastUpdateDate;
import com.cbo.riskmanagement.model.OfacUpdateCheck;
import com.cbo.riskmanagement.model.UNLastUpdateDate;
import com.cbo.riskmanagement.repository.OfacLastUpdateDateRepository;;
import com.cbo.riskmanagement.repository.OfacUpdateCheckRepository;
import com.cbo.riskmanagement.repository.UNLastUpdateDateRepository;
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


@Service
public class OfacService {
  @Autowired
  OfacLastUpdateDateRepository ofacLastUpdateDateRepository;
    @Autowired
    private OfacUpdateCheckRepository ofacUpdateCheckRepository;
    @Autowired
    private UNLastUpdateDateRepository uNLastUpdateDateRepository;

    @Transactional
    public void  checkOfacUpdate ()  throws IOException, ParserConfigurationException, SAXException{
        System.out.println("update check running fine for ofac");
        URL url = new URL("https://www.treasury.gov/ofac/downloads/sdn.xml");
        URLConnection conn = url.openConnection();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(conn.getInputStream());
        NodeList publishDateList = doc.getElementsByTagName("Publish_Date");
        OfacLastUpdateDate  data;
        if (publishDateList.getLength() > 0) {
            Element publishDateElement = (Element) publishDateList.item(0);
            String publishDate = publishDateElement.getTextContent();
            data = ofacLastUpdateDateRepository.getById(1L);
            String DateFromUpdateCheck =    data.getDate();
            if(publishDate.equals(DateFromUpdateCheck)){

            } else {
                int OfacUpdateChecker = getUpdateCounter()  +1;
                setUpdateCounter(OfacUpdateChecker);
                //then update ofaclastupdatedate
                setLastUpdateDate(publishDate);
                System.out.println("Updated Ofac");
            }

        }

    }


    public void setLastUpdateDate(String  newDate) {
        Optional<OfacLastUpdateDate> optionalUpdateCheck = ofacLastUpdateDateRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            OfacLastUpdateDate updateCheck = optionalUpdateCheck.get();
            updateCheck.setDate(newDate);
            ofacLastUpdateDateRepository.save(updateCheck);



        } else {
            throw new RuntimeException("No OfacUpdateCheck found");
        }

    }

    public int  getUpdateCounter() {
        Optional<OfacUpdateCheck> optionalUpdateCheck = ofacUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            OfacUpdateCheck updateCheck = optionalUpdateCheck.get();
            return updateCheck.getUpdateCheck();
        } else {
            throw new RuntimeException("No OfacUpdateCheck found");
        }

    }

    public void setUpdateCounter(int newUpdateCounter) {
        Optional<OfacUpdateCheck> optionalUpdateCheck = ofacUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            OfacUpdateCheck updateCheck = optionalUpdateCheck.get();
            updateCheck.setUpdateCheck(newUpdateCounter);
            ofacUpdateCheckRepository.save(updateCheck);



        } else {
            throw new RuntimeException("No OfacUpdateCheck found");
        }

    }








}

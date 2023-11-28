package com.cbo.riskmanagement.service;

import com.cbo.riskmanagement.model.DataModel;
import com.cbo.riskmanagement.model.UKResponseDetail;
import com.cbo.riskmanagement.model.adverser.Adverser;
import com.cbo.riskmanagement.model.adverser.AdverserResponseDetail;
import com.cbo.riskmanagement.model.eu.*;
import com.cbo.riskmanagement.model.nbeblacklist.NbeBacklist;
import com.cbo.riskmanagement.model.pep.PepList;
import com.cbo.riskmanagement.model.pep.PepResponseDetail;
import com.cbo.riskmanagement.model.uk.*;
import com.cbo.riskmanagement.model.un.*;
import com.cbo.riskmanagement.model.us.OfacSanction;
import com.cbo.riskmanagement.repository.*;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import com.cbo.riskmanagement.model.un.UNSanctions;
import com.cbo.riskmanagement.repository.SanctionRepository;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import java.io.*;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    SanctionRepository sr;

    public void importUnSanctions(){

        File file = new File("C:\\Users\\Abdyf\\Documents\\Sanctions\\filee.xml");

        try {
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            Document doc = db.parse(file);
            NodeList individualNode = doc.getElementsByTagName("INDIVIDUAL");
            NodeList entityNode = doc.getElementsByTagName("ENTITY");


            // Sanctions llogs = new Sanctions();
            // iterate the individuals
            for (int i = 0; i < individualNode.getLength(); i++) {
                UNSanctions sanction = new UNSanctions();

                Element element = (Element) individualNode.item(i);

                NodeList dataId = element.getElementsByTagName("DATAID");
                Element line = (Element) dataId.item(0);
                if(dataId.item(0) != null){

                    sanction.setDataId(getCharacterDataFromElement(line));
                }

                String FullName = "";
                NodeList firstName = element.getElementsByTagName("FIRST_NAME");
                if(firstName.item(0) != null){
                    line = (Element) firstName.item(0);

                    FullName = FullName + " " +getCharacterDataFromElement(line);

                    sanction.setFirstName(getCharacterDataFromElement(line));
                }


                NodeList secondName = element.getElementsByTagName("SECOND_NAME");
                if(secondName.item(0) != null){
                    line = (Element) secondName.item(0);
                    FullName = FullName + " " + getCharacterDataFromElement(line);

                    sanction.setSecondName(getCharacterDataFromElement(line));

                }

                sanction.setFullName(FullName);

                NodeList listType = element.getElementsByTagName("UN_LIST_TYPE");
                if(secondName.item(0) != null){
                    line = (Element) listType.item(0);

                    sanction.setType(getCharacterDataFromElement(line));
                }

                NodeList referenceNumber = element.getElementsByTagName("REFERENCE_NUMBER");
                if(referenceNumber.item(0) != null){
                    line = (Element) referenceNumber.item(0);

                    sanction.setReferenceNumber(getCharacterDataFromElement(line));
                }

                NodeList listedOn = element.getElementsByTagName("LISTED_ON");
                if(listedOn.item(0) != null){
                    line = (Element) listedOn.item(0);

                    sanction.setListedOn(getCharacterDataFromElement(line));
                }

                NodeList comments1 = element.getElementsByTagName("COMMENTS1");
                if(comments1.item(0) != null){
                    line = (Element) comments1.item(0);

                    sanction.setComment(getCharacterDataFromElement(line));
                }

                //to access all children of the node

                //THIS SOMETIMES SHOWS COMMENTS VALUE AND SOMETIMES DONT'T SHOW ATT ALL
                //SO LETS CHANGE IT


                if(element.getElementsByTagName("DESIGNATION").item(0) != null){
                    NodeList designationValue = element.getElementsByTagName("DESIGNATION").item(0).getChildNodes();
                    String Designation = "";
                    for(int j=0;j<designationValue.getLength();j++) {
                        if(designationValue.item(j).getNodeType()== Node.ELEMENT_NODE) {
                            Element child = (Element)designationValue.item(j);
                            Designation = Designation + "\n -> " +child.getTextContent();
                        }
                    }

                    sanction.setDesignation(Designation);
                }

                //to access all children of the node
                if(element.getElementsByTagName("NATIONALITY").item(0) != null){
                    NodeList nationalityValue = element.getElementsByTagName("NATIONALITY").item(0).getChildNodes();
                    String Nationality = "";
                    for(int j=0;j<nationalityValue.getLength();j++) {
                        if(nationalityValue.item(j).getNodeType()== Node.ELEMENT_NODE) {
                            Element child = (Element)nationalityValue.item(j);
                            Nationality = Nationality + "\n -> " +child.getTextContent();
                        }
                    }

                    sanction.setNationality(Nationality);

                }

                //to access all children of the node
                if(element.getElementsByTagName("LIST_TYPE").item(0) != null){
                    NodeList listTypeValue = element.getElementsByTagName("LIST_TYPE").item(0).getChildNodes();
                    String ListTypeValue = "";
                    for(int j=0;j<listTypeValue.getLength();j++) {
                        if(listTypeValue.item(j).getNodeType()== Node.ELEMENT_NODE) {
                            Element child = (Element)listTypeValue.item(j);
                            ListTypeValue = child.getTextContent();
                        }
                    }

                    sanction.setListType(ListTypeValue);
                }

                //to access all children of the node
                if(element.getElementsByTagName("LAST_DAY_UPDATED").item(0) != null){
                    NodeList lastDayUpdated = element.getElementsByTagName("LAST_DAY_UPDATED").item(0).getChildNodes();
                    String LastDayUpdated = "";
                    for(int j=0;j<lastDayUpdated.getLength();j++) {
                        if(lastDayUpdated.item(j).getNodeType()== Node.ELEMENT_NODE) {
                            Element child = (Element)lastDayUpdated.item(j);
                            LastDayUpdated = LastDayUpdated + "\n -> " +child.getTextContent();
                        }
                    }

                    sanction.setLastDayUpdated(LastDayUpdated);
                }


                NodeList aliasNode = element.getElementsByTagName("INDIVIDUAL_ALIAS");
                String  AliasName = "";
                for (int j = 0; j < aliasNode.getLength(); j++) {
                    Element aliasElement = (Element) aliasNode.item(j);


                    NodeList quality = aliasElement.getElementsByTagName("QUALITY");
                    NodeList aliasName = aliasElement.getElementsByTagName("ALIAS_NAME");

                    if(quality.item(0) != null){
                        line = (Element) quality.item(0);
                        AliasName = AliasName + "\n  -> Quality: " + getCharacterDataFromElement(line);

                    }


                    if(aliasName.item(0) != null){
                        line = (Element) aliasName.item(0);
                        AliasName = AliasName + "\n  -> Alias Name: " + getCharacterDataFromElement(line);
                    }


                }

                sanction.setAlias(AliasName);



                NodeList individualAddressNode = element.getElementsByTagName("INDIVIDUAL_ADDRESS");
                String  individualAddress = "";
                for (int j = 0; j < individualAddressNode.getLength(); j++) {
                    Element individualAddressElement = (Element) individualAddressNode.item(j);


                    NodeList street = individualAddressElement.getElementsByTagName("STREET");
                    NodeList city = individualAddressElement.getElementsByTagName("CITY");
                    NodeList country = individualAddressElement.getElementsByTagName("COUNTRY");
                    NodeList stateProvince = individualAddressElement.getElementsByTagName("STATE_PROVINCE");
                    NodeList addressNote = individualAddressElement.getElementsByTagName("NOTE");
                    NodeList zipCode = individualAddressElement.getElementsByTagName("ZIP_CODE");



                    if(street.item(0) != null){
                        line = (Element) street.item(0);
                        individualAddress = individualAddress + "\n  -> Street: " + getCharacterDataFromElement(line);

                    }

                    if(city.item(0) != null){
                        line = (Element) city.item(0);
                        individualAddress = individualAddress + "\n  -> City: " + getCharacterDataFromElement(line);

                    }


                    if(country.item(0) != null){
                        line = (Element) country.item(0);
                        individualAddress = individualAddress + "\n  -> Country: " + getCharacterDataFromElement(line);

                    }

                    if(stateProvince.item(0) != null){
                        line = (Element) stateProvince.item(0);
                        individualAddress = individualAddress + "\n  -> State Province: " + getCharacterDataFromElement(line);

                    }


                    if(addressNote.item(0) != null){
                        line = (Element) addressNote.item(0);
                        individualAddress = individualAddress + "\n  -> Address Note: " + getCharacterDataFromElement(line);

                    }

                    if(zipCode.item(0) != null){
                        line = (Element) zipCode.item(0);
                        individualAddress = individualAddress + "\n  -> Zip Code: " + getCharacterDataFromElement(line);

                    }



                }

                sanction.setIndividualAddress(individualAddress);




                NodeList individualDateOfBirthNode = element.getElementsByTagName("INDIVIDUAL_DATE_OF_BIRTH");
                String  IndividualDateOfBirth = "";
                for (int j = 0; j < individualDateOfBirthNode.getLength(); j++) {
                    Element dbElement = (Element) individualDateOfBirthNode.item(j);


                    NodeList typeOfDate = dbElement.getElementsByTagName("TYPE_OF_DATE");
                    NodeList date = dbElement.getElementsByTagName("DATE");
                    NodeList fromYear = dbElement.getElementsByTagName("FROM_YEAR");
                    NodeList toYear = dbElement.getElementsByTagName("TO_YEAR");
                    NodeList year = dbElement.getElementsByTagName("YEAR");
                    NodeList note = dbElement.getElementsByTagName("NOTE");

                    if(typeOfDate.item(0) != null){
                        line = (Element) typeOfDate.item(0);
                        IndividualDateOfBirth = IndividualDateOfBirth + "\n  -> Type Of Date: " + getCharacterDataFromElement(line);

                    }


                    if(date.item(0) != null){
                        line = (Element) date.item(0);
                        IndividualDateOfBirth = IndividualDateOfBirth + "\n  -> Date of birth: " + getCharacterDataFromElement(line);
                    }

                    if(fromYear.item(0) != null){
                        line = (Element) fromYear.item(0);
                        IndividualDateOfBirth = IndividualDateOfBirth + "\n  ->From year " + getCharacterDataFromElement(line);
                    }

                    if(toYear.item(0) != null){
                        line = (Element) toYear.item(0);
                        IndividualDateOfBirth = IndividualDateOfBirth + "\n  -> To year: " + getCharacterDataFromElement(line);
                    }
                    if(year.item(0) != null){
                        line = (Element) year.item(0);
                        IndividualDateOfBirth = IndividualDateOfBirth + "\n  -> Year: " + getCharacterDataFromElement(line);
                    }
                    if(note.item(0) != null){
                        line = (Element) note.item(0);
                        IndividualDateOfBirth = IndividualDateOfBirth + "\n  -> Note: " + getCharacterDataFromElement(line);
                    }



                }

                sanction.setDateOfBirth(IndividualDateOfBirth);

                NodeList individualPlaceOfBirthNode = element.getElementsByTagName("INDIVIDUAL_PLACE_OF_BIRTH");
                String  IndividualPlaceOfBirth = "";
                for (int j = 0; j < individualPlaceOfBirthNode.getLength(); j++) {
                    Element birthPlaceElement = (Element) individualPlaceOfBirthNode.item(j);


                    NodeList city = birthPlaceElement.getElementsByTagName("CITY");
                    NodeList stateProvince = birthPlaceElement.getElementsByTagName("STATE_PROVINCE");
                    NodeList country = birthPlaceElement.getElementsByTagName("COUNTRY");


                    if(city.item(0) != null){
                        line = (Element) city.item(0);
                        IndividualPlaceOfBirth = IndividualPlaceOfBirth + "\n  -> City: " + getCharacterDataFromElement(line);

                    }


                    if(stateProvince.item(0) != null){
                        line = (Element) stateProvince.item(0);
                        IndividualPlaceOfBirth = IndividualPlaceOfBirth + "\n  -> State Province: " + getCharacterDataFromElement(line);
                    }

                    if(country.item(0) != null){
                        line = (Element) country.item(0);
                        IndividualPlaceOfBirth = IndividualPlaceOfBirth + "\n  -> Country: " + getCharacterDataFromElement(line);
                    }


                }

                sanction.setPlaceOfBirth(IndividualPlaceOfBirth);

                NodeList individualDocumentNode = element.getElementsByTagName("INDIVIDUAL_DOCUMENT");
                String  individualDocument = "";
                for (int j = 0; j < individualDocumentNode.getLength(); j++) {
                    Element documentElement = (Element) individualDocumentNode.item(j);


                    NodeList typeOfDocument = documentElement.getElementsByTagName("TYPE_OF_DOCUMENT");
                    NodeList typeOfDocument2 = documentElement.getElementsByTagName("TYPE_OF_DOCUMENT2");
                    NodeList number = documentElement.getElementsByTagName("NUMBER");
                    NodeList issuingCountry = documentElement.getElementsByTagName("ISSUING_COUNTRY");
                    NodeList dateOfIssue = documentElement.getElementsByTagName("DATE_OF_ISSUE");
                    NodeList note = documentElement.getElementsByTagName("NOTE");


                    if(typeOfDocument.item(0) != null){
                        line = (Element) typeOfDocument.item(0);
                        individualDocument = individualDocument + "\n  -> Type: " + getCharacterDataFromElement(line);

                    }


                    if(typeOfDocument2.item(0) != null){
                        line = (Element) typeOfDocument2.item(0);
                        individualDocument = individualDocument + "\n  -> Type of Document 2: " + getCharacterDataFromElement(line);
                    }

                    if(number.item(0) != null){
                        line = (Element) number.item(0);
                        individualDocument = individualDocument + "\n  -> Number: " + getCharacterDataFromElement(line);
                    }


                    if(issuingCountry.item(0) != null){
                        line = (Element) issuingCountry.item(0);
                        individualDocument = individualDocument + "\n  -> Issuing Country: " + getCharacterDataFromElement(line);

                    }


                    if(dateOfIssue.item(0) != null){
                        line = (Element) dateOfIssue.item(0);
                        individualDocument = individualDocument + "\n  -> Date of Issue: " + getCharacterDataFromElement(line);
                    }

                    if(note.item(0) != null){
                        line = (Element) note.item(0);
                        individualDocument = individualDocument + "\n  -> Note: " + getCharacterDataFromElement(line);
                    }


                }

                sanction.setIndividualDocument(individualDocument);

                sr.save(sanction);
            }

            // iterate the entities
            for (int i = 0; i < entityNode.getLength(); i++) {
                UNSanctions sanction = new UNSanctions();

                Element element = (Element) entityNode.item(i);

                NodeList dataId = element.getElementsByTagName("DATAID");
                Element line = (Element) dataId.item(0);
                if(dataId.item(0) != null){

                    sanction.setDataId(getCharacterDataFromElement(line));
                }

                String FullName = "";
                NodeList firstName = element.getElementsByTagName("FIRST_NAME");
                if(firstName.item(0) != null){
                    line = (Element) firstName.item(0);

                    FullName = FullName + getCharacterDataFromElement(line);
                    sanction.setFirstName(getCharacterDataFromElement(line));
                }


                NodeList secondName = element.getElementsByTagName("SECOND_NAME");
                if(secondName.item(0) != null){
                    line = (Element) secondName.item(0);

                    FullName = FullName + getCharacterDataFromElement(line);
                    sanction.setSecondName(getCharacterDataFromElement(line));

                }
                sanction.setFullName(FullName);

                NodeList listType = element.getElementsByTagName("UN_LIST_TYPE");
                if(secondName.item(0) != null){
                    line = (Element) listType.item(0);

                    sanction.setType(getCharacterDataFromElement(line));
                }

                NodeList referenceNumber = element.getElementsByTagName("REFERENCE_NUMBER");
                if(referenceNumber.item(0) != null){
                    line = (Element) referenceNumber.item(0);

                    sanction.setReferenceNumber(getCharacterDataFromElement(line));
                }

                NodeList listedOn = element.getElementsByTagName("LISTED_ON");
                if(listedOn.item(0) != null){
                    line = (Element) listedOn.item(0);

                    sanction.setListedOn(getCharacterDataFromElement(line));
                }

                NodeList comments1 = element.getElementsByTagName("COMMENTS1");
                if(comments1.item(0) != null){
                    line = (Element) comments1.item(0);

                    sanction.setComment(getCharacterDataFromElement(line));
                }

                //to access all children of the node
                if(element.getElementsByTagName("DESIGNATION").item(0) != null){
                    NodeList designationValue = element.getElementsByTagName("DESIGNATION").item(0).getChildNodes();
                    String Designation = "";
                    for(int j=0;j<designationValue.getLength();j++) {
                        if(designationValue.item(j).getNodeType()== Node.ELEMENT_NODE) {
                            Element child = (Element)designationValue.item(j);
                            Designation = Designation + "\n -> " +child.getTextContent();
                        }
                    }

                    sanction.setDesignation(Designation);
                }

                //to access all children of the node
                if(element.getElementsByTagName("NATIONALITY").item(0) != null){
                    NodeList nationalityValue = element.getElementsByTagName("NATIONALITY").item(0).getChildNodes();
                    String Nationality = "";
                    for(int j=0;j<nationalityValue.getLength();j++) {
                        if(nationalityValue.item(j).getNodeType()== Node.ELEMENT_NODE) {
                            Element child = (Element)nationalityValue.item(j);
                            Nationality = Nationality + "\n -> " +child.getTextContent();
                        }
                    }

                    sanction.setNationality(Nationality);

                }

                //to access all children of the node
                if(element.getElementsByTagName("LIST_TYPE").item(0) != null){
                    NodeList listTypeValue = element.getElementsByTagName("LIST_TYPE").item(0).getChildNodes();
                    String ListTypeValue = "";
                    for(int j=0;j<listTypeValue.getLength();j++) {
                        if(listTypeValue.item(j).getNodeType()== Node.ELEMENT_NODE) {
                            Element child = (Element)listTypeValue.item(j);
                            ListTypeValue = child.getTextContent();
                        }
                    }

                    sanction.setListType(ListTypeValue);
                }

                //to access all children of the node
                if(element.getElementsByTagName("LAST_DAY_UPDATED").item(0) != null){
                    NodeList lastDayUpdated = element.getElementsByTagName("LAST_DAY_UPDATED").item(0).getChildNodes();
                    String LastDayUpdated = "";
                    for(int j=0;j<lastDayUpdated.getLength();j++) {
                        if(lastDayUpdated.item(j).getNodeType()== Node.ELEMENT_NODE) {
                            Element child = (Element)lastDayUpdated.item(j);
                            LastDayUpdated = LastDayUpdated + "\n -> " +child.getTextContent();
                        }
                    }

                    sanction.setLastDayUpdated(LastDayUpdated);
                }


                NodeList aliasNode = element.getElementsByTagName("INDIVIDUAL_ALIAS");
                String  AliasName = "";
                for (int j = 0; j < aliasNode.getLength(); j++) {
                    Element aliasElement = (Element) aliasNode.item(j);


                    NodeList quality = aliasElement.getElementsByTagName("QUALITY");
                    NodeList aliasName = aliasElement.getElementsByTagName("ALIAS_NAME");

                    if(quality.item(0) != null){
                        line = (Element) quality.item(0);
                        AliasName = AliasName + "\n  -> Quality: " + getCharacterDataFromElement(line);

                    }


                    if(aliasName.item(0) != null){
                        line = (Element) aliasName.item(0);
                        AliasName = AliasName + "\n  -> Alias Name: " + getCharacterDataFromElement(line);
                    }


                }

                sanction.setAlias(AliasName);



                NodeList individualAddressNode = element.getElementsByTagName("INDIVIDUAL_ADDRESS");
                String  individualAddress = "";
                for (int j = 0; j < individualAddressNode.getLength(); j++) {
                    Element individualAddressElement = (Element) individualAddressNode.item(j);


                    NodeList street = individualAddressElement.getElementsByTagName("STREET");
                    NodeList city = individualAddressElement.getElementsByTagName("CITY");
                    NodeList country = individualAddressElement.getElementsByTagName("COUNTRY");
                    NodeList stateProvince = individualAddressElement.getElementsByTagName("STATE_PROVINCE");
                    NodeList addressNote = individualAddressElement.getElementsByTagName("NOTE");
                    NodeList zipCode = individualAddressElement.getElementsByTagName("ZIP_CODE");



                    if(street.item(0) != null){
                        line = (Element) street.item(0);
                        individualAddress = individualAddress + "\n  -> Street: " + getCharacterDataFromElement(line);

                    }

                    if(city.item(0) != null){
                        line = (Element) city.item(0);
                        individualAddress = individualAddress + "\n  -> City: " + getCharacterDataFromElement(line);

                    }


                    if(country.item(0) != null){
                        line = (Element) country.item(0);
                        individualAddress = individualAddress + "\n  -> Country: " + getCharacterDataFromElement(line);

                    }

                    if(stateProvince.item(0) != null){
                        line = (Element) stateProvince.item(0);
                        individualAddress = individualAddress + "\n  -> State Province: " + getCharacterDataFromElement(line);

                    }


                    if(addressNote.item(0) != null){
                        line = (Element) addressNote.item(0);
                        individualAddress = individualAddress + "\n  -> Address Note: " + getCharacterDataFromElement(line);

                    }

                    if(zipCode.item(0) != null){
                        line = (Element) zipCode.item(0);
                        individualAddress = individualAddress + "\n  -> Zip Code: " + getCharacterDataFromElement(line);

                    }



                }

                sanction.setIndividualAddress(individualAddress);




                NodeList entityDateOfBirthNode = element.getElementsByTagName("INDIVIDUAL_DATE_OF_BIRTH");
                String  EntityDateOfBirth = "";
                for (int j = 0; j < entityDateOfBirthNode.getLength(); j++) {
                    Element dbElement = (Element) entityDateOfBirthNode.item(j);


                    NodeList typeOfDate = dbElement.getElementsByTagName("TYPE_OF_DATE");
                    NodeList date = dbElement.getElementsByTagName("DATE");
                    NodeList fromYear = dbElement.getElementsByTagName("FROM_YEAR");
                    NodeList toYear = dbElement.getElementsByTagName("TO_YEAR");
                    NodeList year = dbElement.getElementsByTagName("YEAR");
                    NodeList note = dbElement.getElementsByTagName("NOTE");

                    if(typeOfDate.item(0) != null){
                        line = (Element) typeOfDate.item(0);
                        EntityDateOfBirth = EntityDateOfBirth + "\n  -> Type Of Date: " + getCharacterDataFromElement(line);

                    }


                    if(date.item(0) != null){
                        line = (Element) date.item(0);
                        EntityDateOfBirth = EntityDateOfBirth + "\n  -> Date of birth: " + getCharacterDataFromElement(line);
                    }

                    if(fromYear.item(0) != null){
                        line = (Element) fromYear.item(0);
                        EntityDateOfBirth = EntityDateOfBirth + "\n  ->From year " + getCharacterDataFromElement(line);
                    }

                    if(toYear.item(0) != null){
                        line = (Element) toYear.item(0);
                        EntityDateOfBirth = EntityDateOfBirth  + "\n  -> To year: " + getCharacterDataFromElement(line);
                    }
                    if(year.item(0) != null){
                        line = (Element) year.item(0);
                        EntityDateOfBirth = EntityDateOfBirth  + "\n  -> Year: " + getCharacterDataFromElement(line);
                    }
                    if(note.item(0) != null){
                        line = (Element) note.item(0);
                        EntityDateOfBirth = EntityDateOfBirth  + "\n  -> Note: " + getCharacterDataFromElement(line);
                    }



                }

                sanction.setDateOfBirth(EntityDateOfBirth);

                NodeList entityPlaceOfBirthNode = element.getElementsByTagName("INDIVIDUAL_PLACE_OF_BIRTH");
                String  EntityPlaceOfBirth = "";
                for (int j = 0; j < entityPlaceOfBirthNode.getLength(); j++) {
                    Element birthPlaceElement = (Element)  entityPlaceOfBirthNode.item(j);


                    NodeList city = birthPlaceElement.getElementsByTagName("CITY");
                    NodeList stateProvince = birthPlaceElement.getElementsByTagName("STATE_PROVINCE");
                    NodeList country = birthPlaceElement.getElementsByTagName("COUNTRY");


                    if(city.item(0) != null){
                        line = (Element) city.item(0);
                        EntityPlaceOfBirth = EntityPlaceOfBirth + "\n  -> City: " + getCharacterDataFromElement(line);

                    }


                    if(stateProvince.item(0) != null){
                        line = (Element) stateProvince.item(0);
                        EntityPlaceOfBirth = EntityPlaceOfBirth + "\n  -> State Province: " + getCharacterDataFromElement(line);
                    }

                    if(country.item(0) != null){
                        line = (Element) country.item(0);
                        EntityPlaceOfBirth = EntityPlaceOfBirth + "\n  -> Country: " + getCharacterDataFromElement(line);
                    }


                }

                sanction.setPlaceOfBirth(EntityPlaceOfBirth);

                NodeList entityDocumentNode = element.getElementsByTagName("INDIVIDUAL_DOCUMENT");
                String  entityDocument = "";
                for (int j = 0; j < entityDocumentNode.getLength(); j++) {
                    Element documentElement = (Element) entityDocumentNode.item(j);


                    NodeList typeOfDocument = documentElement.getElementsByTagName("TYPE_OF_DOCUMENT");
                    NodeList typeOfDocument2 = documentElement.getElementsByTagName("TYPE_OF_DOCUMENT2");
                    NodeList number = documentElement.getElementsByTagName("NUMBER");
                    NodeList issuingCountry = documentElement.getElementsByTagName("ISSUING_COUNTRY");
                    NodeList dateOfIssue = documentElement.getElementsByTagName("DATE_OF_ISSUE");
                    NodeList note = documentElement.getElementsByTagName("NOTE");


                    if(typeOfDocument.item(0) != null){
                        line = (Element) typeOfDocument.item(0);
                        entityDocument = entityDocument + "\n  -> Type: " + getCharacterDataFromElement(line);

                    }


                    if(typeOfDocument2.item(0) != null){
                        line = (Element) typeOfDocument2.item(0);
                        entityDocument = entityDocument + "\n  -> Type of Document 2: " + getCharacterDataFromElement(line);
                    }

                    if(number.item(0) != null){
                        line = (Element) number.item(0);
                        entityDocument = entityDocument + "\n  -> Number: " + getCharacterDataFromElement(line);
                    }


                    if(issuingCountry.item(0) != null){
                        line = (Element) issuingCountry.item(0);
                        entityDocument = entityDocument + "\n  -> Issuing Country: " + getCharacterDataFromElement(line);

                    }


                    if(dateOfIssue.item(0) != null){
                        line = (Element) dateOfIssue.item(0);
                        entityDocument = entityDocument + "\n  -> Date of Issue: " + getCharacterDataFromElement(line);
                    }

                    if(note.item(0) != null){
                        line = (Element) note.item(0);
                        entityDocument = entityDocument + "\n  -> Note: " + getCharacterDataFromElement(line);
                    }


                }

                sanction.setIndividualDocument(entityDocument);

                sr.save(sanction);

            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void importEUSanctions(){

        File file = new File("C:\\Users\\Abdyf\\Documents\\Sanctions\\20230426-FULL-1_1(xsd).xml");

        try {
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            Document doc = db.parse(file);
            NodeList entity = doc.getElementsByTagName("sanctionEntity");



            // Sanctions llogs = new Sanctions();
            // iterate the entities
            for (int i = 0; i < entity.getLength(); i++) {


                Element element = (Element) entity.item(i);


                //Do Variable
                String DesignationDate;
                DesignationDate =  element.getAttribute("designationDate");


                //Do Variable
                String DesignationDetails;
                DesignationDetails =  element.getAttribute("designationDetails");


                //Do Variable
                String UnitedNationId;
                UnitedNationId =  element.getAttribute("unitedNationId");


                //Do Variable
                String EUReferenceNumber;
                EUReferenceNumber =  element.getAttribute("euReferenceNumber");


                //Do Variable
                String LogicalId;
                LogicalId =  element.getAttribute("logicalId");


                NodeList ListOfRemarks = element.getElementsByTagName("remark");
                Element line;
                // Da Variable
                String Remark = "";
                if(ListOfRemarks.item(0) != null){
                    for( int j= 0; j<ListOfRemarks.getLength(); j++){
                        if(ListOfRemarks.item(j).getParentNode().getNodeName() == "sanctionEntity"){
                            line = (Element) ListOfRemarks.item(j);
                            Remark = Remark + getCharacterDataFromElement(line);
                        }

                    }
                }


                NodeList ListOfRegulations = element.getElementsByTagName("regulation");
                Element Regulation_ = (Element) ListOfRegulations.item(0);

                //Do variable
                String OrganizationType = "";
                OrganizationType = Regulation_.getAttribute("organisationType");

                //Do variable
                String PublicationDate = "";
                PublicationDate = Regulation_.getAttribute("publicationDate");

                //Do variable
                String EntryIntoForceDate = "";
                EntryIntoForceDate = Regulation_.getAttribute("entryIntoForceDate");

                //Do variable
                String NumberTitle = "";
                NumberTitle = Regulation_.getAttribute("numberTitle");

                //Do variable
                String Programme = "";
                Programme = Regulation_.getAttribute("programme");

                //Do variable
                String RegulationLogicalId = "";
                RegulationLogicalId = Regulation_.getAttribute("logicalId");

                //Do variable
                String PublicationUrl = "";
                NodeList ListOfPublicationUrl = Regulation_.getElementsByTagName("publicationUrl");
                Element PublicationUrl_ = (Element) ListOfPublicationUrl.item(0);
                PublicationUrl = getCharacterDataFromElement(PublicationUrl_);











            }




        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";


    }

}
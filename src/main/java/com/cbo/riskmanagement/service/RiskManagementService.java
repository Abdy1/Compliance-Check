package com.cbo.riskmanagement.service;

import com.cbo.riskmanagement.model.*;
import com.cbo.riskmanagement.model.adverser.Adverser;
import com.cbo.riskmanagement.model.adverser.AdverserResponseDetail;
import com.cbo.riskmanagement.model.adverser.AdverserUpdateCheck;
import com.cbo.riskmanagement.model.eu.*;
import com.cbo.riskmanagement.model.nbeblacklist.NbeBacklist;
import com.cbo.riskmanagement.model.pep.PepList;
import com.cbo.riskmanagement.model.pep.PepResponseDetail;
import com.cbo.riskmanagement.model.pep.PepUpdateCheck;
import com.cbo.riskmanagement.model.uk.*;
import com.cbo.riskmanagement.model.uk.EuUpdateCheck;
import com.cbo.riskmanagement.model.un.*;
import com.cbo.riskmanagement.model.us.*;
import org.apache.poi.ss.usermodel.Cell;


import com.cbo.riskmanagement.repository.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.transaction.Transactional;


import org.apache.commons.math3.analysis.function.Add;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;




@Service
@Transactional
public class RiskManagementService {

    @Autowired
    UkNameRepository ukNameRepository;
    @Autowired
    NbeBackListRepository nbeBackListRepository;
    @Autowired
    UkGenderRepository ukGenderRepository;
    @Autowired
    UkIndividualRepository ukIndividualRepository;
    @Autowired
    UkAddressRepository ukAddressRepository;
    @Autowired
    UnIndividualBirthDateRepository unIndividualBirthDateRepository;
@Autowired
UkPassportRepository ukPassportRepository;
@Autowired
LocationRepository locationRepository;
    @Autowired
    UNEntityRiskManagementRepository unEntityRiskManagementRepository;
    @Autowired
    UNIndividualRiskManagementRepository unIndividualRiskManagementRepository;
    @Autowired
    EuRiskManagementRepository euRiskManagementRepository;
@Autowired
NameAliasRepository nameAliasRepository;
@Autowired
UnNationalityRepository unNationalityRepository;
@Autowired
UnIndividualAliasRepository unIndividualAliasRepository;
@Autowired
UnIndividualAddressRepository unIndividualAddressRepository;
@Autowired
UnIndividualDocumentRepository unIndividualDocumentRepository;

@Autowired
NationalIdentifierRepository nationalIdentifierRepository;
@Autowired
NonLationNameRepository nonLationNameRepository;
    @Autowired
    UKRiskManagementRepository ukRiskManagementRepository;

    @Autowired
    PepListRepository pepListRepository;
    @Autowired
    AdverserRepository adverserRepository;
@Autowired
UkNamesRepository ukNamesRepository;
@Autowired
ConsolidatedRepository consolidatedRepository;

@Autowired
DeliquentRepository deliquentRepository;

@Autowired
DeliquentRepositoryUpdated deliquentRepositoryUpdated;





    File homedir = new File(System.getProperty("user.home"));




    public Consolidated getConsolidatedEntityList() throws XMLStreamException, IOException {
        URL url = new URL("https://www.un.org/securitycouncil/sites/www.un.org.securitycouncil/files/consolidated_en.0629_0.xml");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(connection.getInputStream());
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Consolidated consolidated = xmlMapper.readValue(xmlStreamReader, Consolidated.class);

        return consolidated;

    }


    public List<UnEntity> getEntitiesData() {
        return unEntityRiskManagementRepository.findAll();
    }

    public List<UnIndividual> getIndividualsData() {
        return unIndividualRiskManagementRepository.findAll();
    }


    public ExportedEntity readSanctionEntityFromXML() throws XMLStreamException, IOException {
        InputStream xmlResource = RiskManagementService.class.getClassLoader().getResourceAsStream("eu.xml");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(xmlResource);
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        ExportedEntity exportedEntity = xmlMapper.readValue(xmlStreamReader, ExportedEntity.class);
        return exportedEntity;
    }
    @Transactional
    public ExportedEntity parseEuXmlFile(MultipartFile file) throws IOException, XMLStreamException {
        InputStream inputStream = file.getInputStream();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        ExportedEntity exportedEntity = xmlMapper.readValue(xmlStreamReader, ExportedEntity.class);
        return exportedEntity;
    }
//@Transactional
//    public void saveSanctionEntityToDatabase(ExportedEntity exportedEntity) {
//        // Delete all existing EU SanctionEntity records
////        euRiskManagementRepository.deleteAllInBatch();
//
//        // Save the new SanctionEntity records to the database
//        for (SanctionEntity sanctionEntity : exportedEntity.getSanctionEntity()) {
//            euRiskManagementRepository.save(sanctionEntity);
//        }
//    }


    public List<SanctionEntity> getAllSanctionEntities() {
        return euRiskManagementRepository.findAll();
    }

    public Designations readDesignationsFromXML() throws XMLStreamException, IOException {
        InputStream xmlResource = RiskManagementService.class.getClassLoader().getResourceAsStream("uk.xml");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(xmlResource);
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        Designations designations = xmlMapper.readValue(xmlStreamReader, Designations.class);
        return designations;
    }



    public void saveDesignationsToDatabase(Designations designations) {
        ukRiskManagementRepository.deleteAll(); // Delete all existing UK designations
        UkDesignation ukDesignation = null;
        for (UkDesignation ukDesignationData : designations.getUkDesignation()) {

            ukDesignation = ukDesignationData;

            if (ukDesignation != null)
                ukRiskManagementRepository.save(ukDesignation);
        }
    }
    public Designations getDesignations() throws XMLStreamException, IOException {
        UkDesignation ukDesignation = null;
        InputStream xmlResource = RiskManagementService.class.getClassLoader().getResourceAsStream("uk.xml");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(xmlResource);
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        Designations designations = xmlMapper.readValue(xmlStreamReader, Designations.class);
        for (UkDesignation ukDesignationData : designations.getUkDesignation()) {
            System.out.println("FirstName: " + ukDesignationData.getNames());
            ukDesignation = ukDesignationData;
            System.out.println("Designation: " + ukDesignation.getNames());
            if (ukDesignation != null)
                ukRiskManagementRepository.save(ukDesignation);
        }


        return designations;
    }




    public Designations loadDesignationsFromXml() throws XMLStreamException, IOException {
        InputStream xmlResource = RiskManagementService.class.getClassLoader().getResourceAsStream("uk.xml");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(xmlResource);
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        Designations designations = xmlMapper.readValue(xmlStreamReader, Designations.class);
        return designations;
    }

    @Transactional
    public Designations  parseUkXmlFile(MultipartFile file) throws IOException, XMLStreamException {
        InputStream inputStream = file.getInputStream();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        Designations designations = xmlMapper.readValue(xmlStreamReader, Designations.class);
        return designations;
    }

    public void saveDesignationsToDb(Designations designations) {
        ukRiskManagementRepository.deleteAllInBatch();
        for (UkDesignation ukDesignation : designations.getUkDesignation()) {
            ukRiskManagementRepository.save(ukDesignation);
        }
    }

    @Cacheable("ukDesignations")
    @Async
    public List<UkDesignation> getAllUkDesignations() {
        return ukRiskManagementRepository.findAll();
    }


    public Consolidated getConsolidatedIndividualList() throws XMLStreamException, IOException {
        UnIndividual unIndividual = null;
        URL url = new URL("http://example.com/un.xml");
        URLConnection connection = url.openConnection();
        InputStream xmlResource = connection.getInputStream();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(xmlResource);
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        Consolidated consolidated = xmlMapper.readValue(xmlStreamReader, Consolidated.class);
        for (UnIndividual unIndividualData : consolidated.getIndividuals().getUnIndividualList()) {
            System.out.println("FirstName: " + unIndividualData.getFirstName());
            unIndividual = unIndividualData;
            if (unIndividual != null) {
                unIndividualRiskManagementRepository.save(unIndividual);
            }
        }

        return consolidated;
    }

    public List<SanctionEntity> getEuSanctionEntity() {
        return euRiskManagementRepository.findAll();
    }
    public List<Name> getUKSanctionSearchResult(String fullName){
      UkDesignation ukDesignation;
      List<Name> nameList;

        if (ukNameRepository.findUkSanctionByName(fullName) !=null){
            nameList = ukNameRepository.findUkSanctionByName(fullName);
            for(Name name : nameList) {
                System.out.println("Name: "+name.getName6());
//                Long namesId = ukNameRepository.getNamesId(name.getId());
//                ukDesignation = ukRiskManagementRepository.findUkDesignationById(namesId);
             //   System.out.println("UkDesignation: "+ukDesignation.getRegimeName());
            }
            return nameList;

        }else {
            return null;
        }
    }


    public int  getUkUpdateCounter() {
        Optional<UkUpdateCheck> optionalUpdateCheck = ukUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            UkUpdateCheck updateCheck = optionalUpdateCheck.get();
            return updateCheck.getUpdateCheck();
        } else {
            throw new RuntimeException("No UkUpdateCheck found");
        }

    }

    public void setUkUpdateCounter(int newUpdateCounter) {
        Optional<UkUpdateCheck> optionalUpdateCheck = ukUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            UkUpdateCheck updateCheck = optionalUpdateCheck.get();
            updateCheck.setUpdateCheck(newUpdateCounter);
            ukUpdateCheckRepository.save(updateCheck);
        } else {
            throw new RuntimeException("No UKUpdateCheck found");
        }

    }
    public List<PepResponseDetail> getPEPSearchResult(String fullName) {
        String checker = "";
List<PepResponseDetail> pepResponseDetailList = new ArrayList<>();
        if (pepListRepository.findByKeyword(fullName) != null) {
            List<PepList> pepLists = pepListRepository.findByKeyword(fullName);
            for (PepList pepList : pepLists) {
                PepResponseDetail pepResponseDetail = new PepResponseDetail();
                pepResponseDetail.setId(pepList.getId());
                pepResponseDetail.setNameInAmh(pepList.getNameInAmh());
                pepResponseDetail.setNameInEng(pepList.getNameInEng());
                pepResponseDetail.setDetails(pepList.getDetails());
                pepResponseDetail.setPosition(pepList.getPosition());
                pepResponseDetail.setPlaceOfAssignment(pepList.getPlaceOfAssignment());
                pepResponseDetailList.add(pepResponseDetail);

            }
            return pepResponseDetailList;


        }
        return null;
    }
    public List<AdverserResponseDetail> getAdverserSearchResult(String fullName) {
       List<AdverserResponseDetail> adverserResponseDetailList = new ArrayList<>();

        if (adverserRepository.findByKeyword(fullName) != null) {
            List<Adverser> adversers = adverserRepository.findByKeyword(fullName);
            for (Adverser adverser : adversers) {
            AdverserResponseDetail adverserResponseDetail = new AdverserResponseDetail();
            adverserResponseDetail.setId(adverser.getId());
            adverserResponseDetail.setName(adverser.getName());
            adverser.setPosition(adverser.getPosition());
            adverserResponseDetailList.add(adverserResponseDetail);
            }
            return adverserResponseDetailList;


        }
        return null;
    }
    //Un Individual search Query;
    public List<UnIndividualResponseDetail> getUNIndividualSearchResult(String fullName){
        List<UnIndividualResponseDetail> unIndividualResponseDetailList = new ArrayList<>();
        if (unIndividualRiskManagementRepository.findByKeyword(fullName.toUpperCase()) != null) {
            List<UnIndividual> unIndividuals = unIndividualRiskManagementRepository.findByKeyword(fullName.toUpperCase());
            for(UnIndividual unIndividual: unIndividuals) {
             Long addressId  = unIndividualRiskManagementRepository.getIndividualAddressId(unIndividual.getId());
             Long documentId = unIndividualRiskManagementRepository.getUnIndividualDocumentId(unIndividual.getId());
             Long aliasId = unIndividualRiskManagementRepository.getUnIndividualAliasId(unIndividual.getId());
             Long birthDateid = unIndividualRiskManagementRepository.getBirtDateId(unIndividual.getId());
             Long nationalityId = unIndividualRiskManagementRepository.getUnNationalityId(unIndividual.getId());

              UnIndividualResponseDetail unIndividualResponseDetail =new UnIndividualResponseDetail();
                IndividualAlias individualAlias = unIndividualAliasRepository.getIndividualAliasById(aliasId);
                IndividualAddress individualAddress = unIndividualAddressRepository.getIndividualAddressById(addressId);
                IndividualDocument individualDocument = unIndividualDocumentRepository.getIndividualDocumentById(documentId);
                IndividualDateOfBirth individualDateOfBirth = unIndividualBirthDateRepository.getIndividualDateOfBirthById(birthDateid);
                Nationality nationality = unNationalityRepository.getNationalityById(nationalityId);
                unIndividualResponseDetail.setId(unIndividual.getId());
                unIndividualResponseDetail.setFirstName(unIndividual.getFirstName());
                unIndividualResponseDetail.setSecondName(unIndividual.getSecondName());
                unIndividualResponseDetail.setThirdName(unIndividual.getThirdName());
                unIndividualResponseDetail.setFourthName(unIndividual.getFourthName());
                unIndividualResponseDetail.setTitle(unIndividual.getTitle());
                unIndividualResponseDetail.setUnListType(unIndividual.getUnListType());
                unIndividualResponseDetail.setReferenceNumber(unIndividual.getReferenceNumber());
                unIndividualResponseDetail.setListedOn(unIndividual.getListedOn());
                unIndividualResponseDetail.setComments1(unIndividual.getComments1());
                unIndividualResponseDetail.setIndividualPlaceOfBirth(unIndividual.getIndividualPlaceOfBirth());
                unIndividualResponseDetail.setSortKey(unIndividual.getSortKey());
                unIndividualResponseDetail.setSortKeyLastMod(unIndividual.getSortKeyLastMod());
                if(nationality !=null){ unIndividualResponseDetail.setNationality(nationality.getValue());}else {
                    unIndividualResponseDetail.setNationality("NO DATA AVAILABLE");
                }
                unIndividualResponseDetail.setNameOriginalScript(unIndividual.getNameOriginalScript());
                unIndividualResponseDetail.setGender(unIndividual.getGender());
                unIndividualResponseDetail.setSubmittedBy(unIndividual.getSubmittedBy());
                if(individualAddress !=null) {
                    unIndividualResponseDetail.setCountry(individualAddress.getCountry());
                    unIndividualResponseDetail.setAddressNote(individualAddress.getNote());
                    unIndividualResponseDetail.setStateProvince(individualAddress.getStateProvince());
                    unIndividualResponseDetail.setStreet(individualAddress.getStreet());
                    unIndividualResponseDetail.setCity(individualAddress.getCity());
                    unIndividualResponseDetail.setZipCode(individualAddress.getZipCode());
                }

                if(individualAlias !=null) {
                    unIndividualResponseDetail.setQuality(individualAlias.getQuality());
                    unIndividualResponseDetail.setAliasName(individualAlias.getAliasName());
                    unIndividualResponseDetail.setAliasNote(individualAlias.getNote());
                    unIndividualResponseDetail.setDateOfBirth(individualAlias.getDateOfBirth());
                    unIndividualResponseDetail.setCityOfBirth(individualAlias.getCityOfBirth());
                    unIndividualResponseDetail.setCountryOfBirth(individualAlias.getCountryOfBirth());
                }
                if(individualDateOfBirth !=null) {
                    unIndividualResponseDetail.setTypeOfDate(individualDateOfBirth.getTypeOfDate());
                    unIndividualResponseDetail.setDate(individualDateOfBirth.getDate());
                    unIndividualResponseDetail.setYear(individualDateOfBirth.getYear());
                    unIndividualResponseDetail.setFromYear(individualDateOfBirth.getFromYear());
                }
                if(individualDocument !=null) {
                    unIndividualResponseDetail.setTypeOfDocument(individualDocument.getTypeOfDocument());
                    unIndividualResponseDetail.setNumber(individualDocument.getNumber());
                    unIndividualResponseDetail.setIssuingCountry(individualDocument.getIssuingCountry());
                    unIndividualResponseDetail.setDocumentStateProvince(individualDocument.getStateProvince());
                    unIndividualResponseDetail.setDateOfIssue(individualDocument.getDateOfIssue());
                    unIndividualResponseDetail.setCityOfIssue(individualDocument.getCityOfIssue());
                }
                unIndividualResponseDetailList.add(unIndividualResponseDetail);

            }

        }
        return unIndividualResponseDetailList;
    }
    // EU Search Query service;
    public List<NameAlias> getEUSearchResult(String fullName) {
        List<NameAlias> nameAliasData = new ArrayList<>();
            if (nameAliasRepository.findSanctionEntityByNameAlias(fullName) != null) {
               nameAliasData = nameAliasRepository.findSanctionEntityByNameAlias(fullName);
                return nameAliasData;
            }
            return null;
        }
        //PEP LIST Extract from csv

    File pepfile = new File(homedir, "Documents/sanction_data/pep.csv");
        public List<PepList> getPepList() throws FileNotFoundException {
       List<PepList> pepLists = new CsvToBeanBuilder<PepList>(new FileReader(pepfile))
               .withType(PepList.class)
               .build()
               .parse();
       return pepLists;
        }
    File nbefile = new File(homedir, "Documents/sanction_data/nbebacklist.csv");
    public List<NbeBacklist> getNbeBacklist() throws FileNotFoundException {
        List<NbeBacklist> nbeBacklists = new CsvToBeanBuilder<NbeBacklist>(new FileReader(nbefile))
                .withType(NbeBacklist.class)
                .build()
                .parse();
        return nbeBacklists;
    }
    @Transactional
    public List<NbeBacklist> saveNbeBacklistToDatabase() throws FileNotFoundException {
        List<NbeBacklist> nbeBacklists = getNbeBacklist();
        nbeBackListRepository.deleteAll();
        return nbeBackListRepository.saveAll(nbeBacklists);
    }

    @Transactional
    public List<NbeBacklist> saveNbeBacklistToDatabaseFromFile(List<NbeBacklist> nbeBacklists) throws FileNotFoundException {
        nbeBackListRepository.deleteAll();
        return nbeBackListRepository.saveAll(nbeBacklists);
    }


    File adverserfile = new File(homedir, "Documents/sanction_data/adverser.csv");
    public List<Adverser> getAdverser() throws FileNotFoundException {
        return new CsvToBeanBuilder<Adverser>(new FileReader(adverserfile))
                .withType(Adverser.class)
                .build()
                .parse();
    }
    @Transactional
    public List<Adverser> getAdverserListImport(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        CsvToBean<Adverser> csvToBean = new CsvToBeanBuilder<Adverser>(reader)
                .withType(Adverser.class)
                .build();
        return csvToBean.parse();
    }
    @Transactional
    public List<Adverser> saveAdverserToDatabase() throws FileNotFoundException {
        List<Adverser> adversers = getAdverser();
        adverserRepository.deleteAll();
        return adverserRepository.saveAll(adversers);
    }

    public int  getAdverserUpdateCounter() {
        Optional<AdverserUpdateCheck> optionalUpdateCheck = adverserUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            AdverserUpdateCheck updateCheck = optionalUpdateCheck.get();
            return updateCheck.getUpdateCheck();
        } else {
            throw new RuntimeException("No Adverser  Update Check found");
        }

    }

    public void setAdverserUpdateCounter(int newUpdateCounter) {
        Optional<AdverserUpdateCheck> optionalUpdateCheck = adverserUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            AdverserUpdateCheck updateCheck = optionalUpdateCheck.get();
            updateCheck.setUpdateCheck(newUpdateCounter);
            adverserUpdateCheckRepository.save(updateCheck);
        } else {
            throw new RuntimeException("No Adverser Update Check found");
        }

    }

    File ofacfile = new File(homedir, "Documents/sanction_data/ofac.csv");
    public List<OfacSanction> getOfacSanction() throws FileNotFoundException {
        List<OfacSanction> ofacSanctionsList = new CsvToBeanBuilder<OfacSanction>(new FileReader(ofacfile))
                .withType(OfacSanction.class)
                .build()
                .parse();
        return ofacSanctionsList;
    }



//    public List<SdnList> getOfacSanctionFromFile() throws IOException {
//        File ofacfilexml = new File(homedir, "Documents/sanction_data/sdn.xml");
//        InputStream inputStream = new FileInputStream(ofacfilexml);
//        if (ofacfilexml.getName().endsWith(".gz")) {
//            inputStream = new GZIPInputStream(inputStream);
//        }
//
//        JacksonXmlModule module = new JacksonXmlModule();
//        module.setDefaultUseWrapper(false);
//        XmlMapper xmlMapper = new XmlMapper(module);
//        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        SdnList[] ofacSanctionsArray = xmlMapper.readValue(inputStream, SdnList[].class);
//
//        return Arrays.asList(ofacSanctionsArray);
//    }
//    public List<SdnList> getOfacSanctionFromFile1() throws IOException {
//        File ofacfilexml = new File(homedir, "Documents/sanction_data/sdn.xml");
//        InputStream inputStream = new FileInputStream(ofacfilexml);
//        if (ofacfilexml.getName().endsWith(".gz")) {
//            inputStream = new GZIPInputStream(inputStream);
//        }
//
//        // Print out the XML content
//        String xmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
//        System.out.println(xmlContent);
//
//        // Reset the input stream
//        inputStream.close();
//        inputStream = new FileInputStream(ofacfilexml);
//        if (ofacfilexml.getName().endsWith(".gz")) {
//            inputStream = new GZIPInputStream(inputStream);
//        }
//
//        JacksonXmlModule module = new JacksonXmlModule();
//        module.setDefaultUseWrapper(false);
//        XmlMapper xmlMapper = new XmlMapper(module);
//        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        SdnList[] ofacSanctionsArray = xmlMapper.readValue(inputStream, SdnList[].class);
//
//        // Print out the deserialized SdnList object
//        System.out.println(Arrays.toString(ofacSanctionsArray));
//
//        return Arrays.asList(ofacSanctionsArray);
//    }
//
//    public List<SdnList> getOfacSanctionFromUrls() throws IOException, XMLStreamException {
//        URL url = new URL("https://www.treasury.gov/ofac/downloads/sdn.xml");
//        InputStream inputStream = url.openStream();
//        if (url.getPath().endsWith(".gz")) {
//            inputStream = new GZIPInputStream(inputStream);
//        }
//
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//        XMLEventReader eventReader = factory.createXMLEventReader(inputStream);
//
//        List<SdnList> sdnLists = new ArrayList<>();
//        SdnList sdnList = null;
//        SdnEntry sdnEntry = null;
//        PublishInformation publishInformation = null;
//        while (eventReader.hasNext()) {
//            XMLEvent event = eventReader.nextEvent();
//            if (event.isStartElement()) {
//                StartElement startElement = event.asStartElement();
//                String elementName = startElement.getName().getLocalPart();
//                switch (elementName) {
//                    case "sdnEntry":
//                        sdnEntry = new SdnEntry();
//                        break;
//                    case "firstName":
//                        sdnEntry.setLastName(eventReader.getElementText());
//                        break;
//                    // Set other fields of the SdnEntry object
//                    case "sdnList":
//                        sdnList = new SdnList();
//                        break;
//                    case "sdnEntryList":
//                        sdnList.setSdnEntryList(new ArrayList<>());
//                        break;
//                    // Set other fields of the SdnList object
//                    case "publishInformation":
//                        publishInformation = new PublishInformation();
//                        break;
//                    // Set other fields of the PublishInformation object
//                }
//            } else if (event.isEndElement()) {
//                EndElement endElement = event.asEndElement();
//                String elementName = endElement.getName().getLocalPart();
//                switch (elementName) {
//                    case "sdnEntry":
//                        sdnList.getSdnEntryList().add(sdnEntry);
//                        sdnEntry = null;
//                        break;
//                    // Handle other end elements
//                    case "sdnList":
//                        sdnLists.add(sdnList);
//                        sdnList = null;
//                        break;
//                }
//            }
//        }
//
//        return sdnLists;
//    }
//    public List<SdnList> getOfacSanctionFromUrl() throws IOException {
//        URL url = new URL("https://www.treasury.gov/ofac/downloads/sdn.xml");
//        URLConnection connection = url.openConnection();
//
//        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
//        InputStream inputStream = connection.getInputStream();
//        if ("gzip".equals(connection.getContentEncoding())) {
//            inputStream = new GZIPInputStream(inputStream);
//        }
//
//        JacksonXmlModule module = new JacksonXmlModule();
//        module.setDefaultUseWrapper(false);
//        XmlMapper xmlMapper = new XmlMapper(module);
//        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        SdnList[] ofacSanctionsArray = xmlMapper.readValue(connection.getInputStream(), SdnList[].class);
//       // System.out.println(ofacSanctionsArray[0].getPublishInformation().getPublishDate());
//
//
//
//        return Arrays.asList(ofacSanctionsArray);
//    }
//
//    public List<SdnList> getOfacSanctionFromUrlss() throws IOException, XMLStreamException {
//        URL url = new URL("https://www.treasury.gov/ofac/downloads/sdn.xml");
//        InputStream inputStream = url.openStream();
//        if (url.getPath().endsWith(".gz")) {
//            inputStream = new GZIPInputStream(inputStream);
//        }
//
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//        XMLEventReader eventReader = factory.createXMLEventReader(inputStream);
//
//        List<SdnList> sdnLists = new ArrayList<>();
//        SdnList sdnList = null;
//        SdnEntry sdnEntry = null;
//        PublishInformation publishInformation = null;
//        while (eventReader.hasNext()) {
//            XMLEvent event = eventReader.nextEvent();
//            if (event.isStartElement()) {
//                StartElement startElement = event.asStartElement();
//                String elementName = startElement.getName().getLocalPart();
//                System.out.println(event);
//                switch (elementName) {
//                    case "sdnEntry":
//                        sdnEntry = new SdnEntry();
//                        break;
//                    case "lastName":
//                        sdnEntry.setLastName(eventReader.getElementText());
//                        System.out.println("Set " + sdnEntry.getLastName() + " to " + eventReader.getElementText());
//                        break;
//                    // Set other fields of the SdnEntry object
//                    case "sdnList":
//                        sdnList = new SdnList();
//                        break;
//                    case "sdnEntryList":
//                        sdnList.setSdnEntryList(new ArrayList<>()); // Initialize the list
//                        break;
//                    // Set other fields of the SdnList object
//                    case "publishInformation":
//                        publishInformation = new PublishInformation();
//                        break;
//                    // Set other fields of the PublishInformation object
//                }
//            } else if (event.isEndElement()) {
//                EndElement endElement = event.asEndElement();
//                String elementName = endElement.getName().getLocalPart();
//                switch (elementName) {
//                    case "sdnEntry":
//                        if (sdnList.getSdnEntryList() == null) {
//                            sdnList.setSdnEntryList(new ArrayList<>()); // Initialize the list if null
//                        }
//                        sdnList.getSdnEntryList().add(sdnEntry); // Add to the initialized list
//                        sdnEntry = null;
//                        break;
//                    // Handle other end elements
//                    case "sdnList":
//                        sdnLists.add(sdnList);
//                        sdnList = null;
//                        break;
//                }
//            }
//        }
//
//        return sdnLists;
//    }
//    public List<SdnEntry> gets() throws IOException {
//        // Create a URL object and open a connection
//        URL url = new URL("https://www.treasury.gov/ofac/downloads/sdn.xml");
//        URLConnection connection = url.openConnection();
//
//        // Set a user agent to avoid HTTP 403 Forbidden error
//        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
//
//        // Create an instance of XmlMapper and configure it to use a custom DateFormat
//        XmlMapper xmlMapper = new XmlMapper();
//
//        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        // Read the XML content from the connection input stream and map it to your model
//        SdnList sdnList = xmlMapper.readValue(connection.getInputStream(), SdnList.class);
//
//        // Access the properties of the mapped object
//        PublishInformation publishInformation = sdnList.getPublishInformation();
//
//        System.out.println("Record Count: " + publishInformation.getRecordCount());
//        List<SdnEntry> sdnEntryList = sdnList.getSdnEntryList();
//        System.out.println("Number of SDN entries: " + sdnEntryList.size());
//
//        return sdnEntryList;
//    }
    private static final String SDN_URL = "https://www.treasury.gov/ofac/downloads/sdn.csv";
    private static final String ADD_URL = "https://www.treasury.gov/ofac/downloads/add.csv";
    private static final String ALT_URL = "https://www.treasury.gov/ofac/downloads/alt.csv";
    private static final String REMARKS_URL = "https://www.treasury.gov/ofac/downloads/sdn_comments.csv";

//    public List<SdnList> getSdnList() throws IOException {
//        Map<Long, List<Address>> addressMap = getAddressMap();
//        Map<Long, List<Aka>> akaMap = getAkaMap();
//        Map<Long, List<SdnCommentsEntity>> commentsMap = getCommentsMap();
//        CSVParser parser = CSVParser.parse(new InputStreamReader(new URL(SDN_URL).openStream(), StandardCharsets.UTF_8), CSVFormat.DEFAULT);
//        List<SdnList> sdnList = new ArrayList<>();
//        for (CSVRecord record : parser) {
//            if (record.size() == 1 && record.get(0).equals("\u001A")) {
//                // Skip the last record in the CSV file
//                break;
//            }
//            SdnList sdn = new SdnList();
//            sdn.setUid(Long.parseLong(record.get(0).trim()));
//            sdn.setName(record.get(1).trim());
//            sdn.setSdnType(record.get(2).trim());
//            sdn.setProgram(record.get(3).trim());
//            sdn.setTitle(record.get(4).trim());
//            sdn.setCallSign(record.get(5).trim());
//            sdn.setVessType(record.get(6).trim());
//            sdn.setTonnage(record.get(7).trim());
//            sdn.setGrt(record.get(8).trim());
//            sdn.setVessFlag(record.get(9).trim());
//            sdn.setVessOwner(record.get(10).trim());
//            sdn.setRemarks(record.get(11).trim());
//            sdn.setAddressList(addressMap.get(Long.parseLong(record.get(0).trim())));
//            sdn.setAkaList(akaMap.get(Long.parseLong(record.get(0).trim())));
//            List<SdnCommentsEntity> commentsList = commentsMap.get(Long.parseLong(record.get(0).trim()));
//            if (commentsList != null && !commentsList.isEmpty()) {
//                String remarksExtended = commentsList.get(0).getRemarkExtended();
//                SdnCommentsEntity remarks = new SdnCommentsEntity();
//                remarks.setRemarkExtended(remarksExtended);
//                sdn.setRemarkExtended(remarks);
//            }
//            sdnList.add(sdn);
//        }
//        return sdnList;
//    }
//
//    private Map<Long,List<Address>> getAddressMap() throws IOException {
//        CSVParser parser = CSVParser.parse(new InputStreamReader(new URL(ADD_URL).openStream(), StandardCharsets.UTF_8), CSVFormat.DEFAULT.withSkipHeaderRecord(true).withIgnoreEmptyLines(true));
//        Map<Long, List<Address>> addressMap = new HashMap<>();
//        Iterator<CSVRecord> iterator = parser.iterator();
//        while (iterator.hasNext()) {
//            CSVRecord record = iterator.next();
//            if (record.size() == 1 && record.get(0).equals("\u001A")) {
//                // Skip the last record in the CSV file
//                break;
//            }
//            Address address = new Address();
//            address.setUid(Long.parseLong(record.get(0)));
//            address.setId(address.getId()); // set the auto-generated ID
//            address.setAddressNum(Integer.parseInt(record.get(1)));
//            address.setAddress(record.get(2));
//            address.setCityProvincePostalCode(record.get(3));
//            address.setCountry(record.get(4));
//            address.setAddRemarks(record.get(5));
//            List<Address> addressList = addressMap.getOrDefault(Long.parseLong(record.get(0)), new ArrayList<>());
//            addressList.add(address);
//            addressMap.put(Long.parseLong(record.get(0)), addressList);
//        }
//        return addressMap;
//    }
//
//
//    private Map<Long, List<Aka>> getAkaMap() throws IOException {
//        CSVParser parser = CSVParser.parse(new InputStreamReader(new URL(ALT_URL).openStream(), StandardCharsets.UTF_8), CSVFormat.DEFAULT.withSkipHeaderRecord(true).withIgnoreEmptyLines(true));
//        Map<Long, List<Aka>> akaMap = new HashMap<>();
//        Iterator<CSVRecord> iterator = parser.iterator();
//        while (iterator.hasNext()) {
//            CSVRecord record = iterator.next();
//            if (record.size() == 1 && record.get(0).equals("\u001A")) {
//                // Skip the last record in the CSV file
//                break;
//            }
//            Aka aka = new Aka();
//            aka.setUid(Long.parseLong(record.get(0)));
//            aka.setId(aka.getId()); // set the auto-generated ID
//            aka.setAltNum(Integer.parseInt(record.get(1)));
//            aka.setAltType(record.get(2));
//            aka.setAltName(record.get(3));
//            aka.setAltRemarks(record.get(4));
//            List<Aka> akaList = akaMap.getOrDefault(Long.parseLong(record.get(0)), new ArrayList<>());
//            akaList.add(aka);
//            akaMap.put(Long.parseLong(record.get(0)), akaList);
//        }
//        return akaMap;
//    }
//
//    private Map<Long, List<SdnCommentsEntity>> getCommentsMap() throws IOException {
//        CSVParser parser = CSVParser.parse(new InputStreamReader(new URL(REMARKS_URL).openStream(), StandardCharsets.UTF_8), CSVFormat.DEFAULT.withSkipHeaderRecord(true).withIgnoreEmptyLines(true));
//        Map<Long, List<SdnCommentsEntity>> commentsMap = new HashMap<>();
//        Iterator<CSVRecord> iterator = parser.iterator();
//        while (iterator.hasNext()) {
//            CSVRecord record = iterator.next();
//            if (record.size() == 1 && record.get(0).equals("\u001A")) {
//                // Skip the last record in the CSV file
//                break;
//            }
//            SdnCommentsEntity comment = new SdnCommentsEntity();
//            comment.setUid(Long.parseLong(record.get(0)));
//            comment.setId(comment.getId());
//            comment.setRemarkExtended(record.get(1));
//            List<SdnCommentsEntity>commentList = commentsMap.getOrDefault(Long.parseLong(record.get(0)), new ArrayList<>());
//            commentList.add(comment);
//            commentsMap.put(Long.parseLong(record.get(0)), commentList);
//        }
//        return commentsMap;
//    }

    public List<GptSdnList> getGptSdnList() throws IOException {
        Map<Long, List<GptAddress>> addressMap = getAddressMap();
        Map<Long, List<GptAka>> akaMap = getAkaMap();
        Map<Long, List<GptSdnCommentsEntity>> commentsMap = getCommentsMap();
        CSVParser parser = CSVParser.parse(new InputStreamReader(new URL(SDN_URL).openStream(), StandardCharsets.UTF_8), CSVFormat.DEFAULT);
        List<GptSdnList> gptSdnList = new ArrayList<>();
        for (CSVRecord record : parser) {
            if (record.size() == 1 && record.get(0).equals("\u001A")) {
                // Skip the last record in the CSV file
                break;
            }
            GptSdnList sdn = new GptSdnList();
            sdn.setUid(Long.parseLong(record.get(0).trim()));
            sdn.setName(record.get(1).trim());
            sdn.setSdnType(record.get(2).trim());
            sdn.setProgram(record.get(3).trim());
            sdn.setTitle(record.get(4).trim());
            sdn.setCallSign(record.get(5).trim());
            sdn.setVessType(record.get(6).trim());
            sdn.setTonnage(record.get(7).trim());
            sdn.setGrt(record.get(8).trim());
            sdn.setVessFlag(record.get(9).trim());
            sdn.setVessOwner(record.get(10).trim());
            sdn.setRemarks(record.get(11).trim());
            sdn.setAddressList(addressMap.get(Long.parseLong(record.get(0).trim())));
            sdn.setAkaList(akaMap.get(Long.parseLong(record.get(0).trim())));
            List<GptSdnCommentsEntity> commentsList = commentsMap.get(Long.parseLong(record.get(0).trim()));
            if (commentsList != null && !commentsList.isEmpty()) {
                String remarksExtended = commentsList.get(0).getRemarkExtended();
                GptSdnCommentsEntity remarks = new GptSdnCommentsEntity();
                remarks.setRemarkExtended(remarksExtended);
                sdn.setRemarkExtended(remarks);
            }
            gptSdnList.add(sdn);
        }

        return gptSdnList;
    }

    private Map<Long,List<GptAddress>> getAddressMap() throws IOException {
        CSVParser parser = CSVParser.parse(new InputStreamReader(new URL(ADD_URL).openStream(), StandardCharsets.UTF_8), CSVFormat.DEFAULT.withSkipHeaderRecord(true).withIgnoreEmptyLines(true));
        Map<Long, List<GptAddress>> addressMap = new HashMap<>();
        Iterator<CSVRecord> iterator = parser.iterator();
        while (iterator.hasNext()) {
            CSVRecord record = iterator.next();
            if (record.size() == 1 && record.get(0).equals("\u001A")) {
                // Skip the last record in the CSV file
                break;
            }
            GptAddress address = new GptAddress();
            address.setUid(Long.parseLong(record.get(0)));
            address.setId(address.getId()); // set the auto-generated ID
            address.setAddressNum(Integer.parseInt(record.get(1)));
            address.setAddress(record.get(2));
            address.setCityProvincePostalCode(record.get(3));
            address.setCountry(record.get(4));
            address.setAddRemarks(record.get(5));
            List<GptAddress> addressList = addressMap.getOrDefault(Long.parseLong(record.get(0)), new ArrayList<>());
            addressList.add(address);
            addressMap.put(Long.parseLong(record.get(0)), addressList);
        }
        return addressMap;
    }


    private Map<Long, List<GptAka>> getAkaMap() throws IOException {
        CSVParser parser = CSVParser.parse(new InputStreamReader(new URL(ALT_URL).openStream(), StandardCharsets.UTF_8), CSVFormat.DEFAULT.withSkipHeaderRecord(true).withIgnoreEmptyLines(true));
        Map<Long, List<GptAka>> akaMap = new HashMap<>();
        Iterator<CSVRecord> iterator = parser.iterator();
        while (iterator.hasNext()) {
            CSVRecord record = iterator.next();
            if (record.size() == 1 && record.get(0).equals("\u001A")) {
                // Skip the last record in the CSV file
                break;
            }
            GptAka aka = new GptAka();
            aka.setUid(Long.parseLong(record.get(0)));
            aka.setId(aka.getId()); // set the auto-generated ID
            aka.setAltNum(Integer.parseInt(record.get(1)));
            aka.setAltType(record.get(2));
            aka.setAltName(record.get(3));
            aka.setAltRemarks(record.get(4));
            List<GptAka> akaList = akaMap.getOrDefault(Long.parseLong(record.get(0)), new ArrayList<>());
            akaList.add(aka);
            akaMap.put(Long.parseLong(record.get(0)), akaList);
        }
        return akaMap;
    }

    private Map<Long, List<GptSdnCommentsEntity>> getCommentsMap() throws IOException {
        CSVParser parser = CSVParser.parse(new InputStreamReader(new URL(REMARKS_URL).openStream(), StandardCharsets.UTF_8), CSVFormat.DEFAULT.withSkipHeaderRecord(true).withIgnoreEmptyLines(true));
        Map<Long, List<GptSdnCommentsEntity>> commentsMap = new HashMap<>();
        Iterator<CSVRecord> iterator = parser.iterator();
        while (iterator.hasNext()) {
            CSVRecord record = iterator.next();
            if (record.size() == 1 && record.get(0).equals("\u001A")) {
                // Skip the last record in the CSV file
                break;
            }
            GptSdnCommentsEntity comment = new GptSdnCommentsEntity();
            comment.setUid(Long.parseLong(record.get(0)));
            comment.setId(comment.getId());
            comment.setRemarkExtended(record.get(1));
            List<GptSdnCommentsEntity>commentList = commentsMap.getOrDefault(Long.parseLong(record.get(0)), new ArrayList<>());
            commentList.add(comment);
            commentsMap.put(Long.parseLong(record.get(0)), commentList);
        }
        return commentsMap;
    }




//    public List<DeliquentList> getDeliquent() throws FileNotFoundException {
//        List<DeliquentList> deliquentLists = new CsvToBeanBuilder<DeliquentList>(new FileReader(deliquentfile))
//                .withType(DeliquentList.class)
//                .build()
//                .parse();
//        return deliquentLists;
//    }

    @Transactional
    public void  getDeliquent(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        CsvToBean<DeliquentList> csvToBean = new CsvToBeanBuilder<DeliquentList>(reader)
                .withType(DeliquentList.class)
                .build();
        List<DeliquentList> deliquentLists = csvToBean.parse();
        // Save the contents of the CSV file to a database
        deliquentRepository.deleteAll();
        for (DeliquentList deliquentList : deliquentLists) {
            deliquentRepository.save(deliquentList);
        }
    }


//    @Transactional
//    public List<DeliquentList> parseDeliquentFile(MultipartFile file) throws IOException {
//        InputStream inputStream = file.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        CsvToBean<DeliquentList> csvToBean = new CsvToBeanBuilder<DeliquentList>(reader)
//                .withType(DeliquentList.class)
//                .build();
//        return csvToBean.parse();
//    }
File deliquentfile = new File(homedir, "Documents/sanction_data/delinquent_list_22122022(1) (2).csv");
    @Autowired
    private BusinessContinuityRepository businessContinuityRepository;
    @Autowired
    private GptSdnRepository gptSdnRepository;
    @Autowired
    private UkUpdateCheckRepository ukUpdateCheckRepository;
    @Autowired
    private EuUpdateCheckRepository euUpdateCheckRepository;
    @Autowired
    private PepUpdateCheckRepository pepUpdateCheckRepository;
    @Autowired
    private AdverserUpdateCheckRepository adverserUpdateCheckRepository;


    //BUSINES CONTUINTY
    @Transactional
    public List<BusinessContinuity> parseBusinessContuinity(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String firstLine = reader.readLine();
        if (firstLine != null && firstLine.trim().isEmpty()) {
            // First row is empty, skip it
            reader.readLine();
        }

        CsvToBean<BusinessContinuity> csvToBean = new CsvToBeanBuilder<BusinessContinuity>(reader)
                .withType(BusinessContinuity.class)
                .build();

        return csvToBean.parse();
    }
    @Transactional
    public void saveBusinessContuintyToDatabase(List<BusinessContinuity> businessContinuities) {
        businessContinuityRepository.deleteAll();
        businessContinuityRepository.saveAll(businessContinuities);
    }

    @Transactional
    public List<PepList> getPepListImport(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        CsvToBean<PepList> csvToBean = new CsvToBeanBuilder<PepList>(reader)
                .withType(PepList.class)
                .build();
        return csvToBean.parse();
    }


    @Transactional
    public List<NbeBacklist> parseBlackListFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        CsvToBean<NbeBacklist> csvToBean = new CsvToBeanBuilder<NbeBacklist>(reader)
                .withType(NbeBacklist.class)
                .build();
        return csvToBean.parse();
    }


//    @Transactional
//    public void saveDeliquentToDatabase(List<DeliquentList> deliquentLists) {
//        deliquentRepository.deleteAll();
//        deliquentRepository.saveAll(deliquentLists);
//    }



    public SanctionEntity findEUListById(String Id){return euRiskManagementRepository.findByEuSanctionCustomerId(Id);
    }
public List<NbeBacklist> getNbeBlacklistSearchResult(String fullName){
    List<NbeBacklist> nbeBacklistList = new ArrayList<>();
    if(nbeBackListRepository.getNbeBacklistByName(fullName) !=null){
        List<NbeBacklist> nbeBacklists = nbeBackListRepository.getNbeBacklistByName(fullName);
        for( NbeBacklist nbeBacklist : nbeBacklists){
            nbeBacklistList.add(nbeBacklist);
        }
    }
    return nbeBacklistList;
}
    public DataModel getUNEntitySearchResult(String fullName){
        DataModel dataModel = new DataModel();
        if(unEntityRiskManagementRepository.findByKeyword(fullName) !=null){
            List<UnEntity> unEntities = unEntityRiskManagementRepository.findByKeyword(fullName);
            for(UnEntity unEntity : unEntities){
                dataModel.setId(String.valueOf(unEntity.getId()));
                dataModel.setFirstName(unEntity.getFirstName());
                System.out.println("FirstName UN Entity: "+dataModel.getFirstName());
                dataModel.setLastName(unEntity.getThirdName());
                dataModel.setMiddleName(unEntity.getSecondName());
                dataModel.setWholeName(unEntity.getFirstName() +" "+ unEntity.getSecondName()+
                        " "+unEntity.getThirdName());
                dataModel.setRemark(unEntity.getComments1());
                dataModel.setFrom("UN ENTITY");
                dataModel.setLogicalId(unEntity.getDataId());
            }
            return dataModel;
        }
        return null;

    }

    public DataModel findPepByFull(String fullName){
        List<PepList> pepLists = pepListRepository.findByKeyword(fullName);
        DataModel dataModel = new DataModel();
        for(PepList pepList: pepLists) {
            dataModel.setId(String.valueOf(pepList.getId()));
            dataModel.setLogicalId(String.valueOf(pepList.getId()));
            dataModel.setCountry("Ethiopia");
            dataModel.setFrom("PEP LIST");
            dataModel.setWholeName(pepList.getNameInEng());
            System.out.println("WholeName: " + dataModel.getWholeName());
            dataModel.setNameInAhm(pepList.getNameInAmh());
            dataModel.setRemark(pepList.getDetails());
            return dataModel;
        }
        return dataModel;
    }
    public List<DataModel> getEUCustomerDetails(String Id){

     List<DataModel> dataModelList = new ArrayList<>();

    System.out.println("NameAliasId: "+Id);
    if(nameAliasRepository.getNameAliasId(Id) !=null) {

        String sanctionId = nameAliasRepository.getNameAliasId(Id);
        SanctionEntity sanctionEntity = euRiskManagementRepository.findByEuSanctionCustomerId(sanctionId);
        List<NameAlias> nameAlaisIdList = nameAliasRepository.getNameAliasBySanctionId(sanctionEntity.getId());
        System.out.println("NameAliasIdList: "+nameAlaisIdList);
        DataModel dataModel=null;
        for (NameAlias nameAlias : nameAliasRepository.getNameAliasBySanctionId(sanctionEntity.getId())) {
            dataModel =new DataModel();
                Citizenship citizenship = sanctionEntity.getCitizenship();
                if(citizenship !=null)  dataModel.setCountry(citizenship.getCountryDescription());
                Identification identification = sanctionEntity.getIdentification();
                if(identification !=null)dataModel.setPassportNumber(identification.getNumber());
                if(identification !=null)dataModel.setIdentificationType(identification.getIdentificationTypeDescription());
                if(identification !=null)dataModel.setIdentificationTypeCode(identification.getIdentificationTypeCode());
                Regulation regulation = sanctionEntity.getRegulation();
                if(regulation !=null) dataModel.setPublicationDate(regulation.getPublicationDate());
                dataModel.setId(sanctionEntity.getId());
                dataModel.setId(nameAlias.getId());
                dataModel.setWholeName(nameAlias.getWholeName());
                dataModel.setLastName(nameAlias.getLastName());
                dataModel.setNameLanguage(nameAlias.getNameLanguage());
                dataModel.setGender(nameAlias.getGender());
                dataModel.setFirstName(nameAlias.getFirstName());
                dataModel.setPublicationUrl(nameAlias.getRegulationSummary().getPublicationUrl());
                BirthDate birthDate = sanctionEntity.getBirthDate();
                if(birthDate !=null) dataModel.setDateOfBirth(birthDate.getBirthdate());
                System.out.println("UserDetails: "+sanctionEntity.getRemark());
                dataModel.setLogicalId(sanctionEntity.getLogicalId());
                dataModel.setFrom("EU");
                dataModel.setRemark(sanctionEntity.getRemark());
                dataModel.setNameInAhm("No Amharic Name");
                dataModel.setUniqueId("EU");
                dataModelList.add(dataModel);
            }


            System.out.println("DataModelList: "+dataModelList);
            return dataModelList;
        }else{
            return null;
       }
    }

    public int  getEuUpdateCounter() {
        Optional<EuUpdateCheck> optionalUpdateCheck = euUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            EuUpdateCheck updateCheck = optionalUpdateCheck.get();
            return updateCheck.getUpdateCheck();
        } else {
            throw new RuntimeException("No UkUpdateCheck found");
        }

    }

    public void setEuUpdateCounter(int newUpdateCounter) {
        Optional<EuUpdateCheck> optionalUpdateCheck = euUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            EuUpdateCheck updateCheck = optionalUpdateCheck.get();
            updateCheck.setUpdateCheck(newUpdateCounter);
            euUpdateCheckRepository.save(updateCheck);
        } else {
            throw new RuntimeException("No UKUpdateCheck found");
        }

    }
    public UKResponseDetail getUkCustomerDetails(Long Id){
      UKResponseDetail responseDetail =new UKResponseDetail();
        System.out.println("Named: "+Id);

        if(ukNameRepository.getNamesId(Id) !=null) {
            Genders genders = null;
            List<String> emailAddresses = null;
            List<String> dobs =null;
            Long namesId = ukNameRepository.getNamesId(Id);
            UkDesignation ukDesignation = ukRiskManagementRepository.getUkDesignationByNamesId(namesId);
            Long ukIndividualDetailId = ukRiskManagementRepository.getUkIndividualDetailId(namesId);
            Long addressessId = ukRiskManagementRepository.getUkAddressesId(namesId);
            Long emailAddressessId = ukRiskManagementRepository.getUkEmailAddressId(namesId);
            Long nonLationNamesId = ukRiskManagementRepository.getUkNonLatinNamesId(namesId);
            Long phoneNumbersId = ukRiskManagementRepository.getPhoneNumbersId(namesId);
            Long websitesId = ukRiskManagementRepository.getWebsitesId(namesId);
            Long titlesId = ukRiskManagementRepository.getTitlesId(namesId);
            Long namesIdfromUkDesignation = ukRiskManagementRepository.getUkNamesId(namesId);
            UkIndividual ukIndividual = ukIndividualRepository.getUkIndividualById(ukIndividualDetailId);
            Long ukNationalitiesId= ukIndividualRepository.getUKNationalitiesId(ukIndividual.getId());
            Long birthDetailsId= ukIndividualRepository.getBirthDetailsId(ukIndividual.getId());
            Long dobsId= ukIndividualRepository.getDobsId(ukIndividual.getId());
            Long gendersId=ukIndividualRepository.getGendersId(ukIndividual.getId());
            Long nationalIdentifierDetailsId=ukIndividualRepository.getNationalIdentifierDetailsId(ukIndividual.getId());
            Long passportDetailsId= ukIndividualRepository.getPassportDetailsId(ukIndividual.getId());
            Long positionsId= ukIndividualRepository.getPositionsId(ukIndividual.getId());


            List<String> positions= ukIndividualRepository.getPostions(positionsId);
            List<String> nationalities= ukIndividualRepository.getNationalities(ukNationalitiesId);
            List<NationalIdentifier> nationalIdentifiers = nationalIdentifierRepository.getNationalIdentifiers(nationalIdentifierDetailsId);
            List<Passport> passportList = ukPassportRepository.getPassports(passportDetailsId);
            List<Location> locationList = locationRepository.getBirthLocation(birthDetailsId);
            if(ukGenderRepository.getGenders(gendersId) !=null)
             genders= ukGenderRepository.getGenders(gendersId);
            List<NonLatinName> nonLatinNameList = nonLationNameRepository.getNonLatinNames(nonLationNamesId);
        if(ukRiskManagementRepository.getEmailAddresses(emailAddressessId) !=null)
            emailAddresses= ukRiskManagementRepository.getEmailAddresses(emailAddressessId);

           if(ukIndividualRepository.getDobs(dobsId) !=null)
               dobs = ukIndividualRepository.getDobs(dobsId);
            List<String> phoneNumbers =null;
            if(ukIndividualRepository.getPhoneNumbers(phoneNumbersId) !=null)
                phoneNumbers= ukIndividualRepository.getPhoneNumbers(phoneNumbersId);
            List<Name> nameIdList = null;
            if(ukNameRepository.getNameByNamesId(namesIdfromUkDesignation) !=null)
                nameIdList = ukNameRepository.getNameByNamesId(namesIdfromUkDesignation);
            List<UkAddress> addressList =null;

           if(ukAddressRepository.getUkAddresses(addressessId) !=null)
              addressList= ukAddressRepository.getUkAddresses(addressessId);
            List<String> titlelist = ukRiskManagementRepository.getTitles(titlesId);
            List<String> websiteList = ukRiskManagementRepository.getWebsites(websitesId);

           if(nameIdList !=null) responseDetail.setNames(nameIdList);
           if(positions !=null) responseDetail.setPosition(positions);
           if(passportList !=null) responseDetail.setPassportList(passportList);
           if(addressList !=null) responseDetail.setUkAddressList(addressList);
           if(ukDesignation !=null) responseDetail.setDateDesignated(ukDesignation.getDateDesignated());
          if(emailAddresses !=null)  responseDetail.setEmailAddress(emailAddresses);
            responseDetail.setTitles(titlelist);
            responseDetail.setWebsiteList(websiteList);
            responseDetail.setPhoneNumber(phoneNumbers);
            if(genders !=null)
            responseDetail.setGender(genders.getGender());
            if(ukDesignation !=null)
            responseDetail.setLastUpdated(ukDesignation.getLastUpdated());
            responseDetail.setDesignationSource(ukDesignation.getDesignationSource());

            responseDetail.setNationality(nationalities);
            responseDetail.setLocationList(locationList);
            responseDetail.setUnReferenceNumber(ukDesignation.getUNReferenceNumber());
            responseDetail.setUniqueId(ukDesignation.getUniqueID());
            responseDetail.setNationalIdentifierList(nationalIdentifiers);
            responseDetail.setUkStatementOfReasons(ukDesignation.getUKStatementofReasons());
            responseDetail.setOtherInformation(ukDesignation.getOtherInformation());
            responseDetail.setNonLatinNameList(nonLatinNameList);
            responseDetail.setTypeOfEntity("Not Done Yet");
            responseDetail.setDobs(dobs);
            responseDetail.setSanctionImposed(ukDesignation.getSanctionsImposed());
            responseDetail.setOFSIgroupId(ukDesignation.getOFSIGroupID());
            responseDetail.setRegimeName(ukDesignation.getRegimeName());

            for (Name name : nameIdList) {
           System.out.println("NameSix: "+name.getName6());
            }


            System.out.println("DataModelList: "+responseDetail);

if(responseDetail !=null)
            return responseDetail;
else
    return null;
        }else{
            return null;
        }
    }
public List<PepResponseDetail> getPepCustomerDetails(String Id){
     List<PepResponseDetail> pepResponseDetailList = new ArrayList<>();

     if(pepListRepository.findByCustomerId(Id) !=null){
         List<PepList> pepLists = pepListRepository.findByCustomerId(Id);
         for(PepList pepList: pepLists){
             PepResponseDetail pepResponseDetail = new PepResponseDetail();
             pepResponseDetail.setId(pepList.getId());
             pepResponseDetail.setNameInAmh(pepList.getNameInAmh());
             pepResponseDetail.setNameInEng(pepList.getNameInEng());
             pepResponseDetail.setDetails(pepList.getDetails());
             pepResponseDetail.setPosition(pepList.getPosition());
             pepResponseDetail.setPlaceOfAssignment(pepList.getPlaceOfAssignment());
             pepResponseDetailList.add(pepResponseDetail);
         }
     }
     return pepResponseDetailList;
}
    public int  getPepUpdateCounter() {
        Optional<PepUpdateCheck> optionalUpdateCheck = pepUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            PepUpdateCheck updateCheck = optionalUpdateCheck.get();
            return updateCheck.getUpdateCheck();
        } else {
            throw new RuntimeException("No Pep Update Check found");
        }

    }

    public void setPepUpdateCounter(int newUpdateCounter) {
        Optional<PepUpdateCheck> optionalUpdateCheck = pepUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            PepUpdateCheck updateCheck = optionalUpdateCheck.get();
            updateCheck.setUpdateCheck(newUpdateCounter);
            pepUpdateCheckRepository.save(updateCheck);
        } else {
            throw new RuntimeException("No UKUpdateCheck found");
        }

    }
public List<UnIndividualResponseDetail> getUnIndividualCustomerDetail(Long Id){
    List<UnIndividualResponseDetail> unIndividualResponseDetailList = new ArrayList<>();
    if (unIndividualRiskManagementRepository.getUnIndividualById(Id) != null) {
        List<UnIndividual> unIndividuals = unIndividualRiskManagementRepository.getUnIndividualById(Id);
        for(UnIndividual unIndividual: unIndividuals) {
            Long addressId  = unIndividualRiskManagementRepository.getIndividualAddressId(unIndividual.getId());
            Long documentId = unIndividualRiskManagementRepository.getUnIndividualDocumentId(unIndividual.getId());
            Long aliasId = unIndividualRiskManagementRepository.getUnIndividualAliasId(unIndividual.getId());
            Long birthDateid = unIndividualRiskManagementRepository.getBirtDateId(unIndividual.getId());
            Long nationalityId = unIndividualRiskManagementRepository.getUnNationalityId(unIndividual.getId());

            UnIndividualResponseDetail unIndividualResponseDetail =new UnIndividualResponseDetail();
            IndividualAlias individualAlias = unIndividualAliasRepository.getIndividualAliasById(aliasId);
            IndividualAddress individualAddress = unIndividualAddressRepository.getIndividualAddressById(addressId);
            IndividualDocument individualDocument = unIndividualDocumentRepository.getIndividualDocumentById(documentId);
            IndividualDateOfBirth individualDateOfBirth = unIndividualBirthDateRepository.getIndividualDateOfBirthById(birthDateid);
            Nationality nationality = unNationalityRepository.getNationalityById(nationalityId);
                unIndividualResponseDetail.setId(unIndividual.getId());
                unIndividualResponseDetail.setFirstName(unIndividual.getFirstName());
                unIndividualResponseDetail.setSecondName(unIndividual.getSecondName());
                unIndividualResponseDetail.setThirdName(unIndividual.getThirdName());
                unIndividualResponseDetail.setFourthName(unIndividual.getFourthName());
                unIndividualResponseDetail.setTitle(unIndividual.getTitle());
                unIndividualResponseDetail.setUnListType(unIndividual.getUnListType());
                unIndividualResponseDetail.setReferenceNumber(unIndividual.getReferenceNumber());
                unIndividualResponseDetail.setListedOn(unIndividual.getListedOn());
                unIndividualResponseDetail.setComments1(unIndividual.getComments1());
                unIndividualResponseDetail.setIndividualPlaceOfBirth(unIndividual.getIndividualPlaceOfBirth());
                unIndividualResponseDetail.setSortKey(unIndividual.getSortKey());
                unIndividualResponseDetail.setSortKeyLastMod(unIndividual.getSortKeyLastMod());
               if(nationality !=null){ unIndividualResponseDetail.setNationality(nationality.getValue());}else {
                   unIndividualResponseDetail.setNationality("NO DATA AVAILABLE");
               }
                unIndividualResponseDetail.setNameOriginalScript(unIndividual.getNameOriginalScript());
                unIndividualResponseDetail.setGender(unIndividual.getGender());
                unIndividualResponseDetail.setSubmittedBy(unIndividual.getSubmittedBy());
                if(individualAddress !=null) {
                    unIndividualResponseDetail.setCountry(individualAddress.getCountry());
                    unIndividualResponseDetail.setAddressNote(individualAddress.getNote());
                    unIndividualResponseDetail.setStateProvince(individualAddress.getStateProvince());
                    unIndividualResponseDetail.setStreet(individualAddress.getStreet());
                    unIndividualResponseDetail.setCity(individualAddress.getCity());
                    unIndividualResponseDetail.setZipCode(individualAddress.getZipCode());
                }

            if(individualAlias !=null) {
                unIndividualResponseDetail.setQuality(individualAlias.getQuality());
                unIndividualResponseDetail.setAliasName(individualAlias.getAliasName());
                unIndividualResponseDetail.setAliasNote(individualAlias.getNote());
                unIndividualResponseDetail.setDateOfBirth(individualAlias.getDateOfBirth());
                unIndividualResponseDetail.setCityOfBirth(individualAlias.getCityOfBirth());
                unIndividualResponseDetail.setCountryOfBirth(individualAlias.getCountryOfBirth());
            }
            if(individualDateOfBirth !=null) {
                unIndividualResponseDetail.setTypeOfDate(individualDateOfBirth.getTypeOfDate());
                unIndividualResponseDetail.setDate(individualDateOfBirth.getDate());
                unIndividualResponseDetail.setYear(individualDateOfBirth.getYear());
                unIndividualResponseDetail.setFromYear(individualDateOfBirth.getFromYear());
            }
           if(individualDocument !=null) {
               unIndividualResponseDetail.setTypeOfDocument(individualDocument.getTypeOfDocument());
               unIndividualResponseDetail.setNumber(individualDocument.getNumber());
               unIndividualResponseDetail.setIssuingCountry(individualDocument.getIssuingCountry());
               unIndividualResponseDetail.setDocumentStateProvince(individualDocument.getStateProvince());
               unIndividualResponseDetail.setDateOfIssue(individualDocument.getDateOfIssue());
               unIndividualResponseDetail.setCityOfIssue(individualDocument.getCityOfIssue());
           }
                unIndividualResponseDetailList.add(unIndividualResponseDetail);

        }

    }
    return unIndividualResponseDetailList;
}




}
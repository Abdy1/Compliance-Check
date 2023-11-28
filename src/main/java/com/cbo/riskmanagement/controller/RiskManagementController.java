package com.cbo.riskmanagement.controller;

import com.cbo.riskmanagement.model.*;
import com.cbo.riskmanagement.model.uk.UkDesignation;
import com.cbo.riskmanagement.model.us.*;
import com.cbo.riskmanagement.repository.UsersAddedThisWeekRepository;
import com.cbo.riskmanagement.model.adverser.Adverser;
import com.cbo.riskmanagement.model.adverser.AdverserResponseDetail;
import com.cbo.riskmanagement.model.eu.ExportedEntity;
import com.cbo.riskmanagement.model.eu.NameAlias;
import com.cbo.riskmanagement.model.eu.SanctionEntity;
import com.cbo.riskmanagement.model.nbeblacklist.NbeBacklist;
import com.cbo.riskmanagement.model.pep.PepList;
import com.cbo.riskmanagement.model.pep.PepResponseDetail;
import com.cbo.riskmanagement.model.uk.Designations;
import com.cbo.riskmanagement.model.uk.Name;
import com.cbo.riskmanagement.model.un.*;
import com.cbo.riskmanagement.repository.*;
import com.cbo.riskmanagement.service.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.xml.sax.SAXException;

@RestController
@RequestMapping(value = "/api/v1/")
public class RiskManagementController {
    // Get current date and time
    LocalDateTime now = LocalDateTime.now();

    // Format date and time
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = now.format(formatter);
    List<String> files = new ArrayList<>();
    private final Path rootLocation = get("/");
    @Autowired
    EuRiskManagementRepository euRiskManagementRepository;
    @Autowired
    NbeBackListRepository nbeBackListRepository;

    @Autowired
    Service service;

    @Autowired
    SanctionRepository sr;
    @Autowired
    OfacRepository ofacRepository;
    @Autowired
    RiskManagementService riskManagementService;
    @Autowired
    PepListRepository pepListRepository;
    @Autowired
    AdverserRepository adverserRepository;

    @Autowired
    WeeklyService weeklyService;

    @Autowired
    DeliquentRepository deliquentRepository;

    @Autowired
    UNEntityRiskManagementRepository unEntityRiskManagementRepository;

    @Autowired
    UNIndividualRiskManagementRepository unIndividualRiskManagementRepository;

    @Autowired
    sdnRepository sdnrepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    GptSdnRepository gptSdnRepository;

//    @Autowired
//    AkaRepository akaRepository;
//
//    @Autowired
//    SdnCommentsRepository sdnCommentsRepository;
//
//    @Autowired
//    AddressRepository addressRepository;
    @Autowired
    private Weekly weekly;
    @Autowired
    private UsersAddedThisWeekRepository usersAddedThisWeekRepository;
    @Autowired
    private ConsolidatedRepository consolidatedRepository;

    @Autowired
    private DeliquentService deliquentService;

    @Autowired
    private BusinessContinuityService businessContinuityService;


    // define a location
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";
    @Autowired
    private DeliquentRepositoryUpdated deliquentRepositoryUpdated;
    @Autowired
    private BusinessContinuityRepository businessContinuityRepository;
    @Autowired
    private UkUpdateCheckRepository ukUpdateCheckRepository;

    @Autowired
    private UNService unService;

    @Autowired
    OfacService ofacService;


    //abdydidit






    //returns UN individual list
    @RequestMapping(value = "un_all_individuals", produces = {"application/json"}, method = RequestMethod.GET)
    List  GetAllUnIndividuals()  throws XMLStreamException,IOException {

        return  riskManagementService.getConsolidatedEntityList().getIndividuals().getUnIndividualList();

    }

    @RequestMapping(value = "un_all_individuals_from_db", produces = {"application/json"}, method = RequestMethod.GET)
    List  GetAllUnIndividualsFromDb() {

        return  unIndividualRiskManagementRepository.findAll();

    }

    // Read all UN individuals from file or database
    @GetMapping(value = "un_all_individuals_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getAllUnIndividuals() {
        try {

            return ResponseEntity.ok(riskManagementService.getConsolidatedEntityList().getIndividuals().getUnIndividualList());
        } catch (Exception e) {

            return getAllUnIndividualsFromDb();
        }
    }

    @GetMapping(value = "un_all_individuals_from_db_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getAllUnIndividualsFromDb() {
        try {
            System.out.println("now i smy turn");
            return ResponseEntity.ok(unIndividualRiskManagementRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @GetMapping("/checkUnUpdate")
    public int CheckUn() {
        return unService.getUpdateCounter();
    }




    // returns UN entity list
    @RequestMapping(value = "un_all_entities", produces = {"application/json"}, method = RequestMethod.GET)
    List  GetAllUnEntities()  throws XMLStreamException,IOException {

        return  riskManagementService.getConsolidatedEntityList().getEntities();

    }

    @RequestMapping(value = "un_all_entities_from_db", produces = {"application/json"}, method = RequestMethod.GET)
    List  GetAllUnEntitiesFromDb()   {

        return  unEntityRiskManagementRepository.findAll();

    }

    // Read all UN entities from file or database
    @GetMapping(value = "un_all_entities_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getAllUnEntities() {
        try {
            return ResponseEntity.ok(riskManagementService.getConsolidatedEntityList().getEntities());
        } catch (Exception e) {
            return getAllUnEntitiesFromDb();
        }
    }

    @GetMapping(value = "un_all_entities_from_db_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getAllUnEntitiesFromDb() {
        try {

            return ResponseEntity.ok(unEntityRiskManagementRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }


    @Transactional
    @RequestMapping(value = "save-un", produces = {"application/json"}, method = RequestMethod.POST)
    void saveUn() throws XMLStreamException,IOException {
        consolidatedRepository.deleteAll();
        consolidatedRepository.saveAll(Collections.singletonList(riskManagementService.getConsolidatedEntityList()));

    }

    // Endpoint for reading SanctionEntity records from XML file
    //significantly faster
    @GetMapping("/eu_all")
    public List<SanctionEntity> readSanctionEntityFromXML() throws XMLStreamException, IOException {
        return riskManagementService.readSanctionEntityFromXML().getSanctionEntity();
    }
    @GetMapping("/eu_all_from_db")
    public ResponseEntity<List<SanctionEntity>> getAllSanctionEntities() {
        try {
            List<SanctionEntity> sanctionEntities = riskManagementService.getAllSanctionEntities();
            return ResponseEntity.ok(sanctionEntities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }
    // Read all EU entities from file or database
    @GetMapping(value = "eu_all_optimized", produces = {"application/json"})
    public ResponseEntity<?> getEuEntities() {
        try {
            return ResponseEntity.ok(riskManagementService.readSanctionEntityFromXML().getSanctionEntity());
        } catch (Exception e) {
            return getEuEntitiesFromDb();
        }
    }

    @GetMapping(value = "eu_all_from_db_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getEuEntitiesFromDb() {
        try {
            return ResponseEntity.ok(riskManagementService.getAllSanctionEntities());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    // Endpoint for saving SanctionEntity records to database
    @Transactional
    @PostMapping("/save-eu")
    public void saveSanctionEntityToDatabase() throws XMLStreamException, IOException {
        ExportedEntity exportedEntity = riskManagementService.readSanctionEntityFromXML();
        euRiskManagementRepository.deleteAll();
        euRiskManagementRepository.saveAll(exportedEntity.getSanctionEntity());
    }
    @Transactional
    @PostMapping(path = "/import-eu-xml-to-db")
    public void importEuXmlToListDb(@RequestPart(required = true) MultipartFile file) throws IOException, XMLStreamException {
        ExportedEntity exportedEntity = riskManagementService.parseEuXmlFile(file);
        euRiskManagementRepository.deleteAll();
        euRiskManagementRepository.saveAll(exportedEntity.getSanctionEntity());
    }
    @PostMapping(path = "/import-eu-xml-to-db-atf")
    @Transactional
    public ResponseEntity<?> importEuXmlToDb(@RequestPart(required = true) MultipartFile file) throws IOException, XMLStreamException {

        int euChecker = riskManagementService.getEuUpdateCounter()  +1;
        riskManagementService.setEuUpdateCounter(euChecker);

        ExportedEntity exportedEntity = riskManagementService.parseEuXmlFile(file);
        try {
            euRiskManagementRepository.deleteAll();
            euRiskManagementRepository.saveAll(exportedEntity.getSanctionEntity());
            // Save the file to the desired directory location
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            File homedir = new File(System.getProperty("user.home"));
            File directory = new File(homedir, "Documents/sanction_data/");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File fileObj = new File(directory, fileName);


            if (!fileObj.exists()) {
                System.out.println("The file does not exist");

                // Create a new file
                boolean fileCreated = fileObj.createNewFile();
                if (fileCreated) {
                    System.out.println("The file was created successfully");
                } else {
                    System.out.println("Failed to create the file");
                }
            }


            if (fileObj.canWrite()) {
                System.out.println("The file is writable");
                FileOutputStream fos = new FileOutputStream(fileObj);
                fos.write(file.getBytes());
                fos.close();
            } else {
                System.out.println("The file is not writable");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing eu  list  file: the file is not writable");
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing eu   list  file: " + e.getMessage());
        }
    }

    @GetMapping("/checkEuUpdate")
    public int CheckEu() {

        return riskManagementService.getEuUpdateCounter();
    }




    // THIS IS TO READ ALL EU DIRECTLY FROM DATABASE SLOWER BUT I THINK TAKE LESS PROCESSING


    // Endpoint for reading Designations from XML file
    @GetMapping("/uk_all")
    public List<UkDesignation> readDesignationsFromXML() throws XMLStreamException, IOException {
        return riskManagementService.readDesignationsFromXML().getUkDesignation();
    }


// READ FROM DATABASE
    @GetMapping(value = "uk_all_from_db", produces = {"application/json"})
    public ResponseEntity<List<UkDesignation>> getAllUkDesignations() {
        try {
            List<UkDesignation> ukDesignations = riskManagementService.getAllUkDesignations();
            return ResponseEntity.ok(ukDesignations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }
    // Read all UK entities from file or database
    @GetMapping(value = "uk_all_optimized")
    public ResponseEntity<?> getUkEntities() {
        try {
            return ResponseEntity.ok(riskManagementService.readDesignationsFromXML().getUkDesignation());
        } catch (Exception e) {
            return getUkEntitiesFromDb();
        }
    }

    @GetMapping(value = "uk_all_from_db_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getUkEntitiesFromDb() {
        try {
            return ResponseEntity.ok(riskManagementService.getAllUkDesignations());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @PostMapping(value = "save-uk", produces = {"application/json"})
    public ResponseEntity<Void> saveDesignationsToDb() {
        try {
            Designations designations = riskManagementService.loadDesignationsFromXml();
            riskManagementService.saveDesignationsToDb(designations);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional
    @PostMapping(path = "/import-uk-xml-to-db")
    public  ResponseEntity<Void> importUkListXmlToDb(@RequestPart(required = true) MultipartFile file) throws IOException, XMLStreamException {
        try {
            Designations designations = riskManagementService.parseUkXmlFile(file);
            riskManagementService.saveDesignationsToDb(designations);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //flaghere
    @PostMapping(path = "/import-uk-xml-to-db-atf")
    @Transactional
    public ResponseEntity<?> importUkXmlToDb(@RequestPart(required = true) MultipartFile file) throws IOException, XMLStreamException {

        int ukChecker = riskManagementService.getUkUpdateCounter()  +1;
        riskManagementService.setUkUpdateCounter(ukChecker);

        Designations designations = riskManagementService.parseUkXmlFile(file);
        try {
            euRiskManagementRepository.deleteAll();
            riskManagementService.saveDesignationsToDb(designations);
            // Save the file to the desired directory location
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            File homedir = new File(System.getProperty("user.home"));
            File directory = new File(homedir, "Documents/sanction_data/");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File fileObj = new File(directory, fileName);


            if (!fileObj.exists()) {
                System.out.println("The file does not exist");

                // Create a new file
                boolean fileCreated = fileObj.createNewFile();
                if (fileCreated) {
                    System.out.println("The file was created successfully");
                } else {
                    System.out.println("Failed to create the file");
                }
            }


            if (fileObj.canWrite()) {
                System.out.println("The file is writable");
                FileOutputStream fos = new FileOutputStream(fileObj);
                fos.write(file.getBytes());
                fos.close();
            } else {
                System.out.println("The file is not writable");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing adverser  list  file: the file is not writable");
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing adverser  list  file: " + e.getMessage());
        }
    }

    @GetMapping("/checkUkUpdate")
    public int CheckUk() {

        return riskManagementService.getUkUpdateCounter();
    }



    @GetMapping(value = "adverser_all", produces = {"application/json"})
    List GetAllAdverser() throws FileNotFoundException, XMLStreamException, IOException {
        return  riskManagementService.getAdverser();


    }

    @GetMapping(value = "adverser_all_from_db", produces = {"application/json"})
    List GetAllAdverserFromDb() throws FileNotFoundException, XMLStreamException, IOException {
        return  adverserRepository.findAll();


    }

    // Read all Adverser entities from file or database
    @GetMapping(value = "adverser_all_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getAllAdverser() {
        try {
            return ResponseEntity.ok(riskManagementService.getAdverser());
        } catch (Exception e) {
            return getAllAdverserFromDb();
        }
    }

    @GetMapping(value = "adverser_all_from_db_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getAllAdverserFromDb() {
        try {

            return ResponseEntity.ok(adverserRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @PostMapping(value = "save-adverser", produces = {"application/json"})
    List saveAdverserToDatabase() throws FileNotFoundException {
        return riskManagementService.saveAdverserToDatabase();
    }
    @Transactional
    @PostMapping(path = "/import-adverser-to-db")
    public void importAdverserList(@RequestPart(required = true) MultipartFile file) throws IOException {
        List<Adverser> adversers= riskManagementService.getAdverserListImport(file);
        adverserRepository.deleteAll();
        adverserRepository.saveAll(adversers);

    }
    @PostMapping(path = "/import-adverser-to-db-atf")
    @Transactional
    public ResponseEntity<?> importAdverser(@RequestPart(required = true) MultipartFile file) throws IOException {

        int adverserChecker = riskManagementService.getAdverserUpdateCounter()  +1;
        riskManagementService.setAdverserUpdateCounter(adverserChecker);

        List<Adverser> adversers= riskManagementService.getAdverserListImport(file);
        try {
            adverserRepository.deleteAll();
            adverserRepository.saveAll(adversers);
            // Save the file to the desired directory location
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            File homedir = new File(System.getProperty("user.home"));
            File directory = new File(homedir, "Documents/sanction_data/");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File fileObj = new File(directory, fileName);


            if (!fileObj.exists()) {
                System.out.println("The file does not exist");

                // Create a new file
                boolean fileCreated = fileObj.createNewFile();
                if (fileCreated) {
                    System.out.println("The file was created successfully");
                } else {
                    System.out.println("Failed to create the file");
                }
            }


            if (fileObj.canWrite()) {
                System.out.println("The file is writable");
                FileOutputStream fos = new FileOutputStream(fileObj);
                fos.write(file.getBytes());
                fos.close();
            } else {
                System.out.println("The file is not writable");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing adverser  list  file: the file is not writable");
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing adverser  list  file: " + e.getMessage());
        }
    }

    @GetMapping("/checkAdverserUpdate")
    public int CheckAdverser() {

        return riskManagementService.getAdverserUpdateCounter();
    }


    @GetMapping(value = "black_list_all")
    List<NbeBacklist> saveNbeBacklist() throws FileNotFoundException {

        return riskManagementService.getNbeBacklist();

    }
    @GetMapping(value = "black_list_all_from_db")
    List<NbeBacklist> saveNbeBacklistFromDb() throws FileNotFoundException {

        return nbeBackListRepository.findAll();

    }
    // Read all NBE Blacklist entities from file or database
    @GetMapping(value = "black_list_all_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getNbeBlacklist() {
        try {
            return ResponseEntity.ok(riskManagementService.getNbeBacklist());
        } catch (Exception e) {
            return getNbeBlacklistFromDb();
        }
    }

    @GetMapping(value = "black_list_all_from_db_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getNbeBlacklistFromDb() {
        try {

            return ResponseEntity.ok(nbeBackListRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @PostMapping(value = "black_list-save", produces = {"application/json"})
    List<NbeBacklist> saveNbeBacklistToDatabase() throws FileNotFoundException {
        return riskManagementService.saveNbeBacklistToDatabase();
    }
    @PostMapping(path = "/import-black_list-to-db")
    public void importBlackList(@RequestPart(required = true) MultipartFile file) throws IOException {
        List<NbeBacklist> nbeBacklists = riskManagementService.parseBlackListFile(file);
        riskManagementService.saveNbeBacklistToDatabaseFromFile(nbeBacklists);
    }
    @PostMapping(path = "/import-back_list-to-db-atf")
    @Transactional
    public ResponseEntity<?> importBlack(@RequestPart(required = true) MultipartFile file) throws IOException {
        List<NbeBacklist> nbeBacklists = riskManagementService.parseBlackListFile(file);
        try {
            riskManagementService.saveNbeBacklistToDatabaseFromFile(nbeBacklists);
            // Save the file to the desired directory location
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            File homedir = new File(System.getProperty("user.home"));
            File directory = new File(homedir, "Documents/sanction_data/");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File fileObj = new File(directory, fileName);


            if (!fileObj.exists()) {
                System.out.println("The file does not exist");

                // Create a new file
                boolean fileCreated = fileObj.createNewFile();
                if (fileCreated) {
                    System.out.println("The file was created successfully");
                } else {
                    System.out.println("Failed to create the file");
                }
            }


            if (fileObj.canWrite()) {
                System.out.println("The file is writable");
                FileOutputStream fos = new FileOutputStream(fileObj);
                fos.write(file.getBytes());
                fos.close();
            } else {
                System.out.println("The file is not writable");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing nbe black list  file: the file is not writable");
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing nbe black list  file: " + e.getMessage());
        }
    }


//DELIQUENT STARTS HERE


    @PostMapping(path = "/import-deliquent-to-db-atf1")
    @Transactional
    public ResponseEntity<?> importDeliquent1(@RequestPart(required = true) MultipartFile file) throws IOException {
        try {
            deliquentService.parseDeliquentFile(file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing delinquent file: " + e.getMessage());
        }
    }

    @PostMapping(path = "/import-excel-deliquent-to-db-atf1")
    @Transactional
    public ResponseEntity<?> importDeliquent12(@RequestPart(required = true) MultipartFile file) throws IOException {
        try {
            deliquentService.parseDeliquentFileE(file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing delinquent file: " + e.getMessage());
        }
    }


    @GetMapping(value = "deliquent_list_all_from_db_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getDeliquentListFromDb() {
        try {

            return ResponseEntity.ok(deliquentRepositoryUpdated.findAll());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }
    @Transactional
    @PostMapping("/create_deliquent_user")
    public DeliquentListUpdate createUser(@RequestBody DeliquentListUpdate user) {
        // Save the user object to the database
        user.setRlog_create_date_time(formattedDateTime);
//        sendStringToFrontend("deliquent_updated");

        int DeliquentupdateChecker = deliquentService.getUpdateCounter()  +1;
        deliquentService.setUpdateCounter(DeliquentupdateChecker);
        Long lastElementId = deliquentRepositoryUpdated.findLastElementId(PageRequest.of(0, 1)).get(0)+1;


        user.setDelinquent_list_id(lastElementId);

        return deliquentRepositoryUpdated.save(user);
    }

    @GetMapping(value = "get_deliquent_by_id/{id}", produces = {"application/json"})
    public ResponseEntity<String> getDeliquentListFromDb(@PathVariable Long id) {
        try {
            Optional<DeliquentListUpdate> deliquent = deliquentRepositoryUpdated.findById(id);
            if (deliquent.isPresent()) {
                String customerName = deliquent.get().getCustomer_name();
                return ResponseEntity.ok(customerName);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @GetMapping(value = "get_deliquent_by_tin/{tin}", produces = {"application/json"})
    public ResponseEntity<String> getDeliquentListByTin(@PathVariable String tin) {
        try {
            DeliquentListUpdate deliquent = deliquentRepositoryUpdated.findByTinAccount(tin);
            if (deliquent != null) {
                String customerName = deliquent.getCustomer_name();
                return ResponseEntity.ok(customerName);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }


    // Annotate the method as a DELETE mapping
    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete_deliquent_user/{id}")
    public void deleteUser(@PathVariable Long id) {
        // Delete the user object from the database by id
        int DeliquentupdateChecker = deliquentService.getUpdateCounter()  +1;
        deliquentService.setUpdateCounter(DeliquentupdateChecker);

        deliquentRepositoryUpdated.deleteById(id);
//        sendStringToFrontend("deliquent_updated");

    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete_deliquent_user_by_tin/{tin}")
    public void deleteUserByTin(@PathVariable String tin) {
        // Delete the user object from the database by id
        int DeliquentupdateChecker = deliquentService.getUpdateCounter()  +1;
        deliquentService.setUpdateCounter(DeliquentupdateChecker);

        deliquentRepositoryUpdated.deleteByTin_Account(tin);
//        sendStringToFrontend("deliquent_updated");

    }

    @GetMapping("/checkDeliquentUpdate")
    public int CheckDeliquent() {

        return deliquentService.getUpdateCounter();
    }
//DELIQUENT ENDS HERE
    //IMPORT BUSINESS CONTUINITY XLSX FILE
    @PostMapping(path = "/import-to-db-business")
    public void importBusinessContiunity(@RequestPart(required = true) List<MultipartFile> files) {

        businessContinuityService.importBusinessContinuityToDb(files);

    }


    @GetMapping(value = "business_continuity_list_all_from_db_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getBusinessContinuity() {
        try {

            return ResponseEntity.ok(businessContinuityRepository.findAll());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }
    @Transactional
    @PostMapping("/create_business_continuity_user")
    public BusinessContinuity createUser(@RequestBody BusinessContinuity user) {
        // Save the user object to the database
//        sendStringToFrontend("businessContinuity_updated");
        int BusinessContinuityUpdateChecker = businessContinuityService.getUpdateCounter()  +1;
        businessContinuityService.setUpdateCounter(BusinessContinuityUpdateChecker);
        user.setRlog_create_date_time(formattedDateTime);
        Long lastElementId = businessContinuityRepository.findLastElementId(PageRequest.of(0, 1)).get(0)+1;


        user.setDelinquent_list_id(lastElementId);
        return businessContinuityRepository.save(user);
    }

    @PostMapping("upload-id-file")
    public ResponseEntity<List<String>> uploadIdFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            System.out.println("FileName: "+filename);
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
        }
        return ResponseEntity.ok().body(filenames);
    }


    // Annotate the method as a DELETE mapping
    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete_businessc_user/{id}")
    public void deleteBusinessContuinityUser(@PathVariable Long id) {
        // Delete the user object from the database by id
        int BusinessContinuityUpdateChecker = businessContinuityService.getUpdateCounter()  +1;
        businessContinuityService.setUpdateCounter(BusinessContinuityUpdateChecker);
        businessContinuityRepository.deleteById(id);
//        sendStringToFrontend("businessContinuity_updated");

    }
    @GetMapping(value = "get_business_continuity_by_id/{id}", produces = {"application/json"})
    public ResponseEntity<String> getBusinessContinuityFromDb(@PathVariable Long id) {
        try {
            Optional<BusinessContinuity> businessContinuity = businessContinuityRepository.findById(id);
            if (businessContinuity.isPresent()) {
                String customerName = businessContinuity.get().getCustomer_name();
                return ResponseEntity.ok(customerName);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }


    //REFERENCE NUMBER IS NOT UNIQUE SO COMMENT OUT
//    @GetMapping(value = "get_business_continuity_by_refNo/{refno}", produces = {"application/json"})
//    public ResponseEntity<String> getBusinessContinuityByRefno(@PathVariable String refno) {
//        String originalRefno = refno.replace("_", "/");
//        try {
//            BusinessContinuity bc = businessContinuityRepository.findByRefno(originalRefno);
//            if (bc != null) {
//                String customerName = bc.getCustomer_name();
//                return ResponseEntity.ok(customerName);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
//
//        }
//    }


    @GetMapping("/checkBusinessContinuityUpdate")
    public int Checkcheck() {

        return businessContinuityService.getUpdateCounter();
    }


//    @GetMapping(value = "ofac_all")
//    List<OfacSanction> getOfacList() throws FileNotFoundException {
//        return riskManagementService.getOfacSanction();
//    }
//
//    @GetMapping(value = "ofac_all_from_db")
//    List<OfacSanction> getOfacListFromDb()  {
//        return ofacRepository.findAll();
//    }

    @GetMapping(value = "ofac_all_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getOfacList() {
        try {
            return ResponseEntity.ok(riskManagementService.getGptSdnList());
        } catch (Exception e) {
            return getOfacListFromDb();
        }
    }
    // Read all OFAC entities from file or database

    @GetMapping(value = "ofac_all_from_db_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getOfacListFromDb() {
        try {

            return ResponseEntity.ok(gptSdnRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }


    @GetMapping(value = "getdate", produces = {"application/json"})
    public void  getdate() throws IOException, ParserConfigurationException, SAXException {
        unService.checkUNUpdate();

    }



//    @GetMapping(value = "ofac_all_from_db_optimized", produces = {"application/json"})
//    public ResponseEntity<List<?>> getOfacListFromDb() {
//        try {
//            List<SdnList> sdnList = sdnrepository.getSdnListWithAddressAndAka();
//
//            return ResponseEntity.ok(sdnList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
//        }
//    }

//    @Transactional
//    @GetMapping(value = "save-ofac")
//    void saveOfacList() throws IOException {
//        List<SdnList> sdnListlist = riskManagementService.getSdnList();
//        ofacRepository.deleteAll();
//
//        for (SdnList sdn : sdnListlist) {
//            // Merge the Address entity into the persistence context
//            Address address = sdn.getAddressList().get(0);
//            sdn.setAddressList(Collections.singletonList(entityManager.merge(address)));
//
//            // Merge the Aka entities into the persistence context
//            List<Aka> akaList = sdn.getAkaList();
//            List<Aka> newAkaList = new ArrayList<>();
//            if (akaList != null) {
//                for (Aka aka : akaList) {
//                    newAkaList.add(entityManager.merge(aka));
//                }
//            }
//            sdn.setAkaList(newAkaList);
//
//            // Persist the SdnList entity
//            entityManager.persist(sdn);
//
//            // Create and persist a SdnCommentsEntity entity that relates to the SdnList entity
//            SdnCommentsEntity comments = new SdnCommentsEntity();
//            comments.setSdnList(sdn);
//            comments.setRemarksExtended("Some remarks");
//            entityManager.persist(comments);
//
//            // Merge the SdnCommentsEntity into the persistence context
//            sdn.setRemarkExtended(entityManager.merge(comments));
//        }
//
//        // Save the changes to the database
//        entityManager.flush();
//    }


    @GetMapping("/save-ofac")
    public void saveSdnList() throws IOException {
         List<GptSdnList> sdnList = riskManagementService.getGptSdnList();
          sdnrepository.deleteAllInBatch();
          sdnrepository.saveAll(sdnList);

    }

//    @GetMapping("/save-ofac")
//    public void saveSdnList() throws IOException {
//        List<SdnList> sdnList = riskManagementService.getSdnList();
//        sdnrepository.deleteAllInBatch();
//
//        for (SdnList sdn : sdnList) {
//            SdnList savedSdn = sdnrepository.save(sdn);
//
//            // set uid for Address entities
//            List<Address> addressList = sdn.getAddressList();
//            if (addressList != null) {
//                for (Address address : addressList) {
//                    address.setUid(savedSdn.getUid());
//                    addressRepository.save(address);
//                }
//            }
//
//            // set uid for Aka entities
//            List<Aka> akaList = sdn.getAkaList();
//            if (akaList != null) {
//                for (Aka aka : akaList) {
//                    aka.setUid(savedSdn.getUid());
//                    akaRepository.save(aka);
//                }
//            }
//
//            // set uid for SdnCommentsEntity
//            SdnCommentsEntity sdnComments = sdn.getRemarkExtended();
//            if (sdnComments != null) {
//                sdnComments.setUid(savedSdn.getUid());
//                sdnCommentsRepository.save(sdnComments);
//            }
//        }
//    }
    //FORMER OFAC LOGIC

//    @GetMapping(value = "ofac-sanctions")
//    List<OfacSanction> checkOfacList() throws FileNotFoundException {
//        OfacSanction ofacSanctionData =null;
//        List<OfacSanction> ofacSanctionList= riskManagementService.getOfacSanction();
//        for(OfacSanction ofacSanction: ofacSanctionList){
//            ofacRepository.save(ofacSanction);
//            System.out.println("Ofac Name: "+ " "+ ofacSanction.getName());
//            System.out.println("Ofac Sanctions: "+ " "+ ofacSanction.getSanctions());
//            ofacSanctionData=ofacSanction;
//        }
//        if(ofacSanctionList !=null)
//            return ofacSanctionList;
//        else
//            return null;
//
//    }

    @GetMapping("/checkOfacUpdate")
    public int CheckOfac() {
        return ofacService.getUpdateCounter();
    }

    @GetMapping(value = "pep_all")
    List<PepList> getPept() throws FileNotFoundException {
        return riskManagementService.getPepList();
    }

    @GetMapping(value = "pep_all_from_db")
    List<PepList> getOfacListFromDbs() {
        return pepListRepository.findAll();
    }
    // Read all PEP entities from file or database
    @GetMapping(value = "pep_all_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getPepList() {
        try {
            return ResponseEntity.ok(riskManagementService.getPepList());
        } catch (Exception e) {
            return getPepListFromDb();
        }
    }

    @GetMapping(value = "pep_all_from_db_optimized", produces = {"application/json"})
    public ResponseEntity<List<?>> getPepListFromDb() {
        try {

            return ResponseEntity.ok(pepListRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @Transactional
    @GetMapping(value = "save-pep")
    void  checkPepList() throws FileNotFoundException {

        List<PepList> pepLists= riskManagementService.getPepList();
        pepListRepository.deleteAll();
        for(PepList pepList: pepLists){
            pepListRepository.save(pepList);

        }


    }
    @Transactional
    @PostMapping(path = "/import-pep-to-db")
    public void importPepList(@RequestPart(required = true) MultipartFile file) throws IOException {
        List<PepList> pepLists= riskManagementService.getPepListImport(file);
        pepListRepository.deleteAll();
        pepListRepository.saveAll(pepLists);

    }
    @PostMapping(path = "/import-pep-to-db-atf")
    @Transactional
    public ResponseEntity<?> importPep(@RequestPart(required = true) MultipartFile file) throws IOException {


        int pepCecker = riskManagementService.getPepUpdateCounter()  +1;
        riskManagementService.setPepUpdateCounter(pepCecker);

        List<PepList> pepLists= riskManagementService.getPepListImport(file);
        try {
            pepListRepository.deleteAll();
            pepListRepository.saveAll(pepLists);
            // Save the file to the desired directory location
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            File homedir = new File(System.getProperty("user.home"));
            File directory = new File(homedir, "Documents/sanction_data/");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File fileObj = new File(directory, fileName);


            if (!fileObj.exists()) {
                System.out.println("The file does not exist");

                // Create a new file
                boolean fileCreated = fileObj.createNewFile();
                if (fileCreated) {
                    System.out.println("The file was created successfully");
                } else {
                    System.out.println("Failed to create the file");
                }
            }


            if (fileObj.canWrite()) {
                System.out.println("The file is writable");
                FileOutputStream fos = new FileOutputStream(fileObj);
                fos.write(file.getBytes());
                fos.close();
            } else {
                System.out.println("The file is not writable");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing pep list  file: the file is not writable");
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing pep  list  file: " + e.getMessage());
        }
    }
    @GetMapping("/checkPepUpdate")
    public int CheckPep() {

        return riskManagementService.getPepUpdateCounter();
    }



    //FORMER PEP LOGIC

//    @GetMapping(value = "pep-list")
//    List<PepList> checkPepList() throws FileNotFoundException {
//        PepList pepListData =null;
//        List<PepList> pepLists= riskManagementService.getPepList();
//        for(PepList pepList: pepLists){
//            pepListRepository.save(pepList);
//            System.out.println("PepList Name: "+ " "+ pepList.getNameInAmh());
//            System.out.println("PepList Name: "+ " "+ pepList.getNameInEng());
//            pepListData=pepList;
//        }
//        if(pepLists !=null)
//            return pepLists;
//        else
//            return null;
//
//    }

    //returns UN individual list
    //abdydiditends

    @RequestMapping(value = "un-entity-sanction", produces = {"application/json"}, method = RequestMethod.GET)
    Consolidated consolidatedEntity()  throws XMLStreamException,IOException {
            return  riskManagementService.getConsolidatedEntityList();
    }

    @RequestMapping(value = "un-individual-sanction", produces = {"application/json"})
    Consolidated consolidatedIndividual()  throws XMLStreamException,IOException {
        return  riskManagementService.getConsolidatedIndividualList();
    }

//    @GetMapping(value = "eu-sanction", produces = {"application/json"})
//    ExportedEntity exportedEntity() throws XMLStreamException, IOException{
//        return riskManagementService.getSanctionEntity();
//    }
    @GetMapping(value = "eu-sanction-list")
    ResponseEntity<List<SanctionEntity>> getEuSanctionEntityList(){
        return ResponseEntity.ok(riskManagementService.getEuSanctionEntity());
    }
    //@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "uk-sanction", produces = {"application/json"})
    Designations designations() throws XMLStreamException, IOException{
        return riskManagementService.getDesignations();
    }

    @RequestMapping(value = "search-sanction/{fullName}", method = RequestMethod.GET)
    List<PepResponseDetail> searchPEPListSanction(@PathVariable("fullName") String fullName){
        System.out.println("FullName: "+fullName);
        return riskManagementService.getPEPSearchResult(fullName);
    }
    @RequestMapping(value = "search-nbe-black/{fullName}", method = RequestMethod.GET)
    List<NbeBacklist> searchNbeBackList(@PathVariable("fullName") String fullName){
        System.out.println("FullName: "+fullName);
        return riskManagementService.getNbeBlacklistSearchResult(fullName);
    }
    @RequestMapping(value = "search-uk-sanction/{fullName}", method = RequestMethod.GET)
    ResponseEntity<List<Name>> searchUkListSanction(@PathVariable("fullName") String fullName){
        System.out.println("FullName: "+fullName);
        return ResponseEntity.ok(riskManagementService.getUKSanctionSearchResult(fullName));
    }
    @GetMapping("/look/{fullname}")
    public ResponseEntity<List<UNSanctions>> getLogByPhoneNumber(@PathVariable(name = "fullname" ,required = false) String fullname){


        if(fullname.equals("novalue")) {
            System.out.println("String is null ");
            fullname = null;
        }

        Optional<List<UNSanctions>> data = sr.findByFullNameContaining(fullname);

        if(data.isPresent()){
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @RequestMapping(value = "search-adverser-sanction/{fullName}", method = RequestMethod.GET)
    List<AdverserResponseDetail> searchAdverserListSanction(@PathVariable("fullName") String fullName){
        System.out.println("FullName: "+fullName);
        return riskManagementService.getAdverserSearchResult(fullName);
    }
    @RequestMapping(value = "search-eu-sanction/{fullName}", method = RequestMethod.GET)
    List<NameAlias> searchEUListSanction(@PathVariable("fullName") String fullName){
        return riskManagementService.getEUSearchResult(fullName);
    }


    @RequestMapping(value = "search-un-entity-sanction/{fullName}", method = RequestMethod.GET)
    DataModel searchUNEntityListSanction(@PathVariable("fullName") String fullName){
        return riskManagementService.getUNEntitySearchResult(fullName);
    }
    @RequestMapping(value = "search-un-individual-sanction/{fullName}", method = RequestMethod.GET)
    List<UnIndividualResponseDetail> searchUNIndividualListSanction(@PathVariable("fullName") String fullName){
        return riskManagementService.getUNIndividualSearchResult(fullName);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET )
    String search(){
       return  "Nothing";
    }

    @PostMapping(path = "/import-to-db")
        public void importTransactionsFromExcelToDb(@RequestPart(required = true) List<MultipartFile> files) {

        		weeklyService.importToDb(files);

        	}
//    @PostMapping(path = "/import-deliquent-to-db")
//    public void importDeliquent(@RequestPart(required = true) MultipartFile files) throws IOException {
//
////        riskManagementService.getDeliquent((MultipartFile) files);
//        riskManagementService.getDeliquent(files);
//
//
//    }

    @GetMapping(value = "check")
    List<UsersAddedThisWeek> check()  {


            return weekly.findCommonNames();


    }

    @GetMapping(value = "adverser-list")
    Adverser checkAdverser() throws FileNotFoundException {
        Adverser adverserListData =null;
        List<Adverser> adverserList= riskManagementService.getAdverser();
        for(Adverser adverser: adverserList){
            adverserRepository.save(adverser);
            System.out.println("adverser Name: "+ " "+ adverser.getName());
            System.out.println("adverser Position: "+ " "+ adverser.getPosition());
            adverserListData=adverser;
        }
        if(adverserListData !=null)
            return adverserListData;
        else
            return null;

    }




    // Define a method to upload files

    @PostMapping("upload-sanction-file")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            System.out.println("FileName: "+filename);
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
        }
        return ResponseEntity.ok().body(filenames);
    }
    // Define a method to download files

    @GetMapping("download-sanction-file/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "eu-details/{Id}")
    List<DataModel> getEUCustomerDetail(@PathVariable("Id")String Id){
        System.out.println("ControllerSanctionId: "+Id);
       return riskManagementService.getEUCustomerDetails(Id);
         }



    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "uk-details/{Id}")
    UKResponseDetail getUkCustomerDetail(@PathVariable("Id")Long Id){
        System.out.println("ControllerSanctionId: "+Id);
        return riskManagementService.getUkCustomerDetails(Id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "pep-details/{Id}")
    List<PepResponseDetail> getUkCustomerDetail(@PathVariable("Id")String Id){
        System.out.println("ControllerSanctionId: "+Id);
        return riskManagementService.getPepCustomerDetails(Id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "un-individual-details/{Id}")
    List<UnIndividualResponseDetail> getUnIndividualCustomerDetail(@PathVariable("Id")Long Id){
        System.out.println("ControllerSanctionId: "+Id);
        return riskManagementService.getUnIndividualCustomerDetail(Id);
    }

    @GetMapping("/look-id/{DataId}")
    public ResponseEntity<List<UNSanctions>> getUnSanctionById(@PathVariable(name = "DataId" ,required = false) String dataid){


        if(dataid.equals("novalue")) {
            System.out.println("String is null ");
            dataid = null;
        }

        Optional<List<UNSanctions>> data = sr.findById(dataid);

        if(data.isPresent()){
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

}

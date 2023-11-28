package com.cbo.riskmanagement.service;


import com.cbo.riskmanagement.model.BusinessContinuity;
import com.cbo.riskmanagement.model.BusinessContinuityUpdateCheck;
import com.cbo.riskmanagement.model.DeliquentListUpdate;
import com.cbo.riskmanagement.model.DeliquentUpdateCheck;
import com.cbo.riskmanagement.repository.BusinessContinuityRepository;
import com.cbo.riskmanagement.repository.BusinessContinuityUpdateCheckRepository;
import com.cbo.riskmanagement.repository.DeliquentRepositoryUpdated;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessContinuityService {
    @Autowired
    BusinessContinuityRepository businessContinuityRepository;
    @Autowired
    private BusinessContinuityUpdateCheckRepository businessContinuityUpdateCheckRepository;


    @Transactional
    public void importBusinessContinuityToDb(List<MultipartFile> multipartFiles) {
        if (!multipartFiles.isEmpty()) {
            MultipartFile multipartFile = multipartFiles.get(0);
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
                XSSFSheet sheet = workbook.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();

                for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                    XSSFRow row = sheet.getRow(rowIndex);

                    Long id = getCellValueAsLong(row.getCell(0));
                    if (id != null) {
                        BusinessContinuity existingBusinessContinuity = businessContinuityRepository.findById(id).orElse(null);

                        if (existingBusinessContinuity != null) {
                            // Update existing record
                            existingBusinessContinuity.setRlog_create_date_time(getCellValueAsString(row.getCell(2)));
                            existingBusinessContinuity.setRlog_edit_date_time(getCellValueAsString(row.getCell(6)));
                            existingBusinessContinuity.setCustomer_name(getCellValueAsString(row.getCell(10)));
                            existingBusinessContinuity.setMother_name(getCellValueAsString(row.getCell(11)));
                            existingBusinessContinuity.setNbe_reference(getCellValueAsString(row.getCell(12)));
                            existingBusinessContinuity.setAccount_number(getCellValueAsString(row.getCell(13)));
                            existingBusinessContinuity.setData_closed(getCellValueAsString(row.getCell(14)));
                            existingBusinessContinuity.setBank(getCellValueAsString(row.getCell(15)));
                            existingBusinessContinuity.setBranch(getCellValueAsString(row.getCell(16)));
                            existingBusinessContinuity.setRemark(getCellValueAsString(row.getCell(17)));
                            // Update other columns of existingBusinessContinuity

                            businessContinuityRepository.save(existingBusinessContinuity);
                        } else {
                            // Create new record
                            BusinessContinuity newBusinessContinuity = new BusinessContinuity();
                            newBusinessContinuity.setDelinquent_list_id(id);
                            newBusinessContinuity.setRlog_create_date_time(getCellValueAsString(row.getCell(2)));
                            newBusinessContinuity.setRlog_edit_date_time(getCellValueAsString(row.getCell(6)));
                            newBusinessContinuity.setCustomer_name(getCellValueAsString(row.getCell(10)));
                            newBusinessContinuity.setMother_name(getCellValueAsString(row.getCell(11)));
                            newBusinessContinuity.setNbe_reference(getCellValueAsString(row.getCell(12)));
                            newBusinessContinuity.setAccount_number(getCellValueAsString(row.getCell(13)));
                            newBusinessContinuity.setData_closed(getCellValueAsString(row.getCell(14)));
                            newBusinessContinuity.setBank(getCellValueAsString(row.getCell(15)));
                            newBusinessContinuity.setBranch(getCellValueAsString(row.getCell(16)));
                            newBusinessContinuity.setRemark(getCellValueAsString(row.getCell(17)));
                            // Set other columns of newBusinessContinuity

                            businessContinuityRepository.save(newBusinessContinuity);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        CellType cellType = cell.getCellType();
        if (cellType == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cellType == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cellType == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else {
            return "";
        }
    }

    private Long getCellValueAsLong(Cell cell) {
        if (cell == null) {
            return null;
        }

        CellType cellType = cell.getCellType();
        if (cellType == CellType.NUMERIC) {
            return (long) cell.getNumericCellValue();
        } else {
            return null;
        }
    }

    public int getUpdateCounter() {
        Optional<BusinessContinuityUpdateCheck> businessContinuityUpdateCheck = businessContinuityUpdateCheckRepository.findById(1L);

        if (businessContinuityUpdateCheck.isPresent()) {
            BusinessContinuityUpdateCheck updateCheck = businessContinuityUpdateCheck.get();
            return updateCheck.getUpdateCheck();
        } else {
            throw new RuntimeException("No Business Continuity  found");
        }
    }

    public void setUpdateCounter(int businessContinuityUpdateChecker) {
        Optional<BusinessContinuityUpdateCheck> optionalUpdateCheck = businessContinuityUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            BusinessContinuityUpdateCheck updateCheck = optionalUpdateCheck.get();
            updateCheck.setUpdateCheck(businessContinuityUpdateChecker);
            businessContinuityUpdateCheckRepository.save(updateCheck);
        } else {
            throw new RuntimeException("No Business Continuity Update  Check found");
        }

    }
}

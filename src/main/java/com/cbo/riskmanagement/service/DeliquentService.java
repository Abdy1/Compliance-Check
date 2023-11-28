package com.cbo.riskmanagement.service;


import com.cbo.riskmanagement.model.DeliquentListUpdate;
import com.cbo.riskmanagement.model.DeliquentUpdateCheck;
import com.cbo.riskmanagement.repository.DeliquentRepository;
import com.cbo.riskmanagement.repository.DeliquentRepositoryUpdated;
import com.cbo.riskmanagement.repository.DeliquentUpdateCheckRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeliquentService {
    @Autowired
    DeliquentRepositoryUpdated deliquentRepositoryUpdated;

    @Autowired
    DeliquentUpdateCheckRepository deliquentUpdateCheckRepository;
    @Autowired
    private DeliquentRepository deliquentRepository;


    @Transactional
    public List<DeliquentListUpdate> parseDeliquentFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String firstLine = reader.readLine();
        if (firstLine != null && firstLine.trim().isEmpty()) {
            // First row is empty, skip it
            reader.readLine();
        }

        CsvToBean<DeliquentListUpdate> csvToBean = new CsvToBeanBuilder<DeliquentListUpdate>(reader)
                .withType(DeliquentListUpdate.class)
                .build();
//        saveDeliquentToDatabase(csvToBean.parse());
        deliquentRepositoryUpdated.deleteAll();
        deliquentRepositoryUpdated.saveAll(csvToBean.parse());
        return csvToBean.parse();
    }
    @Transactional
    public List<DeliquentListUpdate> parseDeliquentFileE(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<DeliquentListUpdate> deliquentListUpdates = new ArrayList<>();

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null || row.getCell(0) == null || row.getCell(0).getCellType() != CellType.NUMERIC) {
                // Skip empty rows and column definitions
                continue;
            }
            DeliquentListUpdate deliquentListUpdate = new DeliquentListUpdate();
            deliquentListUpdate.setDelinquent_list_id((long) row.getCell(0).getNumericCellValue());
//            deliquentListUpdate.setRlog_create_date_time(row.getCell(2).getStringCellValue());

            if (DateUtil.isCellDateFormatted(row.getCell(2))) {
                deliquentListUpdate.setRlog_create_date_time(row.getCell(2).getDateCellValue().toString());
            } else {
                deliquentListUpdate.setRlog_create_date_time(row.getCell(2).getStringCellValue());
            }


            deliquentListUpdate.setRlog_create_user_name(row.getCell(5).getStringCellValue());



            if (row.getCell(6) != null) {
                if (row.getCell(6).getCellType() == CellType.NUMERIC) {
                    if (DateUtil.isCellDateFormatted(row.getCell(6))) {
                        // Cell is numeric and contains a date value, read its value as a date
                        Date date = row.getCell(6).getDateCellValue();
                        deliquentListUpdate.setRlog_edit_date_time(date.toString());
                    } else {
                        // Cell is numeric but does not contain a date value, read its value as a number
                        double value = row.getCell(6).getNumericCellValue();
                        deliquentListUpdate.setRlog_edit_date_time(String.valueOf(value));
                    }
                } else {
                    // Cell is not numeric, read its value as a string
                    String value = row.getCell(6).getStringCellValue();
                    deliquentListUpdate.setRlog_edit_date_time(value);
                }
            } else {
                // Cell is null, handle it accordingly
            }


            if (row.getCell(10) != null) {
                deliquentListUpdate.setCustomer_name(row.getCell(10).getStringCellValue());
            }

//            deliquentListUpdate.setNbe_reference(row.getCell(11).getStringCellValue());

            if (row.getCell(11) != null) {
                if(row.getCell(11).getCellType() == CellType.NUMERIC){
                    deliquentListUpdate.setNbe_reference(String.valueOf(row.getCell(11).getNumericCellValue()));
                } else {
                    deliquentListUpdate.setNbe_reference(row.getCell(11).getStringCellValue());
                }
                // Cell is not null, read its value

            } else {
                // Cell is null, handle it accordingly
            }



            if (row.getCell(12) != null) {
                if (row.getCell(12).getCellType() == CellType.NUMERIC) {
                    // Cell is numeric, read its value as a number
                    double value = row.getCell(12).getNumericCellValue();
                    if (value >= 0 && String.valueOf(value).length() < 10) {
                        // Value is non-negative and has less than 10 digits, read it as a long with leading zeros
                        String stringValue = String.format("%010d", (long) value);
                        deliquentListUpdate.setTin_Account(stringValue);
                    } else {
                        // Value is negative or has 10 or more digits, read it as a string
                        String stringValue = String.valueOf(value);
                        deliquentListUpdate.setTin_Account(stringValue);
                    }
                } else {
                    // Cell is not numeric, read its value as a string
                    String value = row.getCell(12).getStringCellValue();
                    deliquentListUpdate.setTin_Account(value);
                }
            } else {
                // Cell is null, handle it accordingly
            }






            if (row.getCell(13) != null) {
                if (row.getCell(13).getCellType() == CellType.NUMERIC) {
                    if (DateUtil.isCellDateFormatted(row.getCell(13))) {
                        // Cell is numeric and contains a date value, read its value as a date
                        Date date = row.getCell(13).getDateCellValue();
                        deliquentListUpdate.setData_closed(date.toString());
                    } else {
                        // Cell is numeric but does not contain a date value, read its value as a number
                        double value = row.getCell(13).getNumericCellValue();
                        deliquentListUpdate.setData_closed(String.valueOf(value));
                    }
                } else {
                    // Cell is not numeric, read its value as a string
                    String value = row.getCell(13).getStringCellValue();
                    deliquentListUpdate.setData_closed(value);
                }
            } else {
                // Cell is null, handle it accordingly
            }


            if (row.getCell(14) != null) {
                // Cell is not null, read its value
                deliquentListUpdate.setBank(row.getCell(14).getStringCellValue());
            }

            if (row.getCell(15) != null) {
                // Cell is not null, read its value
                deliquentListUpdate.setBranch(row.getCell(15).getStringCellValue());
            }

            if (row.getCell(16) != null) {
                if(row.getCell(16).getCellType() == CellType.NUMERIC){
                    double value = row.getCell(16).getNumericCellValue();
                    deliquentListUpdate.setRemark(String.valueOf(value));
                } else {
                    deliquentListUpdate.setRemark(row.getCell(16).getStringCellValue());
                }
                // Cell is not null, read its value

            }






            deliquentListUpdates.add(deliquentListUpdate);
        }

        inputStream.close();
        workbook.close();

        deliquentRepositoryUpdated.deleteAll();
        deliquentRepositoryUpdated.saveAll(deliquentListUpdates);

        return deliquentListUpdates;
    }



    @Transactional
    public void  deleteUser(MultipartFile file) throws IOException {

    }

    public int  getUpdateCounter() {
        Optional<DeliquentUpdateCheck> optionalUpdateCheck = deliquentUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            DeliquentUpdateCheck updateCheck = optionalUpdateCheck.get();
            return updateCheck.getUpdateCheck();
        } else {
            throw new RuntimeException("No DeliquentUpdateCheck found");
        }

    }

    public void setUpdateCounter(int newUpdateCounter) {
        Optional<DeliquentUpdateCheck> optionalUpdateCheck = deliquentUpdateCheckRepository.findById(1L);

        if (optionalUpdateCheck.isPresent()) {
            DeliquentUpdateCheck updateCheck = optionalUpdateCheck.get();
            updateCheck.setUpdateCheck(newUpdateCounter);
            deliquentUpdateCheckRepository.save(updateCheck);
        } else {
            throw new RuntimeException("No DeliquentUpdateCheck found");
        }

    }
}

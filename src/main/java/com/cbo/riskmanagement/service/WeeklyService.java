package com.cbo.riskmanagement.service;

import com.cbo.riskmanagement.repository.Weekly;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cbo.riskmanagement.model.UsersAddedThisWeek;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeeklyService {
    @Autowired
  private Weekly weekly;
    @Transactional
    public void importToDb(List<MultipartFile> multipartfiles) {
        if (!multipartfiles.isEmpty()) {
            List<UsersAddedThisWeek> usersAddedThisWeek = new ArrayList<>();
            MultipartFile multipartfile = multipartfiles.get(0);
            try {
                XSSFWorkbook workBook = new XSSFWorkbook(multipartfile.getInputStream());
                XSSFSheet sheet = workBook.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();

                for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {

                    XSSFRow row = sheet.getRow(rowIndex);
                    String customerName = row.getCell(0).getStringCellValue();


                    UsersAddedThisWeek userAddedThisWeek = UsersAddedThisWeek.builder().customerName(customerName).build();

                    usersAddedThisWeek.add(userAddedThisWeek);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!usersAddedThisWeek.isEmpty()) {
               weekly.deleteAll();
                weekly.saveAll(usersAddedThisWeek);
            }
        }
    }


}

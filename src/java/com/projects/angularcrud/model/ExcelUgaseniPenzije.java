/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.model;

import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;
 
/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */
public class ExcelUgaseniPenzije extends AbstractExcelView {
 
    @Override
    protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        List<ExcelBook> listBooks = (List<ExcelBook>) model.get("excelPenzije");
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("PIO");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header = sheet.createRow(0);
         
        header.createCell(0).setCellValue("Filijala");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("ID_Penzije");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("IME");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("JMBG");
        header.getCell(3).setCellStyle(style);
        
        header.createCell(4).setCellValue("IZNOS");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("PARTIJA");
        header.getCell(5).setCellStyle(style);
        // create data rows
        int rowCount = 1;
         
        for (ExcelBook aBook : listBooks) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(aBook.getKolona1());
            aRow.createCell(1).setCellValue(aBook.getKolona2());
            aRow.createCell(2).setCellValue(aBook.getKolona3());
            aRow.createCell(3).setCellValue(aBook.getKolona4());
            aRow.createCell(4).setCellValue(aBook.getKolona5());
            aRow.createCell(5).setCellValue(aBook.getKolona6());
        }
    }
}
 

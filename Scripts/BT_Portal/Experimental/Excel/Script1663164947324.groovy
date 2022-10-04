import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook

import com.kms.katalon.core.configuration.RunConfiguration



String page_title ='Real User' 



String filename = RunConfiguration.getProjectDir()+ '/Data Files/' + page_title + '.xls'
//creating an instance of HSSFWorkbook class
HSSFWorkbook workbook = new HSSFWorkbook();
//invoking creatSheet() method and passing the name of the sheet to be created
HSSFSheet sheet = workbook.createSheet("page_title");
//creating the 0th row using the createRow() method
HSSFRow rowhead = sheet.createRow((short)0);
//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method
rowhead.createCell(0).setCellValue("Error2");
FileOutputStream fileOut = new FileOutputStream(filename);
workbook.write(fileOut);
//closing the Stream
fileOut.close();
//closing the workbook
workbook.close();
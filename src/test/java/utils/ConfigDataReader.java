package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataReader {
    public static Properties prop = new Properties();
    public static String EMAIL="",PASSWORD="";
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;


    public static void excelFileReader() {
        String filePath = "src/main/resources/AmazonData.xlsx";
        try {
            FileInputStream file = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = workbook.getSheet("amazon_sheet");

        // Get Physical Row Count
        int totalRows = sheet.getPhysicalNumberOfRows();

        for (int i = 1; i < totalRows; i++) {
            for (int j = 0; j <= 1; j++) {
                if (j == 0) {
                    EMAIL =  sheet.getRow(i).getCell(j).getStringCellValue();
                } else if (j == 1) {
                    PASSWORD =  sheet.getRow(i).getCell(j).getStringCellValue();
                }
            }
        }
    }


    public static void configFileReader() {
        try{
            String filePath = "src/main/resources/config.properties";
            FileInputStream file = new FileInputStream(filePath);
            prop.load(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getConfigValue(String key){
        return prop.getProperty(key);
    }

}


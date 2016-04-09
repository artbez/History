import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

/**
 * Created by artemiibezguzikov on 06.04.16.
 */
public class XMLReader {

    File myFile = null;
    String[][] res = null;

    String alignment_line(int number, String string){
        while(string.length() < number){
            string = " " + string;
        }
        return string;
    }

    double roundResult (double d, int precise) {
        precise = 10 ^ precise;
        d = d * precise;
        int i = (int) Math.round(d);
        return (double) i / precise;

    }

    String[][] getRes() {
        return res;
    }

    public XMLReader(String fileName, int num) {
        myFile = new File(fileName);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(myFile);
            // Finds the workbook instance for XLSX file
            XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);

            // Return first sheet from the XLSX workbook
            XSSFSheet mySheet = myWorkBook.getSheetAt(num);
            FormulaEvaluator evaluator = myWorkBook.getCreationHelper().createFormulaEvaluator();

            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = mySheet.iterator();
            // Traversing over each row of XLSX file
            res = new String[mySheet.getPhysicalNumberOfRows()][];
            int x = 0;
            int y = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                res[x] = new String[row.getPhysicalNumberOfCells()];
                y = 0;

                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_FORMULA:
                            evaluator.evaluateFormulaCell(cell);
                            double cur = cell.getNumericCellValue();
                            double newDouble = new BigDecimal(cur).setScale(8, RoundingMode.UP).doubleValue();
                            res[x][y] = String.valueOf(newDouble);
                            //    System.out.print(alignment_line(15, String.valueOf(newDouble)));
                            //   System.out.print(Main.alignment_line(15, cell.getCellFormula()));
                            break;
                        case Cell.CELL_TYPE_STRING:
                            //    System.out.print(alignment_line(15, cell.getStringCellValue()));
                            res[x][y] = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            //    System.out.print(alignment_line(15, String.valueOf(cell.getNumericCellValue())));
                            res[x][y] = String.valueOf(cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            //    System.out.print(alignment_line(15, String.valueOf(cell.getBooleanCellValue())));
                            res[x][y] = String.valueOf(cell.getNumericCellValue());
                            break;
                        default:
                    }
                    y++;
                } //System.out.println("");
                x++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

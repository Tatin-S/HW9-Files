import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipParsingTests {
    private final ClassLoader cl = ZipParsingTests.class.getClassLoader();

    @Test
    @DisplayName("Проверка наличия pdf-файла в архиве")
    void pdfParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("files.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("PDF_test.pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertEquals(59, pdf.numberOfPages);
                    return;
                }
            }
            Assertions.fail("Файл не найден");
        }
    }

    @Test
    @DisplayName("Проверка заголовков xlsx-таблицы")
    void xlsxParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("files.zip"))
        )) {
            ZipEntry entry;
            XLS xls = null;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("XLSX_test.xlsx")) {
                    xls = new XLS(zis);
                    Assertions.assertEquals("ID", xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue());
                    Assertions.assertEquals("Тема", xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue());
                    Assertions.assertEquals("Статус", xls.excel.getSheetAt(0).getRow(0).getCell(2).getStringCellValue());
                    Assertions.assertEquals("Приоритет", xls.excel.getSheetAt(0).getRow(0).getCell(3).getStringCellValue());

                    return;
                }
            }
            Assertions.fail("Файл не найден");
        }
    }


        @Test
        @DisplayName("Проверка наличия нужного csv файла в архиве")
        void csvFileParsingTest () throws Exception {

            try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("files.zip"))) {
                ZipEntry entry;

                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().equals("CSV_test.csv")) {
                        CSVReader csv = new CSVReader(new InputStreamReader(zis));
                        List<String[]> data = csv.readAll();

                        Assertions.assertArrayEquals(new String[]{"ID;subject;status;Working group"}, data.get(0));
                        return;
                    }
                }
                Assertions.fail("Файл не найден");
            }
        }
    }




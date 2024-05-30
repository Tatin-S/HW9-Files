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

import static org.assertj.core.api.Assertions.assertThat;
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
}



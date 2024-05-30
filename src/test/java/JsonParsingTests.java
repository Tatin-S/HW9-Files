import com.fasterxml.jackson.databind.ObjectMapper;
import model.Cat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;
public class JsonParsingTests {
    ClassLoader cl = JsonParsingTests.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Проверка содержимого Json файла")
    void jsonParsingTests() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("Cat.json")
        ) {
            assert resource != null;
            try (InputStreamReader reader = new InputStreamReader(resource)
            ) {
                Cat json = objectMapper.readValue(reader, Cat.class);
                assertThat(json.getName()).isEqualTo("Masik");
                assertThat(json.getBreed()).isEqualTo("Maine Coon");
                assertThat(json.getProperties()).contains(
                        "fluffy",
                        "gray",
                        "purrs");
            }
        }
    }
}

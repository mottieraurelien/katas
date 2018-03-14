package representation;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import model.Part;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class RepresentationTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final String PART_JSON = "fixtures/part.json";
    private static final String TEST_PART_NAME = "testPartName";
    private static final String TEST_PART_CODE = "testPartCode";

    @Test
    public void serializesToJson() throws Exception {
        final Part part = new Part(1, TEST_PART_NAME, TEST_PART_CODE);

        final String expected =
                MAPPER.writeValueAsString(MAPPER.readValue(fixture(PART_JSON), Part.class));

        assertThat(MAPPER.writeValueAsString(part)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJson() throws Exception {
        final Part part = new Part(1, TEST_PART_NAME, TEST_PART_CODE);

        assertThat(MAPPER.readValue(fixture(PART_JSON), Part.class).getId()).isEqualTo(part.getId());
        assertThat(MAPPER.readValue(fixture(PART_JSON), Part.class).getName())
                .isEqualTo(part.getName());
        assertThat(MAPPER.readValue(fixture(PART_JSON), Part.class).getCode())
                .isEqualTo(part.getCode());
    }
}

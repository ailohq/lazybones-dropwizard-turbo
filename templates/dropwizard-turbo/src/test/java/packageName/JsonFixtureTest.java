package ${packageName};

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;

public abstract class JsonFixtureTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    public static <T> T readFromJson(String path, Class<T> objectClass) {
        try {
            return MAPPER.readValue(fixture(path), objectClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

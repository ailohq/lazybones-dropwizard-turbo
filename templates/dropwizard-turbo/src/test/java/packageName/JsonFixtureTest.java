package ${packageName};

import com.example.model.${domainName};
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

    protected ${domainName} given${domainName}WithId(final Long id) {
        final ${domainName} ${domainSnakeName} = readFromJson("fixtures/${domainSnakeName}.json", ${domainName}.class);
        ${domainSnakeName}.setId(id);
        return ${domainSnakeName};
    }
}

package ${packageName}.health;

import com.hubspot.dropwizard.guice.InjectableHealthCheck;

public class ${domainName}HealthCheck extends InjectableHealthCheck {
    @Override
    public String getName() {
        return "Lazybones Health";
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}

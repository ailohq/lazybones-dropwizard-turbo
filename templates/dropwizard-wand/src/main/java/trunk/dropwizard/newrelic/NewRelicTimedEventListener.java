package trunk.dropwizard.newrelic;

import com.newrelic.api.agent.NewRelic;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

import java.lang.reflect.Method;
import java.util.Map;

class NewRelicTimedEventListener implements RequestEventListener {

    private final Map<Method, String> methodMap;

    public NewRelicTimedEventListener(Map<Method, String> methodMap) {
        this.methodMap = methodMap;
    }

    @Override
    public void onEvent(RequestEvent event) {

        if (event.getType() == RequestEvent.Type.RESOURCE_METHOD_START) {
            Method method = event.getUriInfo().getMatchedResourceMethod().getInvocable().getDefinitionMethod();
            NewRelic.setTransactionName("dropwizard", methodMap.get(method));
        }
    }

    public Map<Method, String> getMethodMap() {
        return methodMap;
    }
}

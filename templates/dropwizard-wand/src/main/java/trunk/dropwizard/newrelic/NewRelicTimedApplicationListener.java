package trunk.dropwizard.newrelic;

import com.codahale.metrics.annotation.Timed;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

import javax.inject.Singleton;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Provider
public class NewRelicTimedApplicationListener implements ApplicationEventListener {

    private Map<Method, String> methodMap = new HashMap<>();

    @Override
    public void onEvent(ApplicationEvent event) {
        if (event.getType() == ApplicationEvent.Type.INITIALIZATION_APP_FINISHED) {
            for (Resource resource : event.getResourceModel().getResources()) {
                resource.getAllMethods().forEach(this::registerUnitOfWorkAnnotations);

                for (Resource childResource : resource.getChildResources()) {
                    childResource.getAllMethods().forEach(this::registerUnitOfWorkAnnotations);
                }
            }
        }
    }

    @Override
    public RequestEventListener onRequest(RequestEvent requestEvent) {
        return new NewRelicTimedEventListener(methodMap);
    }

    private void registerUnitOfWorkAnnotations(ResourceMethod resourceMethod) {
        final Method method = resourceMethod.getInvocable().getDefinitionMethod();
        Timed annotation = method.getAnnotation(Timed.class);

        if (annotation != null) {
            final String resourceName = method.getDeclaringClass().getSimpleName();
            final String methodName = method.getName();
            this.methodMap.put(method, resourceName + "/" + methodName);
        }
    }

}

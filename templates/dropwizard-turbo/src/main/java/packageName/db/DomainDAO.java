package ${packageName}.db;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import ${packageName}.model.${domainName};

import javax.inject.Inject;
import java.util.List;

public class ${domainName}DAO extends AbstractDAO<${domainName}> {

    @Inject
    public ${domainName}DAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void create(${domainName} ${domainSnakeName}) {
        currentSession().persist(${domainSnakeName});
    }

    public List<${domainName}> readAll() {
        return list(currentSession().createCriteria(${domainName}.class));
    }

    public Optional<${domainName}> readByName(String name) {
        return Optional.fromNullable(get(name));
    }
}

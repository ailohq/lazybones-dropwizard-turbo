package ${packageName}.db;

import java.util.Optional;
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

    public ${domainName} create(${domainName} ${domainSnakeName}) {
        return persist(${domainSnakeName});
    }

    public List<${domainName}> readAll() {
        return list(currentSession().createCriteria(${domainName}.class));
    }

    public Optional<${domainName}> readById(Long id) {
        return Optional.ofNullable(get(id));
    }
}

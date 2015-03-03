package packageName.db;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import packageName.model.Wand;

import javax.inject.Inject;
import java.util.List;

public class WandDAO extends AbstractDAO<Wand> {

    @Inject
    public WandDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void create(Wand wand) {
        currentSession().persist(wand);
    }

    public List<Wand> readAll() {
        return list(currentSession().createCriteria(Wand.class));
    }

    public Optional<Wand> readByName(String name) {
        return Optional.fromNullable(get(name));
    }
}

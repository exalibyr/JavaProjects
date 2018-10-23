package dao.impl.templates;

import org.hibernate.Session;

public interface Operation {

    void doOperation(Session session, Object object);

}

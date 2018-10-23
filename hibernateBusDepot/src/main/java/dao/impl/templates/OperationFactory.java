package dao.impl.templates;

import org.hibernate.Session;

public class OperationFactory {

    public static void update(Session session, Object object){
        session.update(object);
    }

    public static void add(Session session, Object object){
        session.save(object);
    }

    public static void delete(Session session, Object object){
        session.delete(object);
    }

}

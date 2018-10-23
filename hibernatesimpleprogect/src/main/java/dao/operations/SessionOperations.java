package dao.operations;

import models.User;
import org.hibernate.Session;

public class SessionOperations {

    public static void sessionOperationSave(Session session, User user){
        session.save(user);
    }

    public static void sessionOperationUpdate(Session session, User user){
        session.update(user);
    }

    public static void sessionOperationDelete(Session session, User user){
        session.delete(user);
    }
}

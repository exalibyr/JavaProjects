package dao.operations;

import models.User;
import org.hibernate.Session;

public interface Operation {

    void doOperation(Session session, User user);
}

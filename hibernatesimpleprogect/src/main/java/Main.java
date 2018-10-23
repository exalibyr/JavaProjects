import models.Auto;
import models.User;
import services.UserService;
import services.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        User user = new User("Petrosyan", 54);
        userService.saveUser(user);
        Auto auto = new Auto("Vedro pomoynoe", "Cvet govna");
        user.addAuto(auto);
        userService.updateUser(user);

        User user1 = new User("Vsauce", 35);
        userService.saveUser(user1);
        Auto auto1 = new Auto("Ford Mustang 1969", "Black-Chrome");
        user1.addAuto(auto1);
        Auto auto2 = new Auto("Lamborgini", "Yellow");
        user1.addAuto(auto2);
        userService.updateUser(user1);

    }
}

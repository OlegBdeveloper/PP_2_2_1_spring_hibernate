package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        try {

        Car car1 = new Car("BMW", 5);
        User user1 = new User("Proba", "Proba", "user@mail.ru");
        user1.setCar(car1);
        car1.setOwner(user1);
        userService.add(user1);

        Car car2 = new Car("BMWWW", 6);
        User user2 = new User("PProba", "ProbPa", "Puser@mail.ru");
        user2.setCar(car2);
        car2.setOwner(user2);
        userService.add(user2);

            List<User> users = userService.listUsers();
            for (User user : users) {

                if (user.getCar() != null) {
                    System.out.println("Id = " + user.getId());
                    System.out.println("First Name = " + user.getFirstName());
                    System.out.println("Last Name = " + user.getLastName());
                    System.out.println("Email = " + user.getEmail());
                    System.out.println("Car Model = " + user.getCar().getModel());
                    System.out.println("Car Series = " + user.getCar().getSeries());
                }
                System.out.println();
            }
        } finally {
            context.close();
        }
    }
}

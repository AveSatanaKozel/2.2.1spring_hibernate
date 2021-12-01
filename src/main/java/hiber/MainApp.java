package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {


    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

//        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        User ivan = new User("Ivan", "Stroganov", "123@mail.ru");
        Car car1 = new Car("Chepyrka", 14);


        User alex = new User("Alexi", "Laiho", "456@mail.ru");
        Car car2 = new Car("Mustang", 6);

        carService.add(car1);
        carService.add(car2);

        ivan.setCar(car1);
        alex.setCar(car2);

        userService.add(ivan);
        userService.add(alex);

        System.out.println(userService.getUserByCar("Mustang", 6));


//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println("Car = " + user.getCar());
//
//      }

        context.close();
    }
}

package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car c1 = new Car("Camry",3);
      Car c2 = new Car("BVM",5);
      Car c3 = new Car("GLE",8);
      Car c4 = new Car("LADA",9);

      User u1 = new User("User1", "Lastname1", "user1@mail.ru");
      User u2 = new User("User2", "Lastname2", "user2@mail.ru");
      User u3 = new User("User3", "Lastname3", "user3@mail.ru");
      User u4 = new User("User4", "Lastname4", "user4@mail.ru");
      c1.setUser(u1);
      c2.setUser(u2);
      c3.setUser(u3);
      c4.setUser(u4);

      userService.add(u1);
      userService.add(u2);
      userService.add(u3);
      userService.add(u4);

      carService.add(c1);
      carService.add(c2);
      carService.add(c3);
      carService.add(c4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      System.out.println(userService.userOfCar("Camry",3).toString());
      context.close();
   }
}

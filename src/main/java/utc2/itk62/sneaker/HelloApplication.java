package utc2.itk62.sneaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.connection.ConnectionUtil;
import utc2.itk62.sneaker.models.Category;
import utc2.itk62.sneaker.models.Customer;
import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.repositories.CategoryRepo;
import utc2.itk62.sneaker.repositories.CustomerRepo;
import utc2.itk62.sneaker.repositories.PositionRepo;
import utc2.itk62.sneaker.repositories.StaffRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch();
//        CategoryRepo categoryRepo = new CategoryRepo();
//        categoryRepo.getAllCategories(new Paging(0,0));
//        if (categoryRepo.getCategoryById(10) != null) {
//            System.out.println("test");
//        }
//        if (categoryRepo.getCategoryByName("Test") != null) {
//            System.out.println("test");
//        }
//        categoryRepo.updateCategory(new Category());
//        categoryRepo.deleteCategory(10);
//
//        StaffRepo staffRepo = new StaffRepo();
//        staffRepo.createStaff(new Staff());
//        staffRepo.deleteStaff(10);
//        staffRepo.getStaffByUsername("Test");
//        staffRepo.getAllCategories(new Paging(0,0));
//        staffRepo.updateStaff(new Staff());

//        PositionRepo positionRepo = new PositionRepo();
//        positionRepo.getAllPosition();
//        positionRepo.getPosition(1);
//
//        CustomerRepo customerRepo = new CustomerRepo();
//        customerRepo.createCustomer(new Customer());
//        customerRepo.deleteCustomer(1);
//        customerRepo.updateCustomer(new Customer());
//        customerRepo.getAllCustomer(new Paging(0, 0));
//        customerRepo.getCustomerByPhoneNumber("123");
//        customerRepo.getCustomerById(1);


    }

}
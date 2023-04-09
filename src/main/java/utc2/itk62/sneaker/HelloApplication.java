package utc2.itk62.sneaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.models.*;
import utc2.itk62.sneaker.repositories.*;

import java.io.IOException;

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

//        InvoiceRepo invoiceRepo = new InvoiceRepo();
//        invoiceRepo.deleteInvoice(1);
//        invoiceRepo.getCategoryByIdStaff(1);
//        invoiceRepo.getCategoryByIdCustomer(1);
//        invoiceRepo.getAllInvoices(new Paging(0, 0));
//        invoiceRepo.createInvoice(new Invoice(1, 2, 1));
        InvoiceDetailRepo invoiceDetailRepo = new InvoiceDetailRepo();
        invoiceDetailRepo.getAllInvoicesDetailByInvoiceId(new Paging(0, 0), 1);
        invoiceDetailRepo.createInvoiceDetail(new InvoiceDetail());
    }

}
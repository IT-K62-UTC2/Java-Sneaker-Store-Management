package utc2.itk62.sneaker.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import utc2.itk62.sneaker.models.Customer;

public class CustomerController {
    @FXML
    public TextField searchValue;
    @FXML
    public ComboBox<String> keySearch;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnUpdate;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnExportExcel;
    @FXML
    public TextField id;
    @FXML
    public TextField name;
    @FXML
    public TextField address;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField cccd;
    @FXML
    public ComboBox<String> gender;
    @FXML
    public TableView<Customer> tableListCustomer;
}

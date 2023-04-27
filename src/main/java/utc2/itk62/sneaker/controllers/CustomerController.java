package utc2.itk62.sneaker.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utc2.itk62.sneaker.models.Customer;
import utc2.itk62.sneaker.services.CustomerService;

import java.sql.Timestamp;

public class CustomerController {
    private static final CustomerService customerService = new CustomerService();

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
    public TextField email;
    @FXML
    public TableView<Customer> tableListCustomer;
    public TableColumn<Integer, Integer> stt;
    public TableColumn<Customer, Integer> colId;
    public TableColumn<Customer, String> colName;
    public TableColumn<Customer, String> colAddress;
    public TableColumn<Customer, String> colPhoneNumber;
    public TableColumn<Customer, String> colCccd;
    public TableColumn<Customer, String> colGender;
    public TableColumn<Customer, Integer>colStatus;
    public TableColumn<Customer, String> colEmail;
    public TableColumn<Customer, Timestamp> colCreatedAt;
    public TableColumn<Customer, Timestamp> colUpdatedAt;
    public RadioButton btnMale;
    public RadioButton btnFemale;

    private ObservableList<Customer> customerList;
    private ToggleGroup toggleGenderGroup = new ToggleGroup();
    public void initialize () {
        // Gender
        btnFemale.setToggleGroup(toggleGenderGroup);
        btnMale.setToggleGroup(toggleGenderGroup);
        toggleGenderGroup.selectToggle(btnMale);
        setUpTableView();
        reloadTableView();
    }

    private void reloadTableView() {
        // table view
        tableListCustomer.getItems().clear();
        customerList = FXCollections.observableArrayList(customerService.getAllCustomer());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colCccd.setCellValueFactory(new PropertyValueFactory<>("cccd"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
        tableListCustomer.setItems(customerList);
        tableListCustomer.getSelectionModel().selectFirst();
        updateProductCurrentRowToForm();
    }

    private void updateProductCurrentRowToForm() {
        Customer customer = tableListCustomer.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(customer.getId()));
        name.setText(customer.getFullName());
        address.setText(customer.getAddress());
        phoneNumber.setText(customer.getPhoneNumber());
        email.setText(customer.getEmail());
        cccd.setText(customer.getCccd());
        toggleGenderGroup.selectToggle(customer.getGender().equals(btnMale.getText()) ? btnMale : btnFemale);
    }

    private void setUpTableView() {
        tableListCustomer.setOnMouseClicked(mouseEvent -> {
            updateProductCurrentRowToForm();
        });
        tableListCustomer.setOnKeyPressed(keyPressed ->{
            updateProductCurrentRowToForm();
        });
    }
}

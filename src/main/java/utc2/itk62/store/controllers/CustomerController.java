package utc2.itk62.store.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utc2.itk62.store.Validator.CustomerValidator;
import utc2.itk62.store.models.Customer;
import utc2.itk62.store.models.Staff;
import utc2.itk62.store.services.CustomerService;
import utc2.itk62.store.util.CustomAlert;

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
    private ObservableList<String> listKetSearch = FXCollections.observableArrayList(
            "ID","Name", "Address","Email",  "PhoneNumber", "CCCD", "Gender");
    public void initialize () {
        // Gender
        btnFemale.setToggleGroup(toggleGenderGroup);
        btnMale.setToggleGroup(toggleGenderGroup);
        toggleGenderGroup.selectToggle(btnMale);
        setUpTableView();
        reloadTableView();
        setupBtnAdd();
        setupBtnDelete();
        setupBtnUpdate();
        setupBtnExportExcel();

        // Search
        keySearch.setItems(listKetSearch);
        keySearch.setValue("ID");
        setupSearch();

    }

    private void setupSearch() {
        searchValue.setOnKeyTyped(keyEvent ->  {
            // Khởi tạo FilteredList và gán nó với danh sách positionList
            FilteredList<Customer> filteredList = new FilteredList<>(customerList, p -> true);

            // Gán FilteredList làm nguồn dữ liệu cho TableView
            tableListCustomer.setItems(filteredList);
            filteredList.setPredicate(p -> {
                if(searchValue.getText().isEmpty()) {
                    return true;
                }
                if(keySearch.getValue().equals("ID")) {
                    return String.valueOf(p.getId()).toLowerCase().contains(searchValue.getText());
                }
                if(keySearch.getValue().equals("Name")) {
                    return p.getFullName().toLowerCase().contains(searchValue.getText());
                }
                if(keySearch.getValue().equals("Address")) {
                    return p.getAddress().toLowerCase().contains(searchValue.getText());
                }
                if(keySearch.getValue().equals("Email")) {
                    return p.getEmail().toLowerCase().contains(searchValue.getText());
                }
                if(keySearch.getValue().equals("PhoneNumber")) {
                    return p.getPhoneNumber().toLowerCase().contains(searchValue.getText());
                }
                if(keySearch.getValue().equals("Gender")) {
                    return p.getFullName().toLowerCase().contains(searchValue.getText());
                }

                return p.getCccd().toLowerCase().contains(searchValue.getText());
            });
            if(tableListCustomer.getSelectionModel() != null) {
                tableListCustomer.getSelectionModel().selectFirst();
            }

            updateCustomerCurrentRowToForm();
        });
    }

    private void setupBtnExportExcel() {
        btnExportExcel.setOnAction(actionEvent -> {
            customerService.exportExcel(customerList);
        });
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
        updateCustomerCurrentRowToForm();
    }

    private void updateCustomerCurrentRowToForm() {
        Customer customer = tableListCustomer.getSelectionModel().getSelectedItem();
        if ( customer == null) {
            cleanForm();
            return;
        }
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
            updateCustomerCurrentRowToForm();
        });
        tableListCustomer.setOnKeyPressed(keyPressed ->{
            updateCustomerCurrentRowToForm();
        });
    }

    private void setupBtnAdd() {
        btnAdd.setOnAction(actionEvent ->  {
            tableListCustomer.getSelectionModel().clearSelection();
            if(id.getText() != null && id.getText().length() > 0) {
                cleanForm();
            }
            if(!validateFormStaff()) {
                return;
            }
            Customer customer = new Customer();
            customer.setFullName(name.getText());
            customer.setAddress(address.getText());
            customer.setCccd(cccd.getText());
            customer.setEmail(email.getText());
            customer.setGender(getGender());
            customer.setPhoneNumber(phoneNumber.getText());
            if(!customerService.createCustomer(customer)) {
                CustomAlert.showAlert(Alert.AlertType.ERROR, id.getScene().getWindow(), "Error!", "Sometimes customer service is not available");
                return;
            }
            CustomAlert.showAlert(Alert.AlertType.INFORMATION,id.getScene().getWindow(), "Success", "Create customer successfully");
            reloadTableView();
        });
    }

    private void cleanForm() {
        id.setText("");
        name.setText("");
        address.setText("");
        email.setText("");
        cccd.setText("");
        phoneNumber.setText("");
    }

    private boolean validateFormStaff() {
        // Validate name
        if(!CustomerValidator.validateFullname(name.getText())) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, phoneNumber.getScene().getWindow(), "Form Error!", "Invalid full name");
            name.requestFocus();
            return false;
        }

        // Validate address
        if(!CustomerValidator.validateAddress(address.getText())) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, address.getScene().getWindow(), "Form Error!", "Invalid address");
            address.requestFocus();
            return false;
        }

        // Validate email
        if(!CustomerValidator.validateEmail(email.getText())){
            CustomAlert.showAlert(Alert.AlertType.ERROR, email.getScene().getWindow(), "Form Error!", "Invalid email");
            email.requestFocus();
            return false;
        }

        if(CustomerValidator.isExistEmail(email.getText(),
                id.getText().equals("") ? 0
                        : Integer.parseInt(id.getText()))) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, email.getScene().getWindow(), "Form Error!", "Email is exist");
            email.requestFocus();
            return false;
        }

        // Validate phone number
        if (!CustomerValidator.validatePhoneNumber(phoneNumber.getText())){
            CustomAlert.showAlert(Alert.AlertType.ERROR, phoneNumber.getScene().getWindow(), "Form Error!", "Invalid phone number");
            phoneNumber.requestFocus();
            return false;
        }
        if(CustomerValidator.isExistPhoneNumber(phoneNumber.getText(),
                id.getText().equals("") ? 0
                        : Integer.parseInt(id.getText()))) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, phoneNumber.getScene().getWindow(), "Form Error!", "Phone number is exist");
            phoneNumber.requestFocus();
            return false;
        }

        // Validate CCCD
        if(!CustomerValidator.validateCCCD(cccd.getText())){
            CustomAlert.showAlert(Alert.AlertType.ERROR, cccd.getScene().getWindow(), "Form Error!", "Invalid CCCD");
            cccd.requestFocus();
            return false;
        }
        if(CustomerValidator.isExistCCCD(cccd.getText(),
                id.getText().equals("") ? 0
                        : Integer.parseInt(id.getText()))) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, cccd.getScene().getWindow(), "Form Error!", "CCCD is exist");
            cccd.requestFocus();
            return false;
        }


        return true;
    }

    private void setupBtnDelete() {
        btnDelete.setOnAction(actionEvent -> {
            if(id.getText().equals("")) {
                return;
            }
            if(!CustomAlert.showAlert(Alert.AlertType.CONFIRMATION, id.getScene().getWindow(), "Delete customer", "Are you sure you want to delete this customer")) {
                return;
            }
            if(!customerService.deleteCustomer(Integer.parseInt(id.getText()))) {
                CustomAlert.showAlert(Alert.AlertType.ERROR, id.getScene().getWindow(), "Error!", "Sometimes the customer service is not available");
            }
            reloadTableView();
        });

    }

    private void setupBtnUpdate() {
        btnUpdate.setOnAction(actionEvent -> {
            if(id.getText().equals("")) {
                return;
            }
            if(!validateFormStaff()) {
                return;
            }
            Customer customer = new Customer();
            customer.setId(Integer.parseInt(id.getText()));
            customer.setFullName(name.getText());
            customer.setAddress(address.getText());
            customer.setCccd(cccd.getText());
            customer.setEmail(email.getText());
            customer.setGender(getGender());
            customer.setPhoneNumber(phoneNumber.getText());
            if(!customerService.updateCustomer(customer)) {
                CustomAlert.showAlert(Alert.AlertType.ERROR,id.getScene().getWindow(),"Error!","Sometimes customer service is not available");
                return;
            }
            CustomAlert.showAlert(Alert.AlertType.INFORMATION, id.getScene().getWindow(),"Success", "Customer customer successfully");
            reloadTableView();
        });
    }

    private String getGender() {
        RadioButton radioButton = (RadioButton)toggleGenderGroup.getSelectedToggle();
        return radioButton.getText();
    }
}

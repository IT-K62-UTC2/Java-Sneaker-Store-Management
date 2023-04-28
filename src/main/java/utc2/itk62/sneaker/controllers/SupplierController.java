package utc2.itk62.sneaker.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utc2.itk62.sneaker.Validator.SupplireValidator;
import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.models.Supplier;
import utc2.itk62.sneaker.services.SupplierServices;
import utc2.itk62.sneaker.util.CustomMessageBox;

import java.sql.SQLException;
import java.sql.Timestamp;

public class SupplierController {

    
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnExportExcel;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Supplier, Timestamp> colCreatedAtSupplier;

    @FXML
    private TableColumn<?, ?> colCreatedAtProduct;

    @FXML
    private TableColumn<?, ?> colDescProduct;

    @FXML
    private TableColumn<Supplier, String> colNameSupplier;

    @FXML
    private TableColumn<Supplier, String> colAddressSupplier;

    @FXML
    private TableColumn<?, ?> colNameProduct;

    @FXML
    private TableColumn<?, ?> colPriceProduct;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colSTTProduct;

    @FXML
    private TableColumn<?, ?> colSizeProduct;

    @FXML
    private TableColumn<Supplier, String> colPhoneSupplier;

    @FXML
    private TableColumn<Supplier, Integer> colIdSupplier;//x

    @FXML
    private TableColumn<?, ?> colSupplierProduct;

    @FXML
    private TableColumn<Supplier, Timestamp> colUpdatedAtSupplier;

    @FXML
    private TableColumn<?, ?> colUpdatedAtProduct;

    @FXML
    private TextField idSupplire;

    @FXML
    private ComboBox<String> keySearch;

    @FXML
    private TextField nameSupplier;

    @FXML
    private TextField phoneSupplier;

    @FXML
    private TextField addressSupplier;

    @FXML
    private TextField emailSupplier;


    @FXML
    private TextField searchValue;

    @FXML
    private TableView<Supplier> tableListSupplier;

    @FXML
    private TableView<?> tableListProduct;

    private ObservableList<Supplier> supplierList;
    private static final SupplierServices supplierServices = new SupplierServices();

    private ObservableList<String> listKetSearch = FXCollections.observableArrayList(
            "ID","Name", "PhoneNumber", "Email");

    @FXML
    public void initialize() throws SQLException {
        reloadTableView();

        keySearch.setItems(listKetSearch);
        keySearch.setValue("ID");
    }



    @FXML
    void handleBtnAdd(ActionEvent event) {
        // get values from ui
        String name = nameSupplier.getText();
        String email = emailSupplier.getText();
        String address = addressSupplier.getText();
        String phone = phoneSupplier.getText();

        // validate
        if(!SupplireValidator.validateUsername(name)) {
            CustomMessageBox.boxError("Invalid name");
            nameSupplier.requestFocus();
            return;
        }

        if(SupplireValidator.isExistUsername(name)) {
            CustomMessageBox.boxError("Username is exists");
            nameSupplier.requestFocus();
            return;
        }
        if(!SupplireValidator.validateAddress(address)) {
            CustomMessageBox.boxError("Invalid address");
            addressSupplier.requestFocus();
            return;
        }

        // Validate phone number
        if (!SupplireValidator.validatePhoneNumber(phone)){
            CustomMessageBox.boxError("Invalid phone number");
            phoneSupplier.requestFocus();
            return;
        }
        if(SupplireValidator.isExistPhoneNumber(phone)) {
            CustomMessageBox.boxError("Phone number is exists");
            phoneSupplier.requestFocus();
            return;
        }
        if(!SupplireValidator.validateEmail(email)){
            CustomMessageBox.boxError("Invalid email");
            emailSupplier.requestFocus();
            return;
        }

        if(SupplireValidator.isExistEmail(email)) {
            CustomMessageBox.boxError("Email is exists");
            emailSupplier.requestFocus();
            return;
        }

        Supplier supplier = new Supplier();
        supplier.setName(name);
        supplier.setEmail(email);
        supplier.setAddress(address);
        supplier.setPhoneNumber(phone);
        System.out.println("123");

        if(!supplierServices.createSupplier(supplier)) {

            CustomMessageBox.boxError("Sometimes the supplier service is not available");
            return;
        }
        CustomMessageBox.boxOk("Create supplier successfully");

        cleanForm();

        reloadTableView();
    }

    private void reloadTableView() {
        tableListSupplier.getItems().clear();
        supplierList = FXCollections.observableArrayList(supplierServices.getAllSupplier());
        colAddressSupplier.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
        colIdSupplier.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
        colNameSupplier.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
        colPhoneSupplier.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phoneNumber"));
        colCreatedAtSupplier.setCellValueFactory(new PropertyValueFactory<Supplier, Timestamp>("createdAt"));
        colUpdatedAtSupplier.setCellValueFactory(new PropertyValueFactory<Supplier, Timestamp>("updatedAt"));
        tableListSupplier.setItems(supplierList);

        tableListSupplier.getSelectionModel().selectFirst();


    }
    public void cleanForm() {
        nameSupplier.setText("");
        emailSupplier.setText("");
        addressSupplier.setText("");
        phoneSupplier.setText("");

    }

    private Supplier updateSupplierCurrentRowToForm() {
        Supplier selectedSupplier = tableListSupplier.getSelectionModel().getSelectedItem();
        if (tableListSupplier == null) {
            cleanForm();
            return null;
        }
        emailSupplier.setText(selectedSupplier.getEmail());
        nameSupplier.setText(selectedSupplier.getName());
        addressSupplier.setText(selectedSupplier.getAddress());
        phoneSupplier.setText(selectedSupplier.getPhoneNumber());
        idSupplire.setText(String.valueOf(selectedSupplier.getId()));

        return selectedSupplier;
    }

    @FXML
    void handleBtnDelete(ActionEvent event) {
        Supplier currentSelectSupplier = updateSupplierCurrentRowToForm();
        if (!CustomMessageBox.isYes("Delete", "Are you sure you want to delete")) {
            return;
        }
        if (!supplierServices.deleteSupplier(currentSelectSupplier)) {
            CustomMessageBox.boxError("Delete supplier failed");
            return;
        }
        tableListSupplier.getItems().remove(currentSelectSupplier);
        cleanForm();


    }



    @FXML
    void handleBtnUpdate(ActionEvent event) {
        Supplier currentSupplier = new Supplier() ;


        currentSupplier.setPhoneNumber(phoneSupplier.getText());
        currentSupplier.setAddress(addressSupplier.getText());
        currentSupplier.setName(nameSupplier.getText());
        currentSupplier.setEmail(emailSupplier.getText());
        currentSupplier.setId(Integer.parseInt(idSupplire.getText()));

        if (!supplierServices.updateSupplier(currentSupplier)) {
            CustomMessageBox.boxError("Update supplier failed");
            return;
        }
        reloadTableView();
        cleanForm();
    }

    @FXML
    public void handleChangeSearchValue(KeyEvent actionEvent) {
        // Khởi tạo FilteredList và gán nó với danh sách positionList
        FilteredList<Supplier> filteredList = new FilteredList<>(supplierList, p -> true);

        // Gán FilteredList làm nguồn dữ liệu cho TableView
        tableListSupplier.setItems(filteredList);
        filteredList.setPredicate(p -> {
            if(searchValue.getText().isEmpty()) {
                return true;
            }
            // "ID","Username", "Name", "Address", "PhoneNumber", "CCCD");
            if(keySearch.getValue().equals("ID")) {
                return String.valueOf(p.getId()).toLowerCase().contains(searchValue.getText());
            }
            if(keySearch.getValue().equals("Name")) {
                return p.getName().toLowerCase().contains(searchValue.getText());
            }
            if(keySearch.getValue().equals("Address")) {
                return p.getAddress().toLowerCase().contains(searchValue.getText());
            }
            if(keySearch.getValue().equals("PhoneNumber")) {
                return p.getPhoneNumber().toLowerCase().contains(searchValue.getText());
            }
            return p.getEmail().toLowerCase().contains(searchValue.getText());


        });
        if(tableListSupplier.getSelectionModel() != null) {
            tableListSupplier.getSelectionModel().selectFirst();
        }
        updateSupplierCurrentRowToForm();
    }

    @FXML
    public void handleClickedTableView(MouseEvent mouseEvent) {
        updateSupplierCurrentRowToForm();
    }

    @FXML
    void handleOnKeyPresedTableView(KeyEvent event) {

    }
    @FXML
    public void handleBtnExport(ActionEvent actionEvent) {
        supplierServices.exportExcel(supplierList);
    }

}

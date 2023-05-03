package utc2.itk62.store.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utc2.itk62.store.Validator.SupplierValidator;
import utc2.itk62.store.models.Category;
import utc2.itk62.store.models.Product;
import utc2.itk62.store.models.Supplier;
import utc2.itk62.store.services.ProductService;
import utc2.itk62.store.services.SupplierService;
import utc2.itk62.store.util.CustomAlert;
import utc2.itk62.store.util.FormatDouble;

import java.sql.Timestamp;

public class SupplierController {
    private static final SupplierService supplierService = new SupplierService();
    private static final ProductService productService = new ProductService();

    public TextField id;
    public TextField name;
    public TextField email;
    public TextField phoneNumber;
    public TextField address;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnExportExcel;
    public TextField valueSearch;
    public ComboBox<String> keySearch;
    public TableView<Supplier> tableListSupplier;
    public TableColumn<Supplier, Integer> colStt;
    public TableColumn<Supplier, Integer> colId;
    public TableColumn<Supplier, String> colName;
    public TableColumn<Supplier, String> colEmail;
    public TableColumn<Supplier, String> colPhoneNumber;
    public TableColumn<Supplier, String> colAddress;
    public TableColumn<Supplier, Integer> colStatus;
    public TableColumn<Supplier, Timestamp> colCreatedAt;
    public TableColumn<Supplier, Timestamp> colUpdatedAt;
    public TableView<Product> tableListProduct;
    public TableColumn<Product, Integer> colSttProduct;
    public TableColumn<Product, Category> colCategoryProduct;
    public TableColumn<Product, String> colNameProduct;
    public TableColumn<Product, Integer> colQuantityProduct;
    public TableColumn<Product, String> colDescProduct;
    public TableColumn<Product, String> colPriceProduct;

    private ObservableList<Supplier> supplierList;

    public void initialize() {
        reloadTableView();
        setupBtnAddSupplier();
        setupUpdateSupplier();
        setupDeleteSupplier();
        setUpTableListCategory();
    }

    private void setupDeleteSupplier() {
        btnDelete.setOnAction(actionEvent -> {
            if(id.getText().equals("")) {
                return;
            }
            Supplier supplier = getSupplierCurrentForm();
            if (!CustomAlert.showAlert(Alert.AlertType.CONFIRMATION, id.getScene().getWindow(), "Delete supplier", "Are you sure you want to delete this supplier?")) {
                return;
            }
            if (!supplierService.deleteSupplier(supplier)) {
                CustomAlert.showAlert(Alert.AlertType.ERROR, id.getScene().getWindow(), "Error!","Delete supplier failed");
                return;
            }
            CustomAlert.showAlert(Alert.AlertType.INFORMATION, id.getScene().getWindow(), "Success!","Delete supplier successfully");
            reloadTableView();
        });
    }

    private void reloadTableView() {
        if (!tableListSupplier.getItems().isEmpty()) {
            tableListSupplier.getItems().clear();
        }
        if (!tableListProduct.getItems().isEmpty()) {
            tableListProduct.getItems().clear();
        }
        supplierList = FXCollections.observableArrayList(supplierService.getAllSuppliers());
        colId.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Supplier, String>("email"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phoneNumber"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("status"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<Supplier, Timestamp>("createdAt"));
        colUpdatedAt.setCellValueFactory(new PropertyValueFactory<Supplier, Timestamp>("updatedAt"));
        tableListSupplier.setItems(supplierList);
        new Thread(()->{
            for (Supplier supplier : supplierList) {
                supplier.setProductList(productService.getProductByIdSupplier(supplier.getId()));
            }
            Platform.runLater(this::updateProductCurrentRowSupplier);
        }).start();

        // update table other
        tableListSupplier.getSelectionModel().selectFirst();
        updateCurrentSupplierToForm();

    }

    private void updateCurrentSupplierToForm() {
        Supplier supplier = tableListSupplier.getSelectionModel().getSelectedItem();
        if (supplier == null) {
            cleanForm();
            return;
        }
        id.setText(String.valueOf(supplier.getId()));
        name.setText(supplier.getName());
        email.setText(supplier.getEmail());
        phoneNumber.setText(supplier.getPhoneNumber());
        address.setText(supplier.getAddress());
    }

    private void cleanForm() {
        id.setText("");
        name.setText("");
        email.setText("");
        phoneNumber.setText("");
        address.setText("");
    }

    private void updateProductCurrentRowSupplier() {
        tableListProduct.getItems().clear();
        colNameProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        colPriceProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        colPriceProduct.setCellValueFactory(param -> {
            Product product = param.getValue();
            return new SimpleStringProperty(FormatDouble.toString(product.getPrice()));
        });
        colDescProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        colQuantityProduct.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        colCategoryProduct.setCellValueFactory(new PropertyValueFactory<Product, Category>("category"));
        tableListProduct.setItems(FXCollections.observableArrayList(tableListSupplier.getSelectionModel().getSelectedItem().getProductList()));
    }

    private void setUpTableListCategory() {
        tableListSupplier.setOnMouseClicked(mouseEvent -> {
            updateCurrentSupplierToForm();
            updateProductCurrentRowSupplier();
        });
        tableListSupplier.setOnKeyPressed(keyEvent -> {
            updateCurrentSupplierToForm();
            updateProductCurrentRowSupplier();
        });
    }

    private void setupBtnAddSupplier() {
        btnAdd.setOnAction(actionEvent -> {
            tableListSupplier.getSelectionModel().clearSelection();
            if (!id.getText().equals("")) {
                cleanForm();
            }
            if (!validateForm()) {
                return;
            }

            Supplier supplier = new Supplier();
            supplier.setName(name.getText());
            supplier.setAddress(address.getText());
            supplier.setEmail(email.getText());
            supplier.setPhoneNumber(phoneNumber.getText());

            if (!supplierService.createSupplier(supplier)) {
                CustomAlert.showAlert(Alert.AlertType.ERROR, id.getScene().getWindow(), "Error!", "Sometimes the supplier service is not available");
                return;
            }
            CustomAlert.showAlert(Alert.AlertType.INFORMATION, id.getScene().getWindow(), "Success", "Add supplier successfully");
            reloadTableView();
        });
    }

    public void setupUpdateSupplier() {
        btnUpdate.setOnAction(actionEvent -> {
            Supplier supplier = getSupplierCurrentForm();
            if (!validateForm()) {
                return;
            }
            if (!supplierService.updateSupplier(supplier)) {
                CustomAlert.showAlert(Alert.AlertType.ERROR, id.getScene().getWindow(), "Error!", "Update supplier failed");
                return;
            }
            CustomAlert.showAlert(Alert.AlertType.INFORMATION, id.getScene().getWindow(), "Success!", "Update supplier successfully");
            reloadTableView();
        });
    }

    private Supplier getSupplierCurrentForm() {
        Supplier supplier = new Supplier();
        supplier.setId(Integer.parseInt(id.getText()));
        supplier.setEmail(email.getText());
        supplier.setName(name.getText());
        supplier.setAddress(address.getText());
        supplier.setPhoneNumber(phoneNumber.getText());
        return supplier;
    }


    private boolean validateForm() {
        if (!SupplierValidator.validateName(name.getText())) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, id.getScene().getWindow(), "Form Error!", "Invalid supplier name");
            name.requestFocus();
            return false;
        }

        if (!SupplierValidator.validateEmail(email.getText())) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, id.getScene().getWindow(), "Form Error!", "Invalid supplier email");
            email.requestFocus();
            return false;
        }

        if (!SupplierValidator.validatePhoneNumber(phoneNumber.getText())) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, id.getScene().getWindow(), "Form Error!", "Invalid supplier phone number");
            phoneNumber.requestFocus();
            return false;
        }

        if (!SupplierValidator.validateAddress(address.getText())) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, id.getScene().getWindow(), "Form Error!", "Invalid supplier address");
            address.requestFocus();
            return false;
        }

        return true;
    }
}

package utc2.itk62.store.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import utc2.itk62.store.models.Category;
import utc2.itk62.store.models.Product;
import utc2.itk62.store.models.Supplier;
import utc2.itk62.store.services.ProductService;
import utc2.itk62.store.services.SupplierService;
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
    }

    private void reloadTableView() {
        if (!tableListSupplier.getItems().isEmpty()) {
            tableListSupplier.getItems().clear();
        }
        if (!tableListProduct.getItems().isEmpty()) {
            tableListProduct.getItems().clear();
        }
        supplierList = FXCollections.observableArrayList(supplierService.getAllSuppliers());
        for (Supplier supplier : supplierList) {
            supplier.setProductList(productService.getProductByIdSupplier(supplier.getId()));
        }
        colId.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Supplier, String>("email"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phoneNumber"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("status"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<Supplier, Timestamp>("createdAt"));
        colUpdatedAt.setCellValueFactory(new PropertyValueFactory<Supplier, Timestamp>("updatedAt"));
        tableListSupplier.setItems(supplierList);

        // update table other
        tableListSupplier.getSelectionModel().selectFirst();
        updateCurrentSupplierToForm();
        updateProductCurrentRowSupplier();
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
}

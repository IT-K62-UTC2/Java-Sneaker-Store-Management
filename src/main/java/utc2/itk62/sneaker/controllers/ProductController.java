package utc2.itk62.sneaker.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utc2.itk62.sneaker.models.*;
import utc2.itk62.sneaker.services.CategoryService;
import utc2.itk62.sneaker.services.ProductService;
import utc2.itk62.sneaker.services.SupplierService;

import java.sql.Timestamp;

public class ProductController {
    private static final ProductService productService = new ProductService();
    private static final SupplierService supplierService = new SupplierService();
    private static final CategoryService categoryService = new CategoryService();

    public TableView<Product> tableListProduct;
    public TextField id;
    public TextField name;
    public TextField size;
    public TextField quantity;
    public ComboBox<Supplier> supplier;
    public TextField price;
    public TextArea desc;
    public ComboBox<Category> category;
    public Button btnAddImage;
    public ComboBox<String> keySearch;
    public TextField valueSearch;
    public DatePicker toDate;
    public DatePicker fromDate;
    public TableColumn<Integer, Integer> stt;
    public TableColumn<Product, Integer> colId;
    public TableColumn<Product, Supplier> colSupplier;
    public TableColumn<Product, Category> colCategory;
    public TableColumn<Product, String> colName;
    public TableColumn<Product, Double> colSize;
    public TableColumn<Product, Integer> colQuantity;
    public TableColumn<Product, Double> colPrice;
    public TableColumn<Product, String> colDesc;
    public TableColumn<Product, Integer> colStatus;
    public TableColumn<Product, Timestamp> colCreatedAt;
    public TableColumn<Product, Timestamp> colUpdatedAt;
    public ImageView image;


    private ObservableList<Product> productList;
    private ObservableList<Supplier> supplierList = FXCollections.observableArrayList(supplierService.getAllSuppliers());
    private ObservableList<Category> categoryList = FXCollections.observableArrayList(categoryService.getAllCategory());

    public void initialize() {
        // set supplier
        supplier.setItems(supplierList);

        // set category
        category.setItems(categoryList);

        // setup event for table view
        setUpTableView();

        reloadTableView();

    }

    private void reloadTableView() {
        // table view
        tableListProduct.getItems().clear();
        productList = FXCollections.observableArrayList(productService.getAllProduct());

        colSupplier.setCellValueFactory(new PropertyValueFactory<Product, Supplier>("supplier"));
        colCategory.setCellValueFactory(new PropertyValueFactory<Product, Category>("category"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        colSize.setCellValueFactory(new PropertyValueFactory<Product, Double>("size"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        colName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        colId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<Product, Timestamp>("createdAt"));
        colUpdatedAt.setCellValueFactory(new PropertyValueFactory<Product,Timestamp>("updatedAt"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Product, Integer>("status"));
        tableListProduct.setItems(productList);
        tableListProduct.getSelectionModel().selectFirst();
        updateProductCurrentRowToForm();
    }

    private void updateProductCurrentRowToForm() {
        Product product = tableListProduct.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(product.getId()));
        supplier.setValue(product.getSupplier());
        category.setValue(product.getCategory());
        name.setText(product.getName());
        size.setText(String.valueOf(product.getSize()));
        quantity.setText(String.valueOf(product.getQuantity()));
        desc.setText(product.getDescription());
        price.setText(String.valueOf(product.getPrice()));
        image.setImage(new Image(product.getAvatar()));
    }

    private void setUpTableView( ) {
        tableListProduct.setOnMouseClicked(mouseEvent -> {
            updateProductCurrentRowToForm();
        });

        tableListProduct.setOnKeyPressed(keyPressed -> {
            updateProductCurrentRowToForm();
        });
    }
}

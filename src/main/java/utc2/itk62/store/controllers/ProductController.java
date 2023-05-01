package utc2.itk62.store.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import utc2.itk62.store.Validator.ProductValidator;
import utc2.itk62.store.models.Category;
import utc2.itk62.store.models.Product;
import utc2.itk62.store.models.Supplier;
import utc2.itk62.store.services.CategoryService;
import utc2.itk62.store.services.ProductService;
import utc2.itk62.store.services.SupplierService;
import utc2.itk62.store.util.CustomAlert;
import utc2.itk62.store.util.FormatDouble;

import java.io.File;
import java.sql.Timestamp;

public class ProductController {
    private static final ProductService productService = new ProductService();
    private static final SupplierService supplierService = new SupplierService();
    private static final CategoryService categoryService = new CategoryService();

    public TableView<Product> tableListProduct;
    public TextField id;
    public TextField name;
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
    public TableColumn<Product, Integer> colQuantity;
    public TableColumn<Product, String> colPrice;
    public TableColumn<Product, String> colDesc;
    public TableColumn<Product, Integer> colStatus;
    public TableColumn<Product, Timestamp> colCreatedAt;
    public TableColumn<Product, Timestamp> colUpdatedAt;
    public ImageView image;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnExportExcel;


    private ObservableList<Product> productList;
    private ObservableList<Supplier> supplierList = FXCollections.observableArrayList(supplierService.getAllSuppliers());
    private ObservableList<Category> categoryList = FXCollections.observableArrayList(categoryService.getAllCategory());

    public void initialize() {
        setupPriceTextField();
        setupQuantityTextField();
        setupBtnAddProduct();


        // set supplier
        supplier.setItems(supplierList);

        // set category
        category.setItems(categoryList);

        setupBtnAddImage();

        // setup event for table view
        setUpTableView();
        reloadTableView();

    }

    private void setupBtnAddProduct() {
        btnAdd.setOnAction(actionEvent -> {
            tableListProduct.getSelectionModel().clearSelection();
            if(id.getText() != null && id.getText().length() > 0) {
                cleanForm();
            }

            if(!validateFormProduct()) {
                return;
            }
            Product product = new Product();;
            product.setAvatar(image.getImage().getUrl());
            System.out.println(category.getValue().getId());
            System.out.println(supplier.getValue().getId() + "Supplier");
            product.setCategory(category.getValue());
            product.setDescription(desc.getText());
            product.setName(name.getText());
            product.setPrice(FormatDouble.toDouble(price.getText()));
            product.setSupplier(supplier.getValue());
            product.setQuantity(Integer.parseInt(quantity.getText()));
            if(!productService.createStaff(product)) {
                CustomAlert.showAlert(Alert.AlertType.ERROR, id.getScene().getWindow(), "Error!", "Sometimes the product service is not available");
                return;
            }
            CustomAlert.showAlert(Alert.AlertType.INFORMATION, id.getScene().getWindow(), "Success", "Staff product successfully");
            reloadTableView();
        });
    }

    private boolean validateFormProduct() {
        // Validate image
        if(!ProductValidator.validateImageAvatar(image)) {
            CustomAlert.showAlert(Alert.AlertType.INFORMATION, id.getScene().getWindow(), "Form Error!", "Please selected an image");
            btnAddImage.fire();
            name.requestFocus();
            return false;
        }

        // Validate name product
        if(!ProductValidator.validateNameProduct(name.getText())) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, name.getScene().getWindow(), "Form Error!", "Invalid name product");
            name.requestFocus();
            return false;
        }

        // Validate price
        if(!ProductValidator.validatePrice(FormatDouble.toDouble(price.getText()))) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, price.getScene().getWindow(), "Form Error!", "Price always > 0");
            price.requestFocus();
            return false;
        }

        // validate quantity
        if(!ProductValidator.validateQuantity(Integer.parseInt(quantity.getText()))) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, quantity.getScene().getWindow(), "Form Error!", "Quantity not less than 0");
            quantity.requestFocus();
            return false;
        }
        return true;
    }

    private void cleanForm() {
        supplier.setValue(supplierList.get(0));
        category.setValue(categoryList.get(0));
        id.setText("");
        image.setImage(null);
        name.setText("");
        quantity.setText("");
        desc.setText("");
        price.setText("");
    }

    private void setupBtnAddImage() {
        btnAddImage.setOnAction(actionEvent -> {
            // Tạo một đối tượng FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Vui lòng chọn hình ảnh");

            // Chỉ cho phép chọn file hình ảnh
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Hình ảnh", "*.jpg", "*.jpeg", "*.png"));
            // Hiển thị hộp thoại chọn file
            File selectedFile = fileChooser.showOpenDialog(id.getScene().getWindow());
            // Kiểm tra xem người dùng đã chọn file chưa
            if (selectedFile != null) {
                // Lấy đường dẫn của file được chọn
                image.setImage(new Image(String.valueOf(selectedFile)));
            }
        });
    }

    private void reloadTableView() {
        // table view
        tableListProduct.getItems().clear();
        productList = FXCollections.observableArrayList(productService.getAllProduct());

        colSupplier.setCellValueFactory(new PropertyValueFactory<Product, Supplier>("supplier"));
        colCategory.setCellValueFactory(new PropertyValueFactory<Product, Category>("category"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        colPrice.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
                Product product = param.getValue();
                return new SimpleStringProperty(FormatDouble.toString(product.getPrice()));
            }
        });
        colQuantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
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

    private void setupPriceTextField() {
        price.textProperty().addListener((observable, oldValue, newValue) -> {
            // kiểm tra xem chuỗi mới nhập vào có thể định dạng thành số không
            try {
                System.out.println("price");
                String valueAmount = FormatDouble.toString(FormatDouble.toDouble(newValue));
                price.setText(valueAmount);
            } catch (NumberFormatException e) {
                // nếu không thể định dạng thành số, bỏ qua và giữ nguyên chuỗi nhập vào
            }
        });
    }

    private void setupQuantityTextField() {
        quantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                quantity.setText("0");
            } else if (!newValue.matches("\\d*")) {
                quantity.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }


    private void updateProductCurrentRowToForm() {
        Product product = tableListProduct.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(product.getId()));
        supplier.setValue(product.getSupplier());
        category.setValue(product.getCategory());
        name.setText(product.getName());
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

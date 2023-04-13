package utc2.itk62.sneaker.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utc2.itk62.sneaker.models.Category;
import utc2.itk62.sneaker.models.Product;
import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.models.Supplier;
import utc2.itk62.sneaker.services.CategoryService;
import utc2.itk62.sneaker.services.ProductService;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public class CategoryController {
    private static final CategoryService categoryService = new CategoryService();
    private static final ProductService productService = new ProductService();

    @FXML
    public TextField searchValue;
    @FXML
    public ComboBox findBy;
    @FXML
    public TableColumn<Category, Integer> colSttCategory;
    @FXML
    public TableColumn<Category, Integer> colIdCategory;
    @FXML
    public TableColumn<Category, String> colNameCategory;
    @FXML
    public TableColumn<Category, Integer> colStatusCategory;
    @FXML
    public TableColumn<Category, Timestamp> colCreatedAtCategory;
    @FXML
    public TableColumn<Category, Timestamp> colUpdatedAtCategory;
    @FXML
    public TableView<Category> tableListCategory;
    @FXML
    public TableColumn<Product,Integer> colSTTProduct;
    @FXML
    public TableColumn<Product, Supplier> colSupplierProduct;
    @FXML
    public TableColumn<Product, String> colNameProduct;
    @FXML
    public TableColumn<Product, Double> colSizeProduct;
    @FXML
    public TableColumn<Product, String> colDescProduct;
    @FXML
    public TableColumn<Product, Double> colPriceProduct;
    @FXML
    public TableColumn<Product, Integer> colStatusProduct;
    @FXML
    public TableColumn<Product, Timestamp> colCreatedAtProduct;
    @FXML
    public TableColumn<Product, Timestamp> colUpdatedAtProduct;
    @FXML
    public TableView<Product> tableListProduct;

    private ObservableList<Category> categoryList;


    public void initialize() throws SQLException {
        // Gender

        reloadTableView();
    }

    private void reloadTableView() {
        // table view categoryList
        tableListCategory.getItems().clear();
        categoryList = FXCollections.observableArrayList(categoryService.getAllCategory());
        for (Category category : categoryList) {
            category.setProductList(productService.getProductsByIdCategory(category.getId()));
        }
        colIdCategory.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
        colNameCategory.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        colStatusCategory.setCellValueFactory(new PropertyValueFactory<Category,Integer>("status"));
        colCreatedAtCategory.setCellValueFactory(new PropertyValueFactory<Category,Timestamp>("createdAt"));
        colUpdatedAtCategory.setCellValueFactory(new PropertyValueFactory<Category,Timestamp>("updatedAt"));
        tableListCategory.setItems(categoryList);
        tableListCategory.getSelectionModel().selectFirst();

        // table view list product
       updateProductCurrentRowCategory();
    }

    @FXML
    public void handleBtnExportEx(ActionEvent actionEvent) {

    }

    @FXML
    public void handleClickedTableView(MouseEvent mouseEvent) {
        updateProductCurrentRowCategory();
    }

    private void updateProductCurrentRowCategory() {
        tableListProduct.getItems().clear();
        colNameProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        colSizeProduct.setCellValueFactory(new PropertyValueFactory<Product, Double>("size"));
        colPriceProduct.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        colDescProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        colStatusProduct.setCellValueFactory(new PropertyValueFactory<Product,Integer>("status"));
        colSupplierProduct.setCellValueFactory(new PropertyValueFactory<Product, Supplier>("supplier"));
        colCreatedAtProduct.setCellValueFactory(new PropertyValueFactory<Product,Timestamp>("createdAt"));
        colUpdatedAtProduct.setCellValueFactory(new PropertyValueFactory<Product,Timestamp>("updatedAt"));
        tableListProduct.setItems(FXCollections.observableArrayList(tableListCategory.getSelectionModel().getSelectedItem().getProductList()));
    }


    public void handleOnKeyPresedTableView(KeyEvent keyEvent) {
        updateProductCurrentRowCategory();
    }
}

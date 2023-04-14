package utc2.itk62.sneaker.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utc2.itk62.sneaker.models.Category;
import utc2.itk62.sneaker.models.Product;
import utc2.itk62.sneaker.models.Supplier;
import utc2.itk62.sneaker.services.CategoryService;
import utc2.itk62.sneaker.services.ProductService;
import utc2.itk62.sneaker.util.CustomMessageBox;

import java.sql.Timestamp;

public class CategoryController {
    private static final CategoryService categoryService = new CategoryService();
    private static final ProductService productService = new ProductService();

    @FXML
    public TextField searchValue;
    @FXML
    public TableView<Category> tableListCategory;
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
    public TableColumn<Product, Integer> colSTTProduct;
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
    @FXML
    public TextField idCategory;
    @FXML
    public TextField nameCategory;
    @FXML
    public ComboBox<String> keySearch;
    @FXML
    public Button btnExportExcel;
    @FXML
    public Button btnDeleteCategory;
    @FXML
    public Button btnAddCategory;
    @FXML
    public Button btnUpdateCategory;


    private ObservableList<Category> categoryList;
    private ObservableList<String> listKeySearch = FXCollections.observableArrayList("ID", "Name");

    public void initialize() {
        // Add keysearch
        keySearch.setItems(listKeySearch);
        keySearch.setValue("ID");
        reloadTableView();
        setupBtnExportExcel();
        setupHandleSearch();
    }


    // Setup handle search
    private void setupHandleSearch() {


    }

    ;


    // setup handle export excel
    private void setupBtnExportExcel() {
        btnExportExcel.setOnAction(action -> {
            categoryService.exportExcel(categoryList);
        });
    }


    private void reloadTableView() {
        tableListCategory.getItems().clear();
        tableListProduct.getItems().clear();
        categoryList = FXCollections.observableArrayList(categoryService.getAllCategory());
        for (Category category : categoryList) {
            category.setProductList(productService.getProductsByIdCategory(category.getId()));
        }
        colIdCategory.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
        colNameCategory.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        colStatusCategory.setCellValueFactory(new PropertyValueFactory<Category, Integer>("status"));
        colCreatedAtCategory.setCellValueFactory(new PropertyValueFactory<Category, Timestamp>("createdAt"));
        colUpdatedAtCategory.setCellValueFactory(new PropertyValueFactory<Category, Timestamp>("updatedAt"));
        tableListCategory.setItems(categoryList);

        // update table other
        tableListCategory.getSelectionModel().selectFirst();
        updateProductCurrentRowCategory();
        updateCurrentCategoryToForm();
    }

    @FXML
    public void handleClickedTableView(MouseEvent mouseEvent) {
        updateProductCurrentRowCategory();
        updateCurrentCategoryToForm();
    }

    @FXML
    public void handleOnKeyPresedTableView(KeyEvent keyEvent) {
        updateProductCurrentRowCategory();
        updateCurrentCategoryToForm();
    }

    private void updateProductCurrentRowCategory() {
        tableListProduct.getItems().clear();
        colNameProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        colSizeProduct.setCellValueFactory(new PropertyValueFactory<Product, Double>("size"));
        colPriceProduct.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        colDescProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        colStatusProduct.setCellValueFactory(new PropertyValueFactory<Product, Integer>("status"));
        colStatusProduct.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        colSupplierProduct.setCellValueFactory(new PropertyValueFactory<Product, Supplier>("supplier"));
        colCreatedAtProduct.setCellValueFactory(new PropertyValueFactory<Product, Timestamp>("createdAt"));
        colUpdatedAtProduct.setCellValueFactory(new PropertyValueFactory<Product, Timestamp>("updatedAt"));
        tableListProduct.setItems(FXCollections.observableArrayList(tableListCategory.getSelectionModel().getSelectedItem().getProductList()));
    }

    private void updateCurrentCategoryToForm() {
        Category category = tableListCategory.getSelectionModel().getSelectedItem();
        idCategory.setText(String.valueOf(category.getId()));
        nameCategory.setText(category.getName());
    }


    @FXML
    public void handleChangeSeachValue(KeyEvent keyEvent) {
        // Khởi tạo FilteredList và gán nó với danh sách positionList
        FilteredList<Category> filteredList = new FilteredList<>(categoryList, p -> true);

        // Gán FilteredList làm nguồn dữ liệu cho TableView
        tableListCategory.setItems(filteredList);
        String searchText = searchValue.getText().toLowerCase();
        filteredList.setPredicate(p -> {
            if (searchText.isEmpty()) {
                return true;
            }
            if ("Name".equals(keySearch.getValue())) {
                return p.getName().toLowerCase().contains(searchText);
            }
            return String.valueOf(p.getId()).toLowerCase().contains(searchText);
        });
        tableListCategory.getSelectionModel().selectFirst();
        updateCurrentCategoryToForm();
        updateProductCurrentRowCategory();
    }

    @FXML
    public void handleBtnAdd(ActionEvent actionEvent) {
        tableListCategory.getSelectionModel().clearSelection();
        if(idCategory.getText()!=null&&idCategory.getText().length()>0){
            idCategory.setText("");
            nameCategory.setText("");
            CustomMessageBox.boxInfo("Please enter the name category");
            nameCategory.requestFocus();
            return;
        }
        Category category=new Category(nameCategory.getText(),null);
        if(!categoryService.createCategory(category)){
            CustomMessageBox.boxError("Sometimes the category service is not available");
            return;
        }
        CustomMessageBox.boxOk("Created category successfully");
        reloadTableView();
    }
};

    

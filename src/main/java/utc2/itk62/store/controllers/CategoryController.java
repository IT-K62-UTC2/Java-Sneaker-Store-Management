package utc2.itk62.store.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utc2.itk62.store.models.Category;
import utc2.itk62.store.models.Product;
import utc2.itk62.store.models.Supplier;
import utc2.itk62.store.services.CategoryService;
import utc2.itk62.store.services.ProductService;
import utc2.itk62.store.util.CustomMessageBox;

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
        setUpTableListCategory();
        setUpUpdateCategory();
        setUpAddCategory();
        setUpDeleteCategory();
    }


    // Setup handle search
    private void setupHandleSearch() {
        searchValue.setOnKeyReleased(keyEvent -> {
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
        });
    }

    private void setUpUpdateCategory() {
        btnUpdateCategory.setOnAction(actionEvent ->{
            Category category = new Category();
            category.setId(Integer.parseInt(idCategory.getText()));
            category.setName(nameCategory.getText());
            if(!categoryService.updateCategory(category)) {
                CustomMessageBox.boxError("Something went wrong");
                return;
            }
            CustomMessageBox.boxOk("Update category successfully");
            reloadTableView();
        });
    }

    private void setUpDeleteCategory() {
        btnDeleteCategory.setOnAction(actionEvent -> {
            Category category = tableListCategory.getSelectionModel().getSelectedItem();
            if(category == null) {
                return;
            }
            if(!categoryService.deleteCategory(category)) {
                CustomMessageBox.boxError("Something went wrong");
                return;
            }
            CustomMessageBox.boxOk("Delete category successfully");
            reloadTableView();
        });
    }

    private void setUpAddCategory() {
        btnAddCategory.setOnAction(actionEvent ->{
            Category category = new Category();
            if(!idCategory.getText().equals("")) {
                cleanForm();
            }
            if(nameCategory.getText().equals("")) {
                CustomMessageBox.boxError("Please enter a name category");
                nameCategory.requestFocus();
                return;
            }
            category.setName(nameCategory.getText());
            if(!categoryService.createCategory(category)) {
                CustomMessageBox.boxError("Something went wrong");
                return;
            }
            CustomMessageBox.boxOk("Add category successfully");
            reloadTableView();
        });
    }

    private void setUpTableListCategory() {
        tableListCategory.setOnMouseClicked(mouseEvent -> {
            updateProductCurrentRowCategory();
            updateCurrentCategoryToForm();
        });
        tableListCategory.setOnKeyPressed(keyEvent -> {
            updateProductCurrentRowCategory();
            updateCurrentCategoryToForm();
        });
    }


    // setup handle export excel
    private void setupBtnExportExcel() {
        btnExportExcel.setOnAction(action -> {
            categoryService.exportExcel(categoryList);
        });
    }


    private void reloadTableView() {
        if (!tableListProduct.getItems().isEmpty()) {
            tableListProduct.getItems().clear();
        }
        if (!tableListCategory.getItems().isEmpty()) {
            tableListCategory.getItems().clear();
        }
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
        updateCurrentCategoryToForm();
        updateProductCurrentRowCategory();
    }

    private void updateProductCurrentRowCategory() {
        tableListProduct.getItems().clear();
        colNameProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
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
        if (category == null) {
            cleanForm();
            return;
        }
        idCategory.setText(String.valueOf(category.getId()));
        nameCategory.setText(category.getName());

    }

    private void cleanForm() {
        idCategory.setText("");
        nameCategory.setText("");
    }
};

    

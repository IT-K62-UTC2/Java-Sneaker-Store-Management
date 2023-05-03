package utc2.itk62.store.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utc2.itk62.store.Main;
import utc2.itk62.store.models.ImportGoodsDetail;
import utc2.itk62.store.models.Product;
import utc2.itk62.store.services.ProductService;
import utc2.itk62.store.util.CustomAlert;
import utc2.itk62.store.util.FormatDouble;

import java.io.IOException;
import java.util.Optional;

public class ImportGoodsController {
    private static final ProductService productService = new ProductService();


    public TextField valueSearch;
    public TableView<Product> tableListProduct;
    public ComboBox<String> keySearch;
    public ImageView imageProduct;
    public Button deleteAll;
    public Label quantityImportGoods;
    public Label moneyImportGoods;
    public TableColumn<Product, Integer> colSttProduct;
    public TableColumn<Product, Integer> colIdProduct;
    public TableColumn<Product, String> colNameProduct;
    public TableColumn<Product, String> colPriceProduct;
    public TableColumn<Product, Integer> colQuantityProduct;
    public TableView<ImportGoodsDetail>tableListImportDetail;
    public TableColumn<ImportGoodsDetail, Integer> colSttImportDetail;
    public TableColumn<ImportGoodsDetail, Integer> colIdProductImportDetail;
    public TableColumn<ImportGoodsDetail, Product> colProductImportDetail;
    public TableColumn<ImportGoodsDetail, String> colUnitPriceImportDetail;
    public TableColumn<ImportGoodsDetail, Integer> colQuantityImportDetail;
    public TableColumn<ImportGoodsDetail, String> colAmount;
    public Button btnImport;
    public AnchorPane anchorPane;
    public Button btnAddProduct;

    private ObservableList<Product> productList;

    public void initialize() {
        setupBtnAdd();
        reloadTableView();
    }

    private void reloadTableView() {
        // table view
        if(!tableListProduct.getItems().isEmpty()) {
            tableListProduct.getItems().clear();
        }
        productList = FXCollections.observableArrayList(productService.getAllProduct());
        colIdProduct.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNameProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPriceProduct.setCellValueFactory(param -> {
            Product product = param.getValue();
            return new SimpleStringProperty(FormatDouble.toString(product.getPrice()));
        });
        colQuantityProduct.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableListProduct.setItems(productList);
        tableListProduct.getSelectionModel().selectFirst();
//        updateProductCurrentRowToForm();
    }

    private void setupBtnAdd() {
        btnAddProduct.setOnAction(actionEvent -> {
            showProductImport();
        });
    }



    // Start card product import
    private void showProductImport() {
        Product product = tableListProduct.getSelectionModel().getSelectedItem();
        if(product == null) {
            CustomAlert.showAlert(Alert.AlertType.WARNING, tableListImportDetail.getScene().getWindow(),
                    "Product import is empty", "Please select a product");
            return;
        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/card-import-goods.fxml"));
        Parent node = null;
        try {
            node = loader.load();
            Scene scene = new Scene(node);
            Stage stage = new Stage();
            stage.initOwner(anchorPane.getScene().getWindow());
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

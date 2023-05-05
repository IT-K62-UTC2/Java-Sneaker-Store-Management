package utc2.itk62.store.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utc2.itk62.store.Main;
import utc2.itk62.store.models.ImportGoods;
import utc2.itk62.store.models.ImportGoodsDetail;
import utc2.itk62.store.models.Product;
import utc2.itk62.store.services.ImportGoodsService;
import utc2.itk62.store.services.ProductService;
import utc2.itk62.store.util.CustomAlert;
import utc2.itk62.store.util.FormatDouble;

import java.io.IOException;
import java.sql.Timestamp;

public class ImportGoodsController {
    private static final ProductService productService = new ProductService();
    private static final ImportGoodsService importGoodsService = new ImportGoodsService();


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
    public TableView<ImportGoodsDetail> tableListImportDetail;
    public TableColumn<ImportGoodsDetail, Integer> colSttImportDetail;
    public TableColumn<ImportGoodsDetail, Integer> colIdProductImportDetail;
    public TableColumn<ImportGoodsDetail, Product> colProductImportDetail;
    public TableColumn<ImportGoodsDetail, String> colUnitPriceImportDetail;
    public TableColumn<ImportGoodsDetail, Integer> colQuantityImportDetail;
    public TableColumn<ImportGoodsDetail, String> colAmount;
    public Button btnImport;
    public AnchorPane anchorPane;
    public Button btnAddProduct;
    public TabPane tabPane;
    public Tab tabImport;
    public Tab tabHistory;


    // History
    public TextField valueSearchHistory;
    public ComboBox<String> keySearchHistory;
    public DatePicker fromDateHistory;
    public DatePicker toDateHistory;
    public TableView<ImportGoods> tbHtrImport;
    public TableColumn<ImportGoods, Integer> colSttHtrImport;
    public TableColumn<ImportGoods, Integer> colIdHtrImport;
    public TableColumn<ImportGoods, Integer> colQtyHtrImport;
    public TableColumn<ImportGoods, String> colMoneyHtrImport;
    public TableColumn<ImportGoods, Timestamp> colCreateAtHtrImport;
    public TableView<ImportGoodsDetail> tbHtrImportDetail;
    public TableColumn<ImportGoodsDetail, Integer> colSttHtrImportDetail;
    public TableColumn<ImportGoodsDetail, Product> colProductHtrImportDetail;
    public TableColumn<ImportGoodsDetail, Integer> colQtyHtrImportDetail;
    public TableColumn<ImportGoodsDetail, String> colPriceHtrImportDetail;
    public TableColumn<ImportGoodsDetail, String> colMoneyHtrImportDetail;


    private ObservableList<Product> productList;
    private ObservableList<ImportGoods> importGoodsList;

    public void initialize() {
        setupTabPane();
        tabPane.getSelectionModel().select(tabImport);

        // Import
        setupBtnAdd();
        reloadTableViewImport();

        // History
    }

    private void setupTabPane() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tabImport) {
                System.out.println("Tab clicked: " + newTab.getText());
                return;
            }

            if (newTab == tabHistory) {
                System.out.println("Tab clicked: " + newTab.getText());
            }
            // Handle the event when a tab is clicked
        });
    }

    private void reloadTableViewImport() {
        // table view
        if (!tableListProduct.getItems().isEmpty()) {
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
            showProductImport(tableListProduct.getSelectionModel().getSelectedItem());
        });
    }


    // Start card product import
    private void showProductImport(Product product) {
        VBox vbox = new VBox();
        vbox.alignmentProperty();

        // Tạo các thành phần giao diện
        Label idProduct = new Label(Integer.toString(product.getId()));
        Label nameProduct = new Label(product.getName());

        HBox hbox1 = new HBox();
        Label priceProduct = new Label("Price:");
        Label priceProductValue = new Label(FormatDouble.toString(product.getPrice()));
        hbox1.getChildren().add(priceProduct);
        hbox1.getChildren().add(priceProductValue);

        vbox.getChildren().add(idProduct);
        vbox.getChildren().add(nameProduct);
        vbox.getChildren().add(hbox1);

        Stage stage = new Stage();
        Scene scene = new Scene(vbox);
        stage.initOwner(anchorPane.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}

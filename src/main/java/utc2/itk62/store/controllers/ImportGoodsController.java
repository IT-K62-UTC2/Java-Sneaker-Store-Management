package utc2.itk62.store.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import utc2.itk62.store.models.ImportGoods;
import utc2.itk62.store.models.ImportGoodsDetail;
import utc2.itk62.store.models.Product;
import utc2.itk62.store.services.ImportGoodsService;
import utc2.itk62.store.services.ProductService;
import utc2.itk62.store.util.FormatDouble;

import java.sql.Timestamp;
import java.util.ArrayList;

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
    public TableColumn colActionImportGoodsDetail;
    public TableColumn<ImportGoodsDetail, Integer> colSttImportDetail;
    public TableColumn<ImportGoodsDetail, String> colIdProductImportDetail;
    public TableColumn<ImportGoodsDetail, Product> colProductImportDetail;
    public TableColumn<ImportGoodsDetail, String> colUnitPriceImportDetail;
    public TableColumn<ImportGoodsDetail, Integer> colQuantityImportDetail;
    public TableColumn<ImportGoodsDetail, String> colAmountDetail;
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
    private ObservableList<ImportGoodsDetail> importGoodsDetailList = FXCollections.observableArrayList(new ArrayList<>());

    public void initialize() {
        setupTabPane();
        tabPane.getSelectionModel().select(tabImport);

        // Import
        setupBtnAdd();
        reloadTableViewImport();

        // Import details
        reloadTableViewImportDetails();

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

    private void reloadTableViewImportDetails() {
        if (!tableListImportDetail.getItems().isEmpty()) {
            tableListImportDetail.getItems().clear();
        }
        colProductImportDetail.setCellValueFactory(new PropertyValueFactory<>("product"));
        colIdProductImportDetail.setCellValueFactory(param -> {
            return new SimpleStringProperty(String.valueOf(param.getValue().getProduct().getId()));
        });
        Callback<TableColumn<ImportGoodsDetail, String>, TableCell<ImportGoodsDetail, String>> cellFactory
                = //
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<ImportGoodsDetail, String> param) {
                        final TableCell<ImportGoodsDetail, String> cell = new TableCell<>() {

                            final Button btn = new Button("Delete");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(action -> {
                                        getTableView().getItems().remove(getIndex());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
//        colActionImportGoodsDetail.setCellFactory(cellFactory);
//        colUnitPriceImportDetail.setCellValueFactory(param -> new SimpleStringProperty(FormatDouble.toString(param.getValue().getUnitPrice())));
//        colQuantityImportDetail.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        colAmountDetail.setCellValueFactory(param -> new SimpleStringProperty(FormatDouble.toString(param.getValue().getMoneyTotal())));
//        tableListImportDetail.setItems(importGoodsDetailList);
    }

    private void setupBtnAdd() {
        btnAddProduct.setOnAction(actionEvent -> {
            showProductImport(tableListProduct.getSelectionModel().getSelectedItem());
        });
    }


    // Start card product import
    private void showProductImport(Product product) {
        VBox vbox = new VBox();
        vbox.fillWidthProperty();
        vbox.alignmentProperty();

        // Tạo các thành phần giao diện
        Label idProductLabel = new Label("ID Product:");
        Label idProdcut = new Label(Integer.toString(product.getId()));
        HBox hbox1 = new HBox();
        hbox1.getChildren().add(idProductLabel);
        hbox1.getChildren().add(idProdcut);

        Label nameProductLabel = new Label("Name Product:");
        Label nameProdcut = new Label(product.getName());
        HBox hbox2 = new HBox();
        hbox2.getChildren().add(nameProductLabel);
        hbox2.getChildren().add(nameProdcut);


        HBox hbox3 = new HBox();
        Label priceProductLabel = new Label("Price:");
        Label priceProduct = new Label(FormatDouble.toString(product.getPrice()));
        hbox3.getChildren().add(priceProductLabel);
        hbox3.getChildren().add(priceProduct);

        HBox hbox4 = new HBox();
        Label priceImportLabel = new Label("Price import:");
        TextField priceImport = new TextField();
        priceImport.textProperty().addListener((observable, oldValue, newValue) -> {
            // kiểm tra xem chuỗi mới nhập vào có thể định dạng thành số không
            try {
                String valueAmount = FormatDouble.toString(FormatDouble.toDouble(newValue));
                priceImport.setText(valueAmount);
            } catch (NumberFormatException e) {
                // nếu không thể định dạng thành số, bỏ qua và giữ nguyên chuỗi nhập vào
            }
        });
        hbox4.getChildren().add(priceImportLabel);
        hbox4.getChildren().add(priceImport);

        HBox hbox5 = new HBox();
        Label quantityImportLabel = new Label("Quantity:");
        TextField quantityImport = new TextField();
        quantityImport.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                quantityImport.setText("");
            } else if (!newValue.matches("\\d*")) {
                quantityImport.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        hbox5.getChildren().add(quantityImportLabel);
        hbox5.getChildren().add(quantityImport);

        HBox hbox6 = new HBox();
        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");
        hbox6.getChildren().add(ok);
        hbox6.getChildren().add(cancel);

        vbox.getChildren().add(hbox1);
        vbox.getChildren().add(hbox2);
        vbox.getChildren().add(hbox3);
        vbox.getChildren().add(hbox4);
        vbox.getChildren().add(hbox5);
        vbox.getChildren().add(hbox6);

        Stage stage = new Stage();
        Scene scene = new Scene(vbox);
        stage.initOwner(anchorPane.getScene().getWindow());
        stage.setScene(scene);
        stage.show();

        ok.setOnAction(actionEvent -> {
////            Stage stageClose = (Stage) ok.getScene().getWindow();
////            for (ImportGoodsDetail item : importGoodsDetailList) {
////                if (item.getProduct().getId() == product.getId()) {
////                    item.setQuantity(item.getQuantity() + Integer.parseInt(quantityImport.getText()));
////                    item.setUnitPrice(FormatDouble.toDouble(priceImport.getText()));
////                    item.setMoneyTotal(FormatDouble.toDouble(priceImport.getText()) * item.getQuantity());
////                    tableListImportDetail.refresh();
////                    stageClose.close();
////                    return;
////                }
////            }
////
////            ImportGoodsDetail importGoodsDetail = new ImportGoodsDetail();
////            importGoodsDetail.setProduct(product);
////            importGoodsDetail.setQuantity(Integer.parseInt(quantityImport.getText()));
////            importGoodsDetail.setUnitPrice(FormatDouble.toDouble(priceImport.getText()));
////            importGoodsDetail.setMoneyTotal(Integer.parseInt(quantityImport.getText()) * importGoodsDetail.getUnitPrice());
////            importGoodsDetailList.add(importGoodsDetail);
//            stageClose.close();
        });

        cancel.setOnAction(actionEvent -> {
            Stage stageClose = (Stage) cancel.getScene().getWindow();
            stageClose.close();
        });

    }
}

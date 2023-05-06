package utc2.itk62.store.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import utc2.itk62.store.models.ImportGoods;
import utc2.itk62.store.models.ImportGoodsDetail;
import utc2.itk62.store.models.Product;
import utc2.itk62.store.services.ImportGoodsService;
import utc2.itk62.store.services.ProductService;
import utc2.itk62.store.util.CustomAlert;
import utc2.itk62.store.util.FormatDouble;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

public class ImportGoodsController {
    private static final ProductService productService = new ProductService();
    private static final ImportGoodsService importGoodsService = new ImportGoodsService();


    public TextField valueSearch;
    public TableView<Product> tableListProduct;
    public ComboBox<String> keySearch;
    public ImageView imageProduct;
    public Button btnDeleteAll;
    public Label quantityImportGoods;
    public Label moneyImportGoods;
    public TableColumn<Product, Integer> colSttProduct;
    public TableColumn<Product, Integer> colIdProduct;
    public TableColumn<Product, String> colNameProduct;
    public TableColumn<Product, String> colPriceProduct;
    public TableColumn<Product, Integer> colQuantityProduct;
    public TableView<ImportGoodsDetail> tableListImportDetail;
    public TableColumn<ImportGoodsDetail, String> colActionImportGoodsDetail;
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
        setupBtnDeleteAll();
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
            return new SimpleStringProperty(FormatDouble.toString(product.getImportPrice()));
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
                                        updateQuantityAndAmount();
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        colActionImportGoodsDetail.setCellFactory(cellFactory);
        colUnitPriceImportDetail.setCellValueFactory(param -> new SimpleStringProperty(FormatDouble.toString(param.getValue().getProduct().getImportPrice())));
        colQuantityImportDetail.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colAmountDetail.setCellValueFactory(param -> new SimpleStringProperty(FormatDouble.toString(param.getValue().getMoneyTotal())));
        tableListImportDetail.setItems(importGoodsDetailList);
    }

    private void updateQuantityAndAmount() {
        int totalQuantity = 0;
        double amount = 0;
        for(ImportGoodsDetail item: importGoodsDetailList) {
            totalQuantity+=item.getQuantity();
            amount+=item.getMoneyTotal();
        }
        quantityImportGoods.setText(String.valueOf(totalQuantity));
        moneyImportGoods.setText(FormatDouble.toString(amount));
    }

    private void setupBtnDeleteAll() {
       btnDeleteAll.setOnAction(actionEvent -> {
           if(!importGoodsDetailList.isEmpty()){
               importGoodsDetailList.clear();
               updateQuantityAndAmount();
           }
       });
    }
    private void setupBtnAdd() {
        btnAddProduct.setOnAction(actionEvent -> {
            Product product = tableListProduct.getSelectionModel().getSelectedItem();
            if(product == null) {
                CustomAlert.showAlert(Alert.AlertType.ERROR, anchorPane.getScene().getWindow(), "Error!", "Please select a product");
                return;
            }
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Enter Quantity");
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter the quantity:");
            dialog.initOwner(anchorPane.getScene().getWindow());
            dialog.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    dialog.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                try {
                    int quantity = Integer.parseInt(result.get());
                    for(ImportGoodsDetail item : importGoodsDetailList) {
                        if(item.getProduct().getId() == product.getId()) {
                            item.setQuantity(item.getQuantity()+quantity);
                            item.setMoneyTotal(item.getQuantity() * product.getImportPrice());
                            tableListImportDetail.refresh();
                            System.out.println("Exiting");
                            updateQuantityAndAmount();
                            return;
                        }
                    }

                    ImportGoodsDetail importGoodsDetail = new ImportGoodsDetail();
                    importGoodsDetail.setQuantity(quantity);
                    importGoodsDetail.setQuantity(quantity);
                    importGoodsDetail.setMoneyTotal(quantity * product.getImportPrice());
                    importGoodsDetail.setProduct(product);
                    importGoodsDetailList.add(importGoodsDetail);
                    updateQuantityAndAmount();
                } catch (NumberFormatException e) {
                    CustomAlert.showAlert(Alert.AlertType.ERROR, anchorPane.getScene().getWindow(), "Error!", "Quantity always > 0");
                    btnAddProduct.fire();
                }
            }
        });
    }


}

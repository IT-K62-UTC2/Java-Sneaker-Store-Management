package utc2.itk62.store.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import utc2.itk62.store.common.User;
import utc2.itk62.store.models.ImportGoods;
import utc2.itk62.store.models.ImportGoodsDetail;
import utc2.itk62.store.models.Product;
import utc2.itk62.store.services.ImportGoodsDetailService;
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
    private static final ImportGoodsDetailService importGoodsDetailService = new ImportGoodsDetailService();


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
    private final ObservableList<ImportGoodsDetail> importGoodsDetailList = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<String> listKetSearch = FXCollections.observableArrayList("ID", "Name");

    // History
    private ObservableList<ImportGoods> listImportGoodsHtr = FXCollections.observableArrayList(new ArrayList<>());

    public void initialize() {
        setupTabPane();
        tabPane.getSelectionModel().select(tabImport);

        // Import
        // Import details
        setupTableListProduct();
        keySearch.setItems(listKetSearch);
        keySearch.setValue("ID");
        setupSearch();
        setupBtnAdd();
        reloadTableViewImport();
        reloadTableViewImportDetails();
        setupBtnImport();
        setupBtnDeleteAll();

        // History
        setupTableHtrImportGoods();
    }

    private void setupTabPane() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tabImport) {
                System.out.println("Tab clicked: " + newTab.getText());
                return;
            }

            if (newTab == tabHistory) {
                reloadTableViewImportGoodsHtr();
            }
        });
    }

    // Start Import
    private void setupSearch() {
        valueSearch.setOnKeyTyped(keyEvent -> {
            // Khởi tạo FilteredList và gán nó với danh sách positionList
            FilteredList<Product> filteredList = new FilteredList<>(productList, p -> true);

            // Gán FilteredList làm nguồn dữ liệu cho TableView
            tableListProduct.setItems(filteredList);
            filteredList.setPredicate(p -> {
                if (valueSearch.getText().isEmpty()) {
                    return true;
                }
                if (keySearch.getValue().equals("Name")) {
                    return p.getName().toLowerCase().contains(valueSearch.getText());
                }
                return p.getId() == Integer.parseInt(valueSearch.getText());
            });
            if (tableListProduct.getSelectionModel() != null) {
                tableListProduct.getSelectionModel().selectFirst();
            }
        });
    }

    private void setupTableListProduct() {
        tableListProduct.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) { // kiểm tra số lần click chuột để xác định double click
                Product product = tableListProduct.getSelectionModel().getSelectedItem(); // lấy item được chọn
                if (product != null) {
                    btnAddProduct.fire();
                }
            }
        });
    }


    private void setupBtnImport() {
        btnImport.setOnAction(actionEvent -> {
            if (importGoodsDetailList.isEmpty()) {
                return;
            }
            if (!CustomAlert.showAlert(Alert.AlertType.CONFIRMATION, btnImport.getScene().getWindow(), "Confirm Import goods", "Are you sure you want to import")) {
                return;
            }
            ImportGoods importGoods = new ImportGoods();
            System.out.println("Id staff" + User.staff.getId());
            importGoods.setStaff(User.staff);
            importGoods.setQuantity(Integer.parseInt(quantityImportGoods.getText()));
            importGoods.setMoneyTotal(FormatDouble.toDouble(moneyImportGoods.getText()));
            ImportGoods importGoodsResult = importGoodsService.createImportGoods(importGoods);
            if (importGoodsResult != null) {
                for (ImportGoodsDetail item : importGoodsDetailList) {
                    item.setImportGoods(importGoodsResult);
                    importGoodsDetailService.createImportGoodsDetail(item);
                }
            }
            reloadTableViewImport();
            tableListImportDetail.getItems().clear();
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
        Callback<TableColumn<ImportGoodsDetail, String>, TableCell<ImportGoodsDetail, String>> cellFactory = //
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
        for (ImportGoodsDetail item : importGoodsDetailList) {
            totalQuantity += item.getQuantity();
            amount += item.getMoneyTotal();
        }
        quantityImportGoods.setText(String.valueOf(totalQuantity));
        moneyImportGoods.setText(FormatDouble.toString(amount));
    }

    private void setupBtnDeleteAll() {
        btnDeleteAll.setOnAction(actionEvent -> {
            if (!importGoodsDetailList.isEmpty()) {
                importGoodsDetailList.clear();
                updateQuantityAndAmount();
            }
        });
    }

    private void setupBtnAdd() {
        btnAddProduct.setOnAction(actionEvent -> {
            Product product = tableListProduct.getSelectionModel().getSelectedItem();
            if (product == null) {
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
                    for (ImportGoodsDetail item : importGoodsDetailList) {
                        if (item.getProduct().getId() == product.getId()) {
                            item.setQuantity(item.getQuantity() + quantity);
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
    // End import

    // Start history
    private void reloadTableViewImportGoodsHtr() {
        if (!tbHtrImport.getItems().isEmpty()) {
            tbHtrImport.getItems().clear();
        }
        listImportGoodsHtr = FXCollections.observableArrayList(importGoodsService.getAllImportGoods());
        colIdHtrImport.setCellValueFactory(new PropertyValueFactory<>("id"));
        colQtyHtrImport.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colMoneyHtrImport.setCellValueFactory(param -> new SimpleStringProperty(FormatDouble.toString(param.getValue().getMoneyTotal())));
        colCreateAtHtrImport.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        tbHtrImport.setItems(listImportGoodsHtr);
        tbHtrImport.getSelectionModel().selectFirst();
        new Thread(() -> {
            for (ImportGoods item : listImportGoodsHtr) {
                item.setImportGoodsDetailList(importGoodsDetailService.getImportDetailByIdImport(item.getId()));
            }
            // update table invoice details
            Platform.runLater(this::updateImportGoodsCurrentRowToDetail);
        }).start();
    }

    private void updateImportGoodsCurrentRowToDetail() {
        ImportGoods importGoods = tbHtrImport.getSelectionModel().getSelectedItem();
        if (importGoods == null) {
            return;
        }
        tbHtrImportDetail.getItems().clear();
        colProductHtrImportDetail.setCellValueFactory(new PropertyValueFactory<>("product"));
        colQtyHtrImportDetail.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPriceHtrImportDetail.setCellValueFactory(param -> new SimpleStringProperty(FormatDouble.toString(param.getValue().getProduct().getImportPrice())));
        colMoneyHtrImportDetail.setCellValueFactory(param -> new SimpleStringProperty(FormatDouble.toString(param.getValue().getMoneyTotal())));
        tbHtrImportDetail.setItems(FXCollections.observableArrayList(importGoods.getImportGoodsDetailList()));

    }

    private void setupTableHtrImportGoods() {
        tbHtrImport.setOnKeyPressed(keyEvent -> {
            updateImportGoodsCurrentRowToDetail();
        });

        tbHtrImport.setOnMouseClicked(mouseEvent -> {
            updateImportGoodsCurrentRowToDetail();
        });
    }
    // End history
}

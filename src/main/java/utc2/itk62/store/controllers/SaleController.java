package utc2.itk62.store.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utc2.itk62.store.Main;
import utc2.itk62.store.Validator.StaffValidator;
import utc2.itk62.store.common.JasperReportConfig;
import utc2.itk62.store.common.MailConfig;
import utc2.itk62.store.common.User;
import utc2.itk62.store.models.Customer;
import utc2.itk62.store.models.Invoice;
import utc2.itk62.store.models.InvoiceDetail;
import utc2.itk62.store.models.Product;
import utc2.itk62.store.services.CustomerService;
import utc2.itk62.store.services.InvoiceDetailsService;
import utc2.itk62.store.services.InvoiceService;
import utc2.itk62.store.services.ProductService;
import utc2.itk62.store.util.CustomAlert;
import utc2.itk62.store.util.FormatDouble;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleController {
    private static final ProductService productService = new ProductService();
    private static final CustomerService customerService = new CustomerService();
    private static final InvoiceService invoiceService = new InvoiceService();
    private static final InvoiceDetailsService invoiceDetailsService = new InvoiceDetailsService();
    private final List<CardProductController> listCardControllers = new ArrayList<>();
    private final ObservableList<Product> listProducts = FXCollections.observableArrayList(productService.getAllProduct());
    private final ObservableList<Customer> customerList = FXCollections.observableArrayList(customerService.getAllCustomer());
    public GridPane menuProduct;
    public ComboBox<Customer> customer;
    public TableView<InvoiceDetail> tableViewOrder;
    public TableColumn<InvoiceDetail, Product> colNameProd;
    public TableColumn<InvoiceDetail, Integer> colQuantityProd;
    public TableColumn<InvoiceDetail, String> colPriceProd;
    public TableColumn<InvoiceDetail, String> colTotal;
    public TableColumn<InvoiceDetail, String> colAction;
    public TextField deliveryPhoneNumber;
    public TextArea deliveryAddress;
    public Label totalQuantityView;
    public Label totalMoneyView;
    public Label total;
    public TextField amount;
    public Label change;
    public Button btnPay;
    public Button btnRemove;
    public Button btnReceipt;
    public ObservableList<InvoiceDetail> listOrders = FXCollections.observableArrayList(new ArrayList<>());

    public void initialize() {
        setupAmountTextField();
        menuDisplayProduct();
        setupListOrder();
        setupTableViewOrder();
        setupCustomer();
        setUpChangeAmount();
        setupBtnPay();
        setupBtnRemove();
    }

    private void setupCustomer() {
        customer.setItems(customerList);
        customer.setOnAction(action -> {
            deliveryAddress.setText(customer.getValue().getAddress());
            deliveryPhoneNumber.setText(customer.getValue().getPhoneNumber());
        });
        customer.setValue(customerList.get(0));

    }

    private void setupAmountTextField() {
        amount.textProperty().addListener((observable, oldValue, newValue) -> {
            // kiểm tra xem chuỗi mới nhập vào có thể định dạng thành số không
            try {
                String valueAmount = FormatDouble.toString(FormatDouble.toDouble(newValue));
                amount.setText(valueAmount);
            } catch (NumberFormatException e) {
                // nếu không thể định dạng thành số, bỏ qua và giữ nguyên chuỗi nhập vào
            }
        });
        amount.setOnMousePressed(mouseEvent -> {
            amount.selectAll();
        });
    }

    private void setupListOrder() {
        tableViewOrder.setItems(listOrders);
        colQuantityProd.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        // handle when change quantity in table view
        colQuantityProd.setCellFactory(colum -> new TableCell<InvoiceDetail, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    updatePrice();
                    setText(String.valueOf(item));
                }
            }
        });
        colNameProd.setCellValueFactory(new PropertyValueFactory<>("product"));
        Callback<TableColumn<InvoiceDetail, String>, TableCell<InvoiceDetail, String>> cellFactory
                = //
                new Callback<TableColumn<InvoiceDetail, String>, TableCell<InvoiceDetail, String>>() {
                    @Override
                    public TableCell call(final TableColumn<InvoiceDetail, String> param) {
                        final TableCell<InvoiceDetail, String> cell = new TableCell<InvoiceDetail, String>() {

                            final Button btn = new Button("Delete");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(action -> {
                                        InvoiceDetail invoiceDetail = getTableView().getItems().get(getIndex());
                                        for (CardProductController cardProductController : listCardControllers) {
                                            if (cardProductController.getProduct().getId() == invoiceDetail.getProduct().getId()) {
                                                cardProductController.quantity.setText(
                                                        String.valueOf(Integer.parseInt(cardProductController.quantity.getText())
                                                                + invoiceDetail.getProductQuantity()));

                                                cardProductController.cashQuantity.setValueFactory(
                                                        new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.parseInt(cardProductController.quantity.getText()), 0));
                                                break;
                                            }
                                        }
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
        colAction.setCellFactory(cellFactory);
        colPriceProd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceDetail, String> param) {
                InvoiceDetail invoiceDetail = param.getValue();
                return new SimpleStringProperty(FormatDouble.toString(invoiceDetail.getProduct().getPrice()));

            }
        });
        colTotal.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceDetail, String> param) {
                InvoiceDetail invoiceDetail = param.getValue();
                double total = invoiceDetail.getMoneyTotal();
                return new SimpleStringProperty(FormatDouble.toString(total));
            }
        });

    }


    public void menuDisplayProduct() {
        int col = 0;
        int row = 0;
        menuProduct.getRowConstraints().clear();
        menuProduct.getColumnConstraints().clear();
        menuProduct.getChildren().clear();
        for (int i = 0; i < listProducts.size(); i++) {
            try {
                FXMLLoader load = new FXMLLoader(Main.class.getResource("views/card-product.fxml"));
                Pane pane = load.load();
                CardProductController cardProductController = load.getController();
                cardProductController.setProduct(listProducts.get(i));
                cardProductController.setBtnAdd(this);
                listCardControllers.add(cardProductController);
                if (col == 3) {
                    col = 0;
                    row += 1;
                }
                menuProduct.add(pane, col++, row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void setupTableViewOrder() {
        listOrders.addListener((ListChangeListener<InvoiceDetail>) c -> {
            updatePrice();
        });
    }

    private void setUpChangeAmount() {
        amount.setOnKeyReleased(keyEvent -> {
            double totalMoney = FormatDouble.toDouble(total.getText());
            double amountInput = amount.getText().equals("") ? FormatDouble.toDouble("0") : FormatDouble.toDouble(amount.getText());
            change.setText(FormatDouble.toString(amountInput - totalMoney));
        });
    }

    private void setupBtnRemove() {
        btnRemove.setOnAction(actionEvent -> {
            listOrders.clear();
            listCardControllers.clear();
            menuDisplayProduct();
        });
    }

    private void setupBtnPay() {
        btnPay.setOnAction(actionEvent -> {
            if (tableViewOrder.getItems().isEmpty()) {
                return;
            }
            if (!CustomAlert.showAlert(Alert.AlertType.CONFIRMATION, btnPay.getScene().getWindow(), "Thanh toán", "Xác nhận thanh toán?")) {
                return;
            }
            Invoice invoice = new Invoice();
            // set stafff
            invoice.setStaff(User.staff);
            invoice.setDeliveryPhoneNumber(deliveryPhoneNumber.getText());
            invoice.setDeliveryAddress(deliveryAddress.getText());
            invoice.setMoneyTotal(FormatDouble.toDouble(total.getText()));
            invoice.setCustomer(customer.getValue());
            invoice.setTotalQuantity(Integer.parseInt(totalQuantityView.getText()));
            invoice.setCreatedAt(new Timestamp(new Date().getTime()));
            Invoice invoiceInDb = invoiceService.createInvoice(invoice);
            if (invoiceInDb == null) {
                CustomAlert.showAlert(Alert.AlertType.ERROR, tableViewOrder.getScene().getWindow(), "Error!", "Sometimes the invoice service is not available");
                return;
            }
            List<InvoiceDetail> exportInvoiceDetails = new ArrayList<>();
            for (int i = 0; i < listOrders.size(); i++) {
                InvoiceDetail invoiceDetail = listOrders.get(i);
                invoiceDetail.setInvoice(invoiceInDb);
                invoiceDetailsService.createInvoiceDetail(invoiceDetail);
                productService.updateQuantityProduct(-invoiceDetail.getProductQuantity(), invoiceDetail.getProduct().getId());
                invoiceDetail.getProduct().setQuantity(invoiceDetail.getProduct().getQuantity() - invoiceDetail.getProductQuantity());
                exportInvoiceDetails.add(invoiceDetail);
            }
            invoice.setListInvoiceDetails(exportInvoiceDetails);
            invoice.setId(invoiceInDb.getId());
            CustomAlert.showAlert(Alert.AlertType.INFORMATION, tableViewOrder.getScene().getWindow(), "Success", "Pay successfully");
            tableViewOrder.getItems().clear();


            JasperPrint jasperPrint = JasperReportConfig.createJasperPrintInvoice(invoice);
            if (!invoice.getCustomer().getEmail().equals("") && StaffValidator.validateEmail(invoice.getCustomer().getEmail())) {
                MailConfig.sendInvoiceToCustomer(invoice.getCustomer().getEmail(), jasperPrint, invoice);
            }
            showJasperReport(jasperPrint);
        });
    }

    private void showJasperReport(JasperPrint jasperPrint) {
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setTitle("Invoice");
        jasperViewer.setZoomRatio(0.5F);
        jasperViewer.setFitPageZoomRatio();
        jasperViewer.setSize(600, 800);
        jasperViewer.setLocationRelativeTo(null);
        jasperViewer.setVisible(true);
    }

    private void updatePrice() {
        amount.setText("0");
        double totalView = 0;
        int quantityView = 0;
        for (int i = 0; i < listOrders.size(); i++) {
            totalView += listOrders.get(i).getMoneyTotal();
            quantityView += listOrders.get(i).getProductQuantity();
        }
        totalQuantityView.setText(String.valueOf(quantityView));
        totalMoneyView.setText(FormatDouble.toString(totalView));
        total.setText(FormatDouble.toString(totalView));
        change.setText(FormatDouble.toString(-totalView));
    }
}

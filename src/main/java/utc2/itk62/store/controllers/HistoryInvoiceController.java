package utc2.itk62.store.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingNode;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import utc2.itk62.store.common.JasperReportConfig;
import utc2.itk62.store.models.*;
import utc2.itk62.store.services.InvoiceDetailsService;
import utc2.itk62.store.services.InvoiceService;
import utc2.itk62.store.util.FormatDouble;

import java.sql.Timestamp;

public class HistoryInvoiceController {
    private static final InvoiceService invoicesService = new InvoiceService();
    private static final InvoiceDetailsService invoicesDetailsService = new InvoiceDetailsService();


    public TextField valueSearch;
    public ComboBox<String> keySearch;
    public DatePicker fromDate;
    public DatePicker toDate;
    public TableColumn<Integer, Integer> colSttInvoice;
    public TableColumn<Invoice, Integer> colIdInvoice;
    public TableColumn<Invoice, Staff> colStaffInvoice;
    public TableColumn<Invoice, Customer> colCustomerInvoice;
    public TableColumn<Invoice, Double> colTotalMoneyInvoice;
    public TableColumn<Invoice, Integer> colTotalQuantityInvoice;
    public TableColumn<Invoice, String> colDeliveryAddressInvoice;
    public TableColumn<Invoice, String> colDeliveryPhoneNumberInvoice;
    public TableColumn<Invoice, Timestamp> colCreatedAtInvoice;
    public TableView<Invoice> tableListInvoice;

    public TableColumn<Integer, Integer> colSttInvoiceDetail;
    public TableColumn<InvoiceDetail, Product> colProductInvoiceDetail;
    public TableColumn<InvoiceDetail, String> colPriceInvoiceDetail;
    public TableColumn<InvoiceDetail, Integer> colQuantityInvoiceDetail;
    public TableColumn<InvoiceDetail, Double> colTotalMoneyInvoiceDetail;
    public TableView<InvoiceDetail> tableListInvoiceDetail;
    public AnchorPane viewInvoice;
    public Button btnExportInvoice;
    public Button btnExportExcel;

    private ObservableList<Invoice> listInvoice;
    private ObservableList<String> listKetSearch = FXCollections.observableArrayList(
            "ID","Staff", "Customer", "Delivery Address", "Delivery PhoneNumber");


    public void initialize() {
        // keysearch
        keySearch.setItems(listKetSearch);
        keySearch.setValue("ID");
        setupSearch();
        setupExportExcel();
        reloadTableView();
        setUpTableListInvoice();
        setupBtnExportInvoice();
    }

    private void reloadTableView() {
        listInvoice = FXCollections.observableArrayList(invoicesService.getAllInvoice());
        if(listInvoice.size() == 0) {
            return;
        }
        colCustomerInvoice.setCellValueFactory(new PropertyValueFactory<>("customer"));
        colStaffInvoice.setCellValueFactory(new PropertyValueFactory<>("staff"));
        colTotalMoneyInvoice.setCellValueFactory(new PropertyValueFactory<>("moneyTotal"));
        colTotalMoneyInvoice.setCellFactory(column -> new TableCell<Invoice, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(FormatDouble.toString(item));
                }
            }
        });

        colTotalQuantityInvoice.setCellValueFactory(new PropertyValueFactory<>("totalQuantity"));
        colDeliveryAddressInvoice.setCellValueFactory(new PropertyValueFactory<>("deliveryAddress"));
        colDeliveryPhoneNumberInvoice.setCellValueFactory(new PropertyValueFactory<>("deliveryPhoneNumber"));
        colCreatedAtInvoice.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colIdInvoice.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableListInvoice.setItems(listInvoice);
        tableListInvoice.getSelectionModel().selectFirst();
        new Thread(()-> {
            for (Invoice item : listInvoice) {
                item.setListInvoiceDetails(invoicesDetailsService.getInvoiceDetailByIdInvoice(item.getId()));
            }
            Platform.runLater(()->{
                // update table invoice details
                updateInvoiceDetailsCurrentRowInvoice();
                loadInvoice();
            });
        }).start();
    }

    private void setupExportExcel() {
        btnExportExcel.setOnAction(actionEvent -> {
            invoicesService.exportExcel(listInvoice, keySearch.getScene().getWindow());
        });
    }

    private void setupSearch() {
        valueSearch.setOnKeyReleased(keyEvent -> {
            // Khởi tạo FilteredList và gán nó với danh sách positionList
            FilteredList<Invoice> filteredList = new FilteredList<>(listInvoice, p -> true);

            // Gán FilteredList làm nguồn dữ liệu cho TableView
            tableListInvoice.setItems(filteredList);
            String searchText = valueSearch.getText().toLowerCase();
            filteredList.setPredicate(p -> {
                if (searchText.isEmpty()) {
                    return true;
                }
                if ("ID".equals(keySearch.getValue())) {
                    return String.valueOf(p.getId()).toLowerCase().contains(searchText);
                }
                if("Staff".equals(keySearch.getValue())) {
                    return p.getStaff().toString().toLowerCase().contains(searchText);
                }
                if ("Customer".equals(keySearch.getValue())) {
                    return p.getCustomer().toString().toLowerCase().contains(searchText);
                }
                if ("Delivery Address".equals(keySearch.getValue())) {
                    return p.getDeliveryAddress().toLowerCase().contains(searchText);
                }

                return p.getDeliveryPhoneNumber().toLowerCase().contains(searchText);
            });
            tableListInvoice.getSelectionModel().selectFirst();
            loadInvoice();
            updateInvoiceDetailsCurrentRowInvoice();
        });
    }

    private void updateInvoiceDetailsCurrentRowInvoice() {
        Invoice currentInvoice = tableListInvoice.getSelectionModel().getSelectedItem();
        if(currentInvoice == null) {
            return;
        }
        tableListInvoiceDetail.getItems().clear();
        colProductInvoiceDetail.setCellValueFactory(new PropertyValueFactory<>("product"));
        colQuantityInvoiceDetail.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        colTotalMoneyInvoiceDetail.setCellValueFactory(new PropertyValueFactory<>("moneyTotal"));
        colTotalMoneyInvoiceDetail.setCellFactory(column -> new TableCell<InvoiceDetail, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(FormatDouble.toString(item));
                }
            }
        });

        colPriceInvoiceDetail.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceDetail, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceDetail, String> param) {
                InvoiceDetail invoiceDetail = param.getValue();
                return new SimpleStringProperty(FormatDouble.toString(invoiceDetail.getProduct().getPrice()));
            }
        });
        tableListInvoiceDetail.setItems(FXCollections.observableArrayList(currentInvoice.getListInvoiceDetails()));
    }

    private void setUpTableListInvoice() {
        tableListInvoice.setOnMouseClicked(mouseEvent -> {
            loadInvoice();
            updateInvoiceDetailsCurrentRowInvoice();
        });
        tableListInvoice.setOnKeyPressed(keyEvent -> {
            loadInvoice();
            updateInvoiceDetailsCurrentRowInvoice();
        });
    }

    public void exportInvoice() {
        Invoice invoice = tableListInvoice.getSelectionModel().getSelectedItem();
        JasperPrint jasperPrint = JasperReportConfig.createJasperPrintInvoice(invoice);
        embedJasperReport(jasperPrint);
        showJasperReport(jasperPrint);
    }

    public void loadInvoice() {
        Invoice invoice = tableListInvoice.getSelectionModel().getSelectedItem();
        JasperPrint jasperPrint = JasperReportConfig.createJasperPrintInvoice(invoice);
        embedJasperReport(jasperPrint);
    }

    private void embedJasperReport(JasperPrint jasperPrint) {
        if(jasperPrint == null) {
            viewInvoice.getChildren().clear();
            return;
        }
        new Thread(()->{
            JRViewer viewer = new JRViewer(jasperPrint);
            viewer.setZoomRatio(.5F);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
            SwingNode swingNode = new SwingNode();
            swingNode.setContent(viewer);
            Platform.runLater(() -> {
                if(viewInvoice.getChildren().isEmpty()) {
                    viewInvoice.getChildren().add(0, swingNode);
                    viewInvoice.requestLayout();
                    return;
                }
                viewInvoice.getChildren().set(0, swingNode);
                viewInvoice.requestLayout();
            });
        }).start();
    }

    private void setupBtnExportInvoice() {
        btnExportInvoice.setOnAction(actionEvent -> {
            exportInvoice();
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
}

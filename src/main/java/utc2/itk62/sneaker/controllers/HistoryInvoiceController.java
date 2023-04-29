package utc2.itk62.sneaker.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import utc2.itk62.sneaker.models.*;
import utc2.itk62.sneaker.services.InvoiceDetailsService;
import utc2.itk62.sneaker.services.InvoiceService;
import utc2.itk62.sneaker.util.FormatDouble;

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

    private ObservableList<Invoice> listInvoice;

    public void initialize() {
        reloadTableView();
        setUpTableListInvoice();
    }

    private void reloadTableView() {
        listInvoice = FXCollections.observableArrayList(invoicesService.getAllInvoice());
        for (Invoice item : listInvoice) {
            item.setListInvoiceDetails(invoicesDetailsService.getInvoiceDetailByIdInvoice(item.getId()));
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

        // update table invoice details
        updateInvoiceDetailsCurrentRowInvoice();
    }

    private void updateInvoiceDetailsCurrentRowInvoice() {
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
        tableListInvoiceDetail.setItems(FXCollections.observableArrayList(tableListInvoice.getSelectionModel().getSelectedItem().getListInvoiceDetails()));
    }

    private void setUpTableListInvoice() {
        tableListInvoice.setOnMouseClicked(mouseEvent -> {
            updateInvoiceDetailsCurrentRowInvoice();
        });
        tableListInvoice.setOnKeyPressed(keyEvent -> {
            updateInvoiceDetailsCurrentRowInvoice();
        });
    }

}

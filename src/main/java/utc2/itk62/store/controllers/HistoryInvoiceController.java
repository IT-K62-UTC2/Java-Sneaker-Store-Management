package utc2.itk62.store.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.swing.JRViewerPanel;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;
import utc2.itk62.store.connection.ConnectionUtil;
import utc2.itk62.store.models.*;
import utc2.itk62.store.services.InvoiceDetailsService;
import utc2.itk62.store.services.InvoiceService;
import utc2.itk62.store.util.FormatDateTime;
import utc2.itk62.store.util.FormatDouble;

import javax.swing.*;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private ObservableList<Invoice> listInvoice;

    public void initialize() {
        reloadTableView();
        setUpTableListInvoice();
        setupBtnExportInvoice();
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
        loadInvoice();
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
            loadInvoice();
            updateInvoiceDetailsCurrentRowInvoice();
        });
        tableListInvoice.setOnKeyPressed(keyEvent -> {
            loadInvoice();
            updateInvoiceDetailsCurrentRowInvoice();
        });
    }

    public void exportInvoice() {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            Invoice currentInvoice = tableListInvoice.getSelectionModel().getSelectedItem();
            params.put("idInvoice", currentInvoice.getId());
            params.put("staff", currentInvoice.getStaff().getFullName());
            params.put("customer", currentInvoice.getCustomer().getFullName());
            params.put("deliveryAddress", currentInvoice.getDeliveryAddress());
            params.put("deliveryPhoneNumber", currentInvoice.getDeliveryPhoneNumber());
            params.put("tax", "0");
            params.put("discount", "-0");
            params.put("subTotal", FormatDouble.toString(currentInvoice.getMoneyTotal()));
            params.put("totalDue", FormatDouble.toString(currentInvoice.getMoneyTotal()));
            params.put("date", FormatDateTime.dateToString(currentInvoice.getCreatedAt()));
            params.put("time", FormatDateTime.timeToString(currentInvoice.getCreatedAt()));
            List<InvoiceDetail> invoiceDetailList = currentInvoice.getListInvoiceDetails();
            File fileTemp = new File("src/main/resources/utc2/itk62/store/report/invoice.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(fileTemp.getAbsolutePath());
            JasperPrint jasperPrint;
            if(invoiceDetailList.isEmpty()) {
                jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            } else {
                JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(invoiceDetailList);
                jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrBeanCollectionDataSource);
            };
            JRViewer viewer = new JRViewer(jasperPrint);
            viewer.setZoomRatio(.5F);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
            SwingNode swingNode = new SwingNode();
            swingNode.setContent(viewer);
            viewInvoice.getChildren().add(swingNode);
            viewInvoice.requestLayout();
            showJasperReport(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } finally{
            ConnectionUtil.closeConnection();
        }
    }

    public void loadInvoice() {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            Invoice currentInvoice = tableListInvoice.getSelectionModel().getSelectedItem();
            params.put("idInvoice", currentInvoice.getId());
            params.put("staff", currentInvoice.getStaff().getFullName());
            params.put("customer", currentInvoice.getCustomer().getFullName());
            params.put("deliveryAddress", currentInvoice.getDeliveryAddress());
            params.put("deliveryPhoneNumber", currentInvoice.getDeliveryPhoneNumber());
            params.put("tax", "0");
            params.put("discount", "-0");
            params.put("subTotal", FormatDouble.toString(currentInvoice.getMoneyTotal()));
            params.put("totalDue", FormatDouble.toString(currentInvoice.getMoneyTotal()));
            params.put("date", FormatDateTime.dateToString(currentInvoice.getCreatedAt()));
            params.put("time", FormatDateTime.timeToString(currentInvoice.getCreatedAt()));
            List<InvoiceDetail> invoiceDetailList = currentInvoice.getListInvoiceDetails();
            File fileTemp = new File("src/main/resources/utc2/itk62/store/report/invoice.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(fileTemp.getAbsolutePath());
            JasperPrint jasperPrint;
            if(invoiceDetailList.isEmpty()) {
                jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            } else {
                JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(invoiceDetailList);
                jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrBeanCollectionDataSource);
            }

            JRViewer viewer = new JRViewer(jasperPrint);
            viewer.setZoomRatio(.5F);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
            SwingNode swingNode = new SwingNode();
            swingNode.setContent(viewer);
            viewInvoice.getChildren().add(swingNode);
            viewInvoice.requestLayout();
        } catch (JRException e) {
            throw new RuntimeException(e);
        } finally{
            ConnectionUtil.closeConnection();
        }
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

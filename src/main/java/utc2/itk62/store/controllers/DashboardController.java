package utc2.itk62.store.controllers;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import utc2.itk62.store.common.FromAndToDate;
import utc2.itk62.store.models.Invoice;
import utc2.itk62.store.models.InvoiceDetail;
import utc2.itk62.store.services.InvoiceDetailsService;
import utc2.itk62.store.services.InvoiceService;
import utc2.itk62.store.util.FormatDouble;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardController {
    private static final InvoiceService invoicesService = new InvoiceService();
    public Label totalProductSell;
    public Label income;
    public LineChart<?, ?> lineChart;
    public Label qtyProductToday;
    public Label incomeToday;

    private List<Invoice>  invoiceList = new ArrayList<>();

    public void initialize() {
        initLineChart();
    }

    private void initLineChart() {
        invoiceList = invoicesService.getAllInvoice(new FromAndToDate());
        XYChart.Series series = new XYChart.Series<>();
        Map<Integer, Integer> mapDayAndQuantitySell = new HashMap<Integer, Integer>();
        double totalIncome = 0;
        int qtyProduct = 0;
        int qtyToday = 0;
        double totalIncomeToday =0;
        LocalDate toady = LocalDate.now();
        for(int i = 0; i < invoiceList.size(); i++) {
            Invoice invoice = invoiceList.get(i);
            totalIncome += invoice.getMoneyTotal();
            qtyProduct += invoice.getTotalQuantity();
            int dayCreatedInvoice = invoice.getCreatedAt().toLocalDateTime().getDayOfMonth();//1
            if(toady.getDayOfMonth() == dayCreatedInvoice) {
                qtyToday += invoice.getTotalQuantity();
                totalIncomeToday =invoice.getMoneyTotal();
            }
            if(mapDayAndQuantitySell.get(dayCreatedInvoice) == null) {
                mapDayAndQuantitySell.put(dayCreatedInvoice, invoice.getTotalQuantity());
            } else{
                mapDayAndQuantitySell.replace(dayCreatedInvoice, mapDayAndQuantitySell.get(dayCreatedInvoice)+ invoice.getTotalQuantity());
            }
        }
        for(int i = 1 ; i <=31; i++) {
            if(mapDayAndQuantitySell.get(i) != null) {
                series.getData().add(new XYChart.Data<>(String.valueOf(i), mapDayAndQuantitySell.get(i)));
                continue;
            }
            series.getData().add(new XYChart.Data<>(String.valueOf(i),0));
        }
        lineChart.getData().addAll(series);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        series.getNode().setStyle("-fx-stroke: #FFD6DC");

        income.setText(FormatDouble.toString(totalIncome));
        totalProductSell.setText(String.valueOf(qtyProduct));

        qtyProductToday.setText(String.valueOf(qtyToday));
        incomeToday.setText(FormatDouble.toString(totalIncomeToday));
    }
}

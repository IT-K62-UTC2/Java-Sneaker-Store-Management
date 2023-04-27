package utc2.itk62.sneaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import utc2.itk62.sneaker.util.HashedPassword;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        try {
//            JasperReport jasperReport = JasperCompileManager.compileReport(String.valueOf(Main.class.getResource("report/import-goods.jrxml")));
//            // Parameters for report
//            Map<String, Object> parameters = new HashMap<>();
//            // Fill report
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
//            // Export report to PDF
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "import-goods.pdf");
//
//        } catch (JRException e) {
//            throw new RuntimeException(e);
//        }
        launch();
        System.out.println(HashedPassword.hashPassword("admin"));
    }

}
package utc2.itk62.sneaker.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import utc2.itk62.sneaker.Main;
import utc2.itk62.sneaker.models.Product;
import utc2.itk62.sneaker.services.ProductService;

import java.io.IOException;

public class SaleController {
    private static final ProductService productService = new ProductService();

    public GridPane menuProduct;
    public TableView<Product> tableViewOrder;
    public TableColumn<Product, String> nameProductOrder;
    public TableColumn<Product, Integer> quantityProductOrder;
    public TableColumn<Product, Double> priceProductOrder;

    private ObservableList<Product> listProducts = FXCollections.observableArrayList(productService.getAllProduct());
    public ObservableList<Product> listProductsOrder;

    public void initialize() {
        menuDisplayProduct();
    }


    public void menuDisplayProduct() {
        int col = 0;
        int row =0;
        menuProduct.getRowConstraints().clear();
        menuProduct.getColumnConstraints().clear();
        for (int i = 0; i < listProducts.size(); i++) {
            try {
                FXMLLoader load = new   FXMLLoader(Main.class.getResource("views/card-product.fxml"));
                Pane pane = load.load();
                CardProductController cardProductController = load.getController();
                cardProductController.setData(listProducts.get(i));

                if(col == 3) {
                    col= 0;
                    row+=1;
                }
                menuProduct.add(pane, col++, row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

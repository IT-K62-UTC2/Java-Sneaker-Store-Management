package utc2.itk62.sneaker.controllers;

import javafx.scene.control.*;
import utc2.itk62.sneaker.models.Category;
import utc2.itk62.sneaker.models.Product;
import utc2.itk62.sneaker.models.Supplier;

public class ProductController {
    public TableView<Product> tableListProduct;
    public TextField id;
    public TextField name;
    public TextField size;
    public TextField quantity;
    public ComboBox<Supplier> supplier;
    public TextField email;
    public TextArea desc;
    public ComboBox<Category> category;
    public Button btnAddImage;
    public ComboBox<String> keySearch;
    public TextField valueSearch;
    public DatePicker toDate;
    public DatePicker fromDate;
}

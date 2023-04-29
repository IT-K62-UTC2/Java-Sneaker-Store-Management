package utc2.itk62.sneaker.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utc2.itk62.sneaker.models.InvoiceDetail;
import utc2.itk62.sneaker.models.Product;
import utc2.itk62.sneaker.util.FormatDouble;

public class CardProductController {
    public Button btnAdd;
    public Label priceProduct;
    public Label sizeProduct;
    public ImageView imageProduct;
    public Label nameProduct;
    public Spinner<Integer> cashQuantity;
    public Label quantity;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        priceProduct.setText(FormatDouble.toString(product.getPrice()));
        sizeProduct.setText(String.valueOf(product.getSize()));
        nameProduct.setText(String.valueOf(product.getName()));
        imageProduct.setImage(new Image(product.getAvatar()));
        quantity.setText(String.valueOf(product.getQuantity()));
        cashQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, product.getQuantity(), 0));
    }

    public void initialize() {}

    public void setBtnAdd(SaleController saleController) {
        btnAdd.setOnAction(actionEvent -> {
            if(cashQuantity.getValue() <=0) {
                return;
            }

            for(InvoiceDetail item : saleController.listOrders) {
                if(item.getProduct().getId() == product.getId()) {
                    item.setProductQuantity(item.getProductQuantity() + cashQuantity.getValue());
                    item.setMoneyTotal(item.getMoneyTotal() + product.getPrice() * cashQuantity.getValue());
                    quantity.setText(String.valueOf(Integer.valueOf(quantity.getText()) - cashQuantity.getValue()));
                    cashQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.valueOf(quantity.getText()), 0));
                    saleController.tableViewOrder.refresh();
                    return;
                }
            }

            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setProduct(product);

            invoiceDetail.setMoneyTotal(product.getPrice() * cashQuantity.getValue());
            invoiceDetail.setProductQuantity(cashQuantity.getValue());

            quantity.setText(String.valueOf(Integer.valueOf(quantity.getText()) - cashQuantity.getValue()));
            cashQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.valueOf(quantity.getText()), 0));
            saleController.listOrders.add(invoiceDetail);

        });
    }

}

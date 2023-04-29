package utc2.itk62.sneaker.services;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.models.Product;
import utc2.itk62.sneaker.repositories.ProductRepo;

import java.util.List;

public class ProductService {
    private static final ProductRepo productRepo = new ProductRepo();

    public List<Product> getProductsByIdCategory(int idCategory) {
        return productRepo.getProductListByIdCategory(idCategory);
    }

    public boolean updateProduct(Product product) {
        if(productRepo.updateProduct(product) <=0) {
            return false;
        }
        return true;
    }

    public boolean updateQuantityProduct(int quantity, int idProduct) {
        Product product = new Product();
        product.setQuantity(quantity);
        product.setId(idProduct);
        if(productRepo.updateQuantityProduct(product) <= 0) {
            return  false;
        }
        return true;
    }

    public List<Product> getAllProduct() {
        return productRepo.getAllProducts(new Paging(0,0));
    }
}

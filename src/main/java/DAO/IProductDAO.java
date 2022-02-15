package DAO;

import model.Product;

import java.util.ArrayList;
import java.util.Properties;

public interface IProductDAO {
    ArrayList<Product> getAllProduct();

    void addProduct(Product product);

    boolean deleteProduct(int id);

    void updateProduct(Product product, int id);

    Product getProduct(String name);
}

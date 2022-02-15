package DAO;

import connection.MyConnection;
import jdk.nashorn.internal.ir.SplitReturn;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ProductDAO implements IProductDAO{
    private final MyConnection myConnection = new MyConnection();

    private static final String SELECT_ALL_PRODUCTS = "select id_product, name_product, price_product, quantity_product, color_product, description, name_category from product join category on product.id_category = category.id_category;";
    private static final String INSERT_PRODUCT = "INSERT INTO `exam_module3`.`product` (`name_product`, `price_product`, `quantity_product`, `color_product`, `description`, `id_category`) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_PRODUCT = "DELETE FROM `exam_module3`.`product` WHERE (`id_product` = ?);";
    private static final String UPDATE_PRODUCT = "UPDATE `exam_module3`.`product` SET `name_product` = ?, `price_product` = ?, `color_product` = ?, `description` = ?, `id_category` = ? WHERE (`id_product` = ?);";
    private static final String SELECT_PRODUCT_BY_NAME = "select id_product, name_product, price_product, quantity_product, color_product, description, name_category from product join\n" +
            "category on product.id_category = category.id_category where name_product = %?%;";



    @Override
    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                int quantity = resultSet.getInt(4);
                String color = resultSet.getString(5);
                String description = resultSet.getString(6);
                String category = resultSet.getString(7);
                products.add(new Product(id, name, price,quantity, color, description, category ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void addProduct(Product product) {
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getCategory());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        boolean rowDeleted = false;
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public void updateProduct(Product product, int id) {
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5, product.getDescription());
//            preparedStatement.setString(6, product.get);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProduct(String nameFind) {
        Product product = null;
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME);
            preparedStatement.setString(1, nameFind);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                int quantity = resultSet.getInt(4);
                String color = resultSet.getString(5);
                String description = resultSet.getString(6);
                String category = resultSet.getString(7);
                product = (new Product(id, name, price,quantity, color, description, category ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}

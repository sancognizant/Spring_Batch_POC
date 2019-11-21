package com.example.productbatch.Mapper;

import com.example.productbatch.Model.Product;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

// this mapper maps the incoming database values to the model
public class ProductMapper implements RowMapper<Product> {

    private String PRODUCT_NAME_COLUMN = "productName";
    private String QUANTITY_COLUMN = "quantity";

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();

        product.setProductName(resultSet.getString(PRODUCT_NAME_COLUMN));
        product.setQuantity(resultSet.getInt(QUANTITY_COLUMN));

        return product;
    }
}

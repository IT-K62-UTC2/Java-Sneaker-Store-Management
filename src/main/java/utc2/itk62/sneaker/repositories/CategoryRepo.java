package utc2.itk62.sneaker.repositories;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.connection.ConnectionUtil;
import utc2.itk62.sneaker.models.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepo {

    public CategoryRepo() {
    }

    public List<Category> getAllCategories(Paging paging) {
        paging.checkPageLimit();
        List<Category> categories = new ArrayList<Category>();
        String query = "SELECT * FROM category WHERE status = 1 LIMIT ? OFFSET ? ";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1,paging.getLimit());
            ptmt.setInt(2,paging.getOffset());
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setStatus(rs.getInt("status"));
                category.setCreatedAt(rs.getTimestamp("created_at"));
                category.setUpdatedAt(rs.getTimestamp("updated_at"));
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }


}

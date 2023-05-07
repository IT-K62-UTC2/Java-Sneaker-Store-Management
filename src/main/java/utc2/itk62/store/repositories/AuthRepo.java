package utc2.itk62.store.repositories;

import utc2.itk62.store.connection.ConnectionUtil;
import utc2.itk62.store.models.Auth;
import utc2.itk62.store.models.Menu;
import utc2.itk62.store.models.Position;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthRepo {
    public List<Auth> getAllAuthByPosition(Position position) {
        String query = "SELECT * FROM auth " +
                " INNER JOIN menu ON menu.id = auth.id_menu WHERE id_position = ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1, position.getId());
            ResultSet rs = ptmt.executeQuery();
            List<Auth> authList = new ArrayList<Auth>();
            while (rs.next()) {
                Auth auth = new Auth();
                Menu menu = new Menu();
                menu.setName(rs.getString("menu.name"));
                menu.setPath(rs.getString("menu.path"));
                auth.setMenu(menu);
                auth.setPosition(position);
                authList.add(auth);
            }
            return authList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }
}

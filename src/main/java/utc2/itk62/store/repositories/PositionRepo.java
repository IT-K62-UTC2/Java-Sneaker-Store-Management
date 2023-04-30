package utc2.itk62.store.repositories;

import utc2.itk62.store.connection.ConnectionUtil;
import utc2.itk62.store.models.Position;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionRepo {
    public PositionRepo() {
    }

    public List<Position> getAllPosition() {
        List<Position> positionList = new ArrayList<Position>();
        String query = "SELECT * FROM position WHERE status = 1";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Position position = new Position();
                position.setId(rs.getInt("id"));
                position.setName(rs.getString("name"));
                position.setStatus(rs.getInt("status"));
                position.setCreatedAt(rs.getTimestamp("created_at"));
                position.setUpdatedAt(rs.getTimestamp("updated_at"));
                positionList.add(position);
            }
            return positionList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public Position getPosition(int id) {
        List<Position> positionList = new ArrayList<Position>();
        String query = "SELECT * FROM position WHERE status = 1 AND id = ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            Position position = null;
            while (rs.next()) {
                position = new Position();
                position.setId(rs.getInt("id"));
                position.setName(rs.getString("name"));
                position.setStatus(rs.getInt("status"));
                position.setCreatedAt(rs.getTimestamp("created_at"));
                position.setUpdatedAt(rs.getTimestamp("updated_at"));
                positionList.add(position);
            }
            return position;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }
}

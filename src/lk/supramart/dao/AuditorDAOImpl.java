package lk.supramart.dao;

import lk.supramart.auditor.Auditor;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shenu
 */
public class AuditorDAOImpl implements AuditorDAO {

    private final Connection connection;

    public AuditorDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Auditor getAuditorById(int id) {
        String sql = "SELECT * FROM auditor WHERE auditor_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractAuditor(rs);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public List<Auditor> getAllAuditors() {
        List<Auditor> auditors = new ArrayList<>();
        String sql = "SELECT * FROM auditor";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                auditors.add(extractAuditor(rs));
            }
        } catch (SQLException e) {
        }
        return auditors;
    }

    @Override
    public boolean addAuditor(Auditor auditor) {
        String sql = "INSERT INTO auditor (auditor_name, auditor_email, auditor_password, auditor_role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, auditor.getName());
            ps.setString(2, auditor.getEmail());
            ps.setString(3, auditor.getPassword());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean updateAuditor(Auditor auditor) {
        String sql = "UPDATE auditor SET auditor_name=?, auditor_email=?, auditor_password=?, auditor_role=? WHERE auditor_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, auditor.getName());
            ps.setString(2, auditor.getEmail());
            ps.setString(3, auditor.getPassword());
            ps.setInt(4, auditor.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean deleteAuditor(int id) {
        String sql = "DELETE FROM auditor WHERE auditor_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        return false;
    }

    private Auditor extractAuditor(ResultSet rs) throws SQLException {
        return new Auditor(
                rs.getInt("auditor_id"),
                rs.getString("auditor_name"),
                rs.getString("auditor_email"),
                rs.getString("auditor_password")
        );
    }
}

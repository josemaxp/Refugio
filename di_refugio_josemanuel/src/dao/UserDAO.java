package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConnectionManager;

public class UserDAO {

    /**
     * Method to get the password of a specific user
     */
    public String getPassword(String id_user) {
        PreparedStatement preparedStatement = null;
        String checkUser = "";

        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            String query = "SELECT password FROM usuario WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, id_user);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                checkUser = result.getString(1);
            }
        } catch (SQLException e) {
            System.err.println("Error SQL" + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.err.println("Error SQL" + e.getMessage());
            }
        }
        return checkUser;
    }

    /**
     * Method to check if a user exist or not
     */
    public boolean getExistingUser(String user) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        boolean checkID = false;

        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            ps = connection.prepareStatement("select * from usuario where id_user = ?");
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next()) {
                checkID = true;
            }
        } catch (SQLException e) {
            System.err.println("Error SQL" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
        return checkID;
    }

    /**
     * Method to insert a new user
     */
    public void insertUser(String user_id, String password) {
        PreparedStatement ps = null;

        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            ps = connection.prepareStatement("insert into usuario values (?,?)");
            ps.setString(1, user_id);
            ps.setString(2, password);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error SQL" + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
    }
}

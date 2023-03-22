/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    private static final String LOGIN = "SELECT userId, role FROM tblUser WHERE username = ? AND password = ?";
    private static final String SEARCH = "SELECT userID, username, password, role FROM tblUser WHERE username like ?";
    private static final String DELETE = "DELETE FROM tblUser WHERE userID = ?";
    private static final String UPDATE = "UPDATE tblUser SET username=?, password=? WHERE userID = ?";
    private static final String CHANGE_ROLE = "UPDATE tblUser SET role=? WHERE userID = ?";
    private static final String CHECK_DUPLICATE = "SELECT userID FROM tblUser WHERE userID = ?";
    private static final String INSERT = "INSERT INTO tblUser(userID, username, password, role) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_USERS = "SELECT userID, username, password, role FROM tblUser";

    public AccountEntity checkLogin(String username, String password) throws SQLException {
        AccountEntity user = null;
        try (
                Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(LOGIN);
                ResultSet rs = ptm.executeQuery();) {
            if (conn != null) {

                ptm.setString(1, username);
                ptm.setString(2, password);

                if (rs.next()) {
                    String userId = rs.getString("username");
                    String role = rs.getString("role");
                    user = new AccountEntity(userId, username, password, role);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    
    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        try (
                Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ) {
            if (conn != null) {
                ptm.setString(1, userID);
                ResultSet rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                    rs.close();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    public List<AccountEntity> search(String searcher) throws SQLException {
        List<AccountEntity> list = new ArrayList<>();
        try (
                Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(SEARCH);
                ResultSet rs = ptm.executeQuery();) {
            if (conn != null) {
                ptm.setString(1, "%" + searcher + "%");
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String password = rs.getString("password");
                    String name = rs.getString("username");
                    String roleID = rs.getString("role");
                    list.add(new AccountEntity(userID, name, password, roleID));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<AccountEntity> getAllUsers() throws SQLException {
        List<AccountEntity> list = new ArrayList<>();
        try (
                Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(GET_ALL_USERS);
                ResultSet rs = ptm.executeQuery();) {
            if (conn != null) {
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String password = rs.getString("password");
                    String name = rs.getString("username");
                    String roleID = rs.getString("role");
                    list.add(new AccountEntity(userID, name, password, roleID));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public boolean delete(String userID) throws SQLException {
        boolean checkDelete = false;
        try (
                Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(DELETE);) {
            
            if (conn != null) {
                ptm.setString(1, userID);
                checkDelete = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return checkDelete;
    }

    public boolean updateAccountInfo(AccountEntity user) throws SQLException {
        boolean checkUpdate = false;
        try (
                Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(UPDATE);) {
            if (conn != null) {
                ptm.setString(1, user.getUsername());
                ptm.setString(2, user.getPassword());
                ptm.setString(3, user.getUserId());
                checkUpdate = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return checkUpdate;
    }
    
    public boolean updateRole(AccountEntity user) throws SQLException {
        boolean checkUpdate = false;
        try (
                Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(CHANGE_ROLE);) {
            if (conn != null) {
                ptm.setString(1, user.getRole());
                ptm.setString(3, user.getUserId());
                checkUpdate = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return checkUpdate;
    }

    public boolean insert(AccountEntity user) throws SQLException {
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getUserId());
                ptm.setString(2, user.getUsername());
                ptm.setString(3, user.getRole());
                ptm.setString(4, user.getPassword());
                checkInsert = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkInsert;
    }
}

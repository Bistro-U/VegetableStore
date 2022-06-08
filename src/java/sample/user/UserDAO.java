package sample.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtils;

public class UserDAO {

    private static final String LOGIN = "SELECT fullName, roleID, address, birthday, phone, email, status FROM tblUsers WHERE userID=? AND password=?";
    private static final String SEARCH = "SELECT userID, fullName, roleID, address, birthday, phone, email, status FROM tblUsers WHERE fullName like ?";
    private static final String SEARCH_ALL = "SELECT userID, fullName, roleID, address, birthday, phone, email, status FROM tblUsers";
    private static final String DELETE = "UPDATE tblUsers SET status=? WHERE userID=?";
    private static final String UPDATE = "UPDATE tblUsers SET fullName =?, roleID=?, address=?, birthday=?, phone=?, email=? WHERE userID=?";
    private static final String CHECK_DUPLICATE = "SELECT fullName FROM tblUsers WHERE userID=?";
    private static final String CREATE = "INSERT INTO tblUsers(userID,fullName,password,roleID,address,birthday,phone,email,status) VALUES (?,?,?,?,?,?,?,?,?)";

    public boolean create(UserDTO user) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getPassword());
                ptm.setString(4, user.getRoleID());
                ptm.setString(5, user.getAddress());
                ptm.setString(6, user.getBirthday());
                ptm.setString(7, user.getPhone());
                ptm.setString(8, user.getEmail());
                ptm.setBoolean(9, true);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if (status) {
                        String fullName = rs.getString("fullName");
                        String roleID = rs.getString("roleID");
                        String address = rs.getString("address");
                        String birthday = rs.getString("birthday");
                        String phone = rs.getString("phone");
                        String email = rs.getString("email");
                        user = new UserDTO(userID, fullName, "", roleID, address, birthday, phone, email, status);
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (search != "") {
                    ptm = conn.prepareStatement(SEARCH);
                    ptm.setString(1, "%" + search + "%");
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        boolean status = rs.getBoolean("status");
                        if (status) {
                            String userID = rs.getString("userID");
                            String fullName = rs.getString("fullName");
                            String roleID = rs.getString("roleID");
                            String address = rs.getString("address");
                            String birthday = rs.getString("birthday");
                            String phone = rs.getString("phone");
                            String email = rs.getString("email");
                            listUser.add(new UserDTO(userID, fullName, "******", roleID, address, birthday, phone, email, status));
                        }
                    }
                } else {
                    ptm = conn.prepareStatement(SEARCH_ALL);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        boolean status = rs.getBoolean("status");
                        if (status) {
                            String userID = rs.getString("userID");
                            String fullName = rs.getString("fullName");
                            String roleID = rs.getString("roleID");
                            String address = rs.getString("address");
                            String birthday = rs.getString("birthday");
                            String phone = rs.getString("phone");
                            String email = rs.getString("email");
                            listUser.add(new UserDTO(userID, fullName, "******", roleID, address, birthday, phone, email, status));
                        }
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
    }

    public boolean deleteUser(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setBoolean(1, false);
                ptm.setString(2, userID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getRoleID());
                ptm.setString(3, user.getAddress());
                ptm.setString(4, user.getBirthday());
                ptm.setString(5, user.getPhone());
                ptm.setString(6, user.getEmail());
                ptm.setString(7, user.getUserID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}

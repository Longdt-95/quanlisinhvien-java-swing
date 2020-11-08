/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository.IMPL;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Repository.UserRepository;
import controller.ConnectDB;
import model.Users;

/**
 *
 * @author Admin
 */
public class UserRepositoryIMPL implements UserRepository {

    @Override
    public boolean findByStudentId(String studentId) {
        boolean flag = false;
        String sql = "SELECT * FROM users WHERE studentid = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, studentId);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BatchRepositoryIMPL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return flag;
    }

    @Override
    public int update(Users users) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sqlUpdateFromUser = "UPDATE users SET passwords = ? ,studentid = ?  WHERE username = ?";
            conn = controller.ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sqlUpdateFromUser);
            preparedStatement.setString(1, md5(users.getPassword()));
            preparedStatement.setString(2, users.getStudentid());
            preparedStatement.setString(3, users.getUsername());
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BatchRepositoryIMPL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public Users getById(String batchId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Users> getAll() {
        List<Users> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM users";
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Users users;
                users = new Users(rs.getString("username"), rs.getString("passwords"), rs.getString("studentid"));
                result.add(users);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BatchRepositoryIMPL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public long getLastId() {
        long useId = -1;
        String sql = "SELECT TOP 1 * FROM users ORDER BY id DESC";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                useId = rs.getLong("id");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BatchRepositoryIMPL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return useId;
    }

    @Override
    public int saveUser(Users users) {
        int result = 0;
        String sql = "INSERT INTO users(username,passwords,studentid) values(?,?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, users.getUsername());
            String passwork = md5(users.getPassword());
            preparedStatement.setString(2, passwork);
            preparedStatement.setString(3, users.getStudentid());
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BatchRepositoryIMPL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    private String md5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return  sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    @Override
    public int delUser(String userName) {
        int result = 0;
        String sql = "DELETE FROM users WHERE username = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BatchRepositoryIMPL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean isExist(String userName) {
        boolean flag = false;
        String sql = "SELECT * FROM users WHERE username = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BatchRepositoryIMPL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return flag;
    }

}

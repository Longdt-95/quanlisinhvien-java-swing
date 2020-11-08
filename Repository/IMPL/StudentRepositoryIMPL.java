/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository.IMPL;

import Repository.StudentRepository;
import controller.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;
import util.BuildSQL;

/**
 *
 * @author Admin
 */
public class StudentRepositoryIMPL implements StudentRepository {

    @Override
    public boolean findByBatchId(String batchId) {
        boolean flag = false;
        String sql = "SELECT * FROM student WHERE batchid = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, batchId);
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
    public int update(Student student) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sqlUpdateFromStudent = "UPDATE student SET studentname = ? ,studentgender = ? ,batchid = ?  WHERE studentid = ?";
            conn = controller.ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sqlUpdateFromStudent);
            BuildSQL.setParam(student, preparedStatement);
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
    public Student getById(String batchId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Student> getAll() {
        List<Student> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM student";
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Student student = new Student(rs.getString("studentid"), rs.getString("studentname"),
                         rs.getString("studentgender"), rs.getString("batchid"));
                result.add(student);
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
    public String getLastId() {
        String studentID = "";
        String sql = "SELECT TOP 1 * FROM student ORDER BY studentid DESC";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                studentID = rs.getString("studentid");
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
        return studentID;
    }

    @Override
    public int saveStudent(Student student) {
        int result = 0;
        String sql = "INSERT INTO student values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, student.getId());
            preparedStatement.setString(2, student.getStudentname());
            preparedStatement.setString(3, student.getStudentgender());
            preparedStatement.setString(4, student.getBatchid());
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
    public int delStudent(String studentId) {
        int result = 0;
        String sql = "DELETE FROM student WHERE studentid = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, studentId);
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

}

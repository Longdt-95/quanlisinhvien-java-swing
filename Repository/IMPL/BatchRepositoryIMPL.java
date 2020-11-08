
package Repository.IMPL;

import Repository.BatchRepository;
import controller.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Batch;
import util.BuildSQL;

/**
 *
 * @author Admin
 */
public class BatchRepositoryIMPL implements BatchRepository {

    @Override
    public int update(Batch batch) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sqlUpdateFromBatch = BuildSQL.buildSqlUpdate(batch);
            conn = controller.ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sqlUpdateFromBatch);
            BuildSQL.setParam(batch, preparedStatement);
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(BatchRepositoryIMPL.class.getName()).log(Level.SEVERE, null, ex);
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
    public Batch getById(String batchId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Batch> getAll() {
        List<Batch> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM batch";
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Batch batch = new Batch(rs.getString("batchid"), rs.getString("batchname"));
                result.add(batch);
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
        String batchId = "";
        String sql = "SELECT TOP 1 * FROM batch ORDER BY batchid DESC";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                batchId = rs.getString("batchid");
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
        return batchId;
    }

    @Override
    public int saveBatch(Batch batch) {
        int result = 0;
        String sql = "INSERT INTO batch values(?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, batch.getId());
            preparedStatement.setString(2, batch.getBatchName());
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
    public int delBatch(String batchId) {
        int result = 0;
        String sql = "DELETE FROM batch WHERE batchid = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectDB.connectSQLServer();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, batchId);
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

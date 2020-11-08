/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.util.List;
import model.Batch;

/**
 *
 * @author Admin
 */
public interface BatchRepository {
    int update(Batch batch);
    Batch getById(String batchId);
    List<Batch> getAll();
    String getLastId();
    int saveBatch(Batch batch);
    int delBatch(String batchId);
}

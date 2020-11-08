/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;

import Repository.BatchRepository;
import Repository.IMPL.BatchRepositoryIMPL;
import model.Batch;

/**
 *
 * @author thaond
 */
public class BatchController {
    
    private BatchRepository batchRepository = new BatchRepositoryIMPL();
    public List<Batch> getAll() {
        return batchRepository.getAll();
    }
    
    public String getLastBatchId() {
        return batchRepository.getLastId();
    }
    
    public int addBatch(Batch batch){
        return batchRepository.saveBatch(batch);
    }
    
    public int editBatch(Batch batch) {
        return batchRepository.update(batch);
    };
    
    public int delBatch(String batchId) {
        return batchRepository.delBatch(batchId);
    }
}

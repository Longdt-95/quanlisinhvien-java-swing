/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.util.List;
import model.Student;

/**
 *
 * @author Admin
 */
public interface StudentRepository {
    public boolean findByBatchId(String StudentId);
    int update(Student student);
    Student getById(String batchId);
    List<Student> getAll();
    String getLastId();
    int saveStudent(Student student);
    int delStudent(String studentId);
}

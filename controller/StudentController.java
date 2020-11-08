/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Repository.IMPL.StudentRepositoryIMPL;
import Repository.StudentRepository;
import java.util.List;
import model.Student;

/**
 *
 * @author Admin
 */
public class StudentController {
    
    private StudentRepository studentRepository = new StudentRepositoryIMPL();
    public boolean findByBatchId(String batchId) {
        return studentRepository.findByBatchId(batchId);
    };
    
    public List<Student> getAll() {
        return studentRepository.getAll();
    }
    
    public String getLastStudentId() {
        return studentRepository.getLastId();
    }
    
    public int addStudent(Student student){
        return studentRepository.saveStudent(student);
    }
    
    public int editStudent(Student student) {
        return studentRepository.update(student);
    }
    
    public int delStudent(String studentid) {
        return studentRepository.delStudent(studentid);
    }
}

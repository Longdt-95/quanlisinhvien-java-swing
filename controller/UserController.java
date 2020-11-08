/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;

import Repository.UserRepository;
import Repository.IMPL.UserRepositoryIMPL;
import model.Users;

/**
 *
 * @author Admin
 */
public class UserController {
    
    private UserRepository userRepository = new UserRepositoryIMPL();
    
    public boolean findByStudentId(String studentId) {
        return userRepository.findByStudentId(studentId);
    };
    
    public List<Users> getAll() {
        return userRepository.getAll();
    }
    
    public long getLastUserId() {
        return userRepository.getLastId();
    }
    
    public boolean isExist(String userName) {
        return userRepository.isExist(userName);
    }
    
    public int addUser(Users users){
        return userRepository.saveUser(users);
    }
    
    public int editUser(Users users) {
        return userRepository.update(users);
    }
    
    public int delUser(String userName) {
        return userRepository.delUser(userName);
    }
}

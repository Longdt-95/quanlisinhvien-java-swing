/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.util.List;
import model.Users;

/**
 *
 * @author Admin
 */
public interface UserRepository {
    boolean findByStudentId(String studentId);
    int update(Users ssers);
    Users getById(String batchId);
    List<Users> getAll();
    long getLastId();
    int saveUser(Users users);
    int delUser(String userName);
    public boolean isExist(String userName);
}

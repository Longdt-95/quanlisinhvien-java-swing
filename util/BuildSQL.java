/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class BuildSQL {

    public static <T> String buildSqlUpdate(T t) throws NoSuchFieldException {
        Class<?> GenericClass = t.getClass();
        Field[] fields = GenericClass.getDeclaredFields();
        StringBuilder columnAndValues = new StringBuilder("");
        int i = 0;
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("id")) {
                if (i >= 1) {
                    columnAndValues.append(",");
                }
                columnAndValues.append(field.getName().toLowerCase() + " = ? ");
                i++;
            }
        }
        String sqlUpdate = "UPDATE " + GenericClass.getSimpleName().toLowerCase() + " SET "
                + columnAndValues.toString() + " WHERE batchid = ?";
        return sqlUpdate;
    }

    public static <T> void setParam(T t, PreparedStatement statement) {
        Class<?> GenericClass = t.getClass();
        Field[] fields = GenericClass.getDeclaredFields();
        int index = 1;
        String idValue = "";
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equalsIgnoreCase("id")) {
                    //System.out.println(field.getType().getName());
                    if (field.getType().getName().equals("java.lang.String")) {
                        statement.setString(index, (String) field.get(t));
                    } else if (field.getType().equals("java.lang.Boolean")) {
                        statement.setBoolean(index, (Boolean) field.get(t));
                    }
                    index++;
                } else {
                    idValue = (String) field.get(t);
                }
            } catch (Exception ex) {
                Logger.getLogger(BuildSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            statement.setString(index, idValue);
        } catch (SQLException ex) {
            Logger.getLogger(BuildSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

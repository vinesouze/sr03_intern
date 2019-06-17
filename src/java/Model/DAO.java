/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Junior
 */
public interface DAO<T> {
    public final static String URL = "jdbc:mysql://localhost:3306/sr03_intern";
    public final static String USERNAME = "root";
    public final static String PASSWORD = "";
    public final static String DRIVER = "com.mysql.jdbc.Driver";
    
    Optional<T> find(String email);
    Optional<T> find(int email);
    ArrayList<T> getAll();
    void create(T t);
    void update(T t);
    void delete(T t);
}

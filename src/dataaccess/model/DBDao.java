/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.model;

import java.util.ArrayList;

/**
 *
 * @author 62130500127
 */
public interface DBDao<T> {
    void insert(T obj);
    void delete(T obj);
    void update(T obj);
    T findById(String id);
    ArrayList<T> getAll();
}

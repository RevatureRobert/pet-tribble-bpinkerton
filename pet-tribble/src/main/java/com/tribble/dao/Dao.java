package com.tribble.dao;

import java.util.List;

public interface Dao <T>{
    public boolean create(T t);
    public T get(int id);
    public boolean update(T t);
    public boolean delete(int id);
}

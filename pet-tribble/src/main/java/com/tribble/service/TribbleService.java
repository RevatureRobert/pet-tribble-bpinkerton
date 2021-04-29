package com.tribble.service;

import com.tribble.dao.TribbleDaoImpl;
import com.tribble.model.Tribble;
import com.tribble.util.DAOUtilities;

import java.util.List;

public class TribbleService {
    private final TribbleDaoImpl tribbleDao;

    public TribbleService() {
        this.tribbleDao = DAOUtilities.getTribbleDao();
    }

    public boolean createTribble(Tribble tribble){
        return tribbleDao.create(tribble);
    }
    public Tribble getTribble(int id){
        return tribbleDao.get(id);
    }

    public List<Tribble> getAllTribbles(){
        return tribbleDao.getAll();
    }
    public boolean updateTribble(Tribble tribble){
        return tribbleDao.update(tribble);
    }
    public boolean deleteTribble(int id){
        return tribbleDao.delete(id);
    }
}

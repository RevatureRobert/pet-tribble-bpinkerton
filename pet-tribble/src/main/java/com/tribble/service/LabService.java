package com.tribble.service;

import com.tribble.dao.LabDaoImpl;
import com.tribble.model.Lab;
import com.tribble.model.Tribble;
import com.tribble.util.DAOUtilities;

import java.util.List;

public class LabService {
    private final LabDaoImpl labDao;

    public LabService() {
        this.labDao = DAOUtilities.getLabDao();
    }

    public boolean createLab(Lab lab){
        return labDao.create(lab);
    }
    public Lab getLab(int id){
        return labDao.get(id);
    }
    public List<Lab> getAllLabs(){ return labDao.getAll(); }
    public boolean updateLab(Lab lab){
        return labDao.update(lab);
    }
    public boolean deleteLab(int id){
        return labDao.delete(id);
    }
}

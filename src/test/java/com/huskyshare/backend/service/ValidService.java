package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.ValiDao;
import com.huskyshare.backend.entity.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidService {
    @Autowired
    ValiDao valiDao;

    public String findCodebyEmail(String email){
        if(valiDao.findCode(email)==null) return "";
        return valiDao.findCode(email).getCode();
    }

    public void save(Code code){
        valiDao.save(code);
    }

}

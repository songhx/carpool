package com.carpool.service.impl;

import com.carpool.dao.SysMacroDao;
import com.carpool.entity.SysMacroEntity;
import com.carpool.service.SysMacroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通用字典表Service实现类
 *
 * @author bjsonghongxu
 * @email songhongxucg@gmail.com
 * @date 2017-08-22 11:48:16
 */
@Service("sysMacroService")
public class SysMacroServiceImpl implements SysMacroService {
    @Autowired
    private SysMacroDao sysMacroDao;

    @Override
    public SysMacroEntity queryObject(Long macroId) {
        return sysMacroDao.queryObject(macroId);
    }

    @Override
    public List<SysMacroEntity> queryList(Map<String, Object> map) {
        return sysMacroDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysMacroDao.queryTotal(map);
    }

    @Override
    public int save(SysMacroEntity sysMacro) {
        sysMacro.setGmtCreate(new Date());
        return sysMacroDao.save(sysMacro);
    }

    @Override
    public int update(SysMacroEntity sysMacro) {
        sysMacro.setGmtModified(new Date());
        return sysMacroDao.update(sysMacro);
    }

    @Override
    public int delete(Long macroId) {
        return sysMacroDao.delete(macroId);
    }

    @Override
    public int deleteBatch(Long[] macroIds) {
        return sysMacroDao.deleteBatch(macroIds);
    }

    @Override
    public List<SysMacroEntity> queryMacrosByValue(String value) {
        return sysMacroDao.queryMacrosByValue(value);
    }
}

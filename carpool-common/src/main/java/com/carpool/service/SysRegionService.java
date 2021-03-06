package com.carpool.service;


import com.carpool.entity.SysRegionEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author bjsonghongxu
 * @email songhongxucg@gmail.com
 * @date 2017-11-04 11:19:31
 */
public interface SysRegionService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    SysRegionEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<SysRegionEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param region 实体
     * @return 保存条数
     */
    int save(SysRegionEntity region);

    /**
     * 根据主键更新实体
     *
     * @param region 实体
     * @return 更新条数
     */
    int update(SysRegionEntity region);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);
}

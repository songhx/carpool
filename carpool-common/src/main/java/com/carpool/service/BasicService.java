package com.carpool.service;

/**
 * 如底层没有继承通用Mapper,那么在服务层请勿集成该接口
 */

import java.util.List;

public interface BasicService<T> {
	/**
	 * 查询一个对象
	 * @param record
	 * @return
	 */
    T queryOne(T record);

    /**
     * 查询所有,如果传入null那么查询全部;如传入T对象那么会进行等于匹配
     * @return
     */
    List<T> queryAll(T record);
    
    /**
     * 查询记录数,如果传入null那么查询全部;如传入T对象那么会进行等于匹配
     * @param record
     * @return
     */
    int queryCount(T record);

    /**
     * 通过ID进行查询
     * @param key
     * @return
     */
    T queryByPrimaryKey(Object key);


    /**
     * 选择性添加
     * @param record
     * @return
     */
    int addSelective(T record);

    /**
     * 按对象删除
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 通过ID查询
     * @param key
     * @return
     */
    int deleteByPrimaryKey(Object key);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 选择性修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 模糊查询记录数
     * @param example
     * @return
     */
    int queryCountByExample(T example);

    /**
     * 模糊查询记录,具体实现请参看userServiceImpl
     * @param example
     * @return
     */
    List<T> queryByExample(T example);

}

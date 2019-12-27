package com.myz.java.study.jdbc.dao;

import com.myz.java.study.jdbc.bean.Emp;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;


/**
 * @author xuwt
 * @version V1.0
 * @Title: IEmpDAO.java
 * @Package com.myz.java.study.jdbc.dao
 * @Description: 数据层标准
 * 业务分析:
 * 更新: doCreate() doUpdate() doRemoveBatch()
 * 查询: findById() findByName()findAll() findAllCount()
 * 统计: get()
 * @date 2017年6月5日 下午11:43:52
 */
public interface IEmpDAO {
    /**
     * @param vo
     * @return 数据保存成功, 返回true, 失败返回boolean
     * @throws SQLException
     * @Title: doCreate
     * @Description: 添加
     * 实现数据的增加
     */
    public boolean doCreate(Emp vo) throws SQLException;

    /**
     * @param vo
     * @throws SQLException
     * @Title: doUpdate
     * @Description: 修改
     * 根据id实现全部字段的修改
     * @return: 数据修改成功, 返回true, 失败返回boolean
     */
    public boolean doUpdate(Emp vo) throws SQLException;

    /**
     * @throws SQLException
     * @Title: doRemoveBatch
     * @Description: 数据库的批量删除
     * 所有删除的数据以Set集合保存
     * @param: ids包含删除数据的ID
     * @return: 数据删除成功(数据要删除的个数和数据的个数相同), 返回true, 失败返回boolean
     */
    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException;

    /**
     * @param id
     * @throws SQLException
     * @Title: findById
     * @Description: 查询
     * 根据id查询信息
     * @return: Emp, 如果存在, 返回对象, 不存在, 返回null
     */
    public Emp findById(Integer id) throws SQLException;

    /**
     * @throws SQLException
     * @Title: findByName
     * @Description: 查询
     * 根据name查询信息
     * @param: name
     * @return: Emp, 如果存在, 返回对象, 不存在, 返回false
     */
    public Emp findByName(String name) throws SQLException;

    /**
     * @throws SQLException
     * @Title: findAll
     * @Description: 查询指定数据表全部信息
     * 结果以集合的形式返回
     * @return: 如果有数据, 结果以集合的形式返回对象</ br>
     * 没有数据,那么集合长度为0(size() == 0,不是null)
     */
    public List<Emp> findAll() throws SQLException;

    /**
     * @throws SQLException
     * @Title: findAllSplit
     * @Description: 分页模糊查询
     * @param: currentPage当前页面
     * @param: lineSize每页显示行数
     * @param: column模糊查询列
     * @param: keyWord模糊查询关键字
     * @return: 如果有数据, 结果以集合的形式返回对象</ br>
     * 没有数据,那么集合长度为0(size() == 0,不是null) List<Emp>
     */
    public List<Emp> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws SQLException;

    /**
     * @throws SQLException
     * @Title: getAllCount
     * @Description: 模糊查询数量的统计, 如果没有, 返回0;
     * @param: column模糊查询列
     * @param: keyWord模糊查询关键字
     * @return: Integer
     */
    public Integer getAllCount(String column, String keyWord) throws SQLException;


}

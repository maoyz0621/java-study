package com.myz.java.study.jdbc.service;

import com.myz.java.study.jdbc.bean.Emp;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xuwt
 * @ClassName: IEmpService
 * @Description: 业务层执行标准
 * insert()
 * update()
 * delete()
 * get()
 * list()
 * @date 2017年6月14日 下午1:19:40
 */
public interface IEmpService {
    /**
     * @param vo包含数据的VO对象
     * @return boolean
     * @throws Exception
     * @Title: insert
     * @Description: 实现雇员数据增加的操作
     * 首先判断雇员编号是否存在,调用IEmpDAO.getById();
     * 若不存在,使用雇员的IEmpDAO.doCreate()方法,返回操作的结果
     */
    public boolean insert(Emp vo) throws SQLException;

    /**
     * @param vo
     * @return boolean
     * @throws Exception
     * @Title: update
     * @Description: 实现数据的修改操作, 调用IEmpDAO.doUpDate()
     */
    public boolean update(Emp vo) throws SQLException;

    /**
     * @throws SQLException
     * @Title: delete
     * @Description: 实现数据的删除操作, 调用IEmpDAO.doRemoveBatch()
     * @param: ids
     * @return: boolean
     */
    public boolean delete(Set<Integer> ids) throws SQLException;

    /**
     * @Title: get
     * @Description: 根据ID查询相关信息
     * @param: @param uuid
     * @param: @return
     * @param: @throws SQLException
     * @return: Emp
     * @throws:
     */
    public Emp get(Integer id) throws SQLException;

    /**
     * @Title: list
     * @Description: 查询全部数据
     * @param: @return
     * @param: @throws SQLException
     * @return: List<Emp>
     * @throws:
     */
    public List<Emp> list() throws SQLException;

    /**
     * @throws SQLException
     * @Title: list
     * @Description: TODO
     * @param: currentPage
     * @param: lineSize
     * @param: column
     * @param: keyWord
     * @return: Map<String   ,   Object>
     */
    public Map<String, Object> list(Integer currentPage, Integer lineSize, String column, String keyWord) throws SQLException;


}

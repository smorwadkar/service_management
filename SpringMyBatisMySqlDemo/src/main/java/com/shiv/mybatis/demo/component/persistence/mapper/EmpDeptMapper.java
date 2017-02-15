package com.shiv.mybatis.demo.component.persistence.mapper;

import com.shiv.mybatis.demo.component.persistence.EmpDeptExample;
import com.shiv.mybatis.demo.component.persistence.EmpDeptKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmpDeptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dept
     *
     * @mbggenerated Wed Jan 11 09:43:15 IST 2017
     */
    int countByExample(EmpDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dept
     *
     * @mbggenerated Wed Jan 11 09:43:15 IST 2017
     */
    int deleteByExample(EmpDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dept
     *
     * @mbggenerated Wed Jan 11 09:43:15 IST 2017
     */
    int deleteByPrimaryKey(EmpDeptKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dept
     *
     * @mbggenerated Wed Jan 11 09:43:15 IST 2017
     */
    int insert(EmpDeptKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dept
     *
     * @mbggenerated Wed Jan 11 09:43:15 IST 2017
     */
    int insertSelective(EmpDeptKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dept
     *
     * @mbggenerated Wed Jan 11 09:43:15 IST 2017
     */
    List<EmpDeptKey> selectByExample(EmpDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dept
     *
     * @mbggenerated Wed Jan 11 09:43:15 IST 2017
     */
    int updateByExampleSelective(@Param("record") EmpDeptKey record, @Param("example") EmpDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dept
     *
     * @mbggenerated Wed Jan 11 09:43:15 IST 2017
     */
    int updateByExample(@Param("record") EmpDeptKey record, @Param("example") EmpDeptExample example);
}
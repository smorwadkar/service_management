package com.service.manager.user.persistence.mapper;

import com.service.manager.user.persistence.CategorySubCategoryExample;
import com.service.manager.user.persistence.CategorySubCategoryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategorySubCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category_sub_category
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    int countByExample(CategorySubCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category_sub_category
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    int deleteByExample(CategorySubCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category_sub_category
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    int deleteByPrimaryKey(CategorySubCategoryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category_sub_category
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    int insert(CategorySubCategoryKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category_sub_category
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    int insertSelective(CategorySubCategoryKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category_sub_category
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    List<CategorySubCategoryKey> selectByExample(CategorySubCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category_sub_category
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    int updateByExampleSelective(@Param("record") CategorySubCategoryKey record, @Param("example") CategorySubCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category_sub_category
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    int updateByExample(@Param("record") CategorySubCategoryKey record, @Param("example") CategorySubCategoryExample example);
}
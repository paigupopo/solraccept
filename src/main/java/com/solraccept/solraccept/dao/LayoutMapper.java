package com.solraccept.solraccept.dao;

import com.solraccept.solraccept.bean.solr.Layout;
import com.solraccept.solraccept.bean.solr.LayoutExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LayoutMapper {
    long countByExample(LayoutExample example);

    int deleteByExample(LayoutExample example);

    int deleteByPrimaryKey(Long plid);

    int insert(Layout record);

    int insertSelective(Layout record);

    List<Layout> selectByExample(LayoutExample example);

    Layout selectByPrimaryKey(Long plid);

    int updateByExampleSelective(@Param("record") Layout record, @Param("example") LayoutExample example);

    int updateByExample(@Param("record") Layout record, @Param("example") LayoutExample example);

    int updateByPrimaryKeySelective(Layout record);

    int updateByPrimaryKey(Layout record);
    //下拉导航栏目数据
    List selectName(@Param("groupId") Long groupId);

    Long selectLayout(@Param("groupId") Long groupId, @Param("name") String name);

    List selectLayoutName(@Param("groupId") Long groupId, @Param("layoutId") Long layoutId);
}
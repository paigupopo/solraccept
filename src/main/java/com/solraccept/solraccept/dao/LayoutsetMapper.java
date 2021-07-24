package com.solraccept.solraccept.dao;


import com.solraccept.solraccept.bean.solr.Layoutset;
import com.solraccept.solraccept.bean.solr.LayoutsetExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LayoutsetMapper {
    long countByExample(LayoutsetExample example);

    int deleteByExample(LayoutsetExample example);

    int deleteByPrimaryKey(Long layoutsetid);

    int insert(Layoutset record);

    int insertSelective(Layoutset record);

    List<Layoutset> selectByExample(LayoutsetExample example);

    Layoutset selectByPrimaryKey(Long layoutsetid);

    int updateByExampleSelective(@Param("record") Layoutset record, @Param("example") LayoutsetExample example);


    int updateByExample(@Param("record") Layoutset record, @Param("example") LayoutsetExample example);

    int updateByPrimaryKeySelective(Layoutset record);


    int updateByPrimaryKey(Layoutset record);

    Long selectout(Long groupId);


}
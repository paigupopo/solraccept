package com.solraccept.solraccept.dao;

import com.solraccept.solraccept.bean.solr.Virtualhost;
import com.solraccept.solraccept.bean.solr.VirtualhostExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VirtualhostMapper {
    long countByExample(VirtualhostExample example);

    int deleteByExample(VirtualhostExample example);

    int deleteByPrimaryKey(Long virtualhostid);

    int insert(Virtualhost record);

    int insertSelective(Virtualhost record);

    List<Virtualhost> selectByExample(VirtualhostExample example);

    Virtualhost selectByPrimaryKey(Long virtualhostid);

    int updateByExampleSelective(@Param("record") Virtualhost record, @Param("example") VirtualhostExample example);

    int updateByExample(@Param("record") Virtualhost record, @Param("example") VirtualhostExample example);

    int updateByPrimaryKeySelective(Virtualhost record);

    int updateByPrimaryKey(Virtualhost record);

    String selectURL(Long layoutId);
}
package com.solraccept.solraccept.dao;

import com.solraccept.solraccept.bean.solr.Journalarticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface JournalarticleMapper {
    List<Journalarticle> queryList(List<String> idList);
}

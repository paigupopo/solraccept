package com.solraccept.solraccept.service;

import com.solraccept.solraccept.bean.solr.Journalarticle;
import com.solraccept.solraccept.bean.MessageBean;

import java.util.List;

public interface JournalarticleService {

    /**
     * 获得文章数据
     * @param messageBeans
     * @return
     */
    List<Journalarticle> queryJournalarticleList(List<MessageBean> messageBeans);
}

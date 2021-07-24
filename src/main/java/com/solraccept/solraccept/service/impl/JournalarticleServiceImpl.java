package com.solraccept.solraccept.service.impl;

import com.solraccept.solraccept.bean.solr.Journalarticle;
import com.solraccept.solraccept.bean.MessageBean;
import com.solraccept.solraccept.dao.GroupMapper;
import com.solraccept.solraccept.dao.JournalarticleMapper;
import com.solraccept.solraccept.dao.LayoutsetMapper;
import com.solraccept.solraccept.dao.VirtualhostMapper;
import com.solraccept.solraccept.service.JournalarticleService;
import com.solraccept.solraccept.utils.HtmlUtils;
import com.solraccept.solraccept.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JournalarticleServiceImpl implements JournalarticleService {
    @Autowired
    private JournalarticleMapper journalarticleMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private LayoutsetMapper mapper;

    @Autowired
    private VirtualhostMapper virtualhostMapper;

    @Override
    public List<Journalarticle> queryJournalarticleList(List<MessageBean> messageBeans) {
        List<String> idList = new ArrayList<String>();
        for (MessageBean messageBean : messageBeans) {
            if (!messageBean.getUrl().endsWith(".shtml")) {
                continue;
            }
            idList.add(StringUtils.getJournalarticleId(messageBean.getUrl()));
        }

        List<Journalarticle> journalarticles = journalarticleMapper.queryList(idList);

        for (Journalarticle journalar : journalarticles) {
            //将得到的数据集进行接送转换
            String url = journalar.getUrl();
            String content = journalar.getContent();

            long groupId = journalar.getGroupId();

            List<String> img = StringUtils.getImg(content);
            for (String s : img) {
                Pattern p1 = Pattern.compile("\"(.*?)\"");
                Matcher matcher = p1.matcher(s);


                if (matcher.find()) {

                    if (matcher.group(1).contains("http")) {
                        journalar.setImgUrl(matcher.group(1));
                    } else {
                        if (matcher.group(1).contains("documents") && groupId == 10184) {
                            journalar.setImgUrl("http://www.suining.gov.cn/" + matcher.group(1));

                        } else {
                            long l = mapper.selectout(groupId);

                            String s1 = virtualhostMapper.selectURL(l);

                            if (matcher.group(1).contains("documents") && s1 != null && !s1.equals("")) {
                                journalar.setImgUrl("http://" + s1 + matcher.group(1));

                            } else {
                                Long parentId = groupMapper.selectparentGroupid(groupId);
                                Long aLong = mapper.selectout(parentId);
                                String imgSrc = virtualhostMapper.selectURL(aLong);
                                if (matcher.group(1).contains("documents") && imgSrc != null && !imgSrc.equals("")) {
                                    journalar.setImgUrl("http://" + imgSrc + matcher.group(1));
                                } else if (matcher.group(1).contains("documents")) {

                                    journalar.setImgUrl("http://www.suining.gov.cn/" + matcher.group(1));

                                }
                            }

                        }

                    }
                }
            }

            journalar.setName(HtmlUtils.Html2Text(journalar.getName()));

            Document parse1 = Jsoup.parse(content);
            String text1 = parse1.text();

            String s1 = HtmlUtils.Html2Text(text1);

            journalar.setContent(s1);

            //对url路径进行处理
            if (url != null && url.indexOf("http") != -1) {
                journalar.setUrl(journalar.getUrl());
            } else {
                if (groupId == 10184) {
                    journalar.setUrl("http://www.suining.gov.cn" + journalar.getUrl());
                } else {
                    long l = mapper.selectout(groupId);

                    String s = virtualhostMapper.selectURL(l);

                    if (s != null && !s.equals("")) {
                        journalar.setUrl("http://" + s + journalar.getUrl());

                    } else {
                        Long parentId = groupMapper.selectparentGroupid(groupId);
                        Long aLong = mapper.selectout(parentId);
                        String webUrl = virtualhostMapper.selectURL(aLong);
                        if (webUrl != null && !webUrl.equals("")) {
                            journalar.setUrl("http://" + webUrl + journalar.getUrl());
                        } else {
                            journalar.setUrl("http://www.suining.gov.cn" + journalar.getUrl());

                        }
                    }
                }
            }

            String s = journalar.getTitle();

            Document parse = Jsoup.parse(s);
            String text = parse.text();
            String s2 = HtmlUtils.Html2Text(text);
            journalar.setTitle(s2);

            journalar.setName(HtmlUtils.Html2Text(journalar.getName()));

        }
        return journalarticles;
    }
}

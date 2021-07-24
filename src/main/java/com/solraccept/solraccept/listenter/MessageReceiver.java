package com.solraccept.solraccept.listenter;

import java.util.*;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.solraccept.solraccept.bean.solr.Journalarticle;
import com.solraccept.solraccept.bean.MessageBean;
import com.solraccept.solraccept.constant.BusiTypeEnum;
import com.solraccept.solraccept.service.JournalarticleService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;


import com.rabbitmq.client.Channel;

@Component
public class MessageReceiver {

	@Autowired
	private SolrTemplate solrTemplate;

	@Autowired
	private JournalarticleService journalarticleService;

	/**
	 * 接受mq消息
	 * @param message
	 * @param channel
	 * @throws Exception
	 */
	@RabbitListener(queues = {"CMS-pages-static-information1"},concurrency = "20")
	@RabbitHandler
	public void onMessage(Message message, Channel channel) throws Exception{
		String msg = new String(message.getBody());
		System.out.println("<start---------------------------------------------------------------------------------------------------------->");
		System.out.println("接受到的message:" + msg);
		System.out.println("<end---------------------------------------------------------------------------------------------------------->");
		List<MessageBean> beans = JSONArray.parseArray(msg, MessageBean.class);
		System.out.println("开始发送");
		List<Journalarticle> journalarticles = journalarticleService.queryJournalarticleList(beans);

		solrTemplate.saveBeans("collection1", journalarticles);
		solrTemplate.commit("collection1");
		System.out.println("处理消息");
		channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
	}

	@RabbitListener(queues = {"CMS-pages-static-information2"},concurrency = "20")
	@RabbitHandler
	public void onTodo(Message message, Channel channel) throws Exception{
		String msg = new String(message.getBody());
		System.out.println("<start---------------------------------------------------------------------------------------------------------->");
		System.out.println("接受到的message:" + msg);
		System.out.println("<end---------------------------------------------------------------------------------------------------------->");
		List<JSONObject> beans = JSONArray.parseArray(msg, JSONObject.class);

		Map<String, List<Object>> map = getDataMap(beans);

		for (String key : map.keySet()) {
			solrTemplate.saveBeans("collection1", map.get(key));
		}

		solrTemplate.commit("collection1");
		System.out.println("处理消息");
		channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
	}

	private Map<String, List<Object>> getDataMap(List<JSONObject> beans) {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		for (JSONObject o : beans) {
			String busiType = o.getString("busiType");
			List<Object> l = map.get(busiType);
			if (l == null) {
				l = new ArrayList<>();
			}
			l.add(JSONObject.parseObject(JSONObject.toJSONString(l) ,BusiTypeEnum.ALL.getClassName(busiType)));
			map.put(busiType, l);
		}
		return map;
	}
}

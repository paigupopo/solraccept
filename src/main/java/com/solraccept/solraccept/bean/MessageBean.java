package com.solraccept.solraccept.bean;

import java.io.Serializable;

public class MessageBean implements Serializable{

	private static final long serialVersionUID = -5774491309301571355L;
	private String cmd;
	private String type;
	private String url;
	
	public MessageBean() {

	}
	
	public MessageBean(String cmd, String type, String url) {
		this.cmd =cmd;
		this.type =type;
		this.url =url;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

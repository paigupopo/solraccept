package com.solraccept.solraccept.constant;

import com.solraccept.solraccept.bean.solr.HwadeeInterviewnotice;
import com.solraccept.solraccept.bean.solr.HwadeeMailbox;
import com.solraccept.solraccept.bean.solr.HwadeePolls;
import com.solraccept.solraccept.bean.solr.HwadeeSuggest;

public enum BusiTypeEnum {
    ALL("00", null),
    MAILBOX("01", HwadeeMailbox.class),
    SUGGEST("02", HwadeeSuggest.class),
    INTERVIEW("03", HwadeeInterviewnotice.class),
    SURVEY("04", HwadeePolls.class);

    private String value;
    
    private Class<?> className;

    private BusiTypeEnum(String value, Class<?> className){
        this.value = value;
        this.className = className;
    }
    
    public Class<?> getClassName(String value) {
        for (BusiTypeEnum e : BusiTypeEnum.values()) {
            if (value.equals(e.getValue())) {
                return e.getClassName();
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Class<?> getClassName() {
        return className;
    }

    public void setClassName(Class<?> className) {
        this.className = className;
    }
}

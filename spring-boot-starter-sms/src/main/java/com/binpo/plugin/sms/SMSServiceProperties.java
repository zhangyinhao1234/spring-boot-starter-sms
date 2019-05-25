package com.binpo.plugin.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("binpo.plugin.sms")
public class SMSServiceProperties {

    /**
     * 实现的短信的bean
     */
    String smsspingBeanIds;
    
    Integer qcloudAppId;
    
    String qcloudAppKey;
    
    String qcloudSignName;
    
    
    
    
    
    

    public String getSmsspingBeanIds() {
        return smsspingBeanIds;
    }

    public void setSmsspingBeanIds(String smsspingBeanIds) {
        this.smsspingBeanIds = smsspingBeanIds;
    }

    

    public Integer getQcloudAppId() {
        return qcloudAppId;
    }

    public void setQcloudAppId(Integer qcloudAppId) {
        this.qcloudAppId = qcloudAppId;
    }

    public String getQcloudAppKey() {
        return qcloudAppKey;
    }

    public void setQcloudAppKey(String qcloudAppKey) {
        this.qcloudAppKey = qcloudAppKey;
    }

    public String getQcloudSignName() {
        return qcloudSignName;
    }

    public void setQcloudSignName(String qcloudSignName) {
        this.qcloudSignName = qcloudSignName;
    }
    
    
    
    
    
}

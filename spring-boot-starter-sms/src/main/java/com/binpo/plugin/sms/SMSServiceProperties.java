package com.binpo.plugin.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("binpo.plugin.sms")
public class SMSServiceProperties {

    /**
     * 实现的短信的bean
     */
    String smsspingBeanIds;

    String qcloud;

    Integer qcloudAppId;

    String qcloudAppKey;

    String qcloudSignName;

    String dhst;

    String dhstAccount;
    String dhstPwd;

    String dhstVoiceAccount;
    String dhstVoicePwd;

    String sykj;

    String sykjAccount;
    String sykjPwd;

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

    public String getDhstAccount() {
        return dhstAccount;
    }

    public void setDhstAccount(String dhstAccount) {
        this.dhstAccount = dhstAccount;
    }

    public String getDhstPwd() {
        return dhstPwd;
    }

    public void setDhstPwd(String dhstPwd) {
        this.dhstPwd = dhstPwd;
    }

    public String getDhstVoiceAccount() {
        return dhstVoiceAccount;
    }

    public void setDhstVoiceAccount(String dhstVoiceAccount) {
        this.dhstVoiceAccount = dhstVoiceAccount;
    }

    public String getDhstVoicePwd() {
        return dhstVoicePwd;
    }

    public void setDhstVoicePwd(String dhstVoicePwd) {
        this.dhstVoicePwd = dhstVoicePwd;
    }

    public String getQcloud() {
        return qcloud;
    }

    public void setQcloud(String qcloud) {
        this.qcloud = qcloud;
    }

    public String getDhst() {
        return dhst;
    }

    public void setDhst(String dhst) {
        this.dhst = dhst;
    }

    public String getSykj() {
        return sykj;
    }

    public void setSykj(String sykj) {
        this.sykj = sykj;
    }

    public String getSykjAccount() {
        return sykjAccount;
    }

    public void setSykjAccount(String sykjAccount) {
        this.sykjAccount = sykjAccount;
    }

    public String getSykjPwd() {
        return sykjPwd;
    }

    public void setSykjPwd(String sykjPwd) {
        this.sykjPwd = sykjPwd;
    }

}

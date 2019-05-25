package com.binpo.plugin.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.binpo.plugin.sms.base.ISMSToolService;
import com.binpo.plugin.sms.control.SMSControlCenterImpl;
import com.binpo.plugin.sms.control.i.SMSConfig;
import com.binpo.plugin.sms.control.i.SMSErrorInfoSend;
import com.binpo.plugin.sms.control.i.SMSSaveContent;
import com.binpo.plugin.sms.control.i.SMSSpringBeanUtil;
import com.binpo.plugin.sms.impl.DHSTSMS;
import com.binpo.plugin.sms.impl.QcloudSMS;
import com.binpo.plugin.sms.impl.SYKJSMS;

@Configuration
@EnableConfigurationProperties(SMSServiceProperties.class)
@ConditionalOnClass(SMSControlCenterImpl.class)
public class SMSAutoConfigure {

    @Autowired
    SMSServiceProperties properties;

    String TRUE = "true";

    @Bean
    @ConditionalOnProperty(prefix = "binpo.plugin.sms", value = "sykj", havingValue = "true")
    public ISMSToolService sykjSMSService() {
        SYKJSMS sykjsms = new SYKJSMS();
        sykjsms.setAccount(properties.getSykjAccount());
        sykjsms.setPswd(properties.getSykjPwd());
        sykjsms.setVoiceAccount(properties.getSykjAccount());
        sykjsms.setVoicePswd(properties.getSykjPwd());
        return sykjsms;
    }

    @Bean
    @ConditionalOnProperty(prefix = "binpo.plugin.sms", value = "qcloud", havingValue = "true")
    public ISMSToolService qcloudSMS() {
        QcloudSMS qcloudSMS = new QcloudSMS();
        qcloudSMS.setAppid(properties.getQcloudAppId());
        qcloudSMS.setAppkey(properties.getQcloudAppKey());
        qcloudSMS.setFreeSignName(properties.getQcloudSignName());
        return qcloudSMS;
    }

    @Bean
    @ConditionalOnProperty(prefix = "binpo.plugin.sms", value = "dhst", havingValue = "true")
    public ISMSToolService dHSTSMS() {
        DHSTSMS dhstsms = new DHSTSMS();
        dhstsms.setAccount(properties.getDhstAccount());
        dhstsms.setPswd(properties.getDhstPwd());
        dhstsms.setVoiceAccount(properties.getDhstVoiceAccount());
        dhstsms.setVoicePswd(properties.getDhstVoicePwd());
        return dhstsms;
    }

    @Bean
    @ConditionalOnMissingBean(SMSControlCenterImpl.class)
    public SMSControlCenterImpl smsControlCenter(SMSSpringBeanUtil smsSpringBeanUtil, SMSConfig smsConfig,
            SMSErrorInfoSend sendErrorInfo, SMSSaveContent saveContentToDB)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        SMSControlCenterImpl smsControlCenterImpl = new SMSControlCenterImpl();
        smsControlCenterImpl.setDefaultSpringids(getDefaultImpleBeanId() + properties.getSmsspingBeanIds());
        smsControlCenterImpl.setSmsConfig(smsConfig);
        smsControlCenterImpl.setApplicationContextUtil(smsSpringBeanUtil);
        smsControlCenterImpl.setSendErrorInfo(sendErrorInfo);
        smsControlCenterImpl.setSaveContentToDB(saveContentToDB);
        return smsControlCenterImpl;
    }

    private String getDefaultImpleBeanId() {
        StringBuilder buf = new StringBuilder("");
        if (TRUE.equals(properties.getQcloud())) {
            buf.append("qcloudSMS,");
        }
        if (TRUE.equals(properties.getDhst())) {
            buf.append("dHSTSMS,");
        }
        if (TRUE.equals(properties.getSykj())) {
            buf.append("sykjSMSService,");
        }
        return buf.toString();
    }

    @Bean
    @ConditionalOnMissingBean
    public SMSSpringBeanUtil smsSpringBeanUtil() {
        return new SMSSpringBeanUtilImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public SMSConfig sMSConfig() {
        return new SMSConfigImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public SMSErrorInfoSend sendErrorInfo() {
        return new SMSErrorInfoSendImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public SMSSaveContent saveContentToDB() {
        return new SMSSaveContentImpl();
    }

}

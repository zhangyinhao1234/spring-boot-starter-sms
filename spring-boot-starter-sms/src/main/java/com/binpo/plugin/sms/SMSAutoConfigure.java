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
    
    @Bean
    public ISMSToolService sykjSMSService() {
        return new SYKJSMS();
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
    public ISMSToolService dHSTSMS() {
        return new DHSTSMS();
    }

    @Bean
    @ConditionalOnMissingBean(SMSControlCenterImpl.class)
    public SMSControlCenterImpl smsControlCenter(SMSSpringBeanUtil smsSpringBeanUtil,SMSConfig smsConfig)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        SMSControlCenterImpl smsControlCenterImpl = new SMSControlCenterImpl();
        smsControlCenterImpl.setDefaultSpringids("sykjSMSService,qcloudSMS,dHSTSMS");
        smsControlCenterImpl.setSmsConfig(smsConfig);
        smsControlCenterImpl.setApplicationContextUtil(smsSpringBeanUtil);
        return smsControlCenterImpl;
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
}

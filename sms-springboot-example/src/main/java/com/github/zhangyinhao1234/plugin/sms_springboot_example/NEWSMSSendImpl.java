package com.github.zhangyinhao1234.plugin.sms_springboot_example;

import org.springframework.stereotype.Component;

import com.binpo.plugin.sms.base.AbstractSMSServer;
import com.binpo.plugin.sms.base.SMSSendParams;
import com.binpo.plugin.sms.base.SMSUtil.SendCode;


/**
 * 
 * 新增加的短信平台
 *
 * @author zhang 2019年5月25日 下午9:35:36
 */
@Component("nEWSMSSendImpl")
public class NEWSMSSendImpl extends AbstractSMSServer{

    @Override
    public SendCode sendVoiceSecurityCode(SMSSendParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSMSServiceName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SendCode sendSMS(SMSSendParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getFreeSignName() {
        // TODO Auto-generated method stub
        return null;
    }

}

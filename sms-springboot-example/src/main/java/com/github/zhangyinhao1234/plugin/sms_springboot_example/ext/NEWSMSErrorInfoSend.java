package com.github.zhangyinhao1234.plugin.sms_springboot_example.ext;

import org.springframework.stereotype.Component;

import com.binpo.plugin.sms.control.i.SMSErrorInfoSend;

@Component
public class NEWSMSErrorInfoSend implements SMSErrorInfoSend {

    @Override
    public void send(String errorInfo) {
        // TODO Auto-generated method stub
        System.out.println("发送错误信息给开发人员/运营人员：" + errorInfo + ";");
    }

}

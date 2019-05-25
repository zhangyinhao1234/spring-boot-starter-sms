package com.github.zhangyinhao1234.plugin.sms_springboot_example;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.binpo.plugin.sms.control.i.SMSSaveContent;
/**
 * 
 * 实现接口保存短信内容
 *
 * @author zhang 2019年5月25日 下午9:33:35
 */
@Component
public class SMSSaveContentNEW implements SMSSaveContent{
    
    public SMSSaveContentNEW() {
        System.out.println("init SMSSaveContentNEW ...................");
    }

    @Override
    public void save(Boolean sendSuccess, String telephone, String content, String smsTemplateCode,
            Map<String, String> params) {
        // TODO Auto-generated method stub
        System.out.println("----------------------------");
    }

}

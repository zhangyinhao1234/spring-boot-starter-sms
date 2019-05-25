package com.github.zhangyinhao1234.plugin.sms_springboot_example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binpo.plugin.sms.base.ISMSToolService;
import com.binpo.plugin.sms.base.SMSSendParams;
import com.binpo.plugin.sms.control.SMSControlCenter;

@RestController
public class SMSController {

    @Autowired
    private SMSControlCenter smsControlCenter;

    @RequestMapping("/sms/send/{tel}")
    public Map send(HttpServletRequest request, HttpServletResponse response,@PathVariable String tel, String content)
            throws IOException {
        content = "您的手机验证码是：123456,打死都不能告诉别人哦！(15分钟有效)";
        SMSSendParams params = new SMSSendParams(tel, content);
        params.setType(ISMSToolService.Type.content);
        smsControlCenter.sendSMS(params);
        Map map = new HashMap();
        map.put("success", "1");
        return map;
    }
}

# 使用说明
## sms-plugin

​	发送短信的核心组件，默认实现了示远科技，大汉三通和腾讯云的短信发送。实现多供应商的短信接口，当有任何一方短信发送失败后会使用另外的短信平台发送短信。

​	对扩展支持：如果想增加其他短信供应商，可以继承 AbstractSMSServer 抽象类。轻松替换/增加短息平台

​	扩展接口：

SMSConfig：必须实现此接口。实现接口做到随时关闭短信服务，启动/禁用指定短信平台

SMSErrorInfoSend：实现此接口可将发送错误的短信进行保存，便于后续的统计

SMSSaveContent：如果你有保存短信内容的需求可以实现此接口

SMSSpringBeanUtil：必须实现此接口。通过ID获取指定的短信供应商短信发送的实现类

以上的实现类必须添加到Spring的上下文中，你可以使用@Bean 注解



### 安装：

在spring-boot-starter-sms\sms-plugin 目录执行  mvn clean install

在spring-boot-starter-sms\spring-boot-starter-sms 目录执行 mvn clean install



简单使用：

	@Autowired
	private SMSControlCenter smsControlCenter;
	@Test
	public void testSSMSConfigImplendSMS() throws IOException{
		String content="您的手机验证码是：123456,打死都不能告诉别人哦！(15分钟有效)";
		SMSSendParams params=new SMSSendParams(this.telephone, content);
		params.setType(ISMSToolService.Type.content);
		smsControlCenter.sendSMS(params);
	}


	添加新接入的短信平台

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


​	



### spring mvc项目如何使用：

POM.xml添加引用

		<dependency>
			<groupId>com.github.zhangyinhao1234.plugin</groupId>
			<artifactId>sms-plugin</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>


初始化：

    @Bean
    public ISMSToolService dHSTSMS() {
        return new DHSTSMS();
    }
    
    @Bean
    public SMSControlCenterImpl smsControlCenter(SMSSpringBeanUtil smsSpringBeanUtil)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        SMSControlCenterImpl smsControlCenterImpl = new SMSControlCenterImpl();
        smsControlCenterImpl.setDefaultSpringids("sykjSMSService,qcloudSMS,dHSTSMS");
        smsControlCenterImpl.setSmsConfig(sMSConfig());
        smsControlCenterImpl.setApplicationContextUtil(smsSpringBeanUtil);
        return smsControlCenterImpl;
    }
    
    private SMSConfig sMSConfig() {
        return new SMSConfigImpl();
    }
    @Bean
    public SMSSpringBeanUtil smsSpringBeanUtil() {
        return new SMSSpringBeanUtilImpl();
    }
使用：

	@Autowired
	private SMSControlCenter smsControlCenter;
	@Test
	public void testSSMSConfigImplendSMS() throws IOException{
		String content="您的手机验证码是：123456,打死都不能告诉别人哦！(15分钟有效)";
		SMSSendParams params=new SMSSendParams(this.telephone, content);
		params.setType(ISMSToolService.Type.content);
		smsControlCenter.sendSMS(params);
	}

## 

### 在SpringBoot 中如何使用

POM.xml添加引用

		<dependency>
			<groupId>com.github.zhangyinhao1234.plugin</groupId>
			<artifactId>spring-boot-starter-sms</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
添加配置文件

    binpo: 
    	plugin: 
            sms: 
              smsspingBeanIds: aa,bb,cc # 需要添加的自己开发短信发送实现类的 spring bean id
              # 腾讯云配置文件
              qcloud: true # 使用腾讯云的短信服务 true/false 不配置表示不使用
              qcloudAppId: 1000000
              qcloudAppKey: APPkey
              qcloudSignName: 【XX科技】 # 短信签名
    
              # 大汉三通短信
              dhst: true 
              dhstAccount: xxxx #内容短信账号
              dhstPwd: pwd # 内容短信密码
              dhstVoiceAccount: xxxx # 语音账号
              dhstVoicePwd: pwd # 语音密码

直接使用：

	@Autowired
	private SMSControlCenter smsControlCenter;
	@Test
	public void testSSMSConfigImplendSMS() throws IOException{
		String content="您的手机验证码是：123456,打死都不能告诉别人哦！(15分钟有效)";
		SMSSendParams params=new SMSSendParams(this.telephone, content);
		params.setType(ISMSToolService.Type.content);
		smsControlCenter.sendSMS(params);
	}
## 

### 在SpringBoot 中使用的例子

请参考项目 [sms-springboot-example](https://github.com/zhangyinhao1234/spring-boot-starter-sms/tree/master/sms-springboot-example)

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
就是这么简单
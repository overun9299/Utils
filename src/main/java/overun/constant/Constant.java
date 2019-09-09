package overun.constant;

public interface Constant {
	/**字符集*/
	String CHARSET = "UTF-8";
	/**请求文本类型*/
	String DEFAULT_CONTENT_TYPE = "application/json; charset=utf-8";
	/**设置连接超时时间*/
	int CONNECT_TIMEOUT = 5000;
	/**设置是否向HttpURLConnection输出*/
	boolean DO_OUTPUT = true;
	/**设置是否从httpUrlConnection读入*/
	boolean DO_INPUT = true;
	/**设置是否用缓存*/
	boolean USE_CACHES = false;
	boolean INSTANCE_FOLLOW_REDIRECTS = true;
	/**维持长连接*/
	String Connection = "Keep-Alive";
	/**接受数据格式*/
	String ACCEPT = "application/json";
	/**true*/
	String TRUE = "true";
	/**false*/
	String FALSE = "false";
	/***/
	/**redis存储  登陆static_list存储*/
	String STATIC_LIST = "static_resource:static_list:";
	/**redis存储  登陆static_name存储*/
	String STATIC_NAME = "static_resource:static_name:";
	/**缓存名称 role_state*/
	String ROLE_STATE = "static_resource:role_state:";

	/**分机号起始*/
	int SLOT_START = 7000;
	int SLOT_END = 7200;
	/**定义boolean*/
	boolean B_TRUE = true;
	boolean B_FALSE = false;

	/**角色*/
	String ROLE_USE = "弹屏,外呼";

	/**验证码校验*/
	String CHECK_AND_PASS = "{\"errorMessages\":\"校验通过\",\"success\":\"0\"}";
	String CHECK_OUT = "{\"errorMessages\":\"验证输入不正确\",\"success\":\"1\"}";
	/**节点不可用*/
	int NODE_FALSE_CODE = 3;
	/**暂无数据*/
	int NO_DATA_CODE = 2;

}

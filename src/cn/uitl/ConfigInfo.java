package cn.uitl;


public class ConfigInfo {
	
	public static Configur config = new Configur("config/properties/params.properties");
	
	public static String url = config.getProperty("url");
}

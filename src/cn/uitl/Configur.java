package cn.uitl;

import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;  
  
/** 
 * 用于读写Property属�?配置文件的类 
 *  
 * @author BianJing<br/> 
 *         E-mail：vipbooks@163.com 
 * @version 0.9 2010-10-28 
 */  
public class Configur {  
    private Properties properties;  
  
    /** 配置文件的完整路�?*/  
    private String filePath;  
  
    /** 
     * 初始化RWProperties 
     *  
     * @param propertyFilePath 
     *            属�?配置文件的完整路径， 
     *            如："com/pagination/config/PaginationConfig.properties" 
     * @return RWProperties的实�?
     */  
    public Configur(String propertyFilePath) {  
        filePath = propertyFilePath;  
        properties = getProperties(propertyFilePath);  
    };  
  
    /** 
     * 获得Key值所对应的Value值�? 
     *  
     * @param key 
     *            属�?配置文件的Key�?
     * @return Key值所对应的Value�?
     */  
    public String getProperty(String key) {  
        return properties.getProperty(key);  
    }
    /**
     * 返回配置文件里面的键值对
     * @return
     */
    @SuppressWarnings({"unchecked" })
	public Map<String, String> getPropertyMap(){
    	Map<String, String> pathMap = new HashMap<String, String>();  
    	Enumeration en = properties.keys();  
        while(en.hasMoreElements()){  
            String name = en.nextElement().toString();  
            String path = properties.getProperty(name);  
            pathMap.put(name, path);  
        }
        return pathMap;
    }
    /** 
     * 修改属�?配置文件 
     *  
     * @param key 
     *            属�?配置文件的Key�?
     * @param value 
     *            属�?配置文件的value�?
     * @param propertyFilePath 
     *            属�?配置文件的完整路径， 
     *            如："com/pagination/config/PaginationConfig.properties" 
     * @return 修改成功返回true，失败则返回false 
     */  
    public boolean setProperty(String key, String value) {  
        FileOutputStream fos = getFileOutputStream(filePath);  
  
        properties.setProperty(key, value);  
        boolean flag = false;  
        try {  
            properties.store(fos, null);  
            flag = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (fos != null) {  
                try {  
                    fos.flush();  
                    fos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    close();  
                } finally {  
                    fos = null;  
                }  
            }  
        }  
  
        return flag;  
    }  
  
    /** 
     * 移除属�?配置文件中的某个属�? 
     *  
     * @param key 
     *            属�?配置文件的Key�?
     * @return 移除成功返回true，失败则返回false 
     */  
    public boolean removeProperty(String key) {  
        FileOutputStream fos = getFileOutputStream(filePath);  
  
        properties.remove(key);  
        boolean flag = false;  
        try {  
            properties.store(fos, null);  
            flag = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (fos != null) {  
                try {  
                    fos.flush();  
                    fos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    close();  
                } finally {  
                    fos = null;  
                }  
            }  
        }  
  
        return flag;  
    }  
  
    /** 
     * 释放资源 
     */  
    public void close() {  
        if (properties != null) {  
            properties.clear();  
        }  
    }  
  
    /** 
     * 返回加载了配置文件的Properties对象 
     *  
     * @param propertyFilePath 
     *            属�?配置文件的完整路径， 
     *            如："com/pagination/config/PaginationConfig.properties" 
     * @return java.util.Properties 
     */  
    private Properties getProperties(String propertyFilePath) {  
        Properties properties = new Properties();  
        InputStream is = null;  
        try {  
            is = Configur.class.getClassLoader().getResourceAsStream(  
                    propertyFilePath);  
  
            properties.load(is);  
        } catch (NullPointerException e) {  
            e.printStackTrace();  
            close();  
        } catch (IOException e) {  
            e.printStackTrace();  
            close();  
        } finally {  
            if (is != null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                } finally {  
                    is = null;  
                }  
            }  
        }  
  
        return properties;  
    }  
  
    /** 
     * 返回已经加载了配置文件的文件输出�?
     *  
     * @param propertyFilePath 
     *            属�?配置文件的完整路径， 
     *            如："com/pagination/config/PaginationConfig.properties" 
     * @return java.io.FileOutputStream 
     */  
    private FileOutputStream getFileOutputStream(String propertyFilePath) {  
        FileOutputStream fos = null;  
        String filePath = null;  
  
        filePath = Configur.class.getClassLoader().getResource(  
                propertyFilePath).getFile();  
        filePath = filePath.replaceFirst("/", "");  
        // 如果URL地址中含有空格，则空格会�?%20"替换，所以要将它替换回来  
        filePath = filePath.replaceAll("%20", " ");  
        try {  
            fos = new FileOutputStream(filePath);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            close();  
        }  
  
        return fos;  
    }  
}  
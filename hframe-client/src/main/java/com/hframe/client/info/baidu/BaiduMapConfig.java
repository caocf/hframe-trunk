package com.hframe.client.info.baidu;

import com.hframe.common.util.ResourceWrapper;

import java.lang.reflect.InvocationTargetException;

@Source("third/baidumap.properties")
public class BaidumapConfig {

	@Key( "third.baidumap.zk")
	private String zk;
	@Key( "third.baidumap.domain")
	private String domain;
  
 
 	
	public String getZk(){
		return zk;
	}

	public void setZk(String zk){
    	this.zk = zk;
    }

 	
	public String getDomain(){
		return domain;
	}

	public void setDomain(String domain){
    	this.domain = domain;
    }

	private static BaidumapConfig instance;

	private BaidumapConfig() {
		super();
	}

	public  static BaidumapConfig getInstance(){
		if(instance == null) {
			synchronized (BaidumapConfig.class) {
				if(instance == null) {
					try {
						return instance = ResourceWrapper.getResourceBean(BaidumapConfig.class);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					return instance = new BaidumapConfig();
				}
			}
		}
		return instance;
	}

}

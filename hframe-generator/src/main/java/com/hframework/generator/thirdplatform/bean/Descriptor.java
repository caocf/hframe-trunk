package com.hframework.generator.thirdplatform.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.hframework.generator.thirdplatform.bean.descriptor.*;
import com.hframework.generator.thirdplatform.bean.descriptor.*;

@XStreamAlias("descriptor")
public class Descriptor   {

	@XStreamAlias("global")
	private Global global;
	@XStreamAlias("interfaces")
	private Interfaces interfaces;

	private String platformName;

	public Descriptor() {
    	}
   
 
 	
	public Global getGlobal(){
		return global;
	}

	public void setGlobal(Global global){
    	this.global = global;
    }

 	
	public Interfaces getInterfaces(){
		return interfaces;
	}

	public void setInterfaces(Interfaces interfaces){
    	this.interfaces = interfaces;
    }

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
}

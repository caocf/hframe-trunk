package com.hframework.web.config.bean.component;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * generated by hframework on 2016.
 */@XStreamAlias("effect")
public class Effect   {

	@XStreamAsAttribute
    @XStreamAlias("type")
	private String type;
	@XStreamAsAttribute
    @XStreamAlias("action")
	private String action;
	@XStreamAsAttribute
    @XStreamAlias("isStack")
	private String isStack;
    @XStreamAsAttribute
    @XStreamAlias("param")
    private String param;
    @XStreamAsAttribute
    @XStreamAlias("content")
    private String content;



    public Effect() {
    }
   
 	 	 
     public String getType(){
     	return type;
     }

     public void setType(String type){
     	this.type = type;
     }
	 	 	 
     public String getAction(){
     	return action;
     }

     public void setAction(String action){
     	this.action = action;
     }
	 	 	 
     public String getIsStack(){
     	return isStack;
     }

     public void setIsStack(String isStack){
     	this.isStack = isStack;
     }
	 	 	 
     public String getParam(){
     	return param;
     }

     public void setParam(String param){
     	this.param = param;
     }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

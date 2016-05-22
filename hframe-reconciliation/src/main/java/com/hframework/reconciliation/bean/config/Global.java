package com.hframework.reconciliation.bean.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 * generated by hframework on 2016.
 */@XStreamAlias("global")
public class Global   {

	@XStreamImplicit
    @XStreamAlias("host")
	private List<Host> hostList;

    public Global() {
    }
   
 	 	 
     public List<Host> getHostList(){
     	return hostList;
     }

     public void setHostList(List<Host> hostList){
     	this.hostList = hostList;
     }
	 
}

package com.hframework.reconciliation.bean.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * generated by hframework on 2016.
 */@XStreamAlias("importer")
public class Importer   {

	@XStreamImplicit
    @XStreamAlias("host")
	private List<Host> hostList;
	@XStreamAlias("file")
	private File file;
	@XStreamAlias("dataset")
	private Dataset dataset;
	@XStreamAsAttribute
    @XStreamAlias("type")
	private String type;

    public Importer() {
    }
   
 	 	 
     public List<Host> getHostList(){
     	return hostList;
     }

     public void setHostList(List<Host> hostList){
     	this.hostList = hostList;
     }
	 	 	 
     public File getFile(){
     	return file;
     }

     public void setFile(File file){
     	this.file = file;
     }
	 	 	 
     public Dataset getDataset(){
     	return dataset;
     }

     public void setDataset(Dataset dataset){
     	this.dataset = dataset;
     }
	 	 	 
     public String getType(){
     	return type;
     }

     public void setType(String type){
     	this.type = type;
     }
	 
}

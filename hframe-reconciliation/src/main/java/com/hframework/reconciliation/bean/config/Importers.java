package com.hframework.reconciliation.bean.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 * generated by hframework on 2016.
 */@XStreamAlias("importers")
public class Importers   {

	@XStreamImplicit
    @XStreamAlias("importer")
	private List<Importer> importerList;

    public Importers() {
    }
   
 	 	 
     public List<Importer> getImporterList(){
     	return importerList;
     }

     public void setImporterList(List<Importer> importerList){
     	this.importerList = importerList;
     }
	 
}

package com.hframework.generator.thirdplatform.bean.descriptor;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("valueMappers")
public class ValueMappers   {

	@XStreamAlias("valueMapper")
	private ValueMapper valueMapper;

    public ValueMappers() {
    	}
   
 
 	
	public ValueMapper getValueMapper(){
		return valueMapper;
	}

	public void setValueMapper(ValueMapper valueMapper){
    	this.valueMapper = valueMapper;
    }
}

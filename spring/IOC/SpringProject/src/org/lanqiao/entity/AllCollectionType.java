package org.lanqiao.entity;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class AllCollectionType {
	private List<String> listElement ;
	private String[] arrayElement ; 
	private Set<String> setElement ; 
	private Map<String,String> mapElement;
	private Properties propsElement ;
	
	
	public AllCollectionType() {
	}

	
	
	public AllCollectionType(List<String> listElement) {
		this.listElement = listElement;
	}


	public List<String> getListElement() {
		return listElement;
	}


	public void setListElement(List<String> listElement) {
		this.listElement = listElement;
	}


	public String[] getArrayElement() {
		return arrayElement;
	}


	public void setArrayElement(String[] arrayElement) {
		this.arrayElement = arrayElement;
	}


	public Set<String> getSetElement() {
		return setElement;
	}


	public void setSetElement(Set<String> setElement) {
		this.setElement = setElement;
	}


	public Map<String, String> getMapElement() {
		return mapElement;
	}


	public void setMapElement(Map<String, String> mapElement) {
		this.mapElement = mapElement;
	}


	public Properties getPropsElement() {
		return propsElement;
	}


	public void setPropsElement(Properties propsElement) {
		this.propsElement = propsElement;
	}


	@Override
	public String toString() {
		//["aa","bb","cc"]
		String strContent = "" ;
		for(String str :arrayElement) {
			strContent += str +"," ;//aa,bb,cc,
		}
		
		return "list:"+this.listElement+"\nset:"+this.setElement+"\nmap:"+this.mapElement+"\nprop:"+this.propsElement +"\narray:"+strContent;
	}
	
	
}

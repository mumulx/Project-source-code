package com.mumulx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

//XML解析
public class Sample {

	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		List<Dog> dogs = XMLParsrUtil.parseXmlToList("src/com/mumulx/dogs.xml");

		System.out.println(dogs);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

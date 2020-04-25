package com.mumulx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParsrUtil {
	// 输入一个xml文件的路径(//dogs.xml)，输出一个List<Dog>

	public static List<Dog> parseXmlToList(String fileName)
			throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
		List<Dog> dogs = new ArrayList<>();

		// DOM方式解析：入口
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();// 产生一个DOM工厂实例
		// 产品
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 准备输出流，为parse()做准备
		// 解析为一个可以使用java来处理的document对象
		Document document = builder.parse(new FileInputStream(fileName));

		Element element = document.getDocumentElement();// 获取所有文档的节点

		// nodeList存储了3个<dog>
		NodeList nodeList = element.getElementsByTagName("dog");// 获取dog节点
		// 遍历nodeList，3个<dog>
		for (int i = 0; i < nodeList.getLength(); i++) {
			Dog dog = new Dog();
			// Node node = nodeList.item(i);//list.get(i)
			// 获取dog的id属性
			// node.getAttributes()[0]//node中只有getAttributes获取所有的属性而我们只有一个属性id
			// 可以使用他的子接口
			// 获取节点属性
			Element dogElement = (Element) nodeList.item(i);// list.get(i)
			int id = Integer.parseInt(dogElement.getAttribute("id"));
			dog.setId(id);
			// 获取dog的子节点<name><score>
			NodeList childNodes = dogElement.getChildNodes();
			// 遍历每一个子节点
			for (int j = 0; j < childNodes.getLength(); j++) {
				// 每一个子节点<name><level><score>包括文字、空格
				Node dogChild = childNodes.item(j);
				// 排除空格，只拿<xxx>形式的子节点,即Node.ELEMENT_NODE形式的
				if (dogChild.getNodeType() == Node.ELEMENT_NODE) {

					// 判断节点名
					if (dogChild.getNodeName().equals("name")) {// <name>
						String name = dogChild.getFirstChild().getNodeValue();
						dog.setName(name);
					} else if (dogChild.getNodeName().equals("score")) {
						double score = Double.parseDouble(dogChild.getFirstChild().getNodeValue());
						dog.setScore(score);
					} else if (dogChild.getNodeName().equals("level")) {
						int level = Integer.parseInt(dogChild.getFirstChild().getNodeValue());
						dog.setLevel(level);
					}
				}
			}
			dogs.add(dog);
		}
		return dogs;
	}
}

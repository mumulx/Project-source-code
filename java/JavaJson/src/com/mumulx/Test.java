package com.mumulx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Test {

	public static void main(String[] args) throws IOException {
		//mapAndJaon();
//		javaBeanAndJSon();
		//stringAndJSon();
		//Test test = new Test();
		
		//文件变json
		//方法一
		//test.fileAndJSon01();
		//test.fileAndJSon02();
		
		//createJSonFile();
		//jsonArray();
		//mapAndJSONArray();
		//stringAndJSon01();
		//javaBeanAndJSon01();
		jsonArrayToMap();
		
	}
	//1.Map集合、JavaBean、字符串--》JSon对象
	//a.Map集合JSon对象
	public static void mapAndJaon() {
		Map<String,String> map = new HashMap<>();
		map.put("s01", "zs1");
		map.put("s02", "zs2");
		map.put("s03", "zs3");
		map.put("s04", "zs4");
		//map-->json
		JSONObject json = new JSONObject(map);
		System.out.println(json);
	}
	//JavaBean(普通java对象)-->JSon
	public static void javaBeanAndJSon() {
		Address address = new  Address("js","xz");
		Person per = new Person(18,"zs",address);
		//JavaBean-->JSon
		JSONObject json = new JSONObject(per);
		System.out.println(json);
	}
	//JavaBean(普通java对象)-->JSon
	public static void javaBeanAndJSon01() {
		Address address = new  Address("js","xz");
		Person per = new Person(18,"zs",address);
		//JavaBean-->JSon
		
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		json = json.fromObject(per);
		System.out.println(json);
	}
	//String-->JSon
	public static void stringAndJSon() {
		//字符串的形式必须是json的形式
		String str = "{\"name\":\"zs\",\"age\":23}";
		JSONObject json = new JSONObject(str);
		System.out.println(json);
	}
	//String-->JSon
	public static void stringAndJSon01() {
		//字符串的形式必须是json的形式
		String str = "{\"name\":\"zs\",\"age\":23}";
		//JSONObject json = new JSONObject(str);
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		
		json=json.fromObject(str);
		System.out.println(json);

	}
	
	//文件-->JSon
	//方式一
	//per.json-->JSON
	//思路：将文件变成字符串，再将字符串变成JSon
	public void fileAndJSon01() throws IOException {//static方法只能调用static函数
		//将文件变成String
		//相对路径文件流
		InputStream in = super.getClass().getClassLoader().getResourceAsStream("com/mumulx/per.json");
		byte[] bs = new byte[1024];
		int len = -1;
		StringBuffer sb = new StringBuffer();
		while((len=in.read(bs))!=-1) {
			//byte[]-->String
			String str = new String(bs,0,len);
			sb.append(str);
		}
		String rs = sb.toString();
		JSONObject json = new JSONObject(rs);
		System.out.println(json);
		
		
		
		
	}
	//方式二
	public void fileAndJSon02() throws IOException {//static方法只能调用static函数
		//文件-->字符串  需要引入commons-io.jar
		String rs = FileUtils.readFileToString(new File("src/com/mumulx/per.json"));
		JSONObject json = new JSONObject(rs);
		System.out.println(json);
	}
	//生成JSon文件
	public static void createJSonFile() throws JSONException, IOException {
		//准备JSon数据
		Map<String,Person> map = new HashMap<>();
		Person per1 = new Person(11,"zs1",new Address("js1","yc1"));
		Person per2 = new Person(12,"zs2",new Address("js2","yc2"));
		Person per3 = new Person(13,"zs3",new Address("js3","yc3"));
		map.put("per1", per1);
		map.put("per2", per2);
		map.put("per3", per3);
		//map-->JSon
		JSONObject json = new JSONObject(map);
		//生成json文件
		Writer writer = new FileWriter("d:\\aa.obj");
		json.write(writer);
		writer.close();
	}
	
	//JSonArray
	//[{"name":"zs","age":23},{"classname":"aa","classno":1},{"schoolname":"bb","schooladdr":"sdfsdf"}]
	public static void jsonArray() {
		String jsonArrayStr = "[{\"name\":\"zs\",\"age\":23},{\"classname\":\"aa\",\"classno\":1},{\"schoolname\":\"bb\",\"schooladdr\":\"sdfsdf\"}]";
		//String格式的json数组-->json数组
		JSONArray jArray = new JSONArray(jsonArrayStr);
		System.out.println(jArray);
	}
	//对于json的类型转换通常需要引入另外一个json库
	
	public static void mapAndJSONArray() {
		Map<String,String> map = new HashMap<>();
		map.put("s01", "zs1");
		map.put("s02", "zs2");
		map.put("s03", "zs3");
		map.put("s04", "zs4");
		
		//冲突: JSONArray既存在于json.jar又 存在于json-lib库
		net.sf.json.JSONArray jArray = new net.sf.json.JSONArray();
		//map->json
		jArray=jArray.fromObject(map);
		
		System.out.println(jArray);
		
		
		
		
	}
	//JSonArray-->map
	public static void jsonArrayToMap() {
		//JSONArray--》每获得一个json-->key:value-->map
		
		//准备数据
		String jsonArrayStr = "[{\"name\":\"zs\",\"age\":23},{\"classname\":\"aa\",\"classno\":1},{\"schoolname\":\"bb\",\"schooladdr\":\"sdfsdf\"}]";
		//string-->jsonArray
		net.sf.json.JSONArray jArray = new net.sf.json.JSONArray();
		jArray=jArray.fromObject(jsonArrayStr);
		
		//JSONArray--》获取每一个JSOn
		Map<String,Object> map = new HashMap<>();
		for(int i = 0;i<jArray.size();i++) {
			
			Object object = jArray.get(i);
			net.sf.json.JSONObject json = (net.sf.json.JSONObject)object;
			//获取每一个json的key/value-->map
			Set<String> keys = json.keySet();//每个json的所有key
			for(String key:keys) {
				Object value=json.getString(key);
				map.put(key, value);
			}
		}
		System.out.println(map);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

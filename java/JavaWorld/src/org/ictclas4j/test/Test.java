package org.ictclas4j.test;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.Segment;

public class Test {

	public static void main(String[] args) {

        Segment st = new Segment(1);  
        String line = "一块勤奋地漂亮的一块钱,/打造经济的航空母舰。ABCD.#$% Hello World!\n又一段文本123辆 ！3.0";  
        SegResult sr = st.split(line);  
        System.out.println(sr.getFinalResult()); 

		
	}
}

package org.ictclas4j.test;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.Segment;

public class Test {

	public static void main(String[] args) {

        Segment st = new Segment(1);  
        String line = "һ���ڷܵ�Ư����һ��Ǯ,/���쾭�õĺ���ĸ����ABCD.#$% Hello World!\n��һ���ı�123�� ��3.0";  
        SegResult sr = st.split(line);  
        System.out.println(sr.getFinalResult()); 

		
	}
}

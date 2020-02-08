package com.yanqun.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class MySimpleTagIterator extends SimpleTagSupport {
    private int num ;//3

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
/*
    <aa>
            xxx
    </aa>

 */
    @Override
    public void doTag() throws JspException, IOException {
        JspFragment jspFragment = getJspBody();
        for(int i=0;i<num ;i++){
            jspFragment.invoke(null);
        }
    }
}

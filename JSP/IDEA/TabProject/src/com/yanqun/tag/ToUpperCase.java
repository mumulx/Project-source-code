package com.yanqun.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ToUpperCase extends BodyTagSupport {

    //第一步，告诉程序 我要将原来的hello变成大写，即将 doStartTag()的返回值设置成 EVAL_BODY_BUFFER (此步骤，已经在父类默认实现)
    //第二步：获取，并修改


    @Override
    public int doEndTag() throws JspException {
        try {
            String content = getBodyContent().getString();//hello

            content = content.toUpperCase();//修改

            bodyContent.getEnclosingWriter().write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.doEndTag();//默认6，代表标签执行完毕后 是否执行其他的JSP元素
    }
}

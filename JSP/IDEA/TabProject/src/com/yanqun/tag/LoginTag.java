package com.yanqun.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class LoginTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        //判断 是否已经登录成功
        PageContext pageContext = (PageContext)getJspContext() ;
        HttpSession session = pageContext.getSession();
        String name = (String)session.getAttribute("name") ;
        if(name !=null){
            //执行一次标签体
            getJspBody().invoke(null) ;
        }
        //如果成功，执行标签体

        //如果失败，不执行
    }
}

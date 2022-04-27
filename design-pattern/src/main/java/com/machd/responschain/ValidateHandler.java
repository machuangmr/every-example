package com.machd.responschain;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * 请添加注释
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/27
 */
public class ValidateHandler extends Handler{
    @Override
    public void doHandler(Member member) {
        if (member.getPassword() == null
                || member.getUsername() == null){
            System.out.println("用户名或密码不能为空");
            return;
        }
        if ("machd".equals(member.getUsername())
                && "1234".equals(member.getPassword())) {
            System.out.println("账号密码正确");
        }
        next.doHandler(member);
    }
}

package com.machd.responschain;

/**
 * 请添加注释
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/27
 */
public class LoginHandler extends Handler{
    @Override
    public void doHandler(Member member) {
        member.setRole("管理员");
        next.doHandler(member);
    }

}

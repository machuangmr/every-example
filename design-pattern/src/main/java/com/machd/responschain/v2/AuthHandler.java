package com.machd.responschain.v2;

import com.machd.responschain.Member;

/**
 * 请添加注释
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/27
 */
public class AuthHandler extends Handler {
    @Override
    public void doHandler(Member member) {
        if (!"管理员".equals(member.getRole())) {
            System.out.println("权限错误");
        } else {
            System.out.println("欢迎你 " + member.getUsername() + "管理员~！");
        }
    }
}

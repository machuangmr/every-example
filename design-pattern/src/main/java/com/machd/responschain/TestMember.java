package com.machd.responschain;

/**
 * 请添加注释
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/27
 */
public class TestMember {
    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        memberService.login("machd", "1234");
    }
}

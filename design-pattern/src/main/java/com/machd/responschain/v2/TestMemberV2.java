package com.machd.responschain.v2;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class TestMemberV2 {
    public static void main(String[] args) {
        MemberServiceV2 memberServiceV2 = new MemberServiceV2();
        memberServiceV2.login("machd", "88888");
    }
}

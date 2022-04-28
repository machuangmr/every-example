package com.machd.responschain.v2;

import com.machd.responschain.Member;

/**
 * 利用建造者模式和指责链模式搭配使用，逻辑更清晰明了
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class MemberServiceV2 {
   public void login(String username, String password) {
       Handler.Builder builder = new Handler.Builder();
        builder.add(new ValidateHandler())
                .add(new LoginHandler())
                .add(new AuthHandler());
        builder.build().doHandler(new Member(username, password));

   }
}

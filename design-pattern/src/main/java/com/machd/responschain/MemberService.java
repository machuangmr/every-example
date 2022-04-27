package com.machd.responschain;

/**
 * 将每个任务，拆分为多个处理器，(解耦),然后将每一个任务穿起来，
 * 在以后的业务增加的时候，只需要增加对应的处理器即可。
 * @author machd
 * @version 3.0
 * @date 2022/4/27
 */
public class MemberService {
    public void login(String userName, String password) {
        ValidateHandler validateHandler = new ValidateHandler();
        LoginHandler loginHandler = new LoginHandler();
        AuthHandler authHandler = new AuthHandler();
        validateHandler.next = loginHandler;
        loginHandler.next = authHandler;
        validateHandler.doHandler(new Member(userName, password));
    }
}

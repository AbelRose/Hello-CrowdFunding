package com.matrix.crowdfunding.exception;

/**
 * @Author yihaosun
 * @Date 2/2/2021 20:30
 */

// RuntimeException spring默认对运行时异常进行事务会回滚  Exception 编译器异常 事务不会回滚
public class LoginException extends RuntimeException {
    public LoginException() {

    }
    public LoginException(String message) {
        super(message);
    }
}

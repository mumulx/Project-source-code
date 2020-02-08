package com.yanqun;

import com.yanqun.com.yanqun.service.impl.AccountServiceImpl;

public class Test {
    public static void main(String[] args) {
        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.transfer(1234,1111,100);
    }
}

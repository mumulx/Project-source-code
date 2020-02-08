package com.yanqun.dao;

import com.yanqun.Account;

import java.sql.SQLException;

public interface AccountDao {
    //根据卡号 查询账户
    Account queryAccountByCard(int cardId) throws SQLException;

    //修改账户
    void updateAccount(Account account) throws SQLException;
}

package com.yanqun.dao.impl;

import com.yanqun.Account;
import com.yanqun.dao.AccountDao;
import com.yanqun.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {
    @Override
    public Account queryAccountByCard(int cardId) throws SQLException {
        QueryRunner runner = new QueryRunner() ;
        Connection conn = JDBCUtil.getConnection() ;
        Account account = runner.query(conn, "select * from account where carid = ?", new BeanHandler<Account>(Account.class), cardId);//如果是手动提交：  QueryRunner()无参；query()update()必须传入connection
        return account;
    }
    //carid   余额
    @Override
    public void updateAccount(Account account) throws SQLException{
        QueryRunner runner = new QueryRunner() ;
        Connection conn = JDBCUtil.getConnection() ;
        runner.update(conn,"update account set balance = ? where carid = ?",new Object[]{account.getBalance(),account.getCardId()} );
    }
}

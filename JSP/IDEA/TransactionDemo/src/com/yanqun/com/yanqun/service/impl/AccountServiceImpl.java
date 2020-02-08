package com.yanqun.com.yanqun.service.impl;

import com.yanqun.Account;
import com.yanqun.com.yanqun.service.AccountService;
import com.yanqun.dao.impl.AccountDaoImpl;
import com.yanqun.util.JDBCUtil;

import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {
    @Override
    public void transfer(int fromCardId, int toCardId, int money) {
        AccountDaoImpl accountDao = new AccountDaoImpl();

        //开启事务
        try {
            JDBCUtil.beginTransaction();
            //各种DML操作
            //a. -1000   b.+1000
            //根据cardid查询相应的账户

            Account fromAccount = accountDao.queryAccountByCard(fromCardId) ;//付款方
            Account toAccount =  accountDao.queryAccountByCard(toCardId) ;//收款方

            //转账
            if(fromAccount.getBalance() >   money){
                //付款方 -1000
                int fromBalance = fromAccount.getBalance() - money;
                fromAccount.setBalance( fromBalance    );
                fromAccount.setCardId(fromCardId );
                accountDao.updateAccount(fromAccount);

                System.out.println(1/0);
                //收款方+1000
                int toBalance = toAccount.getBalance() + money ;
                toAccount.setBalance(toBalance);
                toAccount.setCardId(toCardId);
                accountDao.updateAccount(toAccount);

                System.out.println("转账成功!");




                //正常提交事务
                JDBCUtil.commitTransaction();
            }else{
                System.out.println("余额不足！");
            }

        } catch (Exception e) {
            try {
                JDBCUtil.rollbackTransaction();
                System.out.println("转账失败，回滚操作！");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try {
                JDBCUtil.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //结束事务（正常、失败）
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class AccountFactory {

    public AccountModel generateExistingAccount(String username, String password, AccountDAO dao)
            throws AccountNotFoundException, SQLException {
        AccountModel user = null;
        if (dao != null && username != null && password != null && password.length() >= 33) {
            AccountEntity userEntity = dao.checkLogin(username, password);
            if (userEntity != null) {
                switch (userEntity.getRole()) {
                    case "US":
                        user = new UserModel(userEntity);
                        break;
                    case "AD":
                        user = new AdminModel(userEntity);
                        break;
                }
            }
        } else {
            throw new AccountNotFoundException();
        }
        return user;
    }
    
    public AccountModel generateNewAccount(String username, String userId){
        AccountModel user = null;
        if (username != null && userId != null) {
            user = new UserModel(userId, username);
        }
        return user;
    }

    public AccountModel generateModelFromEntity(AccountEntity userEntity)
            throws AccountNotFoundException, SQLException {
        AccountModel user = null;
        if (userEntity != null) {
            switch (userEntity.getRole()) {
                case "US":
                    user = new UserModel(userEntity);
                    break;
                case "AD":
                    user = new AdminModel(userEntity);
                    break;
            }
        } else {
            throw new AccountNotFoundException();
        }
        return user;
    }

    public AccountEntity generateEntity(AccountModel account, String password, AccountDAO dao)
            throws AccountAlreadyExistsException, SQLException {
        AccountEntity entity = null;
        if (account != null && dao != null && password != null && password.length()>=33) {
            if (!dao.checkDuplicate(account.getUserId())) {
                entity = new AccountEntity(account.getUserId(), account.getUsername(), password, account.getRole());
            } else {
                throw new AccountAlreadyExistsException();
            }
        }
        return entity;
    }
}

package com.rosydawn.service;

import com.rosydawn.dao.LoginLogDao;
import com.rosydawn.dao.UserDao;
import com.rosydawn.domain.LoginLog;
import com.rosydawn.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	private UserDao userDao;
	private LoginLogDao loginLogDao;

    @Autowired
    public UserService(UserDao userDao, LoginLogDao loginLogDao) {
        this.userDao = userDao;
        this.loginLogDao = loginLogDao;
    }

    public boolean hasMatchUser(String userName, String password) {
		int matchCount =userDao.getMatchCount(userName, password);
		return matchCount > 0;
	}
	
	public User findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}

    /**
     * 更新数据库的操作需要加入事务控制。
     * @param user
     */
	@Transactional
    public void loginSuccess(User user) {
		user.setCredits( 5 + user.getCredits());
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
	}
}

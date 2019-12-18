package cn.itcast.itcaststore.service;
import java.sql.SQLException;
import java.util.Date;
import javax.security.auth.login.LoginException;
import cn.itcast.itcaststore.dao.UserDao;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.ActiveUserException;
import cn.itcast.itcaststore.exception.RegisterException;
import cn.itcast.itcaststore.utils.MailUtils;

public class UserService {
	private UserDao dao = new UserDao();
	// 注册操作
	public void register(User user) throws RegisterException {
		// 调用dao完成注册操作
		try {
			dao.addUser(user);
			String emailMsg = "感谢您注册匠心饰品店的会员，您已注册成功";
			MailUtils.sendMail(user.getEmail(), emailMsg);
			dao.activeUser(user.getActiveCode());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("注冊失败");
		}
	}
	// 激活用户
	public void activeUser(String activeCode) throws ActiveUserException {
		try {
			// 根据激活码查找用户
			User user = dao.findUserByActiveCode(activeCode);
			if (user == null) {
				throw new ActiveUserException("激活用户失败");
			}
			// 判断激活码是否过期 24小时内激活有效.
			// 1.得到注册时间
			Date registTime = user.getRegistTime();
			// 2.判断是否超时
			long time = System.currentTimeMillis() - registTime.getTime();
			if (time / 1000 / 60 / 60 > 24) {
				throw new ActiveUserException("激活码过期");
			}
			// 激活用户，就是修改用户的state状态
			dao.activeUser(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ActiveUserException("激活用户失败");
		}
	}
	// 登录操作
	public User login(String username, String password) throws LoginException {
		try {
			//根据登录时表单输入的用户名和密码，查找用户
			User user = dao.findUserByUsernameAndPassword(username, password);
			//如果找到，还需要确定用户是否为激活用户
			if (user != null) {
				// 只有是激活才能登录成功，否则提示“用户未激活”
				if (user.getState() == 1) {
					return user;
				}
				throw new LoginException("用户未激活");
			}
			throw new LoginException("用户名或密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("登录失败");
		}
	}
	public User update(String username, String password,String sex,String telephone) 
			throws LoginException {
		try {
			//根据登录时表单输入的用户名和密码，查找用户
			User user = dao.findUserByUsername(username);
			//如果找到，还需要确定用户是否为激活用户
			if (user != null) {
				
				dao.updateUser(username, password, sex, telephone);
				
			}
			throw new LoginException("用户名或密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("修改信息失败");
		}
	}
}

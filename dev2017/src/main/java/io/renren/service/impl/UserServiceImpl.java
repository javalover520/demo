package io.renren.service.impl;

import io.renren.dao.UserDao;
import io.renren.entity.UserEntity;
import io.renren.service.UserService;
import io.renren.utils.RRException;
import io.renren.validator.Assert;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public UserEntity queryObject(Long userId) {
		return userDao.queryObject(userId);
	}

	public List<UserEntity> queryList(Map<String, Object> map) {
		return userDao.queryList(map);
	}

	public int queryTotal(Map<String, Object> map) {
		return userDao.queryTotal(map);
	}

	public void save(String mobile, String password) {
		UserEntity user = new UserEntity();
		user.setMobile(mobile);
		user.setUsername(mobile);
		user.setPassword(DigestUtils.sha256Hex(password));
		user.setCreateTime(new Date());
		userDao.save(user);
	}

	public void update(UserEntity user) {
		userDao.update(user);
	}

	public void delete(Long userId) {
		userDao.delete(userId);
	}

	public void deleteBatch(Long[] userIds) {
		userDao.deleteBatch(userIds);
	}

	public UserEntity queryByMobile(String mobile) {
		return userDao.queryByMobile(mobile);
	}

	public long login(String mobile, String password) {
		UserEntity user = queryByMobile(mobile);
		Assert.isNull(user, "手机号或密码错误");

		// 密码错误
		if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
			throw new RRException("手机号或密码错误");
		}

		return user.getUserId();
	}
}

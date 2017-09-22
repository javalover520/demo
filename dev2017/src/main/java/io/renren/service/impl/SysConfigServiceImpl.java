package io.renren.service.impl;

import com.alibaba.fastjson.JSON;
import io.renren.dao.SysConfigDao;
import io.renren.entity.SysConfigEntity;
import io.renren.service.SysConfigService;
import io.renren.utils.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	private SysConfigDao sysConfigDao;
	
	public void save(SysConfigEntity config) {
		sysConfigDao.save(config);
	}

	public void update(SysConfigEntity config) {
		sysConfigDao.update(config);
	}

	public void updateValueByKey(String key, String value) {
		sysConfigDao.updateValueByKey(key, value);
	}

	public void deleteBatch(Long[] ids) {
		sysConfigDao.deleteBatch(ids);
	}

	public List<SysConfigEntity> queryList(Map<String, Object> map) {
		return sysConfigDao.queryList(map);
	}

	public int queryTotal(Map<String, Object> map) {
		return sysConfigDao.queryTotal(map);
	}

	public SysConfigEntity queryObject(Long id) {
		return sysConfigDao.queryObject(id);
	}

	public String getValue(String key, String defaultValue) {
		String value = sysConfigDao.queryByKey(key);
		if(StringUtils.isBlank(value)){
			return defaultValue;
		}
		return value;
	}
	
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key, null);
		if(StringUtils.isNotBlank(value)){
			return JSON.parseObject(value, clazz);
		}

		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RRException("获取参数失败");
		}
	}
}

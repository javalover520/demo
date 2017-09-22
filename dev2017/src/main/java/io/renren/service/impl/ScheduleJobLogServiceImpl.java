package io.renren.service.impl;

import io.renren.dao.ScheduleJobLogDao;
import io.renren.entity.ScheduleJobLogEntity;
import io.renren.service.ScheduleJobLogService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
	@Autowired
	private ScheduleJobLogDao scheduleJobLogDao;
	
	public ScheduleJobLogEntity queryObject(Long jobId) {
		return scheduleJobLogDao.queryObject(jobId);
	}

	public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
		return scheduleJobLogDao.queryList(map);
	}

	public int queryTotal(Map<String, Object> map) {
		return scheduleJobLogDao.queryTotal(map);
	}

	public void save(ScheduleJobLogEntity log) {
		scheduleJobLogDao.save(log);
	}

}

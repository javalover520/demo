package io.renren.dao;

import org.apache.ibatis.annotations.Mapper;

import io.renren.entity.ScheduleJobLogEntity;

/**
 * 定时任务日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:30:02
 */
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {
	
}

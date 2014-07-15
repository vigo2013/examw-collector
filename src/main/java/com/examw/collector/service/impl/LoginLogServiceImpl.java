package com.examw.collector.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.collector.dao.ILoginLogDao;
import com.examw.collector.domain.LoginLog;
import com.examw.collector.model.LoginLogInfo;
import com.examw.collector.service.ILoginLogService;
/**
 * 登录日志服务实现。
 * @author yangyong.
 * @since 2014-05-17.
 */
public class LoginLogServiceImpl extends BaseDataServiceImpl<LoginLog, LoginLogInfo> implements ILoginLogService {
	private ILoginLogDao loginLogDao;
	/**
	 * 设置登录日志数据接口。
	 * @param loginLogDao
	 * 数据接口。
	 */
	public void setLoginLogDao(ILoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}
	/*
	 * 添加登录日志。
	 * @see com.examw.netplatform.service.admin.ILoginLogService#addLog(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addLog(String account, String ip, String browser) {
		if(!StringUtils.isEmpty(account)){
			LoginLog data = new LoginLog();
			data.setId(UUID.randomUUID().toString());
			data.setAccount(account);
			data.setIp(ip);
			data.setBrowser(browser);
			data.setTime(new Date());
			this.loginLogDao.save(data);
		}
	}
	/*
	 * 查询数据。
	 * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<LoginLog> find(LoginLogInfo info) {
		return this.loginLogDao.findLoginLogs(info);
	}
	/*
	 * 类型转换。
	 * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected LoginLogInfo changeModel(LoginLog data) {
		if(data == null) return null;
		LoginLogInfo info = new LoginLogInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(LoginLogInfo info) {
		return this.loginLogDao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public LoginLogInfo update(LoginLogInfo info) {
		if(info == null) return null;
		boolean isAdded = false;
		LoginLog data = StringUtils.isEmpty(info.getId()) ? null : this.loginLogDao.load(LoginLog.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())){
				info.setId(UUID.randomUUID().toString());
			}
			data = new LoginLog();
		}
		BeanUtils.copyProperties(info, data);
		if(isAdded)this.loginLogDao.save(data);
		return info;
	}
	/*
	 * 删除数据。
	 * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			LoginLog data = this.loginLogDao.load(LoginLog.class, ids[i]);
			if(data != null) this.loginLogDao.delete(data);
		}
	}
}
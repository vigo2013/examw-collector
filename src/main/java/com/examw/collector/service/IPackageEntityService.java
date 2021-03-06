package com.examw.collector.service;

import java.util.List;

import com.examw.collector.model.PackInfo;

/**
 * 套餐服务接口
 * @author fengwei.
 * @since 2014年7月1日 上午9:58:16.
 */
public interface IPackageEntityService extends IBaseDataService<PackInfo>{
	/**
	 * 初始化导入数据
	 * @param info
	 */
	void init(PackInfo info);
	/**
	 * 更新集合
	 * @param info
	 */
	void update(List<PackInfo> packs);
}

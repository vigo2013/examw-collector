package com.examw.collector.dao;

import java.util.List;

import com.examw.collector.domain.local.CatalogEntity;
import com.examw.collector.model.local.CatalogEntityInfo;

/**
 * 
 * @author fengwei.
 * @since 2014年7月2日 上午9:58:17.
 */
public interface ICatalogEntityDao extends IBaseDao<CatalogEntity>{
	/**
	 * 查询分类数据。
	 * @return
	 * 结果数据。
	 */
	List<CatalogEntity> findCatalogs(CatalogEntityInfo info);

}
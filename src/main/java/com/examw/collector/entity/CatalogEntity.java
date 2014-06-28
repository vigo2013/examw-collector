package com.examw.collector.entity;

import java.io.Serializable;
import java.util.Set;

import com.examw.collector.domain.Subject;

/**
 * 
 * @author fengwei.
 * @since 2014年6月28日 下午5:07:08.
 */
public class CatalogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code,name;
	private Integer classTotal;
	private CatalogEntity parent;
	private Set<Subject> subjects;
	private Set<CatalogEntity> children;
	/**
	 * 获取上级课程类别。
	 * @return 上级课程类别。
	 */
	public CatalogEntity getParent() {
		return parent;
	}
	/**
	 * 设置上级课程类别。
	 * @param parent
	 * 上级课程类别。
	 */
	public void setParent(CatalogEntity parent) {
		this.parent = parent;
	}
	/**
	 * 获取课程类别代码。
	 * @return 课程类别代码。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置课程类别代码。
	 * @param code
	 * 课程类别代码。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取课程类别名称。
	 * @return 课程类别名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置课程类别名称。
	 * @param name
	 * 课程类别名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取班级数目。
	 * @return
	 * 班级数目。
	 */
	public Integer getClassTotal() {
		return classTotal;
	}
	/**
	 * 设置班级数目。
	 * @param classTotal
	 * 班级数目。
	 */
	public void setClassTotal(Integer classTotal) {
		this.classTotal = classTotal;
	}
	/**
	 * 获取科目集合。
	 * @return 科目集合。
	 */
	public Set<Subject> getSubjects() {
		return subjects;
	}
	/**
	 * 设置科目集合。
	 * @param subjects
	 * 科目集合。
	 */
	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	/**
	 * 获取子课程类别集合。
	 * @return 子课程类别集合。
	 */
	public Set<CatalogEntity> getChildren() {
		return children;
	}
	/**
	 * 设置子课程类别集合。
	 * @param children
	 * 子课程类别集合。
	 */
	public void setChildren(Set<CatalogEntity> children) {
		this.children = children;
	}
}
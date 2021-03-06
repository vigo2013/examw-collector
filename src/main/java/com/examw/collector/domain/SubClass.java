package com.examw.collector.domain;

import java.io.Serializable;
/**
 * 科目班级。
 * @author yangyong.
 * @since 2014-06-27.
 */
public class SubClass extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code,name,teacherName,demo,start,end,teacherId;
	private Boolean isLive,isShow;
	private Integer longDay,sourcePrice,salePrice,total;
	private AdVideo adVideo;
	private Subject subject;
	/**
	 * 获取所属科目。
	 * @return 所属科目。
	 */
	public Subject getSubject() {
		return subject;
	}
	/**
	 * 设置所属科目。
	 * @param subject
	 * 所属科目。
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	/**
	 * 获取班级代码。
	 * @return 班级代码。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置班级代码。
	 * @param code
	 * 班级代码。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取班级名称。
	 * @return 班级名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置班级名称。
	 * @param name
	 * 班级名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取老师名称。
	 * @return 老师名称。
	 */
	public String getTeacherName() {
		return teacherName;
	}
	/**
	 * 设置老师名称。
	 * @param teacherName
	 * 老师名称。
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	/**
	 * 获取试听讲的集合（多个以半角逗号分割）。
	 * @return 试听讲的集合（多个以半角逗号分割）。
	 */
	public String getDemo() {
		return demo;
	}
	/**
	 * 设置试听讲的集合（多个以半角逗号分割）。
	 * @param demo
	 * 试听讲的集合（多个以半角逗号分割）。
	 */
	public void setDemo(String demo) {
		this.demo = demo;
	}
	/**
	 * 获取是否直播。
	 * @return 是否直播。
	 */
	public Boolean getIsLive() {
		return isLive;
	}
	/**
	 * 设置是否直播。
	 * @param isLive
	 * 是否直播。
	 */
	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}
	/**
	 * 获取是否显示。
	 * @return 是否显示。
	 */
	public Boolean getIsShow() {
		return isShow;
	}
	/**
	 * 设置是否显示。
	 * @param isShow
	 * 是否显示。
	 */
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}
	/**
	 * 获取课程时长。
	 * @return 课程时长。
	 */
	public Integer getLongDay() {
		return longDay;
	}
	/**
	 * 设置课程时长。
	 * @param longDay
	 * 课程时长。
	 */
	public void setLongDay(Integer longDay) {
		this.longDay = longDay;
	}
	/**
	 * 获取班级的原售价。
	 * @return 班级的原售价。
	 */
	public Integer getSourcePrice() {
		return sourcePrice;
	}
	/**
	 * 设置班级的原售价。
	 * @param sourcePrice
	 * 班级的原售价。
	 */
	public void setSourcePrice(Integer sourcePrice) {
		this.sourcePrice = sourcePrice;
	}
	/**
	 * 获取班级销售价格。
	 * @return 班级销售价格。
	 */
	public Integer getSalePrice() {
		return salePrice;
	}
	/**
	 * 设置班级销售价格。
	 * @param salePrice
	 * 班级销售价格。
	 */
	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * 获取总讲数。
	 * @return 总讲数。
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * 设置总讲数。
	 * @param total
	 * 总讲数。
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * 获取开课日期。
	 * @return 开课日期。
	 */
	public String getStart() {
		return start;
	}
	/**
	 * 设置开课日期。
	 * @param start
	 * 开课日期。
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * 获取接课日期。
	 * @return 接课日期。
	 */
	public String getEnd() {
		return end;
	}
	/**
	 * 设置接课日期。
	 * @param end
	 * 接课日期。
	 */
	public void setEnd(String end) {
		this.end = end;
	}
	/**
	 * 获取广告视频。
	 * @return 广告视频。
	 */
	public AdVideo getAdVideo() {
		return adVideo;
	}
	/**
	 * 设置广告视频集合。
	 * @param adVideo
	 * 广告视频集合。
	 */
	public void setAdVideo(AdVideo adVideo) {
		this.adVideo = adVideo;
	}
	
	//所属考试
	private Catalog catalog;
	/**
	 * 获取 考试分类(小类)
	 * @return catalog
	 * 考试分类
	 */
	public Catalog getCatalog() {
		return catalog;
	}
	/**
	 * 设置 考试分类
	 * @param catalog
	 * 考试分类
	 */
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	/* 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((demo == null) ? 0 : demo.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((isLive == null) ? 0 : isLive.hashCode());
		result = prime * result + ((isShow == null) ? 0 : isShow.hashCode());
		result = prime * result + ((longDay == null) ? 0 : longDay.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((teacherId == null) ? 0 : teacherId.hashCode());
		result = prime * result
				+ ((salePrice == null) ? 0 : salePrice.hashCode());
		result = prime * result
				+ ((sourcePrice == null) ? 0 : sourcePrice.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result
				+ ((teacherName == null) ? 0 : teacherName.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}
	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubClass other = (SubClass) obj;
		boolean flag = true;	updateInfo = "";
		if (code == null) {
			if (other.code != null)
				flag = false;
		} else if (!code.equals(other.code))
		{
			updateInfo += ("代码变更:"+other.code+"-->"+code);
			flag = false;
		}
		if (demo == null) {
			if (other.demo != null)
				flag = false;
		} else if (!demo.equals(other.demo))
		{
			updateInfo += (" 试听项变更:"+other.demo+"-->"+demo);
			flag = false;
		}
		if (end == null) {
			if (other.end != null)
				flag = false;
		} else if (!end.equals(other.end))
		{
			updateInfo += (" 结课日期变更:"+other.end+"-->"+end);
			flag = false;
		}
		if (isLive == null) {
			if (other.isLive != null)
				flag = false;
		} else if (!isLive.equals(other.isLive))
		{
			updateInfo += (" 是否直播变更:"+other.isLive+"-->"+isLive);
			flag = false;
		}
		if (isShow == null) {
			if (other.isShow != null)
				flag = false;
		} else if (!isShow.equals(other.isShow))
		{
			updateInfo += (" 是否显示变更:"+other.isShow+"-->"+isShow);
			flag = false;
		}
		if (longDay == null) {
			if (other.longDay != null)
				flag = false;
		} else if (!longDay.equals(other.longDay))
		{
			updateInfo += (" 学习天数变更:"+other.longDay+"-->"+longDay);
			flag = false;
		}
		if (name == null) {
			if (other.name != null)
				flag = false;
		} else if (!name.equals(other.name))
		{
			updateInfo += (" 名字变更:"+other.name+"-->"+name);
			flag = false;
		}
		if (salePrice == null) {
			if (other.salePrice != null)
				flag = false;
		} else if (!salePrice.equals(other.salePrice))
		{
			updateInfo += (" 优惠价变更:"+other.salePrice+"-->"+salePrice);
			flag = false;
		}
		if (sourcePrice == null) {
			if (other.sourcePrice != null)
				flag = false;
		} else if (!sourcePrice.equals(other.sourcePrice))
		{
			updateInfo += (" 优惠价变更:"+other.sourcePrice+"-->"+sourcePrice);
			flag = false;
		}
		if (start == null) {
			if (other.start != null)
				flag = false;
		} else if (!start.equals(other.start))
		{
			updateInfo += (" 开课日期变更:"+other.start+"-->"+start);
			flag = false;
		}
		if (subject == null) {
			if (other.subject != null)
				flag = false;
		} else if (!subject.getCode().equals(other.subject.getCode()))
		{
			updateInfo += (" 所属科目变更:"+other.subject.getName()+other.subject.getCode()+"-->"+subject.getName()+subject.getCode());
			flag = false;
		}
		if (teacherId == null) {
			if (other.teacherId != null)
				flag = false;
		} else if (!teacherId.equals(other.teacherId))
		{
			updateInfo += (" 教师ID变更:"+other.teacherId+"-->"+teacherId);
			flag = false;
		}
		if (teacherName == null) {
			if (other.teacherName != null)
				flag = false;
		} else if (!teacherName.equals(other.teacherName))
		{
			updateInfo += (" 教师名字变更:"+other.teacherName+"-->"+teacherName);
			flag = false;
		}
		if (total == null) {
			if (other.total != null)
				flag = false;
		} else if (!total.equals(other.total))
		{
			updateInfo += (" 总讲数变更:"+other.total+"-->"+total);
			flag = false;
		}
		return flag;
	}
	/**
	 * 获取 老师ID
	 * @return teacherId
	 * 
	 */
	public String getTeacherId() {
		return teacherId;
	}
	/**
	 * 设置 老师ID
	 * @param teacherId
	 * 
	 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	@Override
	public String toString() {
		String s = "";
		if(catalog!=null)
			s += ("所属类别:"+catalog.getName()+"("+catalog.getCode()+");");
		if(subject!=null)
			s += ("所属科目:"+subject.getName()+"("+subject.getCode()+");");
		s += ("原价:"+sourcePrice+";优惠价:"+salePrice+";老师:"+teacherName);
		return s;
	}
}
package com.examw.collector.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.examw.collector.dao.IAdVideoDao;
import com.examw.collector.dao.ICatalogEntityDao;
import com.examw.collector.dao.IGradeEntityDao;
import com.examw.collector.dao.IListenEntityDao;
import com.examw.collector.dao.IPackDao;
import com.examw.collector.dao.IPackageEntityDao;
import com.examw.collector.dao.ISubClassDao;
import com.examw.collector.dao.ISubjectDao;
import com.examw.collector.dao.ISubjectEntityDao;
import com.examw.collector.dao.ITeacherEntityDao;
import com.examw.collector.domain.Pack;
import com.examw.collector.domain.Relate;
import com.examw.collector.domain.SubClass;
import com.examw.collector.domain.Subject;
import com.examw.collector.domain.local.CatalogEntity;
import com.examw.collector.domain.local.GradeEntity;
import com.examw.collector.domain.local.ListenEntity;
import com.examw.collector.domain.local.PackageEntity;
import com.examw.collector.domain.local.SubjectEntity;
import com.examw.collector.domain.local.TeacherEntity;
import com.examw.collector.model.SubjectInfo;
import com.examw.collector.service.IDataServer;
import com.examw.collector.service.IImportDataService;

/**
 * 整体数据导入服务实现类
 * 
 * @author fengwei.
 * @since 2014年7月10日 上午9:51:44.
 */
public class ImportDataServiceImpl implements IImportDataService {
	private static Logger logger = Logger
			.getLogger(ImportDataServiceImpl.class);
	private ISubjectDao subjectDao;
	private ISubClassDao subClassDao;
	private IPackDao packDao;
	private ISubjectEntityDao subjectEntityDao;
	private IGradeEntityDao gradeEntityDao;
	private IListenEntityDao listenEntityDao;
	private IPackageEntityDao packageEntityDao;
	private IDataServer dataServer;
	private ITeacherEntityDao teacherEntityDao;
	private IAdVideoDao adVideoDao;
	
	private ICatalogEntityDao catalogEntityDao;
	/**
	 * 设置 科目副本数据接口
	 * @param subjectDao
	 * 
	 */
	public void setSubjectDao(ISubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	/**
	 * 设置 班级副本数据接口
	 * @param subClassDao
	 * 
	 */
	public void setSubClassDao(ISubClassDao subClassDao) {
		this.subClassDao = subClassDao;
	}

	/**
	 * 设置 套餐副本数据接口
	 * @param packDao
	 * 
	 */
	public void setPackDao(IPackDao packDao) {
		this.packDao = packDao;
	}

	/**
	 * 设置 本地科目数据接口
	 * @param subjectEntityDao
	 * 
	 */
	public void setSubjectEntityDao(ISubjectEntityDao subjectEntityDao) {
		this.subjectEntityDao = subjectEntityDao;
	}

	/**
	 * 设置 本地班级数据接口
	 * @param gradeEntityDao
	 * 
	 */
	public void setGradeEntityDao(IGradeEntityDao gradeEntityDao) {
		this.gradeEntityDao = gradeEntityDao;
	}

	/**
	 * 设置 本地课节数据接口
	 * @param listenEntityDao
	 * 
	 */
	public void setListenEntityDao(IListenEntityDao listenEntityDao) {
		this.listenEntityDao = listenEntityDao;
	}

	/**
	 * 设置 本地套餐数据接口
	 * @param packageEntityDao
	 * 
	 */
	public void setPackageEntityDao(IPackageEntityDao packageEntityDao) {
		this.packageEntityDao = packageEntityDao;
	}

	/**
	 * 设置 远程数据获取接口
	 * @param dataServer
	 * 
	 */
	public void setDataServer(IDataServer dataServer) {
		this.dataServer = dataServer;
	}
	
	/**
	 * 设置 本地老师数据接口
	 * @param teacherDao
	 * 
	 */
	public void setTeacherEntityDao(ITeacherEntityDao teacherEntityDao) {
		this.teacherEntityDao = teacherEntityDao;
	}
	
	/**
	 * 设置 广告地址
	 * @param adVideoDao
	 * 
	 */
	public void setAdVideoDao(IAdVideoDao adVideoDao) {
		this.adVideoDao = adVideoDao;
	}
	
	/**
	 * 设置 分类数据接口
	 * @param catalogEntityDao
	 * 
	 */
	public void setCatalogEntityDao(ICatalogEntityDao catalogEntityDao) {
		this.catalogEntityDao = catalogEntityDao;
	}

	@Override
	public void init(String[] catalogIds) {
		if (catalogIds == null || catalogIds.length == 0)
			return;
		// 本地副本里现在已经有了分类和科目的数据
		logger.info("插入本地数据");
		for (String id : catalogIds) {
			if (StringUtils.isEmpty(id))
				continue;
			// 导入本地科目数据
			importLocalSubject(id);
			// 导入班级以及课节数据
			importLocalGrade(id);
			// 导入套餐数据
			importLocalPackage(id);
		}
	}

	@Override
	public void initAll() {
		List<CatalogEntity> list = this.catalogEntityDao.findAllWithCode();
		for(CatalogEntity c : list){
			if(c==null) continue;
			// 导入本地科目数据
			importLocalSubject(c.getCode());
			// 导入班级以及课节数据
			importLocalGrade(c.getCode());
			// 导入套餐数据
			importLocalPackage(c.getCode());
		}
	}
	/**
	 * 插入科目数据
	 * 
	 * @param id
	 */
	private void importLocalSubject(final String id) {
		List<Subject> subjects = this.subjectDao
				.findSubjects(new SubjectInfo() {
					private static final long serialVersionUID = 1L;

					@Override
					public String getCatalogId() {
						return id;
					}
				});
		// 转型并插入
		if (subjects == null || subjects.size() == 0)
			return;
		for (Subject s : subjects) {
			SubjectEntity data = this.changeSubjectModel(s);
			if (data != null)
				this.subjectEntityDao.saveOrUpdate(data);
		}
	}

	/**
	 * 科目数据转换
	 * 
	 * @param info
	 * @return
	 */
	private SubjectEntity changeSubjectModel(Subject info) {
		if (info == null)
			return null;
		SubjectEntity data = new SubjectEntity();
		data.setId(info.getCode());
		data.setName(info.getName());
		if (info.getCatalog() == null)
			return null;
		CatalogEntity catalog = new CatalogEntity();
		catalog.setCode(info.getCatalog().getCode());
		data.setCatalogEntity(catalog);
		return data;

	}

	/**
	 * 插入班级数据
	 * 
	 * @param id
	 */
	private void importLocalGrade(String id) {
		// 从远程获取数据
		List<SubClass> list = this.dataServer.loadClasses(id, null);
		// 整个科目下所有的数据
		if (list == null || list.size() == 0)
			return;
		for (SubClass s : list) {
			if (s.getSubject() != null) {
				Subject subject = this.subjectDao.load(Subject.class, s
						.getSubject().getCode());
				if (subject != null) {
					if(s.getAdVideo()!=null){
						this.adVideoDao.saveOrUpdate(s.getAdVideo());
					}
					s.setSubject(subject);
					this.subClassDao.saveOrUpdate(s);
					GradeEntity grade = changeGradeModel(s);
					if (grade != null) {
						this.gradeEntityDao.saveOrUpdate(grade);
						// 保存课节数据,只保存本地实际的课节数据,副本不再存了
						List<Relate> relateList = this.dataServer
								.loadRelates(grade.getId());
						if (relateList != null && relateList.size() > 0) {
							for (Relate r : relateList) {
								ListenEntity l = this.changeListenModel(r);
								if (l != null)
									this.listenEntityDao.saveOrUpdate(l);
							}
						}
					}
				}
			}
		}
		//导入老师数据
		importTeacherData(id,list);
	}
	private void importTeacherData(String calalogId,List<SubClass> subClassList)
	{
		Map<String,String> teacherMap = new HashMap<String,String>();
		if(subClassList!=null && subClassList.size()>0){
			for(SubClass sc: subClassList){
				if(!StringUtils.isEmpty(sc.getTeacherId())){
					teacherMap.put(sc.getTeacherId(), sc.getTeacherName());
				}
			}
		}
		if(teacherMap.size()>0){
			Set<String> keys = teacherMap.keySet();
			List<TeacherEntity> list = new ArrayList<TeacherEntity>();
			for(String id :keys){
				TeacherEntity t = this.teacherEntityDao.load(TeacherEntity.class, id); //老师id已经加过e了
				if(t!=null){
					if(StringUtils.isEmpty(t.getCatalogId())){
						t.setCatalogId(calalogId);
						System.out.println(t.toString());
						list.add(t);
					}else if(!t.getCatalogId().contains(calalogId)){
						t.setCatalogId(t.getCatalogId()+","+calalogId);
						System.out.println(t.toString());
						list.add(t);
					}
				}else{
					t = this.dataServer.loadTeacher(id);
					if(t!=null)
					{
						t.setCatalogId(calalogId);
						System.out.println(t.toString());
						list.add(t);
					}
				}
			}
			if(list.size()>0)
			{
				this.teacherEntityDao.batchSave(list);
			}
		}
	}

	private GradeEntity changeGradeModel(SubClass info) {
		if (info == null)
			return null;
		GradeEntity data = new GradeEntity();
		BeanUtils.copyProperties(info, data);
		data.setId(info.getCode());
		SubjectEntity subject = new SubjectEntity();
		subject.setId(info.getSubject().getCode());
		data.setSubjectEntity(subject);
		return data;
	}

	private ListenEntity changeListenModel(Relate relate) {
		if (relate == null)
			return null;
		ListenEntity data = new ListenEntity();
		BeanUtils.copyProperties(relate, data);
		//==============加e处理=========================
		data.setId(DataServerImpl.ID_PREFIX+relate.getNum().toString());
		//==============加e处理=========================
		if (relate.getSubclass() == null)
			return null;
		GradeEntity grade = new GradeEntity();
		grade.setId(relate.getSubclass().getCode());
		data.setGrade(grade);
		return data;
	}

	/**
	 * 插入套餐数据
	 * 
	 * @param id
	 */
	private void importLocalPackage(String id) {
		List<Pack> list = this.dataServer.loadPacks(id, null);
		if(list == null ||list.size()==0) return;
		for(Pack p:list){
			if(p==null)	continue;
			if(p.getSubject()!=null){
				Subject subject = this.subjectDao.load(Subject.class, p
						.getSubject().getCode());
				if(subject!=null){
					p.setSubject(subject);
				}else{
					continue;
				}
			}
			this.packDao.saveOrUpdate(p);
			PackageEntity data = this.changePackageModel(p);
			if(data!=null)
				this.packageEntityDao.saveOrUpdate(data);
		}
	}
	
	private PackageEntity changePackageModel(Pack info) {
		if(info == null) return null;
		PackageEntity data = new PackageEntity();
		BeanUtils.copyProperties(info, data);
		data.setId(info.getCode());
		if(info.getCatalog()==null) return null;
		CatalogEntity catalog = new CatalogEntity();
		catalog.setCode(info.getCatalog().getCode());
		data.setCatalogEntity(catalog);
		if(info.getSubject()!=null){
			SubjectEntity subject = new SubjectEntity();
			subject.setId(info.getSubject().getCode());
			data.setSubjectEntity(subject);
		}
		return data;
	}
	
	public String getIds(){
		StringBuilder codes = new StringBuilder();
		List<CatalogEntity> list = this.catalogEntityDao.findAllWithCode();
		System.out.println(list.size());
		for(CatalogEntity c : list){
			if(c==null) continue;
			codes.append(c.getId()).append(",");
		}
		if(codes.length()>0)
			return codes.substring(0,codes.length()-1);
		return "";
	}
}

package com.examw.collector.controllers.security;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.collector.model.OperateLogInfo;
import com.examw.collector.service.IOperateLogService;
import com.examw.model.DataGrid;
import com.examw.model.Json;

/**
 * 操作日志控制器
 * @author fengwei.
 * @since 2014年7月15日 上午9:12:55.
 */
@Controller
@RequestMapping(value = "/admin/security/operatelog")
public class OperateLogController {
	private static Logger logger = Logger.getLogger(OperateLogController.class);
	@Resource
	private IOperateLogService operateLogService;
	/**
	 * 获取列表页面。
	 * @return
	 */
	@RequestMapping(value={"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		return "security/operatelog_list";
	}
	/**
	 * 查询数据。
	 * @return
	 */
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<OperateLogInfo> datagrid(OperateLogInfo info){
		return this.operateLogService.datagrid(info);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(Integer type,String id, Model model){
		return "security/operate_detail_"+type;
	}
	/**
	 * 删除数据。
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		Json result = new Json();
		try {
			this.operateLogService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
}

package com.ischoolbar.programmer.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Asset;
import com.ischoolbar.programmer.entity.admin.DormitoryEntity;
import com.ischoolbar.programmer.entity.admin.RepairsEntity;
import com.ischoolbar.programmer.entity.admin.RepairsVO;
import com.ischoolbar.programmer.entity.admin.StudentEntity;
import com.ischoolbar.programmer.entity.admin.TeacherEntity;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.AssetService;
import com.ischoolbar.programmer.service.admin.DormitoryService;
import com.ischoolbar.programmer.service.admin.RepairsService;

@RequestMapping("/admin/fix")
@Controller
public class RepairsController {
	@Autowired
	AssetService assetService;
	@Autowired
	RepairsService repairsService;
	@Autowired
	DormitoryService dormitoryService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("repairs/list");
		return model;
	}

	/**
	 * 修改功能
	 * 
	 * @param studentEntity
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(RepairsEntity repairsEntity) {
		Map<String, String> ret = new HashMap<String, String>();
		if (repairsEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的报修信息！");
			return ret;
		}
		if (repairsService.edit(repairsEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "报修信息修改失败，请联系管理员！");
			return ret;
		}
		Asset as = new Asset();
		as.setAssetid(repairsEntity.getAssetid());
		as.setRepairstatus(0);
		assetService.edit(as);
		ret.put("type", "success");
		ret.put("msg", "报修信息修改成功！");
		return ret;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(RepairsEntity repairsEntity, HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		if (repairsEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的报修信息！");
			return ret;
		}

		if (StringUtils.isEmpty(repairsEntity.getBrank())) {
			ret.put("type", "error");
			ret.put("msg", "请填写报修详情！");
			return ret;
		}
		if (StringUtils.isEmpty(String.valueOf(repairsEntity.getAssetid()))) {
			ret.put("type", "error");
			ret.put("msg", "请填写报修财产！");
			return ret;
		}
		Integer myrole = (Integer) request.getSession().getAttribute("myrole");
		if (myrole != 4) {// 学生
			ret.put("type", "error");
			ret.put("msg", "无权限");
			return ret;
		}
		StudentEntity stu = (StudentEntity) request.getSession().getAttribute("admin");// 获取学生
		repairsEntity.setCreatetime(new Date());
		repairsEntity.setStatus(1);// 1正在维护
		repairsEntity.setStuid(stu.getId());
		Asset as = new Asset();
		as.setAssetid(repairsEntity.getAssetid());
		as.setRepairstatus(1);
		assetService.edit(as);
		if (repairsService.add(repairsEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "报修信息添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "报修信息添加成功！");
		return ret;
	}

	/**
	 * 查询
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMenuList(Page page, HttpServletRequest request) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Integer myrole = (Integer) request.getSession().getAttribute("myrole");
		switch (myrole) {// 1管理员 2老师 4 学生
		case 4:
			StudentEntity stu = (StudentEntity) request.getSession().getAttribute("admin");// 获取学生
			queryMap.put("stuid", stu.getId());
			break;
		case 2:
			Map<String, Object> teacheMap = new HashMap<String, Object>();
			TeacherEntity teacherEntity = (TeacherEntity) request.getSession().getAttribute("admin");// 获取老师
			teacheMap.put("teacherid", teacherEntity.getId());
			List<DormitoryEntity> findALL = dormitoryService.findALL(teacheMap);// 获取负责的寝室
			Integer dormitoryid = findALL.get(0).getDormitoryid();
			queryMap.put("bedchamberid", dormitoryid);
			break;

		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		List<RepairsVO> findList = repairsService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", repairsService.getTotal(queryMap));
		return ret;
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String ids) {
		Map<String, String> ret = new HashMap<String, String>();
		if (StringUtils.isEmpty(ids)) {
			ret.put("type", "error");
			ret.put("msg", "选择要删除的数据！");
			return ret;
		}
		if (ids.contains(",")) {
			ids = ids.substring(0, ids.length() - 1);
		}
		if (repairsService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "报修信息删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "报修信息删除成功！");
		return ret;
	}

}

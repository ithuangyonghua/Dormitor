package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Asset;
import com.ischoolbar.programmer.entity.admin.RegistrationEntity;
import com.ischoolbar.programmer.entity.admin.TeacherEntity;
import com.ischoolbar.programmer.entity.admin.User;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.RegistrationService;
import com.ischoolbar.programmer.util.AjaxJson;

@RequestMapping("/admin/check")
@Controller
public class CheckController {
	@Autowired
	RegistrationService registrationService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("registration/list");
		return model;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson delete(String ids) {
		AjaxJson j = new AjaxJson();
		if (StringUtils.isEmpty(ids)) {
			j.setMsg("删除失败！");
			j.setSuccess(false);
			return j;
		}
		if (ids.contains(",")) {
			ids = ids.substring(0, ids.length() - 1);
		}
		if (registrationService.delete(ids) <= 0) {
			j.setMsg("盘点信息删除失败，请联系管理员！");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("盘点信息删除成功！");
		j.setSuccess(true);
		return j;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson edit(RegistrationEntity registrationEntity) {
		AjaxJson j = new AjaxJson();
		if (registrationEntity == null) {
			j.setMsg("请填写正确的盘点信息！");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(registrationEntity.getRcreatetime().toLocaleString())) {
			j.setMsg("请填写盘点时间");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(String.valueOf(registrationEntity.getRstatus()))) {
			j.setMsg("请填写设施状态");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("保存成功！");
		j.setSuccess(true);
		try {
			registrationService.updateByExampleSelective(registrationEntity);
		} catch (Exception e) {
			j.setMsg("保存失败！");
			j.setSuccess(false);
		}
		return j;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name = "username", required = false, defaultValue = "") String username,
			@RequestParam(name = "name", required = false, defaultValue = "") String name, HttpServletRequest request) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Integer myrole = (Integer) request.getSession().getAttribute("myrole");
		switch (myrole) {// 1管理员 2老师 4 学生
		case 2:
			TeacherEntity teacherEntity = (TeacherEntity) request.getSession().getAttribute("admin");// 获取老师
			queryMap.put("teaid", teacherEntity.getId());
			break;
		}
		queryMap.put("username", username);
		queryMap.put("name", name);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", registrationService.findList(queryMap));
		ret.put("total", registrationService.getTotal(queryMap));
		return ret;
	}

	/**
	 * 
	 * @param dormitoryVO
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson add(RegistrationEntity registrationEntity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (registrationEntity == null) {
			j.setMsg("请填写正确的盘点信息！");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(String.valueOf(registrationEntity.getAssetid()))) {
			j.setMsg("请填写财产名");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(registrationEntity.getRcreatetime().toGMTString())) {
			j.setMsg("请填写登记时间");
			j.setSuccess(false);
			return j;
		}
		Integer myrole = (Integer) request.getSession().getAttribute("myrole");
		switch (myrole) {// 1管理员 2老师 4 学生
		case 2: // 老师
			TeacherEntity teacherEntity = (TeacherEntity) request.getSession().getAttribute("admin");// 获取老师
			registrationEntity.setTeaid(teacherEntity.getId());
			break;
		case 1: // 管理员
			/*
			 * User user = (User) request.getSession().getAttribute("admin");
			 * registrationEntity.setTeaid(user.getUsername()); break;
			 */
			j.setMsg("无权限");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("保存成功！");
		j.setSuccess(true);
		try {
			registrationService.insertSelective(registrationEntity);
		} catch (Exception e) {
			j.setMsg("保存失败！");
			j.setSuccess(false);
		}
		return j;
	}
}

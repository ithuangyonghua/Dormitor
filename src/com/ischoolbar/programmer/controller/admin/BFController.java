package com.ischoolbar.programmer.controller.admin;

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
import com.ischoolbar.programmer.entity.admin.AssetVO;
import com.ischoolbar.programmer.entity.admin.DormitoryEntity;
import com.ischoolbar.programmer.entity.admin.TeacherEntity;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.AssetService;
import com.ischoolbar.programmer.service.admin.BFService;
import com.ischoolbar.programmer.service.admin.DormitoryService;
import com.ischoolbar.programmer.util.AjaxJson;

@RequestMapping("/admin/bf")
@Controller
public class BFController {
	@Autowired
	BFService fFService;
	@Autowired
	AssetService assetService;
	@Autowired
	DormitoryService dormitoryService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("bf/list");
		return model;
	}

	/**
	 * 查询
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMenuList(Page page,HttpServletRequest request) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Integer myrole = (Integer) request.getSession().getAttribute("myrole");
		switch (myrole) {// 1管理员 2老师 4 学生
		case 2:
			Map<String, Object> teacheMap = new HashMap<String, Object>();
			TeacherEntity teacherEntity = (TeacherEntity) request.getSession().getAttribute("admin");// 获取老师
			teacheMap.put("teacherid", teacherEntity.getId());
			List<DormitoryEntity> findALL = dormitoryService.findALL(teacheMap);//获取负责的寝室
			Integer dormitoryid = findALL.get(0).getDormitoryid();
			queryMap.put("bedchamberid", dormitoryid);
			break;
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		List<AssetVO> findList = fFService.findBFList(queryMap);
		ret.put("rows", findList);
		ret.put("total", fFService.getBFTotal(queryMap));
		return ret;
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson add(Asset asset) {
		AjaxJson j = new AjaxJson();
		if (asset == null) {
			j.setMsg("请填写正确的报废信息！");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(String.valueOf(asset.getAssetid()))) {
			j.setMsg("请填写报废财产");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(asset.getScraptime().toLocaleString())) {
			j.setMsg("请填写报废时间");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(asset.getScrapto())) {
			j.setMsg("请填写报废去向");
			j.setSuccess(false);
			return j;
		}
		asset.setRegistrationstatus(-1);
		j.setMsg("保存成功！");
		j.setSuccess(true);
		try {
			assetService.edit(asset);
		} catch (Exception e) {
			j.setMsg("保存失败！");
			j.setSuccess(false);
		}

		return j;
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

		j.setMsg("删除成功！");
		j.setSuccess(true);
		try {
			Asset asset = new Asset();
			asset.setRegistrationstatus(1);
			asset.setAssetid(Integer.valueOf(ids));
			assetService.edit(asset);
		} catch (Exception e) {
			j.setMsg("删除失败！");
			j.setSuccess(false);
		}
		return j;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson edit(Asset asset) {
		AjaxJson j = new AjaxJson();
		if (asset == null) {
			j.setMsg("请填写正确的报废信息！");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(String.valueOf(asset.getAssetid()))) {
			j.setMsg("请填写报废财产");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(asset.getScraptime().toLocaleString())) {
			j.setMsg("请填写报废时间");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(asset.getScrapto())) {
			j.setMsg("请填写报废去向");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("修改成功！");
		j.setSuccess(true);
		try {
			assetService.edit(asset);
		} catch (Exception e) {
			j.setMsg("修改失败！");
			j.setSuccess(false);
		}
		return j;
	}

	/*
	 * @RequestMapping(value = "/delete", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public AjaxJson delete(String ids) { AjaxJson j = new
	 * AjaxJson(); if (StringUtils.isEmpty(ids)) { j.setMsg("删除失败！");
	 * j.setSuccess(false); return j; } if (ids.contains(",")) { ids =
	 * ids.substring(0, ids.length() - 1); } if (assetService.delete(ids) <= 0)
	 * { j.setMsg("财产删除失败，请联系管理员！"); j.setSuccess(false); return j; }
	 * j.setMsg("财产删除成功！"); j.setSuccess(true); return j; }
	 * 
	 * @RequestMapping(value = "/edit", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public AjaxJson edit(Asset asset) { AjaxJson j = new
	 * AjaxJson(); if (asset == null) { j.setMsg("请填写正确的财产信息！");
	 * j.setSuccess(false); return j; } if
	 * (StringUtils.isEmpty(asset.getAssetname())) { j.setMsg("请填写财产名");
	 * j.setSuccess(false); return j; } j.setMsg("保存成功！"); j.setSuccess(true);
	 * try{ assetService.edit(asset); }catch(Exception e){ j.setMsg("保存失败！");
	 * j.setSuccess(false); } return j; }
	 */
}

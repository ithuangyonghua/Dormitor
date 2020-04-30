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
	 * ��ѯ
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
		switch (myrole) {// 1����Ա 2��ʦ 4 ѧ��
		case 2:
			Map<String, Object> teacheMap = new HashMap<String, Object>();
			TeacherEntity teacherEntity = (TeacherEntity) request.getSession().getAttribute("admin");// ��ȡ��ʦ
			teacheMap.put("teacherid", teacherEntity.getId());
			List<DormitoryEntity> findALL = dormitoryService.findALL(teacheMap);//��ȡ���������
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
			j.setMsg("����д��ȷ�ı�����Ϣ��");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(String.valueOf(asset.getAssetid()))) {
			j.setMsg("����д���ϲƲ�");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(asset.getScraptime().toLocaleString())) {
			j.setMsg("����д����ʱ��");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(asset.getScrapto())) {
			j.setMsg("����д����ȥ��");
			j.setSuccess(false);
			return j;
		}
		asset.setRegistrationstatus(-1);
		j.setMsg("����ɹ���");
		j.setSuccess(true);
		try {
			assetService.edit(asset);
		} catch (Exception e) {
			j.setMsg("����ʧ�ܣ�");
			j.setSuccess(false);
		}

		return j;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson delete(String ids) {
		AjaxJson j = new AjaxJson();
		if (StringUtils.isEmpty(ids)) {
			j.setMsg("ɾ��ʧ�ܣ�");
			j.setSuccess(false);
			return j;
		}
		if (ids.contains(",")) {
			ids = ids.substring(0, ids.length() - 1);
		}

		j.setMsg("ɾ���ɹ���");
		j.setSuccess(true);
		try {
			Asset asset = new Asset();
			asset.setRegistrationstatus(1);
			asset.setAssetid(Integer.valueOf(ids));
			assetService.edit(asset);
		} catch (Exception e) {
			j.setMsg("ɾ��ʧ�ܣ�");
			j.setSuccess(false);
		}
		return j;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson edit(Asset asset) {
		AjaxJson j = new AjaxJson();
		if (asset == null) {
			j.setMsg("����д��ȷ�ı�����Ϣ��");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(String.valueOf(asset.getAssetid()))) {
			j.setMsg("����д���ϲƲ�");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(asset.getScraptime().toLocaleString())) {
			j.setMsg("����д����ʱ��");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(asset.getScrapto())) {
			j.setMsg("����д����ȥ��");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("�޸ĳɹ���");
		j.setSuccess(true);
		try {
			assetService.edit(asset);
		} catch (Exception e) {
			j.setMsg("�޸�ʧ�ܣ�");
			j.setSuccess(false);
		}
		return j;
	}

	/*
	 * @RequestMapping(value = "/delete", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public AjaxJson delete(String ids) { AjaxJson j = new
	 * AjaxJson(); if (StringUtils.isEmpty(ids)) { j.setMsg("ɾ��ʧ�ܣ�");
	 * j.setSuccess(false); return j; } if (ids.contains(",")) { ids =
	 * ids.substring(0, ids.length() - 1); } if (assetService.delete(ids) <= 0)
	 * { j.setMsg("�Ʋ�ɾ��ʧ�ܣ�����ϵ����Ա��"); j.setSuccess(false); return j; }
	 * j.setMsg("�Ʋ�ɾ���ɹ���"); j.setSuccess(true); return j; }
	 * 
	 * @RequestMapping(value = "/edit", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public AjaxJson edit(Asset asset) { AjaxJson j = new
	 * AjaxJson(); if (asset == null) { j.setMsg("����д��ȷ�ĲƲ���Ϣ��");
	 * j.setSuccess(false); return j; } if
	 * (StringUtils.isEmpty(asset.getAssetname())) { j.setMsg("����д�Ʋ���");
	 * j.setSuccess(false); return j; } j.setMsg("����ɹ���"); j.setSuccess(true);
	 * try{ assetService.edit(asset); }catch(Exception e){ j.setMsg("����ʧ�ܣ�");
	 * j.setSuccess(false); } return j; }
	 */
}

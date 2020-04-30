package com.ischoolbar.programmer.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.AssetCategoryEntity;
import com.ischoolbar.programmer.entity.admin.AssetPropertyListEntity;
import com.ischoolbar.programmer.entity.admin.AssetPropertyListVO;
import com.ischoolbar.programmer.entity.admin.StudentEntity;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.AssetPropertyListService;

@RequestMapping("/admin/assetpropertylist")
@Controller
public class AssetPeopertyListController {
	@Autowired
	AssetPropertyListService assetPropertyListService;
	
	
	@RequestMapping(value = "/getperpall/{ctegoryid}", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getStu1(@PathVariable(value="ctegoryid") Integer ctegoryid,HttpServletResponse response) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("ctegoryid",ctegoryid);
		List<AssetPropertyListEntity> aeestByCate = assetPropertyListService.getAeestByCate(queryMap);
		System.out.println("��ѯ�������" + aeestByCate);
		// list���map��map���kvֵ��json����namelistȡ��Ҫ���ֶ�
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (AssetPropertyListEntity category : aeestByCate) {
			Map<String, Object> map = new HashMap<String, Object>();
			// ֻȡclassid��ClassName���ɡ�
			String pn = category.getPname();
			String ctid = String.valueOf(category.getCtegoryid());
			// map��ż�ֵ��
			map.put("pn", pn);
			map.put("ctid", pn);
			// list���map
			list.add(map);
			System.out.println("json��ʽ:" + list); // ��ӡ�����ͳ�Ϊ��json��ʽ
		}
		return list;
	}
	
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("assetpropertylist/list");
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(AssetPropertyListEntity assetPropertyListEntity) {
		Map<String, String> ret = new HashMap<String, String>();
		if (assetPropertyListEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�ĲƲ���Ϣ��");
			return ret;
		}
		if (StringUtils.isEmpty(String.valueOf(assetPropertyListEntity.getPname()))) {
			ret.put("type", "error");
			ret.put("msg", "����д�Ʋ���");
			return ret;
		}
		if (assetPropertyListService.edit(assetPropertyListEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "�Ʋ���Ϣ���ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�Ʋ�������Ϣ�޸ĳɹ���");
		return ret;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMenuList(Page page, HttpServletRequest request) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		List<AssetPropertyListVO> findList = assetPropertyListService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", assetPropertyListService.getTotal(queryMap));
		return ret;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String ids) {
		Map<String, String> ret = new HashMap<String, String>();
		if (StringUtils.isEmpty(ids)) {
			ret.put("type", "error");
			ret.put("msg", "ѡ��Ҫɾ�������ݣ�");
			return ret;
		}
		if (ids.contains(",")) {
			ids = ids.substring(0, ids.length() - 1);
		}
		if (assetPropertyListService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "�Ʋ���Ϣɾ��ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�Ʋ���Ϣɾ���ɹ���");
		return ret;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(AssetPropertyListEntity assetPropertyListEntity) {
		Map<String, String> ret = new HashMap<String, String>();
		if (assetPropertyListEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�ĲƲ���Ϣ��");
			return ret;
		}
		if (StringUtils.isEmpty(String.valueOf(assetPropertyListEntity.getCtegoryid()))) {
			ret.put("type", "error");
			ret.put("msg", "����д�Ʋ�������");
			return ret;
		}
		if (StringUtils.isEmpty(String.valueOf(assetPropertyListEntity.getPname()))) {
			ret.put("type", "error");
			ret.put("msg", "����д�Ʋ���");
			return ret;
		}
		if (assetPropertyListService.insertSelective(assetPropertyListEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "�Ʋ���Ϣ���ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�Ʋ���Ϣ��ӳɹ���");
		return ret;
	}
}

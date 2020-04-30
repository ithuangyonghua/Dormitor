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
		System.out.println("查询到的类别：" + aeestByCate);
		// list存放map，map存放kv值（json），namelist取需要的字段
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (AssetPropertyListEntity category : aeestByCate) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 只取classid、ClassName即可。
			String pn = category.getPname();
			String ctid = String.valueOf(category.getCtegoryid());
			// map存放键值对
			map.put("pn", pn);
			map.put("ctid", pn);
			// list存放map
			list.add(map);
			System.out.println("json格式:" + list); // 打印出来就成为了json格式
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
			ret.put("msg", "请填写正确的财产信息！");
			return ret;
		}
		if (StringUtils.isEmpty(String.valueOf(assetPropertyListEntity.getPname()))) {
			ret.put("type", "error");
			ret.put("msg", "请填写财产名");
			return ret;
		}
		if (assetPropertyListService.edit(assetPropertyListEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "财产信息添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财产分类信息修改成功！");
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
			ret.put("msg", "选择要删除的数据！");
			return ret;
		}
		if (ids.contains(",")) {
			ids = ids.substring(0, ids.length() - 1);
		}
		if (assetPropertyListService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "财产信息删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财产信息删除成功！");
		return ret;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(AssetPropertyListEntity assetPropertyListEntity) {
		Map<String, String> ret = new HashMap<String, String>();
		if (assetPropertyListEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的财产信息！");
			return ret;
		}
		if (StringUtils.isEmpty(String.valueOf(assetPropertyListEntity.getCtegoryid()))) {
			ret.put("type", "error");
			ret.put("msg", "请填写财产分类名");
			return ret;
		}
		if (StringUtils.isEmpty(String.valueOf(assetPropertyListEntity.getPname()))) {
			ret.put("type", "error");
			ret.put("msg", "请填写财产名");
			return ret;
		}
		if (assetPropertyListService.insertSelective(assetPropertyListEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "财产信息添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财产信息添加成功！");
		return ret;
	}
}

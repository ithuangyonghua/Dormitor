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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Asset;
import com.ischoolbar.programmer.entity.admin.AssetCategoryEntity;
import com.ischoolbar.programmer.entity.admin.TeacherEntity;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.AssetCategoryService;

@RequestMapping("/admin/assetcategory")
@Controller
public class AssetCategoryController {
	@Autowired
	AssetCategoryService assetCategoryService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("assetcategory/list");
		return model;
	}

	@RequestMapping(value = "/getall", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> loadCategory(HttpServletResponse response) {

		List<AssetCategoryEntity> findAll = assetCategoryService.findAll();
		System.out.println("查询到的类别：" + findAll);
		// list存放map，map存放kv值（json），namelist取需要的字段
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (AssetCategoryEntity category : findAll) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 只取classid、ClassName即可。
			String canme = category.getCname();
			String cid = String.valueOf(category.getId());
			// map存放键值对
			map.put("canme", canme);
			map.put("cid", cid);
			// list存放map
			list.add(map);
			System.out.println("json格式:" + list); // 打印出来就成为了json格式
		}
		return list;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(AssetCategoryEntity assetCategoryEntity) {
		Map<String, String> ret = new HashMap<String, String>();
		if (assetCategoryEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的财产分类信息！");
			return ret;
		}
		if (StringUtils.isEmpty(assetCategoryEntity.getCname())) {
			ret.put("type", "error");
			ret.put("msg", "请填写财产分类名");
			return ret;
		}
		if (StringUtils.isEmpty(String.valueOf(assetCategoryEntity.getCyear()))) {
			ret.put("type", "error");
			ret.put("msg", "请填写财产使用年限");
			return ret;
		}
		if (assetCategoryService.insertSelective(assetCategoryEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "财产分类信息添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财产分类信息添加成功！");
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
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		List<AssetCategoryEntity> findList = assetCategoryService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", assetCategoryService.getTotal(queryMap));
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
		if (assetCategoryService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "财产分类删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财产分类删除成功！");
		return ret;
	}

	/**
	 * 
	 * @param asset
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(AssetCategoryEntity assetCategoryEntity) {
		Map<String, String> ret = new HashMap<String, String>();
		if (assetCategoryEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的财产分类信息！");
			return ret;
		}
		if (StringUtils.isEmpty(assetCategoryEntity.getCname())) {
			ret.put("type", "error");
			ret.put("msg", "请填写财产分类名");
			return ret;
		}
		if (StringUtils.isEmpty(String.valueOf(assetCategoryEntity.getCyear()))) {
			ret.put("type", "error");
			ret.put("msg", "请填写财产使用年限");
			return ret;
		}
		if (assetCategoryService.edit(assetCategoryEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "财产分类信息修改失败，请联系管理员！");
			return ret;
		}

		ret.put("type", "success");
		ret.put("msg", "财产分类信息修改成功！");
		return ret;
	}
}

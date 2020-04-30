package com.ischoolbar.programmer.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.DormitoryEntity;
import com.ischoolbar.programmer.entity.admin.DormitoryVO;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.DormitoryService;
import com.ischoolbar.programmer.util.AjaxJson;

@RequestMapping("/admin/dormitory")
@Controller
public class DormitoryController {
	@Autowired
	DormitoryService dormitoryService;

	/**admin/dormitory/getall
	 * 宿舍楼管理列表页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.addObject("topList", dormitoryService.findTopList());
		List<DormitoryEntity> findTopList = dormitoryService.findTopList();
		model.setViewName("dormitory/list");
		return model;
	}
	
	/*@RequestMapping(value = "/getall", method = {RequestMethod.GET,RequestMethod.POST})
	public List<Map<String, Object>>   getall() {
		List<DormitoryEntity> findTopList = dormitoryService.findALL();
    	System.out.println("查询到的类别："+findTopList);
    	//list存放map，map存放kv值（json），namelist取需要的字段
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for (DormitoryEntity category : findTopList) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		//只取classid、ClassName即可。
    		String dormitoryname = category.getName();
    		String dormitoryid = String.valueOf(category.getDormitoryid());
    		//map存放键值对
    		map.put("dormitoryname",dormitoryname);
	    	map.put("dormitoryid",dormitoryid);
	    	//list存放map
	    	list.add(map);
	    	System.out.println("json格式:"+list); //打印出来就成为了json格式
		}
    	return list;
	}*/

	/**
	 * 获取宿舍列表
	 * 
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMenuList(Page page,
			@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("name", name);
		List<DormitoryVO> findList = dormitoryService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", dormitoryService.getTotal(queryMap));
		return ret;
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
		if (dormitoryService.delete(ids) <= 0) {
			j.setMsg("宿舍删除失败，请联系管理员！");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("宿舍删除成功！");
		j.setSuccess(true);
		return j;
	}
	/*
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson add(DormitoryVO dormitoryVO) {
		AjaxJson j = new AjaxJson();
		if (dormitoryVO == null) {
			j.setMsg("请填写正确的用户信息！");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(dormitoryVO.getName())) {
			j.setMsg("请填写寝室名");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("保存成功！");
		j.setSuccess(true);
		try{
			dormitoryService.add(dormitoryVO);
		}catch(Exception e){
			j.setMsg("保存失败！");
			j.setSuccess(false);
		}
		 return j;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson edit(DormitoryVO dormitoryVO) {
		AjaxJson j = new AjaxJson();
		if (dormitoryVO == null) {
			j.setMsg("请填写正确的用户信息！");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(dormitoryVO.getName())) {
			j.setMsg("请填写寝室名");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("保存成功！");
		j.setSuccess(true);
		try{
			dormitoryService.edit(dormitoryVO);
		}catch(Exception e){
			j.setMsg("保存失败！");
			j.setSuccess(false);
		}
		 return j;
	}
}

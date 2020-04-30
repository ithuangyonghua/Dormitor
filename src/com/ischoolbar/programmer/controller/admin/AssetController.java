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
import com.ischoolbar.programmer.entity.admin.StudentEntity;
import com.ischoolbar.programmer.entity.admin.TeacherEntity;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.AssetService;
import com.ischoolbar.programmer.service.admin.DormitoryService;

@RequestMapping("/admin/asset")
@Controller
public class AssetController {
	@Autowired
	AssetService assetService;
	@Autowired
	DormitoryService dormitoryService;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("asset/list");
		return model;
	}
	/**
	 * 查询
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMenuList(Page page,HttpServletRequest request) {
//		bedchamberid
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
//		
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
		case 4://学生
			StudentEntity stu = (StudentEntity)request.getSession().getAttribute("admin");
			System.out.println(stu.getDormitoryId());
			queryMap.put("bedchamberid", stu.getDormitoryId());
			queryMap.put("uses", stu.getId());
			queryMap.put("registrationstatus", 1);
			break;
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		List<AssetVO> findList = assetService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", assetService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 
	 * @param dormitoryVO
	 * @return
	 */
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Asset asset) {
		Map<String, String> ret = new HashMap<String, String>();
		if (asset == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的财产信息！");
			return ret;
		}
		if (StringUtils.isEmpty(asset.getAssetname())) {
			ret.put("type", "error");
			ret.put("msg", "请填写财产名！");
			return ret;
		}
		asset.setRegistrationstatus(1);
		if (assetService.insertSelective(asset)<= 0) {
			ret.put("type", "error");
			ret.put("msg", "财产信息添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财产信息添加成功！");
		return ret;
	}
	
	/*@RequestMapping(value = "/delete", method = RequestMethod.POST)
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
		if (assetService.delete(ids) <= 0) {
			j.setMsg("财产删除失败，请联系管理员！");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("财产删除成功！");
		j.setSuccess(true);
		return j;
	}*/
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
		if (assetService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "报修信息删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "报修信息删除成功！");
		return ret;
	}
	
	/*@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson edit(Asset asset) {
		AjaxJson j = new AjaxJson();
		if (asset == null) {
			j.setMsg("请填写正确的财产信息！");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(asset.getAssetname())) {
			j.setMsg("请填写财产名");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("保存成功！");
		j.setSuccess(true);
		try{
			assetService.edit(asset);
		}catch(Exception e){
			j.setMsg("保存失败！");
			j.setSuccess(false);
		}
		 return j;
	}*/
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Asset asset) {
		Map<String, String> ret = new HashMap<String, String>();
		if (asset == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的财产信息！");
			return ret;
		}
		if (StringUtils.isEmpty(asset.getAssetname())) {
			ret.put("type", "error");
			ret.put("msg", "请填写财产名");
			return ret;
		}
		if (assetService.edit(asset) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "财产信息修改失败，请联系管理员！");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "财产信息修改成功！");
		return ret;
	}
}

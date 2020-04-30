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
	 * ��ѯ
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
		switch (myrole) {// 1����Ա 2��ʦ 4 ѧ��
		case 2:
			Map<String, Object> teacheMap = new HashMap<String, Object>();
			TeacherEntity teacherEntity = (TeacherEntity) request.getSession().getAttribute("admin");// ��ȡ��ʦ
			teacheMap.put("teacherid", teacherEntity.getId());
			List<DormitoryEntity> findALL = dormitoryService.findALL(teacheMap);//��ȡ���������
			Integer dormitoryid = findALL.get(0).getDormitoryid();
			queryMap.put("bedchamberid", dormitoryid);
			break;
		case 4://ѧ��
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
			ret.put("msg", "����д��ȷ�ĲƲ���Ϣ��");
			return ret;
		}
		if (StringUtils.isEmpty(asset.getAssetname())) {
			ret.put("type", "error");
			ret.put("msg", "����д�Ʋ�����");
			return ret;
		}
		asset.setRegistrationstatus(1);
		if (assetService.insertSelective(asset)<= 0) {
			ret.put("type", "error");
			ret.put("msg", "�Ʋ���Ϣ���ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�Ʋ���Ϣ��ӳɹ���");
		return ret;
	}
	
	/*@RequestMapping(value = "/delete", method = RequestMethod.POST)
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
		if (assetService.delete(ids) <= 0) {
			j.setMsg("�Ʋ�ɾ��ʧ�ܣ�����ϵ����Ա��");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("�Ʋ�ɾ���ɹ���");
		j.setSuccess(true);
		return j;
	}*/
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
		if (assetService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "������Ϣɾ��ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "������Ϣɾ���ɹ���");
		return ret;
	}
	
	/*@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson edit(Asset asset) {
		AjaxJson j = new AjaxJson();
		if (asset == null) {
			j.setMsg("����д��ȷ�ĲƲ���Ϣ��");
			j.setSuccess(false);
			return j;
		}
		if (StringUtils.isEmpty(asset.getAssetname())) {
			j.setMsg("����д�Ʋ���");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("����ɹ���");
		j.setSuccess(true);
		try{
			assetService.edit(asset);
		}catch(Exception e){
			j.setMsg("����ʧ�ܣ�");
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
			ret.put("msg", "����д��ȷ�ĲƲ���Ϣ��");
			return ret;
		}
		if (StringUtils.isEmpty(asset.getAssetname())) {
			ret.put("type", "error");
			ret.put("msg", "����д�Ʋ���");
			return ret;
		}
		if (assetService.edit(asset) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "�Ʋ���Ϣ�޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "�Ʋ���Ϣ�޸ĳɹ���");
		return ret;
	}
}

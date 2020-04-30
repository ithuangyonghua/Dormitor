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
	 * �޸Ĺ���
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
			ret.put("msg", "����д��ȷ�ı�����Ϣ��");
			return ret;
		}
		if (repairsService.edit(repairsEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "������Ϣ�޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		Asset as = new Asset();
		as.setAssetid(repairsEntity.getAssetid());
		as.setRepairstatus(0);
		assetService.edit(as);
		ret.put("type", "success");
		ret.put("msg", "������Ϣ�޸ĳɹ���");
		return ret;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(RepairsEntity repairsEntity, HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		if (repairsEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�ı�����Ϣ��");
			return ret;
		}

		if (StringUtils.isEmpty(repairsEntity.getBrank())) {
			ret.put("type", "error");
			ret.put("msg", "����д�������飡");
			return ret;
		}
		if (StringUtils.isEmpty(String.valueOf(repairsEntity.getAssetid()))) {
			ret.put("type", "error");
			ret.put("msg", "����д���޲Ʋ���");
			return ret;
		}
		Integer myrole = (Integer) request.getSession().getAttribute("myrole");
		if (myrole != 4) {// ѧ��
			ret.put("type", "error");
			ret.put("msg", "��Ȩ��");
			return ret;
		}
		StudentEntity stu = (StudentEntity) request.getSession().getAttribute("admin");// ��ȡѧ��
		repairsEntity.setCreatetime(new Date());
		repairsEntity.setStatus(1);// 1����ά��
		repairsEntity.setStuid(stu.getId());
		Asset as = new Asset();
		as.setAssetid(repairsEntity.getAssetid());
		as.setRepairstatus(1);
		assetService.edit(as);
		if (repairsService.add(repairsEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "������Ϣ���ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "������Ϣ��ӳɹ���");
		return ret;
	}

	/**
	 * ��ѯ
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
		switch (myrole) {// 1����Ա 2��ʦ 4 ѧ��
		case 4:
			StudentEntity stu = (StudentEntity) request.getSession().getAttribute("admin");// ��ȡѧ��
			queryMap.put("stuid", stu.getId());
			break;
		case 2:
			Map<String, Object> teacheMap = new HashMap<String, Object>();
			TeacherEntity teacherEntity = (TeacherEntity) request.getSession().getAttribute("admin");// ��ȡ��ʦ
			teacheMap.put("teacherid", teacherEntity.getId());
			List<DormitoryEntity> findALL = dormitoryService.findALL(teacheMap);// ��ȡ���������
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
	 * ɾ��
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
			ret.put("msg", "ѡ��Ҫɾ�������ݣ�");
			return ret;
		}
		if (ids.contains(",")) {
			ids = ids.substring(0, ids.length() - 1);
		}
		if (repairsService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "������Ϣɾ��ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "������Ϣɾ���ɹ���");
		return ret;
	}

}

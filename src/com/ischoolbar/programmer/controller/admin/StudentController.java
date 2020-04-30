package com.ischoolbar.programmer.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.StudentEntity;
import com.ischoolbar.programmer.entity.admin.TeacherEntity;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.RoleService;
import com.ischoolbar.programmer.service.admin.StudentService;

/**
 * ѧ�����������
 *
 */
@RequestMapping("/admin/student")
@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addObject("roleList", roleService.findList(queryMap));
		model.setViewName("student/list");
		return model;
	}

	/**
	 * ��ȡѧ���б�
	 * 
	 * @param page
	 * @param username
	 * @param roleId
	 * @param sex
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name = "username", required = false, defaultValue = "") String username,
			@RequestParam(name = "roleId", required = false) Long roleId,
			@RequestParam(name = "sex", required = false) Integer sex) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("username", username);
		queryMap.put("roleId", 4);
		queryMap.put("sex", sex);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", studentService.findList(queryMap));
		ret.put("total", studentService.getTotal(queryMap));
		return ret;
	}
   /**
    * �޸Ĺ���
    * @param studentEntity
    * @return
    */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(StudentEntity studentEntity) {
		Map<String, String> ret = new HashMap<String, String>();
		if (studentEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ���û���Ϣ��");
			return ret;
		}
		if (StringUtils.isEmpty(studentEntity.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "����д������");
			return ret;
		}
		if (studentService.edit(studentEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "�û����ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ɫ��ӳɹ���");
		return ret;
	}

	/**
	 * ���
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(StudentEntity studentEntity) {
		Map<String, String> ret = new HashMap<String, String>();

		if (studentEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ���û���Ϣ��");
			return ret;
		}
		if (StringUtils.isEmpty(studentEntity.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "����д�û�����");
			return ret;
		}
		if (StringUtils.isEmpty(studentEntity.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "����д���룡");
			return ret;
		}
		if (isExist(studentEntity.getId())) {
			ret.put("type", "error");
			ret.put("msg", "���û����Ѿ����ڣ����������룡");
			return ret;
		}
		studentEntity.setRoleId(4);
		if (studentService.add(studentEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "�û����ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ɫ��ӳɹ���");
		return ret;
	}

	/**
	 * �ϴ�ͼƬ
	 * 
	 * @param photo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upload_photo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadPhoto(MultipartFile photo, HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		if (photo == null) {
			ret.put("type", "error");
			ret.put("msg", "ѡ��Ҫ�ϴ����ļ���");
			return ret;
		}
		if (photo.getSize() > 1024 * 1024 * 1024) {
			ret.put("type", "error");
			ret.put("msg", "�ļ���С���ܳ���10M��");
			return ret;
		}
		// ��ȡ�ļ���׺
		String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1,
				photo.getOriginalFilename().length());
		if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
			ret.put("type", "error");
			ret.put("msg", "��ѡ��jpg,jpeg,gif,png��ʽ��ͼƬ��");
			return ret;
		}
		String savePath = request.getServletContext().getRealPath("/") + "/resources/upload/";
		File savePathFile = new File(savePath);
		if (!savePathFile.exists()) {
			// �������ڸ�Ŀ¼���򴴽�Ŀ¼
			savePathFile.mkdir();
		}
		String filename = new Date().getTime() + "." + suffix;
		try {
			// ���ļ�������ָ��Ŀ¼
			photo.transferTo(new File(savePath + filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ret.put("type", "error");
			ret.put("msg", "�����ļ��쳣��");
			e.printStackTrace();
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�û�ɾ���ɹ���");
		ret.put("filepath", request.getServletContext().getContextPath() + "/resources/upload/" + filename);
		return ret;
	}

	/**
	 * �жϸ��û����Ƿ����
	 * 
	 * @param username
	 * @param id
	 * @return
	 */
	private boolean isExist(String id) {
		StudentEntity findByStudentname = studentService.findByStudentname(id);
		if (findByStudentname == null)
			return false;
		return true;
	}

	/**
	 * ����ɾ���û�
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
		if (studentService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "�û�ɾ��ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�û�ɾ���ɹ���");
		return ret;
	}
	
}

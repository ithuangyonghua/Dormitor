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
 * 学生管理控制器
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
	 * 获取学生列表
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
    * 修改功能
    * @param studentEntity
    * @return
    */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(StudentEntity studentEntity) {
		Map<String, String> ret = new HashMap<String, String>();
		if (studentEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的用户信息！");
			return ret;
		}
		if (StringUtils.isEmpty(studentEntity.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写姓名！");
			return ret;
		}
		if (studentService.edit(studentEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "用户添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "角色添加成功！");
		return ret;
	}

	/**
	 * 添加
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
			ret.put("msg", "请填写正确的用户信息！");
			return ret;
		}
		if (StringUtils.isEmpty(studentEntity.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		if (StringUtils.isEmpty(studentEntity.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}
		if (isExist(studentEntity.getId())) {
			ret.put("type", "error");
			ret.put("msg", "该用户名已经存在，请重新输入！");
			return ret;
		}
		studentEntity.setRoleId(4);
		if (studentService.add(studentEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "用户添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "角色添加成功！");
		return ret;
	}

	/**
	 * 上传图片
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
			ret.put("msg", "选择要上传的文件！");
			return ret;
		}
		if (photo.getSize() > 1024 * 1024 * 1024) {
			ret.put("type", "error");
			ret.put("msg", "文件大小不能超过10M！");
			return ret;
		}
		// 获取文件后缀
		String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1,
				photo.getOriginalFilename().length());
		if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
			ret.put("type", "error");
			ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
			return ret;
		}
		String savePath = request.getServletContext().getRealPath("/") + "/resources/upload/";
		File savePathFile = new File(savePath);
		if (!savePathFile.exists()) {
			// 若不存在改目录，则创建目录
			savePathFile.mkdir();
		}
		String filename = new Date().getTime() + "." + suffix;
		try {
			// 将文件保存至指定目录
			photo.transferTo(new File(savePath + filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ret.put("type", "error");
			ret.put("msg", "保存文件异常！");
			e.printStackTrace();
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "用户删除成功！");
		ret.put("filepath", request.getServletContext().getContextPath() + "/resources/upload/" + filename);
		return ret;
	}

	/**
	 * 判断该用户名是否存在
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
	 * 批量删除用户
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
		if (studentService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "用户删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "用户删除成功！");
		return ret;
	}
	
}

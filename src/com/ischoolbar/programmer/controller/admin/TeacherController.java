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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 老师管理控制器
 *
 */
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Asset;
import com.ischoolbar.programmer.entity.admin.DormitoryEntity;
import com.ischoolbar.programmer.entity.admin.StudentEntity;
import com.ischoolbar.programmer.entity.admin.TeacherEntity;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.AssetService;
import com.ischoolbar.programmer.service.admin.DormitoryService;
import com.ischoolbar.programmer.service.admin.RoleService;
import com.ischoolbar.programmer.service.admin.StudentService;
import com.ischoolbar.programmer.service.admin.TeacherService;

@RequestMapping("/admin/teach")
@Controller
public class TeacherController {
	@Autowired
	AssetService  assetService;
	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private RoleService roleService;
	@Autowired
	DormitoryService dormitoryService;
	/**
	 * 获取财产
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getassetall/{bedchamberid}", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getassetall(@PathVariable(value="bedchamberid") Integer bedchamberid,HttpServletResponse response,HttpServletRequest request) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		//首先判断是不是学生登录
		Integer myrole = (Integer) request.getSession().getAttribute("myrole");
		if(myrole==4){//学生
			StudentEntity stu = (StudentEntity)request.getSession().getAttribute("admin");// 获取学生
			if(!StringUtils.isEmpty(String.valueOf(stu.getDormitoryId()))){
				queryMap.put("bedchamberid", stu.getDormitoryId());
			}else{
				queryMap.put("bedchamberid",-1);//还未分配宿舍
			}
		}else{//不是学生登录
			if(bedchamberid!=-1){
				queryMap.put("bedchamberid",bedchamberid);
			}
		}
		List<Asset> findALL = assetService.findALL(queryMap);
		System.out.println("查询到的类别：" + findALL);
		// list存放map，map存放kv值（json），namelist取需要的字段
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Asset category : findALL) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 只取classid、ClassName即可。
			String assetname = category.getAssetname();
			String assetid = String.valueOf(category.getAssetid());
			// map存放键值对
			map.put("assetname", assetname);
			map.put("assetid", assetid);
			// list存放map
			list.add(map);
			System.out.println("json格式:" + list); // 打印出来就成为了json格式
		}
		return list;
	}
	
   /**
    * 获取 老师
    * @param response
    * @return
    */
	@RequestMapping(value = "/getall", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> loadCategory(HttpServletResponse response) {

		List<TeacherEntity> namelist = teacherService.findALL();
		System.out.println("查询到的类别：" + namelist);
		// list存放map，map存放kv值（json），namelist取需要的字段
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (TeacherEntity category : namelist) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 只取classid、ClassName即可。
			String username = category.getUsername();
			String tid = category.getId();
			// map存放键值对
			map.put("username", username);
			map.put("tid", tid);
			// list存放map
			list.add(map);
			System.out.println("json格式:" + list); // 打印出来就成为了json格式
		}
		return list;
	}
    /**
     * 获取宿舍信息
     * @return
     */
	@RequestMapping(value = "/dorgetall", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> getall(HttpServletResponse response,HttpServletRequest request) {
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Integer myrole = (Integer)request.getSession().getAttribute("myrole");
		switch(myrole){//1管理员 2老师 4 学生
		case 2 : 
			 TeacherEntity teacherEntity = (TeacherEntity)request.getSession().getAttribute("admin");//获取老师
			 queryMap.put("teacherid", teacherEntity.getId());
			 break;
		case 4: 
			 StudentEntity stu = (StudentEntity)request.getSession().getAttribute("admin");//获取老师
			 if(!StringUtils.isEmpty(String.valueOf(stu.getDormitoryId()))){
				 queryMap.put("dormitoryid", stu.getDormitoryId());
			 }else{
				 queryMap.put("dormitoryid", -1);
			 }
			 
			 break;
		}
		List<DormitoryEntity> findTopList = dormitoryService.findALL(queryMap);
		System.out.println("查询到的类别：" + findTopList);
		// list存放map，map存放kv值（json），namelist取需要的字段
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (DormitoryEntity category : findTopList) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 只取classid、ClassName即可。
			String dormitoryname = category.getName();
			String dormitoryid = String.valueOf(category.getDormitoryid());
			// map存放键值对
			map.put("dormitoryname", dormitoryname);
			map.put("dormitoryid", dormitoryid);
			// list存放map
			list.add(map);
			System.out.println("json格式:" + list); // 打印出来就成为了json格式
		}
		return list;
	}

	/**
	 * 老师列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addObject("roleList", roleService.findList(queryMap));
		model.setViewName("teacher/list");
		return model;
	}

	/**
	 * 获取老师列表
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
		queryMap.put("roleId", 2);
		queryMap.put("sex", sex);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", teacherService.findList(queryMap));
		ret.put("total", teacherService.getTotal(queryMap));
		return ret;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(TeacherEntity teacherEntity) {
		Map<String, String> ret = new HashMap<String, String>();
		if (teacherEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的用户信息！");
			return ret;
		}
		if (StringUtils.isEmpty(teacherEntity.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写姓名名！");
			return ret;
		}
		if (teacherService.edit(teacherEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "用户添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "角色添加成功！");
		return ret;
	}

	/**
	 * 添加老师
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(TeacherEntity teacherEntity) {
		Map<String, String> ret = new HashMap<String, String>();

		if (teacherEntity == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的用户信息！");
			return ret;
		}
		if (StringUtils.isEmpty(teacherEntity.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		if (StringUtils.isEmpty(teacherEntity.getPassWord())) {
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}
		if (isExist(teacherEntity.getId())) {
			ret.put("type", "error");
			ret.put("msg", "该用户名已经存在，请重新输入！");
			return ret;
		}
		teacherEntity.setRoleId(2);
		if (teacherService.add(teacherEntity) <= 0) {
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
	private boolean isExist(String username) {
		TeacherEntity teacher = teacherService.findByTeachername(username);
		if (teacher == null)
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
		if (teacherService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "用户删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "用户删除成功！");
		return ret;
	}
	/**
	 * 获取学生
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getstuall", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getStu(HttpServletResponse response) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		List<StudentEntity> namelist = studentService.findALL(queryMap);
		System.out.println("查询到的类别：" + namelist);
		// list存放map，map存放kv值（json），namelist取需要的字段
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (StudentEntity category : namelist) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 只取classid、ClassName即可。
			String username = category.getUsername();
			String stuid = category.getId();
			// map存放键值对
			map.put("stuname", username);
			map.put("stuid", stuid);
			// list存放map
			list.add(map);
			System.out.println("json格式:" + list); // 打印出来就成为了json格式
		}
		return list;
	}
	/**
	 * 获取学生
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getbstuall/{bedchamberid}", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getStu1(@PathVariable(value="bedchamberid") Integer bedchamberid,HttpServletResponse response) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("dormitoryId",bedchamberid);
		List<StudentEntity> namelist = studentService.findALL(queryMap);
		System.out.println("查询到的类别：" + namelist);
		// list存放map，map存放kv值（json），namelist取需要的字段
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (StudentEntity category : namelist) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 只取classid、ClassName即可。
			String username = category.getUsername();
			String stuid = category.getId();
			// map存放键值对
			map.put("stuname", username);
			map.put("stuid", stuid);
			// list存放map
			list.add(map);
			System.out.println("json格式:" + list); // 打印出来就成为了json格式
		}
		return list;
	}

}

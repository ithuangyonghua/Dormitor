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
 * ��ʦ���������
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
	 * ��ȡ�Ʋ�
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getassetall/{bedchamberid}", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getassetall(@PathVariable(value="bedchamberid") Integer bedchamberid,HttpServletResponse response,HttpServletRequest request) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		//�����ж��ǲ���ѧ����¼
		Integer myrole = (Integer) request.getSession().getAttribute("myrole");
		if(myrole==4){//ѧ��
			StudentEntity stu = (StudentEntity)request.getSession().getAttribute("admin");// ��ȡѧ��
			if(!StringUtils.isEmpty(String.valueOf(stu.getDormitoryId()))){
				queryMap.put("bedchamberid", stu.getDormitoryId());
			}else{
				queryMap.put("bedchamberid",-1);//��δ��������
			}
		}else{//����ѧ����¼
			if(bedchamberid!=-1){
				queryMap.put("bedchamberid",bedchamberid);
			}
		}
		List<Asset> findALL = assetService.findALL(queryMap);
		System.out.println("��ѯ�������" + findALL);
		// list���map��map���kvֵ��json����namelistȡ��Ҫ���ֶ�
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Asset category : findALL) {
			Map<String, Object> map = new HashMap<String, Object>();
			// ֻȡclassid��ClassName���ɡ�
			String assetname = category.getAssetname();
			String assetid = String.valueOf(category.getAssetid());
			// map��ż�ֵ��
			map.put("assetname", assetname);
			map.put("assetid", assetid);
			// list���map
			list.add(map);
			System.out.println("json��ʽ:" + list); // ��ӡ�����ͳ�Ϊ��json��ʽ
		}
		return list;
	}
	
   /**
    * ��ȡ ��ʦ
    * @param response
    * @return
    */
	@RequestMapping(value = "/getall", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> loadCategory(HttpServletResponse response) {

		List<TeacherEntity> namelist = teacherService.findALL();
		System.out.println("��ѯ�������" + namelist);
		// list���map��map���kvֵ��json����namelistȡ��Ҫ���ֶ�
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (TeacherEntity category : namelist) {
			Map<String, Object> map = new HashMap<String, Object>();
			// ֻȡclassid��ClassName���ɡ�
			String username = category.getUsername();
			String tid = category.getId();
			// map��ż�ֵ��
			map.put("username", username);
			map.put("tid", tid);
			// list���map
			list.add(map);
			System.out.println("json��ʽ:" + list); // ��ӡ�����ͳ�Ϊ��json��ʽ
		}
		return list;
	}
    /**
     * ��ȡ������Ϣ
     * @return
     */
	@RequestMapping(value = "/dorgetall", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> getall(HttpServletResponse response,HttpServletRequest request) {
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Integer myrole = (Integer)request.getSession().getAttribute("myrole");
		switch(myrole){//1����Ա 2��ʦ 4 ѧ��
		case 2 : 
			 TeacherEntity teacherEntity = (TeacherEntity)request.getSession().getAttribute("admin");//��ȡ��ʦ
			 queryMap.put("teacherid", teacherEntity.getId());
			 break;
		case 4: 
			 StudentEntity stu = (StudentEntity)request.getSession().getAttribute("admin");//��ȡ��ʦ
			 if(!StringUtils.isEmpty(String.valueOf(stu.getDormitoryId()))){
				 queryMap.put("dormitoryid", stu.getDormitoryId());
			 }else{
				 queryMap.put("dormitoryid", -1);
			 }
			 
			 break;
		}
		List<DormitoryEntity> findTopList = dormitoryService.findALL(queryMap);
		System.out.println("��ѯ�������" + findTopList);
		// list���map��map���kvֵ��json����namelistȡ��Ҫ���ֶ�
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (DormitoryEntity category : findTopList) {
			Map<String, Object> map = new HashMap<String, Object>();
			// ֻȡclassid��ClassName���ɡ�
			String dormitoryname = category.getName();
			String dormitoryid = String.valueOf(category.getDormitoryid());
			// map��ż�ֵ��
			map.put("dormitoryname", dormitoryname);
			map.put("dormitoryid", dormitoryid);
			// list���map
			list.add(map);
			System.out.println("json��ʽ:" + list); // ��ӡ�����ͳ�Ϊ��json��ʽ
		}
		return list;
	}

	/**
	 * ��ʦ�б�
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
	 * ��ȡ��ʦ�б�
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
			ret.put("msg", "����д��ȷ���û���Ϣ��");
			return ret;
		}
		if (StringUtils.isEmpty(teacherEntity.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "����д��������");
			return ret;
		}
		if (teacherService.edit(teacherEntity) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "�û����ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ɫ��ӳɹ���");
		return ret;
	}

	/**
	 * �����ʦ
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
			ret.put("msg", "����д��ȷ���û���Ϣ��");
			return ret;
		}
		if (StringUtils.isEmpty(teacherEntity.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "����д�û�����");
			return ret;
		}
		if (StringUtils.isEmpty(teacherEntity.getPassWord())) {
			ret.put("type", "error");
			ret.put("msg", "����д���룡");
			return ret;
		}
		if (isExist(teacherEntity.getId())) {
			ret.put("type", "error");
			ret.put("msg", "���û����Ѿ����ڣ����������룡");
			return ret;
		}
		teacherEntity.setRoleId(2);
		if (teacherService.add(teacherEntity) <= 0) {
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
	private boolean isExist(String username) {
		TeacherEntity teacher = teacherService.findByTeachername(username);
		if (teacher == null)
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
		if (teacherService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "�û�ɾ��ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�û�ɾ���ɹ���");
		return ret;
	}
	/**
	 * ��ȡѧ��
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getstuall", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getStu(HttpServletResponse response) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		List<StudentEntity> namelist = studentService.findALL(queryMap);
		System.out.println("��ѯ�������" + namelist);
		// list���map��map���kvֵ��json����namelistȡ��Ҫ���ֶ�
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (StudentEntity category : namelist) {
			Map<String, Object> map = new HashMap<String, Object>();
			// ֻȡclassid��ClassName���ɡ�
			String username = category.getUsername();
			String stuid = category.getId();
			// map��ż�ֵ��
			map.put("stuname", username);
			map.put("stuid", stuid);
			// list���map
			list.add(map);
			System.out.println("json��ʽ:" + list); // ��ӡ�����ͳ�Ϊ��json��ʽ
		}
		return list;
	}
	/**
	 * ��ȡѧ��
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getbstuall/{bedchamberid}", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getStu1(@PathVariable(value="bedchamberid") Integer bedchamberid,HttpServletResponse response) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("dormitoryId",bedchamberid);
		List<StudentEntity> namelist = studentService.findALL(queryMap);
		System.out.println("��ѯ�������" + namelist);
		// list���map��map���kvֵ��json����namelistȡ��Ҫ���ֶ�
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (StudentEntity category : namelist) {
			Map<String, Object> map = new HashMap<String, Object>();
			// ֻȡclassid��ClassName���ɡ�
			String username = category.getUsername();
			String stuid = category.getId();
			// map��ż�ֵ��
			map.put("stuname", username);
			map.put("stuid", stuid);
			// list���map
			list.add(map);
			System.out.println("json��ʽ:" + list); // ��ӡ�����ͳ�Ϊ��json��ʽ
		}
		return list;
	}

}

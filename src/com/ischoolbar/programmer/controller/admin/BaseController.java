package com.ischoolbar.programmer.controller.admin;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.ischoolbar.programmer.util.DateConvertEditor;

@Controller
public class BaseController {
	
	/**
	 * ��ǰ̨���ݹ��������ڸ�ʽ���ַ������Զ�ת��ΪDate����
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
	}
	
//	public BaseController(){
//		JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
//	}
}
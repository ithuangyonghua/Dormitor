package com.ischoolbar.programmer.controller.admin;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.ischoolbar.programmer.util.DateConvertEditor;

@Controller
public class BaseController {
	
	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
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
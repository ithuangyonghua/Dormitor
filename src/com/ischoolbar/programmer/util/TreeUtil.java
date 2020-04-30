//package com.ischoolbar.programmer.util;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ischoolbar.programmer.entity.admin.DormitoryVO;
//import com.ischoolbar.programmer.entity.admin.FloorVO;
//import com.ischoolbar.programmer.entity.admin.RoomEntity;
//import com.ischoolbar.programmer.entity.admin.tree;
//
//public class TreeUtil {
//  public static  ArrayList<tree> getTreeJson(List<DormitoryVO> data){
//	  ArrayList<tree> trlist = new ArrayList<>();
//	    Integer parentId=1000;//第一层的楼栋
//	    Integer oneId =10000;//第二层的楼层
//	    Integer twoId = 100000;//第三层的寝室
//	    tree t;
//	    tree t1;
//	    tree t2;
//  if(data.size()>0){
//		  for(int i = 0;i<data.size();i++){
//			  //遍历的是第一层-楼栋
//			   t= new tree();
//			  Integer dormitoryid = data.get(i).getDormitoryid();
//			   t.setId(parentId);
//			  t.set_parentId(0);
//			  t.setName(data.get(i).getName());
//			  t.setParentId(dormitoryid);
//			  t.setUrl(data.get(i).getUrl());
//			  t.setIcon(data.get(i).getIcon());
//			  trlist.add(t);
//			  
//			  //遍历第二层-楼层
//			  ArrayList<FloorVO> listfoor = data.get(i).getListfoor();
//			  if(listfoor.size()>0){
//				 for(int j=0;j<listfoor.size();j++){
//					 t1 = new tree();
//					Integer floorId = listfoor.get(j).getFloorId();
//					 t1.setId(oneId);
//					 t1.setName(String.valueOf(listfoor.get(j).getFloorNum()));
////					 t1.setParentId(floorId);
//					 t1.set_parentId(parentId);
//					 t1.setUrl(data.get(j).getUrl());
//					 t1.setIcon(data.get(j).getIcon());
//					 trlist.add(t1);
//					 
//					 ArrayList<RoomEntity> roomlist = listfoor.get(j).getRoomlist();
//					 if(roomlist.size()>0){
//						 for(int k=0;k<roomlist.size();k++){
//							  t2 = new tree();
////							 Integer floorId2 = roomlist.get(j).getFloorId();
//							 t2.setId(twoId);
//							 t2.setName(roomlist.get(k).getRoomId());
////							 t2.setParentId(floorId2+1);
//							 t2.set_parentId(oneId);
//							 t2.setUrl(data.get(j).getUrl());
//							 t2.setIcon(data.get(j).getIcon());
//							 trlist.add(t2);
//						 }
//						 oneId++;
//					 }
//					
//				 }
//			  }
//			  parentId++;
//		  }
//	  }
//	
//	  return trlist;
//  }
//} 
//
///*[
// {
//   "id": 1,
//   "parentId": 0,
//   "_parentId": 0,
//   "name": "系统设置",
//   "url": "",
//   "icon": "icon-advancedsettings"
// },
// {
//   "id": 5,
//   "parentId": 1,
//   "_parentId": 1,
//   "name": "菜单管理",
//   "url": "../admin/menu/list",
//   "icon": "icon-chart-organisation"
// },
// {
//   "id": 13,
//   "parentId": 1,
//   "_parentId": 1,
//   "name": "角色管理",
//   "url": "../admin/role/list",
//   "icon": "icon-group-key"
// },
// {
//   "id": 14,
//   "parentId": 0,
//   "_parentId": 0,
//   "name": "用户管理",
//   "url": "",
//   "icon": "icon-group-gear"
// },
// {
//   "id": 15,
//   "parentId": 14,
//   "_parentId": 14,
//   "name": "老师列表",
//   "url": "../admin/teach/list",
//   "icon": "icon-group"
// },
// {
//   "id": 17,
//   "parentId": 5,
//   "_parentId": 5,
//   "name": "添加",
//   "url": "openAdd()",
//   "icon": "icon-add"
// },
// {
//   "id": 18,
//   "parentId": 5,
//   "_parentId": 5,
//   "name": "编辑",
//   "url": "openEdit()",
//   "icon": "icon-bullet-edit"
// },
// {
//   "id": 19,
//   "parentId": 5,
//   "_parentId": 5,
//   "name": "删除",
//   "url": "remove()",
//   "icon": "icon-cross"
// }
//]*/
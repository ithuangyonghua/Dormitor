package com.ischoolbar.programmer.util;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ҳʵ����Ϣ
 * @author Administrator
 *
 * @param <E>
 */
public class Pagination<E> {
   
	public Pagination() {

	}

	public Pagination(int start, int pageSize) {
		this.start = start;
		this.pageSize = pageSize;
	}

	/**
	 * ��ʼҳ��
	 */
	private int start;

	/**
	 * ������
	 */
	private int totalCount;

	/**
	 * ����������ʵ���Ӧ��Ϣ
	 */
	private List<E> datas;
	
	
	/**
	 * һҳĬ�ϵ���������
	 */
	private int pageSize = 10;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<E> getDatas() {
		return datas== null? new ArrayList<E>(): datas;
	}

	public void setDatas(List<E> datas) {
		this.datas = datas;
	}
}
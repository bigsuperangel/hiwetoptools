package com.fj.hiwetoptools.jfinal.jqgrid;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;

/**
 * jdGrid分页Model转换工具
 */
public final class JqGridModelUtils {
	public static final String SIDX = "sidx";
	public static final String SORD = "sord";

	public static JqGridModel toJqGridView(Page pageInfo){
		JqGridModel jqGridView=new JqGridModel();
		jqGridView.setPage(pageInfo.getPageNumber());
		jqGridView.setRecords(pageInfo.getTotalRow());
		jqGridView.setRows(pageInfo.getList());
		jqGridView.setTotal(pageInfo.getTotalPage());
		return jqGridView;
	}

	public static JqGridModel toJqGridView(Page pageInfo,Kv foot){
		JqGridModel jqGridView = toJqGridView(pageInfo);
		jqGridView.setUserdata(foot);
		return jqGridView;
	}

}

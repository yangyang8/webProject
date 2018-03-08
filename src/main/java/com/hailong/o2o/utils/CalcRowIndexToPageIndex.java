package com.hailong.o2o.utils;

public class CalcRowIndexToPageIndex {
	
	public static int calcPageIndex(int pageIndex,int pageSize){
		int rowIndex=pageIndex==0?0:(pageIndex-1)*pageSize;
		return rowIndex;
	}
}

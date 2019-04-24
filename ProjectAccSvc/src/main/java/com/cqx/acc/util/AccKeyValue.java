package com.cqx.acc.util;

public class AccKeyValue implements Comparable<AccKeyValue> {
	private String key;
	private Object value;
	private Class<?> value_class;
	public Class<?> getValue_class() {
		return value_class;
	}
	public void setValue_class(Class<?> value_class) {
		this.value_class = value_class;
	}
	private int index;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	/**
	 * 排序顺序规则是0是重复不存入、1是升序、-1是降序
	 * */
	@Override
	public int compareTo(AccKeyValue o) {
		int i = o.getIndex();
		/**
		 * Integer.compareTo
		 * return (thisVal<anotherVal ? -1 : (thisVal==anotherVal ? 0 : 1));
		 * */
		return new Integer(this.index).compareTo(i);
//		return index>i?1:0;
	}
}

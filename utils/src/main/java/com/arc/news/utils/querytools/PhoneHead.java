package com.arc.news.utils.querytools;

public class PhoneHead {
	public int version;
	public int citycount;
	public int blockcount;
	public int citypos;
	public int blockpos;
	
	public int byteLength() {
		return  5 * 4;
	}
}

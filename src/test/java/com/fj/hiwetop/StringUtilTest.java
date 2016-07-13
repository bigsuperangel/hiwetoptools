package com.fj.hiwetop;

import org.junit.Test;

import com.fj.hiwetoptools.StringUtil;

public class StringUtilTest {
	
	@Test
	public void format(){
		System.out.println(StringUtil.format("{} test {}", "aaa","bbb"));
	}
}

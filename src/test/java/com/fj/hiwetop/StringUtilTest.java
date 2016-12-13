package com.fj.hiwetop;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.junit.Test;

import com.fj.hiwetoptools.PinyinUtil;
import com.fj.hiwetoptools.StringUtil;

public class StringUtilTest {
	
	@Test
	public void format() throws UnsupportedEncodingException{
		String str = "张1师";
		System.out.print(PinyinUtil.getPinyinFirst(str.substring(0,1)));
	}
}

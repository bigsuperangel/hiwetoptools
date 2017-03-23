package com.fj.hiwetop;

import com.fj.hiwetoptools.ObjectUtil;
import com.fj.hiwetoptools.StrUtil;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * @des
 * @auther linyu
 * @create 2017-03-23:11:20
 */
public class ExcelTest {
    public static void main(String[] args) {
        Map<String, Object> map = new HashedMap();
        map.put("di", null);
        map.put("df", "");

        for (String s : map.keySet()) {
            System.out.println(ObjectUtil.isEmpty(map.get(s)));
        }
    }
}

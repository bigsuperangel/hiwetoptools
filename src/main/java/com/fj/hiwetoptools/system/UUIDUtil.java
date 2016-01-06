package com.fj.hiwetoptools.system;

import java.util.UUID;

public class UUIDUtil {

	
    /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        s = s.replaceAll("-","");
        return s; 
    } 
   
    
    /** 
     * 获得特定UUID 
     * USER_XR3Q_041211
     * @return String UUID 
     */ 
    public static String getUUID(String fix){ 
    	String s = UUID.randomUUID().toString(); 
    	String num = System.currentTimeMillis()+"";
        return fix+"_"+s.substring(9,13)+"_"+num.substring(6,12); 
    } 
   
    
    
    /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static Long getNumUUID(){ 
        return System.currentTimeMillis(); 
    } 
}

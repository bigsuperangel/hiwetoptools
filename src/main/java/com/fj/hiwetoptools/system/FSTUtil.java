package com.fj.hiwetoptools.system;

import java.io.Serializable;

import org.nustaq.serialization.FSTConfiguration;

/**
 * java 序列化对象，要求对象实现Serializable接口
 * @author linyu
 *
 */
public class FSTUtil {
	private static FSTConfiguration configuration = FSTConfiguration
	           .createDefaultConfiguration();
	  
	public static byte[] serialize(Object obj){
	     return configuration.asByteArray((Serializable)obj);
	}
	public static Object deserialize(byte[] sec){
	    return configuration.asObject(sec);
	}
	
}

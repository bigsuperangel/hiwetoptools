package com.fj.hiwetoptools.serializer;

/**
 * 序列化接口
 * @author linyu
 *
 */
public interface ISerializer {
	public  byte[] serialize(Object obj);
	public  Object deserialize(byte[] sec);
}

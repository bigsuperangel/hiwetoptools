package com.fj.hiwetoptools.serializer;

/**
 * 序列化接口
 * @author linyu
 *
 */
public interface ISerializer {
	byte[] serialize(Object obj);
	Object deserialize(byte[] sec);
}

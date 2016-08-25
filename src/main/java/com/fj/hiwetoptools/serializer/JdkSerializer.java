package com.fj.hiwetoptools.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JdkSerializer.
 */
public class JdkSerializer implements ISerializer {
	
	public static final ISerializer me = new JdkSerializer();
	private static final Logger log = LoggerFactory.getLogger(JdkSerializer.class);

	@Override
	public byte[] serialize(Object obj) {
		ObjectOutputStream objectOut = null;
		try {
			ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
			objectOut = new ObjectOutputStream(bytesOut);
			objectOut.writeObject(obj);
			objectOut.flush();
			return bytesOut.toByteArray();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if(objectOut != null)
				try {objectOut.close();} catch (Exception e) {log.error(e.getMessage(), e);}
		}
	}

	@Override
	public Object deserialize(byte[] sec) {
		if(sec == null || sec.length == 0)
			return null;
		
		ObjectInputStream objectInput = null;
		try {
			ByteArrayInputStream bytesInput = new ByteArrayInputStream(sec);
			objectInput = new ObjectInputStream(bytesInput);
			return objectInput.readObject();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if (objectInput != null)
				try {objectInput.close();} catch (Exception e) {log.error(e.getMessage(), e);}
		}
	}
}




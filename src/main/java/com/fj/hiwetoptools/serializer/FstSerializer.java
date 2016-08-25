package com.fj.hiwetoptools.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fst 序列化
 * @author linyu
 *
 */
public class FstSerializer implements ISerializer {
	
	public static final FstSerializer me = new FstSerializer();
	private static final Logger log = LoggerFactory.getLogger(FstSerializer.class);
	
	@Override
	public byte[] serialize(Object obj) {
		FSTObjectOutput fstOut = null;
		try {
			ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
			fstOut = new FSTObjectOutput(bytesOut);
			fstOut.writeObject(obj);
			fstOut.flush();
			return bytesOut.toByteArray();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if(fstOut != null)
				try {fstOut.close();} catch (IOException e) {log.error(e.getMessage(), e);}
		}
	}

	@Override
	public Object deserialize(byte[] sec) {
		if(sec == null || sec.length == 0)
			return null;
		
		FSTObjectInput fstInput = null;
		try {
			fstInput = new FSTObjectInput(new ByteArrayInputStream(sec));
			return fstInput.readObject();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if(fstInput != null)
				try {fstInput.close();} catch (IOException e) {log.error(e.getMessage(), e);}
		}
	}
}

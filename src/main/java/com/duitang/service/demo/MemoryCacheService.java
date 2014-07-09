package com.duitang.service.demo;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.apache.avro.AvroRemoteException;

public class MemoryCacheService implements DemoService {

	protected Map<CharSequence, CharSequence> memory = new HashMap();
	protected Map<CharSequence, ByteBuffer> memoryB = new HashMap();

	@Override
	public CharSequence memory_getString(CharSequence key) throws AvroRemoteException {
		return memory.get(key);
	}

	@Override
	public boolean memory_setString(CharSequence key, CharSequence value, int ttl) throws AvroRemoteException {
		memory.put(key, value); // mock, so ignore ttl
		return true;
	}

	@Override
	public ByteBuffer memory_getBytes(CharSequence key) throws AvroRemoteException {
		return memoryB.get(key);
	}

	@Override
	public boolean memory_setBytes(CharSequence key, ByteBuffer value, int ttl) throws AvroRemoteException {
		memoryB.put(key, value);
		return true;
	}

}
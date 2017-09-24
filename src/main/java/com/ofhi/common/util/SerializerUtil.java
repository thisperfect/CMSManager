package com.ofhi.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializerUtil {
	
	private static Logger logger = LoggerFactory.getLogger(SerializerUtil.class);
	private SerializerUtil(){}

	/**
     * 序列化
     * @param object
     * @return
     */
    public static byte[] serializeObj(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        	logger.error("序列化失败!", e);
        	return null;
        }
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     */
    public static Object deserializeObj(byte[] bytes) {
        if (bytes == null){
            return null;
        }
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            logger.error("反序列化失败!", e);
        	return null;
        }
    }
}

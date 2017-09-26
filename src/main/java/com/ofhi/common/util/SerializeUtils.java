package com.ofhi.common.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By laizuan
 * Date: 2017-9-26
 * Time: 20:08
 */
public class SerializeUtils extends SerializationUtils {
    private static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);
    private SerializeUtils(){}

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

    public static String serializeToString(Serializable obj) {
        try {
            byte[] value = serialize(obj);
            return Base64.encodeToString(value);
        } catch (Exception e) {
            throw new RuntimeException("serialize session error", e);
        }
    }

    public static Session deserializeFromString(String base64) {
        try {
            byte[] objectData = Base64.decode(base64);
            return deserialize(objectData);
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }

    public static <T> Collection<T> deserializeFromStringController(Collection<String> base64s) {
        try {
            List<T> list = Lists.newLinkedList();
            for (String base64 : base64s) {
                byte[] objectData = Base64.decode(base64);
                T t = deserialize(objectData);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }
}

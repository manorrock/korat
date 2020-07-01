package se.unlogic.standardutils.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import se.unlogic.standardutils.io.CloseUtils;

public class SerializationUtils {

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T cloneSerializable(T obj) {

		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream objectOutputStream = null;

		ByteArrayInputStream byteArrayInputStream = null;
		ObjectInputStream objectInputStream = null;

		try{
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

			objectOutputStream.writeObject(obj);

			byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

			objectInputStream = new ObjectInputStream(byteArrayInputStream);

			return (T)objectInputStream.readObject();

		}catch(IOException e){

			throw new RuntimeException(e);

		}catch(ClassNotFoundException e){

			throw new RuntimeException(e);

		}finally{
			CloseUtils.close(byteArrayOutputStream);
			CloseUtils.close(objectOutputStream);
			CloseUtils.close(byteArrayInputStream);
			CloseUtils.close(objectInputStream);
		}
	}

	public static byte[] serializeToArray(Serializable object) {

		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(object);

		} catch (IOException e) {

			throw new RuntimeException(e);

		}finally{

			CloseUtils.close(byteArrayOutputStream);
			CloseUtils.close(objectOutputStream);
		}

		return byteArrayOutputStream.toByteArray();
	}

	public static void serialize(Object object, OutputStream outputStream) {

		ObjectOutputStream objectOutputStream = null;

		try {
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(object);

		} catch (IOException e) {

			throw new RuntimeException(e);

		}finally{

			CloseUtils.close(objectOutputStream);
			CloseUtils.close(outputStream);
		}
	}	
	
	@SuppressWarnings("unchecked")
	public static <T> T deserializeFromArray(Class<T> clazz, byte[] bytes) {

		ByteArrayInputStream byteArrayInputStream = null;
		ObjectInputStream objectInputStream = null;

		try {
			byteArrayInputStream = new ByteArrayInputStream(bytes);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);

			return (T)objectInputStream.readObject();

		} catch (Exception e) {

			throw new RuntimeException(e);

		}finally{

			CloseUtils.close(objectInputStream);
			CloseUtils.close(byteArrayInputStream);
		}

	}

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(Class<T> clazz, InputStream inputStream) {

		ObjectInputStream objectInputStream = null;

		try {
			objectInputStream = new ObjectInputStream(inputStream);

			return (T)objectInputStream.readObject();

		} catch (Exception e) {

			throw new RuntimeException(e);

		}finally{

			CloseUtils.close(objectInputStream);
			CloseUtils.close(inputStream);
		}
	}
}

package per.owisho.security;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class TestSecurity {

	public static void main(String[] args) throws Exception {
		/*StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 200; i++) {
			sb.append(1);
		}
		RSAPrivateKeySpec prkeyspec = new RSAPrivateKeySpec(new BigInteger(sb.toString().getBytes("utf-8")),
				new BigInteger(sb.toString().getBytes("utf-8")));
		RSAPublicKeySpec pukeyspec = new RSAPublicKeySpec(new BigInteger(sb.toString().getBytes("utf-8")), new BigInteger(sb.toString().getBytes("utf-8")));
		KeyFactory factory = KeyFactory.getInstance("RSA");
		PublicKey puk = factory.generatePublic(pukeyspec);
		PrivateKey prk = factory.generatePrivate(prkeyspec);
		System.out.println(puk);
		System.out.println("-------------------------------------");
		System.out.println(prk);*/
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		KeyPair keypair = generator.generateKeyPair();
		PrivateKey prk = keypair.getPrivate();
		PublicKey puk = keypair.getPublic();
		System.out.println(prk);
		System.out.println("------------------------");
		System.out.println(puk);
		
		String message = "123";
		byte[] enmessage = encrypt(puk,message.getBytes("utf-8"));
		byte[] planmessage = decrypt(prk, enmessage);
		System.out.println(new String(planmessage,"utf-8"));
	}

	/**
	 * ��Կ���ܹ���
	 * 
	 * @param publicKey
	 *            ��Կ
	 * @param plainTextData
	 *            ��������
	 * @return
	 * @throws Exception
	 *             ���ܹ����е��쳣��Ϣ
	 */
	public static byte[] encrypt(PublicKey publicKey, byte[] plainTextData) throws Exception {
		if (publicKey == null) {
			throw new Exception("���ܹ�ԿΪ��, ������");
		}
		Cipher cipher = null;
		try {
			// ʹ��Ĭ��RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("�޴˼����㷨");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("���ܹ�Կ�Ƿ�,����");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("���ĳ��ȷǷ�");
		} catch (BadPaddingException e) {
			throw new Exception("������������");
		}
	}

	/**
	 * ˽Կ���ܹ���
	 * 
	 * @param privateKey
	 *            ˽Կ
	 * @param plainTextData
	 *            ��������
	 * @return
	 * @throws Exception
	 *             ���ܹ����е��쳣��Ϣ
	 */
	public static byte[] encrypt(PrivateKey privateKey, byte[] plainTextData) throws Exception {
		if (privateKey == null) {
			throw new Exception("����˽ԿΪ��, ������");
		}
		Cipher cipher = null;
		try {
			// ʹ��Ĭ��RSA
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("�޴˼����㷨");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("����˽Կ�Ƿ�,����");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("���ĳ��ȷǷ�");
		} catch (BadPaddingException e) {
			throw new Exception("������������");
		}
	}

	/**
	 * ˽Կ���ܹ���
	 * 
	 * @param privateKey
	 *            ˽Կ
	 * @param cipherData
	 *            ��������
	 * @return ����
	 * @throws Exception
	 *             ���ܹ����е��쳣��Ϣ
	 */
	public static byte[] decrypt(PrivateKey privateKey, byte[] cipherData) throws Exception {
		if (privateKey == null) {
			throw new Exception("����˽ԿΪ��, ������");
		}
		Cipher cipher = null;
		try {
			// ʹ��Ĭ��RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("�޴˽����㷨");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("����˽Կ�Ƿ�,����");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("���ĳ��ȷǷ�");
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new Exception("������������");
		}
	}

	/**
	 * ��Կ���ܹ���
	 * 
	 * @param publicKey
	 *            ��Կ
	 * @param cipherData
	 *            ��������
	 * @return ����
	 * @throws Exception
	 *             ���ܹ����е��쳣��Ϣ
	 */
	public static byte[] decrypt(PublicKey publicKey, byte[] cipherData) throws Exception {
		if (publicKey == null) {
			throw new Exception("���ܹ�ԿΪ��, ������");
		}
		Cipher cipher = null;
		try {
			// ʹ��Ĭ��RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("�޴˽����㷨");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("���ܹ�Կ�Ƿ�,����");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("���ĳ��ȷǷ�");
		} catch (BadPaddingException e) {
			throw new Exception("������������");
		}
	}

}

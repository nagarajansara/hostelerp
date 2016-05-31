package hostelerp.com.security;

import org.apache.commons.codec.binary.Base64;

public class EncryptionUtil
{
	public static String decrypt(int encryptionType, String encryptedText)
	{
		String decryptedText = "";
		switch (encryptionType)
		{
			case 1: // BASE64 DECODE
				byte[] decodedBytes = Base64.decodeBase64(encryptedText
						.getBytes());
				decryptedText = new String(decodedBytes);
				break;
			default:
				break;
		}
		return decryptedText;
	}
}
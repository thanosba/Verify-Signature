import java.io.*;
import java.security.*;
import javax.crypto.*;
public class SignatureExample
{	public static void main (String args[]) throws Exception
	{	
		BufferedReader stdin = new BufferedReader
(new InputStreamReader (System.in));
		System.out.print ("Cleartext: ");
		byte [] cleartext =
stdin.readLine().getBytes("UTF8");
		
		KeyPairGenerator keyPGen =
KeyPairGenerator.getInstance ("DSA");
		KeyPair key_pair = keyPGen.generateKeyPair ();
		
		Signature sig = Signature.getInstance
("SHA1withDSA");
		
		System.out.println ("\nSignature...");
		sig.initSign (key_pair.getPrivate());
		sig.update (cleartext);
		byte[] signature = sig.sign();
		
		System.out.println ("Signature: " + new String
(signature, "UTF8"));
		
		System.out.println ("\nVerification...");
		sig.initVerify (key_pair.getPublic());
		sig.update (cleartext);
		try
		{	if (sig.verify (signature))
				System.out.println ("Signature verified!");
			else
				System.out.println ("Signature failed!");
		}
		catch (SignatureException e)
		{ System.out.println ("Signature failed!"); }
	}
} 

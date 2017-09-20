import java.io.*;
import java.security.*;
import javax.crypto.*;
public class SignatureExample
{	public static void main (String args[]) throws Exception
	{	// ζήτα από το χρήστη ένα απλό κείμενο
		BufferedReader stdin = new BufferedReader
(new InputStreamReader (System.in));
		System.out.print ("Cleartext: ");
		byte [] cleartext =
stdin.readLine().getBytes("UTF8");
		// δημιούργησε ένα ζεύγος κλειδιών με χρήση του
		// αλγορίθμου DSA
		KeyPairGenerator keyPGen =
KeyPairGenerator.getInstance ("DSA");
		KeyPair key_pair = keyPGen.generateKeyPair ();
		// δημιούργησε ένα αντικείμενο τύπου Signature που να
		// χρησιμοποιεί τους αλγόριθμους SHA1 και DSA
		Signature sig = Signature.getInstance
("SHA1withDSA");
		// υπόγραψε το απλό μήνυμα
		System.out.println ("\nSignature...");
		sig.initSign (key_pair.getPrivate());
		sig.update (cleartext);
		byte[] signature = sig.sign();
		// εμφάνισε την υπογραφή στο χρήστη
		System.out.println ("Signature: " + new String
(signature, "UTF8"));
		// επαλήθευσε την υπογραφή και εμφάνισε το αποτέλεσμα
		// στο χρήστη
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
} // SignatureExample

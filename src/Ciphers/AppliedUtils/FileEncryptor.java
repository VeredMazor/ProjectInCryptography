package Ciphers.AppliedUtils;

import Ciphers.Basic.Cipher;
import Ciphers.Basic.HashFunction;
import Ciphers.Utils.FileUtil;


/**
 * FileEncryptor class.
 * Implementation of a utility for encrypting and decrypting files.
 * Uses java.io.File as a file class. Uses FileUtil for reading,writing,and file operations.
 * Final class with static methods. Yeap, another WEPUTEVERYTHINGINTHATBOXSOITCANBEUSEFULANDINONEFPLASE-class.
 * @author Israel Nickerson aka Jewpigeon
 * @see java.io.File
 * @see FileUtil
 * @see Cipher
 * @see CipherCascade
 * @version 1.0
 */
public class FileEncryptor {
    /**
     * Function to encrypt file.
     * @param file - Literally a file.
     * @param cipher - Encryption algorithm to encrypt file.
     * @return byte array of encrypted data of file.
     */
    public static byte[] encrypt(java.io.File file, Cipher cipher){
        byte[] fileBytes = FileUtil.Read.read(file);
        fileBytes = cipher.encrypt(fileBytes);
        return fileBytes;
    }
    /**
     * Function to decrypt file.
     * @param file - Literally a file.
     * @param cipher - Encryption algorithm to decrypt file.
     * @return byte array of decrypted data of file.
     */
    public static byte[] decrypt(java.io.File file, Cipher cipher){
        byte[] fileBytes = FileUtil.Read.read(file);
        fileBytes = cipher.decrypt(fileBytes);
        return fileBytes;
    }

    /**
     * Function to encrypt file with cipherCascade.
     * @param file - Literally a file.
     * @param cipherCascade - List of encryption algorithms  to encrypt file.
     * @return byte array of encrypted data of file.
     */
    public static byte[] encrypt(java.io.File file, CipherCascade cipherCascade){
        byte[] fileBytes = FileUtil.Read.read(file);
        fileBytes = cipherCascade.encryptByCascade(fileBytes);
        return fileBytes;
    }

    /**
     * Function to decrypt file with cipherCascade.
     * @param file - Literally a file.
     * @param cipherCascade - List of encryption algorithms  to decrypt file.
     * @return byte array of decrypted data of file.
     */
    public static byte[] decrypt(java.io.File file, CipherCascade cipherCascade){
        byte[] fileBytes = FileUtil.Read.read(file);
        fileBytes = cipherCascade.decryptByCascade(fileBytes);
        return fileBytes;
    }

    /**
     * Function to encrypt file and write it to itself.
     * @param file - Literally a file.
     * @param cipher - Encryption algorithm to encrypt file and write it to itself.
     */
    public static void encryptInFile(java.io.File file,Cipher cipher){
        byte[] fileBytesEncrypted = encrypt(file,cipher);
        FileUtil.Write.write(fileBytesEncrypted,file);

    }
    /**
     * Function to decrypt file and write it to itself.
     * @param file - Literally a file.
     * @param cipher - Encryption algorithm to decrypt file and write it to itself.
     */
    public static void decryptInFile(java.io.File file, Cipher cipher){
        byte[] fileBytesDecrypted = decrypt(file,cipher);
        FileUtil.Write.write(fileBytesDecrypted,file);
    }

    /**
     * Function to encrypt file by cascade and write it to itself.
     * @param file - Literally a file.
     * @param cipherCascade - Encryption algorithms to encrypt file and write it to itself.
     */
    public static void encryptInFile(java.io.File file, CipherCascade cipherCascade){
        byte[] fileBytesEncrypted = encrypt(file,cipherCascade);
        FileUtil.Write.write(fileBytesEncrypted,file);

    }
    /**
     * Function to decrypt file by cascade and write it to itself.
     * @param file - Literally a file.
     * @param cipherCascade - Encryption algorithms to decrypt file and write it to itself.
     */
    public static void decryptInFile(java.io.File file, CipherCascade cipherCascade){
        byte[] fileBytesDecrypted = decrypt(file,cipherCascade);
        FileUtil.Write.write(fileBytesDecrypted,file);
    }


    /**
     * Function to get the hash of the file.
     * @param file - Literally a file.
     * @param hashFunction - Hash function to get the hash of the file
     * @return byte array of result of hash function
     */
    public static byte[] hashOfFile(java.io.File file, HashFunction hashFunction){return hashFunction.process(FileUtil.Read.read(file));}
}

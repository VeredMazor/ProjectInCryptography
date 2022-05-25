package Ciphers.Utils;
import Ciphers.AppliedUtils.FileEncryptor;
import Ciphers.HashFunctions.SHA;
import Ciphers.HashFunctions.SHA2.*;


public final class FileUtil {

    public static final class Read {
        public static byte[] read(java.io.File file) {
            java.io.FileInputStream inputStream = null;
            try {
                inputStream = new java.io.FileInputStream(file);
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
            }
            return read(inputStream);
        }


        public static byte[] read(java.io.FileInputStream fis) {
            java.io.ByteArrayOutputStream ous = null;
            byte[] buffer = new byte[4096];
            try {
                ous = new java.io.ByteArrayOutputStream();
                int read = 0;
                while ((read = fis.read(buffer)) != -1) {
                    ous.write(buffer, 0, read);
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ous != null)
                        ous.close();
                } catch (java.io.IOException e) {
                }

                try {
                    if (fis != null)
                        fis.close();
                } catch (java.io.IOException e) {
                }
            }
            return ous.toByteArray();
        }

        public static byte[] read(String pathToFile) {
            java.io.File file = new java.io.File(pathToFile);
            return Read.read(file);
        }
    }

    public static final class Write {
        public static void write(byte[] bytes, java.io.File file) {
            java.io.FileOutputStream fos = null;
            try {
                 fos = new java.io.FileOutputStream(file);
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
            }
            write(bytes,fos);
        }

        public static void write(byte[] bytes, java.io.FileOutputStream fos) {
            try {
                fos.write(bytes);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }

        public static void write(byte[] bytes, String pathToFile) {
            java.io.File file = new java.io.File(pathToFile);
            write(bytes,file);
        }

    }

    public static final class Operations {
        public static boolean compare(java.io.File file1, java.io.File file2) {
            SHA sha = new SHA512();

            byte[] file1_sum =FileEncryptor.hashOfFile(file1,sha);
            byte[] file2_sum =FileEncryptor.hashOfFile(file2,sha);
            return compare(file1_sum,file2_sum);
        }

        private static boolean compare(byte[] file1, byte[] file2) {
            long[] file1_sum64 = BitUtil.ByteArrays.byteArrayToLongArray(file1);
            long[] file2_sum64 = BitUtil.ByteArrays.byteArrayToLongArray(file2);
            for (int i = 0; i < file1_sum64.length; i++) {
                if(file1_sum64[i] != file2_sum64[i]) return false;
            }
            return true;
        }




    }
}

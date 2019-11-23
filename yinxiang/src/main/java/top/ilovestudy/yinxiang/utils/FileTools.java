package top.ilovestudy.yinxiang.utils;

import com.evernote.edam.type.Data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class FileTools {
  /**
   * Helper method to read the contents of a file on disk and create a new Data
   * object.
   */
  public static Data readFileAsData(String fileName) throws Exception {
//        String filePath = new File(EDAMDemo.class.getResource(
//                EDAMDemo.class.getCanonicalName() + ".class").getPath()).getParent()
//                + File.separator + fileName;

    File filePath = Paths.get(fileName).toFile();
    if (!filePath.exists()) {
      new RuntimeException(fileName + " is not exist");
    }
    // Read the full binary contents of the file
    FileInputStream in = new FileInputStream(filePath);
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    byte[] block = new byte[10240];
    int len;
    while ((len = in.read(block)) >= 0) {
      byteOut.write(block, 0, len);
    }
    in.close();
    byte[] body = byteOut.toByteArray();

    // Create a new Data object to contain the file contents
    Data data = new Data();
    data.setSize(body.length);
    data.setBodyHash(MessageDigest.getInstance("MD5").digest(body));
    data.setBody(body);

    return data;
  }

  /**
   * Helper method to convert a byte array to a hexadecimal string.
   */
  public static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte hashByte : bytes) {
      int intVal = 0xff & hashByte;
      if (intVal < 0x10) {
        sb.append('0');
      }
      sb.append(Integer.toHexString(intVal));
    }
    return sb.toString();
  }
}
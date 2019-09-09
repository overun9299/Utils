package overun.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: FileUtil
 * @Description: 文件工具类
 * @author: 壹米滴答-西安-ZhangPY
 * @version: V1.0
 * @date: 2019/9/9 10:42
 * @Copyright: 2019 www.yimidida.com Inc. All rights reserved.
 */
public class FileUtil {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    private static final String PATH_WHITE_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?";

    private static final Pattern pattern = Pattern.compile("(.*([/\\\\]{1}[\\.\\.]{1,2}|[\\.\\.]{1,2}[/\\\\]{1}|\\.\\.).*|\\.)");



    /**
     * 路径是否是安全路径
     *
     * @param filePath
     * @return
     */
    public static  boolean isSafePath(String filePath)
    {
        Matcher matcher = pattern.matcher(filePath);
        boolean isSafe = !matcher.matches();
        return isSafe;
    }


    /**
     * 解决fortify白名单问题，必须是只有英文字符
     * 文件名必须是这些字符才能使用： PATH_WHITE_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?";
     *
     * @param filePath
     * @return
     */
    public static String checkFile(String filePath)
    {
        if (filePath == null)
        {
            return null;
        }

        final StringBuffer tmpStrBuf = new StringBuffer();
        for (int i = 0; i < filePath.length(); i++)
        {
            for (int j = 0; j < PATH_WHITE_LIST.length(); j++)
            {
                if (filePath.charAt(i) == PATH_WHITE_LIST.charAt(j))
                {
                    tmpStrBuf.append(PATH_WHITE_LIST.charAt(j));
                    break;
                }
            }
        }

        return tmpStrBuf.toString();
    }
    /**
     * input stream to byte[]
     *
     * @param is input stream
     * @return byte[]
     * @throws IOException
     */
    public static byte[] inputStreamToByte(InputStream is)
    {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        byte imgdata[] = null;
        try
        {
            int ch;
            while ((ch = is.read()) != -1)
            {
                bytestream.write(ch);
                bytestream.flush();
            }
            imgdata = bytestream.toByteArray();
        }
        catch (IOException e)
        {
            LOG.error("InputStreamToByte fail.");
        }
        finally
        {
            try
            {
                bytestream.close();
            }
            catch (IOException ex)
            {
                LOG.error("Close output stream fail");
            }
        }
        return imgdata;
    }

}

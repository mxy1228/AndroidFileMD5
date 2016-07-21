package md5.meng.com.androidfilemd5.security;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * MD5
 * Created by Meng on 16/7/21.
 */
public class SecurityHelper {

    public static class SecurityInstanceHolder{
        private static SecurityHelper INSTANCE = new SecurityHelper();
    }

    public static SecurityHelper getInstance(){
        return SecurityInstanceHolder.INSTANCE;
    }

    /**
     * 获取文件MD5值
     * @param filePath 文件路径
     * @return
     */
    public String fileMD5Sync(String filePath){
        if(TextUtils.isEmpty(filePath)){
            return "";
        }
        return fileMD5Sync(new File(filePath));
    }

    /**
     * 获取文件MD5值
     * @param file
     * @return
     */
    public String fileMD5Sync(File file){
        if (file != null && file.exists()) {
            FileInputStream fis = null;
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                fis = new FileInputStream(file);
                MappedByteBuffer byteBuffer = fis.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
                messageDigest.update(byteBuffer);
                BigInteger bigInt = new BigInteger(1, messageDigest.digest());
                String md5 = bigInt.toString(16);
                while(md5.length() < 32){
                    md5 = "0" + md5;
                }
                return md5;
            } catch (NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "";
    }

    /**
     * 获取文件的MD5
     *
     * @param file
     * @return
     */
    public Observable<String> fileMD5Async(final File file) {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(fileMD5Sync(file));
            }
        });
    }

    /**
     * 获取文件MD5
     * @param filePath 文件路径
     * @return
     */
    public Observable<String> fileMD5Async(final String filePath){
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(fileMD5Sync(filePath));
            }
        });
    }



}

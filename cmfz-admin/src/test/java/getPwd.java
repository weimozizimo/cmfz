import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import java.security.MessageDigest;

/**
 * @Description
 * @Author weizimo
 * @Time ${DATA} 19:58.
 */
public class getPwd {
    @Test
    public void test(){
        Md5Hash md5Hash = new Md5Hash("970527","2103",1024);

        System.out.println(md5Hash);
    }
}

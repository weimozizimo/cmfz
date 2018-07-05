import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @Description
 * @Author weizimo
 * @Time ${DATA} 19:58.
 */
public class getPwd {
    @Test
    public void test(){
        String salt = "2103";
        String password = DigestUtils.md5Hex("970527"+salt);
        System.out.println(password);
    }
}

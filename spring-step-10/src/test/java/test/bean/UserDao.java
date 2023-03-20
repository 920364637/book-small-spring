package test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao implements IUserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "Jackson");
        hashMap.put("10002", "Oscar");
        hashMap.put("10003", "Lily");
    }

    @Override
    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}

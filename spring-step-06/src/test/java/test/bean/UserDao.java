package test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, String> dataSet = new HashMap<>();

    static {
        dataSet.put("10001", "Jackson");
        dataSet.put("10002", "Oscar");
        dataSet.put("10003", "Lily");
    }

    public String queryUserName(String uId) {
        return dataSet.get(uId);
    }

}

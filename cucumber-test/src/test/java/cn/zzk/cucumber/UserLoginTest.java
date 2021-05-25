package cn.zzk.cucumber;

import io.cucumber.java.Before;
import io.cucumber.java.zh_cn.假如;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserLoginTest {

    private List<User> users;
    private String username;

    private String password;

    private boolean flag = false;


    @Before
    public void setup() {
        users = Arrays.asList(
                new User().setName("zhagnsan").setPassword("1"),
                new User().setName("lisi").setPassword("2")
        );

    }


    @假如("输入{}和{}")
    public void 输入Username和Password(String username, String password) {
        this.username = username;
        this.password = password;

    }

    @当("用户名密码都正确")
    public void 用户名密码都正确() {
        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                this.flag = true;
                return;
            }
        }
    }

    @那么("登录成功")
    public void 登录成功() {
        assertTrue(flag);
    }
}

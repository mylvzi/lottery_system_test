import tests.BloginTest;
import tests.CreateActivityTest;
import tests.CreatePrizeTest;
import tests.NoLogin;

/**
 * 测试用例执行
 */
public class RunTest {
    public static void main(String[] args) throws InterruptedException {
        BloginTest bloginTest = new BloginTest();
        bloginTest.LoginTest();
        bloginTest.LoginRight();
        bloginTest.LoginSubmitRight();
//        bloginTest.LoginSubmitError1();
//        bloginTest.LoginSubmitError2();
//        bloginTest.LoginSubmitError3();
//        bloginTest.registerTest();

//        CreateActivityTest test = new CreateActivityTest();
//        test.createActivityRight();

        CreatePrizeTest createPrizeTest = new CreatePrizeTest();
        createPrizeTest.createPrizeRight();
//        // 单独测试未登录状态页面
//        NoLogin noLogin = new NoLogin();
//        noLogin.NoLoginTest();
    }
}

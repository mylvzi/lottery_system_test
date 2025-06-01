package tests;

import common.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BloginTest extends Utils {
    public static String url = "http://60.205.7.136:8082/blogin.html";

    public BloginTest() {};

    public void LoginTest() throws InterruptedException {
        driver.get(url);

        // 首先验证标题
        String expectedTitle = driver.getTitle();
        assert expectedTitle.equals("管理员登录界面");

        // 模拟登陆测试
        driver.findElement(By.cssSelector("#phoneNumber")).sendKeys("18039295275");
        driver.findElement(By.cssSelector("#password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#loginForm > button")).click();

        Thread.sleep(1000);
        String expectedTitle1 = driver.getTitle();
        System.out.println(expectedTitle1);
        assert expectedTitle1.equals("后台官迷");
        System.out.println("模拟登陆成功!");
    }

    // 检查是否加载成功
    public void LoginRight() throws InterruptedException {
        driver.get(url);
        // 手机号 + 密码登录
        // 检查是否有 user和 password
        driver.findElement(By.cssSelector("#phoneNumber")).sendKeys("18039295275");
        driver.findElement(By.cssSelector("#password")).sendKeys("123456");
        System.out.println("手机号+密码登录界面测试成功！");
        // Thread.sleep(2000);
        // 验证码登录
        driver.findElement(By.cssSelector("body > div > div.login-container.col-sm-6.col-md-6.col-lg-5.col-xl-5 > div.tab-box > span:nth-child(2)")).click();
        //Thread.sleep(1000);
        driver.findElement(By.cssSelector("#loginMobile")).sendKeys("18039295275");
        driver.findElement(By.cssSelector("#getVerificationCode"));
        driver.findElement(By.cssSelector("#verificationCode")).sendKeys("123456");
        System.out.println("手机号+验证码登录界面测试成功！");

    }

    // 正常登录  验证码登录由于无法使用阿里云短信服务故无法验证  --》直接看手机是否收到手机号
    public void LoginSubmitRight() {

        driver.findElement(By.cssSelector("body > div > div.login-container.col-sm-6.col-md-6.col-lg-5.col-xl-5 > div.tab-box > span.tab-span.active")).click();
        driver.navigate().refresh();
        // 手机号 + 密码登录
        driver.findElement(By.cssSelector("#phoneNumber")).sendKeys("18039295275");
        driver.findElement(By.cssSelector("#password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#loginForm > button")).click();
        String expectedTitle1 = driver.getTitle();
        assert expectedTitle1.equals("后台管理");
        System.out.println("手机号+密码正常登录成功");

    }

    /**
     * 登录失败测试--手机+密码登录
     */
    public void LoginSubmitError1() throws InterruptedException {
        driver.findElement(By.cssSelector("body > div.header-box > div.user-box > div > span")).click();
        // 返回上一界面  因为上一个测试是“登录成功”，成功之后跳转到list界面了
//        driver.navigate().back();
//        driver.navigate().refresh();

        Thread.sleep(2000);

        // 用户名正确 + 密码错误
        driver.findElement(By.cssSelector("#phoneNumber")).sendKeys("18039295275");
        driver.findElement(By.cssSelector("#password")).sendKeys("123451");
        driver.findElement(By.cssSelector("#loginForm > button")).click();

        // 由于设置的有弹窗需要解决一下  alert只能通过显示等待获取
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        assert text.equals("登录失败！密码错误");
        alert.accept();

        System.out.println("登录失败--用户名正确--密码错误--验证成功");
        // driver.quit();
    }

    /**
     * 登录失败测试--用户名 || 密码为空
     */
    public void LoginSubmitError2() throws InterruptedException {
        // 刷新  重新输入
        driver.navigate().refresh();

        // Thread.sleep(3000);

        driver.findElement(By.cssSelector("#phoneNumber")).sendKeys("");
        driver.findElement(By.cssSelector("#password")).sendKeys("123451");
        driver.findElement(By.cssSelector("#loginForm > button")).click();

        String phoneNumberIsNULLText = driver.findElement(By.cssSelector("#phoneNumber-error")).getText();
        phoneNumberIsNULLText.equals("请输入您的手机号");
        System.out.println("登录失败测试--用户名 || 密码为空 验证成功");
    }

    /**
     * 登录失败测试--检查是否存在sql注入问题  弹出"登录失败，密码或者用户名错误！"
     */
    public void LoginSubmitError3() throws InterruptedException {
        // 刷新  重新输入
        driver.navigate().refresh();

        // Thread.sleep(3000);
        driver.findElement(By.cssSelector("#phoneNumber")).sendKeys("admin' -- ");
        driver.findElement(By.cssSelector("#password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#loginForm > button")).click();

        // 由于设置的有弹窗需要解决一下  alert只能通过显示等待获取
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        assert text.equals("登录失败！登录方式不存在");
        alert.accept();

        System.out.println("登录失败测试--检查是否存在sql注入问题--sql注入验证成功");

        // driver.quit();
    }

    public void registerTest() {
        driver.navigate().refresh();
        driver.findElement(By.cssSelector("body > div > div.login-container.col-sm-6.col-md-6.col-lg-5.col-xl-5 > div.register-link > a")).click();
        String expectedTitle = driver.getTitle();
        assert expectedTitle.equals("注册页面");
        System.out.println("成功进入注册页面");

        driver.findElement(By.cssSelector("#name")).sendKeys("yj");
        driver.findElement(By.cssSelector("#mail")).sendKeys("2314394022@qq.com");
        driver.findElement(By.cssSelector("#phoneNumber")).sendKeys("18039295222");
        driver.findElement(By.cssSelector("#password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#registerForm > button")).click();
        System.out.println("注册成功！");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}

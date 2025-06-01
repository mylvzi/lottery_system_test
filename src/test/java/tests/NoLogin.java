package tests;


import common.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * 存放所有“未登录状态"页面的测试用例
 */
public class NoLogin extends Utils {

    public static String adminHtml =  "http://60.205.7.136:8082/admin.html";
    public static String drawHtml =  "http://60.205.7.136:8082/draw.html";


    public NoLogin() {
        super();
    }

    public void NoLoginTest() {
        System.out.println("开始进行未登录状态测试");
        driver.get(adminHtml);

        // 由于设置的有弹窗需要解决一下  alert只能通过显示等待获取
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        assert text.equals("用户未登录, 即将跳转到登录页!");
        alert.accept();

        System.out.println("未登录状态测试成功");

    }
}

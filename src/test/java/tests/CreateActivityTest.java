package tests;

import common.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * 创建活动接口测试
 */
public class CreateActivityTest extends Utils {
    public static String url =  "http://60.205.7.136:8082/admin.html";

    public CreateActivityTest() {
    }

    public void createActivityRight() throws InterruptedException {
        // 假设已经登录
        driver.findElement(By.cssSelector("#createActivity")).click();
        driver.switchTo().frame("contentFrame");
        driver.findElement(By.cssSelector("#activityName")).sendKeys("test2");
        driver.findElement(By.cssSelector("#description")).sendKeys("test2");

        Thread.sleep(2000);
        // 点击圈选奖品--跳出奖品选择模态框（重点是如何对模态框进行补货)
        driver.findElement(By.cssSelector("#buttonPrizes")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement prizeModal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("prizesModal")));
        prizeModal.findElement(By.cssSelector("#prize-20")).click();// 模拟圈选一个奖品
        prizeModal.findElement(By.cssSelector("#prizesModal > div > div.form-btn-box > button.btn.btn-primary")).click();

        Thread.sleep(2000);

        // 点击全选人员
        driver.findElement(By.cssSelector("#buttonUsers")).click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement userModal = wait1.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("usersModal")
        ));
        userModal.findElement(By.cssSelector("#user-41")).click();
        userModal.findElement(By.cssSelector("#usersModal > div > div.form-btn-box > button.btn.btn-primary")).click();

        Thread.sleep(2000);

        driver.findElement(By.cssSelector("#createActivity")).click();
        Alert alert = wait1.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        System.out.println("活动创建成功!");
    }
}

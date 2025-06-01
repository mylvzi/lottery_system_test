package tests;

import common.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreatePrizeTest extends Utils {
    public static String url =  "http://60.205.7.136:8082/admin.html";

    public CreatePrizeTest() {
    }

    public void createPrizeRight() {
        // 假设已经登录
        driver.findElement(By.cssSelector("body > div.cont-box > div.sidebar > ul > li:nth-child(2) > ul > li:nth-child(2) > a")).click();
        driver.switchTo().frame("contentFrame");
        driver.findElement(By.cssSelector("#prizeName")).sendKeys("华为手机");
        driver.findElement(By.cssSelector("#price")).sendKeys("5000");
        driver.findElement(By.cssSelector("#description")).sendKeys("遥遥领先");
        WebElement element = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/input"));
        element.sendKeys("C:\\Users\\绿字\\Desktop\\抽奖系统\\OIP-C (1).jpg");
        driver.findElement(By.cssSelector("body > div > button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        System.out.println("奖品上传成功");
    }

}

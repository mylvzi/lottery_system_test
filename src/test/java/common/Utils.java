package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    // 构造方法  每一个页面都要调用这个类  所以必须要把每个页面的url传输过来
    public Utils() {
        // 1.调用driver对象
        driver = creatDriver();
    }

    // 公用driver对象
    public static WebDriver driver = null;
    /**
     * 1.创建驱动对象
     * 写成一个单例模式  此处采用饿汉模式
     */
    public static WebDriver creatDriver() {
        if(driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            // 允许访问所有的链接
            options.addArguments("--remote-allow-origins=*");

            Map<String, Object> prefs = new HashMap<>();
            // 禁用 Chrome 的密码保存提示
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            // 禁用密码泄露检测（这是关键）
            prefs.put("profile.password_manager_leak_detection", false);

            options.setExperimentalOption("prefs", prefs);

            // 尝试添加其他禁用安全特性的参数，但主要依赖上面的 prefs
            options.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication,SafeBrowseEnhancedProtection,SafeBrowse");
            options.addArguments("--disable-sync"); // 有时候同步设置也会影响
            options.addArguments("--no-default-browser-check"); // 禁用默认浏览器检查


            driver = new ChromeDriver(options);

            // 设置全局的隐式等待
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }

        // System.out.println("驱动对象创建成功!");
        return driver;
    }

    /**
     * 2.屏幕截图
     */
    public static void getScreenShot(String str) throws IOException {
        System.out.println("开始截图");

        // 设置文件格式
        // ./src/test/img/2025-5-25/test01-HHmmmss.png
        SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sim2 = new SimpleDateFormat("HH-mm-ss-SS");// 精确到毫秒

        // 使用时间戳格式化
        String dirTime = sim1.format(System.currentTimeMillis());
        String fileTime = sim2.format(System.currentTimeMillis());

        // 设置保存文件格式
        String fileName = "./src/test/img/" + dirTime + "/" + str + "-" + fileTime + ".png";

        // 截图
        File srcPng = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcPng, new File(fileName));

        System.out.println("截图结束");
    }

}

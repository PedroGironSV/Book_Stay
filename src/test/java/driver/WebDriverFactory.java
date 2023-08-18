package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

    public static WebDriver getDriver(Browser requiredBrowser) {
        WebDriver driver;
        String browserOptions = "--remote-allow-origins=*";
        switch (requiredBrowser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(browserOptions);
                driver = new ChromeDriver(chromeOptions);
                break;
            case EDGE: 
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments(browserOptions);
                driver = new EdgeDriver(edgeOptions);
                break;
            default: 
                throw new IllegalArgumentException("Not supported browser: "+ requiredBrowser);
        }
        return driver;
    }
}

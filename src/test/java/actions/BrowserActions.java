package actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.Driver;
import io.cucumber.java.Scenario;

public class BrowserActions {

    public void explicitWait(By locator) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5), Duration.ofMillis(100));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void fluentWait(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void implicitWait() {
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    public void click(By locator) {
        Driver.getDriver().findElement(locator).click();
    }

    public void increase(By locator, ReservationType type) {
        WebElement increaseCount = Driver.getDriver().findElement(locator);
        switch (type) {
            case ADULTS:
                increaseCount.click();
                break;
            case ROOMS:
                increaseCount.click();
                break;
        }
    }

    public void reduce(By locator, ReservationType type) {
        WebElement reduceCount = Driver.getDriver().findElement(locator);
        switch (type) {
            case ADULTS:
                reduceCount.click();
                break;
            case ROOMS:
                reduceCount.click();
                break;
        }
    }

    public String getCurrentCount(By locator, ReservationType type) {
        WebElement count = Driver.getDriver().findElement(locator);
        switch (type) {
            case ADULTS:
                return count.getAttribute("innerText");
            case ROOMS:
                return count.getAttribute("innerText");
            default:
                return "";
        }
    }

    public void type(By locator, String text) {
        Driver.getDriver().findElement(locator).sendKeys(text);
    }

    public void scrollToElement(By locator) {
        WebElement element = Driver.getDriver().findElement(locator);
        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});", element);
    }

    public String getTextFromElement(By locator) {
        return Driver.getDriver().findElement(locator).getAttribute("innerText");
    }

    public void switchToWindow(int windowIndex) {
        ArrayList<String> windowHandles = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(windowHandles.get(windowIndex));
    }

    public void selectOptionDropdown(By locator, int index) {
        WebElement dropdown = Driver.getDriver().findElement(locator);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    public void markElement(By locator) {
        WebElement targetElement = Driver.getDriver().findElement(locator);
        String elementClass = targetElement.getAttribute("class").replace("\n", " ").trim(), querySelector;
        String elementTag = targetElement.getTagName();
        elementClass = elementClass.replace(" ", ".");
        elementClass = elementClass.replace("..", ".");
        querySelector = "document.querySelector('" + elementTag + "." + elementClass
                + "').style.border='3px solid red'";
        ((JavascriptExecutor) Driver.getDriver()).executeScript(querySelector);
        implicitWait();
    }

    public void takeScreenShot(Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "stepEvidence");
    }

    public static void closeBrowser() {
        try {
            Driver.getDriver().quit();
        } catch (Exception exception) {
            System.err.println("Exception at close browser: " + exception.getMessage());
        }
    }
}

package org.base;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class actions {
    WebDriver driver;

    public boolean webElementClick(WebElement locator, String locatorName) {
        boolean flag = false;
        try {
            locator.click();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Able to click on \""+locatorName+"\"");
            } else {
                System.out.println("Unable to click on \""+locatorName+"\"");
            }
        }}

    public boolean sendkeys(String value,WebElement ele) {
        boolean flag = false;
        try {
            ele.clear();
            ele.sendKeys(value);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("Able to passed the value to the field");
            } else {
                System.out.println("Not able to passed the value to the field");

            }
        }

    }
    public String gettext(WebElement ele)  {
        boolean flag = false;

        String text = ele.getText();

        flag= text.isEmpty();
        if (flag) {
            System.out.println("Current text is not present");
        }
        else
        {
            System.out.println("Current text is: \""+text+"\"");
        }
        return text;

    }
    public boolean JSClick(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            // WebElement element = driver.findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", ele);
            // driver.executeAsyncScript("arguments[0].click();", element);

            flag = true;

        }

        catch (Exception e) {
            throw e;

        } finally {
            if (flag) {
                System.out.println("Click Action is performed");
            } else if (!flag) {
                System.out.println("Click Action is not performed");
            }
        }
        return flag;
    }

    public boolean ActionClick(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            new Actions(driver).click(ele).build().perform();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {

            if (flag) {
                System.out.println("Click Action is performed ");
            } else {
                System.out.println("Click Action is not performed ");
            }
        }

    }
    public void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", ele);

    }

            }


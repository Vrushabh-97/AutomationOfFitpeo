package org.Pages;

import org.base.actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RevenueCalculatorPage {
    private final WebDriver driver;
    private final SoftAssert asserts;
    private final actions action;
    private static final String expectedHomePageText= "Remote Patient";
    private static final String expectedRevenueCalculatorText="Medicare Eligible Patients";
    private static final  String expectedSliderText="MuiSlider";

    JavascriptExecutor script;
    Robot robot;
    @FindBy(xpath = "//h1[contains(text(),\"Remote Patient \")]")
    private WebElement homePageText;
    @FindBy(xpath = "//div[contains(text(),\"Revenue Calculator\")]//parent::a")
    private WebElement revenueCalculator;
    @FindBy(xpath = "//h4[contains(text(),\"Medicare Eligible Patients\")]")
    private WebElement medicareEligibleText;
    @FindBy(xpath = "//span[contains(text(),\"Patients should be between\")]")
    private WebElement patientsText;
    @FindBy(xpath = "//span[contains(text(),\"Patients should be between\")]//parent::div//input[@data-index=\"0\"]//parent::span")
    private WebElement isSliderVisible;
    @FindBy(xpath = "//span[contains(text(),\"Patients should be between\")]//parent::div//input[@type=\"number\"]")
    private WebElement actualBottomTextNumber;

    @FindBy(xpath = "//span[contains(text(),\"Patients should be between\")]//parent::div//input[@data-index=\"0\"]")
    private WebElement sliderControl;
    @FindBy(xpath = "//span[contains(text(),\"Patients should be between\")]//parent::div//input[@data-index=\"0\"]")
    private WebElement sliderControlWidth;
    @FindBy(xpath = "//p[text()=\"CPT-99091\"]//parent::div//child::input")
    private WebElement checkboxOf99091;
    @FindBy(xpath = "//p[text()=\"CPT-99453\"]//parent::div//child::input")
    private WebElement checkboxOf99453;
    @FindBy(xpath = "//p[text()=\"CPT-99454\"]//parent::div//child::input")
    private WebElement checkboxOf99454;
    @FindBy(xpath = "//p[text()=\"Total Recurring Reimbursement for all Patients Per Month:\"]//child::p")
    private WebElement totalRecurringForAllPatients;



    public RevenueCalculatorPage(WebDriver driver) throws AWTException {
        this.driver = driver;
        if (this.driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null.");
        }
        PageFactory.initElements(driver, this);
        this.asserts = new SoftAssert();
        this.action = new actions();
        this.script=(JavascriptExecutor) driver;
        this.robot=new Robot();
    }


    public boolean validateTheHomePage(){
        boolean flag = false;
        String actualHomePageText = homePageText.getText();
        if (actualHomePageText.contains(expectedHomePageText)){
            System.out.println("The test is pass:The user able to navigate to the home Page");
            flag=true;
        }
        else {
            System.out.println("The test is fail : The user not able to navigate to the home Page");
        }
        return flag;
    }
    public boolean navigateToRevenueCalculator(){
        boolean flag = false;
        action.ActionClick(driver,revenueCalculator);
        String actualRevenueCalculatorText = action.gettext(medicareEligibleText);
        if (actualRevenueCalculatorText.contains(expectedRevenueCalculatorText)){
            System.out.println("The test is pass:The user able to navigate to the Revenue Calculator");
            flag=true;
        }
        else {
            System.out.println("The test is fail : The user not able to navigate to the Revenue Calculator");
        }
        return flag;
    }

    public boolean ValidateTheSliderBarIsVisible(){
        script.executeScript("window.scrollBy(0, 350);");
        boolean flag=false;
        String actualSliderText = isSliderVisible.getAttribute("class");
        if (actualSliderText.contains(expectedSliderText))
        {
            System.out.println("The test is pass: The slider is displaying");
            flag=true;
        }
        else {
            System.out.println("The test is fail : The slider is not displaying");
        }
        return flag;
    }

    int numericValue;
    public int WidthOfSlider(){
        WebDriverWait wait=new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOf(sliderControlWidth));
        String actualWidth = sliderControlWidth.getAttribute("aria-valuenow");
        System.out.println(actualWidth);
        // Convert the numeric part to an integer or double
        numericValue = Integer.parseInt(actualWidth);
        System.out.println("Numeric Value: " + numericValue);
        return numericValue;
    }
    String bottomTextNumberBeforeSliderChange;
    public boolean ValidateTheBottomTextValueWhenUserUpdateTheSliderTo820() throws InterruptedException, AWTException {
        boolean flag = false;
        Actions actions=new Actions(driver);
        actions.doubleClick(actualBottomTextNumber).click().build().perform();
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        action.sendkeys("817",actualBottomTextNumber);
        bottomTextNumberBeforeSliderChange = actualBottomTextNumber.getAttribute("value");
        System.out.println("Bottom Text Number Before Slider Change  " +bottomTextNumberBeforeSliderChange);
        int xValue = sliderControl.getLocation().getX();
        int yValue = sliderControl.getLocation().getY();
        Thread.sleep(3000);
        System.out.println(xValue);
        System.out.println(yValue);
        actions.doubleClick(medicareEligibleText).build().perform();
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        if (numericValue==820) {
            System.out.println("The slider already at 820 ");
        }
        else if (numericValue<820) {
            for (int i = xValue; i < 10000; i++) {
                Thread.sleep(1000);
                robot.keyPress(KeyEvent.VK_RIGHT);
                Thread.sleep(1000);
                robot.keyRelease(KeyEvent.VK_RIGHT);
                int newValue = WidthOfSlider();
                if (newValue == 820) {
                    System.out.println("The slider reach to 820 ");
                    flag=true;
                    break;
                }
            }
        }
        else if (numericValue>820) {
            for (int i = xValue; i > 0; i--) {
                Thread.sleep(3000);
                actions.moveToElement(sliderControl).doubleClick().build().perform();
                action.JSClick(driver,sliderControl);
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
                int newValue = WidthOfSlider();
                if (newValue == 820) {
                    flag=true;
                    System.out.println("The slider reach to 820 ");
                    break;
                }
            }
        }
        String bottomTextNumberAfterSliderChange = actualBottomTextNumber.getAttribute("value");
        if (!bottomTextNumberBeforeSliderChange.equals(bottomTextNumberAfterSliderChange))
        {
            System.out.println("The test is pass: As user has change the slider position from 817 to 820");
            flag=true;
        }
        else {
            System.out.println("The test is fail : As After changing the slider bottom text value not changed");
        }
        return flag;
    }
    public boolean ValidateTheSliderPositionWhenUserUpdateTheBottomTextValue() throws AWTException {
        boolean flag = false;
        String actualPositionOfSliderBeforeBottomTextChanged = sliderControlWidth.getAttribute("aria-valuenow");
        System.out.println("Actual Position Of Slider Before Bottom Text Changed" +actualPositionOfSliderBeforeBottomTextChanged);
        String bottomTextNumberBeforeChange = actualBottomTextNumber.getAttribute("value");
        System.out.println("Bottom Text Number Before Update  " +bottomTextNumberBeforeChange);
        Actions actions=new Actions(driver);
        actions.doubleClick(actualBottomTextNumber).click().build().perform();
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        action.sendkeys("560",actualBottomTextNumber);
        String actualWidthAfterBottomTextChanged = sliderControlWidth.getAttribute("aria-valuenow");
        System.out.println("The update slider position is :"+ "  "+ actualWidthAfterBottomTextChanged);
        if (!actualPositionOfSliderBeforeBottomTextChanged.equals(actualWidthAfterBottomTextChanged)){
            System.out.println("The test is pass : The slider position has changed once user update the bottom text value"+ " " + "Updated value" +"  "+actualWidthAfterBottomTextChanged);
            flag=true;
        }
        else {
            System.out.println("The test is fail : The slider position has not changed once user update the bottom text value");
        }
        return flag;
    }
    public boolean validateTotalRecurringForAllPatientsValue() throws InterruptedException {
        boolean flag = false;
        script.executeScript("window.scrollBy(0, 150);");
        Thread.sleep(2000);
        String beforeSelectingTheCheckbox = action.gettext(totalRecurringForAllPatients);
        System.out.println("Before selecting the checkbox total recurring value " +" " +beforeSelectingTheCheckbox);
        action.webElementClick(checkboxOf99091,"checkboxOf99091");
        action.webElementClick(checkboxOf99453,"checkboxOf99453");
        action.scrollByVisibilityOfElement(driver,checkboxOf99091);
        action.webElementClick(checkboxOf99454,"checkboxOf99454");
        String afterSelectingTheCheckbox = action.gettext(totalRecurringForAllPatients);
        if (!beforeSelectingTheCheckbox.equals(afterSelectingTheCheckbox)) {
            System.out.println("The test is pass : The value is update upon selecting the required checkbox and total recurring for all patients value"+"  "+totalRecurringForAllPatients);
            flag=true;
        }
        else {
            System.out.println("The test is fail : The value is not update upon selecting the required checkbox  ");

        }
        return flag;
    }

}


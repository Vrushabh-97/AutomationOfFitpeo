package testScripts;

import org.Pages.RevenueCalculatorPage;
import org.base.launchBrowser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;

public class ValidationOfRevenueCalculationPage extends launchBrowser {
SoftAssert asserts=new SoftAssert();
    @Test(priority = 0)
    public void validateTheHomePage() throws AWTException {
        RevenueCalculatorPage home=new RevenueCalculatorPage(driver);
        boolean isHomePage = home.validateTheHomePage();
        asserts.assertTrue(isHomePage,"The user not able to navigate to the home Page");

    }
    @Test(priority = 1)
    public void validateRevenuePage() throws AWTException {
        RevenueCalculatorPage home=new RevenueCalculatorPage(driver);
        boolean isNavigated = home.navigateToRevenueCalculator();
        asserts.assertTrue(isNavigated,"The user not able to navigate to the Revenue Calculator");

    }
   @Test(priority = 2)
    public void validateSlider() throws InterruptedException, AWTException {
        RevenueCalculatorPage home=new RevenueCalculatorPage(driver);
        boolean isSliderVisible = home.ValidateTheSliderBarIsVisible();
        asserts.assertTrue(isSliderVisible,"The slider is not visible");
    }

    @Test(priority = 3)
    public void validateTheBottomTextValueWhenUserUpdateTheSliderTo820() throws AWTException, InterruptedException {
        RevenueCalculatorPage home=new RevenueCalculatorPage(driver);
        boolean isBottomTextUpdatedUponSliderChanged = home.ValidateTheBottomTextValueWhenUserUpdateTheSliderTo820();
        asserts.assertTrue(isBottomTextUpdatedUponSliderChanged,"The Bottom text value not changed upon slider position changed ");
    }
    @Test(priority = 4)
    public void validateTheSliderPositionWhenUserUpdateTheBottomTextValue() throws AWTException {
        RevenueCalculatorPage home=new RevenueCalculatorPage(driver);
        boolean isSliderPositionChangedUponBottomTextValueChanged = home.ValidateTheSliderPositionWhenUserUpdateTheBottomTextValue();
        asserts.assertTrue(isSliderPositionChangedUponBottomTextValueChanged,"Slider position not changed upon bottom text changed");
    }
    @Test(priority = 5)
    public void validateTotalRecurringForAllPatientsValue() throws AWTException, InterruptedException {
        RevenueCalculatorPage home=new RevenueCalculatorPage(driver);
        boolean isTotalRecurringValueUpdatedUponSelectingTheCheckboxes =home.validateTotalRecurringForAllPatientsValue();
        asserts.assertTrue(isTotalRecurringValueUpdatedUponSelectingTheCheckboxes,"The recurring value of all Patients not changed");

    }
}

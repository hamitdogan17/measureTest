package stepdefinations;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.List;
import java.util.Map.Entry;

public class Scenarios {

    WebDriver driver;

    //public Scenarios() {
      //this.driver = getDriver();
    //}

    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                String testName = scenario.getName();
                scenario.embed(screenshot, "image/png");
                scenario.write(testName);
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
            driver.quit();
        }
    }

    @Given("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to(String url) {
        driver.get(url);
    }

    @When("^I wait for (\\d*) seconds?$")
    public void wait_for(int seconds) throws Throwable {
        Thread.sleep(seconds * 1000);
    }

    @Then("^Exit from webpage$")
    public void exitFromWebpage() {
        driver.quit();
    }

    @Given("^Navigate to web page as \"([^\"]*)\"$")
    public void navigateToWebPageAs(String url) {
        WebDriverManager.chromedriver().version("2.42").setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    @And("^I wait \"([^\"]*)\"$")
    public void iWaited_id(String expression) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        if (driver.findElements(By.id(expression)).size() == 1) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(expression)));
        } else
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(expression)));
    }

    @And("^Click to button of \"([^\"]*)\"$")
    public void iClickLinkText(String text) {
        if (driver.findElements(By.partialLinkText(text)).size() > 0)
            driver.findElement(By.partialLinkText(text)).click();
        else if (driver.findElements(By.id(text)).size() > 0)
            driver.findElement(By.id(text)).click();
        else if (driver.findElements(By.cssSelector(text)).size() > 0)
            driver.findElement(By.cssSelector(text)).click();
        else
            driver.findElement(By.name(text)).click();
    }



    @And("^I wait link \"([^\"]*)\"$")
    public void iWaited_link(String expression) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        if (driver.findElements(By.partialLinkText(expression)).size() > 0)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(expression)));
        else
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(expression)));
    }

    @And("^I wait and click \"([^\"]*)\"$")
    public void iWait_click_id(String expression) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        if (driver.findElements(By.id(expression)).size() == 1) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(expression))).click();
        } else
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(expression))).click();
    }

    @And("^I wait and click link \"([^\"]*)\"$")
    public void iWaited_click_link(String expression) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        if (driver.findElements(By.partialLinkText(expression)).size() > 0)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(expression))).click();
        else
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(expression))).click();
    }


  /*  @When("^I click ([\\w ]+)$")
    public void i_click_link(String key) {
        String selector = selectors.getProperty(key);
        if (selector.startsWith("/")) {
            driver.findElement(By.xpath(selector)).click();
        } else {
            driver.findElement(By.cssSelector(selector)).click();
        }
    }*/


    @When("^I fill \"([^\"]*)\" as \"([^\"]*)\"$")
    public void i_fill_as(String fieldName, String fieldValue) {
        if (driver.findElements(By.id(fieldName)).size() == 1) {
            driver.findElement(By.id(fieldName)).sendKeys(fieldValue);
        } else driver.findElement(By.name(fieldName)).sendKeys(fieldValue);
    }

    @When("^I fill$")
    public void i_fill(DataTable dataTable) {
        for (Entry<String, String> row : dataTable.asMap(String.class, String.class).entrySet()) {
            if (driver.findElements(By.id(row.getKey())).size() == 1) {
                driver.findElement(By.id(row.getKey())).sendKeys(row.getValue());
            } else driver.findElement(By.name(row.getKey())).sendKeys(row.getValue());
        }
    }

    @When("^I select ([^\"]*) as ([^\"]*)$")
    public void i_select(String field, String choice) {
        new Select(driver.findElement(By.name(field))).selectByVisibleText(choice);

    }

    @Then("^I should see text \"([^\"]*)\"$")
    public void i_see(String text) {
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(text));
    }


    public void isImageBroken(WebElement image) {
        if (image.getAttribute("naturalWidth").equals("0")) {
            System.out.println(image.getAttribute("outerHTML") + " is broken.");
            Reporter.log(image.getAttribute("outerHTML") + " is broken.");
        }
    }

    @And("^Check broken images$")
    public void check_broken_image() {

        List<WebElement> imagesList = driver.findElements(By.tagName("img"));
        System.out.println("Total number of images are " + imagesList.size());
        Reporter.log("Total number of images are " + imagesList.size());

        for (WebElement image : driver.findElements(By.cssSelector("img"))) {
            isImageBroken(image);
        }
    }

    @When("^Assert id \"([^\"]*)\" and text \"([^\"]*)\"$")
    public void id_text_assertion(String r_id, String expected) {

        List<WebElement> listElements = driver.findElements(By.id(r_id));
        boolean isFound = false;

        for (WebElement el : listElements) {
            //System.out.println("All list elements\n" + el.getText());
            if (el.getText().equalsIgnoreCase(expected)) {
                System.out.println(expected + " is displayed\n");
                isFound = true;
                break;
            }
        }
        if (!isFound)
            org.testng.Assert.assertEquals(r_id, expected);
    }

    @When("^Assert id \"([^\"]*)$")
    public void getText(String r_id) {

        String listElements = driver.findElement(By.id(r_id)).getText();
        System.out.println(listElements);
    }


    @Then("^Check pop up message of \"([^\"]*)\"$")
    public void checkPopUpMessageOf(String text) throws InterruptedException {
        Thread.sleep(3000);
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(text));
    }

    @And("^12.2.2 Select tab of \"([^\"]*)\"$")
    public void selectTabOf(String text) throws InterruptedException{
        if (driver.findElements(By.partialLinkText(text)).size() > 0)
            driver.findElement(By.partialLinkText(text)).click();
        else if (driver.findElements(By.id(text)).size() > 0)
            driver.findElement(By.id(text)).click();
        Thread.sleep(10000);
       // else
//            driver.findElement(By.cssSelector(text)).click();
    }

    @Given("^Fill e-mail textbox as \"([^\"]*)\"$")
    public void fillEPostaTextboxAs(String fieldValue) {
        if (driver.findElements(By.id("email")).size() == 1) {
            driver.findElement(By.id("email")).clear();
            driver.findElement(By.id("email")).sendKeys("fieldValue");
        } else {
            driver.findElement(By.name("email")).clear();
            driver.findElement(By.name("email")).sendKeys(fieldValue);
        }
    }

    @Given("^Fill name textbox as \"([^\"]*)\"$")
    public void fillNameTextboxAs(String fieldValue) {
        if (driver.findElements(By.id("name")).size() == 1) {
            driver.findElement(By.id("name")).clear();
            driver.findElement(By.id("name")).sendKeys("fieldValue");
        } else {
            driver.findElement(By.name("name")).clear();
            driver.findElement(By.name("name")).sendKeys(fieldValue);
        }
    }

    @Given("^Fill linkedIn textbox as \"([^\"]*)\"$")
    public void filllinkedInTextboxAs(String fieldValue) {
        if (driver.findElements(By.id("linkedIn")).size() == 1) {
            driver.findElement(By.id("linkedIn")).clear();
            driver.findElement(By.id("linkedIn")).sendKeys("fieldValue");
        } else {
            driver.findElement(By.name("linkedIn")).clear();
            driver.findElement(By.name("linkedIn")).sendKeys(fieldValue);
        }
    }




    @When("^Enter email and paşşword$")
    public void enterEmailAndPassword(DataTable dataTable) {
        for (Entry<String, String> row : dataTable.asMap(String.class, String.class).entrySet()) {
            if (driver.findElements(By.id(row.getKey())).size() == 1) {
                driver.findElement(By.id(row.getKey())).sendKeys(row.getValue());
            } else driver.findElement(By.name(row.getKey())).sendKeys(row.getValue());
        }
    }

    @When("^Enter name and surname$")
    public void enterNameAndSurname(DataTable dataTable) {
        for (Entry<String, String> row : dataTable.asMap(String.class, String.class).entrySet()) {
            if (driver.findElements(By.id(row.getKey())).size() == 1) {
                driver.findElement(By.id(row.getKey())).sendKeys(row.getValue());
            } else driver.findElement(By.name(row.getKey())).sendKeys(row.getValue());
        }
    }

    @When("^Enter linkedIn$")
    public void enterLınkedIn(DataTable dataTable) {
        for (Entry<String, String> row : dataTable.asMap(String.class, String.class).entrySet()) {
            if (driver.findElements(By.id(row.getKey())).size() == 1) {
                driver.findElement(By.id(row.getKey())).sendKeys(row.getValue());
            } else driver.findElement(By.name(row.getKey())).sendKeys(row.getValue());
        }
    }

    @Then("^Check the message on screen \"([^\"]*)\"$")
    public void checkTheMessageOnScreen(String text) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginError")));
        Thread.sleep(4000);
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(text));
    }

    @And("^Click tab menu item of \"([^\"]*)\"$")
    public void clickTabMenuItemOf(String text) {
        if (driver.findElements(By.partialLinkText(text)).size() > 0)
            driver.findElement(By.partialLinkText(text)).click();
        else if (driver.findElements(By.id(text)).size() > 0)
            driver.findElement(By.id(text)).click();
        else
            driver.findElement(By.cssSelector(text)).click();
    }

    @And("^Click to the product of \"([^\"]*)\"$")
    public void clickToTheProductOf(String text) throws Throwable {
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        if (driver.findElements(By.partialLinkText(text)).size() > 0)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(text))).click();
        else if (driver.findElements(By.id(text)).size() > 0)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(text))).click();
        //else
       //     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(text))).click();
    }


    @Then("^Check e-mail password to proceed home page without any error$")
    public void checkEPostaSifreToProceedHomePageWithoutAnyError() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/nav[1]/ul/li/a/div")));
    }

    @Given("^User should be logged in$")
    public void userShouldBeLoggedIn() throws Throwable {
        navigateToWebPageAs("https://arikovani.com/");
        iClickLinkText("Giriş Yap");
//        selectTabOf("E-posta ile Giriş Yap");
        i_fill_as("email", "caglar23@gmail.com");
        i_fill_as("password", "492364");
        iClickLinkText("submit");
        checkEPostaSifreToProceedHomePageWithoutAnyError();
    }
}
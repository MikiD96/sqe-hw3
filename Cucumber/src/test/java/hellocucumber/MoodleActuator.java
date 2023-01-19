package hellocucumber;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MoodleActuator {
    private WebDriver driver;
    private WebDriverWait wait;


    public void initSession(String webDriver, String path){
        // webDriver = "webdriver.chrome.driver"
        // path = "C:\\Users\\eylon\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new EdgeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        // launch website -> localhost
        driver.get("http://localhost/moodle");
        //driver.get("https://web.whatsapp.com/");
        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();


        /*
            If we wanted to test the web application on different devices -
                1. Open the web app
                2. Right click -> click inspect
                3. Click on the phone icon at the top left corner of the inspect window (the app changes preview format at this point)
                4. Locate the dimensions drop-down list at the top of the web app and choose device
                5. Copy dimensions size (on the right side of the drop-down list)
                   -> driver.manage().window().setSize(new Dimension(width, height));
         */

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    public void goToLogin(){
        // locate and click on web element -> login
        driver.findElement(By.linkText("Log in")).click();
    }

    public void enterLoginInfo(String username, String password) {
        // locate the username input box and enter username
        // $x("//*[@id='username']")
        // driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']"))).sendKeys(username);

        // locate the password input box and enter password
        // $x("//*[@name='password' and @type='password']")
        driver.findElement(By.xpath("//*[@name='password' and @type='password']")).sendKeys(password);

        // locate Log in button and press
        // $x("//*[@id='loginbtn']")
        driver.findElement(By.id("loginbtn")).click();
    }

    public void WelcomeMessage(){
        // now to check if login process succeeded -> find the element which indicates it succeeded
        // $x("//*[contains(text(),'Welcome, Teacher!')]")
        driver.findElement(By.xpath("//*[contains(text(),'Welcome back,')]")); //TODO CHANGE NAME
    }

    public void loginSequence(String username, String password){
        // locate and click on web element -> login
        goToLogin();

        // enter username and password -> press login
        enterLoginInfo(username, password);

        // check for welcome message
        WelcomeMessage();
    }

    public void logout(){
        // find user menu -> click on it
        driver.findElement(By.id("user-menu-toggle")).click();

        // find log out button in drop down menu -> click on it
        driver.findElement(By.linkText("Log out")).click();

    }




    public void editModeOn(){
        // find edit mode switch -> click on it
        // $x("//*[@type='checkbox' and @name='setmode']")
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='checkbox' and @name='setmode']"))).click();
    }

    public void myCoursesTab(){
        // example of how we can save the element and then operate with it
        // find my courses -> click on it
        // $x("//*[contains(text(),'My courses') and @role='menuitem']")
        WebElement myCoursesTab = driver.findElement(By.xpath("//*[contains(text(),'My courses') and @role='menuitem']"));
        myCoursesTab.click();
    }


    public void goToCourse(String courseName){
        // find course -> click on it
        // $x("//*[@class='multiline' and contains(text(),'Demo course')]")[0].click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='multiline' and contains(text(),'" + courseName + "')]"))).click();
    }



    public void terminateDriver(){
        // close all the driver windows
        // another option - to close a browser window: driver.close();
        driver.quit();

    }


    public boolean goToForum() {

        try{
            WebElement elem = driver.findElement(By.linkText("MyForum"));
            elem.click();
            return true;
        }catch (Exception e){
            return false;
        }
        //driver.findElement(By.xpath("//*[@ihref='http://localhost/moodle/mod/forum/view.php?id=1']")).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='multiline' and contains(text(),'" + courseName + "')]"))).click();
    }

    public void ChangeMyPermissions() {
        driver.findElement(By.xpath("//*[@id='user-menu-toggle']")).click();
        driver.findElement(By.xpath("//*[@href='http://localhost/moodle/course/switchrole.php?id=2&switchrole=-1&returnurl=%2Fcourse%2Fview.php%3Fid%3D2']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Teacher')])"))).click();
        driver.findElement(By.xpath("//*[@name='setmode']")).click();

    }

    public void removeForum() {
        //driver.findElement(By.xpath("//*[@id='action-menu-toggle-2' and @class='btn btn-icon d-flex align-items-center justify-content-center dropdown-toggle icon-no-margin']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("action-menu-toggle-2"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Delete')])"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Yes') and @class='btn btn-primary'])"))).click();
        //driver.findElement(By.xpath("//*[@href='http://localhost/moodle/course/mod.php?sesskey=cv0D7EuX75&sr=0&delete=1']")).click();
    }

    public void openForum() {
        editModeOn();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class = 'btn btn-link text-decoration-none section-modchooser section-modchooser-link activity-add d-flex align-items-center p-3 mb-3' and @data-sectionid='0']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title='Add a new Forum']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_name"))).sendKeys("MyForum");
        driver.findElement(By.xpath("//*[@name='submitbutton2']")).click();
    }

    public void ChangeMyPermissionsToStudent() {
        driver.findElement(By.xpath("//*[@id='user-menu-toggle']")).click();
        driver.findElement(By.linkText("Switch role to...")).click();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='Switch role to...']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Student')])"))).click();
    }

    public boolean CommentOnForum() {
        if(!goToForum()){
            return false;
        }
        driver.findElement(By.linkText("Add discussion topic")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("subject"))).sendKeys("MyComment");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_messageeditable"))).sendKeys("MyComment Description");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_submitbutton")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.findElement(By.id("id_submitbutton")).click();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Post to forum')])"))).click();
        return true;
    }

    public void checkIfCommentExists() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'MyComment')])")));
    }
}



    // to clear users input from text box: driver.findElement(By.name("q")).clear();
    // to navigate backward in browser history: driver.navigate().back();
    // to navigate forward in browser history: driver.navigate().forward();
    // to refresh a web page: driver.navigate().refresh();
    // to close a browser: driver.close();
    // to close a browser and all other windows associated with the driver: driver.quit();
    // to move between windows: driver.switchTo().window("windowName");
    // to move between frames: driver.switchTo().frame("frameName");
    // to drag and drop elements:
    //     WebElement element = driver.findElement(By.name("source"));
    //     WebElement target = driver.findElement(By.name("target"));
    //     (new Actions(driver)).dragAndDrop(element, target).perform();

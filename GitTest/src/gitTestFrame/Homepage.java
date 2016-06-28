package gitTestFrame;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
 
public class Homepage {
               
                WebDriver driver;
               
                // Find all of the elements and assign them names
               
                @FindBy (xpath="//*[@id='originStation']")
                WebElement from;
               
                @FindBy (xpath="//*[@id='destinationStation']")
                WebElement to;
               
                
                //Tomorrow
                @FindBy (xpath=".//*[@id='extendedSearchForm']/div[2]/div[1]/div/div[1]/button[2]")
                WebElement tomorrow;
                @FindBy (xpath="//*[@id='outDate']")
                WebElement outDate;
                @FindBy (xpath=".//*[@id='ui-datepicker-div']")
                WebElement calendar;
                
                //add return
                @FindBy (xpath="//*[@id='add-return']")
                WebElement addReturn;
                
                //NextDay
                @FindBy (xpath=".//*[@id='extendedSearchForm']/div[2]/div[2]/div/div[1]/button[2]")
                WebElement nextDay;
                @FindBy (xpath="//*[@id='returnDate']")
                WebElement returnDate;
                
                //uncheck Oneway
                @FindBy (xpath=".//*[@id='isOneWay']")
                WebElement oneWay;
                
                //Search
                @FindBy (xpath="//*[@id='submitButton']")
                WebElement submit;
                
                //Check timetable
                @FindBy (xpath="//*[@id='timetable']/div[2]")
                WebElement timetable;
               
                
                
                
                public Homepage(WebDriver driver){
                               
                                this.driver = driver;
                                System.out.println("Homepage object has been created");
                               
                                // When the object is created, all of the variables will be set up
                               
                                PageFactory.initElements(driver, this);
                }
               
                // Exercise 5
                public void setOutDate(Integer DaysInFuture) {
                               
                                //Find out current date
                                String presentDay = null;
                                int days = 0;
                                int twoDays = 0;
                                List<WebElement> calendarPresent = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/div[1]"));
                                for (WebElement thisCal : calendarPresent) {
                                                System.out.println(thisCal.getText());
                                                if (thisCal.findElement(By.className("ui-datepicker-today")).isDisplayed()) {
                                                                presentDay = (thisCal.findElement(By.className("ui-datepicker-today")).getText());
                                                                break;
                                                }
                                }
                               
                                // How many days in advance from the start of the month
                                int i = Integer.parseInt(presentDay);
                                DaysInFuture = i + DaysInFuture;
                               
                                //Check to see how many days are in the current month
                               
                                List<WebElement> calendarDays = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/div[1]"));
                                for (WebElement thisCal : calendarDays) {
                                                System.out.println(thisCal.getText());
                                                if (thisCal.findElement(By.linkText("31")).isDisplayed()) {
                                                                days = 31;
                                                                twoDays = 62;
                                                                System.out.println("Days in month" + days);
                                                               
                                                } else if (thisCal.findElement(By.linkText("30")).isDisplayed()) {
                                                                days = 30;
                                                                twoDays = 61;
                                                                System.out.println("Days in month" + days);
                                                               
                                                } else if (thisCal.findElement(By.linkText("28")).isDisplayed()) {
                                                                days = 28;
                                                                twoDays = 61;
                                                                System.out.println("Days in month: " + days);
                                                               
                                                }
                                }
                               
                                // Conditions on which month to choose depending on how many days in the future
                                if (DaysInFuture > (days - i) && (DaysInFuture <= (twoDays - days))) {
                                                System.out.println("2: " + DaysInFuture); //debugging
                                } else if (DaysInFuture >= twoDays) {
                                                DaysInFuture = DaysInFuture - (twoDays - 1);
                                                driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/a/span")).click();
                                                driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/a/span")).click();
                                                System.out.println("4: " + DaysInFuture); //debugging
                                } else {
                                                DaysInFuture = DaysInFuture - days;
                                                driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/a/span")).click();
                                                System.out.println("3: " +DaysInFuture); //debugging
                                }
                               
                                //convert the number of days into a searchable string
                                String date = DaysInFuture.toString();
                                System.out.println(date);
                               
                                //Search through the calendar for the correct date
                                List<WebElement> calendars = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/div[1]"));
                                for (WebElement thisCal : calendars) {
                                                System.out.println(thisCal.getText());
                                                if (thisCal.findElement(By.linkText(date)).isDisplayed()) {
                                                                thisCal.findElement(By.linkText(date)).click();
                                                                break;
                                                }
                                }
                }
                                public void setReturnDate(Integer DaysInFuture) {
                                    
                                    //Find out current date
                                    String presentDay = null;
                                    int days = 0;
                                    int twoDays = 0;
                                    List<WebElement> calendarPresent = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/div[1]"));
                                    for (WebElement thisCal : calendarPresent) {
                                                    System.out.println(thisCal.getText());
                                                    if (thisCal.findElement(By.className("ui-datepicker-days-cell-over")).isDisplayed()) {
                                                                    presentDay = (thisCal.findElement(By.className("ui-datepicker-days-cell-over")).getText());
                                                                    break;
                                                    }
                                    }
                                   
                                    // How many days in advance from the start of the month
                                    int i = Integer.parseInt(presentDay);
                                    DaysInFuture = i + DaysInFuture;
                                   
                                    //Check to see how many days are in the current month
                                   
                                    List<WebElement> calendarDays = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/div[1]"));
                                    for (WebElement thisCal : calendarDays) {
                                                    System.out.println(thisCal.getText());
                                                    if (thisCal.findElement(By.linkText("31")).isDisplayed()) {
                                                                    days = 31;
                                                                    twoDays = 62;
                                                                    System.out.println("Days in month" + days);
                                                                   
                                                    } else if (thisCal.findElement(By.linkText("30")).isDisplayed()) {
                                                                    days = 30;
                                                                    twoDays = 61;
                                                                    System.out.println("Days in month" + days);
                                                                   
                                                    } else if (thisCal.findElement(By.linkText("28")).isDisplayed()) {
                                                                    days = 28;
                                                                    twoDays = 61;
                                                                    System.out.println("Days in month: " + days);
                                                                   
                                                    }
                                    }
                                   
                                    // Conditions on which month to choose depending on how many days in the future
                                    if (DaysInFuture > (days - i) && (DaysInFuture <= (twoDays - days))) {
                                                    System.out.println("2: " + DaysInFuture); //debugging
                                    } else if (DaysInFuture >= twoDays) {
                                                    DaysInFuture = DaysInFuture - (twoDays - 1);
                                                    driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/a/span")).click();
                                                    driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/a/span")).click();
                                                    System.out.println("4: " + DaysInFuture); //debugging
                                    } else {
                                                    DaysInFuture = DaysInFuture - days;
                                                    driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/a/span")).click();
                                                    System.out.println("3: " +DaysInFuture); //debugging
                                    }
                                   
                                    //convert the number of days into a searchable string
                                    String date = DaysInFuture.toString();
                                    System.out.println(date);
                                   
                                    //Search through the calendar for the correct date
                                    List<WebElement> calendars = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/div[1]"));
                                    for (WebElement thisCal : calendars) {
                                                    System.out.println(thisCal.getText());
                                                    if (thisCal.findElement(By.linkText(date)).isDisplayed()) {
                                                                    thisCal.findElement(By.linkText(date)).click();
                                                                    break;
                                                    }
                                    }
                }
}
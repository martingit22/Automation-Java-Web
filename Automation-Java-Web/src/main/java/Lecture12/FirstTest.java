package Lecture12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FirstTest {
    public static void main(String[] args) {
        // myFirstWebBrowser();
        SoftuniLogin();
    }

    public static void myFirstWebBrowser() {
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.get("https://softuni.bg/");
        webDriver.manage().window().maximize();
        webDriver.close();

    }

        public static void SoftuniLogin() {
            // Инициализация на WebDriverManager за ChromeDriver
            WebDriverManager.chromedriver().setup();

            // Инициализация на WebDriver
            WebDriver driver = new ChromeDriver();

            try {
                // Навигиране към сайта на SoftUni
                driver.get("https://softuni.bg");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Кликване върху бутона за вход
                WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login-btn")));
                loginButton.click();

                // Изчакване на полето за потребителско име да се появи
                WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

                // Въвеждане на потребителско име
                usernameField.sendKeys("martinpanaiotov");

                // Въвеждане на парола
                WebElement passwordField = driver.findElement(By.id("password-input"));
                passwordField.sendKeys("loginlogin12");

                // Кликване върху бутона за вход
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("softuni-btn")));
                submitButton.click();

                // Изчакване на успешен вход или проверка за грешка
                wait.until(ExpectedConditions.urlContains("dashboard"));

                System.out.println("Успешен вход!");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Затваряне на браузъра
                driver.quit();
            }
        }
    }
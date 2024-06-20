package Lecture12;

import org.openqa.selenium.chrome.ChromeDriver;
public class FirstTest {
    public static void main(String[] args) {
        myFirstWebBrowser();
    }
    public static void myFirstWebBrowser(){
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.get("https://softuni.bg/");
        webDriver.manage().window().maximize();
        webDriver.close();
    }
}

package ru.yandex.practicum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.page.MainPage;
import ru.yandex.practicum.page.OrderStatusPage;
import ru.yandex.practicum.util.WebDriverUtil;

import java.time.Duration;

import static org.junit.Assert.*;

public class ClickHandlerTest {
    //Константа для выбора браузера в котором тестируем
    private static final String BROWSER = "chrome";
    private static final String URL_SCOOTER="https://qa-scooter.praktikum-services.ru";
    private WebDriver driver;
    MainPage mainPage;
    OrderStatusPage orderStatusPage;

    @Before
    public void webDriver() {
        driver = WebDriverUtil.getWebDriver(BROWSER);
        driver.get(URL_SCOOTER);
        mainPage = new MainPage(driver);
        orderStatusPage = new OrderStatusPage(driver);
    }

    //Тестирование нажатия на логотип "Самоката""
    @Test
    public void clickLogoScooterTest() {
        String expectedUrl = driver.getCurrentUrl();
        mainPage.headerLogoScooterClick();
        assertEquals("Пользователь не попал на главную страницу 'Самоката'", expectedUrl, driver.getCurrentUrl());
    }

    //Тестирование нажатия на логотип "Яндекс"
    @Test
    public void clickLogoYandexTest() {
        String expectedUrl = driver.findElement(mainPage.getHeaderLogoYandex()).getAttribute("href");
        mainPage.headerLogoYandexClick();
        assertEquals("Пользователь не попал на главную страницу 'Yandex'", expectedUrl, driver.getCurrentUrl());
    }

    //Тестирование статуса заказа если ввели неправильный номер заказа
    @Test
    public void clickOrderStatusTest() {
        mainPage.clickOrderStatusButton("111911");
        assertTrue(orderStatusPage.noFoundOrderText());
    }

    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}
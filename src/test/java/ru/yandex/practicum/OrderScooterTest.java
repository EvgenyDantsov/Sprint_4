package ru.yandex.practicum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.page.MainPage;
import ru.yandex.practicum.page.OrderPage;
import ru.yandex.practicum.util.WebDriverUtil;

import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class OrderScooterTest {
    //Константа для выбора браузера в котором тестируем
    private static final String BROWSER = "chrome";
    private static final String URL_SCOOTER="https://qa-scooter.praktikum-services.ru";
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String courierComment;
    private WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;

    public OrderScooterTest(String firstName, String lastName, String address,
                            String metroStation, String phone, String date,
                            String rentalPeriod, String scooterColor, String courierComment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.courierComment = courierComment;
    }

    @Parameterized.Parameters(name = "Test {index}")
    public static Object[][] getAccordionHeader() {
        return new Object[][]{
                {"Иван", "Петрович", "ул. Ленинская 15", "Черкизовская", "+375291232526",
                        "12.04.2024", "трое суток", "black", "Оплата через карту"},
                {"Марина", "Цветкова", "ул. Островская 35", "Фрунзенская", "+375295897545",
                        "15.04.2024", "семеро суток", "grey", "Оплата наличными"}
        };
    }

    @Before
    public void webDriver() {
        driver = WebDriverUtil.getWebDriver(BROWSER);
        driver.get(URL_SCOOTER);
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    //Тестирование заказа самоката при нажатии на кнопку "Заказать" вверху страницы
    @Test
    public void orderScooterTestWithTopButtonTest() {
        mainPage.clickOrderTopButton();
        orderPage.setOrderForm(firstName, lastName, address, metroStation, phone);
        orderPage.setRentForm(date, rentalPeriod, scooterColor, courierComment);
        assertTrue("Открылось окно об успешном создании заказа", orderPage.modalOrderForm());
    }

    //Тестирование заказа самоката при нажатии на кнопку "Заказать" внизу страницы
    @Test
    public void orderScooterTestWithBottomButtonTest() {
        mainPage.clickOrderBottomButton();
        orderPage.setOrderForm(firstName, lastName, address, metroStation, phone);
        orderPage.setRentForm(date, rentalPeriod, scooterColor, courierComment);
        assertTrue("Открылось окно об успешном создании заказа", orderPage.modalOrderForm());
    }

    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}
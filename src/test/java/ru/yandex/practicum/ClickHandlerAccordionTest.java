package ru.yandex.practicum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.page.MainPage;
import ru.yandex.practicum.util.WebDriverUtil;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ClickHandlerAccordionTest {
    //Константа для выбора браузера в котором тестируем
    private static final String BROWSER = "chrome";
    private static final String URL_SCOOTER = "https://qa-scooter.praktikum-services.ru";
    private WebDriver driver;
    MainPage mainPage;
    private final int index;
    private final String answer;

    public ClickHandlerAccordionTest(int index, String answer) {
        this.index = index;
        this.answer = answer;
    }

    @Before
    public void webDriver() {
        driver = WebDriverUtil.getWebDriver(BROWSER);
        driver.get(URL_SCOOTER);
        mainPage = new MainPage(driver);
    }

    @Parameterized.Parameters(name = "Test {index}")
    public static Object[][] getAccordionHeader() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. " +
                        "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                        "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток " +
                        "— даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    //Тестирование выпадающих списков в разделел "Вопросы о важном"
    @Test
    public void clickAccordionPanelTest() {
        mainPage.clickCookieButton();
        mainPage.expandAccordionQuestion(index);
        assertTrue("Не открылся соответствующий текст", mainPage.answerAccordionIsDisplayed(answer));
    }

    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}
package ru.yandex.practicum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    //Локатор для заголовока страницы Яндекс
    private final By headerLogoYandex = By.xpath("//img[@alt='Yandex']");
    //Локатор для заголовока страницы Скутер
    private final By headerLogoScooter = By.xpath("//img[@alt='Scooter']");
    //Локатор для кнопки "Заказать" вверху страницы
    private final By orderTopButton = By.xpath("//div[contains(@class, 'Header')]/button[text()='Заказать']");
    //Локатор для кнопки "Статус заказа"
    private final By orderStatusButton = By.xpath("//button[text()='Статус заказа']");
    //Локатор для  поля "Введите номер заказа"
    private final By orderStatusField = By.xpath("//input[@placeholder='Введите номер заказа']");
    //Локатор для кнопки "Go"
    private final By goButton = By.xpath("//button[text()='Go!']");
    //Локатор для кнопки "да все привыкли"
    private final By cookieButton = By.id("rcc-confirm-button");
    //Локатор для кнопки "Заказать" внизу страницы
    private final By orderBottomButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");
    private final String questionAccordion = "accordion__heading-%s";
    private final String answerAccordion = "//div[contains(@id, 'accordion__panel')][.='%s']";
    //Объект WebDriver, используется для взаимодействия с браузером
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод клика по заголовку "Яндекс"
    public void headerLogoYandexClick() {
        driver.findElement(headerLogoYandex).click();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(driver.getWindowHandle())) {
                // Переключаемся на новую вкладку
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        // Ждем загрузки страницы на новой вкладке, пока заголовк не будет "Яндекс"
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.titleContains("Яндекс"));
    }

    //Метод клика по заголовку "Самокат"
    public void headerLogoScooterClick() {
        driver.findElement(headerLogoScooter).click();
    }

    //Метод поиска нужного вопроса в разделе "Вопросы о важном"
    public void expandAccordionQuestion(int index) {
        WebElement webElement = driver.findElement(By.id(String.format(questionAccordion, index)));
        // Прокручиваем страницу до раздела "Вопросы о важном"
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    //Метод проверки, что открывается соответствующий текст в аккордионе
    public boolean answerAccordionIsDisplayed(String answer) {
        return driver.findElement(By.xpath(String.format(answerAccordion, answer))).isDisplayed();
    }

    //Метод нажатие на кнопку "Статус заказа"
    public void clickOrderStatusButton(String numberOfOrder) {
        driver.findElement(orderStatusButton).click();
        driver.findElement(orderStatusField).sendKeys(numberOfOrder);
        driver.findElement(goButton).click();
    }

    //Получает локатор кнопки "Заказать" в нижней части страницы.
    public By getOrderBottomButton() {
        return orderBottomButton;
    }

    //Метод нажатие на кнопку "да все привыкли".
    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    //Метод нажатие на кнопку "Заказать" в верхней части страницы.
    public void clickOrderTopButton() {
        driver.findElement(orderTopButton).click();
    }

    //Метод нажатие на кнопку "Заказать" в нижней части страницы.
    public void clickOrderBottomButton() {
        // Прокручиваем страницу до кнопки "Заказать" ввнизу страницы
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(getOrderBottomButton()));
        driver.findElement(orderBottomButton).click();
    }

    //Получает локатор заголовка "Яндекс"
    public By getHeaderLogoYandex() {
        return headerLogoYandex;
    }
}
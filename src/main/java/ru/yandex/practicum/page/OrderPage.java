package ru.yandex.practicum.page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class OrderPage {
    //Локатор для поля "Имя"
    private final By firstNameInputField = By.xpath("//input[@placeholder='* Имя']");
    //Локатор для поля "Фамилия"
    private final By lastNameInputField = By.xpath("//input[@placeholder='* Фамилия']");
    //Локатор для поля "Адрес"
    private final By addressInputField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Локатор для поля выбора "Станция метро"
    private final By metroStationInputField = By.xpath("//input[@placeholder='* Станция метро']");
    //Локатор для поля "Телефон"
    private final By phoneInputInputField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор для кнопки "Далее"
    private final By nextButton = By.xpath("//button[text()='Далее']");

    //Локатор для поля выбора "Когда привезти самокат"
    private final By deliveryDateInputField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Локатор для поля выбора "Срок аренды"
    private final By rentalPeriodInputField = By.xpath("//div[text()='* Срок аренды']");
    //Локатор для чекбокса "Цвет самоката черный"
    private final By scooterColorBlackCheckbox = By.id("black");
    //Локатор для чекбокса "Цвет самоката серый"
    private final By scooterColorGreyCheckbox = By.id("grey");
    //Локатор для поля "Комментарий для курьера"
    private final By courierCommentInputLocator = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //Локатор для кнопки "Заказать"
    private final By orderButton = By.xpath("//div[@class='Order_Content__bmtHS']//button[text()='Заказать']");
    //Локатор для кнопки "Да"
    private final By yesButton = By.xpath("//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");
    //Локатор для окна с сообщением об успешном создании заказа
    private final By modalForm = By.xpath("//div[contains(@class, 'Order_Modal__YZ-d3')]//div[contains(text(), 'Заказ оформлен')]");
    private final String rentalPeriodMenu = "//div[@class='Dropdown-option' and text()='%s']";
    //Объект WebDriver, используется для взаимодействия с браузером
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод для заполнеиня формы заказа данными о пользователе
    public void setOrderForm(String firstName, String lastName, String address, String metroStation, String phone) {
        driver.findElement(firstNameInputField).sendKeys(firstName);
        driver.findElement(lastNameInputField).sendKeys(lastName);
        driver.findElement(addressInputField).sendKeys(address);
        driver.findElement(metroStationInputField).click();
        //Вводим название станции, нажимаем на стрелку вниз для выбора станции и подтверждаем выбор станции
        new Actions(driver).sendKeys(metroStation).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        driver.findElement(phoneInputInputField).sendKeys(phone);
        clickNextButton();
    }

    //Метод нажатие на кнопку "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //Метод заполнения формы аренды скутера, данными о дате доставки, периоде аренды, цвете скутера и комментарии курьеру
    public void setRentForm(String date, String rentalPeriod, String scooterColor, String courierComment) {
        driver.findElement(deliveryDateInputField).sendKeys(date);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        driver.findElement(rentalPeriodInputField).click();
        driver.findElement(By.xpath(String.format(rentalPeriodMenu, rentalPeriod))).click();
        switch (scooterColor) {
            case "black":
                driver.findElement(scooterColorBlackCheckbox).click();
                break;
            case "grey":
                driver.findElement(scooterColorGreyCheckbox).click();
                break;
        }
        driver.findElement(courierCommentInputLocator).sendKeys(courierComment);
        clickOrderButton();
    }

    //Метод нажатие на кнопку "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Метод для подтверждения оформления заказа, если успешно открывается окно с сообщением об успешном заказе
    public boolean modalOrderForm() {
        driver.findElement(yesButton).click();
        return driver.findElement(modalForm).isDisplayed();
    }
}
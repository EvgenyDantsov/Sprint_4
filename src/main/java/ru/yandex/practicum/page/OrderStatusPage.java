package ru.yandex.practicum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderStatusPage {
    //Локатор элемента, который представляет собой изображение, используется для идентификации случая, когда такого заказа нет.
    private final By notFoundOrderText = By.xpath("//img[@alt='Not found']");
    //Объект WebDriver, используется для взаимодействия с браузером
    private final WebDriver driver;

    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод проверяет отображение текста об отсутствии заказа на странице
    public boolean noFoundOrderText() {
        return driver.findElement(notFoundOrderText).isDisplayed();
    }
}

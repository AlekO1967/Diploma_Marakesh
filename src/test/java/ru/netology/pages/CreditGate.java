package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataCard;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditGate {
    private SelenideElement heading = $$("h3").find(exactText("Кредит по данным карты"));
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$("[class=\"input_control\"]");
    private SelenideElement monthField = $(byText("Месяц")).parent().$("[class=\"input_control\"]");
    private SelenideElement yearField = $(byText("Год")).parent().$("[class=\"input_control\"]");
    private SelenideElement cardHolder = $(byText("Владелец")).parent().$("[class=\"input_control\"]");
    private SelenideElement cvcField = $(byText("CVC/CVV")).parent().$("[class=\"input_control\"]");
    private SelenideElement continueButton = $(byText("Продолжить"));

    private SelenideElement approvedOperation = $(byText("Операция одобрена Банком")).parent().$("[class=\"notification_content\"]");
    private SelenideElement failureOperation = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=\"notification_content\"]");
    private SelenideElement wrongFormatCardNumber = $(byText("Неверный формат"));
    private SelenideElement monthError = $("div:nth-child(2) > span > span:nth-child(1) > span > span > span.input__sub");
    private SelenideElement yearError = $("div:nth-child(2) > span > span:nth-child(2) > span > span > span.input__sub");
    private SelenideElement wrongDateCard = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpired = $(byText("Истёк срок действия карты"));
    private SelenideElement wrongCardHolderField = $(byText("Поле обязательно для заполнения"));
    private SelenideElement cvcFieldError = $("div:nth-child(3) > span > span:nth-child(2) > span > span > span.input__sub");
    private SelenideElement cancelField = $$("[class=\"icon-button_text\"]").first();

    public CreditGate() {
        heading.shouldBe(visible);
    }

    public void inputData(DataCard card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolder.setValue(card.getCardHolder());
        cvcField.setValue(card.getCvs2());
        continueButton.click();
    }

    public void waitForApprovalNotification() {
        approvedOperation.shouldBe(visible, Duration.ofSeconds(15));
        cancelField.click();
    }

    public void waitForNotificationFailure() {
        failureOperation.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitForNotificationWrongFormat() {
        wrongFormatCardNumber.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void wrongFormatCardHolderField() {
        wrongCardHolderField.shouldHave(text("Поле обязательно для заполнения"));
    }

    public void wrongFormatCardNumber() {
        wrongFormatCardNumber.shouldHave(text("Неверный формат"));
    }

    public void wrongFormatMonth() {
        monthError.shouldHave(text("Неверный формат"));
    }

    public void wrongFormatYear() {
        yearError.shouldHave(text("Неверный формат"));
    }

    public void wrongFormatCVC() {
        cvcFieldError.shouldHave(text("Неверный формат"));
    }

    public void wrongFormatDateCard() {
        wrongDateCard.shouldHave(text("Неверно указан срок действия карты"));
    }

    public void expiredDateCard() {
        cardExpired.shouldHave(text("Истёк срок действия карты"));
    }
}

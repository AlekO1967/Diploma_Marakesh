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
    private SelenideElement cardNumberField = $(".input__box>input.input__control[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement monthField = $($(".input__box>input.input__control[placeholder=\"08\"]"));
    private SelenideElement yearField = $($(".input__box>input.input__control[placeholder=\"22\"]"));
    private SelenideElement cardHolder = $(byText("Владелец")).parent().$(".input__box>input.input__control[type=\"text\"]");
    private SelenideElement cvcField = $(byText("CVC/CVV")).parent().$(".input__box>input.input__control[placeholder=\"999\"]");
    private SelenideElement continueButton = $(byText("Продолжить"));

    private SelenideElement approvedOperation = $$(".notification__title").find(exactText("Успешно"));
    private SelenideElement failureOperation = $$(".notification__title").find(exactText("Ошибка"));


    private SelenideElement wrongFormatCardNumber = $$(".input__top").find(exactText("Номер карты")).parent().
            $$(".input__sub").find(exactText("Неверный формат"));
    private SelenideElement monthError = $$(".input__top").find(exactText("Месяц")).parent().
            $$(".input__sub").find(exactText("Неверный формат"));
    private SelenideElement wrongDateCard = $$(".input__top").find(exactText("Месяц")).parent().
            $$(".input__sub").find(exactText("Неверно указан срок действия карты"));
    private SelenideElement wrongYearCard = $$(".input__top").find(exactText("Год")).parent().
            $$(".input__sub").find(exactText("Неверно указан срок действия карты"));
    private SelenideElement cardExpired = $$(".input__top").find(exactText("Год")).parent().
            $$(".input__sub").find(exactText("Истёк срок действия карты"));
    private SelenideElement yearError = $(byText("Неверный формат"));
    private SelenideElement emptyCardHolderField = $$(".input__top").find(exactText("Владелец")).parent().
            $$(".input__sub").find(exactText("Поле обязательно для заполнения"));
    private SelenideElement wrongCardHolderField = $$(".input__top").find(exactText("Владелец")).parent().
            $$(".input__sub").find(exactText("Неверный формат"));
    private SelenideElement cvcFieldError = $$(".input__top").find(exactText("CVC/CVV")).parent().
            $$(".input__sub").find(exactText("Неверный формат"));

    public CreditGate() {
        heading.shouldBe(visible);
    }

    public void inputData(DataCard card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolder.setValue(card.getCardHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    public void waitForApprovalNotification() {
        approvedOperation.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitForNotificationFailure() {
        failureOperation.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void wrongFormatCardNumber() {
        wrongFormatCardNumber.shouldBe(visible);
    }

    public void wrongFormatMonth() {
        monthError.shouldBe(visible);
    }

    public void wrongYearCard() {
        wrongYearCard.shouldBe(visible);
    }

    public void wrongFormatYear() {
        yearError.shouldBe(visible);
    }

    public void wrongFormatDateCard() {
        wrongDateCard.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void expiredDateCard() {
        cardExpired.shouldBe(visible);
    }

    public void emptyCardHolderField() {
        emptyCardHolderField.shouldBe(visible);
    }

    public void wrongFormatCardHolderField() {
        wrongCardHolderField.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void wrongFormatCVC() {
        cvcFieldError.shouldBe(visible);
    }
}

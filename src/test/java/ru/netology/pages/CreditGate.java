package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataCard;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
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
    private SelenideElement approvedOperation = $(byText("Операция одобрена Банком")).parent().$("[class=\"notification_content\"]");
    private SelenideElement failureOperation = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=\"notification_content\"]");
    private SelenideElement wrongFormatError = $(byText("Неверный формат"));
    private ElementsCollection wrongFormatFourError = $$(byText("Неверный формат"));
    private SelenideElement cardExpirationDateError = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpiredError = $(byText("Истёк срок действия карты"));
    private SelenideElement requiredFieldError = $(byText("Поле обязательно для заполнения"));
    private SelenideElement cancelField = $$("[class=\"icon-button_text\"]").first();
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

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

    public void waitingForApprovalNotification() {
        approvedOperation.shouldBe(visible, Duration.ofSeconds(15));
        cancelField.click();
    }

    public void waitForNotificationFailure() {
        failureOperation.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitForNotificationWrongFormat() {
        wrongFormatError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitForNotificationExpirationDateError() {
        cardExpirationDateError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitForNotificationExpiredError() {
        cardExpiredError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitForNotificationWrongFormatFourFields() {
        wrongFormatFourError.shouldHaveSize(4);
        requiredFieldError.shouldBe(visible, Duration.ofSeconds(15));
    }

}

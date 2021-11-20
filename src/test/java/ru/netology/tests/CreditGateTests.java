package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.pages.PaymentMethod;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditGateTests {
    @BeforeAll
    public void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openPage() {
        open(System.getProperty("sut.url"));
    }

    @AfterAll
    public void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SqlHelper.clearDB();
    }

    @Test
    void shouldByPayApprovedCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitingForApprovalNotification();
        val paymentStatus = SqlHelper.getCreditRequestStatus();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldByPayDeclinedCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitForNotificationFailure();
        val paymentStatus = SqlHelper.getCreditRequestStatus();
        assertEquals("DECLINED", paymentStatus);
    }
}

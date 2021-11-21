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
        payment.waitForApprovalNotification();
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

    @Test
    void shouldPayByValidNumberCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardNotValidNumber());
        payment.waitForNotificationWrongFormat();
    }

    @Test
    void shouldPayByEmptyNumberCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardEmptyNumber());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayByLatinSymbolsNumberCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardNumberInputLatin());
        payment.waitForNotificationWrongFormat();
    }

    @Test
    void shouldPayByCyrillicSymbolsNumberCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardNumberInputCyrillic());
        payment.waitForNotificationWrongFormat();
    }

    @Test
    void shouldPayBySpecialSymbolsNumberCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardNumberInputSpecialCharacters());
        payment.waitForNotificationWrongFormat();
    }

    @Test
    void shouldPayBy15SymbolsNumberCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardNumberInput15Characters());
        payment.waitForNotificationWrongFormat();
    }

    @Test
    void shouldPayByExpiredCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardExpired());
        payment.expiredDateCard();
    }

    @Test
    void shouldPayByMoreFourYearCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardMoreFourYear());
        payment.wrongFormatDateCard();
    }

    @Test
    void shouldPayByEmptyFieldMonthCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardEmptyFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldPayByEmptyFieldYearCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardEmptyFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldPayByInvalidFieldMonthCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardOneDigitInFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldPayByInvalidFieldYearCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardOneDigitInFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldInput00FieldMonthCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInput00InFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldInput13FieldMonthCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInput13InFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldInputCyrillicInFieldMonthCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputCyrillicInFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldInputLatinInFieldMonthCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputLatinInFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldInputCyrillicInFieldYearCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputCyrillicInFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldInputLatinInFieldYearCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputLatinInFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldInputSpecialSymbolsInFieldsMonthYearCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputFieldsMonthYearSpecialCharacters());
        payment.wrongFormatMonth();
        payment.wrongFormatYear();
    }

    @Test
    void shouldPayByEmptyFieldCardHolderCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardNoCardHolder());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldInputRussianInCardHolderCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardHolderCyrillic());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldInputSpecialSymbolsInCardHolderCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardHolderSpecialCharacters());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldPayByEmptyFieldCVCCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardEmptyFieldCVV2_CVS2());
        payment.wrongFormatCVC();
    }

    @Test
    void shouldInputCyrillicSymbolsInFieldCVCCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputCyrillicInFieldCVV2_CVS2());
        payment.wrongFormatCVC();
    }

    @Test
    void shouldInputLatinSymbolsInFieldCVCCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputLatinInFieldCVV2_CVS2());
        payment.wrongFormatCVC();
    }

    @Test
    void shouldInputSpecialSymbolsInFieldCVCCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputFieldCVV2_CVS2SpecialCharacters());
        payment.wrongFormatCVC();
    }
}
package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.pages.PaymentMethod;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentGateTests {

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
    void shouldPayByApprovedCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitForApprovalNotification();
        val paymentStatus = SqlHelper.getPaymentStatus();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldPayByDeclinedCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitForNotificationFailure();
        val paymentStatus = SqlHelper.getPaymentStatus();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    void shouldPayByValidNumberCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardNotValidNumber());
        payment.waitForNotificationWrongFormat();
    }

    @Test
    void shouldPayByEmptyNumberCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardEmptyNumber());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayByLatinSymbolsNumberCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardNumberInputLatin());
        payment.waitForNotificationWrongFormat();
    }

    @Test
    void shouldPayByCyrillicSymbolsNumberCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardNumberInputCyrillic());
        payment.waitForNotificationWrongFormat();
    }

    @Test
    void shouldPayBySpecialSymbolsNumberCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardNumberInputSpecialCharacters());
        payment.waitForNotificationWrongFormat();
    }

    @Test
    void shouldPayBy15SymbolsNumberCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardNumberInput15Characters());
        payment.waitForNotificationWrongFormat();
    }

    @Test
    void shouldPayByExpiredCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardExpired());
        payment.expiredDateCard();
    }

    @Test
    void shouldPayByMoreFourYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardMoreFourYear());
        payment.wrongFormatDateCard();
    }

    @Test
    void shouldPayByEmptyFieldMonthCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardEmptyFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldPayByEmptyFieldYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardEmptyFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldPayByInvalidFieldMonthCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardOneDigitInFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldPayByInvalidFieldYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardOneDigitInFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldInput00FieldMonthCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardInput00InFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldInput13FieldMonthCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardInput13InFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldInputCyrillicInFieldMonthCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardInputCyrillicInFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldInputLatinInFieldMonthCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardInputLatinInFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldInputCyrillicInFieldYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardInputCyrillicInFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldInputLatinInFieldYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardInputLatinInFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldInputSpecialSymbolsInFieldsMonthYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardInputFieldsMonthYearSpecialCharacters());
        payment.wrongFormatMonth();
        payment.wrongFormatYear();
    }

    @Test
    void shouldPayByEmptyFieldCardHolderCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardNoCardHolder());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldInputRussianInCardHolderCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardHolderCyrillic());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldInputSpecialSymbolsInCardHolderCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardHolderSpecialCharacters());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldPayByEmptyFieldCVCCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardEmptyFieldCVV2_CVS2());
        payment.wrongFormatCVC();
    }

    @Test
    void shouldInputCyrillicSymbolsInFieldCVCCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardInputCyrillicInFieldCVV2_CVS2());
        payment.wrongFormatCVC();
    }

    @Test
    void shouldInputLatinSymbolsInFieldCVCCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardInputLatinInFieldCVV2_CVS2());
        payment.wrongFormatCVC();
    }

    @Test
    void shouldInputSpecialSymbolsInFieldCVCCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToPaymentGate();
        payment.inputData(DataHelper.getCardInputFieldCVV2_CVS2SpecialCharacters());
        payment.wrongFormatCVC();
    }
}

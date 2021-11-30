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
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openPage() {
        open(System.getProperty("sut.url"));
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SqlHelper.clearDB();
    }

    @Test
    void shouldByPayApprovedCreditCard() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditGate();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitForApprovalNotification();
        val paymentStatus = SqlHelper.getCreditRequestStatus();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldByPayDeclinedCreditCard() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditGate();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitForNotificationFailure();
        val paymentStatus = SqlHelper.getCreditRequestStatus();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    void shouldPayByNotValidNumberCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardNotValidNumber());
        payment.wrongFormatCardNumber();
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
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayByCyrillicSymbolsNumberCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardNumberInputCyrillic());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayBySpecialSymbolsNumberCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardNumberInputSpecialCharacters());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayBy15SymbolsNumberCreditCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardNumberInput15Characters());
        payment.wrongFormatCardNumber();
    }@Test
    void shouldPayByExpiredCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardExpired());
        payment.expiredDateCard();
    }

    @Test
    void shouldPayByMoreFourYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardMoreFourYear());
        payment.wrongYearCard();
    }

    @Test
    void shouldPayByEmptyFieldMonthCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardEmptyFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldPayByEmptyFieldYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardEmptyFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldPayByInvalidFieldMonthCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardOneDigitInFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldPayByInvalidFieldYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardOneDigitInFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldPayByWrongDateCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInput13InFieldMonth());
        payment.wrongFormatDateCard();
    }

    @Test
    void shouldPayByWrongDateCard_00() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInput00InFieldMonth());
        payment.wrongFormatDateCard();
    }

    @Test
    void shouldInputCyrillicInFieldMonthCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputCyrillicInFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldInputLatinInFieldMonthCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputLatinInFieldMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldInputCyrillicInFieldYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputCyrillicInFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldInputLatinInFieldYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputLatinInFieldYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldInputSpecialSymbolsInFieldsMonthYearCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputFieldsMonthYearSpecialCharacters());
        payment.wrongFormatMonth();
        payment.wrongFormatYear();
    }

    @Test
    void shouldPayByEmptyFieldCardHolderCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardNoCardHolder());
        payment.emptyCardHolderField();
    }

    @Test
    void shouldInputRussianInCardHolderCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardHolderCyrillic());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldInputSpecialSymbolsInCardHolderCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardHolderSpecialCharacters());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldPayByEmptyFieldCVCCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardEmptyFieldCVV2_CVS2());
        payment.wrongFormatCVC();
    }

    @Test
    void shouldInputCyrillicSymbolsInFieldCVCCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputCyrillicInFieldCVV2_CVS2());
        payment.wrongFormatCVC();
    }

    @Test
    void shouldInputLatinSymbolsInFieldCVCCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputLatinInFieldCVV2_CVS2());
        payment.wrongFormatCVC();
    }

    @Test
    void shouldInputSpecialSymbolsInFieldCVCCard() {
        val paymentMethod = new PaymentMethod();
        val payment = paymentMethod.goToCreditGate();
        payment.inputData(DataHelper.getCardInputFieldCVV2_CVS2SpecialCharacters());
        payment.wrongFormatCVC();
    }

}
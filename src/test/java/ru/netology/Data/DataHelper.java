package ru.netology.data;


import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    public static DataCard getApprovedCard() {
        return new DataCard("4444 4444 4444 4441", "08", "22", "Aleksandr Osipov", "123");
    }

    public static DataCard getDeclinedCard() {
        return new DataCard("4444 4444 4444 4442", "08", "22", "Aleksandr Osipov", "123");
    }

    public static DataCard getEmptyCard() {
        return new DataCard("", "", "", "", "");
    }

    public static String getShiftedMonth(int monthCount) {
        return LocalDate.now().plusMonths(monthCount).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getShiftedYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static DataCard getCardNotValidNumber() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("9999 9999 9999 9999", month, year, cardHolder, cvs2);
    }

    public static DataCard getCardEmptyNumber() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard(" ", month, year, cardHolder, cvs2);
    }

    public static DataCard getCardNumberInputLatin() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("aaaa bbbb cccc dddd", month, year, cardHolder, cvs2);
    }

    public static DataCard getCardNumberInputCyrillic() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("аааа бббб вввв гггг", month, year, cardHolder, cvs2);
    }

    public static DataCard getCardNumberInputSpecialCharacters() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("@#$% @#$% ^&*( ^&*(", month, year, cardHolder, cvs2);
    }

    public static DataCard getCardNumberInput15Characters() {
        Faker faker = new Faker();
        String cardNumber = faker.number().digits(15);
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard(cardNumber, month, year, cardHolder, cvs2);
    }

    public static DataCard getCardExpired() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", "06", "18", cardHolder, cvs2);
    }

    public static DataCard getCardMoreFourYear() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(6);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", month, year, cardHolder, cvs2);
    }

    public static DataCard getCardEmptyFieldMonth() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", " ", year, cardHolder, cvs2);
    }

    public static DataCard getCardEmptyFieldYear() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", month, " ", cardHolder, cvs2);
    }

    public static DataCard getCardOneDigitInFieldMonth() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = faker.number().digit();
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", month, year, cardHolder, cvs2);
    }

    public static DataCard getCardOneDigitInFieldYear() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = faker.number().digit();
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", month, year, cardHolder, cvs2);
    }

    public static DataCard getCardInput00InFieldMonth() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", "00", year, cardHolder, cvs2);
    }

    public static DataCard getCardInput13InFieldMonth() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", "13", year, cardHolder, cvs2);
    }

    public static DataCard getCardInputCyrillicInFieldMonth() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", "гг", year, cardHolder, cvs2);
    }

    public static DataCard getCardInputLatinInFieldMonth() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", "zz", year, cardHolder, cvs2);
    }

    public static DataCard getCardInputCyrillicInFieldYear() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", month, "бб", cardHolder, cvs2);
    }

    public static DataCard getCardInputLatinInFieldYear() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", month, "zz", cardHolder, cvs2);
    }

    public static DataCard getCardInputFieldsMonthYearSpecialCharacters() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", "$%", "^&", cardHolder, cvs2);
    }

    public static DataCard getCardNoCardHolder() {
        Faker faker = new Faker();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", month, year, " ", cvs2);
    }

    public static DataCard getCardHolderCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", month, year, cardHolder, cvs2);
    }

    public static DataCard getCardHolderSpecialCharacters() {
        Faker faker = new Faker();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new DataCard("4444 4444 4444 4441", month, year, "!@#$%^&*", cvs2);
    }

    public static DataCard getCardEmptyFieldCVV2_CVS2() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        return new DataCard("4444 4444 4444 4441", month, year, cardHolder, " ");
    }

    public static DataCard getCardInputCyrillicInFieldCVV2_CVS2() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        return new DataCard("4444 4444 4444 4441", month, year, cardHolder, "абв");
    }

    public static DataCard getCardInputLatinInFieldCVV2_CVS2() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        return new DataCard("4444 4444 4444 4441", month, year, cardHolder, "abc");
    }

    public static DataCard getCardInputFieldCVV2_CVS2SpecialCharacters() {
        Faker faker = new Faker();
        String month = getShiftedMonth(9);
        String year = getShiftedYear(1);
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        return new DataCard("4444 4444 4444 4441", month, year, cardHolder, "#$%");
    }

}

package ru.netology.data;

import com.mysql.cj.protocol.x.XProtocolRow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class DataCard {
    private String cardNumber;
    private String month;
    private String year;
    private String cardHolder;
    private String cvs2;
}

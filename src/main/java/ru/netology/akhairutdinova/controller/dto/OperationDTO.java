package ru.netology.akhairutdinova.controller.dto;

import lombok.Data;
import ru.netology.akhairutdinova.domain.enums.Currency;

@Data
public class OperationDTO {
    private final int id;
    private final int sum;
    private final Currency currency;
}

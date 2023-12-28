package ru.netology.akhairutdinova.controller.dto;

import lombok.*;
import ru.netology.akhairutdinova.domain.enums.Currency;

@RequiredArgsConstructor
@Data
public class OperationDTOInput {
    private final int sum;
    private final Currency currency;
    private final String merchant;
    private final int customerId;
}

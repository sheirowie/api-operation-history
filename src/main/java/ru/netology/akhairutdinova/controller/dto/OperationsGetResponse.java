package ru.netology.akhairutdinova.controller.dto;

import lombok.Data;
import java.util.List;

@Data
public class OperationsGetResponse {
    private final List<OperationDTO> operations;
}

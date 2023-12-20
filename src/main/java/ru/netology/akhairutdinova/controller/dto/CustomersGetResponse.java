package ru.netology.akhairutdinova.controller.dto;

import lombok.Data;
import java.util.List;

@Data
public class CustomersGetResponse {
    private final List<CustomerDTO> customers;
}

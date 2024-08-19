package com.example.penjualan_tiket_renang.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddTicketDto {
    @Column(nullable = false)
    private String ticketCode;

    @Column(nullable = false)
    private long price;
}

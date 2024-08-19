package com.example.simple_java_spring_app.model;

import com.example.simple_java_spring_app.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class WebResponse<T> {
    private String message;
    private T data;
}

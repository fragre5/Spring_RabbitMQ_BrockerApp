package com.example.brockerappn.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class DataMessage {

    private String datetime;
    private String title;
    private String text;
}

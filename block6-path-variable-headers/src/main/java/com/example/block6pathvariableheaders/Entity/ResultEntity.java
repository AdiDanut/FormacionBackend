package com.example.block6pathvariableheaders.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ResultEntity {

    private String body;

    private List<String> headers;

    private List<String> requestParams;
}

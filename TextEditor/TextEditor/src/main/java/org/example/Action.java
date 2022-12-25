package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Action {
    private int actionId;
    private LocalDateTime dateTime;
    private boolean isAddition;
    private int lineNumber;
    private String text;
}

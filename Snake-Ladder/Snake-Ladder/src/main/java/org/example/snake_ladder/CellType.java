package org.example.snake_ladder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CellType {
    private Boolean isAdditive;
    private String name;
    private Integer start;
    private Integer end;
}

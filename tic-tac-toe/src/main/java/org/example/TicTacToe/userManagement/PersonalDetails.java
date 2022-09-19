package org.example.TicTacToe.userManagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalDetails {

    private String name;
    private String country;
    private GenderEnum gender;
}

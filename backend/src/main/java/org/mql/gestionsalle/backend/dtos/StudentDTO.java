package org.mql.gestionsalle.backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class StudentDTO {

    private String cne;
    private String lastName;
    private String firstName;
    private String username;
    private String phone;
    private String email;

}

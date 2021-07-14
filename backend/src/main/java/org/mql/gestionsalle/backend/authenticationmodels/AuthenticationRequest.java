package org.mql.gestionsalle.backend.authenticationmodels;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AuthenticationRequest {

    private String username;
    private String password;

}

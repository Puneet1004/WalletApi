package com.test.wallet.queries;


import com.test.wallet.security.Encode;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    Encode encode = new Encode();

    public String getPassword() {
        return encode.encode(password);
    }
}

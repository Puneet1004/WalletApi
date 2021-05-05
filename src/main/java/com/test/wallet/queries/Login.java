package com.test.wallet.queries;

import com.test.wallet.security.Encode;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {
        private String userName;
        private String password;

        Encode encode = new Encode();
        public String getPassword() {
                return encode.encode(password);
        }
}

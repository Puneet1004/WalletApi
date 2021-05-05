package com.test.wallet.queries;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddBalance {
    private String userName;
    private long balance;
}

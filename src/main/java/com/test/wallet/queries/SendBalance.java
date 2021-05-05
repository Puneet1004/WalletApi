package com.test.wallet.queries;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendBalance {
    private String userName;
    private Long transactionAmount;
    private String sendToDetail;
}

package com.test.wallet.queries;


import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CusTransaction implements Comparator<CusTransaction>{

    private long id;
    private long transactionAmount;
    private Date transactionDate;
    private long walletId;
    private long wallet1Id;
    private String type;


    @Override
    public int compare(CusTransaction o1, CusTransaction o2) {
        return o1.getTransactionDate().compareTo(o2.getTransactionDate());
    }
}

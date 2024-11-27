package org.fotm.parallel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SumNotThreadsafe {

    private int total;

    public void performSum(int input) {
        total += input;
//            System.out.println("total: " + total);

    }
}

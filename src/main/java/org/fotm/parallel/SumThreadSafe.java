package org.fotm.parallel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SumThreadSafe {

        private int total;

        public synchronized void performSum(int input){
            total+=input;
        }
}

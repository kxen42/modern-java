package org.fotm.java17.sealed;

import java.math.BigDecimal;

public sealed interface Product permits Expensive, Cheap  {
 void setPrice(BigDecimal price);
 BigDecimal price();
}

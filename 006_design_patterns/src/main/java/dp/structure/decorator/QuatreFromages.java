package dp.structure.decorator;

import java.math.BigDecimal;

public class QuatreFromages extends Pizza {
    @Override
    public String getDescription() {
        return "chèvre, camembert, mozzarella, emmental";
    }
    @Override
    public BigDecimal getCout() {
        return new BigDecimal("13.99");
    }
}

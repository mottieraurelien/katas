package dp.structure.decorator;

import java.math.BigDecimal;

public class Anchois extends Ingredient {
    private Pizza pizza;
    public Anchois(Pizza pizza) {
        this.pizza = pizza;
    }
    @Override
    public String getDescription() {
        return pizza.getDescription() + ", anchois";
    }
    @Override
    public BigDecimal getCout() {
        return new BigDecimal("1.99").add(pizza.getCout());
    }
}
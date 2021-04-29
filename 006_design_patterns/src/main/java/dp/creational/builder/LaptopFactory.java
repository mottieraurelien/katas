package dp.creational.builder;

public class LaptopFactory {

    public void main(String[] args) {

        final Laptop firstLaptop = new Laptop()
                .withColor("Blue")
                .withModel("Macbook Pro")
                .withBrand("Apple")
                .withDisplay("Retina Full HD")
                // an so on...
                ;


    }

}

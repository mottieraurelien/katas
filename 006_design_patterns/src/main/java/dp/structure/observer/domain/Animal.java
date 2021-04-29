package dp.structure.observer.domain;

public class Animal {

    private final String name;
    private final String emoji;

    public Animal(final String name, final String emoji) {
        this.name = name;
        this.emoji = emoji;
    }

    public String getEmoji(){
        return emoji;
    }

    public String getName() {
        return this.name;
    }

}
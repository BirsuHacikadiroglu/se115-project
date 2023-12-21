public class Card {
    String color;
    int value;
    int sign;

    public Card(String color, int value, int sign) {
        this.color = color;
        this.value = value;
        this.sign = sign;
    }
    public String toString() {
        return color + " " + value*sign;
    }
}



import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Card[] newDeck = createDeck();
        System.out.println("Deck: ");
        printDeck(newDeck);
        shuffleDeck(newDeck);
        System.out.println("Shuffled Deck: ");
        printDeck(newDeck);
    }

    private static Card[] createDeck() {
        Card[] newDeck = new Card[40];
        String[] colors = {"yellow", "blue", "red", "green"};
        int[] sign = {1, -1};
        int index = 0;

        for (String color : colors) {
            for (int value = 1; value < 11; value++) {
                newDeck[index++] = new Card(color, value, 1);
            }
        }
        return newDeck;
    }

    public static void printDeck(Card[] deck) {
        for (Card card : deck) {
            System.out.println(card + " ");
        }
        System.out.println();
    }

    public static void shuffleDeck(Card[] deck) {
        Random rand = new Random();

        for (int i = deck.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }
}
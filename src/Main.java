import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Card[] newDeck = createDeck();
        System.out.println("Deck: ");
        printDeck(newDeck);
        shuffleDeck(newDeck);
        System.out.println("Shuffled Deck: ");
        printDeck(newDeck);


        // Player decks
        Card[] computerDeck = new Card[5];
        Card[] userDeck = new Card[5];

        handoutGamecards(newDeck, computerDeck, userDeck);

        System.out.println("User's Deck");
        printDeck(userDeck);
    }

    private static void handoutGamecards(Card[] gameDeck, Card[] computerDeck, Card[] userDeck){
        for(int i=0; i<5; i++){

            computerDeck[i] = gameDeck[i]; //gave computer first 5 cards
            userDeck[i] = gameDeck[gameDeck.length -1 -i]; //gave user last 5 cards
        }
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
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
        Card[] computerDeck = new Card[8];
        Card[] userDeck = new Card[8];

        handoutGamecards(newDeck, computerDeck, userDeck);

        System.out.println("User's Deck");
        printDeck(userDeck);

        Card[] signedCards = generateSignedCards();
        handoutSignedCards(signedCards, computerDeck, userDeck);

        System.out.println("User's Deck");
        printDeck(userDeck);

    }

    private static void handoutGamecards(Card[] gameDeck, Card[] computerDeck, Card[] userDeck){
        for(int i=0; i<5; i++){

            computerDeck[i] = gameDeck[i]; //gave computer first 5 cards
            userDeck[i] = gameDeck[gameDeck.length -1 -i]; //gave user last 5 cards
        }
    }

    private static void handoutSignedCards(Card[] signedCards, Card[] computerDeck, Card[] userDeck){
        for(int i=0; i<3; i++){
            computerDeck[i+5] = signedCards[i];
            userDeck[i+5] = signedCards[i];
        }
    }

    private static Card[] generateSignedCards(){
      Card[] additionalCards = new Card[3];
      Random rand = new Random();

      for(int i=0; i<3; i++){
          String color = getRandomColor();
          int value = rand.nextInt(6)+1; //Random value between 1-6
          int signValue = rand.nextInt(2);
          int sign;

          if(signValue == 0){sign = 1;}
          else { sign =-1; }

          additionalCards[i] = new Card(color, value, sign);

      }
      return additionalCards;
    }

    private static String getRandomColor(){
        String[] colors = {"yellow", "blue", "red", "green"};
        Random rand = new Random();
        int index = rand.nextInt(colors.length);
        return colors[index];
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
         for (int i =0 ; i < deck.length -1; i++) {
             int j = rand.nextInt(i + 1);
             Card temp = deck[i];
             deck[i] = deck[j];
             deck[j] = temp;
         }

         /*for (int i = deck.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }

          */
    }
}
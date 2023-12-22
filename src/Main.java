import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Card[] newDeck = createDeck();
        System.out.println("Deck: ");
        printDeck(newDeck);
        shuffleDeck(newDeck);
        System.out.println("Shuffled Deck: ");
        printDeck(newDeck);


        // Player decks --> Requirement.3
        Card[] computerDeck = new Card[10];
        Card[] userDeck = new Card[10];

        handoutGamecards(newDeck, computerDeck, userDeck);

        System.out.println("User's Deck");
        printDeck(userDeck);

        Card[] signedCards = generateSignedCards();
        handoutSignedCards(signedCards, computerDeck, userDeck);

        System.out.println("User's Deck");
        printDeck(userDeck);

        Card[] specialCards = generateSpecialCards();
        handoutSpecialCards(specialCards, computerDeck, userDeck);
        System.out.println("User's Deck");
        printDeck(userDeck);

        // Player hands
        Card[] computerHand = pickHandCards(computerDeck);
        Card[] userHand = pickHandCards(userDeck);

        System.out.println("User's Hand:");
        printDeck(userHand);


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

    private static void handoutSpecialCards(Card[] specialCards, Card[] computerDeck, Card[] userDeck){
        computerDeck[8] = new Card(specialCards[0].color,specialCards[0].value, specialCards[0].sign );
        userDeck[8] = new Card(specialCards[0].color, specialCards[0].value, specialCards[0].sign); //9th cards

        computerDeck[9] = new Card(specialCards[1].color, specialCards[1].value, specialCards[1].sign);
        userDeck[9] = new Card(specialCards[1].color, specialCards[1].value, specialCards[1].sign); // 10th cards
    }




    private static Card[] pickHandCards(Card[] playerDeck){
        Random rand = new Random();
        Card[] handCards = new Card[4];

        for(int i=0; i<4; i++){
            int randomIndex = rand.nextInt(playerDeck.length);
            handCards[i] = playerDeck[randomIndex];

            playerDeck = removeCard(playerDeck, randomIndex); //remove the selected card from player deck

        }
        return  handCards;
    }

    //method to remove a card from an array
    private  static Card[] removeCard( Card[] array, int index){
        Card[] newArr = new Card[array.length-1];
        System.arraycopy(array, 0, newArr, 0, index ); //copying the cards that come before the
        System.arraycopy(array, index +1, newArr, index, array.length -index -1); //copying the ones that comes after
        return newArr;
    }





    private static Card[] generateSpecialCards() {
        Random rand = new Random();
        Card[] specialCards = new Card[2];

        boolean hasDouble = rand.nextDouble() < 0.2;
        boolean hasFlip = rand.nextDouble() < 0.2;
        // generated 2 boolean variables


        if (hasFlip && hasDouble) { //if true for both
            if (rand.nextBoolean()) {
                specialCards[0] = new Card("flip(+/-)", 0, 0);
                specialCards[1] = new Card("double(x2)", 0, 0);
            } else {
                specialCards[0] = new Card("double(x2)", 0, 0);
                specialCards[1] = new Card("flip(+/-)", 0, 0);
            }
        }
        else {
            if (hasDouble) { //if true for hasDouble
                specialCards[0] = new Card("double(x2)", 0, 0);
            } else if (hasFlip) { //if true for hasFlip
                specialCards[0] = new Card("flip(+/-)", 0, 0);
            } else { //if false for both
                specialCards[0] = generateSignedCard();
            }
        }

        //to make sure that the second card is different from the first one
        do {
            if (hasDouble || hasFlip) {
                specialCards[1] = generateSignedCard();
            } else {
                specialCards[1] = generateSignedCard();
            }
        } while (specialCards[1].toString().equals(specialCards[0].toString()));

        return specialCards;
    }


    private static Card[] generateSignedCards(){
        Random rand = new Random();
        Card[] signedCards = new Card[3];

      for(int i=0; i<3; i++){
          String color = getRandomColor();
          int value = rand.nextInt(6)+1; //Random value between 1-6
          int signValue = rand.nextInt(2);
          int sign;

          if(signValue == 0){sign = 1;}
          else { sign =-1; }

          signedCards[i] = new Card(color, value, sign);
      }
      return signedCards;
    }

    private static Card generateSignedCard() {
        Random rand = new Random();
        String color = getRandomColor();
        int value = rand.nextInt(6) + 1; // Random value between 1-6
        int signValue = rand.nextInt(2);
        int sign;

        if(signValue == 0){sign = 1;}
        else { sign =-1; }
        return new Card(color, value, sign);


}


    private static String getRandomColor(){
        String[] colors = {"yellow", "blue", "red", "green"};
        Random rand = new Random();
        int index = rand.nextInt(colors.length);
        return colors[index];
    }



    private static Card[] createDeck() { //Requirement 1
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

    public static void shuffleDeck(Card[] deck) { //Requirement 2
        Random rand = new Random();
         for (int i =0 ; i < deck.length -1; i++) {
             int j = rand.nextInt(i + 1);
             Card temp = deck[i];
             deck[i] = deck[j];
             deck[j] = temp;
         }
    }

}


/*import java.util.Random;
     class GameDeck { // Requirement 1

         public Card draw () {
             if (size > 0) {
                 return gamecards[--size];
             } else return null; //if deck is empty
         }
     }

     class PlayerDeck {
         private Card[] deck;
         private Card[] hand;
         private int deckSize;
         private int handSize;

         public PlayerDeck() {
             this.deck = new Card[10];
             this.hand = new Card[4];
             this.deckSize = 0;
             this.handSize = 0;
         }


         int index = 0;
         Random random = new Random();
         for(int i = 0;i<3;i++){ // 3 signed cards (1-6 , +/-)
             String color = getRandomColor();
             int value = random.nextInt(6) + 1; // value starts from 1
             String sign;
             if (random.nextBoolean()) {
                 sign = "+";
             } else sign = "-";
             deck[index++] = new Card(color, value, sign);
         }

         for(int i = 0;i<2;i++){ //for other 2 cards
             if (random.nextDouble() < 0.2) {
                 if (random.nextBoolean()) {
                     deck[index++] = createDoubleCard();
                 } else deck[index++] = createFlipCard();
             } else {
                 deck[index++] = createSignedCard(random);
             }
         }



         private Card createFlipCard() {
             return new Card("", 0, "+/-");
         }

         private Card createDoubleCard() {
             return new Card("", 0, "x2");
         }

         private Card createSignedCard(Random random) {
             String color = getRandomColor();
             int value = random.nextInt(6) + 1;
             String sign;
             if (random.nextBoolean()) {
                 sign = "+";
             } else sign = "-";
             return new Card(color, value, sign);
         }

         private String getRandomColor() {
             String[] colors = {"yellow", "blue", "red", "green"};
             return colors[new Random().nextInt(4)];
         }


         public void addToDeck(Card card) {
             if (deckSize < deck.length) {
                 deck[deckSize++] = card;
             } else { //deck full
             }
         }

         public void addToHand(Card card) {
             if (handSize < hand.length) {
                 hand[handSize++] = card;
             }
         }

     }
     */








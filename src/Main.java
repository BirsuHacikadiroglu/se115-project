import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Card[] newDeck = createDeck();
        shuffleDeck(newDeck);


        // Player decks --> Requirement 3
        Card[] computerDeck = new Card[10];
        Card[] userDeck = new Card[10];


        handoutGamecards(newDeck, computerDeck, userDeck);



        Card[] signedCards = generateSignedCards();
        handoutSignedCards(signedCards, computerDeck, userDeck);


        Card[] specialCards = generateSpecialCards();
        handoutSpecialCards(specialCards, computerDeck, userDeck);


        // Player hands
        Card[] computerHand = pickHandCards(computerDeck);
        Card[] userHand = pickHandCards(userDeck);



        // Player boards
        Card[] computerBoard = new Card[9];
        Card[] userBoard = new Card[9];


        System.out.println("Computer's Board:");
        //printDeck(computerBoard);
        System.out.println("User's Board:");
        //printDeck(userBoard);
        System.out.println("User's Hand: " );
        printDeck(userHand);

        // drawing the first card to start the game
        Card drawnCard = drawCard(newDeck);
        userBoard = addCardToBoard(userBoard, drawnCard);
        newDeck=removeCard(newDeck,newDeck.length -1);
        System.out.println("***********");
        System.out.println("Computer's Board:");
        //printDeck(computerBoard);
        System.out.println("User's Board:");
        printDeck(userBoard);
        System.out.println("User's Hand: " );
        printDeck(userHand);
        System.out.println("***********");

        //System.out.println("First Card: " + currentCard.toString());


        playGame(newDeck, computerHand, userHand, computerBoard, userBoard);


    }

    // Creating a gamedeck --> Requirement 1
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


    private static void handoutGamecards(Card[] gameDeck, Card[] computerDeck, Card[] userDeck) {
        for (int i = 0; i < 5; i++) {
            computerDeck[i] = gameDeck[gameDeck.length - 1 - i]; // gave computer first 5 cards
            userDeck[i] = gameDeck[i]; //gave user last 5 cards

        }
        // removing first 5 cards
        for (int i = 0; i < 5; i++) {
            gameDeck = removeCard(gameDeck,i);
        }

        // removing last 5 cards
        for (int i = 0; i < 5; i++) {
            gameDeck = removeCard(gameDeck, gameDeck.length - 1 -i);
        }

    }

    private static void handoutSignedCards(Card[] signedCards, Card[] computerDeck, Card[] userDeck) {
        for (int i = 0; i < 3; i++) {
            computerDeck[i + 5] = signedCards[i];
            userDeck[i + 5] = signedCards[i];
        }
    }

    private static void handoutSpecialCards(Card[] specialCards, Card[] computerDeck, Card[] userDeck) {
        computerDeck[8] = new Card(specialCards[0].color, specialCards[0].value, specialCards[0].sign);
        userDeck[8] = new Card(specialCards[0].color, specialCards[0].value, specialCards[0].sign); //9th cards

        computerDeck[9] = new Card(specialCards[1].color, specialCards[1].value, specialCards[1].sign);
        userDeck[9] = new Card(specialCards[1].color, specialCards[1].value, specialCards[1].sign); // 10th cards
    }


    private static Card[] pickHandCards(Card[] playerDeck) {
        Random rand = new Random();
        Card[] handCards = new Card[4];

        for (int i = 0; i < 4; i++) {
            int randomIndex = rand.nextInt(playerDeck.length);
            handCards[i] = playerDeck[randomIndex];



        }
        return handCards;
    }

    //method to remove a card from an array
    private static Card[] removeCard(Card[] array, int index) {
        Card[] newArr = new Card[array.length - 1];
        System.arraycopy(array, 0, newArr, 0, index); //copying the cards that come before
        System.arraycopy(array, index + 1, newArr, index, array.length - index - 1); //copying the ones that comes after
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
        } else {
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


    private static Card[] generateSignedCards() {
        Random rand = new Random();
        Card[] signedCards = new Card[3];

        for (int i = 0; i < 3; i++) {
            String color = getRandomColor();
            int value = rand.nextInt(6) + 1; //Random value between 1-6
            int signValue = rand.nextInt(2);
            int sign;

            if (signValue == 0) {
                sign = 1;
            } else {
                sign = -1;
            }

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

        if (signValue == 0) {
            sign = 1;
        } else {
            sign = -1;
        }

        return new Card(color, value, sign);
    }


    private static String getRandomColor() {
        String[] colors = {"yellow", "blue", "red", "green"};
        Random rand = new Random();
        int index = rand.nextInt(colors.length);
        return colors[index];
    }




    public static void printDeck(Card[] deck) {
        for (Card card : deck) {
            System.out.print(card + ", ");
        }
        System.out.println(" ");
    }





    private static void playGame(Card[] newDeck, Card[] computerHand, Card[] userHand, Card[] computerBoard, Card[] userBoard) {
        Scanner sc = new Scanner(System.in);

        boolean userTurn = true; // user starts the game
        boolean gameEnded = false;
        boolean userStand = false;


        while (!gameEnded) {
            // Taking turns between players and
            // letting them decide on their moves ->> Requirement 5
            if (userTurn && !userStand) {
                Card drawnCard = drawCard(newDeck);
                userBoard = addCardToBoard(userBoard, drawnCard);
                newDeck=removeCard(newDeck, newDeck.length-1);
                System.out.println("Player's turn");
                System.out.println("1. End your turn");
                System.out.println("2. Stand");
                System.out.println("3. Play a card");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        userTurn = false;


                        break;

                    case 2:
                        userStand=true;
                        userTurn = false;

                        break;

                    case 3:
                       // System.out.println("User's Hand:");
                        //printDeck(userHand);

                        System.out.println("Choose a card to play (1-4):");
                        int cardIndex = sc.nextInt() - 1;

                        if (cardIndex >= 0 && cardIndex < userHand.length && userHand[cardIndex] != null) {
                            Card playedCard = userHand[cardIndex];
                            System.out.println("Played Card: " + playedCard.toString());
                            userHand = removeCard(userHand, cardIndex);
                            userBoard = addCardToBoard(userBoard, playedCard);
                            //evaluateMove(playedCard, userBoard);

                            // End the turn after playing a card
                            userTurn = false;
                        } else {
                            System.out.println("Invalid choice. Try again.");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else { // if computer's turn
                Card drawnCard = drawCard(newDeck);
                computerBoard = addCardToBoard(computerBoard, drawnCard);
                newDeck=removeCard(newDeck, newDeck.length-1);
                //evaluateMove(drawnCard, computerBoard);

                userTurn = true;



            }



            int userBoardSum = calculateBoardSum(userBoard);
            int computerBoardSum = calculateBoardSum(computerBoard);
            if (userBoardSum == 20) {
                System.out.println("User Wins!");
                gameEnded = true;

            } else if (computerBoardSum == 20) {
                System.out.println("Computer Wins!");
                gameEnded = true;
            }
            System.out.println("***********");
            System.out.println("Computer's Board:");
            printDeck(computerBoard);
            System.out.println("User's Board:");
            printDeck(userBoard);
            System.out.println("User's Hand: " );
            printDeck(userHand);

            System.out.println("***********");

        }
        sc.close();
}


    private static void evaluateMove(Card playedCard, Card[] board) {
        int sum = calculateBoardSum(board);

        if (sum > 20) {
            System.out.println("Bust! Sum is over 20. Player loses the set.");
        } else if (sum == 20) {
            System.out.println("Player wins the set!");
        }
    }


    private static int calculateBoardSum(Card[] board) {
        int sum = 0;
        for (Card card : board) {
            if (card != null) {
                sum += card.value * card.sign;
            }
        }
        return sum;
    }


    // Requirement 4
    private static Card[] addCardToBoard(Card[] board, Card card) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                board[i] = card;
                break;
            }
        }
        return board;
    }

    private static Card drawCard(Card[] deck) {
        if (deck.length == 0) {
            System.out.println("Deck is empty!");
            return null;
        }

        Card drawnCard = deck[deck.length - 1];
        deck= removeCard(deck, deck.length - 1);
        return drawnCard;

    }

    // shuffle method --> Requirement 2
    public static void shuffleDeck(Card[] deck) {
        Random rand = new Random();
        for (int i = 0; i < deck.length - 1; i++) {
            int j = rand.nextInt(i + 1);
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }



}


import java.util.Random;
import java.util.concurrent.Callable;

public class Bluejack {
    private class Card{
        String color;
        int value;
        String sign;

        public Card(String color, int value, String sign){
            this.color = color;
            this.value = value;
            this.sign = sign;
        }
    }
    public class Deck{
        private Card[] gamecards;
        private int size;

        public Deck(){
            gamecards = new Card[40];
            size=0;

            String [] colors = {"yellow","blue","red","green"};
            String [] signs = {"+","-"};

            int index=0;
            for (String color : colors){
                for(int value=1; value<=10; value++){
                    gamecards[index++] = new Card(color,value,"+");
                }
            }

            Random random = new Random();
            for (int i=0; i<3; i++){ // 3 signed cards (1-6 , +/-)
                String color = colors[new Random().nextInt(4)];
                int value = random.nextInt(6)+1; // value starts from 1
                String sign;
                if (random.nextBoolean()) {
                    sign="+";
                } else sign="-";
                gamecards[index++] = new Card(color,value,sign);
            }

            for (int i=0; i<2; i++) { //for other 2 cards
                if (random.nextDouble() < 0.2) {
                    gamecards[index++] = createFlipCard();
                } else 
            }
        }
        private Card createFlipCard() {
                return new Card("flip",0,"");
        }








        public void shuffle(){
            Random random = new Random();

            for(int i=0; i<size; i++){
                int randomIndex = random.nextInt(size);

                Card temp = gamecards[i];
                gamecards[i] = gamecards[randomIndex];
                gamecards[randomIndex] = temp;

            }
        }
    }

}















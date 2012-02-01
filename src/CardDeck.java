import java.util.*;

public class CardDeck {
	public static enum Value {A, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, J, Q, K };
	public static enum Suit {Spade, Heart, Diamond, Club};
	private static final Random generator = new Random();
	//public Card [] cards = new Card[52];
	public List<Card> cardList = new ArrayList<Card>();
	
	class Card {
		private Value value;
		private Suit suit;
		public Card (Value v, Suit s){
			value = v;
			suit = s;
		}
		public Value getValue(){
			return value;
		}
		public Suit getSuit() {
			return suit;
		}
		
		public String toString() {
			return "["+value+","+suit+"]";
		}
	}
	
	public CardDeck() {
		initializeDeck();
	}
	
	private void initializeDeck() {
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 13; j++){
//				cards[i*13+j] = new Card(Value.values()[j], Suit.values()[i]);
//			}
//		}
		for (Suit s : Suit.values()) {
			for (Value v: Value.values()) {
				cardList.add(new Card(v, s));
			}
		}
	}
	
	public void suffle() {
		for (int i = 0 ; i < cardList.size(); i++){
			int r = generator.nextInt(cardList.size());
			swap(i, r);
		}
	}
	
	private void swap (int i, int j) {
//		Card tmp = cards[i];
//		cards[i] = cards[j];
//		cards[j] = tmp;
		Card tmp = cardList.get(i);
		cardList.set(i, cardList.get(j));
		cardList.set(j, tmp);		
	}
	
	public static void main (String [] args) {
		CardDeck deck = new CardDeck();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++){
				System.out.print(deck.cardList.get(i*13+j).toString());
			}
			System.out.printf("%n");
		}
		System.out.printf("%nAfter Shuffle....%n");
	    deck.suffle();
	    for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++){
				System.out.print(deck.cardList.get(i*13+j).toString());
			}
			System.out.printf("%n");
		}
	    
	}
	
}
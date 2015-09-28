package ca.ualberta.cs.marini_reflex;

/**
 * Created by adrianomarini on 15-09-26.
 */
public class TwoPlayerStanding extends StandingObject {
    private int player2;

    public TwoPlayerStanding(int data, int player2) {
        super(data);
        this.player2 = player2;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }
}

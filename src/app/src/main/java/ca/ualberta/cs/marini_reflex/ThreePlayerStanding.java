package ca.ualberta.cs.marini_reflex;

/**
 * Created by adrianomarini on 15-09-26.
 */
public class ThreePlayerStanding extends StandingObject {
    private int player2;
    private int player3;

    public ThreePlayerStanding(int data, int player2, int player3) {
        super(data);
        this.player2 = player2;
        this.player3 = player3;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public int getPlayer3() {
        return player3;
    }

    public void setPlayer3(int player3) {
        this.player3 = player3;
    }
}

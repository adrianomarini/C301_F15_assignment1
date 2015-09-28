package ca.ualberta.cs.marini_reflex;

/**
 * Created by adrianomarini on 15-09-26.
 */
public abstract class StandingObject {
    private int data;

    public StandingObject(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}

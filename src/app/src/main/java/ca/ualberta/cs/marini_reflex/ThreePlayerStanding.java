/*Copyright (c) 2015 Adriano Marini

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/


package ca.ualberta.cs.marini_reflex;

//object that extends the ABC StandingObject to store data
//  for the three player mode
//Uses superclass data attribute to store Player1

public class ThreePlayerStanding extends StandingObject {
    private int player2;
    private int player3;

    //standard constructor. Ideal initialization (0,0,0).
    // sets and makes all of the local variables
    public ThreePlayerStanding(int data, int player2, int player3) {
        super(data);
        this.player2 = player2;
        this.player3 = player3;
    }


    //standard getters and setters for players 2 and 3
    //player 1 uses superclass' getter and setter.
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

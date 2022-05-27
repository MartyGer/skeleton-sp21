package gh2;


import deque.Deque;
import edu.princeton.cs.introcs.StdAudio;

import java.text.DecimalFormat;


//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /**
     * Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday.
     */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new deque.ArrayDeque<>();
        int capacity = (int) Math.round(SR / frequency);
        for (int i = 0; i < capacity; i++) {
            buffer.addFirst(0.00);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        // DEQUE EVERYTHING
        for (int i = 0; i < buffer.size(); i++) {
            buffer.removeFirst();
        }

        DecimalFormat dfZero = new DecimalFormat("0.0");
        int bufferSize = buffer.size();
        for (int i = 0; i < bufferSize; i++) {
            double r = Math.random() - 0.5;
            buffer.addFirst(Double.parseDouble(dfZero.format(r)));
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {

        Double newDouble = buffer.removeFirst();
        newDouble = ((newDouble + buffer.get(0)) / 2) * DECAY;
        buffer.addLast(newDouble);
        /*for (int i = 0; i < buffer.size(); i++) {
            Double newDouble = buffer.removeFirst();
            newDouble = ((newDouble + buffer.get(0)) / 2) * DECAY;
            buffer.addLast(newDouble);
        }*/
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }

    public static void main(String[] args) {
        GuitarString aString = new GuitarString(GuitarHeroLite.CONCERT_A);
        aString.pluck();
        System.out.println("name");
    }
}




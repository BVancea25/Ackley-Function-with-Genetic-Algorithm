package org.example;

/** Reprezinta o clasa destinata pentru calculul functiei lui Ackley pentru un set de numere
 */
public class Ackley {
    private final double x;
    private final double y;
    private double result;

    /**
     * Genes constructor
     * @param x gene x
     * @param y gene y
     */
    public Ackley(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method that runs the Ackley function for the certain genes
     */
    public void calculateResult(){
        this.result = - 20 * Math.exp(-0.2 * Math.sqrt(0.5*(x*x + y*y)))
                      - Math.exp(0.5*(Math.cos(2*Math.PI*x) + Math.cos(2*Math.PI*y)))
                      + Math.E + 20 ;
    }

    public double getResult(){
        return this.result;
    }
}

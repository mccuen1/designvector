package com.eonsahead.designvector;

public class Matrix2x2 {
    // fine I made it final
    private final double [][] matrix = new double [2][2];

    // assignment method to not have to specify specific digits
    public Matrix2x2(double[][] matrix) {
        this.matrix[0][0] = matrix[0][0];
        this.matrix[0][1] = matrix[0][1];
        this.matrix[1][0] = matrix[1][0];
        this.matrix[1][1] = matrix[1][1];
    }

    // second method if I dont already have a 'double[][]' array
    public Matrix2x2(double m00, double m01, double m10, double m11) {
        this.matrix[0][0] = m00;
        this.matrix[0][1] = m01;
        this.matrix[1][0] = m10;
        this.matrix[1][1] = m11;
    }

    // here for no real reason
    public final Matrix2x2 makeIndentity() {
        return new Matrix2x2(1, 0, 0, 1);
    }

    @Override
    public String toString() {
        return String.format("\n|%4.4f %4.4f|\n|%4.4f %4.4f|", 
            this.matrix[0][0], this.matrix[0][1], this.matrix[1][0], this.matrix[1][1]);
    }

    public Matrix2x2 multiply(Matrix2x2 other) {
        double [][] product = new double[2][2];

        for (int i=0; i<2; i++) {
            for (int j=0; j<2; j++) {
                // initializes the product array
                product[i][j] = 0.0;
                for (int k=0; k<2; k++) {
                    product[i][j] += this.matrix[i][k] * other.matrix[k][j];
                } // for k
            } // for j
        } // for i
        return new Matrix2x2(product);
    } // multiply Matrix2x2

    public static void main(String[] args) {
        Matrix2x2 m1 = new Matrix2x2(.5, 0, 0, .5);
        Matrix2x2 m2 = new Matrix2x2(2, 0, 0, 2);
        
        Matrix2x2 multiple = m1.multiply(m2);
        System.out.print(multiple);
    }
}

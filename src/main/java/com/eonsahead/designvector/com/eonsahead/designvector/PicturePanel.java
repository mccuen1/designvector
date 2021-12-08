package com.eonsahead.designvector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class PicturePanel extends JPanel {
    // TO-DO: Try a different background color.
    private static final Color BG_COLOR = new Color(160, 90, 190);
    // TO-DO: Try assigning other values to STEPS.
    private static final int STEPS = 200;

    public PicturePanel() {
        this.setBackground(BG_COLOR);
    } // PicturePanel()

    private final double radius(double angle) {
        return 0.5;
    } // radius( double )

    private final double x( double radius, double a, double b, double angle ) {
        return (a+b)*Math.cos(angle) - radius*Math.cos(((a/b)+1)*angle);
    } // x( double, double )
    
    private final double y( double radius, double a, double b, double angle ) {
        return (a+b)*Math.sin(angle) - radius*Math.sin(((a/b)+1)*angle);
    } // y( double, double )

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int w = this.getWidth();
        int h = this.getHeight();

        List<Vector2D> points = new ArrayList<>();
        double radius = 0.7;
        double a = -2.5;
        double b = 1.5;
        for (int i = 0; i < STEPS; i++) {
            // had to change this to get it to show all parts, from zero to 8 pi
            double angle = ((double) i) / STEPS * 8.0 * Math.PI;
            
            // Convert polar coordinates (radius, angle)
            // to Cartesian coordinates (x, y).
            double x = x( radius, a, b, angle );
            double y = y( radius, a, b, angle );
            points.add(new Vector2D(x, y));
        } // for

        // Scale to the size of the panel.
        Matrix3x3 scaling = new Matrix3x3();
        scaling.makeScaling(w / 2, h / 2);

        // Move geometry to center of panel.
        Matrix3x3 translation = new Matrix3x3();
        translation.makeTranslation(1.0, 1.0);

        // Combine the scaling and translation in
        // a single transformation.
        Matrix3x3 transformation = scaling.multiply(translation);

        Path2D path = new Path2D.Double();
        Vector2D p = transformation.multiply(points.get(0));
        double x = p.getX();
        double y = p.getY();
        path.moveTo(x, y);
        for (int i = 1; i < points.size(); i++) {
            p = transformation.multiply(points.get(i));
            x = p.getX();
            y = p.getY();
            path.lineTo(x, y);
        } // for
        //path.closePath();

        // TO-DO: Try drawing with a different color.
        g2D.setColor(Color.black);
        // TO-DO: Try changing the thickness of the line/curve.
        g2D.setStroke(new BasicStroke(4));
        // TO-DO: For some kinds of geometry, you might want
        // to try g2D.fill(path) rather than g2D.draw(path).
        g2D.draw(path);
    } // paintComponent( Graphics )
} // PicturePanel

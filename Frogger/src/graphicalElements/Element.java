package graphicalElements;

import gameCommons.Case;

import java.awt.*;


public class Element extends Case {
    public Color color;
    public Image image;

    /**
     * Constructeur d'éléments (1)
     * @param absc
     * 				abscisse de l'élément
     * @param ord
     * 				ordonnée de l'élément
     * @param image
     * 				image de l'élément
     */
    public Element(int absc, int ord, Image image) {
        super(absc, ord);
        this.image = image;
    }
    
    /**
     * Constructeur d'éléments (2)
     * @param absc
     * 				abscisse de l'élément
     * @param ord
     * 				ordonnée de l'élément
     * @param color
     * 				couleur de l'élément
     */
    public Element(int absc, int ord, Color color) {
        super(absc, ord);
        this.color = color;
    }
    
    /**
     * Constructeur d'éléments (3)
     * @param c
     * 				case de l'élément
     * @param image
     * 				image de l'élément
     */
    public Element(Case c, Image image) {
        super(c.absc, c.ord);
        this.image = image;
    }
    
    /**
     * Constructeurs d'éléments (4)
     * @param c
     * 				case de l'élément
     * @param color
     * 				couleur de l'élément
     */
    public Element(Case c, Color color) {
        super(c.absc, c.ord);
        this.color = color;
    }
    
}

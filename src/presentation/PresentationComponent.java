package presentation;

import core.ApplicationWindow;

import javax.swing.*;
import java.awt.*;


/**
 * <p>SlideViewerComponent is a graphical component that ca display Slides.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class PresentationComponent extends JComponent {
    private static final Color BGCOLOR = Color.white;
    private static final Color COLOR = Color.black;
    private static final String FONTNAME = "Dialog";
    private static final int FONTSTYLE = Font.BOLD;
    private static final int FONTHEIGHT = 10;
    private static final int XPOS = 1100;
    private static final int YPOS = 20;

    private Font labelFont;
    private Presentation presentation;
    private Style[] styles;

    public PresentationComponent() {
        this.setBackground(BGCOLOR);
        this.presentation = new Presentation();
        this.labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
        this.styles = new Style[5];
        createDefaultStyles();
    }

    private void createDefaultStyles() {
        this.styles[0] = new Style(0, Color.red, 48, 20);
        this.styles[1] = new Style(20, Color.blue, 40, 10);
        this.styles[2] = new Style(50, Color.black, 36, 10);
        this.styles[3] = new Style(70, Color.black, 30, 10);
        this.styles[4] = new Style(90, Color.black, 24, 10);
    }

    private Style getStyle(int level) {
        if (this.isValidStyle(level)) {
            return this.styles[level];
        }

        return this.styles[4];
    }

    private boolean isValidStyle(int level) {
        return level >= 0 && level < this.styles.length;
    }

    public Presentation getPresentation() {
        return this.presentation;
    }

    //Draw the slide
    public void paintComponent(Graphics graphics) {
        this.repaint();
        Slide slide = this.presentation.getCurrentSlide();

        graphics.setColor(BGCOLOR);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);

        if (slide == null) {
            return;
        }

        graphics.setFont(this.labelFont);
        graphics.setColor(COLOR);
        graphics.drawString("Slide " + (1 + this.presentation.getSlideNumber()) + " of " + this.presentation.getSlidesAmount(), XPOS, YPOS);
        Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));

        this.drawSlide(slide, graphics, area);
    }

    private void drawSlide(Slide slide, Graphics graphics, Rectangle area) {
        float scale = getScale(area);
        int y = area.y;

        //The title is treated separately
        SlideItem textSlideItem = new TextItem(0, slide.getTitle());
        Style style = this.getStyle(textSlideItem.getLevel());
        textSlideItem.draw(area.x, y, scale, graphics, style);
        y += textSlideItem.getBoundingBox(graphics, scale, style).height;

        for (SlideItem slideItem : slide.getSlideItems()) {
            if (slideItem instanceof BitmapItem) {
                ((BitmapItem) slideItem).setImageObserver(this);
            }
            style = this.getStyle(slideItem.getLevel());
            slideItem.draw(area.x, y, scale, graphics, style);
            y += slideItem.getBoundingBox(graphics, scale, style).height;
        }
    }

    //Returns the scale to draw a slide
    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) ApplicationWindow.WIDTH), ((float) area.height) / ((float) ApplicationWindow.HEIGHT));
    }
}

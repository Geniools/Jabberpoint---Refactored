package presentation;

import core.ApplicationWindow;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>A text item.</p>
 * <p>A text item has drawing capabilities.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class TextItem extends SlideItem {
    private static final String EMPTYTEXT = "No Text Given";
    private String text;

    //A presentation.TextItem of int level with text string
    public TextItem(int level, String string) {
        super(level);
        text = string;
    }

    //Returns the text
    public String getText() {
        return text == null ? "" : text;
    }

    //Returns the AttributedString for the Item
    public AttributedString getAttributedString(Style style, float scale) {
        AttributedString attrStr = new AttributedString(getText());

        // If a element is empty
        if (getText().length() == 0) {
            attrStr = new AttributedString(EMPTYTEXT);
            return attrStr;
        }

        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, getText().length());
        return attrStr;
    }

    @Override
    //Returns the bounding box of an Item
    public Rectangle getBoundingBox(Graphics graphics, float scale, Style myStyle) {
        List<TextLayout> layouts = getLayouts(graphics, myStyle, scale);
        int xsize = 0, ysize = (int) (myStyle.getLeading() * scale);

        for (TextLayout layout : layouts) {
            Rectangle2D bounds = layout.getBounds();
            if (bounds.getWidth() > xsize) {
                xsize = (int) bounds.getWidth();
            }
            if (bounds.getHeight() > 0) {
                ysize += bounds.getHeight();
            }
            ysize += layout.getLeading() + layout.getDescent();
        }

        return new Rectangle((int) (myStyle.getIndent() * scale), 0, xsize, ysize);
    }

    @Override
    //Draws the item
    public void draw(int x, int y, float scale, Graphics graphics, Style myStyle) {
        if (getText().length() == 0) {
            return;
        }
        List<TextLayout> layouts = getLayouts(graphics, myStyle, scale);
        Point pen = new Point(x + (int) (myStyle.getIndent() * scale),
                y + (int) (myStyle.getLeading() * scale));
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(myStyle.getColor());

        for (TextLayout layout : layouts) {
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();
        }
    }

    private List<TextLayout> getLayouts(Graphics g, Style style, float scale) {
        List<TextLayout> layouts = new ArrayList<>();
        AttributedString attrStr = getAttributedString(style, scale);
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        float wrappingWidth = (ApplicationWindow.WIDTH - style.getIndent()) * scale;

        while (measurer.getPosition() < getText().length()) {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            layouts.add(layout);
        }
        return layouts;
    }

    @Override
    public String toString() {
        return "presentation.TextItem[" + getLevel() + "," + getText() + "]";
    }
}

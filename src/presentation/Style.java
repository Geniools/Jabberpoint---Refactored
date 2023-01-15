package presentation;

import java.awt.*;

/**
 * <p>presentation.Style stands for Indent, Color, Font and Leading.</p>
 * <p>The link between a style number and a item level is hard-linked:
 * in presentation.Slide the style is grabbed for an item
 * with a style number the same as the item level.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Style {
    private static final String FONTNAME = "Helvetica";
    private int indent;
    private Color color;
    private Font font;
    private int fontSize;
    private int leading;

    public Style(int indent, Color color, int fontSize, int leading) {
        font = new Font(FONTNAME, Font.BOLD, this.fontSize = fontSize);
        this.indent = indent;
        this.color = color;
        this.leading = leading;
    }

    public int getIndent() {
        return this.indent;
    }

    public Color getColor() {
        return this.color;
    }

    public Font getFont() {
        return this.font;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public int getLeading() {
        return this.leading;
    }

    @Override
    public String toString() {
        return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
    }

    public Font getFont(float scale) {
        return font.deriveFont(fontSize * scale);
    }
}

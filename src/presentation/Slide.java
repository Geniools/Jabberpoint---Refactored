package presentation;

import java.util.ArrayList;

/**
 * <p>A slide. This class has drawing functionality.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide {
    // The title is kept separately
    private String title;
    private ArrayList<SlideItem> items;

    public Slide() {
        this.items = new ArrayList<>();
    }

    // Return the title of a slide
    public String getTitle() {
        return this.title;
    }

    // Change the title of a slide
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    // Return all the SlideItems in an ArrayList
    public ArrayList<SlideItem> getSlideItems() {
        return this.items;
    }

    // Returns the SlideItem
    public SlideItem getSlideItem(int number) {
        return this.items.get(number);
    }

    public int getItemsAmount() {
        return this.items.size();
    }

    // Add a SlideItem
    public void append(SlideItem newItem) {
        this.items.add(newItem);
    }

    // Create a TextItem out of a String and add the TextItem
    public void append(int level, String message) {
        append(new TextItem(level, message));
    }
}

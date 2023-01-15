package core;

import presentation.PresentationComponent;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * <p>The application window for the presentation.PresentationComponent</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class ApplicationWindow extends JFrame {
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;

    private PresentationComponent presentationComponent;
    private Dimension dimension;


    public ApplicationWindow(String title, PresentationComponent presentationComponent) {
        super(title);
        this.presentationComponent = presentationComponent;
        this.dimension = new Dimension(WIDTH, HEIGHT);
    }

    // Setup the GUI
    public void initialize() {
        getContentPane().add(this.presentationComponent);
        this.setSize(this.dimension);
        this.setVisible(true);
    }

    public PresentationComponent getPresentationComponent() {
        return this.presentationComponent;
    }

    public Dimension getDimension() {
        return this.dimension;
    }
}

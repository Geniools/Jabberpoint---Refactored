package core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import presentation.Presentation;

/**
 * <p>This is the core.KeyController (KeyListener)</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class KeyController extends KeyAdapter implements ApplicationController {
    private Presentation presentation;

    public KeyController() {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_DOWN, KeyEvent.VK_ENTER, '+' -> this.presentation.nextSlide();
            case KeyEvent.VK_PAGE_UP, KeyEvent.VK_UP, '-' -> this.presentation.prevSlide();
            case 'q', 'Q' -> System.exit(0);
            //Should not be reached
            default -> {
            }
        }
    }

    @Override
    public void connectToApplication(ApplicationWindow application) {
        this.presentation = application.getPresentationComponent().getPresentation();
        application.addKeyListener(this);
    }
}

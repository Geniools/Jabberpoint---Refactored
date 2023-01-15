import api.Accessor;
import api.XMLAccessor;
import core.ApplicationWindow;
import core.Controller;
import core.KeyController;
import core.MenuController;
import presentation.PresentationComponent;

import javax.swing.*;
import java.io.IOException;

/**
 * JabberPoint Main Program
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class JabberPoint {
    protected static final String IOERR = "IO Error: ";
    protected static final String JABERR = "Jabberpoint Error ";
    protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

    /**
     * The main program
     */
    public static void main(String[] argv) {
        PresentationComponent presentationComponent = new PresentationComponent();
        ApplicationWindow applicationWindow = new ApplicationWindow(JABVERSION, presentationComponent);

        Controller controller = new Controller(applicationWindow);
        MenuController menuController = new MenuController();
        KeyController keyController = new KeyController();
        controller.addController(menuController);
        controller.addController(keyController);


        try {
            // a demo presentation is loaded by default
            if (argv.length == 0) {
                Accessor.getDemoAccessor().loadFile(presentationComponent.getPresentation(), "");
            } else {
                new XMLAccessor().loadFile(presentationComponent.getPresentation(), argv[0]);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    IOERR + ex, JABERR,
                    JOptionPane.ERROR_MESSAGE);
        }

        // Running the application
        controller.run();
    }
}

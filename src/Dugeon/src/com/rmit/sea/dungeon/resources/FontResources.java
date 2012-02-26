package com.rmit.sea.dungeon.resources;

import com.rmit.sea.view.renderers.TextCenterRenderer;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FontResources {

    public static final float DEFAULT_FONT_SIZE = 20f;
//    public static final String DEFAULT_FONT_NAME = Constant.RES_DIR + "Symbola605.ttf";
    public static final String DEFAULT_FONT_NAME = Constant.RES_DIR + "DejaVuSansMono.ttf";
    private static FontResources instance;
    private Font font;
    private int type;

    private FontResources() {
    }

    public static FontResources getInstance() {
        if (instance == null) {
            instance = new FontResources();
        }
        return instance;
    }

    public Font getDefaultFont(int type, float size) {
        if (font == null || this.type != type) {
            InputStream fin = null;

            try {
                fin = new FileInputStream(DEFAULT_FONT_NAME);
                font = Font.createFont(type, fin).deriveFont(DEFAULT_FONT_SIZE);
                this.type = type;
                fin.close();
            } catch (FontFormatException ex) {
                Logger.getLogger(TextCenterRenderer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TextCenterRenderer.class.getName()).log(Level.SEVERE, null, ex);

                // Return the default font
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                font = ge.getAllFonts()[0];
            }
        }

        if (DEFAULT_FONT_SIZE != size) {
            return font.deriveFont(size);
        }


        return font;
    }
}

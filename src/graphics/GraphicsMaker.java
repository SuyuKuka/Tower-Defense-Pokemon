package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class GraphicsMaker {

    private Font customFont;

    public GraphicsMaker() {
        try {

            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("ressources/munro.ttf"));
            customFont = customFont.deriveFont(Font.ITALIC, 60f);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();

        }
    }

    public Font getCustomFont() {
        return customFont;
    }

}

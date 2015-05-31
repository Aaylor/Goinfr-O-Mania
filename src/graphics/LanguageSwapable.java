package graphics;

import java.util.ResourceBundle;

/**
 * Created by PixelMan on 25/05/15.
 */
@SuppressWarnings("DefaultFileTemplate")
public interface LanguageSwapable {

    /**
     * Change all the language of all the texts visible on a component
     * @param bundle The new bundle to use to get texts
     */
    public void swapLang(ResourceBundle bundle);
}

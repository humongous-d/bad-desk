package me.piguy.baddesk.pages.theme;

import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.Theme;

public class DraculaTheme extends PageTheme {

    @Override
    public String getThemeName() {
        return "Dracula";
    }

    @Override
    public Theme getThemeLight() {
        return getThemeDark();
    }

    @Override
    public Theme getThemeDark() {
        return new Dracula();
    }

    @Override
    public boolean hasVariation() {
        return false;
    }
}

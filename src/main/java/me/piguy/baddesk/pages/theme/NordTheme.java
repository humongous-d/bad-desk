package me.piguy.baddesk.pages.theme;

import atlantafx.base.theme.NordDark;
import atlantafx.base.theme.NordLight;
import atlantafx.base.theme.Theme;

public class NordTheme extends PageTheme {
    @Override
    public String getThemeName() {
        return "Nord";
    }

    @Override
    public Theme getThemeLight() {
        return new NordLight();
    }

    @Override
    public Theme getThemeDark() {
        return new NordDark();
    }

    @Override
    public boolean hasVariation() {
        return true;
    }
}

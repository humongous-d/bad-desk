package me.piguy.baddesk.pages.theme;

import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Theme;

public class CupertinoTheme extends PageTheme {
    @Override
    public String getThemeName() {
        return "Cupertino";
    }

    @Override
    public Theme getThemeLight() {
        return new CupertinoLight();
    }

    @Override
    public Theme getThemeDark() {
        return new CupertinoDark();
    }

    @Override
    public boolean hasVariation() {
        return true;
    }
}

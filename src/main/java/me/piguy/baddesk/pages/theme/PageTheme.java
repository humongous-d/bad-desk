package me.piguy.baddesk.pages.theme;


import atlantafx.base.theme.Theme;

import java.util.List;

public abstract class PageTheme {
    public abstract String getThemeName();
    public abstract Theme getThemeLight();
    public abstract Theme getThemeDark();
    public abstract boolean hasVariation();
}

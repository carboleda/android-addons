package test.addon.cfag.com.addontest.model;

import android.graphics.drawable.Drawable;

/**
 * Created by krlosf on 4/03/15.
 */
public class AddonItem {
    public String label;
    public Drawable icon;
    public String pakcageName;
    public String className;

    public AddonItem(String label, Drawable icon, String pakcageName, String className) {
        this.label = label;
        this.icon = icon;
        this.pakcageName = pakcageName;
        this.className = className;
    }
}

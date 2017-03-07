package com.example.kendraslaptop.fontchooser;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Kendra's Laptop on 3/2/2017.
 */

public class FontType extends AppCompatActivity {



    public Typeface getTypeFace(String selectedType) {
        if(selectedType  == null || selectedType.isEmpty()) {
            return Typeface.DEFAULT;
        } else if("Default".equals(selectedType)) {
            return Typeface.DEFAULT;
        } else if("Bold Default".equals(selectedType)) {
            return Typeface.DEFAULT_BOLD;
        } else if("Monospace".equals(selectedType)) {
            return Typeface.MONOSPACE;
        } else if("Sans Serif".equals(selectedType)) {
            return Typeface.SANS_SERIF;
        } else if("Serif".equals(selectedType)) {
            return Typeface.SERIF;
        } else {
            return Typeface.DEFAULT;
        }
    }

    public int getStyle(String selectedStyle) {
        if(selectedStyle  == null || selectedStyle.isEmpty()) {
            return Typeface.NORMAL;
        } else if("Normal".equals(selectedStyle)) {
            return Typeface.NORMAL;
        } else if("Bold".equals(selectedStyle)) {
            return Typeface.BOLD;
        } else if("Italic".equals(selectedStyle)) {
            return Typeface.ITALIC;
        } else if("Bold Italic".equals(selectedStyle)) {
            return Typeface.BOLD_ITALIC;
        } else {
            return Typeface.NORMAL;
        }
    }
}

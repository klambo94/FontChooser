package com.example.kendraslaptop.fontchooser;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner typeFaceSpinner, textStyleSpinner;
    private SeekBar size, redBar, blueBar, greenBar;
    private TextView sampleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setIds();

        addItemsAndSetListenerOnFontStyle();

        addListenerToSeekBars();

    }

    private void setIds() {
        typeFaceSpinner  = (Spinner) findViewById(R.id.typeFaceDropdown);
        textStyleSpinner = (Spinner) findViewById(R.id.fontStyleDropDown);

        size = (SeekBar) findViewById(R.id.textSizeBar);
        redBar = (SeekBar) findViewById(R.id.redSlider);
        blueBar = (SeekBar) findViewById(R.id.blueSlider);
        greenBar = (SeekBar) findViewById(R.id.greenSlider);
        sampleText = (TextView) findViewById(R.id.sampleString);
    }

    private void addItemsAndSetListenerOnFontStyle() {
        addItemsToSpinner(typeFaceSpinner, R.array.typeFaceArray);
        addItemsToSpinner(textStyleSpinner, R.array.typeStyleArray);

        addListenerOnSpinnerItemSelection(typeFaceSpinner);
        addListenerOnSpinnerItemSelection(textStyleSpinner);
    }
    private void addListenerToSeekBars() {
        addListenerOnSeekBar(size);
        addListenerOnSeekBar(redBar);
        addListenerOnSeekBar(blueBar);
        addListenerOnSeekBar(greenBar);
    }

    public void addItemsToSpinner(Spinner spinner, int array) {
        List<String> spinnerItems = new ArrayList<>();

        String selectedItem = String.valueOf(spinner.getSelectedItem());
        String[] typeFaceItems = getResources().getStringArray(array);
        for(String item : typeFaceItems) {
            if(!item.equals(selectedItem)) {
                spinnerItems.add(item);
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, spinnerItems);
        spinner.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection(Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),
                        parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                changeFont();
            }

            @Override
            public void onNothingSelected(AdapterView<?> args) {

            }
        });
    }

    public void addListenerOnSeekBar(SeekBar seekBar){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){


            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(seekBar == size) {
                    changeSize();
                } else {
                    changeColor();
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void changeSize() {
        sampleText.setTextSize(size.getProgress());
    }

    public void changeColor() {
        int blueProg = blueBar.getProgress();
        int greenProg = greenBar.getProgress();
        int redProg = redBar.getProgress();

        sampleText.setTextColor(Color.rgb(redProg, greenProg,blueProg));
    }

    public void changeFont() {
        String selectedStyle = textStyleSpinner.getSelectedItem().toString();
        String selectedType = typeFaceSpinner.getSelectedItem().toString();


        int style = getStyle(selectedStyle);
        Typeface typeFace = getTypeFace(selectedType);
        sampleText.setTypeface(typeFace, style);
    }

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

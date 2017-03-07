package com.example.kendraslaptop.fontchooser;

import android.content.Intent;
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

    private SeekBar sizeBar, redBar, blueBar, greenBar;
    private Spinner typeFaceSpinner, textStyleSpinner;

    private TextView sampleText;
    private Intent incomingIntent;
    private Intent outgoingIntent;

    //Return information
    private int size;
    private int color_blue;
    private int color_red;
    private int color_green;
    private int typeSytleId;
    private String typeFaceId;

    //keys
    private static final String SIZE = "SIZE";
    private static final String FONT_FACE = "FONT FACE";
    private static final String FONT_STYLE = "FONT STYLE";
    private static final String COLOR_RED = "COLOR RED";
    private static final String COLOR_GREEN = "COLOR GREEN";
    private static final String COLOR_BLUE = "COLOR BLUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setIds();
        setUpSeekBars();
        setUpSpinners();

        incomingIntent = getIntent();
        outgoingIntent = new Intent();
    }

    private void setIds() {
        typeFaceSpinner  = (Spinner) findViewById(R.id.typeFaceDropdown);
        textStyleSpinner = (Spinner) findViewById(R.id.fontStyleDropDown);
        sizeBar = (SeekBar) findViewById(R.id.textSizeBar);
        redBar = (SeekBar) findViewById(R.id.redSlider);
        blueBar = (SeekBar) findViewById(R.id.blueSlider);
        greenBar = (SeekBar) findViewById(R.id.greenSlider);
        sampleText = (TextView) findViewById(R.id.sampleString);
    }


    private void setUpSeekBars() {
        SeekBar[] seekBars = {sizeBar, redBar, blueBar, greenBar};
       for(SeekBar bar : seekBars) {
           addListenerOnSeekBar(bar);
       }
    }

    public void addListenerOnSeekBar(SeekBar seekBar){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){


            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(seekBar == sizeBar) {
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

    private void setUpSpinners() {
        Spinner[] spinners = {typeFaceSpinner, textStyleSpinner};
        int[] arrays = {R.array.typeFaceArray, R.array.typeStyleArray};
        for(int i =0; i < spinners.length && i < arrays.length; i++) {
            addItemsToSpinner(spinners[i], arrays[i]);
            addListenerOnSpinnerItemSelection(spinners[i]);
        }
    }

    private void addItemsToSpinner(Spinner spinner, int array) {
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

    private void addListenerOnSpinnerItemSelection(Spinner spinner) {
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

    public void changeFont() {
        String selectedStyle = textStyleSpinner.getSelectedItem().toString();
        String selectedType = typeFaceSpinner.getSelectedItem().toString();

        FontType fontType = new FontType();

        typeSytleId = fontType.getStyle(selectedStyle);
        typeFaceId = selectedType;//ontType.getTypeFace(selectedType);
        sampleText.setTypeface(fontType.getTypeFace(typeFaceId), typeSytleId);
    }

    public void changeSize() {
        size = sizeBar.getProgress();
        sampleText.setTextSize(size);
    }

    public void changeColor() {
        color_blue = blueBar.getProgress();
        color_green = greenBar.getProgress();
        color_red = redBar.getProgress();

        sampleText.setTextColor(Color.rgb(color_red, color_green, color_blue));
    }

    public void saveChanges(View view) {
        finish();
    }

    @Override
    public void finish() {
        outgoingIntent.putExtra(SIZE, size);
        outgoingIntent.putExtra(FONT_STYLE, typeSytleId);
        outgoingIntent.putExtra(FONT_FACE, typeFaceId);
        outgoingIntent.putExtra(COLOR_BLUE, color_blue);
        outgoingIntent.putExtra(COLOR_GREEN, color_green);
        outgoingIntent.putExtra(COLOR_RED, color_red);
        setResult(RESULT_OK, outgoingIntent);
        super.finish();
    }

}

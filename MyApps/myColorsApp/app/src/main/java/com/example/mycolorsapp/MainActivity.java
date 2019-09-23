package com.example.mycolorsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    //1. Instance id's from xml form design
    private SeekBar sbrRed = null;
    private SeekBar sbrGreen = null;
    private SeekBar sbrBlue = null;
    private SeekBar sbrAlpha = null;
    private View vieColors = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. Get id's from xml form design
        sbrRed = findViewById(R.id.sbrRed);
        sbrGreen = findViewById(R.id.sbrGreen);
        sbrBlue = findViewById(R.id.sbrBlue);
        sbrAlpha = findViewById(R.id.sbrAlpha);
        vieColors = findViewById(R.id.vieColors);

        //3. Set Seekbar change on moving
        sbrRed.setOnSeekBarChangeListener(this);
        sbrGreen.setOnSeekBarChangeListener(this);
        sbrBlue.setOnSeekBarChangeListener(this);
        sbrAlpha.setOnSeekBarChangeListener(this);

        //4. Touch on vieColors. Enable context menu on view component
        registerForContextMenu(vieColors);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int r = sbrRed.getProgress();
        int g = sbrGreen.getProgress();
        int b = sbrBlue.getProgress();
        int a = sbrAlpha.getProgress();

        int color = Color.argb(a, r, g, b);
        vieColors.setBackgroundColor(color);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    //Show the menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("::: " + getString(R.string.selColorHeaderTitle) + " :::");
        getMenuInflater().inflate(R.menu.options1, menu);

        //Hide reset and about of buttons for context menu
        menu.findItem(R.id.iteReset).setVisible(false);
        menu.findItem(R.id.iteAboutOf).setVisible(false);
    }

    //Button actions
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.iteTransparent: //Transparent
                sbrAlpha.setProgress(0);
                return true;
            case R.id.iteSemitransparent: //Semitransparent
                sbrAlpha.setProgress(sbrAlpha.getMax() / 2);
                return true;
            case R.id.iteOpaque: //Opaque
                sbrAlpha.setProgress(sbrAlpha.getMax());
                return true;
            case R.id.iteYellow: //Yellow
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(0);
                return true;
            case R.id.iteBlue: //Blue
                sbrRed.setProgress(0);
                sbrGreen.setProgress(0);
                sbrBlue.setProgress(sbrBlue.getMax());
                return true;
            case R.id.iteRed: //Red
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(0);
                sbrBlue.setProgress(0);
                return true;
            case R.id.iteGreen: //Green
                sbrRed.setProgress(0);
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(0);
                return true;
            case R.id.iteOrange: //Orange
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax() / 2);
                sbrBlue.setProgress(0);
                return true;
            case R.id.itePurple: //Purple
                sbrRed.setProgress(sbrRed.getMax() / 2);
                sbrGreen.setProgress(0);
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteBrown: //Brown
                sbrRed.setProgress(sbrRed.getMax() / 2);
                sbrGreen.setProgress(sbrGreen.getMax() / 4);
                sbrBlue.setProgress(0);
                return true;
            case R.id.iteLightYellow: //Light yellow
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteCyan: //Cyan
                sbrRed.setProgress(0);
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(sbrBlue.getMax());
                return true;
            case R.id.itePink: //Pink
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax() / 2);
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteLightGreen: //Light green
                sbrRed.setProgress(sbrRed.getMax() / 2);
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteApricot: //Apricot
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax() / 2 + sbrGreen.getMax() / 4);
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteMagenta: //Magenta
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(0);
                sbrBlue.setProgress(sbrBlue.getMax());
                return true;
            case R.id.iteBlack: //Black
                sbrRed.setProgress(0);
                sbrGreen.setProgress(0);
                sbrBlue.setProgress(0);
                return true;
            case R.id.iteGray: //Gray
                sbrRed.setProgress(sbrRed.getMax() / 2);
                sbrGreen.setProgress(sbrGreen.getMax() / 2);
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteWhite: //White
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(sbrBlue.getMax());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iteReset: //Reset
                sbrRed.setProgress(0);
                sbrGreen.setProgress(0);
                sbrBlue.setProgress(0);
                sbrAlpha.setProgress(0);
                return true;
            case R.id.iteTransparent: //Transparent
                sbrAlpha.setProgress(0);
                return true;
            case R.id.iteSemitransparent: //Semitransparent
                sbrAlpha.setProgress(sbrAlpha.getMax() / 2);
                return true;
            case R.id.iteOpaque: //Opaque
                sbrAlpha.setProgress(sbrAlpha.getMax());
                return true;
            case R.id.iteYellow: //Yellow
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(0);
                return true;
            case R.id.iteBlue: //Blue
                sbrRed.setProgress(0);
                sbrGreen.setProgress(0);
                sbrBlue.setProgress(sbrBlue.getMax());
                return true;
            case R.id.iteRed: //Red
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(0);
                sbrBlue.setProgress(0);
                return true;
            case R.id.iteGreen: //Green
                sbrRed.setProgress(0);
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(0);
                return true;
            case R.id.iteOrange: //Orange
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax() / 2);
                sbrBlue.setProgress(0);
                return true;
            case R.id.itePurple: //Purple
                sbrRed.setProgress(sbrRed.getMax() / 2);
                sbrGreen.setProgress(0);
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteBrown: //Brown
                sbrRed.setProgress(sbrRed.getMax() / 2);
                sbrGreen.setProgress(sbrGreen.getMax() / 4);
                sbrBlue.setProgress(0);
                return true;
            case R.id.iteLightYellow: //Light yellow
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteCyan: //Cyan
                sbrRed.setProgress(0);
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(sbrBlue.getMax());
                return true;
            case R.id.itePink: //Pink
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax() / 2);
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteLightGreen: //Light green
                sbrRed.setProgress(sbrRed.getMax() / 2);
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteApricot: //Apricot
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax() / 2 + sbrGreen.getMax() / 4);
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteMagenta: //Magenta
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(0);
                sbrBlue.setProgress(sbrBlue.getMax());
                return true;
            case R.id.iteBlack: //Black
                sbrRed.setProgress(0);
                sbrGreen.setProgress(0);
                sbrBlue.setProgress(0);
                return true;
            case R.id.iteGray: //Gray
                sbrRed.setProgress(sbrRed.getMax() / 2);
                sbrGreen.setProgress(sbrGreen.getMax() / 2);
                sbrBlue.setProgress(sbrBlue.getMax() / 2);
                return true;
            case R.id.iteWhite: //White
                sbrRed.setProgress(sbrRed.getMax());
                sbrGreen.setProgress(sbrGreen.getMax());
                sbrBlue.setProgress(sbrBlue.getMax());
                return true;
            case R.id.iteAboutOf: //White
                startActivity(new Intent(getApplicationContext(), AboutOfActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*Tarea
    1. Aplicar el menu contextual
    2. Aplicar el menu de opciones
    3. Reset icon = Default (0)
    4. Crear el acerca de (nueva actividad que contenga la información personal)
     */
}

package com.example.uploadx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class IntermediateActivity extends AppCompatActivity {
    public EditText countentry;
    public EditText blendentry;
    public Spinner mySpinner;
    public Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);
        countentry=(EditText) findViewById(R.id.countentry);
        blendentry=(EditText) findViewById(R.id.blendentry);
        b1=(Button) findViewById(R.id.view);
        mySpinner=(Spinner) findViewById(R.id.machine_spinner);
        ArrayAdapter<String> myAdapter=  new ArrayAdapter<String>(IntermediateActivity.this
        ,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.machine));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String coun=countentry.getText().toString();
                String bend=blendentry.getText().toString();
                String machine = mySpinner.getSelectedItem().toString();
                if(machine.equals("Gill GC-1")) {
                    Intent intent=new Intent(IntermediateActivity.this,GillGC1.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                 }
                else if(machine.equals("Gill GC-2"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,GillGC2.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("Gill GC-3"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,GillGC3.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("Gill GC-4"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,GillGC4.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("Gill GN-1"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,GillGN1.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("Gill GN-2"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,GillGN2.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("Gill GN-3"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,GillGN3.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("Gill GN-4"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,GillGN4.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("GV-20"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,GV20.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("FM-7"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,FM7.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("BM-13,15"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,BM13.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("BM-20"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,BM20.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("Textool Ring Frame"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,TextTool.class);
                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("SA Ring Frame"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,SARING.class);

                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("Zinger Ring Frame"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,Zinser.class);

                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("Two For One"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,TwoForOne.class);

                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("Quantum 2 on Manchoner"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,Machconer.class);

                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("SSM Ply Winding"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,Ssm.class);

                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }
                else if(machine.equals("Quantum2 on Autoconer 338"))
                {
                    Intent intent=new Intent(IntermediateActivity.this,QuantAutoconer.class);

                    Log.i("TagCoun", coun);
                    Log.i("TagBend", bend);
                    Log.i("TagMachine", machine);
                    intent.putExtra("coun", coun);
                    intent.putExtra("bend", bend);
                    startActivity(intent);
                }

            }
        });

    }
}
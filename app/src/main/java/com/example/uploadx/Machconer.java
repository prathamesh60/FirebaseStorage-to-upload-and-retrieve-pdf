package com.example.uploadx;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Machconer extends AppCompatActivity {
    public TextView tpi;
    public TextView s_d;
    public TextView neps;
    public TextView short1;
    public TextView long1;
    public TextView thin;
    public TextView cp;
    public TextView cm;
    public TextView ccp;
    public TextView ccm;
    public TextView jp;
    public TextView pc_sensitivity;
    public TextView pc_fault;
    public TextView pc_length;
    public TextView pc_no_of_faults;
    public TextView upper_yarn;
    public TextView h1_snl;
    public TextView h2_snl;
    public TextView h3_snl;
    public TextView h4_snl;
    public TextView h5_snl;
    public TextView h1_thin;
    public TextView h2_thin;
    public TextView h3_thin;
    public TextView speed;
    public TextView yarn_strength;
    public TextView splicing_nozzle;
    public TextView front_plate;
    public TextView yarn_holding_lever;
    public TextView ln_lever;
    public TextView p1;
    public TextView p2;
    public TextView p6;
    public TextView s10;
    public TextView composition;



    public String coun;
    public String bend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machconer);
        tpi=(TextView) findViewById(R.id.tpi);
        s_d =(TextView)findViewById(R.id.s_d);
        neps=(TextView)findViewById(R.id.neps);
        short1=(TextView)findViewById(R.id.short1);
        long1=(TextView)findViewById(R.id.long1);
        thin=(TextView)findViewById(R.id.thin);
        cp=(TextView)findViewById(R.id.cp);
        cm=(TextView)findViewById(R.id.cm);
        ccp=(TextView)findViewById(R.id.ccp);
        ccm=(TextView) findViewById(R.id.ccm);
        jp=(TextView)findViewById(R.id.jp);
        pc_sensitivity=(TextView) findViewById(R.id.pc_sensitivity);
        pc_fault =(TextView)findViewById(R.id.pc_fault);
        pc_length=(TextView)findViewById(R.id.pc_length);
        pc_no_of_faults=(TextView)findViewById(R.id.pc_no_of_faults);
        upper_yarn=(TextView)findViewById(R.id.upper_yarn);
        h1_snl=(TextView)findViewById(R.id.h1_snl);
        h2_snl=(TextView)findViewById(R.id.h2_snl);
        h3_snl=(TextView)findViewById(R.id.h3_snl);
        h4_snl=(TextView)findViewById(R.id.h4_snl);
        h5_snl=(TextView) findViewById(R.id.h5_snl);
        h1_thin=(TextView)findViewById(R.id.h1_thin);
        h2_thin=(TextView) findViewById(R.id.h2_thin);
        h3_thin =(TextView)findViewById(R.id.h3_thin);
        speed=(TextView)findViewById(R.id.speed);
        yarn_strength=(TextView)findViewById(R.id.yarn_strength);
        splicing_nozzle=(TextView)findViewById(R.id.spicing_nozzle);
        front_plate=(TextView)findViewById(R.id.front_plate);
        yarn_holding_lever=(TextView)findViewById(R.id.yarn_holding_lever);
        ln_lever=(TextView)findViewById(R.id.ln_lever_position);
        p1=(TextView)findViewById(R.id.p1);
        p2=(TextView)findViewById(R.id.p2);
        p6=(TextView) findViewById(R.id.p6);
        s10=(TextView)findViewById(R.id.s10);

        composition= (TextView)findViewById(R.id.composition1);

        Intent intent = getIntent();
        coun=intent.getStringExtra("coun");
        bend=intent.getStringExtra("bend");
        new Machconer.GetDataTaskgv20().execute();

    }
    class GetDataTaskgv20 extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */


            dialog = new ProgressDialog(Machconer.this);

            dialog.setTitle("Please Wait...");
            dialog.setMessage("Loading the information");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            /**
             * Getting JSON Object from Web Using okHttp
             */
            JSONObject jsonObject = JSONPArsermachconer.getDataFromWeb();
            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    if(jsonObject.length() > 0) {
                        /**
                         * Getting Array named "contacts" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray("Sheet1");

                        /**
                         * Check Length of Array...
                         */


                        int lenArray = array.length();

                        if(lenArray > 0) {
                            int jIndex=0;
                            Log.i("Tagging","Entered");
                            for( ; jIndex < lenArray; jIndex++) {

                                /**
                                 * Creating Every time New Object
                                 * and
                                 * Adding into List
                                 */


                                /**
                                 * Getting Inner Object from contacts array...
                                 * and
                                 * From that We will get Name of that Contact
                                 *
                                 */
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String count = innerObject.getString("Count");
                                String blend= innerObject.getString("Blend");
                                String TPI=innerObject.getString("TPI");
                                String S_D=innerObject.getString("Single/Double");
                                String NEPS=innerObject.getString("Neps");
                                String SHORT1=innerObject.getString("Short");
                                String LONG1=innerObject.getString("Long");
                                String THIN=innerObject.getString("Thin");
                                String CP=innerObject.getString("Cp+");
                                String CM=innerObject.getString("Cm");
                                String CCP= innerObject.getString("CCp+");
                                String CCM=innerObject.getString("CCm");
                                String JP=innerObject.getString("JP");
                                String PC_SENSITIVITY=innerObject.getString("PC_Sensitivity");

                                String PC_FAULT=innerObject.getString("PC_fault_distance");
                                String PC_Length=innerObject.getString("PC_Length");
                                String PC_NO_OF_FAULTS=innerObject.getString("PC_No_of_Faults");
                                String Upper_YARN=innerObject.getString("Upper_yarn");
                                String H1_SNL=innerObject.getString("H1-SNL");
                                String H2_SNL=innerObject.getString("H2-SNL");
                                String H3_SNL=innerObject.getString("H3-SNL");
                                String H4_SNL=innerObject.getString("H4-SNL");
                                String H5_SNL= innerObject.getString("H5-SNL");
                                String H1_THIN=innerObject.getString("H1-Thin");
                                String H2_THIN=innerObject.getString("H2-Thin");
                                String H3_THIN=innerObject.getString("H3-Thin");

                                String SPEED=innerObject.getString("Speed");
                                String YARN_STRENGTH=innerObject.getString("Yarn_strength");
                                String SPLICING_NOZZLE=innerObject.getString("Splicing_Nozzle");
                                String FRONT_PLATE=innerObject.getString("Front_Plate");
                                String YARN_HOLDING_LEVER=innerObject.getString("Yarn_Holding_Lever");
                                String LN_LEVER=innerObject.getString("Ln_Lever_Position");
                                String P1=innerObject.getString("P1");
                                String P2=innerObject.getString("P2");
                                String P6= innerObject.getString("P6");
                                String S10=innerObject.getString("S10_Screw_Position");
                                String COMPOSITION=innerObject.getString("Composition");


                                Log.i("TAG",count);
                                Log.i("TAG",blend);
                                Log.i("TAG",TPI);
                                Log.i("TAG",S_D);


                                Log.i("TAG",COMPOSITION);
                                if(count.equals(coun) && blend.equals(bend))
                                {   composition.setText(COMPOSITION);
                                    tpi.setText(TPI);
                                    s_d.setText(S_D);
                                    neps.setText(NEPS);
                                    short1.setText(SHORT1);
                                    long1.setText(LONG1);
                                    thin.setText(THIN);
                                    cp.setText(CP);
                                    cm.setText(CM);
                                    ccp.setText(CCP);
                                    ccm.setText(CCM);
                                    jp.setText(JP);
                                    pc_sensitivity.setText(PC_SENSITIVITY);
                                    pc_fault.setText(PC_FAULT);
                                    pc_length.setText(PC_Length);
                                    pc_no_of_faults.setText(PC_NO_OF_FAULTS);
                                    upper_yarn.setText(Upper_YARN);
                                    h1_snl.setText(H1_SNL);
                                    h2_snl.setText(H2_SNL);
                                    h3_snl.setText(H3_SNL);
                                    h4_snl.setText(H4_SNL);
                                    h5_snl.setText(H5_SNL);
                                    h1_thin.setText(H1_THIN);
                                    h2_thin.setText(H2_THIN);
                                    h3_thin.setText(H3_THIN);
                                    speed.setText(SPEED);
                                    yarn_strength.setText(YARN_STRENGTH);
                                    splicing_nozzle.setText(SPLICING_NOZZLE);
                                    front_plate.setText(FRONT_PLATE);
                                    yarn_holding_lever.setText(YARN_HOLDING_LEVER);
                                    ln_lever.setText(LN_LEVER);
                                    p1.setText(P1);
                                    p2.setText(P2);
                                    p6.setText(P6);
                                    s10.setText(S10);
                                    break;
                                }




                                /**
                                 * Getting Object from Object "phone"
                                 */
                                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                                //String phone = phoneObject.getString(Keys.KEY_MOBILE);

//                            model.setName(name);
//                            model.setCountry(country);

                                /**
                                 * Adding name and phone concatenation in List...
                                 */
//                            list.add(model);
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(JSONPArsermachconer.TAG, "" + je.getLocalizedMessage());
                Log.i("TAG","Exception caught");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            /**
             * Checking if List size if more than zero then
             * Update ListView
             */

        }
    }
}
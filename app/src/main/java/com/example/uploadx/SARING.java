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

public class SARING extends AppCompatActivity {
    public TextView tpi;
    public TextView s_d;
    public TextView speed;
    public TextView feed_wrapping;
    public TextView break_draft;
    public TextView cal_draft;
    public TextView tpm;
    public TextView ratch;
    public TextView spacer;
    public TextView ring_traveller;
    public TextView top_arm_pressure;
    public TextView composition;

    public String coun;
    public String bend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_a_r_i_n_g);
        tpi=(TextView) findViewById(R.id.tpi);
        s_d =(TextView)findViewById(R.id.s_d);
        speed=(TextView)findViewById(R.id.speed);
        feed_wrapping=(TextView)findViewById(R.id.feed_wrapping);
        break_draft=(TextView)findViewById(R.id.break_draft);
        cal_draft=(TextView)findViewById(R.id.cal_draft);
        tpm=(TextView)findViewById(R.id.tpm);
        ratch=(TextView)findViewById(R.id.ratch);
        spacer=(TextView)findViewById(R.id.spacer);
        ring_traveller=(TextView) findViewById(R.id.ring_traveller);
        top_arm_pressure=(TextView)findViewById(R.id.top_arm_pressure);
        composition= (TextView)findViewById(R.id.composition1);
        Intent intent = getIntent();
        coun=intent.getStringExtra("coun");
        bend=intent.getStringExtra("bend");
        new SARING.GetDataTaskgv20().execute();
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


            dialog = new ProgressDialog(SARING.this);

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
            JSONObject jsonObject = JSONPArsersa.getDataFromWeb();
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
                                String SPEED=innerObject.getString("Spl_Speed");
                                String FEED_WRAPPING=innerObject.getString("Feed_Wrapping");
                                String BREAK_DRAFT=innerObject.getString("Break_draft");
                                String CAL_DRAFT=innerObject.getString("Cal_Draft");
                                String TPM=innerObject.getString("TPI1");
                                String RATCH=innerObject.getString("Ratch");
                                String SPACER= innerObject.getString("Spacer");
                                String RING_TRAVELLER =innerObject.getString("RT");
                                String TOP_ARM_PRESSURE=innerObject.getString("Top_arm_pressure");
                                String COMPOSITION=innerObject.getString("Composition");
                                Log.i("TAG",count);
                                Log.i("TAG",blend);
                                Log.i("TAG",TPI);
                                Log.i("TAG",S_D);
                                Log.i("TAG",SPEED);
                                Log.i("TAG",FEED_WRAPPING);
                                Log.i("TAG",BREAK_DRAFT);
                                Log.i("TAG",CAL_DRAFT);
                                Log.i("TAG",TPM);
                                Log.i("TAG",RATCH);
                                Log.i("TAG",SPACER);
                                Log.i("TAG",RING_TRAVELLER);
                                Log.i("TAG",TOP_ARM_PRESSURE);

                                Log.i("TAG",COMPOSITION);
                                if(count.equals(coun) && blend.equals(bend))
                                {   composition.setText(COMPOSITION);
                                    tpi.setText(TPI);
                                    s_d.setText(S_D);
                                    speed.setText(SPEED);
                                    feed_wrapping.setText(FEED_WRAPPING);
                                    break_draft.setText(BREAK_DRAFT);
                                    cal_draft.setText(CAL_DRAFT);
                                    tpm.setText(TPM);
                                    ratch.setText(RATCH);
                                    spacer.setText(SPACER);
                                    ring_traveller.setText(RING_TRAVELLER);
                                    top_arm_pressure.setText(TOP_ARM_PRESSURE);


                                    //composition.setText(COMPOSITION);
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
                Log.i(JSONPArsersa.TAG, "" + je.getLocalizedMessage());
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
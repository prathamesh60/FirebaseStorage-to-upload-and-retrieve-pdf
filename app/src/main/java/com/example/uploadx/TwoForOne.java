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

public class TwoForOne extends AppCompatActivity {
    public TextView tpi;
    public TextView s_d;
    public TextView tpin;
    public TextView tpm;
    public TextView spindle_speed;
    public TextView tensor_spring;
    public TextView tensor_dial;
    public TextView winding_tension;
    public TextView lease_angle;
    public TextView over_feed;
    public TextView reserve_angle;
    public TextView composition;

    public String coun;
    public String bend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_for_one);
        tpi=(TextView) findViewById(R.id.tpi);
        s_d =(TextView)findViewById(R.id.s_d);
        tpin=(TextView)findViewById(R.id.tpin);
        tpm=(TextView)findViewById(R.id.tpm);
        spindle_speed=(TextView)findViewById(R.id.spindle_speed);
        tensor_spring=(TextView)findViewById(R.id.tensor_spring);
        tensor_dial=(TextView)findViewById(R.id.tensor_dial);
        winding_tension=(TextView)findViewById(R.id.winding_tension);
        lease_angle=(TextView)findViewById(R.id.lease_angle);
        over_feed=(TextView) findViewById(R.id.over_feed);
        reserve_angle=(TextView)findViewById(R.id.reserve_angle);
        composition= (TextView)findViewById(R.id.composition1);
        Intent intent = getIntent();
        coun=intent.getStringExtra("coun");
        bend=intent.getStringExtra("bend");
        new TwoForOne.GetDataTaskgv20().execute();
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


            dialog = new ProgressDialog(TwoForOne.this);

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
            JSONObject jsonObject = JSONPArsertwo.getDataFromWeb();
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
                                String TPIN=innerObject.getString("TPI-Nominal");
                                String TPM=innerObject.getString("TPM-Actual");
                                String SPINDLE_SPEED=innerObject.getString("Spindle_Speed(RPM)");
                                String TENSOR_SPRING=innerObject.getString("Tensor_Spring");
                                String TENSOR_DIAL=innerObject.getString("Tensor_Dial");
                                String WINDING_TENSION=innerObject.getString("Winding_Tension");
                                String LEASE_ANGLE= innerObject.getString("Lease_Angle");
                                String OVER_FEED =innerObject.getString("Over_Feed%");
                                String RESERVE_ANGLE=innerObject.getString("Reserve_angle");
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
                                    tpin.setText(TPIN);
                                    tpm.setText(TPM);
                                    spindle_speed.setText(SPINDLE_SPEED);
                                    tensor_spring.setText(TENSOR_SPRING);
                                    tensor_dial.setText(TENSOR_DIAL);
                                    winding_tension.setText(WINDING_TENSION);
                                    lease_angle.setText(LEASE_ANGLE);
                                    over_feed.setText(OVER_FEED);
                                    reserve_angle.setText(RESERVE_ANGLE);



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
                Log.i(JSONPArsertwo.TAG, "" + je.getLocalizedMessage());
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
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

public class Ssm extends AppCompatActivity {
    public TextView tpi;
    public TextView s_d;
    public TextView speed;
    public TextView dg_ramp_speed;
    public TextView initial_speed;
    public TextView req_yarn_length;
    public TextView empty_tube_relief;
    public TextView package_relief;
    public TextView yarn_tension_factor;
    public TextView yarn_tension_package;
    public TextView yarn_tension_doffing;
    public TextView winding_angle2;
    public TextView winding_angle3;
    public TextView composition;

    public String coun;
    public String bend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssm);
        tpi=(TextView) findViewById(R.id.tpi);
        s_d =(TextView)findViewById(R.id.s_d);
        speed=(TextView)findViewById(R.id.speed);
        dg_ramp_speed=(TextView)findViewById(R.id.dg_ramp_speed);
        initial_speed=(TextView)findViewById(R.id.initial_speed);
        req_yarn_length=(TextView)findViewById(R.id.req_yarn_length);
        empty_tube_relief=(TextView)findViewById(R.id.empty_tube_relief);
        package_relief=(TextView)findViewById(R.id.package_relief);
        yarn_tension_factor=(TextView)findViewById(R.id.yarn_tension_factor);
        yarn_tension_package=(TextView) findViewById(R.id.yarn_tension_package);
        yarn_tension_doffing=(TextView)findViewById(R.id.yarn_tension_doffing);
        winding_angle2=(TextView)findViewById(R.id.winding_angle2);
        winding_angle3= (TextView)findViewById(R.id.winding_angle3);
        composition=(TextView)findViewById(R.id.composition1);
        Intent intent = getIntent();
        coun=intent.getStringExtra("coun");
        bend=intent.getStringExtra("bend");
        new Ssm.GetDataTaskgv20().execute();
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


            dialog = new ProgressDialog(Ssm.this);
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
            JSONObject jsonObject = JSONPArserssm.getDataFromWeb();
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
                                String SPEED=innerObject.getString("Speed_m/min");
                                String DG_RAMP_SPEED=innerObject.getString("DG_Ramp_speed_m/min");
                                String INITIAL_SPEED=innerObject.getString("Initial_speed_m/min");
                                String REQ_YARN_LENGTH=innerObject.getString("Req_Yarn_length_meter_3");
                                String EMPTY_TUBE_RELEIF=innerObject.getString("Empty_tube_relief_%_3");
                                String PACKAGE_RELEIF=innerObject.getString("Package_relief_%_4");
                                String YARN_TENSION_FACTOR=innerObject.getString("Yarn_Tension_Factor_%_6");
                                String YARN_FACTOR_PACKAGE=innerObject.getString("Yarn_Tension_Facor_Package_%_7");
                                String YARN_TENSION_DOFFING=innerObject.getString("Yarn_Tension_doffing_%_9");
                                String WINDING_ANGLE2=innerObject.getString("Winding_angle_°_2");
                                String WINDING_ANGLE3=innerObject.getString("Winding_angle_°_3");
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
                                    speed.setText(SPEED);
                                    dg_ramp_speed.setText(DG_RAMP_SPEED);
                                    initial_speed.setText(INITIAL_SPEED);
                                    req_yarn_length.setText(REQ_YARN_LENGTH);
                                    empty_tube_relief.setText(EMPTY_TUBE_RELEIF);
                                    package_relief.setText(PACKAGE_RELEIF);
                                    yarn_tension_factor.setText(YARN_TENSION_FACTOR);
                                    yarn_tension_package.setText(YARN_FACTOR_PACKAGE);
                                    yarn_tension_doffing.setText(YARN_TENSION_DOFFING);
                                    winding_angle2.setText(WINDING_ANGLE2);
                                    winding_angle3.setText(WINDING_ANGLE3);
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
                Log.i(JSONPArserssm.TAG, "" + je.getLocalizedMessage());
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
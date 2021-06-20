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

public class BM20 extends AppCompatActivity {
    public TextView tpi;
    public TextView s_d;
    public TextView feed;
    public TextView del;
    public TextView draft;
    public TextView tpm;
    public TextView flyer_speed;
    public TextView delivery_speed;
    public TextView bobbin_angle;
    public TextView pitch;
    public TextView thickness;
    public TextView first_layer_tension;
    public TextView ratch;
    public TextView front_condenser;
    public TextView composition;

    public String coun;
    public String bend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m20);
        tpi=(TextView) findViewById(R.id.tpi);
        s_d =(TextView)findViewById(R.id.s_d);
        feed=(TextView)findViewById(R.id.feed);
        del=(TextView)findViewById(R.id.del);
        draft=(TextView)findViewById(R.id.draft);
        tpm=(TextView)findViewById(R.id.tpm);
        flyer_speed=(TextView)findViewById(R.id.flyer_speed);
        delivery_speed=(TextView)findViewById(R.id.delivery_speed);
        bobbin_angle=(TextView)findViewById(R.id.bobbin_angle);
        pitch=(TextView)findViewById(R.id.pitch);
        thickness=(TextView) findViewById(R.id.thickness);
        first_layer_tension=(TextView)findViewById(R.id.first_layer_tension);
        ratch=(TextView)findViewById(R.id.ratch);
        front_condenser= (TextView)findViewById(R.id.front_condenser);
        composition=(TextView)findViewById(R.id.composition1);
        Intent intent = getIntent();
        coun=intent.getStringExtra("coun");
        bend=intent.getStringExtra("bend");
        new BM20.GetDataTaskgv20().execute();

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


            dialog = new ProgressDialog(BM20.this);
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
            JSONObject jsonObject = JSONPArserbm20.getDataFromWeb();
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
                                String FEED=innerObject.getString("FEED(G/M)");
                                String DEL=innerObject.getString("DEL(G/M)");
                                String DRAFT=innerObject.getString("DRAFT");
                                String TPM=innerObject.getString("TPM");
                                String FLYER_SPEED=innerObject.getString("FLYER_SPEED");
                                String DELIVERY_SPEED=innerObject.getString("Delivery_Speed");
                                String BOBBIN_ANGLE=innerObject.getString("BOBBIN_ANGLE");
                                String PITCH=innerObject.getString("PITCH(MM)");
                                String THICKNESS=innerObject.getString("THICKNESS(MM)");
                                String FIRST_LAYER_TENSION=innerObject.getString("FIRST_LAYER_TENTION%");
                                String RATCH=innerObject.getString("RATCH(MM)");
                                String FRONT_CONDENSOR=innerObject.getString("FRONT_CONDENSOR(MM)");
                                String COMPOSITION=innerObject.getString("Composition");
                                Log.i("TAG",count);
                                Log.i("TAG",blend);
                                Log.i("TAG",TPI);
                                Log.i("TAG",S_D);
                                Log.i("TAG",FEED);
                                Log.i("TAG",DEL);
                                Log.i("TAG",DRAFT);
                                Log.i("TAG",TPM);
                                Log.i("TAG",FLYER_SPEED);
                                Log.i("TAG",DELIVERY_SPEED);
                                Log.i("TAG",BOBBIN_ANGLE);
                                Log.i("TAG",PITCH);
                                Log.i("TAG",THICKNESS);
                                Log.i("TAG",FIRST_LAYER_TENSION);
                                Log.i("TAG",RATCH);
                                Log.i("TAG",FRONT_CONDENSOR);
                                Log.i("TAG",COMPOSITION);
                                if(count.equals(coun) && blend.equals(bend))
                                {   composition.setText(COMPOSITION);
                                    tpi.setText(TPI);
                                    s_d.setText(S_D);
                                    feed.setText(FEED);
                                    del.setText(DEL);
                                    draft.setText(DRAFT);
                                    tpm.setText(TPM);
                                    flyer_speed.setText(FLYER_SPEED);
                                    delivery_speed.setText(DELIVERY_SPEED);
                                    bobbin_angle.setText(BOBBIN_ANGLE);
                                    pitch.setText(PITCH);
                                    thickness.setText(THICKNESS);
                                    first_layer_tension.setText(FIRST_LAYER_TENSION);
                                    ratch.setText(RATCH);
                                    front_condenser.setText(FRONT_CONDENSOR);
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
                Log.i(JSONPArserbm20.TAG, "" + je.getLocalizedMessage());
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
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

public class FM7 extends AppCompatActivity {
    public TextView tpi;
    public TextView s_d;
    public TextView feed;
    public TextView delivery;
    public TextView draft;
    public TextView feed_tension;
    public TextView middle_tension;
    public TextView delivery_tension;
    public TextView nip;
    public TextView rubs;
    public TextView delivery_speed;
    public TextView condenser;
    public TextView composition;
    public String coun;
    public String bend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_m7);
        tpi=(TextView) findViewById(R.id.tpi);
        s_d =(TextView)findViewById(R.id.s_d);
        feed=(TextView)findViewById(R.id.feed);
        delivery=(TextView)findViewById(R.id.delivery);
        draft=(TextView)findViewById(R.id.draft);
        feed_tension=(TextView)findViewById(R.id.feed_tension);
        middle_tension=(TextView)findViewById(R.id.middle_tension);
        delivery_tension=(TextView)findViewById(R.id.delivery_tension);
        nip=(TextView)findViewById(R.id.nip);
        rubs=(TextView) findViewById(R.id.rubs);
        delivery_speed=(TextView)findViewById(R.id.delivery_speed);
        condenser=(TextView)findViewById(R.id.condenser);
        composition= (TextView)findViewById(R.id.composition1);
        Intent intent = getIntent();
        coun=intent.getStringExtra("coun");
        bend=intent.getStringExtra("bend");
        new FM7.GetDataTaskgv20().execute();
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


            dialog = new ProgressDialog(FM7.this);
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
            JSONObject jsonObject = JSONPArserfm7.getDataFromWeb();
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
                                String FEED=innerObject.getString("Feed");
                                String DELIVERY=innerObject.getString("Delivery");
                                String DRAFT=innerObject.getString("Draft");
                                String FEED_TENSION=innerObject.getString("Feed_tension");
                                String MIDDLE_TENSION=innerObject.getString("Middle_tension");
                                String DELIVERY_TENSION=innerObject.getString("Delivery_Tension(Belt_position)");
                                String NIP=innerObject.getString("Nip");
                                String RUBS=innerObject.getString("Rubs");
                                String DELIVERY_SPEED=innerObject.getString("Delivery_Speed");
                                String CONDENSER=innerObject.getString("Condenser");
                                String COMPOSITION=innerObject.getString("Composition");
                                Log.i("TAG",count);
                                Log.i("TAG",blend);
                                Log.i("TAG",TPI);
                                Log.i("TAG",S_D);
                                Log.i("TAG",FEED);
                                Log.i("TAG",DELIVERY);
                                Log.i("TAG",DRAFT);
                                Log.i("TAG",FEED_TENSION);
                                Log.i("TAG",MIDDLE_TENSION);
                                Log.i("TAG",DELIVERY_TENSION);
                                Log.i("TAG",NIP);
                                Log.i("TAG",RUBS);
                                Log.i("TAG",DELIVERY_SPEED);
                                Log.i("TAG",CONDENSER);
                                Log.i("TAG",COMPOSITION);
                                if(count.equals(coun) && blend.equals(bend))
                                {   composition.setText(COMPOSITION);
                                    tpi.setText(TPI);
                                    s_d.setText(S_D);
                                    feed.setText(FEED);
                                    delivery.setText(DELIVERY);
                                    draft.setText(DRAFT);
                                    feed_tension.setText(FEED_TENSION);
                                    middle_tension.setText(MIDDLE_TENSION);
                                    delivery_tension.setText(DELIVERY_TENSION);
                                    nip.setText(NIP);
                                    rubs.setText(RUBS);
                                    delivery_speed.setText(DELIVERY_SPEED);
                                    condenser.setText(CONDENSER);
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
                Log.i(JSONPArserfm7.TAG, "" + je.getLocalizedMessage());
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
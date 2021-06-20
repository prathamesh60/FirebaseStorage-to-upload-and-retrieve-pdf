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

public class GillGC1 extends AppCompatActivity {
    public TextView tpi;
    public TextView s_d;
    public TextView feed_wrpg;
    public TextView no_of_doublings;
    public TextView total_feed;
    public TextView del_wraping;
    public TextView cal_draft;
    public TextView del_speed_old;
    public TextView del_speed_new;
    public TextView ratch;
    public TextView faller_pins;
    public TextView fr_pressure;
    public TextView composition;

    public String coun;
    public String bend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gill_g_c1);
        tpi=(TextView) findViewById(R.id.tpi);
        s_d =(TextView)findViewById(R.id.s_d);
        feed_wrpg=(TextView)findViewById(R.id.feed_wrpg);
        no_of_doublings=(TextView)findViewById(R.id.no_of_doublings);
        total_feed=(TextView)findViewById(R.id.total_feed);
        del_wraping=(TextView)findViewById(R.id.del_wrapping);
        cal_draft=(TextView)findViewById(R.id.cal_draft);
        del_speed_old=(TextView)findViewById(R.id.del_speed_old);
        del_speed_new=(TextView)findViewById(R.id.del_speed_new);
        ratch=(TextView) findViewById(R.id.ratch);
        faller_pins=(TextView)findViewById(R.id.faller_pins);
        fr_pressure=(TextView)findViewById(R.id.fr_pressure);
        composition= (TextView)findViewById(R.id.composition1);
        Intent intent = getIntent();
        coun=intent.getStringExtra("coun");
        bend=intent.getStringExtra("bend");
        new GillGC1.GetDataTaskgv20().execute();
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


            dialog = new ProgressDialog(GillGC1.this);
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
            JSONObject jsonObject = JSONPArsergillgc1.getDataFromWeb();
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
                                String FEED_WRPG=innerObject.getString("Feed_Wrpg_in_Grms");
                                String NO_OF_DOUBLINGS=innerObject.getString("No_of_Doublings");
                                String TOTAL_FEED=innerObject.getString("Total_Feed");
                                String DEL_WRAPPING=innerObject.getString("Del_Wrapping");
                                String CAL_DRAFT=innerObject.getString("Cal_Draft");
                                String DEL_SPEED_OLD=innerObject.getString("Delivery_Speed(Old_Spg)");
                                String DEL_SPEED_NEW=innerObject.getString("Delivery_Speed(New_Spg)");
                                String RATCH=innerObject.getString("Ratch");
                                String FALLER_PINS=innerObject.getString("Fallers_Pins(flat)");
                                String FR_PRESSURE=innerObject.getString("FR_Pressure");
                                String COMPOSITION=innerObject.getString("Composition");
                                Log.i("TAG",count);
                                Log.i("TAG",blend);
                                Log.i("TAG",TPI);
                                Log.i("TAG",S_D);
                                Log.i("TAG",FEED_WRPG);
                                Log.i("TAG",NO_OF_DOUBLINGS);
                                Log.i("TAG",TOTAL_FEED);
                                Log.i("TAG",DEL_WRAPPING);
                                Log.i("TAG",CAL_DRAFT);
                                Log.i("TAG",DEL_SPEED_OLD);
                                Log.i("TAG",DEL_SPEED_NEW);
                                Log.i("TAG",RATCH);
                                Log.i("TAG",FALLER_PINS);
                                Log.i("TAG",FR_PRESSURE);
                                Log.i("TAG",COMPOSITION);
                                if(count.equals(coun) && blend.equals(bend))
                                {   composition.setText(COMPOSITION);
                                    tpi.setText(TPI);
                                    s_d.setText(S_D);
                                    feed_wrpg.setText(FEED_WRPG);
                                    no_of_doublings.setText(NO_OF_DOUBLINGS);
                                    total_feed.setText(TOTAL_FEED);
                                    del_wraping.setText(DEL_WRAPPING);
                                    cal_draft.setText(CAL_DRAFT);
                                    del_speed_old.setText(DEL_SPEED_OLD);
                                    del_speed_new.setText(DEL_SPEED_NEW);
                                    ratch.setText(RATCH);
                                    faller_pins.setText(FALLER_PINS);
                                    fr_pressure.setText(FR_PRESSURE);
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
                Log.i(JSONPArsergillgc1.TAG, "" + je.getLocalizedMessage());
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
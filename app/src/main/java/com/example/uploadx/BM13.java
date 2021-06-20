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

public class BM13 extends AppCompatActivity {
    public TextView tpi;
    public TextView s_d;
    public TextView feed;
    public TextView delivery;
    public TextView draft;
    public TextView speed;
    public TextView ratch;
    public TextView tpm;
    public TextView pitch;
    public TextView layer;
    public TextView taper;
    public TextView back_tension;
    public TextView composition;

    public String coun;
    public String bend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m13);
        tpi=(TextView) findViewById(R.id.tpi);
        s_d =(TextView)findViewById(R.id.s_d);
        feed=(TextView)findViewById(R.id.feed);
        delivery=(TextView)findViewById(R.id.delivery);
        draft=(TextView)findViewById(R.id.draft);
        speed=(TextView)findViewById(R.id.speed);
        ratch=(TextView)findViewById(R.id.ratch);
        tpm=(TextView)findViewById(R.id.tpm);
        pitch=(TextView)findViewById(R.id.pitch);
        layer=(TextView) findViewById(R.id.layer);
        taper=(TextView)findViewById(R.id.taper);
        back_tension=(TextView)findViewById(R.id.back_tension);
        composition= (TextView)findViewById(R.id.composition1);
        Intent intent = getIntent();
        coun=intent.getStringExtra("coun");
        bend=intent.getStringExtra("bend");
        new BM13.GetDataTaskgv20().execute();

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


            dialog = new ProgressDialog(BM13.this);
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
            JSONObject jsonObject = JSONPArserbm13.getDataFromWeb();
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
                                String FEED=innerObject.getString("FEED(GM/M)");
                                String DELIVERY=innerObject.getString("DELIVERY(GM/M)");
                                String DRAFT=innerObject.getString("DRAFT");
                                String SPEED=innerObject.getString("SPEED(M/MIN)");
                                String RATCH=innerObject.getString("RATCH(MM)");
                                String TPM=innerObject.getString("TPM");
                                String PITCH=innerObject.getString("PITCH(MM)");
                                String LAYER=innerObject.getString("LAYER_THKNS(MM)");
                                String TAPER=innerObject.getString("TAPER<DGRE");
                                String BACK_TENSION=innerObject.getString("BACK_TENSION%");
                                String COMPOSITION=innerObject.getString("Composition");
                                Log.i("TAG",count);
                                Log.i("TAG",blend);
                                Log.i("TAG",TPI);
                                Log.i("TAG",S_D);
                                Log.i("TAG",FEED);
                                Log.i("TAG",DELIVERY);
                                Log.i("TAG",DRAFT);
                                Log.i("TAG",SPEED);
                                Log.i("TAG",RATCH);
                                Log.i("TAG",TPM);
                                Log.i("TAG",PITCH);
                                Log.i("TAG",LAYER);
                                Log.i("TAG",TAPER);
                                Log.i("TAG",BACK_TENSION);
                                Log.i("TAG",COMPOSITION);
                                if(count.equals(coun) && blend.equals(bend))
                                {   composition.setText(COMPOSITION);
                                    tpi.setText(TPI);
                                    s_d.setText(S_D);
                                    feed.setText(FEED);
                                    delivery.setText(DELIVERY);
                                    draft.setText(DRAFT);
                                    speed.setText(SPEED);
                                    ratch.setText(RATCH);
                                    tpm.setText(TPM);
                                    pitch.setText(PITCH);
                                    layer.setText(LAYER);
                                    taper.setText(TAPER);
                                    back_tension.setText(BACK_TENSION);
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
                Log.i(JSONPArserbm13.TAG, "" + je.getLocalizedMessage());
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
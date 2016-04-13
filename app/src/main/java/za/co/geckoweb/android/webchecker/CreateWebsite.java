package za.co.geckoweb.android.webchecker;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateWebsite extends Activity {

    DatabaseHelper myDb;
    EditText websiteUrl;
    Button websiteAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_website);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        websiteUrl = (EditText) findViewById(R.id.website_url);
        websiteAddBtn = (Button) findViewById(R.id.website_add_btn);

        myDb = new DatabaseHelper(this);
        addWebsite();
    }

    public void addWebsite()
    {
        websiteAddBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertWebSiteData(websiteUrl.getText().toString());

                        if(isInserted){
                            Toast.makeText(CreateWebsite.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CreateWebsite.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                        }

                        finish();
                    }
                }
        );
    }
}

package za.co.geckoweb.android.webchecker;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button createWebsiteBtn;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Create Btn
        createWebsiteBtn = (Button) findViewById(R.id.createWebSiteBtn);
        createWebsiteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateWebsite.class));
            }
        });

        listView = (ListView) findViewById(R.id.listView);

        myDb = new DatabaseHelper(this);

        updateListView();
    }

    public void updateListView()
    {
        Cursor result = myDb.getAllData();

        if (result.getCount() == 0) {
            // show message
            showMessage("Error", "Nothing found");
            return;
        }

        final ArrayList<String[]> list = new ArrayList<String[]>();

        while (result.moveToNext())
        {
            list.add(new String[]{result.getString(1).toString()});
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
            int position, long id) {

                showMessage("Item Id: " + position,"Item:" + id);
//                final String item = (String) parent.getItemAtPosition(position);
//
//                view.animate().setDuration(2000).alpha(0)
//                .withEndAction(new Runnable() {
//                    @Override
//                    public void run() {
//                        list.remove(item);
//                        adapter.notifyDataSetChanged();
//                        view.setAlpha(1);
//                    }
//                });
            }

        });

        //Show all data
        //showMessage("Data", buffer.toString());
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }
}
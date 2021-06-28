package nithra.resume.maker.cv.builder.app.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;




import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import nithra.resume.maker.cv.builder.app.Profile_Adapter;
import nithra.resume.maker.cv.builder.app.R;

public class Profiles_View_Activity extends AppCompatActivity {
    Profile_Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    SQLiteDatabase myDB;
    ArrayList<String> names = new ArrayList<>();
    RecyclerView profile_list;
    Toolbar toolbar;

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        setContentView(R.layout.activity_profiles__view);
        this.profile_list = (RecyclerView) findViewById(R.id.profile_list);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Profiles");
        this.myDB = openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.profile_list.setHasFixedSize(true);
        this.mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.profile_list.setLayoutManager(this.mLayoutManager);
        this.names.clear();
        try {
            Cursor rawQuery = this.myDB.rawQuery("SELECT name FROM profile_table", null);
            if (rawQuery.moveToFirst()) {
                for (int i = 0; i < rawQuery.getCount(); i++) {
                    this.names.add(rawQuery.getString(rawQuery.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME)));
                    rawQuery.moveToNext();
                }
                rawQuery.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.profile_list.setAdapter(this.mAdapter);
        this.mAdapter.notifyDataSetChanged();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}

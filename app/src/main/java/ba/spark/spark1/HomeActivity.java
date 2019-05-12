package ba.spark.spark1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

import static ba.spark.spark1.MainActivity.MY_Preferences_Filename;

public class HomeActivity extends AppCompatActivity {

    private ListView listView;
    private GridView gridView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter postRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //LOGOUT
    String name;
    int v;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                SharedPreferences preferences = getSharedPreferences(MY_Preferences_Filename, 0);
                preferences.edit().remove("username").apply();
                preferences.edit().remove("password").apply();
                preferences.edit().remove("shouldRemember").apply();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.logout:
                SharedPreferences preferences1 = getSharedPreferences(MY_Preferences_Filename, 0);
                preferences1.edit().remove("username").apply();
                preferences1.edit().remove("password").apply();
                preferences1.edit().remove("shouldRemember").apply();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void Snimi(String name, int v) {
        SharedPreferences preferences = getSharedPreferences(MY_Preferences_Filename, 0);
        preferences.edit().putInt(name, v).commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        //**KOMENTIRATI SLJEDECIH 7 LINIJA KODA NAKON PRVOG LOGIRANJA I PONOVO POKRENUTI APP**(samo dodavanje testnih podataka)
        List<Post> podaci = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            podaci.add(new Post("hljubic", "10 minutes ago", R.drawable.image_one, false));
            podaci.add(new Post("tkobacic", "25 minutes ago", R.drawable.image_two, false));
            podaci.add(new Post("iknezovic", "45 minutes ago", R.drawable.image_three, false));
        }
        Post.saveInTx(podaci);

        //****************************************************************************************


        final List<Post> podaci1 = Post.listAll(Post.class);

        recyclerView = (RecyclerView) findViewById(R.id.rv_lista);
        postRecyclerAdapter = new PostRecyclerAdapter(this, podaci1);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(postRecyclerAdapter);
        postRecyclerAdapter.notifyDataSetChanged();

    }
}

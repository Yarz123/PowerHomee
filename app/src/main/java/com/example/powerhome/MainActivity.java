package com.example.powerhome;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerDL;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.habitatListView);

        List<Habitat> habitats = new ArrayList<>();
        Habitat habitat1 = new Habitat(1, "Gastan Leclair", 1);
        habitat1.addAppliance(new Appliances(1, "Aspirateur", "REF123", 200, R.drawable.ic_aspirateur));
        habitat1.addAppliance(new Appliances(2, "Fer à Repasser", "FR456", 150, R.drawable.ic_fer_a_repasser));
        habitats.add(habitat1);
        Log.d("DEBUG_HABITAT", "Avant le log de habitat1");
        if (habitat1 != null) {
            Log.d("Debug Habitat", "habitat1 is not null");
            if (habitat1.getAppliances() != null) {
                Log.d("Debug Habitat", "appliances are not null");
            } else {
                Log.d("Debug Habitat", "appliances are null");
            }
            Log.d("Debug Habitat", habitat1.toString());
        } else {
            Log.d("Debug Habitat", "habitat1 is null");
        }

        Habitat habitat2 = new Habitat(2, "Cédric Boudet", 1);
        habitat2.addAppliance(new Appliances(3, "Machine à laver", "WM789", 500, R.drawable.ic_machine_a_laver));
        habitats.add(habitat2);

        HabitatAdapter adapter = new HabitatAdapter(this, R.layout.item_habitat, habitats);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Habitat habitat = habitats.get(position);
                Toast.makeText(MainActivity.this, habitat.getResidentName(), Toast.LENGTH_SHORT).show();
            }
        });

        drawerDL = findViewById(R.id.drawer);
        NavigationView navNV = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(this, drawerDL, R.string.open, R.string.close);
        drawerDL.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fm = getSupportFragmentManager();
        navNV.setNavigationItemSelectedListener(this);
        navNV.getMenu().performIdentifierAction(R.id.nav_first, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_first) {
            fm.beginTransaction().replace(R.id.contentFL, new MonHabitatFragment()).commit();
        } else if (id == R.id.nav_second) {
            fm.beginTransaction().replace(R.id.contentFL, new ParametresFragment()).commit();
        }
        else if (id == R.id.nav_third) {
            fm.beginTransaction().replace(R.id.contentFL, new MesNotificationsFragment()).commit();
        }
        else if (id == R.id.nav_fourth) {
            fm.beginTransaction().replace(R.id.contentFL, new MesPreferencesFragment()).commit();
        }
        drawerDL.closeDrawer(GravityCompat.START);
        return true;
    }
}

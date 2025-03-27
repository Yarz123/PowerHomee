package com.example.powerhome;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HabitatAdapter extends ArrayAdapter<Habitat> {

    Activity activity;
    List<Habitat> habitats;
    int habitatId;

    public HabitatAdapter(Activity activity, int itemResourceId, List<Habitat> items) {
        super(activity, itemResourceId, items);

        this.activity = activity;
        this.habitatId = itemResourceId;
        this.habitats = items;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(R.layout.item_habitat, parent, false);
        }

        Habitat habitat = habitats.get(position);

        TextView residentName = row.findViewById(R.id.nomResident);
        TextView equipmentCount = row.findViewById(R.id.nbEquipements);
        TextView floor = row.findViewById(R.id.etage);
        LinearLayout equipmentIconsContainer = row.findViewById(R.id.equipementsLayout);

        residentName.setText(habitat.getResidentName());

        equipmentCount.setText(habitat.getAppliances().size() + " équipements");
        floor.setText(""+habitat.getFloor());
        equipmentIconsContainer.removeAllViews();

        if (habitat.getAppliances().isEmpty()) {
            equipmentIconsContainer.setVisibility(View.GONE);
        } else {
            equipmentIconsContainer.setVisibility(View.VISIBLE);

            for (Appliances appliance : habitat.getAppliances()) {
                Log.d("HabitatAdapter", "Ajout de l'icône : " + appliance.getIconResId());

                ImageView icon = new ImageView(activity);
                icon.setImageResource(appliance.getIconResId());
                icon.setLayoutParams(new ViewGroup.LayoutParams(60, 60));
                equipmentIconsContainer.addView(icon);
            }
        }
        return row;
    }
}



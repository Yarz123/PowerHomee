package com.example.powerhome;

import androidx.annotation.NonNull;

public class Appliances {
        private int id;
        private String name;
        private String reference;
        private int wattage;
        private int iconResId;
        public Appliances( int id, String name, String reference, int watt, int iconResId){
            this.id = id;
            this.name = name;
            this.reference = reference;
            this.wattage = watt;
            this.iconResId = iconResId;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getReference() {
            return reference;
        }

        public int getWattage() {
            return wattage;
        }

        public int getIconResId() {
            return iconResId;
        }

        @Override
        public String toString() {
            return "Appliances{id=" + id + ", name='" + name + "resid=" + iconResId +"'}";
        }


}

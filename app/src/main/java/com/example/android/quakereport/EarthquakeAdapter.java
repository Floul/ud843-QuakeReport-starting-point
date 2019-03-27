package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;


import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter {

    private String currentItemPrimaryLocation;
    private String currentItemLocationOffset;

    public EarthquakeAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formattedDate (Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD MM, yyyy");
        return dateFormat.format(date);
    }

    private String formattedTime (Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(date);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        int magnitudeColor = ContextCompat.getColor(getContext(), magnitudeColorResourceId);

        return magnitudeColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentItem = (Earthquake) getItem(position);

        String currentItemMagnitude = formatMagnitude(currentItem.getMagnitude());

        if (currentItem.getLocation().contains("of")) {
            String [] currentItemPlace = currentItem.getLocation().split("(?<=of )");
            currentItemLocationOffset = currentItemPlace[0];
            currentItemPrimaryLocation = currentItemPlace[1];
        } else {
            currentItemLocationOffset = "Near by";
            currentItemPrimaryLocation = currentItem.getLocation();
        }

        Date currentItemDate = new Date (currentItem.getDate());

        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude_text_view);
        TextView primaryLocation = (TextView) listItemView.findViewById(R.id.primary_location);
        TextView locationOffset = (TextView) listItemView.findViewById(R.id.location_offset);
        TextView date = (TextView) listItemView.findViewById(R.id.date_text_view);
        TextView time = (TextView) listItemView.findViewById(R.id.time_text_view);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentItem.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        magnitude.setText(currentItemMagnitude);
        primaryLocation.setText(currentItemPrimaryLocation);
        locationOffset.setText(currentItemLocationOffset);
        date.setText(formattedDate(currentItemDate));
        time.setText(formattedTime(currentItemDate));

        return listItemView;
    }
}

package com.example.covidapp.module.Home.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

public class GraphAdapter {
    private static int mSeries1Index;

    public static void createTracks(Activity activity, int arcViewId, Interpolator interpolator, int color, final int txt_percentage, int percentage) {

        final DecoView decoView = (DecoView) activity.findViewById(arcViewId);
        if (decoView == null) {
            return;
        }

        decoView.deleteAll();
        decoView.configureAngles(360, 180);

        SeriesItem arcBackTrack = new SeriesItem.Builder(Color.argb(255, 228, 228, 228))
                .setRange(0, 100, 100)
                .setLineWidth(12)
                .build();

        decoView.addSeries(arcBackTrack);

        SeriesItem seriesItem1 = new SeriesItem.Builder(color)
                .setRange(0, 100, 100)
                .setInterpolator(interpolator)
                .setLineWidth(12)
                .setSpinDuration(5000)
                .setSpinClockwise(false)
                .build();

        mSeries1Index = decoView.addSeries(seriesItem1);
        setupEvents(activity, arcViewId, percentage, 800, txt_percentage);
    }


    private static void setupEvents(final Activity activity, final int arcId, final int percentage, final int delay, final int txt_percentage) {

        final DecoView decoView = (DecoView) activity.findViewById(arcId);
        final TextView txt_percente = (TextView) activity.findViewById(txt_percentage);

        if (decoView == null || decoView.isEmpty()) {
            throw new IllegalStateException("Unable to add events to empty View");
        }

        decoView.executeReset();
        txt_percente.setText(percentage+"%");
        decoView.addEvent(new DecoEvent.Builder(percentage).setIndex(mSeries1Index).setDelay(delay).build());

    }
}



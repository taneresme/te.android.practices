package tr.com.mskr.sunshine22;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import tr.com.mskr.sunshine22.task.DownloadImageTask;
import tr.com.mskr.sunshine22.weather.List;

public class ForecastViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private static final String DEGREE = "\u00b0";
    private static final String IMAGE_URL = "http://openweathermap.org/img/w/";

    private ListItemClickListener mListener;
    public TextView forecast;
    public TextView forecastDetail;
    public ImageView forecastImage;
    public TextView highDegree;
    public TextView lowDegree;

    public ForecastViewHolder(View itemView, ListItemClickListener listener) {
        super(itemView);
        forecast = (TextView) itemView.findViewById(R.id.tv_item_forecast);
        forecastDetail = (TextView) itemView.findViewById(R.id.tv_item_forecast_detail);
        forecastImage = (ImageView) itemView.findViewById(R.id.tv_item_image);
        highDegree = (TextView) itemView.findViewById(R.id.tv_item_high_degree_txt);
        lowDegree = (TextView) itemView.findViewById(R.id.tv_item_low_degree_txt);
        mListener = listener;
        itemView.setOnClickListener(this);
    }

    void bind(String s) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<List> jsonAdapter = moshi.adapter(List.class);
        try {
            List list = jsonAdapter.fromJson(s);

            String date = list.getReadableDateString();
            long min = Math.round(list.getTemp().getMin());
            long max = Math.round(list.getTemp().getMax());
            long day = Math.round(list.getTemp().getDay());
            String desc = list.getWeather().get(0).getDescription();
            String image = list.getWeather().get(0).getIcon();

            new DownloadImageTask(forecastImage).execute(IMAGE_URL + image + ".png");

            forecast.setText(date);
            forecastDetail.setText(desc);
            highDegree.setText(String.format("%s%s", max, DEGREE));
            lowDegree.setText(String.format("%s%s", min, DEGREE));
        } catch (IOException e) {
            Log.v("DetailActivity.onCreate", e.toString());
            forecast.setText("EXCEPTION OCCURRED");
        }
    }

    @Override
    public void onClick(View view) {

        int clickedPosition = getAdapterPosition();
        mListener.onListItemClick(clickedPosition);
    }
}
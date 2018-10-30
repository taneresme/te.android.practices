package tr.com.mskr.sunshine22;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import tr.com.mskr.sunshine22.weather.List;
import tr.com.mskr.sunshine22.weather.WeatherApiResult;

public class ForecastViewHolder extends RecyclerView.ViewHolder
implements View.OnClickListener{

    private  ListItemClickListener mListener;
    public   TextView              listItemForecastView;

    public ForecastViewHolder(View itemView, ListItemClickListener listener) {
        super(itemView);
        listItemForecastView = (TextView) itemView.findViewById(R.id.tv_item_forecast);
        mListener            = listener;
        itemView.setOnClickListener(this);
    }

    void bind(String s) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<List> jsonAdapter = moshi.adapter(List.class);
        List list = jsonAdapter.fromJson(s);

        String pressureAndHumidity = formatPressureAndHumidity(list.getPressure(), list.getHumidity());

        listItemForecastView.setText(list.getReadableDateString() + " " + pressureAndHumidity);
    }

    @Override
    public void onClick(View view) {
        int clickedPosition = getAdapterPosition();
        mListener.onListItemClick(clickedPosition);
    }

    private String formatPressureAndHumidity(double pressure, double humidity) {
        String roundedPressure  = new DecimalFormat("#.#").format(pressure);
        long roundedHumidity    = Math.round(humidity);

        String pressureAndHum = String.format("Pr: %s, Hum: %s%%", roundedPressure, roundedHumidity);

        return pressureAndHum;
    }
}
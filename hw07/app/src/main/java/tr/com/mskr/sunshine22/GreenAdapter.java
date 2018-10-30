package tr.com.mskr.sunshine22;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

public class GreenAdapter extends RecyclerView.Adapter<ForecastViewHolder> {

    private int                   mNumberItems;
    private String[]              items;
    private ListItemClickListener mOnClickListener;

    public GreenAdapter(ListItemClickListener listener) {
        mOnClickListener = listener;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context        context                         = parent.getContext();
        int            layoutIdForListItem             = R.layout.forecast_list_item;
        LayoutInflater inflater                        = LayoutInflater.from(context);
        boolean        shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ForecastViewHolder viewHolder = new ForecastViewHolder(view, mOnClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        try {
            holder.bind(items[position]);
        } catch (IOException e) {
            Log.v("GreenAdapter: ", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {

        return mNumberItems;
    }

    public void setWeatherData(String[] weatherData) {

        items        = weatherData;
        mNumberItems = weatherData.length;
        notifyDataSetChanged();
    }

    public String getItem(int index){
        return this.items[index];
    }
}

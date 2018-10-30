package tr.com.mskr.sunshine22;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GreenAdapter extends RecyclerView.Adapter<ForecastViewHolder> {

    private int                   mNumberItems;
    private String[]              items;
    private ListItemClickListener mOnClickListener;

    public GreenAdapter(int numberOfItems, String[] itemList, ListItemClickListener listener) {
        mNumberItems     = numberOfItems;
        items            = itemList;
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
        holder.bind(items[position]);
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
}

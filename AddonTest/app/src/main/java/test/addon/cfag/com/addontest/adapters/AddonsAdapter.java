package test.addon.cfag.com.addontest.adapters;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.addon.cfag.com.addontest.R;
import test.addon.cfag.com.addontest.model.AddonItem;

/**
 * Created by krlosf on 3/03/15.
 */
public class AddonsAdapter  extends BaseAdapter {
    private LayoutInflater inflater;
    private int layoutResourceId;
    private Context context;
    private List<?> lstListViewSelectItem;

    public AddonsAdapter(Context context, List<?> lstListViewSelectItem, int layoutResourceId) {
        this.context = context;
        this.lstListViewSelectItem = lstListViewSelectItem;
        this.layoutResourceId = layoutResourceId;
    }

    public int getCount() {
        return lstListViewSelectItem.size();
    }

    public Object getItem(int position) {
        return lstListViewSelectItem.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // Create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AddonItem item = (AddonItem) lstListViewSelectItem.get(position);
        ItemViewHolder viewHolder;

        if(convertView == null) {
            inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layoutResourceId, null, true);

            viewHolder = new ItemViewHolder();
            viewHolder.txtItemName = (TextView) convertView.findViewById(R.id.txtItemName);
            viewHolder.imgItemImage = (ImageView) convertView.findViewById(R.id.imgItemImage);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)convertView.getTag();
        }

        viewHolder.txtItemName.setText(item.label);
        viewHolder.imgItemImage.setImageDrawable(item.icon);

        return convertView;
    }

    private static class ItemViewHolder {
        TextView txtItemName;
        ImageView imgItemImage;
    }
}
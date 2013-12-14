package de.j4velin.picturechooser;

import java.util.List;

import de.j4velin.picturechooser.util.ImageLoader;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryAdapter extends BaseAdapter {

	private final List<GridItem> items;
	private final ImageLoader imageLoader;
	private final LayoutInflater mInflater;

	public GalleryAdapter(final Context context, final List<GridItem> buckets) {
		this.items = buckets;
		this.imageLoader = new ImageLoader(context);
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (items.get(0) instanceof BucketItem) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.bucketitem, null);
				holder = new ViewHolder();
				holder.icon = (ImageView) convertView.findViewById(R.id.icon);
				holder.text = (TextView) convertView.findViewById(R.id.text);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.text.setText(items.get(position).name);
			imageLoader.DisplayImage(items.get(position).path, holder.icon);
			return convertView;
		} else {
			ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = (ImageView) mInflater.inflate(R.layout.imageitem, null);
	        } else {
	            imageView = (ImageView) convertView;
	        }
	        imageLoader.DisplayImage(items.get(position).path, imageView);
	        return imageView;
		}
	}

	private static class ViewHolder {
		ImageView icon;
		TextView text;
	}

}

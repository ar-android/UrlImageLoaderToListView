package com.ocit.asynkcronusimage;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class InflanterList extends BaseAdapter {

	private String[] data;
	private LayoutInflater inflanter;

	private class ViewHolder {
		ImageView img;
	}

	public InflanterList(Context context, String[] image) {
		inflanter = LayoutInflater.from(context);
		this.data = image;

	}

	@Override
	public int getCount() {
		int position = data.length;
		return position;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder a;
		View rowview = convertView;
		if (rowview == null) {
			rowview = inflanter.inflate(R.layout.row_list, null);

			a = new ViewHolder();

			a.img = (ImageView) rowview.findViewById(R.id.image);
			rowview.setTag(a);
		} else {
			a = (ViewHolder) rowview.getTag();
		}

		new DownloadImageTask(a.img).execute(data);
		
		return rowview;
	}


	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}
	}

}

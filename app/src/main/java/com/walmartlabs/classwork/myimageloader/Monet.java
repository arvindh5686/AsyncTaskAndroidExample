package com.walmartlabs.classwork.myimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by abalak5 on 10/20/15.
 */
public class Monet {
    public static void load(String imageUrl, ImageView imageView) {
        ImageDownloadTask task = new ImageDownloadTask(imageView);
        task.execute(new String[]{imageUrl});
    }


    //Unable to find app for caller android.app.ActivityThread$ApplicationThread
    //cannot use main thread for network calls

    private static class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView imageView;
        public ImageDownloadTask(ImageView imageView) {
            this.imageView = imageView;
        }
        //Runs on background thread
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                URL url = null;
                url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
// 2. Open InputStream to connection
                conn.connect();
                InputStream in = conn.getInputStream();
// 3. Download and decode the bitmap using BitmapFactory
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            } catch (IOException e) {

            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}

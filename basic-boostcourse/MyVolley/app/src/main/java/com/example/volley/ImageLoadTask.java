package com.example.volley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.net.URL;
import java.util.HashMap;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
    private String urlStr;
    private  ImageView imageView;
    private static HashMap<String, Bitmap> bitmapHash = new HashMap<>();

    public ImageLoadTask(String urlStr, ImageView imageView) {
        this.urlStr = urlStr;
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap);
        // 혹시 요청이 안 됐을 때 다시 요청받아서 그려줌
        imageView.invalidate();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;
        try {
            // url에서 이미지를 가져올 때 bitmap으로 만들어서 가져옴
            // 그러면 메모리에 쌓임. 계속 쌓이게 되면 out of memory 에러 발생할 수 있음
            // 따라서 이전의 비트맵을 지워줘야함
            // Universal image loader 같은 외부 라이브러리는
            // 이미 불러온 파일을 또 불러오면 저장해놨다가 바로 보여줌 (서버에 다시 요청하지 않고 알아서 재활용함)
            if (bitmapHash.containsKey(urlStr)) {
                Bitmap oldBitmap = bitmapHash.remove(urlStr);
                if (oldBitmap != null) {
                    oldBitmap.recycle();
                    oldBitmap = null;
                }
            }

            URL url = new URL(urlStr);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            bitmapHash.put(urlStr, bitmap);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}

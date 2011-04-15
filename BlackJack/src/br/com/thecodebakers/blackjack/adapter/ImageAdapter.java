package br.com.thecodebakers.blackjack.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import br.com.thecodebakers.blackjack.R;


public class ImageAdapter extends BaseAdapter {
	
    private Context context;
   
    private Integer[] cartaVerso = {
    		R.drawable.verso, R.drawable.verso,
    		R.drawable.verso, R.drawable.verso,
    		R.drawable.verso, R.drawable.verso,
    		R.drawable.verso, R.drawable.verso,
    		R.drawable.verso, R.drawable.verso,
    };

    public ImageAdapter(Context c) {
    	context = c;
    }

    
    public int getCount() {
        return cartaVerso.length;  //retorna onze cartas 
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(50, 50));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(cartaVerso[position]);
        return imageView;
    }
    
   
     
}
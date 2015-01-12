package com.csgit.cao.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.csgit.cao.model.Mod_Minutas;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Imagen;

public class Adp_Minutas_Gallery extends BaseAdapter{
	 private final Context mContext;
	 private List<Mod_Minutas> imagenes;
	 //guardamos las imágenes reescaladas para mejorar el rendimiento ya que estas operaciones son costosas
	 //se usa SparseArray siguiendo la recomendación de Android Lint
	 SparseArray<Bitmap> imagenesEscaladas = new SparseArray<Bitmap>(7);
	 
     public Adp_Minutas_Gallery(Context context, List<Mod_Minutas> elementos) {
         super();
         mContext = context;
         imagenes = elementos;
     }

     @Override
     public int getCount() {
         return imagenes.size();
     }

     @Override
     public Object getItem(int position) {
         return imagenes.get(position);
     }


     @Override
     public long getItemId(int position) {
         return position;
     }

     @Override
     public View getView(int position, View convertView, ViewGroup container) {
         
         View vi = convertView;
         ImageView imageView;
         Holder holder = new Holder();
         if (vi == null) { // if it's not recycled, initialize some attributes
             imageView = new ImageView(mContext);
             imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
             holder.setImage(imageView);
         } else {
             imageView = (ImageView) vi;
             holder.setImage(imageView);
         }
         if (imagenesEscaladas.get(position) == null){
        	 Bitmap bitmap = null;
        	 bitmap = Utl_Imagen.escalarImagen(imagenes.get(position).getPathMinutas(),
    				 Utl_Constantes.IMG_Width, Utl_Constantes.IMG_Height);
  			imagenesEscaladas.put(position, bitmap);			 
  		 }
         holder.getImage().setImageBitmap(imagenesEscaladas.get(position));		
         
         return holder.getImage();
     }
     
     class Holder{
 		ImageView image;		
 		
 		public ImageView getImage(){
 			return image;
 		}
 		
 		public void setImage(ImageView image){
 			this.image = image;
 		}
 	}
}

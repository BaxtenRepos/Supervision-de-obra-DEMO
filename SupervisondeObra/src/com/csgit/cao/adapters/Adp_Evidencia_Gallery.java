package com.csgit.cao.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.csgit.cao.model.Mod_Multimedia;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Imagen;

public class Adp_Evidencia_Gallery extends BaseAdapter{
	
	 private final Context mContext;
	 private List<Mod_Multimedia> imagenes;
	 //guardamos las imágenes reescaladas para mejorar el rendimiento ya que estas operaciones son costosas
	 //se usa SparseArray siguiendo la recomendación de Android Lint
	 SparseArray<Bitmap> imagenesEscaladas = new SparseArray<Bitmap>(7);
	 
     public Adp_Evidencia_Gallery(Context context, List<Mod_Multimedia> elementos) {
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
             holder.setImage(imageView);
         } else {
             imageView = (ImageView) vi;
             holder.setImage(imageView);
         }
         if (imagenesEscaladas.get(position) == null){
        	 Bitmap bitmap = null;
        	 if(imagenes.get(position).getTipo() == Utl_Constantes.TIPO_IMAGEN){
//        		 bitmap = Utl_Imagen.escalarImagen(imagenes.get(position).getPath(),
//        				 Constantes.IMG_Width, Constantes.IMG_Height);
        		 bitmap = Utl_Imagen.badImagen(imagenes.get(position).getPath(),
        				 Utl_Constantes.IMG_Width, Utl_Constantes.IMG_Height);
        	 }else {
        		 bitmap = ThumbnailUtils.createVideoThumbnail(imagenes.get(position).getPath(),MediaStore.Video.Thumbnails.MINI_KIND);//
        	 }
  			imagenesEscaladas.put(position, bitmap);			 
  		 }
         holder.getImage().setScaleType(ImageView.ScaleType.CENTER_CROP);// temporal
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

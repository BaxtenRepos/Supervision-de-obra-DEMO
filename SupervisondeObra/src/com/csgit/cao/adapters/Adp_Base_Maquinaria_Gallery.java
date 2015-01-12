package com.csgit.cao.adapters;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.communicationchannel.model.Maquinaria;
import com.csgit.cao.utils.BitmapUtils;

@SuppressLint({ "InflateParams", "ViewHolder" })
public class Adp_Base_Maquinaria_Gallery extends BaseAdapter {
	
	private Context Contexto;
	private LayoutInflater inflater = null;
	private List<Maquinaria> imagenes;
	//guardamos las imágenes reescaladas para mejorar el rendimiento ya que estas operaciones son costosas
		//se usa SparseArray siguiendo la recomendación de Android Lint
	static SparseArray<Bitmap> imagenesEscaladas = new SparseArray<Bitmap>(7);
	
	public Adp_Base_Maquinaria_Gallery(Context contexto, List<Maquinaria> elementos){
		super();
		this.imagenes = elementos;
		this.Contexto = contexto;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imagenes.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		 return imagenes.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		 return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		Holder holder = null;
		//SparseArray<Bitmap> imagenesEscaladas = new SparseArray<Bitmap>(7);
		if (vi == null){
			
			holder = new Holder(); 
			inflater = (LayoutInflater) Contexto
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);		
			vi = inflater.inflate(R.layout.lyt_adp_maquinaria_gallery, null);
		    holder.setTextView((TextView) vi.findViewById(R.id.txv_maquinaria_gallery_descripcion));
	        holder.setImage((ImageView) vi.findViewById(R.id.img_maquinaria_gallery_imageView));
	        vi.setTag(holder); 
		}else {   
	        holder = (Holder) vi.getTag();
	    }		

       if (imagenesEscaladas.get(position) == null){
			Bitmap bitmap = BitmapUtils.decodeSampledBitmapFromResource(imagenes.get(position).getPathImagen(), 100, 0);
			imagenesEscaladas.put(position, bitmap);			 
		 }
//       	holder.getImage().setImageBitmap(imagenesEscaladas.get(position));//imagenesEscaladas.get(position)		
       holder.getImage().setImageBitmap( BitmapUtils.decodeSampledBitmapFromResource(imagenes.get(position).getPathImagen(), 100, 0));//imagenesEscaladas.get(position)
       holder.getTextView().setText(imagenes.get(position).getDescripcion()); 
         
        return vi;
	}
	
	class Holder{
		ImageView image;		
		TextView textView;
		
		public ImageView getImage(){
			return image;
		}
		
		public void setImage(ImageView image){
			this.image = image;
		}
		
		public TextView getTextView(){
			return textView;
		}
		
		public void setTextView(TextView textView){
			this.textView = textView;
		}		
		
	}

}

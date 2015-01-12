package com.csgit.cao.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Mod_Repo_Maquinaria_Cat_Maquinaria implements Parcelable{

	private long idRepoMaquinariaCatMaquinaria;
	private long idReporteDiario;
	private long idCatMaquinaria;
//	private int cantidad;
	private long cantidad;
	private String nombre;
	private int auxAccion;
	
	public Mod_Repo_Maquinaria_Cat_Maquinaria(){
		
	}
	
	public Mod_Repo_Maquinaria_Cat_Maquinaria(Parcel in){
		this.idRepoMaquinariaCatMaquinaria = in.readLong();
		this.idReporteDiario = in.readLong();
		this.idCatMaquinaria = in.readLong();
		this.cantidad = in.readLong();
		this.nombre = in.readString();
		this.auxAccion = in.readInt();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getIdRepoMaquinariaCatMaquinaria() {
		return idRepoMaquinariaCatMaquinaria;
	}
	public void setIdRepoMaquinariaCatMaquinaria(long idRepoMaquinariaCatMaquinaria) {
		this.idRepoMaquinariaCatMaquinaria = idRepoMaquinariaCatMaquinaria;
	}
	public long getIdReporteDiario() {
		return idReporteDiario;
	}
	public void setIdReporteDiario(long idReporteDiario) {
		this.idReporteDiario = idReporteDiario;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public long getIdCatMaquinaria() {
		return idCatMaquinaria;
	}
	public void setIdCatMaquinaria(long idCatMaquinaria) {
		this.idCatMaquinaria = idCatMaquinaria;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeLong(this.idRepoMaquinariaCatMaquinaria);
		dest.writeLong(this.idReporteDiario);
		dest.writeLong(this.idCatMaquinaria);
		dest.writeLong(this.cantidad);
		dest.writeString(this.nombre);
		dest.writeInt(this.auxAccion);
	}

	public int getAuxAccion() {
		return auxAccion;
	}

	public void setAuxAccion(int auxAccion) {
		this.auxAccion = auxAccion;
	}

	public static final Parcelable.Creator<Mod_Repo_Maquinaria_Cat_Maquinaria> CREATOR =
			new Parcelable.Creator<Mod_Repo_Maquinaria_Cat_Maquinaria>() {

				@Override
				public Mod_Repo_Maquinaria_Cat_Maquinaria createFromParcel(
						Parcel source) {
					// TODO Auto-generated method stub
					return new Mod_Repo_Maquinaria_Cat_Maquinaria(source);
				}

				@Override
				public Mod_Repo_Maquinaria_Cat_Maquinaria[] newArray(int size) {
					// TODO Auto-generated method stub
					return new Mod_Repo_Maquinaria_Cat_Maquinaria[size];
				}
			};
	
	
}

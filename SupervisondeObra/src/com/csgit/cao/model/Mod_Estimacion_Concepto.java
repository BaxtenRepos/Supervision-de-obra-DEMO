package com.csgit.cao.model;


public class Mod_Estimacion_Concepto{
	
	private Long idEstimacionConceptos;
	private Long idConcepto;
	private Long idEstimacion;
	private double CantidadAutorizada;
	private String descripcion;
	
	public Long getIdEstimacionConceptos() {
		return idEstimacionConceptos;
	}
	public void setIdEstimacionConceptos(Long idEstimacionConceptos) {
		this.idEstimacionConceptos = idEstimacionConceptos;
	}
	public Long getIdConcepto() {
		return idConcepto;
	}
	public void setIdConcepto(Long idConcepto) {
		this.idConcepto = idConcepto;
	}
	public Long getIdEstimacion() {
		return idEstimacion;
	}
	public void setIdEstimacion(Long idEstimacion) {
		this.idEstimacion = idEstimacion;
	}
	public double getCantidadAutorizada() {
		return CantidadAutorizada;
	}
	public void setCantidadAutorizada(double cantidadAutorizada) {
		CantidadAutorizada = cantidadAutorizada;
	}	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

//public class Mod_Estimacion_Concepto implements Parcelable {
//	
//	private Long idEstimacionConceptos;
//	private Long idConcepto;
//	private Long idEstimacion;
////	private int CantidadAutorizada;
//	private double CantidadAutorizada;
//	private String descripcion;
//	
//	public Mod_Estimacion_Concepto(){
//		
//	}
//	
//	public Mod_Estimacion_Concepto(Parcel in){
//		this.idEstimacionConceptos = in.readLong();
//		this.idConcepto = in.readLong();
//		this.idEstimacion = in.readLong();
//		this.CantidadAutorizada = in.readDouble();
//		this.descripcion = in.readString();
//	}
//	
//	public Long getIdEstimacionConceptos() {
//		return idEstimacionConceptos;
//	}
//	public void setIdEstimacionConceptos(Long idEstimacionConceptos) {
//		this.idEstimacionConceptos = idEstimacionConceptos;
//	}
//	public Long getIdConcepto() {
//		return idConcepto;
//	}
//	public void setIdConcepto(Long idConcepto) {
//		this.idConcepto = idConcepto;
//	}
//	public Long getIdEstimacion() {
//		return idEstimacion;
//	}
//	public void setIdEstimacion(Long idEstimacion) {
//		this.idEstimacion = idEstimacion;
//	}
//	public double getCantidadAutorizada() {
//		return CantidadAutorizada;
//	}
//	public void setCantidadAutorizada(double cantidadAutorizada) {
//		CantidadAutorizada = cantidadAutorizada;
//	}	
//	public String getDescripcion() {
//		return descripcion;
//	}
//	public void setDescripcion(String descripcion) {
//		this.descripcion = descripcion;
//	}
//
//	@Override
//	public int describeContents() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void writeToParcel(Parcel dest, int flags) {
//		// TODO Auto-generated method stub
//		dest.writeLong(this.idEstimacionConceptos);
//		dest.writeLong(this.idConcepto);
//		dest.writeLong(this.idEstimacion);
//		dest.writeDouble(this.CantidadAutorizada);
//		dest.writeString(this.descripcion);
//	}
//	
//	public static final Parcelable.Creator<Mod_Estimacion_Concepto> CREATOR
//    = new Parcelable.Creator<Mod_Estimacion_Concepto>() {
//        public Mod_Estimacion_Concepto createFromParcel(Parcel in) {
//            return new Mod_Estimacion_Concepto(in);
//        }
// 
//        public Mod_Estimacion_Concepto[] newArray(int size) {
//            return new Mod_Estimacion_Concepto[size];
//        }
//    };
//
//}
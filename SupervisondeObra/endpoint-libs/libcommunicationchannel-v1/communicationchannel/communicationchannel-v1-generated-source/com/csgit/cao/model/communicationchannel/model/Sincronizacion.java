/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-11-17 18:43:33 UTC)
 * on 2015-01-10 at 02:56:28 UTC 
 * Modify at your own risk.
 */

package com.csgit.cao.model.communicationchannel.model;

/**
 * Model definition for Sincronizacion.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the communicationchannel. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Sincronizacion extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long accion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String entidad;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long idRegistro;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long idSincronizacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long status;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getAccion() {
    return accion;
  }

  /**
   * @param accion accion or {@code null} for none
   */
  public Sincronizacion setAccion(java.lang.Long accion) {
    this.accion = accion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEntidad() {
    return entidad;
  }

  /**
   * @param entidad entidad or {@code null} for none
   */
  public Sincronizacion setEntidad(java.lang.String entidad) {
    this.entidad = entidad;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getIdRegistro() {
    return idRegistro;
  }

  /**
   * @param idRegistro idRegistro or {@code null} for none
   */
  public Sincronizacion setIdRegistro(java.lang.Long idRegistro) {
    this.idRegistro = idRegistro;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getIdSincronizacion() {
    return idSincronizacion;
  }

  /**
   * @param idSincronizacion idSincronizacion or {@code null} for none
   */
  public Sincronizacion setIdSincronizacion(java.lang.Long idSincronizacion) {
    this.idSincronizacion = idSincronizacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getStatus() {
    return status;
  }

  /**
   * @param status status or {@code null} for none
   */
  public Sincronizacion setStatus(java.lang.Long status) {
    this.status = status;
    return this;
  }

  @Override
  public Sincronizacion set(String fieldName, Object value) {
    return (Sincronizacion) super.set(fieldName, value);
  }

  @Override
  public Sincronizacion clone() {
    return (Sincronizacion) super.clone();
  }

}
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
 * Model definition for Usuario.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the communicationchannel. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Usuario extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String contrasena;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("id_Persona") @com.google.api.client.json.JsonString
  private java.lang.Long idPersona;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("id_Propietario") @com.google.api.client.json.JsonString
  private java.lang.Long idPropietario;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("id_Tipo_Persona") @com.google.api.client.json.JsonString
  private java.lang.Long idTipoPersona;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String usuario;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean visible;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getContrasena() {
    return contrasena;
  }

  /**
   * @param contrasena contrasena or {@code null} for none
   */
  public Usuario setContrasena(java.lang.String contrasena) {
    this.contrasena = contrasena;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getIdPersona() {
    return idPersona;
  }

  /**
   * @param idPersona idPersona or {@code null} for none
   */
  public Usuario setIdPersona(java.lang.Long idPersona) {
    this.idPersona = idPersona;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getIdPropietario() {
    return idPropietario;
  }

  /**
   * @param idPropietario idPropietario or {@code null} for none
   */
  public Usuario setIdPropietario(java.lang.Long idPropietario) {
    this.idPropietario = idPropietario;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getIdTipoPersona() {
    return idTipoPersona;
  }

  /**
   * @param idTipoPersona idTipoPersona or {@code null} for none
   */
  public Usuario setIdTipoPersona(java.lang.Long idTipoPersona) {
    this.idTipoPersona = idTipoPersona;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getUsuario() {
    return usuario;
  }

  /**
   * @param usuario usuario or {@code null} for none
   */
  public Usuario setUsuario(java.lang.String usuario) {
    this.usuario = usuario;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getVisible() {
    return visible;
  }

  /**
   * @param visible visible or {@code null} for none
   */
  public Usuario setVisible(java.lang.Boolean visible) {
    this.visible = visible;
    return this;
  }

  @Override
  public Usuario set(String fieldName, Object value) {
    return (Usuario) super.set(fieldName, value);
  }

  @Override
  public Usuario clone() {
    return (Usuario) super.clone();
  }

}
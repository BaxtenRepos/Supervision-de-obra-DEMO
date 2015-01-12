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
 * Model definition for ProyectoDelDirectivo.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the communicationchannel. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class ProyectoDelDirectivo extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<AvanceFinanciero> avanceFinanciero;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<AvanceFisico> avanceFisico;

  static {
    // hack to force ProGuard to consider AvanceFisico used, since otherwise it would be stripped out
    // see http://code.google.com/p/google-api-java-client/issues/detail?id=528
    com.google.api.client.util.Data.nullOf(AvanceFisico.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<ObraUtil> obras;

  static {
    // hack to force ProGuard to consider ObraUtil used, since otherwise it would be stripped out
    // see http://code.google.com/p/google-api-java-client/issues/detail?id=528
    com.google.api.client.util.Data.nullOf(ObraUtil.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Proyecto proyecto;

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<AvanceFinanciero> getAvanceFinanciero() {
    return avanceFinanciero;
  }

  /**
   * @param avanceFinanciero avanceFinanciero or {@code null} for none
   */
  public ProyectoDelDirectivo setAvanceFinanciero(java.util.List<AvanceFinanciero> avanceFinanciero) {
    this.avanceFinanciero = avanceFinanciero;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<AvanceFisico> getAvanceFisico() {
    return avanceFisico;
  }

  /**
   * @param avanceFisico avanceFisico or {@code null} for none
   */
  public ProyectoDelDirectivo setAvanceFisico(java.util.List<AvanceFisico> avanceFisico) {
    this.avanceFisico = avanceFisico;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public ProyectoDelDirectivo setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<ObraUtil> getObras() {
    return obras;
  }

  /**
   * @param obras obras or {@code null} for none
   */
  public ProyectoDelDirectivo setObras(java.util.List<ObraUtil> obras) {
    this.obras = obras;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Proyecto getProyecto() {
    return proyecto;
  }

  /**
   * @param proyecto proyecto or {@code null} for none
   */
  public ProyectoDelDirectivo setProyecto(Proyecto proyecto) {
    this.proyecto = proyecto;
    return this;
  }

  @Override
  public ProyectoDelDirectivo set(String fieldName, Object value) {
    return (ProyectoDelDirectivo) super.set(fieldName, value);
  }

  @Override
  public ProyectoDelDirectivo clone() {
    return (ProyectoDelDirectivo) super.clone();
  }

}
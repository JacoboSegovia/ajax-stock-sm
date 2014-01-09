/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rafaelaznar.bean;

public class ProductoBean {

    private Integer id = 0;
    private String codigo = "";
    private String descripcion = "";
    private Double precio = 0.0;
    private TipoproductoBean tipoproducto = null;
    private Integer id_tipoproducto = 0;

    public ProductoBean() {
        this.tipoproducto = new TipoproductoBean();
    }

    public ProductoBean(Integer id) {
        this.id = id;
        this.tipoproducto = new TipoproductoBean();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public TipoproductoBean getTipoProducto() {
        return tipoproducto;
    }

    public void setTipoProducto(TipoproductoBean tipoProducto) {
        this.tipoproducto = tipoProducto;
        this.id_tipoproducto = tipoProducto.getId();
    }
    
    public Integer getIdTipoproducto(){
        return id_tipoproducto;
    }
}
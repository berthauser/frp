package ar.frp.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para el Modelo Items
 *
 * @author berthaüser
 */

public class Items {
    
    private final SimpleDoubleProperty cantidad;
    private final StringProperty descripcion;
    private final SimpleDoubleProperty precioUnitario;
    private final SimpleDoubleProperty total;
    
    /**
     * Constructor.
     */
    public Items() {
        this(null, null);
    }

    /**
     * Constructor con algunos datos iniciales
     * 
     * @param cantidad
     * @param descripcion
     */
    public Items(Float cantidad, String descripcion) {
        this.cantidad = new SimpleDoubleProperty(1.0);
        this.descripcion = new SimpleStringProperty("Ingrese el #item de la operación");
        // Datos iniciales, sólo para testing
        this.precioUnitario = new SimpleDoubleProperty(1.5);
        this.total = new SimpleDoubleProperty(1234);
     
    }

    public SimpleDoubleProperty cantidadProperty() {
        return this.cantidad;
    }
    

    public double getCantidad() {
        return this.cantidadProperty().get();
    }
    

    public void setCantidad(final double cantidad) {
        this.cantidadProperty().set(cantidad);
    }
    

    public StringProperty descripcionProperty() {
        return this.descripcion;
    }
    

    public String getDescripcion() {
        return this.descripcionProperty().get();
    }
    

    public void setDescripcion(final String descripcion) {
        this.descripcionProperty().set(descripcion);
    }
    

    public SimpleDoubleProperty precioUnitarioProperty() {
        return this.precioUnitario;
    }
    

    public double getPrecioUnitario() {
        return this.precioUnitarioProperty().get();
    }
    

    public void setPrecioUnitario(final double precioUnitario) {
        this.precioUnitarioProperty().set(precioUnitario);
    }
    

    public SimpleDoubleProperty totalProperty() {
        return this.total;
    }
    

    public double getTotal() {
        return this.totalProperty().get();
    }
    

    public void setTotal(final double total) {
        this.totalProperty().set(total);
    }
    

   

}

package ar.frp.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Creado por bertHäuser el 20.10.2017. Esta clase maneja todos los campos de la
 * tabla Facultad. Contiene todos los métodos getters y setters y las
 * propiedades para todos los campos de la clase "modelo". La Propiedad
 * (Property) nos avisa cuando una variable como nombre, cuitl, etc., cambia.
 * Esto permite a la vista sincronizarse con los datos.
 */

public class Facultad {
	// Declaro las columnas de la tabla Facultad, usando "Propiedades"
	private SimpleIntegerProperty id_facultad;
	private StringProperty nombre;
	private StringProperty direccion;
	private StringProperty cuit;
	private IntegerProperty sucursal;
	private StringProperty telefono;
	private StringProperty correo;
	private BooleanProperty defecto;

	public Facultad() {
		this.id_facultad = new SimpleIntegerProperty();
		this.nombre = new SimpleStringProperty();
		this.direccion = new SimpleStringProperty();
		this.cuit = new SimpleStringProperty();
		this.sucursal = new SimpleIntegerProperty();
		this.telefono = new SimpleStringProperty();
		this.correo = new SimpleStringProperty();
		this.defecto = new SimpleBooleanProperty(false);
	}

	public final SimpleIntegerProperty id_facultadProperty() {
		return this.id_facultad;
	}

	public final int getId_facultad() {
		return this.id_facultadProperty().get();
	}
	
	public final void setId_facultad(final int id_facultad) {
		this.id_facultadProperty().set(id_facultad);
	}
	
	public final String getNombre() {
		return nombreProperty().get();
	}

	public final void setNombre(String value) {
		nombreProperty().set(value);
	}

	public StringProperty nombreProperty() {
		if (nombre == null) {
			nombre = new SimpleStringProperty();
		}
		return nombre;
	}

	public final String getDireccion() {
		return direccionProperty().get();
	}

	public final void setDireccion(String value) {
		direccionProperty().set(value);
	}

	public final StringProperty direccionProperty() {
		if (direccion == null) {
			direccion = new SimpleStringProperty();
		}
		return direccion;
	}

	public final String getCuit() {
		return cuitProperty().get();
	}

	public final void setCuit(String value) {
		cuitProperty().set(value);
	}

	public final StringProperty cuitProperty() {
		if (cuit == null) {
			cuit = new SimpleStringProperty();
		}
		return cuit;
	}

	public final Integer getSucursal() {
		return sucursalProperty().get();
	}

	public final void setSucursal(Integer value) {
		sucursalProperty().set(value);
	}

	public final IntegerProperty sucursalProperty() {
		if (sucursal == null) {
			sucursal = new SimpleIntegerProperty();
		}
		return sucursal;
	}

	public final String getTelefono() {
		return telefonoProperty().get();
	}

	public final void setTelefono(String value) {
		telefonoProperty().set(value);
	}

	public final StringProperty telefonoProperty() {
		if (telefono == null) {
			telefono = new SimpleStringProperty();
		}
		return this.telefono;
	}

	public final String getCorreo() {
		return correoProperty().get();
	}

	public final void setCorreo(String value) {
		correoProperty().set(value);
	}

	public final StringProperty correoProperty() {
		if (correo == null) {
			correo = new SimpleStringProperty();
		}
		return this.correo;
	}

	public final Boolean getDefecto() {
		return defectoProperty().get();
	}

	public final void setDefecto(Boolean value) {
		defectoProperty().set(value);
	}

	public final BooleanProperty defectoProperty() {
		if (defecto == null) {
			defecto = new SimpleBooleanProperty();
		}
		return this.defecto;
	}
}

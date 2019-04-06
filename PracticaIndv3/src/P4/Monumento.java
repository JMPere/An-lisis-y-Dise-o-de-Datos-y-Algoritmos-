package P4;

public class Monumento {
	private String nombre;

	public Monumento(String nombre) {
		this.nombre = nombre;
	}
	public static Monumento create() {
		return new Monumento("");
	}
	public static Monumento create(String name) {
		return new Monumento(name);
	}
	public static Monumento create(String[] names) {
		return new Monumento(names[0]);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String toString() {
		return nombre;
	}
}
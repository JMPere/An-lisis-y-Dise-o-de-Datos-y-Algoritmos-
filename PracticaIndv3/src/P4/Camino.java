package P4;

public class Camino {
	private Monumento origen;
	private Monumento destino;
	private Double tiempo; 

	public Camino(Monumento origen, Monumento destino, Double tiempo) {
		this.origen = origen;
		this.destino = destino;
		this.tiempo = tiempo;
	}
	public Camino(Monumento origen, Monumento destino ) {
		this.origen = origen;
		this.destino = destino;
		this.tiempo = null;
	}
	

	public Monumento getOrigen() {
		return origen;
	}

	public void setOrigen(Monumento origen) {
		this.origen = origen;
	}

	public Monumento getDestino() {
		return destino;
	}

	public void setDestino(Monumento destino) {
		this.destino = destino;
	}

	public Double getTiempo() {
		return tiempo;
	}

	public void setTiempo(Double t) {
		tiempo = t;
	}
	public static Camino create() {
		return new Camino(null,null,null);
	}
	public static Camino create(Monumento o, Monumento d) {
		return new Camino(o,d,null);
	}
	public static Camino create(Monumento o, Monumento d,String[] f) {
		return new Camino(o,d,Double.parseDouble(f[2]));
	}

}

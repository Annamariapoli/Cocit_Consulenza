package bean;

public class AutoreAutoreArticolo {
	
	private int idCreator1;
	private int idCreator2;
	private int numeroArticoli;
	
	public AutoreAutoreArticolo(int idCreator1, int idCreator2, int numeroArticoli) {
		super();
		this.idCreator1 = idCreator1;
		this.idCreator2 = idCreator2;
		this.numeroArticoli = numeroArticoli;
	}
	public int getIcCreator1() {
		return idCreator1;
	}
	public void setIcCreator1(int icCreator1) {
		this.idCreator1 = icCreator1;
	}
	public int getIdCreator2() {
		return idCreator2;
	}
	public void setIdCreator2(int idCreator2) {
		this.idCreator2 = idCreator2;
	}
	public int getNumeroArticoli() {
		return numeroArticoli;
	}
	public void setNumeroArticoli(int numeroArticoli) {
		this.numeroArticoli = numeroArticoli;
	}
	
	public String toString(){
		String r ;
		r = idCreator1+" "+idCreator2+" "+numeroArticoli+" "+" \n";
		return r;
	}

}

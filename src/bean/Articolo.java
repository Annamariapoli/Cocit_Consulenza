package bean;

public class Articolo {

	private int eprintid;
	private int year;
	private String title;
	public Articolo(int eprintid, int year, String title) {
		super();
		this.eprintid = eprintid;
		this.year = year;
		this.title = title;
	}
	public int getEprintid() {
		return eprintid;
	}
	public void setEprintid(int eprintid) {
		this.eprintid = eprintid;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eprintid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articolo other = (Articolo) obj;
		if (eprintid != other.eprintid)
			return false;
		return true;
	}
	
	public String toString(){
		String ris ;
		ris = eprintid+" "+year+" "+title+" \n";
		return ris;
	}
	

}

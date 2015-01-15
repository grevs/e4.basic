package pl.mpiasecki.eclipseE4.contextfunction;

public class CFTestImple implements ICFTest {

	private final String name;
	
	public CFTestImple() {
		this.name = "CFTestImpl";
	}

	public String getName() {
		return name;
	}

	@Override
	public int equalsFour() {
		return 4;
	}
}

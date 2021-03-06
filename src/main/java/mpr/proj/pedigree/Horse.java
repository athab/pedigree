package mpr.proj.pedigree;

public class Horse {

    private long id;
    private String name;
    private Sex sex;
    private DateOfBirth dob;
    private Color color;
    private Horse sire;
    private Horse dam;
    private Breeder breeder;

    public Horse(long id, String name, Sex sex, DateOfBirth dob, Color color, Horse sire, Horse dam, Breeder breeder) {
    	if (name == null) {
            throw new IllegalArgumentException("Horse's name cannot be null");
        }
        name = name.trim();
        if (name.length() == 0) {
            throw new IllegalArgumentException("Horse's name cannot be empty");
        }
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.color = color;
        this.sire = sire;
        this.dam = dam;
        this.breeder = breeder;
    }
    public long getID() {
    	return id;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

    public String getName() {
    	return name;
    }

    public void setSex(Sex sex) {
    	this.sex = sex;
    }

    public Sex getSex() {
    	return sex;
    }
    
    public void setDob(DateOfBirth dob) {
    	this.dob = dob;
    }

    public DateOfBirth getDob() {
    	return dob;
    }
    
    public void setDam(Horse dam) {
    	this.dam = dam;
    }

    public Horse getDam() {
    	return dam;
    }
    
    public void setSire(Horse sire) {
    	this.sire = sire;
    }
    
    public Horse getSire() {
    	return sire;
    }
    
    public void setColor(Color color) {
    	this.color = color;
    }
    
    public Color getColor() {
    	return color;
    }
    
    public void setBreeder(Breeder breeder){
    	this.breeder = breeder;
    }
    
    public Breeder getBreeder() {
    	return breeder;
    }
}
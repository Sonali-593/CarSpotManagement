package management.carspot;
/**
 * 
 * @author Sharath Babu
 *
 */
public class Car {
	private String regNumber;
	private boolean parked;
	private OwnerType ownerType;
	private String owner;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public boolean isParked() {
		return parked;
	}

	public void setParked(boolean parked) {
		this.parked = parked;
	}

	public OwnerType getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(OwnerType ownerType) {
		this.ownerType = ownerType;
	}

	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Car(String regNumber, OwnerType ownerType, String owner) {
		this.regNumber = regNumber;
		this.ownerType = ownerType;
		this.owner = owner;
	}



	@Override
	public String toString() {
		return "Car [regNumber=" + regNumber + ", parked=" + parked + ", ownerType=" + ownerType + ", owner=" + owner
				+ "]";
	}

	/**
	 * Method to park the car. 
	 * @throws Exception
	 */
	public void parkCar() throws Exception {
		if (this.parked)
			throw new Exception("Car is already parked.");
		this.parked = true;
	}

	/**
	 * Method to remove the car.
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */
	public String removeCar(String regNumber) throws Exception {
		if (this.parked == false)
			throw new Exception("Car is not parked.");
		this.parked = false;
		return this.toString();

	}
	
}
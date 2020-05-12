package management.carspot;

import java.util.Optional;

public class ParkingSlot {
	private String slotId;
	private SlotType slotType;
	private boolean occupied;
	private Car carParked;

	public String getSlotId() {
		return slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}

	public SlotType getSlotType() {
		return slotType;
	}

	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public Car getCarParked() {
		return carParked;
	}

	public void setCarParked(Car carParked) {
		this.carParked = carParked;
	}

	public ParkingSlot() {
	}

	public ParkingSlot(String slotId, SlotType slotType) {
		if (!slotId.matches("[A-Z][0-9]{2}")) {
			throw new IllegalArgumentException(
					"Please enter a Capital letter follwed by two digits for the Parking Slot Id.");
		}
		this.slotId = slotId;
		this.slotType = slotType;
	}

	@Override
	public String toString() {
		return "ParkingSlot [slotId=" + slotId + ", slotType=" + slotType + ", occupied=" + occupied + ", carParked="
				+ carParked + "]";
	}

	/**
	 * Method to park the car into the slot.
	 * 
	 * @param carToPark
	 * @return
	 * @throws Exception
	 */
	public String park(Car carToPark) throws Exception {
		if (this.slotType.getValue().equals(carToPark.getOwnerType().getValue()) && this.occupied == false) {
			this.setOccupied(true);
			this.setCarParked(carToPark);
			carToPark.parkCar();
			return "Car parked successfully.";
		}
		return "The slot is reserved for " + this.slotType.getValue();
	}

	/**
	 * Method to remove the Car from the slot.
	 * 
	 * @param car
	 * @return
	 * @throws Exception
	 */
	public String removeCarFromSlot(Optional<Car> car) throws Exception {
		String regNumber = null;
		Car carToBeRemoved = null;
		if (car.isPresent()) {
			regNumber = car.get().getRegNumber();
			carToBeRemoved = car.get();
		}
		if (this.carParked != null && this.carParked.getRegNumber().equals(regNumber)) {
			this.setOccupied(false);
			this.setCarParked(null);
			return "Car removed from slot successfully. Removed Car Details: " + carToBeRemoved.removeCar(regNumber);
		}
		return "This car is not parked in the slot.";
	}

}

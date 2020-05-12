package management.carspot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarPark {
	private List<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();
	//To do: maintain a list of the cars created such that only the unique cars are taken in.

	/**
	 * Method to add a slot.
	 * 
	 * @param slotId
	 * @param slotType
	 * @return
	 * @throws Exception
	 */
	public boolean addSlot(String slotId, SlotType slotType) throws Exception {
		boolean exists = this.parkingSlots.stream().anyMatch(ps -> ps.getSlotId().equals(slotId));
		if (!exists) {
			this.parkingSlots.add(new ParkingSlot(slotId, slotType));
			return true;
		}
		throw new Exception("Slot already Existing.");
	}

	/**
	 * Method to remove a slot.
	 * 
	 * @param slotId
	 * @return
	 * @throws Exception
	 */
	public boolean removeSlot(String slotId) throws Exception {
		Optional<ParkingSlot> slotToBeRemoved = this.parkingSlots.stream().filter(ps -> ps.getSlotId().equals(slotId))
				.findFirst();
		if (!slotToBeRemoved.isPresent()) {
			throw new Exception("Slot not existing.");
		} else if (slotToBeRemoved.get().isOccupied()) {
			throw new Exception("Slot has a Car parked in it.");
		}
		this.parkingSlots.remove(slotToBeRemoved.get());
		return true;
	}

	/**
	 * Method to find the slot detail.
	 * 
	 * @param slotId
	 * @return
	 */
	public Optional<ParkingSlot> findSlot(String slotId) {
		Optional<ParkingSlot> slotDetail = this.parkingSlots.stream().filter(ps -> ps.getSlotId().equals(slotId))
				.findFirst();
		return slotDetail;
	}

	/**
	 * Method to find the occupied slots.
	 * 
	 * @return
	 */
	public List<ParkingSlot> displayOccupiedSlots() {
		List<ParkingSlot> availableSlots = (List<ParkingSlot>) this.parkingSlots.stream()
				.filter(ps -> ps.isOccupied() == true).collect(Collectors.toList());
		return availableSlots;
	}

	/**
	 * Method to find the available slots.
	 * 
	 * @return
	 */
	public List<ParkingSlot> displayAvailableSlots() {
		List<ParkingSlot> availableSlots = this.parkingSlots.stream().filter(ps -> ps.isOccupied() == false)
				.collect(Collectors.toList());
		return availableSlots;
	}

	/**
	 * Method to park the car in a specific slot.
	 * 
	 * @param carToPark
	 * @param slotId
	 * @return
	 * @throws Exception
	 */
	public String park(Car carToPark, String slotId) throws Exception {
		Optional<ParkingSlot> slotDetail = this.parkingSlots.stream().filter(ps -> ps.getSlotId().equals(slotId))
				.findFirst();
		return slotDetail.get().park(carToPark);
	}

	public List<ParkingSlot> getParkingSlots() {
		return parkingSlots;
	}

	public void setParkingSlots(List<ParkingSlot> parkingSlots) {
		this.parkingSlots = parkingSlots;
	}

	/**
	 * Method to find the Car.
	 * 
	 * @param regNo
	 * @return
	 */
	public Optional<Car> findCar(String regNo) {
		List<Car> carsList = new ArrayList<>();
		for (ParkingSlot slot : this.parkingSlots) {
			carsList.add(slot.getCarParked());
		}
		Optional<Car> car = carsList.stream().filter(c -> c.getRegNumber().equals(regNo)).findFirst();

		return car;
	}

	/**
	 * Method to remove the car from a specific slot.
	 * 
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */
	public String removeCarByRegNumber(String regNumber) throws Exception {
		Optional<Car> car = findCar(regNumber);
		Optional<ParkingSlot> parkingSlot = this.parkingSlots.stream()
				.filter(ps -> ps.getCarParked().getRegNumber().equals(regNumber)).findFirst();
		if (parkingSlot.get() != null) {
			return parkingSlot.get().removeCarFromSlot(car);
		}
		return "Car not present in any slot.";
	}

}

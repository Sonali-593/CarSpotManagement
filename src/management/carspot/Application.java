package management.carspot;

import java.util.Optional;
import java.util.Scanner;

public class Application {
//remove owner from car
	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		CarPark carPark = new CarPark();
		String selectedOption = "";
		System.out.println("Welcome to the Parking Slot management application");

		while (true) {
			try {
				displayMenu();
				selectedOption = sc.nextLine();
				switch (selectedOption) {
				case "1":
					addSlot(sc, carPark);
					break;
				case "2":
					deleteSlot(sc, carPark);
					break;
				case "3":
					displayAllSlots(carPark);
					break;
				case "4":
					parkCarToSlot(sc, carPark);
					break;
				case "5":
					findCar(sc, carPark);
					break;
				case "6":
					removeCar(sc, carPark);
					break;
				case "7":
					displayAvailableSlots(carPark);
					break;
				case "8":
					displayOccupiedSlots(carPark);
					break;
				case "9":System.out.println("Program Ends.");
				System.exit(0);
				default:
					System.out.println("enter valid input");
				}
			} catch (Exception ex) {
				if (ex.getMessage() == null) {
					System.out.println("Error: please check your input");
				} else {
					System.out.println(ex.getMessage());
				}
			}
		}
	}

	private static void displayOccupiedSlots(CarPark carPark) {
		System.out.println("The occupied slots are :");
		carPark.displayOccupiedSlots().stream().forEach(s -> System.out.println(s));
	}

	private static void displayAvailableSlots(CarPark carPark) {
		System.out.println("The available slots are :");
		carPark.displayAvailableSlots().stream().forEach(s -> System.out.println(s));
	}

	private static void removeCar(Scanner sc, CarPark carPark) throws Exception {
		System.out.println("Enter the registeration number of the car.");
		String regNumber = sc.nextLine();
		System.out.println(carPark.removeCarByRegNumber(regNumber));
	}

	private static void findCar(Scanner sc, CarPark carPark) {
		System.out.println("Enter the registeration number of the car.");
		String regNo = sc.nextLine();
		Optional<Car> car = carPark.findCar(regNo);
		if (!car.isPresent()) {
			System.out.println("Car does not exist.");
		} else {
			System.out.println(car.toString());
		}
	}

	private static void parkCarToSlot(Scanner sc, CarPark carPark) throws Exception {
		System.out.println("Enter the car details.");
		System.out.println("Enter the registeration number of the car.");
		String registerationNumber = sc.nextLine();
		if (!registerationNumber.matches("[A-Z][0-9]{4}")) {
			throw new IllegalArgumentException(
					"Please enter a Capital letter follwed by four digits for the Parking Slot Id.");
		}
		System.out.println("Please enter the owner name.");
		String Ownername = sc.nextLine();
		System.out.println("Enter 1 if you are a visitor and 2 if you are a staff member.");
		String ownerType = sc.nextLine();
		System.out.println("the available slots are");
		carPark.displayAvailableSlots().stream().forEach(s -> System.out.println(s));
		if (ownerType.equals("1")) {
			Car car = new Car(registerationNumber, OwnerType.VISTOR, Ownername);
			System.out.println("Car created.");
			System.out.println("Enter the slotId.");
			String slotId = sc.nextLine();
			System.out.println(carPark.park(car, slotId));
		} else if (ownerType.equals("2")) {
			Car car = new Car(registerationNumber, OwnerType.STAFF_MEMBER, Ownername);
			System.out.println("Car created.");
			System.out.println("Enter the slotId.");
			String slotId = sc.nextLine();
			System.out.println(carPark.park(car, slotId));
		}
	}

	private static void displayAllSlots(CarPark carPark) {
		System.out.println("All the slots are as follows.");
		carPark.getParkingSlots().stream().forEach(s -> System.out.println(s));
	}

	private static void deleteSlot(Scanner sc, CarPark carPark) throws Exception {
		System.out.println("Please enter a slot Id to be deleted.");
		String slotId = sc.nextLine();
		if (carPark.removeSlot(slotId)) {
			System.out.println("Slot deleted successfully.");
		}
	}

	private static void addSlot(Scanner sc, CarPark carPark) throws Exception {
		System.out.println("Please enter a unique slot Id.");
		System.out.println("Note: should be in the format of one capital letter followed by 2 digits.");
		String slotId = sc.nextLine();
		System.out.println("Enter the slot type");
		System.out.println("Enter 1 for visitor and 2 for Staff member");
		String slotTypeEntry = sc.nextLine();
		if (slotTypeEntry.equals("1")) {
			if (carPark.addSlot(slotId, SlotType.VISTOR)) {
				System.out.println("slot added successfully.");
			}
		} else if (slotTypeEntry.equals("2")) {
			if (carPark.addSlot(slotId, SlotType.STAFF_MEMBER)) {
				System.out.println("slot added successfully.");
			}
		} else {
			System.out.println("Please enter a valid input.");
		}
	}

	private static void displayMenu() {
		System.out.println();
		System.out.println("Select a action from the menu below");
		System.out.println("1. Add a car spot");
		System.out.println("2. Delete a parking slot by slot ID");
		System.out.println("3. List all slots.");
		System.out.println("4. Park a car into a slot.");
		System.out.println("5. Find a car by registeration number.");
		System.out.println("6. Remove a car by registeration number.");
		System.out.println("7. display the available slots.");
		System.out.println("8. display the occupied slots.");
		System.out.println("9. Exit");
		System.out.println();
	}
}

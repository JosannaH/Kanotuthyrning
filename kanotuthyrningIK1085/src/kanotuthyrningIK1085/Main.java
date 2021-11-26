package kanotuthyrningIK1085;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Josanna Hyttsten IK1085
 * 
 *         Jag har haft problem med scanner.nextLine() som har tagit in värden
 *         som inte finns och därmed hoppat över att hämta in värden där den ska
 *         på vissa ställen Löste det med att använda next(); istället och sedan
 *         rensa scannern efter varje String-inmatning Vid inmatning av int har
 *         try/catch fångat upp att det inte får finnas mer än ett värde i
 *         scannern Blir problem om det finns kanotmärken eller typer med fler
 *         än ett ord då dessa inte kommer att godkännas.
 * 
 *         Blev inte jättemycket kommentaren, men tyckte varibelnamn och
 *         metodnamn talar för sig själva
 *
 */

public class Main {

	static String kanotID;
	static String kanotTyp;
	static String kanotMarke;
	static String kanotFarg;
	static int langd;
	static int antalPlatser;
	static String lagerPlats;
	static int prisPerTim;
	static Kanot canoeArray[] = new Kanot[24];
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		initializeArray();

		showMainMenu();

	}

	private static void showMainMenu() throws IOException {
		System.out.println("\nKANOTUTHYRNING");
		System.out.println("1. Lägg till kanot");
		System.out.println("2. Ändra pris eller lagerplats");
		System.out.println("3. Radera kanot");
		System.out.println("4. Visa alla kanoter");
		System.out.println("5. Sök kanot efter pris");
		System.out.println("6. Sök kanot efter typ");
		System.out.println("7. Sök kanot efter ID");
		System.out.println("8. Visa totala antalet kanotplatser");
		System.out.println("9. Hyr ut kanot"); // se punkt f
		System.out.println("10. Avsluta");

		// ta in användarens val
		int choice = 0;
		try {
			choice = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Felaktig inmatning");
			// töm scanner
			scanner.nextLine();
			showMainMenu();
		}

		if (choice >= 1 && choice <= 10) {
			switch (choice) {
			case 1:
				System.out.println("LÄGG TILL KANOT");
				addCanoe();
				break;
			case 2:
				System.out.println("UPPDATERA KANOT");
				updateCanoe();
				break;
			case 3:
				System.out.println("RADERA KANOT");
				deleteCanoe();
				break;
			case 4:
				System.out.println("ALLA KANOTER");
				printCanoes();
				break;
			case 5:
				System.out.println("SÖK EFTER PRIS");
				searchPrice();
				break;
			case 6:
				System.out.println("SÖK EFTER TYP");
				searchType();
				break;
			case 7:
				System.out.println("SÖK EFTER ID");
				searchID();
				break;
			case 8:
				System.out.println("VISA PLATSER");
				calcPlaces();
				break;
			case 9:
				System.out.println("HYR UT KANOT");
				rentCanoe();
				break;
			case 10:
				System.out.println("PROGRAMMET HAR AVSLUTATS");
				System.exit(0);
			}
		} else {
			System.out.println("Felaktig inmatning");
			scanner.nextLine();
			showMainMenu();
		}
	}

	private static void addCanoe() throws IOException {
		boolean full = true;
		int i;

		// kolla om det finns ledig plats lager
		// lagerplats 0 är default, dvs ledig
		for (int j = 0; j < canoeArray.length; j++) {
			if (canoeArray[j].getLagerPlats() == "0") {
				full = false;
			}
		}
		if (full == true) {
			System.out.println("Lagret är fullt");
			showMainMenu();
		} else { // om ledig lagerplats finns kan användaren lägga till ny kanot
			System.out.println("ID: ");
			try {
				kanotID = scanner.next().toUpperCase();
				scanner.nextLine();
				for (i = 0; i < canoeArray.length; i++) {
					// kolla om ID är upptaget
					if (canoeArray[i].getKanotID().equals(kanotID)) {
						System.out.println("Kanot med detta ID finns redan, ange ett annat ID.");
						scanner.nextLine();
						addCanoe();

						// om lagerplats finns - hitta första lediga lagerplats
					} else if (canoeArray[i].getLagerPlats() == "0") {

						System.out.println("Typ: ");
						kanotTyp = scanner.next().toLowerCase();
						scanner.nextLine();
						System.out.println("Märke: ");
						kanotMarke = scanner.next();
						scanner.nextLine();
						System.out.println("Färg: ");
						kanotFarg = scanner.next();
						scanner.nextLine();
						System.out.println("Längd (ange cm): ");
						langd = scanner.nextInt();
						System.out.println("Antal platser: ");
						antalPlatser = scanner.nextInt();
						System.out.println("Lagerplats: ");
						lagerPlats = scanner.next().toUpperCase();
						scanner.nextLine();
						System.out.println("Pris per timme: ");
						prisPerTim = scanner.nextInt();

						// skapa ny kanot
						Kanot kanot = new Kanot(kanotID, kanotTyp, kanotMarke, kanotFarg, langd, antalPlatser,
								lagerPlats, prisPerTim);

						// lägg till kanot i array
						canoeArray[i] = kanot;
						System.out.println("Kanoten är tillagd");
						showMainMenu();
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Ogiltig inmatning, börja från början.");
				scanner.nextLine();
				addCanoe();
			}
		}
	}

	private static void updateCanoe() throws IOException {
		int choice = 0;
		System.out.println("1. Ändra pris");
		System.out.println("2. Ändra lagerplats");
		System.out.println("3. Tillbaka till startmeny");
		try {
			choice = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Felaktig inmatning");
			scanner.nextLine();
			updateCanoe();
		}
		if (choice >= 1 && choice <= 3) {
			switch (choice) {
			case 1:
				changePrice();
				break;
			case 2:
				changeStock();
				break;
			case 3:
				showMainMenu();
				break;
			}
		}
	}

	private static void changeStock() throws IOException {
		String id = "0";
		boolean match = false;
		String stock = "0";

		System.out.println("ID: ");
		id = scanner.next().toUpperCase();

		for (int i = 0; i < canoeArray.length; i++) {
			if (canoeArray[i].getKanotID().equals(id)) {
				match = true;
			}
		}
		if (match == false) {
			System.out.println("Kanotens ID finns ej");
			System.out.println("Tryck 1 för att ange nytt ID");
			System.out.println("Tryck 2 för att gå till startmenyn");

			int choice = 0;
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Felaktig inmatning");
				scanner.nextLine();
				updateCanoe();
			}
			if (choice == 1) {
				changeStock();
			} else {
				showMainMenu();
			}
		} else {
			System.out.println("Ange ny lagerplats: ");
			try {
				stock = scanner.next().toUpperCase();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Felaktig inmatning");
				changeStock();
			}
			for (int i = 0; i < canoeArray.length; i++) {
				if (canoeArray[i].getKanotID().equals(id)) {

					canoeArray[i].setLagerPlats(stock);
					System.out.println("Ny lagerplats för kanot " + id + ": " + stock);
					showMainMenu();
				}
			}
		}
	}

	private static void changePrice() throws IOException {
		String id = "0";
		boolean match = false;
		int price = 0;

		System.out.println("ID: ");
		id = scanner.next().toUpperCase();

		for (int i = 0; i < canoeArray.length; i++) {
			if (canoeArray[i].getKanotID().equals(id)) {
				match = true;
			}
		}
		if (match == false) {
			System.out.println("Kanotens ID finns ej");
			System.out.println("Tryck 1 för att ange nytt ID");
			System.out.println("Tryck 2 för att gå till startmenyn");

			int choice = 0;
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Felaktig inmatning");
				scanner.nextLine();
				updateCanoe();
			}
			if (choice == 1) {
				changePrice();
			} else {
				showMainMenu();
			}

		} else {
			System.out.println("Ange nytt pris: ");
			try {
				price = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Felaktig inmatning");
				scanner.nextLine();
				changePrice();
			}
			for (int i = 0; i < canoeArray.length; i++) {
				if (canoeArray[i].getKanotID().equals(id)) {

					canoeArray[i].setPrisPerTim(price);
					System.out.println("Nytt pris för kanot " + id + ": " + price + " kr");
					showMainMenu();
				}
			}
		}
	}

	private static void deleteCanoe() throws IOException {
		String canoeToDelete = "0";
		boolean match = false;

		System.out.println("ID: ");
		canoeToDelete = scanner.next().toUpperCase();
		scanner.nextLine();

		for (int i = 0; i < canoeArray.length; i++) {
			if (canoeArray[i].getKanotID().equals(canoeToDelete)) {
				match = true;
			}
		}
		if (match == false) {
			System.out.println("Kanotens ID finns ej");
			System.out.println("Tryck 1 för att ange nytt ID");
			System.out.println("Tryck 2 för att gå till startmenyn");

			int choice = 0;
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("(Catch) Felaktig inmatning, försök igen: ");
				scanner.nextLine();
				deleteCanoe();
			}
			if (choice == 1) {
				deleteCanoe();
			} else {
				showMainMenu();

			}

		} else {
			for (int i = 0; i < canoeArray.length; i++) {
				if (canoeArray[i].getKanotID().equals(canoeToDelete)) {

					// ändra värden till default för att ta bort kanot/markera lagerplatsen som
					// ledig
					canoeArray[i].setKanotID("0");
					canoeArray[i].setKanotTyp("0");
					canoeArray[i].setKanotFarg("0");
					canoeArray[i].setKanotMarke("0");
					canoeArray[i].setAntalPlatser(0);
					canoeArray[i].setLagerPlats("0");
					canoeArray[i].setLangd(0);
					canoeArray[i].setPrisPerTim(0);

					System.out.println("Kanoten med ID " + canoeToDelete + " har raderats.");
					showMainMenu();
				}
			}
		}
	}

	private static void printCanoes() throws IOException {

		for (int i = 0; i < canoeArray.length; i++) {
			Kanot c = canoeArray[i];
			// skriv ut alla objekt som inte har en lagerplats som heter 0, vilket är ett
			// default värde = tom lagerplats
			if (c.getKanotID() != "0") {
				System.out.println("ID: " + c.getKanotID());
				System.out.println("Typ: " + c.getKanotTyp());
				System.out.println("Märke: " + c.getKanotMarke());
				System.out.println("Färg: " + c.getKanotFarg());
				System.out.println("Längd (ange cm): " + c.getLangd() + " cm");
				System.out.println("Antal platser: " + c.getAntalPlatser());
				System.out.println("Lagerplats: " + c.getLagerPlats());
				System.out.println("Pris per timme: " + c.getPrisPerTim() + " kr");
				System.out.println("------------------------------");
			}
		}
		showMainMenu();
	}

	private static void searchPrice() throws IOException {
		int price = 0;

		System.out.println("Sök kanoter med längre pris än: ");
		try {
			price = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Felaktig inmatning");
			scanner.nextLine();
			searchPrice();
		}
		for (int i = 0; i < canoeArray.length; i++) {
			Kanot c = canoeArray[i];

			// visa kanoter som kostar mindre än input och mer än 0 (som är default värde)
			if (c.getPrisPerTim() < price && c.getPrisPerTim() > 0) {

				System.out.println("ID: " + c.getKanotID());
				System.out.println("Typ: " + c.getKanotTyp());
				System.out.println("Märke: " + c.getKanotMarke());
				System.out.println("Färg: " + c.getKanotFarg());
				System.out.println("Längd (ange cm): " + c.getLangd() + " cm");
				System.out.println("Antal platser: " + c.getAntalPlatser());
				System.out.println("Lagerplats: " + c.getLagerPlats());
				System.out.println("Pris per timme: " + c.getPrisPerTim() + " kr");
				System.out.println("------------------------------");
			}
		}
		System.out.println("Finns inga fler kanoter att visa");
		System.out.println("Tryck 1 för att göra en ny sökning");
		System.out.println("Tryck 2 för att gå till startmenyn");

		int choice = 0;
		try {
			choice = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Felaktig inmatning");
			scanner.nextLine();
			searchPrice();
		}
		if (choice == 1) {
			searchPrice();
		} else if (choice == 2) {
			showMainMenu();
		} else {
			System.out.println("Felaktig inmatning, försök igen: ");
			scanner.nextLine();
			searchPrice();
		}
	}

	private static void searchType() throws IOException {
		String type = "0";
		boolean match = false;

		System.out.println("Typ: ");
		type = scanner.next().toLowerCase();
		scanner.nextLine();

		// kolla om det finns kanoter av denna typ i lager
		for (int i = 0; i < canoeArray.length; i++) {
			if (canoeArray[i].getKanotTyp().equals(type)) {
				match = true;
			}
		}
		if (match == false) {
			System.out.println("Det finns inga kanoter av den här typen.");
			System.out.println("Tryck 1 för att söka igen");
			System.out.println("Tryck 2 för att gå till startmenyn");

			int choice = 0;
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Felaktig inmatning, försök igen: ");
				scanner.nextLine();
				searchType();
			}
			if (choice == 1) {
				searchType();
			} else {
				showMainMenu();
			}
		} else { // om kanottyp finns i lager, visa alla av denna typ
			for (int i = 0; i < canoeArray.length; i++) {
				Kanot c = canoeArray[i];
				if (canoeArray[i].getKanotTyp().equals(type)) {

					System.out.println("\nID: " + c.getKanotID());
					System.out.println("Typ: " + c.getKanotTyp());
					System.out.println("Märke: " + c.getKanotMarke());
					System.out.println("Färg: " + c.getKanotFarg());
					System.out.println("Längd (ange cm): " + c.getLangd() + " cm");
					System.out.println("Antal platser: " + c.getAntalPlatser());
					System.out.println("Lagerplats: " + c.getLagerPlats());
					System.out.println("Pris per timme: " + c.getPrisPerTim() + " kr");
					System.out.println("------------------------------");
				}
			}
			showMainMenu();
		}
	}

	private static void searchID() throws IOException {
		String id = "0";
		boolean match = false;

		System.out.println("ID: ");
		id = scanner.next().toUpperCase();
		scanner.nextLine();
		// kolla om kanot med detta ID finns i lager
		for (int i = 0; i < canoeArray.length; i++) {
			if (canoeArray[i].getKanotID().equals(id)) {
				match = true;
			}
		}
		if (match == false) {
			System.out.println("Det finns ingen kanot med detta ID.");
			System.out.println("Tryck 1 för att söka igen");
			System.out.println("Tryck 2 för att gå till startmenyn");

			int choice = 0;
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Felaktig inmatning, försök igen: ");
				scanner.nextLine();
				searchID();
			}
			if (choice == 1) {
				searchID();
			} else {
				showMainMenu();
			}
		} else { // om minst en kanot av typen finns i lager, visa alla kanoter av typen
			for (int i = 0; i < canoeArray.length; i++) {
				Kanot c = canoeArray[i];
				if (canoeArray[i].getKanotID().equals(id)) {

					System.out.println("\nID: " + c.getKanotID());
					System.out.println("Typ: " + c.getKanotTyp());
					System.out.println("Märke: " + c.getKanotMarke());
					System.out.println("Färg: " + c.getKanotFarg());
					System.out.println("Längd (ange cm): " + c.getLangd() + " cm");
					System.out.println("Antal platser: " + c.getAntalPlatser());
					System.out.println("Lagerplats: " + c.getLagerPlats());
					System.out.println("Pris per timme: " + c.getPrisPerTim() + " kr");
					System.out.println("------------------------------");

					showMainMenu();
				}
			}
		}
	}

	private static void calcPlaces() throws IOException {
		int places = 0;
		// hämta värdet för variabel antalPlatser för alla kanoter och addera dessa
		for (int i = 0; i < canoeArray.length; i++) {
			places = places + canoeArray[i].getAntalPlatser();
		}
		System.out.println("Totala antalet kanotplatser är: " + places);
		showMainMenu();
	}

	private static void rentCanoe() throws IOException {
		String id = "0";
		int hours = 0;
		int price = 0;
		boolean match = false;
		try {
			System.out.println("Ange ID för kanot: ");
			id = scanner.next().toUpperCase();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Felaktig inmatning.");
			scanner.nextLine();
			rentCanoe();
		}
		// kolla om kanot med detta ID finns i lager
		for (int i = 0; i < canoeArray.length; i++) {
			if (canoeArray[i].getKanotID().equals(id)) {
				match = true;
			}
		}
		if (match == false) {
			System.out.println("Det finns ingen kanot med detta ID, försök igen.");
			rentCanoe();
		} else { // om kanot finns
			System.out.println("Ange antal timmar: ");
			try {
				hours = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Felaktig inmatning.");
				scanner.nextLine();
				rentCanoe();
			} // ta fram prisinfo för efterfrågat kanot
			for (int i = 0; i < canoeArray.length; i++) {
				if (canoeArray[i].getKanotID().equals(id)) {
					price = canoeArray[i].getPrisPerTim();
				}
			}
			int totalPrice = price * hours;

			System.out.println("Kanot: " + id + "\nTimmar: " + hours + "\nPris per timme: " + price + " kr"
					+ "\nSumma: " + totalPrice + " kr");
			showMainMenu();
		}
	}

	private static void initializeArray() {
		// lägg till uppgiftens kanoter i lager
		canoeArray[0] = new Kanot("K1", "turkanot", "Bell", "vit", 550, 3, "T3", 200);
		canoeArray[1] = new Kanot("K2", "forskanot", "Thunderbird", "röd", 350, 2, "F3", 500);
		canoeArray[2] = new Kanot("K3", "singelkanot", "Prijon", "blå", 320, 1, "S1", 400);
		// lägg till default värden till resterande platser i arrayen
		for (int i = 3; i < canoeArray.length; i++)
			canoeArray[i] = new Kanot("0", "0", "0", "0", 0, 0, "0", 0);
	}
}

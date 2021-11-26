package kanotuthyrningIK1085;

public class Kanot {

	String kanotID;
	String kanotTyp;
	String kanotMarke;
	String kanotFarg;
	int langd;
	int antalPlatser;
	String lagerPlats;
	int prisPerTim;
	String searchString;

	public Kanot(String kanotID, String kanotTyp, String kanotMarke, String kanotFarg, int langd, int antalPlatser,
			String lagerPlats, int prisPerTimme) {
		this.kanotID = kanotID;
		this.kanotTyp = kanotTyp;
		this.kanotMarke = kanotMarke;
		this.kanotFarg = kanotFarg;
		this.langd = langd;
		this.antalPlatser = antalPlatser;
		this.lagerPlats = lagerPlats;
		this.prisPerTim = prisPerTimme;
	}

	public Kanot() {

	}

	String getKanotID() {
		return kanotID;
	}

	void setKanotID(String kanotID) {
		this.kanotID = kanotID;
	}

	String getKanotTyp() {
		return kanotTyp;
	}

	void setKanotTyp(String kanotTyp) {
		this.kanotTyp = kanotTyp;
	}

	String getKanotMarke() {
		return kanotMarke;
	}

	void setKanotMarke(String kanotMarke) {
		this.kanotMarke = kanotMarke;
	}

	String getKanotFarg() {
		return kanotFarg;
	}

	void setKanotFarg(String kanotFarg) {
		this.kanotFarg = kanotFarg;
	}

	int getLangd() {
		return langd;
	}

	void setLangd(int langd) {
		this.langd = langd;
	}

	int getAntalPlatser() {
		return antalPlatser;
	}

	void setAntalPlatser(int antalPlatser) {
		this.antalPlatser = antalPlatser;
	}

	String getLagerPlats() {
		return lagerPlats;
	}

	void setLagerPlats(String lagerPlats) {
		this.lagerPlats = lagerPlats;
	}

	int getPrisPerTim() {
		return prisPerTim;
	}

	void setPrisPerTim(int prisPerTim) {
		this.prisPerTim = prisPerTim;
	}
}

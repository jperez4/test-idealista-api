package io.swagger.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulatedDB {
	public static Map<Integer, String> photosDB = new HashMap<Integer, String>();
    public static List<AssignScoreOutput> scoresDB = new ArrayList<>();
    public static List<Ads> adsDB = new ArrayList<>();
    private AssignScoreOutput score;
    private Ads adMock = new Ads();
    
    public SimulatedDB () {
    	photosDB.put(1, "SD");
        photosDB.put(2, "HD");
        photosDB.put(3, "SD");
        photosDB.put(4, "HD");
        photosDB.put(5, "SD");
        photosDB.put(6, "SD");
        photosDB.put(7, "SD");
        
    	SimulatedDB.scoresDB.add(new AssignScoreOutput("1", 20));
    	
    	adMock.setDescription("Este piso es una ganga, compra, compra, COMPRA!!!!!");
    	adMock.setGardenSize(0);
    	adMock.setHouseSize(200);
    	adMock.setId(1);
    	adMock.setPictures(new ArrayList<Integer>());
    	adMock.setTypology("PISO");
        SimulatedDB.adsDB.add(adMock);
    }

	public static Map<Integer, String> getPhotosDB() {
		return photosDB;
	}

	public static void setPhotosDB(Map<Integer, String> photosDB) {
		SimulatedDB.photosDB = photosDB;
	}

	public static List<AssignScoreOutput> getScoresDB() {
		return scoresDB;
	}

	public static void setScoresDB(List<AssignScoreOutput> scoresDB) {
		SimulatedDB.scoresDB = scoresDB;
	}

	public static List<Ads> getAdsDB() {
		return adsDB;
	}

	public static void setAdsDB(List<Ads> adsDB) {
		SimulatedDB.adsDB = adsDB;
	}
}

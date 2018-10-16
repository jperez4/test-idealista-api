package io.swagger.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Ads;
import io.swagger.model.AssignScoreOutput;
import io.swagger.model.SimulatedDB;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-11T11:28:57.691Z")

/**
 * This endpoint assigns a score between 0 and 100 to the ads passed in the request body following several rules:
 * - If the ad doesn't have a photo then the score is decreased by 10.
 * - If the ad have a HD photo then the score is increased by 20 otherwise by 10.
 * - If the ad have a description then the score is increased by 5.
 * - If the description of the ad have more than 50 words and it's a 'Chalet' then the score is increased by 20.
 * - If the description of the ad have more than 20 words and it's a 'Piso' then the score is increased by 20.
 * - If the description of the ad have more than 50 words and it's a 'Piso' then the score is increased by 30.
 * - If the ad's description contains the following words: 'Luminoso', 'Centrico', 'Reformado', 'Ático', 'Nuevo' adds 5 points for each.
 * - If the ad is complete the score is increased by 40. An ad is complete if it has description, at least one photo and
 *   if the ad is a 'Piso' it must have a house size. Also, if the ad is a 'Chalet' it must have house and garden size.
 * 
 * @author Javier Pérez Gonzalo
 *
 */
@Controller
public class AssignScoreApiController implements AssignScoreApi {
    
    @SuppressWarnings("unused")
	private SimulatedDB database = new SimulatedDB();

    public ResponseEntity<List<AssignScoreOutput>> assignScore(@ApiParam(value = "" ,required=true )  @Valid @RequestBody List<Ads> assignScoreInput) {
        
    	/* To fill the adsDB */
    	SimulatedDB.setAdsDB(assignScoreInput);

    	/* Output response */
        List<AssignScoreOutput> scores = new ArrayList<AssignScoreOutput>();
        
        for (Ads ad : assignScoreInput)
        {
        	/* Variables */	
        	int score = 0; 
        	int houseSize = (ad.getHouseSize() != null)?ad.getHouseSize():0;
        	int gardenSize = (ad.getGardenSize() != null)?ad.getGardenSize():0;
        	
            score += checkPhotos(ad.getPictures());
            
        	score += checkDescription(ad.getDescription(), ad.getTypology());

        	score += checkCompleteness(ad.getDescription(), ad.getPictures(),
        			ad.getTypology(), houseSize, gardenSize);
        	
        	scores.add(new AssignScoreOutput(ad.getId().toString(), score));
        }
        
        /* Simulate a call to save the scores in DB */
        SimulatedDB.setScoresDB(scores);
        
        return new ResponseEntity<List<AssignScoreOutput>>(scores, HttpStatus.OK);
    }
    
    
    /**
     * Check the photo identifiers list of an ad and perform a score.
     * @param photoIds
     * @return score based on rules provided.
     */
    public int checkPhotos (List<Integer> photoIds) {
    	
    	int score = 0;
    	
    	if (photoIds.isEmpty())
    		score -= 10;
    	else 
    	{
    		for (Integer id : photoIds)
    			score += (SimulatedDB.photosDB.get(id) == "HD")?20:10;
    	}
    	
    	return score;
    }
    
    
    /**
     * Check the ad description and typology and perform a score.
     * @param description of the ad.
     * @param typology of the ad.
     * @return score based on rules provided.
     */
    public int checkDescription (String description, String typology) {
    	
    	final String[] desirableWords = {"LUMINOSO", "NUEVO", "CENTRICO", "REFORMADO", "ATICO"};
    	int score = 0;
    	
    	if (description.length() != 0)
    	{
    		String[] descriptionWords = description.toUpperCase().split("(?=[,.])|\\s+");
    		score += 5;
    		
    		for (String dWord : desirableWords)
        	{
        		for (String word : descriptionWords)
        		{
        			if (dWord.equals(word))
        				score += 5;
        		}
        	}
    		
    		if (typology.equals("CHALET"))
        	{
        		if (description.length() >= 50)
        			score += 20;
        	}
        	else if (typology.equals("PISO"))
        	{
        		if (description.length() >= 50)
        			score += 30;
        		else if (description.length() >= 20)
        			score += 10;
        	}
    	}
    	
    	return score;
    }
    
    
    /**
     * Check completeness of the ad and perform score.
     * @param description of the ad.
     * @param photoIds of the ad.
     * @param typology of the ad.
     * @param houseSize of the ad.
     * @param gardenSize of the ad.
     * @return score based on rules provided.
     */
    public int checkCompleteness(String description, List<Integer> photoIds, String typology,
    		int houseSize, int gardenSize) {
    	
    	int score = 0;
    	
    	if (description.length() != 0 && photoIds.size() >= 1 
    			&& ((typology.equals("PISO") && houseSize > 0)
    			|| (typology.equals("CHALET") && houseSize > 0 && gardenSize > 0)))
    		
    		score += 40;
    	
    	return score;
    } 
    
}

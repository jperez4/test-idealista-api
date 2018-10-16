package io.swagger.api;

import io.swagger.model.Ads;
import io.swagger.model.AssignScoreOutput;
import io.swagger.model.SimulatedDB;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-11T11:28:57.691Z")

/**
 * Retrieve the list of ads in decreasing order based on its score.
 * @author Javier Pérez Gonzalo
 *
 */
@Controller
public class ListUserAdsApiController implements ListUserAdsApi {

    public ResponseEntity<List<Ads>> listUserAds() {
        
    	/* Variables */
    	Map<Integer, String> ads = new HashMap<Integer, String>();
    	List<Integer> adsScore = new ArrayList<Integer>();
    	List<Ads> output = new ArrayList<Ads>();
    	
    	/* Retrieve id's and scores of ads with a score greater or equals 40 */
    	for (AssignScoreOutput score : SimulatedDB.scoresDB)
    	{
    		if (score.getScore() >= 40)
    		{
    			ads.put(score.getScore(), score.getId());
    			adsScore.add(score.getScore());
    		}
    	}
    	
    	/* Sort the results */
    	sort(adsScore);

    	/* Retrieve the ads objects */
    	for (Integer score : adsScore) 
    	{
    		Ads adFound = searchAdByScore(ads.get(score));
    		if (adFound != null)
    			output.add(adFound);
    	}
    	
        return new ResponseEntity<List<Ads>>(output, HttpStatus.OK);
    }
    
    
    /**
     * Sort scores in decreasing order.
     * @param scores to sort.
     */
    public void sort(List<Integer> scores) {
    	
    	for (int m = scores.size(); m >= 0; m--)
    	{
    		for (int i = 0; i < scores.size() - 1; i ++)
    		{
    			int k = i + 1;
    			if (scores.get(i) < scores.get(k))
    			{
    				int temp = scores.get(i);
    				scores.set(i, scores.get(k));
    				scores.set(k, temp);
    			}
    		}
    	}
    }
    
    
    /**
     * Search in the simulated ad database for an ad.
     * @param id of the ad to retrieve.
     * @return ad found or null otherwise.
     */
    public Ads searchAdByScore(String id) {
    	
    	Ads output = null;
    	int i = 0;
    	boolean found = false;
    	
		while (i < SimulatedDB.adsDB.size() && !found)
		{
			if (SimulatedDB.adsDB.get(i).getId().toString().equals(id))
			{
				output = SimulatedDB.adsDB.get(i);
				found = true;
			}
			
			i++;
		}
		
		return output;
    }
    
}

package io.swagger.api;

import io.swagger.model.Ads;
import io.swagger.model.AssignScoreOutput;
import io.swagger.model.SimulatedDB;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-11T11:28:57.691Z")

/**
 * Retrieve the list of irrelevant ads (ads with a score under 40).
 * @author Javier Pérez Gonzalo
 *
 */
@Controller
public class ListQMAdsApiController implements ListQMAdsApi {

    public ResponseEntity<List<Ads>> listQMAds() {
    	
    	/* Variables */
    	List<String> adsIndexToRecover = new ArrayList<>();
    	List<Ads> adsToRecover = new ArrayList<>();
    	
        /* Look for scores under 40 and retrieve its indexes */
    	for (AssignScoreOutput scoreIter : SimulatedDB.scoresDB)
    	{
    		if (scoreIter.getScore() <= 40)
    			adsIndexToRecover.add(scoreIter.getId());
    	}
    	
    	/* Get ads based on its indexes */
    	for (Ads ad : SimulatedDB.adsDB)
    	{
    		if (adsIndexToRecover.contains(ad.getId().toString()))
    			adsToRecover.add(ad);
    	}

        return new ResponseEntity<List<Ads>>(adsToRecover, HttpStatus.OK);
    }

}

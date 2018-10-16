/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Ads;
import io.swagger.model.AssignScoreOutput;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-11T11:28:57.691Z")

@Api(value = "assignScore", description = "the assignScore API")
public interface AssignScoreApi {

    @ApiOperation(value = "Calculate the score of an ad", nickname = "assignScore", notes = "Depending on the parameters of the ad such as photos, quality, description, etc.", response = AssignScoreOutput.class, tags={ "Endpoints", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = AssignScoreOutput.class),
        @ApiResponse(code = 405, message = "Error operation") })
    @RequestMapping(value = "/assignScore",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<List<AssignScoreOutput>> assignScore(@ApiParam(value = "" ,required=true )  @Valid @RequestBody List<Ads> assignScoreInput);

}
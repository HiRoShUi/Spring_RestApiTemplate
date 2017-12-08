package classes.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseUtil {

	
	  /**
     * This method returns a ResponseEntity with the given message and type.
     * @param string
     * @param string2
     * @return
     */
    public static ResponseEntity<String> getResponse(String aiMessage, HttpStatus aiStatus) {
    	return new ResponseEntity<String>(aiMessage,aiStatus);
	}

	/**
     * This method returns a ResponseEntity for a argument that was not send to the resource!
     * @param aiErrortype
     * @return Returns a message about the argument that is missing and a bad-request-Status.
     */
    public static ResponseEntity<String> getLessArgsResponse(String aiArgName){
    	return  getResponse("You failed to upload the file because argument '" + aiArgName + "' was missing.", HttpStatus.BAD_REQUEST);
    }
    
	
}

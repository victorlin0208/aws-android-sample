package com.simpledb.info;

import java.util.ArrayList;
import java.util.List;
import android.os.AsyncTask;

import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;

public class StoreToDB extends AsyncTask<Movie, Void, Void>  {
	
	// 2. Create Domain and save movie information in domain
	public static void saveMovie(String name, float rating, String description)
	{
		try {

			 Connection.getAwsSimpleDB().createDomain(new CreateDomainRequest( "movie_info"));
			 List<ReplaceableAttribute> attribute= new ArrayList<ReplaceableAttribute>(1);
			 attribute.add(new ReplaceableAttribute().withName("movieName").withValue(name));
			 attribute.add(new ReplaceableAttribute().withName("movieRating").withValue(Float.toString(rating)));
			 attribute.add(new ReplaceableAttribute().withName("movieDescription").withValue(description));
			 Connection.awsSimpleDB.putAttributes(new PutAttributesRequest("movie_info",name, attribute));
			
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	@Override
	protected Void doInBackground(Movie... params) {
		// TODO Auto-generated method stub
		saveMovie(params[0].movieName, params[0].movieRating, params[0].movieDesctiption);
		return null;
	}



	
}

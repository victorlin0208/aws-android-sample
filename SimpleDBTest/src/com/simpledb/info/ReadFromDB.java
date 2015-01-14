package com.simpledb.info;

import java.util.List;

import com.amazonaws.services.simpledb.model.SelectRequest;

import android.os.AsyncTask;

public class ReadFromDB extends AsyncTask<Void, Void, Movie[]> {

	
	
	public static Movie [] getAllMovies() throws Exception
	{
		SelectRequest selectRequest=  new SelectRequest("select * from movie_info").withConsistentRead(true);
		
		List<com.amazonaws.services.simpledb.model.Item> items  = Connection.getAwsSimpleDB().select(selectRequest).getItems();
		
		try
		{
		com.amazonaws.services.simpledb.model.Item temp1;
		int size= items.size();
		Movie [] movieList= new  Movie[size];
		
		for(int i=0; i<size;i++)
		{
			temp1= ((com.amazonaws.services.simpledb.model.Item)items.get( i ));
			
			List<com.amazonaws.services.simpledb.model.Attribute> tempAttribute= temp1.getAttributes();
			movieList[i]= new Movie();
			for(int j=0; j< tempAttribute.size();j++)
			{
				if(tempAttribute.get(j).getName().equals("movieName"))
				{
					movieList[i].movieName=tempAttribute.get(j).getValue();
				}
				else if(tempAttribute.get(j).getName().equals("movieDescription"))
				{
					movieList[i].movieDesctiption =tempAttribute.get(j).getValue();
				}
				else if(tempAttribute.get(j).getName().equals("movieRating"))
				{
					movieList[i].movieRating =Float.valueOf(tempAttribute.get(j).getValue());					
				}
			}
		}
		return movieList;
		}
		catch( Exception eex)
		{
			throw new Exception("FIRST EXCEPTION", eex);
		}
	}
	
	@Override
	protected Movie[] doInBackground(Void... params) {
		// TODO Auto-generated method stub
		try {
			return getAllMovies();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

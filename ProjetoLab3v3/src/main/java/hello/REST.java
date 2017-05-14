package hello;

import java.util.List;
import static spark.Spark.get;
import static spark.Spark.post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * @author NickChecan
 * {@docRoot}
 */

public class REST {

	private Model model;

	public REST(Model store) {
		this.model = store;
	}
	
public void getEx(){
		
		get("/login/:var", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	 
	        	 
	        	response.header("Access-Control-Allow-Origin", "*");
	        	 
	            
	            
	            try {
	            	Exam infoExam = model.getExam(request.params(":var"));
	            	
	            	if(infoExam != null){
	            		
	            		JSONArray jsonResult = new JSONArray();
		         	    JSONObject jsonObj = new JSONObject();

		        		jsonObj.put("exam", infoExam.getAuthor());
		        		
		        		
		             	jsonResult.put(jsonObj);
		             	
		             	return jsonResult;
	            		
	            	} else {
	            		
	            		
	            		
	            	}
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			//e.printStackTrace();

	        		}
	         	    	
	
	            JSONArray jsonResult = new JSONArray();
         	    JSONObject jsonObj = new JSONObject();

        		jsonObj.put("exam", "Deu ruim.. não encontrado");
        		
        		
             	jsonResult.put(jsonObj);
             	
             	return jsonResult;
	            
	     	     
	         }
	         
	      });
			

	}
	
public void getLoginAndroid(){
	
	post("/login2", new Route() {
		@Override
		public Object handle(final Request request, final Response response) {
			response.header("Access-Control-Allow-Origin", "*");

			// Get html data through the post method
			JSONObject json = new JSONObject(request.body());
			String email = json.getString("email");
			String password = json.getString("password");

			try {
				User user = model.login(email, password);
				if (user != null) {
					//JSONArray jsonResult = new JSONArray();
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("name", user.getName());
					//jsonResult.put(jsonObj);
					return jsonObj;
				}
			} catch (JSONException error) {
				// error.printStackTrace();
			}
			
			// Return 0 if no user has been found
			JSONArray jsonResult = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("name", "Deu ruim");
			jsonResult.put(jsonObj);
			return null;
		}
	});
		

}	

	// Login function for the index page
	public void getLogin() {

		post("/login", new Route() {
			@Override
			public Object handle(final Request request, final Response response) {
				response.header("Access-Control-Allow-Origin", "*");

				// Get html data through the post method
				JSONObject json = new JSONObject(request.body());
				String email = json.getString("email");
				String password = json.getString("password");

				try {
					User user = model.login(email, password);
					if (user != null) {
						JSONArray jsonResult = new JSONArray();
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("name", user.getName());
						jsonResult.put(jsonObj);
						return jsonResult;
					}
				} catch (JSONException error) {
					// error.printStackTrace();
				}
				
				// Return 0 if no user has been found
				JSONArray jsonResult = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("name", 0);
				jsonResult.put(jsonObj);
				return jsonResult;
			}
		});
	}

	// Return to the view all registered exams in the database (home.html)
	public void getExams() {
		get("/getExams", new Route() {
			
			Gson gson = new Gson();
			@Override
			public Object handle(final Request request, final Response response) {
				response.header("Access-Control-Allow-Origin", "*");
				try {
					List<Exam> allExams = model.getExams();
					if (!allExams.isEmpty()) {
						
						
						JSONArray jsonResult = new JSONArray();
						for(Exam ae:allExams){
							JSONObject jsonObj = new JSONObject(gson.toJson(ae));
							jsonResult.put(jsonObj);
						}
						
						return jsonResult;
					}
				} catch (JSONException error) {
					 error.printStackTrace();
				}
				// Return 0 if no exam has been found
				JSONArray jsonResult = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("exams", 0);
				jsonResult.put(jsonObj);
				return jsonResult;
			}
		});
	}

	// Return to the view the selected exam (apply.html)
	public void getExam() {
		post("/getExam", new Route() {
			@Override
			public Object handle(final Request request, final Response response) {
				response.header("Access-Control-Allow-Origin", "*");
				
				// Get html data through the post method
				JSONObject json = new JSONObject(request.body());
				String userName = json.getString("userName");
				String examTitle = json.getString("examTitle");
				
				try {
					Gson gson = new Gson();
					Exam exam = model.getExam(examTitle);
					if (exam != null) {
						JSONArray jsonResult = new JSONArray();
						JSONObject jsonObj = new JSONObject(gson.toJson(exam));
						boolean activityFinished = model.getActivityStatus(userName, examTitle);
						if (activityFinished) {
							float result = model.getActivityResults(userName, examTitle);
							System.out.println(result);
							jsonObj.put("result", result);
						}
						jsonResult.put(jsonObj);
						return jsonResult;
					}
				} catch (JSONException error) {
					// error.printStackTrace();
				}
				// Return 0 if no exam has been found
				JSONArray jsonResult = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("exam", 0);
				jsonResult.put(jsonObj);
				return jsonResult;
			}
		});
	}

	// Return to the view the next question for the selected exam
	public void getNextQuestion() {
		post("/getNextQuestion", new Route(){
			@Override
			public Object handle(final Request request, final Response response) {
				response.header("Access-Control-Allow-Origin", "*");
				
				// Get html data through the post method
				JSONObject json = new JSONObject(request.body());
				String userName = json.getString("userName");
				String examTitle = json.getString("examTitle");
				
				try {
					Gson gson = new Gson();
					Question question = model.getNextQuestion(userName,	examTitle);
					if (question != null) {
						JSONArray jsonResult = new JSONArray();
						JSONObject jsonObj = new JSONObject(gson.toJson(question));
						jsonResult.put(jsonObj);
						return jsonResult;
					}
				} catch (JSONException error) {
					// error.printStackTrace();
				}
				// Return 0 if no exam has been found
				JSONArray jsonResult = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("question", 0);
				jsonResult.put(jsonObj);
				return jsonResult;
			}
		});
	}

	public void setUserAnswer() {
		post("/setUserAnswer", new Route() {
			@Override
			public Object handle(final Request request, final Response response) {
				response.header("Access-Control-Allow-Origin", "*");

				// Get html data through the post method
				JSONObject json = new JSONObject(request.body());
				String userName = json.getString("userName");
				String examTitle = json.getString("examTitle");
				String questionTitle = json.getString("questionTitle");
				String userAnswer = json.getString("userAnswer");

				try {
					boolean isCorrect = model.setAnswer(userName, examTitle, questionTitle, userAnswer);
					if (isCorrect) {
						// Return 1 if the answer is correct
						JSONArray jsonResult = new JSONArray();
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("isCorrect", 1);
						jsonResult.put(jsonObj);
						return jsonResult;
					}
				} catch (JSONException error) {
					// error.printStackTrace();
				}
				// Return 0 if the answer is not correct
				JSONArray jsonResult = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("isCorrect", 0);
				jsonResult.put(jsonObj);
				return jsonResult;
			}

		});
	}
	
	public void getActivityResults() {
		post("/getActivityResults", new Route() {
			@Override
			public Object handle(final Request request, final Response response) {
				response.header("Access-Control-Allow-Origin", "*");

				// Get html data through the post method
				JSONObject json = new JSONObject(request.body());
				String userName = json.getString("userName");
				String examTitle = json.getString("examTitle");

				try {
					boolean activityFinished = model.getActivityStatus(userName, examTitle);
					if (activityFinished) {
						float result = model.getActivityResults(userName, examTitle);
						// Return 1 if the answer is correct
						JSONArray jsonResult = new JSONArray();
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("result", result);
						jsonResult.put(jsonObj);
						return jsonResult;
					}
				} catch (JSONException error) {
					// error.printStackTrace();
				}
				// Return 0 if the answer is not correct
				JSONArray jsonResult = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("result", "X");
				jsonResult.put(jsonObj);
				return jsonResult;
			}
		});
	}
	
	public void checkActivity() {
		post("/checkActivity", new Route() {
			@Override
			public Object handle(final Request request, final Response response) {
				response.header("Access-Control-Allow-Origin", "*");

				// Get html data through the post method
				JSONObject json = new JSONObject(request.body());
				String userName = json.getString("userName");
				String examTitle = json.getString("examTitle");

				try {
					boolean activityFinished = model.getActivityStatus(userName, examTitle);
					if (activityFinished) {
						JSONArray jsonResult = new JSONArray();
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("complete", 1);
						jsonResult.put(jsonObj);
						return jsonResult;
					}
				} catch (JSONException error) {
					// error.printStackTrace();
				}
				// Return 0 if the answer is not correct
				JSONArray jsonResult = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("complete", 0);
				jsonResult.put(jsonObj);
				return jsonResult;
			}
		});
	}
	
	public void resetActivity() {
		post("/resetActivity", new Route() {
			@Override
			public Object handle(final Request request, final Response response) {
				response.header("Access-Control-Allow-Origin", "*");

				// Get html data through the post method
				JSONObject json = new JSONObject(request.body());
				String userName = json.getString("userName");
				String examTitle = json.getString("examTitle");

				try {
					if (model.resetActivity(userName, examTitle)) {
						JSONArray jsonResult = new JSONArray();
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("reset", 1);
						jsonResult.put(jsonObj);
						return jsonResult;
					}
				} catch (JSONException error) {
					// error.printStackTrace();
				}
				// Return 0 if the activity was not reseted
				JSONArray jsonResult = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("reset", 0);
				jsonResult.put(jsonObj);
				return jsonResult;
			}
		});
	}
	
	public void createUser() {
		post("/createUser", new Route() {
			@Override
			public Object handle(final Request request, final Response response) {
				response.header("Access-Control-Allow-Origin", "*");

				// Get html data through the post method
				JSONObject json = new JSONObject(request.body());
				String userName = json.getString("userName");
				String email = json.getString("email");
				String password = json.getString("password");

				try {
					model.addUser(new User(userName, true, new Login(email, password)));
					JSONArray jsonResult = new JSONArray();
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("created", 1);
					jsonResult.put(jsonObj);
					return jsonResult;
				} catch (JSONException error) {
					// error.printStackTrace();
				}
				// Return 0 if the activity was not reseted
				JSONArray jsonResult = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("created", 0);
				jsonResult.put(jsonObj);
				return jsonResult;
			}

		});
	}
}

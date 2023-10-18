package com.ass.mini2.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ass.mini2.entity.Result;
import com.ass.mini2.entity.Result2;
import com.ass.mini2.entity.Result3;
import com.ass.mini2.entity.User;
import com.ass.mini2.util.AppConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Service
public class NameApiService {
	private final WebClient.Builder webClientBuilder;
	private final WebClient.Builder webClientBuilder1;
	private final WebClient.Builder webClientBuilder2;
	
    @Autowired
    public NameApiService(WebClient.Builder webClientBuilder,WebClient.Builder webClientBuilder1,WebClient.Builder webClientBuilder2) {
        this.webClientBuilder = webClientBuilder;
        this.webClientBuilder1 = webClientBuilder1;
        this.webClientBuilder2 = webClientBuilder2;
    }
	
	public Result fetchDataFromApi1() {
//		return CompletableFuture.supplyAsync(() -> {
	        return webClientBuilder.build()
	                .get()
	                .uri("/") 
	                .retrieve()
	                .onStatus(HttpStatus::is4xxClientError,
	                        error -> Mono.error(new RuntimeException("API not found")))
	                .onStatus(HttpStatus::is5xxServerError,
	                        error -> Mono.error(new RuntimeException("Server is not responding")))
	                .bodyToMono(String.class)
	                .map(jsonResponse -> {
	                    // Parse JSON response
	                    JsonNode rootNode = null;
						try {
							rootNode = new ObjectMapper().readTree(jsonResponse);
						} catch (JsonProcessingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    // Extract values from the JSON structure
	                    JsonNode resultsNode = rootNode.get("results").get(0);
	                    String nat = resultsNode.get("nat").asText();
	                    String gender = resultsNode.get("gender").asText();
	                    String first = resultsNode.get("name").get("first").asText();
	                    String last = resultsNode.get("name").get("last").asText();
	                    String dob=resultsNode.get("dob").get("date").asText().substring(0,10);
	                    Integer age=resultsNode.get("dob").get("age").asInt();
	                    Result result=new Result(gender,first+" "+last,first,nat,dob,age);
//	                    System.out.println("Response1 "+result);
	                    return result;
	                    
	                }).block();
//		}, executor);
	}
	
	public Result2 fetchDataFromApi2(String name1){
//		return CompletableFuture.supplyAsync(() -> {
//             Call the first API using the extracted name
//			System.out.println("name1 in 2nd api",name1);
            return webClientBuilder1.build()
            		.get()
            		.uri("?name="+name1)
            		.retrieve()
            		 .onStatus(HttpStatus::is4xxClientError,
                             error -> Mono.error(new RuntimeException("API not found")))
                     .onStatus(HttpStatus::is5xxServerError,
                             error -> Mono.error(new RuntimeException("Server is not responding")))
            		.bodyToMono(String.class)
            		.map(jsonResponse->{
//            			System.out.println(jsonResponse);
            			JsonNode rootNode = null;
            			
            			try {
    						rootNode = new ObjectMapper().readTree(jsonResponse);
//    						System.out.println("In api 2", name1);
    						
    						
    					} catch (JsonProcessingException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
//            			System.out.println(rootNode);
            			String name=rootNode.get("name").asText();
            			JsonNode countryArray = rootNode.get("country");
            			List<String> countryIds = new ArrayList<>();
                        for (JsonNode countryNode : countryArray) {
                            String countryId = countryNode.get("country_id").asText();
                            countryIds.add(countryId);
                        }
                        Result2 res=new Result2(name,countryIds);
                        return res;

            			
                        
//                        System.out.println(res);
            			
            			
            		})
            		.block();
//        }, executor);
	}
	
	public Result3 fetchDataFromApi3(String name1){
//		return CompletableFuture.supplyAsync(() -> {
            // Call the first API using the extracted name
            return webClientBuilder2.build()
            		.get()
            		.uri("?name="+name1)
            		.retrieve()
            		 .onStatus(HttpStatus::is4xxClientError,
                             error -> Mono.error(new RuntimeException("API not found")))
                     .onStatus(HttpStatus::is5xxServerError,
                             error -> Mono.error(new RuntimeException("Server is not responding")))
            		.bodyToMono(String.class)
            		.map((jsonResponse)->{
            			System.out.println(jsonResponse);
            			JsonNode rootNode = null;
            			try {
//            				System.out.println("In api 3");
    						rootNode = new ObjectMapper().readTree(jsonResponse);
//    						System.out.println(rootNode);
    					
    					} catch (JsonProcessingException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
//            			System.out.println(rootNode);
            			String name=rootNode.get("name").asText();
            			String gender=rootNode.get("gender").asText();
            			Result3 res=new Result3(name,gender);
//            			System.out.println(res);
            			return res;
            			
            		})
            		.block();
//        }, executor);
	}
	public User callApisInParallelAndVerify() throws InterruptedException, ExecutionException{
		 ExecutorService executor = Executors.newFixedThreadPool(2);
		 CompletableFuture<Result> future = CompletableFuture.supplyAsync(() ->fetchDataFromApi1(),executor);
		 Result res=future.join();
//		 System.out.println("Response1 "+res);
		 String name1=res.getName();
//		 System.out.println("Name1 "+name1);
		 CompletableFuture<Result2> future1 = CompletableFuture.supplyAsync(() ->fetchDataFromApi2(name1),executor);;
		 CompletableFuture<Result3> future2 = CompletableFuture.supplyAsync(() ->fetchDataFromApi3(name1),executor);;
		 
		 Result2 result2= future1.get();
		 Result3 result3= future2.get();
		 System.out.println("Result from API 2: " + result2);
	        System.out.println("Result from API 3: " + result3);

	        // Remember to shut down the executor when done with it
		 if((result2.getCountryId().contains(res.getNat())) && (result3.getGender().equals(res.getGender()))) {
			 User verifiedUser=new User(res.getName(),res.getAge(),res.getGender(),res.getDob(),AppConstants.VERIFIED);
			 executor.shutdown();
			 return verifiedUser;
		 }
		 else {
			 User nonVerifiedUser=new User(res.getName(),res.getAge(),res.getGender(),res.getDob(),AppConstants.TO_BE_VERIFIED); 
			 executor.shutdown();
			 return nonVerifiedUser;
		 }
		 
	
	}
}







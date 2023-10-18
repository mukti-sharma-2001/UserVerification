package com.ass.mini2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ass.mini2.entity.PageInfo;
import com.ass.mini2.entity.User;
import com.ass.mini2.entity.UserListResponse;
import com.ass.mini2.services.UserService;
import com.ass.mini2.services.impl.NameApiService;
import com.ass.mini2.util.AppConstants;


@RestController
@RequestMapping(value="/api")
public class MainController {
	@Autowired
	private NameApiService service; 
	@Autowired
	private UserService userService; 
	
	@PostMapping("/{size}")
	public ResponseEntity<List<User>> getData(@PathVariable Integer size) throws InterruptedException, ExecutionException{
		List<User> users=new ArrayList<>();
		if(size<=5 &&size>=1) {
		
		for(int i=1;i<=size;i++) {
			System.out.println("in loop");
			User obj=service.callApisInParallelAndVerify();
			users.add(obj);
			userService.createUser(obj);
			System.out.println("end loop");
		}
//		if(size==1) {
//			users.add(service.callApisInParallelAndVerify());
//		}
//		User obj=service.callApisInParallelAndVerify();
		System.out.println(users);
//		User user=service.callApisInParallelAndVerify();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<User>>(users,HttpStatus.FORBIDDEN);
		}
		
	}
	
    @GetMapping("/listUser/")
    public ResponseEntity<UserListResponse> listUserResponse(
    @RequestParam(name = "sortType", defaultValue = AppConstants.NAME) String sortType,
    @RequestParam(name = "sortOrder", defaultValue = AppConstants.EVEN) String sortOrder,
    @RequestParam(name = "limit", defaultValue = "5") String limit,
    @RequestParam(name = "offset", defaultValue = "0") String offset)
    {
       
        boolean isSortTypeValid = sortType.equalsIgnoreCase(AppConstants.NAME) || sortType.equalsIgnoreCase(AppConstants.AGE);
        boolean isSortOrderValid = sortOrder.equalsIgnoreCase(AppConstants.EVEN) || sortOrder.equalsIgnoreCase(AppConstants.ODD);
        boolean isLimitValid = (Integer.parseInt(limit) >= 1) && (Integer.parseInt(limit) <= 5);
        boolean isOffsetValid = (Integer.parseInt(offset) >= 0);

        if (!isSortTypeValid || !isSortOrderValid || !isLimitValid || !isOffsetValid) {
            
            return ResponseEntity.badRequest().build();
        }

        
        List<User> users = userService.getUsers(sortType, sortOrder, Integer.parseInt(limit), Integer.parseInt(offset));
        int totalUsers = users.size();
        int startIndex = Math.min(Integer.parseInt(offset), totalUsers);
        int endIndex = Math.min(Integer.parseInt(offset) + Integer.parseInt(limit), totalUsers);

        if (startIndex > endIndex) {
            startIndex = endIndex;
        }
        List<User> paginatedUsers = users.subList(startIndex, endIndex);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setHasNextPage(endIndex < totalUsers);
        pageInfo.setHasPreviousPage(Integer.parseInt(offset) > 0);
        pageInfo.setTotal(totalUsers);
        UserListResponse userResponse = new UserListResponse(paginatedUsers, pageInfo);
        
        System.out.println(userResponse);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> listUser(
    @RequestParam(name = "sortType", defaultValue = AppConstants.NAME) String sortType,
    @RequestParam(name = "sortOrder", defaultValue = AppConstants.EVEN) String sortOrder,
    @RequestParam(name = "limit", defaultValue = "5") String limit,
    @RequestParam(name = "offset", defaultValue = "0") String offset)
    {
       
        boolean isSortTypeValid = sortType.equalsIgnoreCase(AppConstants.NAME) || sortType.equalsIgnoreCase(AppConstants.AGE);
        boolean isSortOrderValid = sortOrder.equalsIgnoreCase(AppConstants.EVEN) || sortOrder.equalsIgnoreCase(AppConstants.ODD);
        boolean isLimitValid = (Integer.parseInt(limit) >= 1) && (Integer.parseInt(limit) <= 5);
        boolean isOffsetValid = (Integer.parseInt(offset) >= 0);

        if (!isSortTypeValid || !isSortOrderValid || !isLimitValid || !isOffsetValid) {
            
            return ResponseEntity.badRequest().build();
        }

        
        List<User> users = userService.getUsers(sortType, sortOrder, Integer.parseInt(limit), Integer.parseInt(offset));

        return ResponseEntity.ok(users);
    }


	
}

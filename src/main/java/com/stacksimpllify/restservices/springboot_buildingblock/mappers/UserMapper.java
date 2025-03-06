package com.stacksimpllify.restservices.springboot_buildingblock.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.stacksimpllify.restservices.springboot_buildingblock.dto.UserMsDto;
import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;

/*private Long userid;
private String username;
private String emailaddress;*/


@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mapping(source="email",target="emailaddress")
	UserMsDto userToUserMsDto(User user);
	
	//List<User> to List<UserMsDto>
	@Mappings({
		@Mapping(source= "email", target="emailaddress"),
		@Mapping(source = "com.stacksimpllify.restservices.springboot_buildingblock.entities.User.id", target="userid")
		})
	List<UserMsDto> usersToUserDtos(List<User> users);

}

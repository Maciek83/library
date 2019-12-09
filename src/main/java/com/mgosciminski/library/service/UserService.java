package com.mgosciminski.library.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mgosciminski.library.converter.UserCreatorDtoToUser;
import com.mgosciminski.library.converter.UserToUserDisplayDto;
import com.mgosciminski.library.dto.BookRentDto;
import com.mgosciminski.library.dto.UserCreatorDto;
import com.mgosciminski.library.dto.UserDisplayDto;
import com.mgosciminski.library.dto.UserEditDto;
import com.mgosciminski.library.model.Book;
import com.mgosciminski.library.model.Role;
import com.mgosciminski.library.model.User;
import com.mgosciminski.library.repository.UserRepository;

import javassist.NotFoundException;

@Service("userService")
public class UserService {

	private final UserRepository userRepository;
	private final BookService bookService;
	private final UserCreatorDtoToUser userCreatorDtoToUserConverter;
	private final RoleService roleService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserToUserDisplayDto userToUserDisplayDtoConverter;
	
	@Autowired
	public UserService(UserRepository userRepository,
			BookService bookService,
			BCryptPasswordEncoder bCryptPasswordEncoder, 
			RoleService roleService,
			UserCreatorDtoToUser userCreatorDtoToUser,
			UserToUserDisplayDto userToUserDisplayDtoConverter) {
		super();
		this.userRepository = userRepository;
		this.bookService = bookService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleService = roleService;
		this.userCreatorDtoToUserConverter = userCreatorDtoToUser;
		this.userToUserDisplayDtoConverter = userToUserDisplayDtoConverter;
	}
	
	public User saveExistingUser(User user)
	{
		return userRepository.save(user);
	}
	
	public User findUserByName(String name)
	{
		return userRepository.findByName(name);
	}
	
	public List<User> findAllUsers()
	{
		return userRepository.findAll();
	}
	
	public List<UserDisplayDto> findAllUsersDisplayDto()
	{
		
		return findAllUsers()
				.stream()
				.map(this::convertUserToUserDisplayDto)
				.collect(Collectors.toList());
	}
	
	public UserDisplayDto findUserByEmail(String email)
	{
		return convertUserToUserDisplayDto(userRepository.findByEmail(email));
	}
	
	public UserDisplayDto findUserDisplayDtoByName(String name)
	{
		return convertUserToUserDisplayDto(findUserByName(name));
	}
	
	public UserDisplayDto findUserDisplayDtoById(Long id) throws NotFoundException
	{
		return convertUserToUserDisplayDto(findUserById(id));
	}
	
	public User findUserById(Long id) throws NotFoundException {
		return userRepository.findById(id).orElseThrow(()-> new NotFoundException("Can't find user with this id."));
	}
	
	public UserDisplayDto saveUserDto(UserCreatorDto userCreatorDto)
	{
		User newUser = convertUserCreatorDtoToUser(userCreatorDto);
		
		return saveNewUser(newUser);
	}
	
	public UserDisplayDto convertUserToUserDisplayDto(User user)
	{
		return userToUserDisplayDtoConverter.convert(user);
	}
	
	public User convertUserCreatorDtoToUser(UserCreatorDto userCreatorDto)
	{
		return userCreatorDtoToUserConverter.convert(userCreatorDto);
	}
	
	public UserDisplayDto saveNewUser(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<Role> rolesOld = user.getRoles();
		user.setRoles(new HashSet<Role>());
		
		User fromDb = userRepository.save(user);
		
		Set<Role> rolesNew = new HashSet<>();
		
		for (Role r : rolesOld) {
			
			Role fromDataBaseRole = roleService.saveRole(r.getRole().toString());
			
			rolesNew.add(fromDataBaseRole);
		}
		
		fromDb.setRoles(rolesNew);
		
		
		return convertUserToUserDisplayDto(userRepository.save(fromDb)) ;
		
	}
	
	public UserDisplayDto rentBooks(String userName, BookRentDto bookRentDto)
	{
		User userFromDb = findUserByName(userName);
		
		Set<Book> newBooks = bookService.findBooksToRent(bookRentDto);
		Set<Book> actualBooks = userFromDb.getBook();
		
		Set<Book> filteredBooks = filterNewBooksByActualBooks(actualBooks, newBooks);
		
		Set<Book> substractedBooks = new HashSet<Book>();
		
		try {
			substractedBooks = substractFromEachBookQuantity(filteredBooks);
		} 
		catch (NotFoundException e) 
		{
			e.printStackTrace();
		}
		
		userFromDb.getBook().addAll(substractedBooks);
		
		return convertUserToUserDisplayDto(saveExistingUser(userFromDb));
	}
	
	public UserDisplayDto giveBackBooks(Long id, BookRentDto bookRentDto) throws NotFoundException
	{
		User actualUser = findUserById(id);
		
		Set<Book> bookToGiveBack = bookService.findBooksToRent(bookRentDto);
		
		Set<Book> actualBooks = actualUser.getBook();
		
		Set<Book> filteredBooks = filterBookToGiveBackByActualBooks(bookToGiveBack, actualBooks);
		
		User savedUser = substractBooksFromUser(actualUser, filteredBooks);
		
		return convertUserToUserDisplayDto(savedUser);
	}
	
	public User substractBooksFromUser(User user, Set<Book> bookToSubstract)
	{
		 Set<Book> books = addToEachBookQuantity(bookToSubstract);
		 
		 user.getBook().removeAll(books);
		 
		 return saveExistingUser(user);
	}
	
	public Set<Book> addToEachBookQuantity(Set<Book> book)
	{
		book.stream().forEach(b->{
			b.getQuantity().setNumber(b.getQuantity().getNumber()+1);
			bookService.saveBook(b);
		}
		);
		
		return book;
	}
	
	public Set<Book> filterBookToGiveBackByActualBooks(Set<Book> bookToGiveBack, Set<Book> actualBooks) 
	{
		
		Set<Book> filteredBooks = new HashSet<Book>();
		
		for (Book book : bookToGiveBack) {
			
			if(actualBooks.contains(book))
				filteredBooks.add(book);
		}
		
		
		return filteredBooks;
	}
	
	public Set<Book> substractFromEachBookQuantity(Set<Book> books) throws NotFoundException
	{
		for (Book book : books) {
			
			if(book.getQuantity().getNumber() <= 0)
			{
				throw new NotFoundException("We don't have this book");
			}
			
			book.getQuantity().setNumber(book.getQuantity().getNumber()-1);
		}
		
		return books;
	}
	
	public Set<Book> filterNewBooksByActualBooks(Set<Book> actualBooks, Set<Book> newBooks)
	{
		Set<Book> filteredBooks = new HashSet<Book>();
		
		for (Book book : newBooks) {
			
			if(!actualBooks.contains(book))
			{
				filteredBooks.add(book);
			}
			else 
			{
				System.out.println("I have this book!");
			}
		}
		
		return filteredBooks;
	}
	
	public UserDisplayDto editUser(Long id, UserEditDto userEditDto) throws NotFoundException
	{
		User userFromDb = findUserById(id);
		
		Set<Role> newRoles = roleService.findRolesByStrings(userEditDto.getRoles());
		
		userFromDb.setActive(userEditDto.getActive());
		userFromDb.setRoles(newRoles);
		
		UserDisplayDto userDisplayDto = convertUserToUserDisplayDto(saveExistingUser(userFromDb));
		
		return userDisplayDto;
	}
}

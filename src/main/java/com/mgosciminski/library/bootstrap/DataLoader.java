package com.mgosciminski.library.bootstrap;

import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.util.buf.B2CConverter;
import org.graalvm.compiler.phases.common.UseTrappingNullChecksPhase;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mgosciminski.library.model.Book;
import com.mgosciminski.library.model.Role;
import com.mgosciminski.library.model.RoleType;
import com.mgosciminski.library.model.User;
import com.mgosciminski.library.repository.BookRepository;
import com.mgosciminski.library.service.BookService;
import com.mgosciminski.library.service.RoleService;
import com.mgosciminski.library.service.UserService;

import antlr.debug.NewLineEvent;

@Component
public class DataLoader implements CommandLineRunner {

	private final RoleService roleService;
	private final UserService userService;
	private final BookService bookService;
	
	@Autowired()
	public DataLoader(RoleService roleService, UserService userService, BookService bookService) {
		super();
		this.roleService = roleService;
		this.userService = userService;
		this.bookService = bookService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role r1 = new Role();
		r1.setRole(RoleType.ADMIN);
		Role r2 = new Role();
		r2.setRole(RoleType.USER);
		
		roleService.saveRole(r1.getRole().toString());
		roleService.saveRole(r2.getRole().toString());
		
		Set<Role> roles = new HashSet<>();
		Role r3 = new Role();
		r3.setRole(RoleType.ADMIN);
		roles.add(r3);
		
		Set<Role> roles2 = new HashSet<>();
		Role r4 = new Role();
		r4.setRole(RoleType.USER);
		roles2.add(r4);
		
		Set<Book> b11 = new HashSet<>();
		Book book = new Book();
		book.setTitle("dd");
		book.setCount(2);
		b11.add(book);
		
		Set<Book> b22 = new HashSet<>();
		Book book2 = new Book();
		book.setTitle("dd");
		book.setCount(2);
		b22.add(book2);
		
		User user = new User();
		user.setName("Maciek");
		user.setEmail("m.gosciminski@wp.pl");
		user.setActive(1);
		user.setPassword("koko");
		user.setRoles(roles);

		
		User user2 = new User();
		user2.setName("Bobo");
		user2.setEmail("bobo.sds@wp.pl");
		user2.setActive(1);
		user2.setPassword("koko");
		user2.setRoles(roles2);

		
		userService.saveUser(user);
		userService.saveUser(user2);
		
		Book b1 = new Book();
		b1.setTitle("title");
		b1.setCount(4);;
		Book b2 = new Book();
		b2.setTitle("title2");
		b2.setCount(4);
		
		bookService.saveBook(b1);
		bookService.saveBook(b2);
		
	}

}

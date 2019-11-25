package com.mgosciminski.library.bootstrap;

import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.util.buf.B2CConverter;
import org.graalvm.compiler.hotspot.nodes.G1ArrayRangePostWriteBarrier;
import org.graalvm.compiler.phases.common.UseTrappingNullChecksPhase;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mgosciminski.library.model.Book;
import com.mgosciminski.library.model.Quantity;
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
		Quantity q1 = new Quantity();
		q1.setNumber(22);
		q1.setBook(book);
		book.setQuantity(q1);
		b11.add(book);
		
		Set<Book> b22 = new HashSet<>();
		Book book2 = new Book();
		book.setTitle("dd");
		Quantity q2 = new Quantity();
		q2.setNumber(12);
		q2.setBook(book2);
		book2.setQuantity(q2);
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


		
		userService.saveNewUser(user);
		userService.saveNewUser(user2);
		
		Book b1 = new Book();
		b1.setTitle("title");
		Quantity g3 = new Quantity();
		g3.setBook(b1);
		g3.setNumber(12);
		b1.setQuantity(g3);
		
		Book b2 = new Book();
		b2.setTitle("title2");
		Quantity g4 = new Quantity();
		g4.setNumber(1234);
		g4.setBook(b2);
		b2.setQuantity(g4);
		
		
		bookService.saveBook(b1);
		bookService.saveBook(b2);
		
		user2.getBook().add(b1);
		
		userService.saveExistingUser(user2);
		
	}

}

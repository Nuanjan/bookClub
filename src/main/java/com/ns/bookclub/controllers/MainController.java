package com.ns.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ns.bookclub.models.Book;
import com.ns.bookclub.models.LoginUser;
import com.ns.bookclub.models.User;
import com.ns.bookclub.services.BookService;
import com.ns.bookclub.services.UserService;

@Controller
public class MainController {

    private UserService userServ;
 	private BookService bookServ;
 	public MainController(UserService userServ, BookService bookServ) {
 		this.bookServ = bookServ;
 		this.userServ = userServ;
 	}
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
  // ==================== Log in and Register ==============================
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/books";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/books";
    }
    
    // ===================== Log Out Route ===========================
    @GetMapping("/logout")
    public String  logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
  
    // ======================== Get Routes ============================
    
    @GetMapping("/books")
    public String getUser(Model model, HttpSession session, RedirectAttributes flash) {
    	if(session.getAttribute("user_id") == null) {
    		flash.addFlashAttribute("login", "Please Log in or Register before entering page");
    		return "forbidden.jsp";
    	}
    	Long id = (Long)session.getAttribute("user_id");
    	User user = userServ.findUser(id);
    	List<Book> books = bookServ.allBooks();
    	model.addAttribute("user", user);
    	model.addAttribute("books", books);
    	return "books.jsp";
    	
    }
    
    @GetMapping("/books/{id}")
    public String getBook(@PathVariable("id") Long id, Model model, HttpSession session) {
    	if(session.getAttribute("user_id") == null) {
    		return "forbidden.jsp";
    	}
    	Book book = bookServ.findBook(id);
    	User tempUser = book.getUser();
    	Long currentUserId = (Long)session.getAttribute("user_id");
    	if(tempUser.getId().equals(currentUserId)) {
    		session.setAttribute("current_user", "true"); 
    	} else {
    		session.setAttribute("current_user", "false"); 
    	}
    	model.addAttribute(book);
    	return "bookDetail.jsp";
    }
    
  // ========================Create Routes ================================
    @GetMapping("/books/new")
    public String newBook(@ModelAttribute("book") Book book, Model model, HttpSession session) {
    	Long id = (Long)session.getAttribute("user_id");
    	User user = userServ.findUser(id);
    	model.addAttribute("user", user);
    	
    	return "newBook.jsp";
    }
    
    @PostMapping(value="/books/create")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result
    		,Model model, HttpSession session) {
    	Long id = (Long)session.getAttribute("user_id");
    	User user = userServ.findUser(id);
    	model.addAttribute("user", user);
    	System.out.println("result" + result);
    	
    	if(result.hasErrors()) {
    		return "newBook.jsp";
    	} else {
    		bookServ.saveBook(book);
    		return "redirect:/books";
    	}
    }
    
    
    
    // ======================= Edit Routes ==========================
    @GetMapping("/books/{id}/edit")
    public String editBook(@PathVariable("id") Long id, Model model) {
    	Book book = bookServ.findBook(id);
    	model.addAttribute("book", book);
    	return "editBook.jsp";
    }
    
    @PutMapping("/books/{id}/update")
    public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result
    		, HttpSession session) {
    	if(session.getAttribute("user_id") == null) {
    		return "forbidden.jsp";
    	}
   
    	if(result.hasErrors()) {
    		return "editBook.jsp";
    	} else {
    		bookServ.saveBook(book);
    		return "redirect:/books";
    	}
    }
    
    
   // ===========================Delete Routes ===========================
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, HttpSession session) {
    	if(session.getAttribute("user_id") == null) {
    		return "forbidden.jsp";
    	}
    	bookServ.deleteBook(id);
    	return "redirect:/books";
    }
    
}

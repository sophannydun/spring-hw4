package com.spn.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.spn.Model.Book;
import com.spn.Service.BookService;
import com.spn.Service.BookServiceImp;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value= {"/","/index","/home"})
	public String allBook(Model model) {
		System.out.println("home controller");
		List<Book> listBook = bookService.findAll();
		model.addAttribute("listBook", listBook);
		return "index";
	}
	
	
	@RequestMapping(value="/book/{id}")
	public String bookDetail(@PathVariable("id") Integer id,Model model){
		Book book=bookService.findById(id);
		model.addAttribute("bookdetail", book);
		return "bookDetail";
	}
	
	@RequestMapping(value="/book/add")
	public String addBook(Model model){
		model.addAttribute("addStatus", true);
		model.addAttribute("book", new Book());
		return "addbook";
	}
	@RequestMapping(value="/book/add",method= RequestMethod.POST)
	public String actionAddBook(Book book){
		
		boolean b= bookService.save(book);
		return "redirect:index";
		
	}

	@GetMapping("/book/edit")
	public String editBook(Model model, @RequestParam("id") Integer id){
		System.out.println("Id: " + id);
		Book book =bookService.findById(id);
		model.addAttribute("book", book);
		model.addAttribute("addStatus", false);
		return "addbook";
	}
	@PostMapping("/book/update") 
	public String updateUser(Book book){
		System.out.println(book);
		bookService.update(book);
		return "redirect:index";
	}
	@RequestMapping(value="/book/remove", method= RequestMethod.POST)
	public String remove(@RequestParam("id") Integer id){
		System.out.println("Id: " + id);
		bookService.remove(id);
		return "redirect:index";
	}
}

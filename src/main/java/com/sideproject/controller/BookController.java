package com.sideproject.controller;

import com.sideproject.dao.BookRepository;
import com.sideproject.dto.BookDto;
import com.sideproject.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Api(tags = "Book")
@RestController
@RequestMapping(value = "/api")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	//	@ApiOperation(value = "取得書本", notes = "列書所有書本")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/v1/book", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	//	@ApiOperation(value = "新增書本", notes = "新增書本的內容")
//	@ApiResponses(value = {@ApiResponse(code = 201, message = "存檔成功")})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/v1/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookDto create(@RequestBody BookDto bookDto) {
		Book book = new Book();
		book.setBookid(bookDto.getBookid());
		book.setName(bookDto.getName());
		book.setAuthor(bookDto.getAuthor());
		book = bookRepository.save(book);
		bookDto.setBookid(book.getBookid());
		return bookDto;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/v1/book/{bookid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookDto get(@PathVariable Integer bookid) {
		Book book = bookRepository.findById(bookid).orElse(new Book());

		BookDto bookDto = new BookDto();
		bookDto.setBookid(book.getBookid());
		bookDto.setName(book.getName());
		bookDto.setAuthor(book.getAuthor());
		return bookDto;
	}
}

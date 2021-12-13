package com.ns.bookclub.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ns.bookclub.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
List<Book> findAll();
Optional<Book> findById(Long id);
void deleteById(Long id);
}

package com.github.leosilvadev.store.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.leosilvadev.store.domain.Store;

@Repository
public interface StoreRepository extends PagingAndSortingRepository<Store, String> {

}
